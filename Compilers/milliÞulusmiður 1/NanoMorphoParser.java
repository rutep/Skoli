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
    /**
    * Fetch
    * Store
    * MakeVal
    * Call
    * Go
    * GoTrue
    * GoFalse
    * Push
    * Return
    * Not
    */
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
    private static int hashCount = 0;
    private static HashMap<String,Integer> varTable = new HashMap<String,Integer>();
    static Object[] function() throws Exception
    {
        varTable = new HashMap<String,Integer>();
        Vector<Object> exprArgs = new Vector<Object>();
        
        int farg = 0;
        int vname = 0;
        over(NAME); over('(');
        if( getToken1()!=')' )
        {
            for(;;)
            {
                varTable.put(NanoMorphoLexer.getLexeme(),hashCount);
                farg++;
                over(NAME);
                if( getToken1()!=',' ) break;
                over(',');
            }
        }
        over(')'); over('{');
        while( getToken1()==VAR )
        {
            vname = decl(); over(';');
        }
        while( getToken1()!='}' )
        {
            exprArgs.add(expr()); 
            over(';');
        }
        over('}');
        
        return new Object[]{"f",farg,vname,exprArgs.toArray()};
    }

    static int decl() throws Exception
    {
        int count = 0;
        over(VAR);
        for(;;)
        {
            varTable.put(NanoMorphoLexer.getLexeme(),hashCount);
            count++;
            over(NAME);
            if( getToken1()!=',' ) break;
            over(',');
        }
        return count;
    }

    static Object[] expr() throws Exception
    {
        Vector<Object> exprArgs = new Vector<Object>();
        if( getToken1()==RETURN )
        {
            over(RETURN);
            return new Object[]{"Return",expr()};
        }   
        else if( getToken1()==NAME && NanoMorphoLexer.getToken2()=='=' )
        {
            over(NAME); over('='); 
            return new Object[]{"Store",expr()};
        }
        else
        {
            binopexpr();
        }
        return exprArgs.toArray();
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