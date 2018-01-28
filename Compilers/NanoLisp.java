// ���andi fyrir ofureinfalt NanoLisp forritunarm�l.
// H�fundur: Snorri Agnarsson, 2014-2017.

import java.io.Reader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;

public class NanoLisp
{
	// Eftirfarandi fastar standa fyrir allar ��r
	// m�gulegu ger�ir af seg�um sem milli�ula
	// (intermediate code) getur innihaldi�.
	// �essar fj�rar ger�ir seg�a (�samt �eim
	// m�guleika a� skrifa f�ll sem nota sl�kar
	// seg�ir) duga reyndar til a� h�gt s� a�
	// reikna hva� sem er reiknanlegt.
	enum CodeType
	{
		IF, LITERAL, NAME, CALL
	};

	// Tilvik af klasanum LexCase er skilgreining
	// � einhverju lesgreinanlegu fyrirb�ri, sem
	// m� �� koma fyrir � l�glegum forritstexta.
	// Lesgreinirinn � �essum ���anda er skrifa�ur
	// � mj�g frumst��an h�tt og a�fer�in er ekki
	// til eftirbreytni � neinum ���anda sem �tla�ur
	// er til raunverulegrar notkunar.
	static class LexCase
	{
		final Pattern pat;
		final char token;
		int end;
		
		// Notkun: LexCase c = new LexCase(p,t);
		// Fyrir:  p er strengur sem inniheldur reglulega
		//         seg� sem skilgreinir m�l sem inniheldur
		//         �� strengi sem sem eiga a� flokkast sem
		//         eitthvert tilteki� lesgreinanlegt
		//         fyrirb�ri (les, lexeme). t er stafur
		//         sem er �a� t�k (token) sem stendur
		//         fyrir �etta mengi strengja (�etta m�l).
		// Eftir:  c v�sar � hlut sem nota m� til a�
		//         bera kennsl � strengi � m�linu.
		public LexCase( String p, char t )
		{
			pat = Pattern.compile(p,Pattern.MULTILINE);
			token = t;
		}

		// Notkun: boolean b = c.match(s,pos);
		// Fyrir:  s er strengur og pos er sta�setning
		//         innan s.
		// Eftir:  b er satt ��aa sta�setningin pos v�si
		//         � byrjun hlutstrengs sem er � m�linu
		//         sem c skilgreinir.
		public boolean match( String s, int pos )
		{
			Matcher m = pat.matcher(s).region(pos,s.length());
			boolean res = m.lookingAt();
			if( res ) end = m.end();
			return res;
		}

		// Notkun: int i = c.end();
		// Fyrir:  B�i� er a� kalla c.match(s,pos) og f�
		//         sanna ni�urst��u.
		// Eftir:  i inniheldur sta�setningu innan s sem
		//         v�sar � n�sta staf � eftir �eim streng
		//         sem bori� var kennsl � � match kallinu.
		public int end()
		{
			return end;
		}
	}

