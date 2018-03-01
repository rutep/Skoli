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

    static String[] vars;
    static String[] decles;

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
    
    // generateProgam()
    // generateFuncion()
    // generateExpr()
    static public void main( String[] args ) throws Exception
    {
        try
        {
            NanoMorphoLexer.startLexer(args[0]);
            Object[] intermediate =  program();
        }
        catch( Throwable e )
        {
            System.out.println(e.getMessage());
        }
    }

    static Object[] program() throws Exception
    {
        Vector<Object> res = new Vector<Object>();
        while( getToken1()!=0 ){
            res.add(function());
        } 
        return res.toArray();
    }

    static Object[] function() throws Exception
    {
        Vector<String> args = new Vector<String>();
        Vector<Object> varArgs = new Vector<Object>();
        Vector<Object> exprArgs = new Vector<Object>();
        
        over(NAME); over('(');
        args.add("N");
        if( getToken1()!=')' )
        {
            for(;;)
            {
                over(NAME);
                args.add("N");
                if( getToken1()!=',' ) break;
                over(',');
            }
        }
        over(')'); over('{');
        while( getToken1()==VAR )
        {   
            varArgs.add(decl()); over(';');
        }
        while( getToken1()!='}' )
        {
            exprArgs.add(expr());
            over(';');
        }
        over('}');
        vars = new String[args.size()];
        args.toArray(vars);
        

        // geima fall með stærð og fjöld var og expr
        Object[] res = new Object[]{vars[0],vars.length-1,varArgs,exprArgs};
        return res;
    }

    static String[] decl() throws Exception
    {
        Vector<String> args = new Vector<String>();
        
        over(VAR);
        args.add("V");
        for(;;)
        {
            args.add("N");
            over(NAME);
            if( getToken1()!=',' ) break;
            over(',');
        }
        vars = new String[args.size()];
        args.toArray(vars);
        return vars;
    }

    static Object expr() throws Exception
    {   
        Vector<Object> exprArgs = new Vector<Object>();
        Vector<String> args = new Vector<String>();
        
        if( getToken1()==RETURN )
        {
            args.add("R");
            over(RETURN); exprArgs.add(expr());
        }
        else if( getToken1()==NAME && NanoMorphoLexer.getToken2()=='=' )
        {
            args.add("N");
            over(NAME); over('='); exprArgs.add(expr());
        }
        else
        {
            binopexpr();
        }
        vars = new String[args.size()];
        args.toArray(vars);

        Object[] res = new Object[]{"1"};
        return res;
    }

    static void  binopexpr() throws Exception
    {
        smallexpr();
        while( getToken1()==OPNAME )
        {
            over(OPNAME); smallexpr();
        }
    }

    static void smallexpr() throws Exception
    {
        switch( getToken1() )
        {
        case NAME:
            over(NAME);
            if( getToken1()=='(' )
            {
                over('(');
                if( getToken1()!=')' )
                {
                    for(;;)
                    {
                        expr();
                        if( getToken1()==')' ) break;
                        over(',');
                    }
                }
                over(')');
            }
            return;
        case WHILE:
            over(WHILE); expr(); body(); return;
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
            return;
        case LITERAL:
            over(LITERAL); return;
        case OPNAME:
            over(OPNAME); smallexpr(); return;
        case '(':
            over('('); expr(); over(')'); return;
        default:
            NanoMorphoLexer.expected("expression");
        }
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
}