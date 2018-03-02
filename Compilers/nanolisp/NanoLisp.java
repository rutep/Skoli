


import java.io.Reader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;

public class NanoLisp
{
	
	
	
	
	
	
	
	enum CodeType
	{
		IF, LITERAL, NAME, CALL
	};

	
	
	
	
	
	
	
	static class LexCase
	{
		final Pattern pat;
		final char token;
		int end;
		
		
		
		
		
		
		
		
		
		
		
		public LexCase( String p, char t )
		{
			pat = Pattern.compile(p,Pattern.MULTILINE);
			token = t;
		}

		
		
		
		
		
		
		public boolean match( String s, int pos )
		{
			Matcher m = pat.matcher(s).region(pos,s.length());
			boolean res = m.lookingAt();
			if( res ) end = m.end();
			return res;
		}

		
		
		
		
		
		
		public int end()
		{
			return end;
		}
	}

	
	
	
	
	static class Lexer
	{
		final LexCase[]
			cases =
				{ new LexCase("\\G\\(",'(')
				, new LexCase("\\G\\)",')')
				, new LexCase("\\G(\\s|(;.*$)|\\r|\\n)",'C')
				, new LexCase("\\G\\d+(\\.(\\d)+([eE][+\\-]?\\d+)?)?",'L')
				, new LexCase("\\G\\\"([^\\\\\"]|\\t|\\n|\\r|\\f)*\\\"",'L')
				, new LexCase("\\G\\\'([^\\\\\']|\\t|\\n|\\r|\\f)\\\'",'L')
				, new LexCase("\\G(\\p{Alpha}|[+\\-\\.*/<=>!\\?:$%_&~^0-9])+",'N')
				, new LexCase(".",'?')
				};
		final String input;
		int i;
		char token;
		String lexeme;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		public Lexer( Reader r ) throws IOException
		{
			StringBuffer b = new StringBuffer();
			char[] buf = new char[100000];
			for(;;)
			{
				int n = r.read(buf);
				if( n == -1 ) break;
				b.append(buf,0,n);
			}
			input = b.toString();
			i = 0;
			advance();
		}

		
		
		
		char getToken()
		{
			return token;
		}

		
		
		String getLexeme()
		{
			return lexeme;
		}

		
		
		
		void advance()
		{
			for(;;)
			{
				if( i>=input.length() )
				{
					token = '$';
					lexeme = "EOF";
					return;
				}
				for( int k=0 ; k!=cases.length ; k++ )
				{
					Matcher m = cases[k].pat.matcher(input);
					if( m.find(i) )
					{
						int j = m.end();
						token = cases[k].token;
						if( token=='C' )
						{
							i = j;
							break;
						}
						lexeme = input.substring(i,j);
						i = j;
						if( token=='N' )
						{
							if( lexeme.equals("if") )
								token = 'I';
							else if( lexeme.equals("define") )
								token = 'D';
							else if( lexeme.equals("null") )
								token = 'L';
							else if( lexeme.equals("true") )
								token = 'L';
							else if( lexeme.equals("false") )
								token = 'L';
						}
						return;
					}
				}
			}
		}

		
		
		
		
		
		static String tokenName( char t )
		{
			switch( t )
			{
			case '(': return "(";
			case ')': return ")";
			case 'N': return "name";
			case 'L': return "literal";
			case 'D': return "define";
			case 'I': return "if";
			case '$': return "EOF";
			}
			return "?";
		}

		
		
		
		
		
		
		
		String over( char tok )
		{
			if( token!=tok ) throw new Error("Expected "+tokenName(tok)+", found "+lexeme);
			String res = lexeme;
			advance();
			return res;
		}
	}

	
	Lexer lex;
	
	
	
	
	String[] vars;

	
	
	
	
	public NanoLisp( Lexer lexer )
	{
		lex = lexer;
	}

	
	
	
	
	
	int varPos( String name )
	{
		for( int i=1 ; i!=vars.length ; i++ )
			if( vars[i].equals(name) ) return i-1;
		throw new Error("Variable "+name+" is not defined");
	}

	
	
	
	
	
	
	
	
	Object[] program()
	{
		Vector<Object> res = new Vector<Object>();
		while( lex.getToken() == '(' ) res.add(fundecl());
		return res.toArray();
	}

	
	
	
	
	
	
	
	Object[] fundecl()
	{
		lex.over('(');
		lex.over('D');
		lex.over('(');
		Vector<String> args = new Vector<String>();
		args.add(lex.over('N'));
		while( lex.getToken()!=')' ) args.add(lex.over('N'));
		lex.over(')');
		vars = new String[args.size()];
		args.toArray(vars);
		Object[] res = new Object[]{vars[0],vars.length-1,expr()};
		vars = null;
		lex.over(')');
		return res;
	}

	
	
	
	
	
	
	
	Object[] expr()
	{
		Object[] res;
		switch( lex.getToken() )
		{
		case 'L':
			res = new Object[]{CodeType.LITERAL,lex.getLexeme()};
			lex.advance();
			return res;
		case 'N':
			res = new Object[]{CodeType.NAME,varPos(lex.getLexeme())};
			lex.advance();
			return res;
		case '(':
			lex.advance();
			switch( lex.getToken() )
			{
			case 'N':
				String name = lex.over('N');
				Vector<Object> args = new Vector<Object>();
				while( lex.getToken()!=')' ) args.add(expr());
				lex.advance();
				return new Object[]{CodeType.CALL,name,args.toArray()};
			case 'I':
				Object cond,thenexpr,elseexpr;
				lex.advance();
				cond = expr();
				thenexpr = expr();
				elseexpr = expr();
				lex.over(')');
				return new Object[]{CodeType.IF,cond,thenexpr,elseexpr};
			}
			throw new Error("Expected a name or the keyword 'if', found "+lex.getLexeme());
		default:
			throw new Error("Expected an expression, found "+lex.getLexeme());
		}
	}

	
	
	
	static void emit( String line )
	{
		System.out.println(line);
	}

	
	
	
	
	
	