	// Hlutur af tagi Lexer er lesgreinirinn � �essum ���anda.
	// �etta er �kaflega frumst��ur lesgreinir sem getur
	// lesgreint allt a� 100000 stafa inntaksskr�r og notar
	// frekar l�lega a�fer� til �ess.
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
		// Fastayr�ing gagna:
		//   input inniheldur forritstextann sem veri� er a� ���a.
		//   i er sta�setning n�sta �lesna stafs � forritstextanum.
		//   token er stafur sem stendur fyrir n�sta t�k sem ekki
		//   er b�i� a� vinna �r.  �a� samsvarar stafarunu r�tt fyrir
		//   framan sta�setninguna i.  lexeme er strengur sem
		//   inniheldur �� stafi �r input sem samsvara token.
		//   cases inniheldur skilgreiningar � �eim m�lum sem
		//   lesgreinirinn gerir greinarmun �.  Hvert stak �
		//   cases skilgreinir annars vegar eitthvert m�l (mengi
		//   strengja) sem bori� er kennsl � og hins vegar staf
		//   sem er samsvarandi t�k.  Merking t�kanna er:
		//     '(':  Strengurinn "("
		//     ')':  Strengurinn ")"
		//     'C':  Strengur sem er athugasemd e�a bilstafur,
		//           sem skal �v� ekki skila �fram til ��ttarans
		//     'L':  Strengur sem stendur fyrir lesfasta, �.e.
		//           t�lufasti, strengfasti, staffasti e�a einn
		//           af lesf�stunum true, false e�a null.
		//     'N':  Strengur sem er l�glegt breytunafn e�a nafn
		//           � falli.
		//     'I':  Lykilor�i� if.
		//     'D':  Lykilor�i� define.
		//     '$':  Skr�rlok, �.e. endir inntaksins.
		//   Athugi� a� nokkur breytun�fn eru �ess e�lis a� �egar
		//   lesgreinirinn skilar t�ki fyrir �au �� � hann a�
		//   segja a� �au s�u lesfastar.  �etta eru breytun�fnin
		//   true, false og null.  Lesgreinirinn �arf �v� a�
		//   athuga hvort um �essi s�rst�ku breytun�fn er a� r��a
		//   eftir a� � lj�s hefur komi� a� lesgreindi strengurinn
		//   er breytunafn.  Sama gildir um lykilor�in if og define.

		// Notkun: Lexer l = new Lexer(r);
		// Fyrir:  r er Reader sem inniheldur allt a� 100000
		//         stafi.
		// Eftir:  l v�sar � n�jan lesgreini sem lesgreinir
		//         innihaldi� � r.  Lesgreinirinn er � upphafi
		//         sta�settur � fremsta lesi (lexeme) � r.
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

		// Notkun: char c = l.getToken();
		// Eftir:  c er t�ki� (token) sem stendur fyrir
		//         �a� m�l sem n�sta les � l flokkast �.
		char getToken()
		{
			return token;
		}

		// Notkun: String s = l.getLexeme();
		// Eftir:  s er n�sta les � l.
		String getLexeme()
		{
			return lexeme;
		}

		// Notkun: l.advance();
		// Eftir:  l hefur f�rst �fram � n�sta les �
		//         inntakinu (sem ekki er athugasemd).
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

		// Notkun: String n = Lexer.tokenName(c);
		// Fyrir:  c er stafur sem er einn �eirra sem
		//         lexgreinirinn getur skila� sem t�k.
		// Eftir:  n er nafni� � �essu t�ki � mannlega
		//         l�silegu sni�i.
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

