import java.util.Vector;
import java.util.HashMap;

public class NanoMorphoParser
{
    final static int ERROR = -1;
    final static int IF = 1001;
    final static int ELSE = 1002;
    final static int ELSIF = 1003;
    final static int WHILE = 1004;
    final static int VAR = 1005;
    final static int RETURN = 1006;
    final static int NAME = 1007;
    final static int OPNAME = 1008;
    final static int LITERAL = 1009;

    static String advance() throws Exception
    {
        return NanoMorphoLexer.advance();
    }
    
    static String over( int tok ) throws Exception
    {
        return NanoMorphoLexer.over(tok);
    }
    
    static String over( char tok ) throws Exception
    {
        return NanoMorphoLexer.over(tok);
    }
    
    static int getToken1()
    {
        return NanoMorphoLexer.getToken1();
    }
    
	private static int varCount;
	private static HashMap<String,Integer> varTable;

	private static void addVar( String name )
	{
		if( varTable.get(name) != null )
			throw new Error("Variable "+name+" already exists, near line "+NanoMorphoLexer.getLine());
		varTable.put(name,varCount++);
	}

	private static int findVar( String name )
	{
		Integer res = varTable.get(name);
		if( res == null )
			throw new Error("Variable "+name+" does not exist, near line "+NanoMorphoLexer.getLine());
		return res;
	}
    
    static public void main( String[] args ) throws Exception
    {
        Object[] code = null;
        try
        {
            NanoMorphoLexer.startLexer(args[0]);
            code = program();
        }
        catch( Throwable e )
        {
            System.out.println(e.getMessage());
        }
        generateProgram(args[0],code);
    }

    static Object[] program() throws Exception
    {
        Vector<Object> prog = new Vector<Object>();
        while( getToken1()!=0 ) prog.add(function());
        return prog.toArray();
    }

    static Object[] function() throws Exception
    {
        varCount = 0;
        varTable = new HashMap<String,Integer>();
        String fname;
        int argcount = 0, varcount = 0;
        Vector<Object> fbody = new Vector<Object>();
        if( getToken1()!=NAME && getToken1()!=OPNAME )
            NanoMorphoLexer.expected("name or operation name");
        fname = advance(); over('(');
        if( getToken1()!=')' )
        {
            addVar(over(NAME));
            argcount++;
            while( getToken1()==',' )
            {
                over(','); addVar(over(NAME)); argcount++;
            }
        }
        over(')'); over('{');
        while( getToken1()==VAR )
        {
            varcount += decl(); over(';');
        }
        while( getToken1()!='}' )
        {
            fbody.add(expr()); over(';');
        }
        over('}');
        return new Object[]{fname,argcount,varcount,fbody.toArray()};
    }

    static int decl() throws Exception
    {
        int varcount = 1;
        over(VAR); addVar(over(NAME));
        while( getToken1()==',' )
        {
            over(','); addVar(over(NAME)); varcount++;
        }
        return varcount;
    }

    static Object[] expr() throws Exception
    {
        if( getToken1()==RETURN )
        {
            over(RETURN);
            return new Object[]{"RETURN",expr()};
        }
        else if( getToken1()==NAME && NanoMorphoLexer.getToken2()==(int)'=' )
        {
            int pos = findVar(advance()); advance();
            return new Object[]{"STORE",pos,expr()};
        }
        else
        {
            return binopexpr(1);
        }
    }

    static Object[] binopexpr( int pri ) throws Exception
    {
        if( pri>7 )
            return smallexpr();
        else if( pri==2 )
        {
            Object[] e = binopexpr(3);
            if( getToken1()==OPNAME && priority(NanoMorphoLexer.getLexeme())==2 )
            {
                String op = advance();
                e = new Object[]{"CALL",op,new Object[]{e,binopexpr(2)}};
            }
            return e;
        }
        else
        {
            Object[] e = binopexpr(pri+1);
            while( getToken1()==OPNAME && priority(NanoMorphoLexer.getLexeme())==pri )
            {
                String op = advance();
                e = new Object[]{"CALL",op,new Object[]{e,binopexpr(pri+1)}};
            }
            return e;
        }
    }
    
    static int priority( String opname )
    {
        switch( opname.charAt(0) )
        {
        case '^':
        case '?':
        case '~':
            return 1;
        case ':':
            return 2;
        case '|':
            return 3;
        case '&':
            return 4;
        case '!':
        case '=':
        case '<':
        case '>':
            return 5;
        case '+':
        case '-':
            return 6;
        case '*':
        case '/':
        case '%':
            return 7;
        default:
            throw new Error("Invalid opname");
        }
    }

