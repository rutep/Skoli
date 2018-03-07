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
    
    static int CALL = 0;
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

    static String getLexeme() throws Exception{
        return NanoMorphoLexer.getLexeme();
    }
    
    static int getToken1()
    {
        return NanoMorphoLexer.getToken1();
    }
    
    static public void main( String[] args ) throws Exception
    {
        try
        {
            NanoMorphoLexer.startLexer(args[0]);
            program(args[0].substring(0,args[0].lastIndexOf('.')));
        }
        catch( Throwable e )
        {
            System.out.println("villa");
            System.out.println(e.getMessage());
        }
    }

    static void program(String proName) throws Exception
    {
        emit("\""+proName+".mexe\" = main in");
		emit("!{{");
		while( getToken1()!=0 ) generateFunction(function());
        emit("}}*BASIS;");

    }

    static HashMap<String,Integer> varTable = new HashMap<String,Integer>();
    static int hashCount = 0;
    static int acount = 0;
    static int vcount = 0;
        

    static Object[] function() throws Exception
    {   
        acount = 0;
        vcount = 0;
        hashCount = 0;
        varTable.clear();
        Vector<Object[]> res = new Vector<Object[]>();
        String funName = getLexeme();
        over(NAME); over('(');
        if( getToken1()!=')' )
        {
            for(;;)
            {
                acount++;
                varTable.put(getLexeme(),hashCount++);
                over(NAME);
                if( getToken1()!=',' ) break;
                over(',');
            }
        }
        over(')'); over('{');
        while( getToken1()==VAR )
        {
            decl(); over(';');
        }
        while( getToken1()!='}' )
        {
            res.add(expr()); over(';');
        }
        over('}');
        return new Object[]{funName,acount,vcount,res.toArray()};
    }

    static void decl() throws Exception
    {
        over(VAR);
        for(;;)
        {
            varTable.put(getLexeme(),hashCount++);
            over(NAME);
            vcount++;
            if( getToken1()!=',' ) break;
            over(',');
        }
    }

    static Object[] expr() throws Exception
    {
        Object[] res = new Object[]{};
        res = new Object[]{RETURN,expr()};
        if( getToken1()==RETURN )
        {   
            over(RETURN);
            res =  new Object[]{RETURN,expr()};
            return res;
        }
        else if( getToken1()==NAME && NanoMorphoLexer.getToken2()=='=' )
        {
            res = new Object[]{NAME,varTable.get(getLexeme())};
            over(NAME); over('='); expr();
            return res;
        }
        else
        {
            res = binopexpr();
        }
        return res;
    }

    static Object[]  binopexpr() throws Exception
    {
        Vector<Object[]> res = new Vector<Object[]>();
        res.add(CALL,smallexpr());
        while( getToken1()==OPNAME )
        {
            over(OPNAME); res.add(new Object[]{OPNAME,smallexpr()});
        }
        return res.toArray();
    }

    static Object[] smallexpr() throws Exception
    {
        Object[] res = new Object[]{};
        Vector<Object[]> args = new Vector<Object[]>();
        res = new Object[]{RETURN};
        
        switch( getToken1() )
        {
        case NAME:
            String name = getLexeme()
;            over(NAME);
            if( getToken1()=='(' )
            {
                over('(');
                if( getToken1()!=')' )
                {
                    for(;;)
                    {
                        args.add(expr());
                        if( getToken1()==')' ) break;
                        over(',');
                    }
                }
                over(')');
                return new Object[]{CALL,name,args.toArray()};
            
            }
            return res;
        case WHILE:
            over(WHILE); expr(); body(); return res;
        case IF:
            over(IF); expr(); body();
            while( getToken1()==ELSIF )
            {
                over(ELSIF); expr(); body();
            }
            if( getToken1()==ELSE )
            {
                over(ELSE); body();
            }
            return res;
        case LITERAL:
            res = new Object[]{LITERAL,getLexeme()};
            over(LITERAL); return res;
        case OPNAME:
            over(OPNAME); smallexpr(); return res;
        case '(':
            over('('); expr(); over(')'); return res;
        default:
            NanoMorphoLexer.expected("expression");
        }
        
        return res;
    }

    static void body() throws Exception
    {
        over('{');
        while( getToken1()!='}' )
        {
            expr(); over(';');
        }
        over('}');
    }

    static void generateFunction( Object[] f )
	{
		// f = {fname,acount,vcount,expr[]}
		String fname = (String)f[0];
        int count = (Integer)f[1];
		emit("#\""+fname+"[f"+count+"]\" =");
        emit("[");
        Object[] arr = (Object[])f[3];
        for(int i = 0; i < arr.length; i++)
        generateExpr((Object[])arr[i]);
		emit("];");
	}

    static void generateExpr(Object[] e){
        
        // System.out.println(e[0]);
        
        if((int)e[0] == NAME){
            emit("(Fetch "+e[1]+")");
            emit("(Push)");
        }
        if((int)e[0] == LITERAL){
            emit("(MakeVal "+(String)e[1]+")");
        }
        if ((int)e[0] == RETURN) {
            emit("(Return)");
            // generateExpr((Object[])e[1]);
        }
        /**
        if (((String)e[0]).equals("CALL")){
            generateExpr((Object[])e[1]);
        }
        */
    }

    static void emit(String s){
        System.out.println(s);
    }
}