		// Notkun: l.over(c);
		// Fyrir:  c er stafur sem stendur fyrir m�gulegt t�k.
		//         N�sta t�k � l er c.
		// Eftir:  B�i� er a� f�ra lesgreininn eitt skref �fram
		//         eins og � advance().
		// Afbrig�i:  Ef svo vill til a� n�sta t�k � l er ekki
		//            c �� eru skrifu� villubo� og keyrslan st��vast.
		String over( char tok )
		{
			if( token!=tok ) throw new Error("Expected "+tokenName(tok)+", found "+lexeme);
			String res = lexeme;
			advance();
			return res;
		}
	}

	// lex er lesgreinirinn.
	Lexer lex;
	// Inni � hverri fallsskilgreiningu inniheldur vars n�fnin
	// � vi�f�ngunum � falli� (�.e. leppunum e�a breytun�fnunum
	// sem standa fyrir vi�f�ngin), � s�tum 1 og aftar.  S�ti
	// 0 inniheldur nafn fallsins sem veri� er a� skilgreina.
	String[] vars;

	// Notkun: NanoLisp n = new NanoLisp(l);
	// Fyrir:  l er lesgreinir.
	// Eftir:  n v�sar � n�jan NanoLisp ���anda sem ���ir inntaki�
	//         sem l hefur.
	public NanoLisp( Lexer lexer )
	{
		lex = lexer;
	}

	// Notkun: int i = n.varPos(name);
	// Fyrir:  n er NanoLisp ���andi og er a� ���a stofn einhvers
	//         falls.  name er nafni� � einhverju vi�fangi � falli�.
	// Eftir:  i er sta�setning vi�fangsins � vi�fangarunu fallsins
	//         �ar sem fyrsta vi�fang er tali� vera � s�ti 0.
	int varPos( String name )
	{
		for( int i=1 ; i!=vars.length ; i++ )
			if( vars[i].equals(name) ) return i-1;
		throw new Error("Variable "+name+" is not defined");
	}

	// Notkun: Object[] code = n.program();
	// Fyrir:  n er NanoLisp ���andi og inntaki� er l�glegt
	//         NanoLisp forrit.
	// Eftir:  B�i� er a� ���a forriti� og code v�sar � n�tt
	//         fylki sem inniheldur milli�ulurnar fyrir �ll
	//         f�llin � forritinu.
	// Afbrig�i: Ef forriti� er ekki l�glegt �� eru skrifu�
	//           villubo� og keyrslan st��vu�.
	Object[] program()
	{
		Vector<Object> res = new Vector<Object>();
		while( lex.getToken() == '(' ) res.add(fundecl());
		return res.toArray();
	}

	// Notkun: Object[] fun = n.fundecl();
	// Fyrir:  n er NanoLisp ���andi sem er sta�settur �
	//         byrjun fallsskilgreiningar.
	// Eftir:  B�i� er a� lesa fallsskilgreininguna og fun v�sar
	//         � n�tt fylki sem er milli�ulan fyrir falli�.
	//         ���andinn er n� a� horfa � n�sta t�kn � inntakinu
	//         fyrir aftan fallsskilgreininguna.
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

	// Notkun: Object[] e = n.expr();
	// Fyrir:  n er NanoLisp ���andi sem er sta�settur �
	//         byrjun seg�ar innan fallsskilgreiningar.
	// Eftir:  B�i� er a� lesa seg�ina og ���a hana.
	//         e v�sar � milli�uluna fyrir seg�ina.
	//         ���andinn er n� a� horfa � n�sta t�kn � inntakinu
	//         fyrir aftan seg�ina.
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

	// Notkun: emit(line);
	// Fyrir:  line er l�na � loka�ulu.
	// Eftir:  B�i� er a� skrifa l�nuna � a�al�ttak.
	static void emit( String line )
	{
		System.out.println(line);
	}

	// Notkun: generateProgram(name,p);
	// Fyrir:  name er strengur, p er fylki fallsskilgreininga,
	//         �.e. fylki af milli�ulum fyrir f�ll.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir forrit sem
	//         samanstendur af f�llunum �.a. name er nafn
	//         forritsins.
	static void generateProgram( String name, Object[] p )
	{
		emit("\""+name+".mexe\" = main in");
		emit("!{{");
		for( int i=0 ; i!=p.length ; i++ ) generateFunction((Object[])p[i]);
		emit("}}*BASIS;");
	}

	// Notkun: generateFunction(f);
	// Fyrir:  f er milli�ula fyrir fall.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir falli� �
	//         a�al�ttak.
	static void generateFunction( Object[] f )
	{
		// f = {fname,argcount,expr}
		String fname = (String)f[0];
		int count = (Integer)f[1];
		emit("#\""+fname+"[f"+count+"]\" =");
		emit("[");
		generateExprR((Object[])f[2]);
		emit("];");
	}

	static int nextLab = 1;

	// Notkun: int i = newLab();
	// Eftir:  i er j�kv�� heiltala sem ekki hefur ��ur
	//         veri� skila� �r �essu falli.  Tilgangurinn
	//         er a� b�a til n�tt merki (label), sem er
	//         ekki �a� sama og neitt anna� merki.
	static int newLab()
	{
		return nextLab++;
	}

	// Notkun: generateExpr(e);
	// Fyrir:  e er milli�ula fyrir seg�.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir seg�ina
	//         � a�al�ttak.  Loka�ulan reiknar gildi
	//         seg�arinnar og skilur gildi� eftir �
	//         gildinu ac.
	static void generateExpr( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			// e = {NAME,name}
			emit("(Fetch "+e[1]+")");
			return;
		case LITERAL:
			// e = {LITERAL,literal}
			emit("(MakeVal "+(String)e[1]+")");
			return;
		case IF:
			// e = {IF,cond,then,else}
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
			// e = {CALL,name,args}
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

	// Notkun: generateJump(e,labTrue,labTrue);
	// Fyrir:  e er milli�ula fyrir seg�, labTrue og
	//         labFalse eru heilt�lur sem standa fyrir
	//         merki e�a eru n�ll.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir seg�ina
	//         � a�al�ttak.  Loka�ulan veldur st�kki til
	//         merkisins labTrue ef seg�ina skilar s�nnu,
	//         annars st�kki til labFalse.  Ef anna� merki�
	//         er n�ll �� er �a� jafngilt merki sem er r�tt
	//         fyrir aftan �ulu seg�arinnar.
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

	// Notkun: generateJumpP(e,labTrue,labFalse);
	// Fyrir:  e er milli�ula fyrir seg�, labTrue og
	//         labFalse eru heilt�lur sem standa fyrir
	//         merki e�a eru n�ll.
	// Eftir:  �etta kall b�r til loka�ulu sem er jafngild
	//         �ulunni sem k�llin
	//            emit("(Push)");
	//            generateJump(e,labTrue,labFalse);
	//         framlei�a.  �ulan er samt ekki endilega s�
	//         sama og �essi k�ll framlei�a �v� tilgangurinn
	//         er a� geta framleitt betri �ulu.
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

	// Notkun: generateExpr(e);
	// Fyrir:  e er milli�ula fyrir seg�.
	// Eftir:  �etta kall b�r til loka�ulu sem er jafngild
	//         �ulunni sem k�llin
	//            generateExpr(e);
	//            emit("(Return)");
	//         framlei�a.  �ulan er samt ekki endilega s�
	//         sama og �essi k�ll framlei�a �v� tilgangurinn
	//         er a� geta framleitt betri �ulu.
	static void generateExprR( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			// e = {NAME,name}
			emit("(FetchR "+e[1]+")");
			return;
		case LITERAL:
			// e = {LITERAL,literal}
			emit("(MakeValR "+(String)e[1]+")");
			return;
		case IF:
			// e = {IF,cond,then,else}
			int labElse = newLab();
			generateJump((Object[])e[1],0,labElse);
			generateExprR((Object[])e[2]);
			emit("_"+labElse+":");
			generateExprR((Object[])e[3]);
			return;
		case CALL:
			// e = {CALL,name,args}
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

	// Notkun: generateExprP(e);
	// Fyrir:  e er milli�ula fyrir seg�.
	// Eftir:  �etta kall b�r til loka�ulu sem er jafngild
	//         �ulunni sem k�llin
	//            emit("(Push)");
	//            generateExpr(e);
	//         framlei�a.  �ulan er samt ekki endilega s�
	//         sama og �essi k�ll framlei�a �v� tilgangurinn
	//         er a� geta framleitt betri �ulu.
	static void generateExprP( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			// e = {NAME,name}
			emit("(FetchP "+e[1]+")");
			return;
		case LITERAL:
			// e = {LITERAL,literal}
			emit("(MakeValP "+(String)e[1]+")");
			return;
		case IF:
			// e = {IF,cond,then,else}
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
			// e = {CALL,name,args}
			Object[] args = (Object[])e[2];
			int i;
			for( i=0 ; i!=args.length ; i++ ) generateExprP((Object[])args[i]);
			if( i==0 ) emit("(Push)");
			emit("(Call #\""+e[1]+"[f"+i+"]\" "+i+")");
			return;
		}
	}

	// Notkun (af skipanal�nu):
	//        java NanoLisp forrit.s > forrit.masm
	// Fyrir: Skr�in forrit.s inniheldur l�glegt NanoLisp
	//        forit.
	// Eftir: B�i� er a� ���a forriti� og skrifa loka�uluna
	//        � skr�na forrit.masm.  S� s� loka�ula ��dd me�
	//        skipuninni
	//           morpho -c forrit.masm
	//        �� ver�ur til keyrsluh�fa Morpho skr�in forrit.mexe.
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
}// ���andi fyrir ofureinfalt NanoLisp forritunarm�l.
// H�fundur: Snorri Agnarsson, 2014-2017.

