import java.util.Vector;

import javafx.beans.binding.ObjectExpression;

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

    static String getLexeme() throws Exception{
        return NanoMorphoLexer.getLexeme();
    }
    static void program(String proName) throws Exception
    {   
        Vector<Object[]> res = new Vector<Object[]>();        
        while( getToken1()!=0 ) res.add(function());
        generateProgram(proName, res.toArray());
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
            over(NAME);
            if( getToken1()!=',' ) break;
            over(',');
        }
    }

    static Object[] expr() throws Exception
    {
        Object[] res = new Object[]{};
        res = new Object[]{RETURN};
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
            binopexpr();
        }
        return res;
    }

     /**
     * (MakeVal null)
     * (Push) breitur og viðfaung
     * ATH AC(acumilator) Hlaða
     */
    static Object  binopexpr() throws Exception
    {
        Object[] e = smallexpr();
        smallexpr();
        while( getToken1()==OPNAME )
        {   
            String op = getLexeme();
            over(OPNAME); 
            Object[] r = smallexpr();
            e = new Object[]{"CALL",op,2,new Object[]{e,r}};
        }
        return e;
    }

    static Object[] smallexpr() throws Exception
    {
        Object[] res = new Object[]{};
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
        Object[] args = (Object[])f[3];
		emit("#\""+fname+"[f"+count+"]\" =");
        emit("[");
        emit("(MakeVal null)");
        while(vcount != -1){
            emit("(Push)"); vcount--;
        } 
        while(acount != -1){
            emit("(Push)"); acount--;
        } 
        for(int i = 0; i < args.length; i++) generateExpr((Object[])args[i]);
        emit("];");
    }

    static void generateProgram( String name, Object[] p )
	{
		emit("\""+name+".mexe\" = main in");
		emit("!{{");
		for( int i=0 ; i!=p.length ; i++ ) generateFunction((Object[])p[i]);
		emit("}}*BASIS;");
	}

    static void generateExpr(Object[] e){
        
        if (e == null) {
            return;
        }
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
    }

    static void emit(String s){
        System.out.println(s);
    }
}