	static void generateProgram( String name, Object[] p )
	{
		emit("\""+name+".mexe\" = main in");
		emit("!{{");
		for( int i=0 ; i!=p.length ; i++ ) generateFunction((Object[])p[i]);
		emit("}}*BASIS;");
	}

	
	
	
	
	static void generateFunction( Object[] f )
	{
		
		String fname = (String)f[0];
		int count = (Integer)f[1];
		emit("#\""+fname+"[f"+count+"]\" =");
		emit("[");
		generateExprR((Object[])f[2]);
		emit("];");
	}

	static int nextLab = 1;

	
	
	
	
	
	static int newLab()
	{
		return nextLab++;
	}

	
	
	
	
	
	
	static void generateExpr( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			
			emit("(Fetch "+e[1]+")");
			return;
		case LITERAL:
			
			emit("(MakeVal "+(String)e[1]+")");
			return;
		case IF:
			
			int labElse = newLab();
			int labEnd = newLab();
			generateJump((Object[])e[1],0,labElse);
			generateExpr((Object[])e[2]);
			emit("(Go _"+labEnd+")");
			emit("_"+labElse+":");
			generateExpr((Object[])e[3]);
			emit("_"+labEnd+":");
			return;
		case CALL:
			
			Object[] args = (Object[])e[2];
			int i;
			for( i=0 ; i!=args.length ; i++ )
				if( i==0 )
					generateExpr((Object[])args[i]);
				else
					generateExprP((Object[])args[i]);
			emit("(Call #\""+e[1]+"[f"+i+"]\" "+i+")");
			return;
		}
	}

	
	
	
	
	
	
	
	
	
	
	static void generateJump( Object[] e, int labTrue, int labFalse )
	{
		switch( (CodeType)e[0] )
		{
		case LITERAL:
			String literal = (String)e[1];
			if( literal.equals("false") || literal.equals("null") )
			{
				if( labFalse!=0 ) emit("(Go _"+labFalse+")");
				return;
			}
			if( labTrue!=0 ) emit("(Go _"+labTrue+")");
			return;
		default:
			generateExpr(e);
			if( labTrue!=0 ) emit("(GoTrue _"+labTrue+")");
			if( labFalse!=0 ) emit("(GoFalse _"+labFalse+")");
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	static void generateJumpP( Object[] e, int labTrue, int labFalse )
	{
		switch( (CodeType)e[0] )
		{
		case LITERAL:
			String literal = (String)e[1];
			emit("(Push)");
			if( literal.equals("false") || literal.equals("null") )
			{
				if( labFalse!=0 ) emit("(Go _"+labFalse+")");
				return;
			}
			if( labTrue!=0 ) emit("(Go _"+labTrue+")");
			return;
		default:
			generateExprP(e);
			if( labTrue!=0 ) emit("(GoTrue _"+labTrue+")");
			if( labFalse!=0 ) emit("(GoFalse _"+labFalse+")");
		}
	}

	
	
	
	
	
	
	
	
	
	static void generateExprR( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			
			emit("(FetchR "+e[1]+")");
			return;
		case LITERAL:
			
			emit("(MakeValR "+(String)e[1]+")");
			return;
		case IF:
			
			int labElse = newLab();
			generateJump((Object[])e[1],0,labElse);
			generateExprR((Object[])e[2]);
			emit("_"+labElse+":");
			generateExprR((Object[])e[3]);
			return;
		case CALL:
			
			Object[] args = (Object[])e[2];
			int i;
			for( i=0 ; i!=args.length ; i++ )
				if( i==0 )
					generateExpr((Object[])args[i]);
				else
					generateExprP((Object[])args[i]);
			emit("(CallR #\""+e[1]+"[f"+i+"]\" "+i+")");
			return;
		}
	}

	
	
	
	
	
	
	
	
	
	static void generateExprP( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			
			emit("(FetchP "+e[1]+")");
			return;
		case LITERAL:
			
			emit("(MakeValP "+(String)e[1]+")");
			return;
		case IF:
			
			int labElse = newLab();
			int labEnd = newLab();
			generateJumpP((Object[])e[1],0,labElse);
			generateExpr((Object[])e[2]);
			emit("(Go _"+labEnd+")");
			emit("_"+labElse+":");
			generateExpr((Object[])e[3]);
			emit("_"+labEnd+":");
			return;
		case CALL:
			
			Object[] args = (Object[])e[2];
			int i;
			for( i=0 ; i!=args.length ; i++ ) generateExprP((Object[])args[i]);
			if( i==0 ) emit("(Push)");
			emit("(Call #\""+e[1]+"[f"+i+"]\" "+i+")");
			return;
		}
	}

	
	
	
	
	
	
	
	
	
	public static void main( String[] args )
		throws IOException
	{
		Lexer lexer = new Lexer(new FileReader(args[0]));
		String name = args[0].substring(0,args[0].lastIndexOf('.'));
		NanoLisp parser = new NanoLisp(lexer);
		Object[] intermediate = parser.program();
		if( lexer.getToken()!='$' ) throw new Error("Expected EOF, found "+lexer.getLexeme());
		generateProgram(name,intermediate);
	}
}