import java.io.Reader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;

public class NanoLisp
{
	// Eftirfarandi fastar standa fyrir allar ��r
	// m�gulegu ger�ir af seg�um sem milli�ula
	// (intermediate code) getur innihaldi�.
	// �essar fj�rar ger�ir seg�a (�samt �eim
	// m�guleika a� skrifa f�ll sem nota sl�kar
	// seg�ir) duga reyndar til a� h�gt s� a�
	// reikna hva� sem er reiknanlegt.
	enum CodeType
	{
		IF, LITERAL, NAME, CALL
	};

	// Tilvik af klasanum LexCase er skilgreining
	// � einhverju lesgreinanlegu fyrirb�ri, sem
	// m� �� koma fyrir � l�glegum forritstexta.
	// Lesgreinirinn � �essum ���anda er skrifa�ur
	// � mj�g frumst��an h�tt og a�fer�in er ekki
	// til eftirbreytni � neinum ���anda sem �tla�ur
	// er til raunverulegrar notkunar.
	static class LexCase
	{
		final Pattern pat;
		final char token;
		int end;
		
		// Notkun: LexCase c = new LexCase(p,t);
		// Fyrir:  p er strengur sem inniheldur reglulega
		//         seg� sem skilgreinir m�l sem inniheldur
		//         �� strengi sem sem eiga a� flokkast sem
		//         eitthvert tilteki� lesgreinanlegt
		//         fyrirb�ri (les, lexeme). t er stafur
		//         sem er �a� t�k (token) sem stendur
		//         fyrir �etta mengi strengja (�etta m�l).
		// Eftir:  c v�sar � hlut sem nota m� til a�
		//         bera kennsl � strengi � m�linu.
		public LexCase( String p, char t )
		{
			pat = Pattern.compile(p,Pattern.MULTILINE);
			token = t;
		}