    static Object[] smallexpr() throws Exception
    {
        switch( getToken1() )
        {
        case NAME:
            {
                String name = advance();
                if( getToken1()!=(int)'(' )
                    return new Object[]{"FETCH",findVar(name)};
                advance();
                Vector<Object> args = new Vector<Object>();
                if( getToken1()!=')' )
                {
                    args.add(expr());
                    while( getToken1()==',' )
                    {
                        advance(); args.add(expr());
                    }
                }
                over(')');
                return new Object[]{"CALL",name,args.toArray()};
            }
        case WHILE:
            {
                advance(); Object[] cond = expr(); Object[] bod = body();
                return new Object[]{"WHILE",cond,bod};
            }
        case IF:
            {
                advance();
                over('(');
                Object[] cond = expr();
                over(')');
                Object[] thenpart = body();
                return ifrest(cond,thenpart);
            }
        case LITERAL:
            return new Object[]{"LITERAL",advance()};
        case OPNAME:
            return new Object[]{"CALL",advance(),new Object[]{smallexpr()}};
        case '(':
            advance(); Object[] res = expr(); over(')');
            return res;
        default:
            NanoMorphoLexer.expected("expression");
        }
        return null;
    }
    
    static Object[] ifrest( Object[] cond, Object[] thenpart ) throws Exception
    {
        switch( getToken1() )
        {
        case ELSIF:
            {
                advance();
                over('(');
                Object[] nextcond = expr();
                over(')');
                Object[] nextbod = body();
                return new Object[]{"IF2",cond,thenpart,ifrest(nextcond,nextbod)};
            }
        case ELSE:
            {
                advance();
                Object[] elsepart = body();
                return new Object[]{"IF2",cond,thenpart,elsepart};
            }
        default:
            return new Object[]{"IF1",cond,thenpart};
        }
    }

    static Object[] body() throws Exception
    {
        over('{');
        Vector<Object> res = new Vector<Object>();
        while( getToken1()!='}' )
        {
            res.add(expr()); over(';');
        }
        advance();
        return res.toArray();
    }
    
    static void generateProgram( String filename, Object[] funs )
    {
        String programname = filename.substring(0,filename.indexOf('.'));
        System.out.println("\""+programname+".mexe\" = main in");
        System.out.println("!");
        System.out.println("{{");
        for( Object f: funs )
        {
            generateFunction((Object[])f);
        }
        System.out.println("}}");
        System.out.println("*");
        System.out.println("BASIS;");
    }
    
    static void generateFunction( Object[] fun )
    {
        System.out.println("#\""+fun[0]+"[f"+fun[1]+"]\" =");
        System.out.println("[");
        int varcount = (Integer)fun[2];
        if( varcount!=0 ) System.out.println("(MakeVal null)");
        for( int i=0 ; i!=varcount ; i++ ) System.out.println("(Push)");
        Object[] exprs = (Object[])fun[3];
        for( Object e: exprs )
        {
            generateExpr((Object[])e);
        }
        System.out.println("(Return)");
        System.out.println("];");
    }
    
    static int nextLab = 0;
    
    static void generateExpr( Object[] e )
    {
        switch( (String)e[0] )
        {
        case "FETCH":
            System.out.println("(Fetch "+e[1]+")");
            return;
        case "STORE":
            generateExpr((Object[])e[2]); System.out.println("(Store "+e[1]+")");
            return;
        case "IF1":
            {
                // ["IF1",cond,thenpart]
                int endlab = nextLab++;
                generateExpr((Object[])e[1]);
                System.out.println("(GoFalse _"+endlab+")");
                generateBody((Object[])e[2]);
                System.out.println("_"+endlab+":");
                return;
            }
        case "IF2":
            {
                // ["IF2",cond,thenpart,elsepart]
                int elslab = nextLab++;
                int endlab = nextLab++;
                generateExpr((Object[])e[1]);
                System.out.println("(GoFalse _"+elslab+")");
                generateBody((Object[])e[2]);
                System.out.println("(Go _"+endlab+")");
                System.out.println("_"+elslab+":");
                generateBody((Object[])e[3]);
                System.out.println("_"+endlab+":");
                return;
            }
        case "WHILE":
            {
                int startlab = nextLab++;
                int endlab = nextLab++;
                System.out.println("_"+startlab+":");
                generateExpr((Object[])e[1]);
                System.out.println("(GoFalse _"+endlab+")");
                generateBody((Object[])e[2]);
                System.out.println("(Go _"+startlab+")");
                System.out.println("_"+endlab+":");
                return;
            }
        case "CALL":
            {
                Object[] args = (Object[])e[2];
                if( args.length!=0 ) generateExpr((Object[])args[0]);
                for( int i=1 ; i<args.length ; i++ )
                {
                    System.out.println("(Push)");
                    generateExpr((Object[])args[i]);
                }
                System.out.println("(Call #\""+e[1]+"[f"+args.length+"]\" "+args.length+")");
                return;
            }
        case "RETURN":
            generateExpr((Object[])e[1]); System.out.println("(Return)");
            return;
        case "LITERAL":
            System.out.println("(MakeVal "+e[1]+")");
            return;
        default:
            throw new Error("Invalid expression type: "+e[0]);
        }
    }
    
    static void generateBody( Object[] bod )
    {
        for( Object e: bod )
        {
            generateExpr((Object[])e);
        }
    }
}