		// Notkun: boolean b = c.match(s,pos);
		// Fyrir:  s er strengur og pos er sta�setning
		//         innan s.
		// Eftir:  b er satt ��aa sta�setningin pos v�si
		//         � byrjun hlutstrengs sem er � m�linu
		//         sem c skilgreinir.
		public boolean match( String s, int pos )
		{
			Matcher m = pat.matcher(s).region(pos,s.length());
			boolean res = m.lookingAt();
			if( res ) end = m.end();
			return res;
		}

		// Notkun: int i = c.end();
		// Fyrir:  B�i� er a� kalla c.match(s,pos) og f�
		//         sanna ni�urst��u.
		// Eftir:  i inniheldur sta�setningu innan s sem
		//         v�sar � n�sta staf � eftir �eim streng
		//         sem bori� var kennsl � � match kallinu.
		public int end()
		{
			return end;
		}
	}

	// Hlutur af tagi Lexer er lesgreinirinn � �essum ���anda.
	// �etta er �kaflega frumst��ur lesgreinir sem getur
	// lesgreint allt a� 100000 stafa inntaksskr�r og notar
	// frekar l�lega a�fer� til �ess.
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
		// Fastayr�ing gagna:
		//   input inniheldur forritstextann sem veri� er a� ���a.
		//   i er sta�setning n�sta �lesna stafs � forritstextanum.
		//   token er stafur sem stendur fyrir n�sta t�k sem ekki
		//   er b�i� a� vinna �r.  �a� samsvarar stafarunu r�tt fyrir
		//   framan sta�setninguna i.  lexeme er strengur sem
		//   inniheldur �� stafi �r input sem samsvara token.
		//   cases inniheldur skilgreiningar � �eim m�lum sem
		//   lesgreinirinn gerir greinarmun �.  Hvert stak �
		//   cases skilgreinir annars vegar eitthvert m�l (mengi
		//   strengja) sem bori� er kennsl � og hins vegar staf
		//   sem er samsvarandi t�k.  Merking t�kanna er:
		//     '(':  Strengurinn "("
		//     ')':  Strengurinn ")"
		//     'C':  Strengur sem er athugasemd e�a bilstafur,
		//           sem skal �v� ekki skila �fram til ��ttarans
		//     'L':  Strengur sem stendur fyrir lesfasta, �.e.
		//           t�lufasti, strengfasti, staffasti e�a einn
		//           af lesf�stunum true, false e�a null.
		//     'N':  Strengur sem er l�glegt breytunafn e�a nafn
		//           � falli.
		//     'I':  Lykilor�i� if.
		//     'D':  Lykilor�i� define.
		//     '$':  Skr�rlok, �.e. endir inntaksins.
		//   Athugi� a� nokkur breytun�fn eru �ess e�lis a� �egar
		//   lesgreinirinn skilar t�ki fyrir �au �� � hann a�
		//   segja a� �au s�u lesfastar.  �etta eru breytun�fnin
		//   true, false og null.  Lesgreinirinn �arf �v� a�
		//   athuga hvort um �essi s�rst�ku breytun�fn er a� r��a
		//   eftir a� � lj�s hefur komi� a� lesgreindi strengurinn
		//   er breytunafn.  Sama gildir um lykilor�in if og define.

		// Notkun: Lexer l = new Lexer(r);
		// Fyrir:  r er Reader sem inniheldur allt a� 100000
		//         stafi.
		// Eftir:  l v�sar � n�jan lesgreini sem lesgreinir
		//         innihaldi� � r.  Lesgreinirinn er � upphafi
		//         sta�settur � fremsta lesi (lexeme) � r.
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

		// Notkun: char c = l.getToken();
		// Eftir:  c er t�ki� (token) sem stendur fyrir
		//         �a� m�l sem n�sta les � l flokkast �.
		char getToken()
		{
			return token;
		}

		// Notkun: String s = l.getLexeme();
		// Eftir:  s er n�sta les � l.
		String getLexeme()
		{
			return lexeme;
		}

		// Notkun: l.advance();
		// Eftir:  l hefur f�rst �fram � n�sta les �
		//         inntakinu (sem ekki er athugasemd).
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

		// Notkun: String n = Lexer.tokenName(c);
		// Fyrir:  c er stafur sem er einn �eirra sem
		//         lexgreinirinn getur skila� sem t�k.
		// Eftir:  n er nafni� � �essu t�ki � mannlega
		//         l�silegu sni�i.
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

		// Notkun: l.over(c);
		// Fyrir:  c er stafur sem stendur fyrir m�gulegt t�k.
		//         N�sta t�k � l er c.
		// Eftir:  B�i� er a� f�ra lesgreininn eitt skref �fram
		//         eins og � advance().
		// Afbrig�i:  Ef svo vill til a� n�sta t�k � l er ekki
		//            c �� eru skrifu� villubo� og keyrslan st��vast.
		String over( char tok )
		{
			if( token!=tok ) throw new Error("Expected "+tokenName(tok)+", found "+lexeme);
			String res = lexeme;
			advance();
			return res;
		}
	}

	// lex er lesgreinirinn.
	Lexer lex;
	// Inni � hverri fallsskilgreiningu inniheldur vars n�fnin
	// � vi�f�ngunum � falli� (�.e. leppunum e�a breytun�fnunum
	// sem standa fyrir vi�f�ngin), � s�tum 1 og aftar.  S�ti
	// 0 inniheldur nafn fallsins sem veri� er a� skilgreina.
	String[] vars;

	// Notkun: NanoLisp n = new NanoLisp(l);
	// Fyrir:  l er lesgreinir.
	// Eftir:  n v�sar � n�jan NanoLisp ���anda sem ���ir inntaki�
	//         sem l hefur.
	public NanoLisp( Lexer lexer )
	{
		lex = lexer;
	}

	// Notkun: int i = n.varPos(name);
	// Fyrir:  n er NanoLisp ���andi og er a� ���a stofn einhvers
	//         falls.  name er nafni� � einhverju vi�fangi � falli�.
	// Eftir:  i er sta�setning vi�fangsins � vi�fangarunu fallsins
	//         �ar sem fyrsta vi�fang er tali� vera � s�ti 0.
	int varPos( String name )
	{
		for( int i=1 ; i!=vars.length ; i++ )
			if( vars[i].equals(name) ) return i-1;
		throw new Error("Variable "+name+" is not defined");
	}

	// Notkun: Object[] code = n.program();
	// Fyrir:  n er NanoLisp ���andi og inntaki� er l�glegt
	//         NanoLisp forrit.
	// Eftir:  B�i� er a� ���a forriti� og code v�sar � n�tt
	//         fylki sem inniheldur milli�ulurnar fyrir �ll
	//         f�llin � forritinu.
	// Afbrig�i: Ef forriti� er ekki l�glegt �� eru skrifu�
	//           villubo� og keyrslan st��vu�.
	Object[] program()
	{
		Vector<Object> res = new Vector<Object>();
		while( lex.getToken() == '(' ) res.add(fundecl());
		return res.toArray();
	}

	// Notkun: Object[] fun = n.fundecl();
	// Fyrir:  n er NanoLisp ���andi sem er sta�settur �
	//         byrjun fallsskilgreiningar.
	// Eftir:  B�i� er a� lesa fallsskilgreininguna og fun v�sar
	//         � n�tt fylki sem er milli�ulan fyrir falli�.
	//         ���andinn er n� a� horfa � n�sta t�kn � inntakinu
	//         fyrir aftan fallsskilgreininguna.
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

	// Notkun: Object[] e = n.expr();
	// Fyrir:  n er NanoLisp ���andi sem er sta�settur �
	//         byrjun seg�ar innan fallsskilgreiningar.
	// Eftir:  B�i� er a� lesa seg�ina og ���a hana.
	//         e v�sar � milli�uluna fyrir seg�ina.
	//         ���andinn er n� a� horfa � n�sta t�kn � inntakinu
	//         fyrir aftan seg�ina.
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

	// Notkun: emit(line);
	// Fyrir:  line er l�na � loka�ulu.
	// Eftir:  B�i� er a� skrifa l�nuna � a�al�ttak.
	static void emit( String line )
	{
		System.out.println(line);
	}

	// Notkun: generateProgram(name,p);
	// Fyrir:  name er strengur, p er fylki fallsskilgreininga,
	//         �.e. fylki af milli�ulum fyrir f�ll.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir forrit sem
	//         samanstendur af f�llunum �.a. name er nafn
	//         forritsins.
	static void generateProgram( String name, Object[] p )
	{
		emit("\""+name+".mexe\" = main in");
		emit("!{{");
		for( int i=0 ; i!=p.length ; i++ ) generateFunction((Object[])p[i]);
		emit("}}*BASIS;");
	}

	// Notkun: generateFunction(f);
	// Fyrir:  f er milli�ula fyrir fall.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir falli� �
	//         a�al�ttak.
	static void generateFunction( Object[] f )
	{
		// f = {fname,argcount,expr}
		String fname = (String)f[0];
		int count = (Integer)f[1];
		emit("#\""+fname+"[f"+count+"]\" =");
		emit("[");
		generateExprR((Object[])f[2]);
		emit("];");
	}

	static int nextLab = 1;

	// Notkun: int i = newLab();
	// Eftir:  i er j�kv�� heiltala sem ekki hefur ��ur
	//         veri� skila� �r �essu falli.  Tilgangurinn
	//         er a� b�a til n�tt merki (label), sem er
	//         ekki �a� sama og neitt anna� merki.
	static int newLab()
	{
		return nextLab++;
	}

	// Notkun: generateExpr(e);
	// Fyrir:  e er milli�ula fyrir seg�.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir seg�ina
	//         � a�al�ttak.  Loka�ulan reiknar gildi
	//         seg�arinnar og skilur gildi� eftir �
	//         gildinu ac.
	static void generateExpr( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			// e = {NAME,name}
			emit("(Fetch "+e[1]+")");
			return;
		case LITERAL:
			// e = {LITERAL,literal}
			emit("(MakeVal "+(String)e[1]+")");
			return;
		case IF:
			// e = {IF,cond,then,else}
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
			// e = {CALL,name,args}
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

	// Notkun: generateJump(e,labTrue,labTrue);
	// Fyrir:  e er milli�ula fyrir seg�, labTrue og
	//         labFalse eru heilt�lur sem standa fyrir
	//         merki e�a eru n�ll.
	// Eftir:  B�i� er a� skrifa loka�ulu fyrir seg�ina
	//         � a�al�ttak.  Loka�ulan veldur st�kki til
	//         merkisins labTrue ef seg�ina skilar s�nnu,
	//         annars st�kki til labFalse.  Ef anna� merki�
	//         er n�ll �� er �a� jafngilt merki sem er r�tt
	//         fyrir aftan �ulu seg�arinnar.
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

	// Notkun: generateJumpP(e,labTrue,labFalse);
	// Fyrir:  e er milli�ula fyrir seg�, labTrue og
	//         labFalse eru heilt�lur sem standa fyrir
	//         merki e�a eru n�ll.
	// Eftir:  �etta kall b�r til loka�ulu sem er jafngild
	//         �ulunni sem k�llin
	//            emit("(Push)");
	//            generateJump(e,labTrue,labFalse);
	//         framlei�a.  �ulan er samt ekki endilega s�
	//         sama og �essi k�ll framlei�a �v� tilgangurinn
	//         er a� geta framleitt betri �ulu.
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

	// Notkun: generateExpr(e);
	// Fyrir:  e er milli�ula fyrir seg�.
	// Eftir:  �etta kall b�r til loka�ulu sem er jafngild
	//         �ulunni sem k�llin
	//            generateExpr(e);
	//            emit("(Return)");
	//         framlei�a.  �ulan er samt ekki endilega s�
	//         sama og �essi k�ll framlei�a �v� tilgangurinn
	//         er a� geta framleitt betri �ulu.
	static void generateExprR( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			// e = {NAME,name}
			emit("(FetchR "+e[1]+")");
			return;
		case LITERAL:
			// e = {LITERAL,literal}
			emit("(MakeValR "+(String)e[1]+")");
			return;
		case IF:
			// e = {IF,cond,then,else}
			int labElse = newLab();
			generateJump((Object[])e[1],0,labElse);
			generateExprR((Object[])e[2]);
			emit("_"+labElse+":");
			generateExprR((Object[])e[3]);
			return;
		case CALL:
			// e = {CALL,name,args}
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

	// Notkun: generateExprP(e);
	// Fyrir:  e er milli�ula fyrir seg�.
	// Eftir:  �etta kall b�r til loka�ulu sem er jafngild
	//         �ulunni sem k�llin
	//            emit("(Push)");
	//            generateExpr(e);
	//         framlei�a.  �ulan er samt ekki endilega s�
	//         sama og �essi k�ll framlei�a �v� tilgangurinn
	//         er a� geta framleitt betri �ulu.
	static void generateExprP( Object[] e )
	{
		switch( (CodeType)e[0] )
		{
		case NAME:
			// e = {NAME,name}
			emit("(FetchP "+e[1]+")");
			return;
		case LITERAL:
			// e = {LITERAL,literal}
			emit("(MakeValP "+(String)e[1]+")");
			return;
		case IF:
			// e = {IF,cond,then,else}
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
			// e = {CALL,name,args}
			Object[] args = (Object[])e[2];
			int i;
			for( i=0 ; i!=args.length ; i++ ) generateExprP((Object[])args[i]);
			if( i==0 ) emit("(Push)");
			emit("(Call #\""+e[1]+"[f"+i+"]\" "+i+")");
			return;
		}
	}

	// Notkun (af skipanal�nu):
	//        java NanoLisp forrit.s > forrit.masm
	// Fyrir: Skr�in forrit.s inniheldur l�glegt NanoLisp
	//        forit.
	// Eftir: B�i� er a� ���a forriti� og skrifa loka�uluna
	//        � skr�na forrit.masm.  S� s� loka�ula ��dd me�
	//        skipuninni
	//           morpho -c forrit.masm
	//        �� ver�ur til keyrsluh�fa Morpho skr�in forrit.mexe.
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
