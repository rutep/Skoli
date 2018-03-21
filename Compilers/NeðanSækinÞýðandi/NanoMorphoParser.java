//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "NanoMorpho.byaccj"
	import java.io.*;
	import java.util.*;
//#line 20 "NanoMorphoParser.java"




public class NanoMorphoParser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class NanoMorphoParserVal is defined in NanoMorphoParserVal.java


String   yytext;//user variable to return contextual strings
NanoMorphoParserVal yyval; //used to return semantic vals from action routines
NanoMorphoParserVal yylval;//the 'lval' (result) I got from yylex()
NanoMorphoParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new NanoMorphoParserVal[YYSTACKSIZE];
  yyval=new NanoMorphoParserVal();
  yylval=new NanoMorphoParserVal();
  valptr=-1;
}
void val_push(NanoMorphoParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
NanoMorphoParserVal val_pop()
{
  if (valptr<0)
    return new NanoMorphoParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
NanoMorphoParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new NanoMorphoParserVal();
  return valstk[ptr];
}
final NanoMorphoParserVal dup_yyval(NanoMorphoParserVal val)
{
  NanoMorphoParserVal dup = new NanoMorphoParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short LITERAL=257;
public final static short NAME=258;
public final static short OPNAME=259;
public final static short ERROR=260;
public final static short DEFINE=261;
public final static short PRINTLN=262;
public final static short OP1=263;
public final static short OP2=264;
public final static short OP3=265;
public final static short OP4=266;
public final static short OP5=267;
public final static short OP6=268;
public final static short OP7=269;
public final static short IF=270;
public final static short ELSE=271;
public final static short ELSIF=272;
public final static short WHILE=273;
public final static short VAR=274;
public final static short RETURN=275;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,   12,    2,   10,   10,   10,   11,   11,
   11,    4,    4,    5,    5,    6,    6,    7,    7,    8,
    8,    9,    9,    9,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,
};
final static short yylen[] = {                            2,
    1,    2,    1,    0,   11,    0,    3,    1,    0,    3,
    1,    3,    2,    0,    1,    3,    1,    0,    3,    3,
    2,    2,    5,    6,    2,    3,    4,    3,    3,    3,
    3,    3,    3,    3,    3,    1,    4,    5,    5,    6,
    1,
};
final static short yydefred[] = {                         4,
    0,    0,    3,    0,    2,    0,    0,    8,    0,    0,
    0,    0,    7,    0,   11,    0,    0,    0,   41,    0,
    0,    0,    0,    0,    0,    0,    0,   10,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   13,    5,    0,    0,    0,    0,    0,    0,
    0,    0,   35,    0,    0,    0,    0,    0,    0,   34,
   12,   37,    0,   27,    0,    0,    0,    0,    0,   38,
    0,    0,    0,    0,   40,   21,   19,    0,   22,    0,
   20,    0,    0,    0,   24,
};
final static short yydgoto[] = {                          1,
    2,    3,   26,   27,   48,   49,   69,   72,   75,    9,
   16,    4,
};
final static short yysindex[] = {                         0,
    0,    0,    0, -254,    0,  -33, -241,    0,  -23,  -90,
 -219, -225,    0, -188,    0,  -29,  -20, -187,    0,  -37,
   34,   37,   38,  -20,  -20,  -57,  -40,    0,  -20,  -20,
  -20,  -20,  -20, -143,   35,  -20,  -20,  -20,  -20,  -20,
  -20,  -20,    0,    0,   79, -143, -143,   40,   31,   42,
   49,   56,    0, -222, -222, -174, -208, -205, -190,    0,
    0,    0,  -20,    0,  -41,  -41, -143,  -20, -204,    0,
   86,  -34,  -41,   44,    0,    0,    0,   93,    0,  -20,
    0,   63,  -41, -204,    0,
};
final static short yyrindex[] = {                         0,
    0,    1,    0,    0,    0,    0,   -9,    0,    0,    0,
    0,    0,    0,  -28,    0,    0,    0,    0,    0,    7,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   45,
    0,    0,    0,  -31,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -30,    9,    0,   47,    0,
    0,    0,    0,  -36,  -32,   -7,   75,   70,  -19,    0,
    0,    0,    0,    0,   -3,   14,   13,    0,   21,    0,
    0,    0,   14,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -3,   28,    0,
};
final static short yygindex[] = {                         0,
    0,   87,  117,    0,    0,    0,  -47,    0,   12,    0,
    0,    0,
};
final static int YYTABLESIZE=362;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         25,
    1,   43,   30,    6,   28,   25,    7,   28,   29,   25,
   26,   29,   25,   26,   18,    9,    8,   10,   70,   25,
   11,   33,   28,   29,   33,   79,   29,   25,   26,   17,
    9,    6,   12,   30,    6,   84,   30,   18,   13,   33,
   18,   37,   38,   39,   40,   41,   42,   36,   14,   17,
   36,   30,   17,   16,   18,   18,   16,   18,   40,   41,
   42,   39,   41,   42,   39,   36,   73,   74,   23,   15,
   28,   23,   18,   31,   63,   53,   32,   33,   42,   39,
   62,   68,   64,   80,   44,   14,   23,   15,    5,   65,
   77,   39,   40,   41,   42,   85,   66,    0,    0,    0,
    0,    0,    0,   83,    0,    0,    0,    0,    0,    0,
   32,    0,    0,   32,    0,   31,    0,    0,   31,   36,
   37,   38,   39,   40,   41,   42,    0,    0,   32,    0,
    0,    0,    0,   31,    0,    0,    0,   61,    0,    0,
   34,   35,    0,   45,   76,   46,   47,   50,   51,   52,
    0,   81,   54,   55,   56,   57,   58,   59,   60,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   67,
    0,    0,    0,    0,   71,    0,    0,    0,   78,    0,
    0,    0,    0,    0,    0,    0,   82,    0,    0,    0,
    0,    0,    0,    0,    0,   36,   37,   38,   39,   40,
   41,   42,    0,    0,    0,    0,   19,   20,    0,    0,
    0,   21,   19,   20,    0,    0,   28,   21,    0,   22,
   29,    0,   23,    0,   24,   22,   19,   20,   23,    0,
   24,   21,    0,   33,   33,   33,   33,   33,   33,   22,
    0,    0,   23,    0,   24,   30,   30,   30,    4,   18,
   18,   18,   18,   18,   18,   18,    0,   18,   18,   36,
   36,   36,   36,   36,   36,   36,   18,   18,   18,   18,
   18,   18,   18,   39,   39,   39,   39,   39,   39,   39,
   23,   23,   23,   23,   23,   23,   23,   36,   37,   38,
   39,   40,   41,   42,   36,   37,   38,   39,   40,   41,
   42,   36,   37,   38,   39,   40,   41,   42,   36,   37,
   38,   39,   40,   41,   42,   36,   37,   38,   39,   40,
   41,   42,   32,   32,   32,   32,   32,   31,   31,   31,
   31,   36,   37,   38,   39,   40,   41,   42,   36,   37,
   38,   39,   40,   41,   42,   36,   37,   38,   39,   40,
   41,   42,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   59,   40,  258,   41,   40,   40,   44,   41,   41,
   41,   44,   44,   44,   44,   44,  258,   41,   66,   40,
   44,   41,   59,   61,   44,   73,   59,   59,   59,   59,
   59,   41,  123,   41,   44,   83,   44,   41,  258,   59,
   44,  264,  265,  266,  267,  268,  269,   41,  274,   41,
   44,   59,   44,   41,   41,   59,   44,   44,  267,  268,
  269,   41,  268,  269,   44,   59,  271,  272,   41,  258,
  258,   44,   59,   40,   44,   41,   40,   40,  269,   59,
   41,  123,   41,   40,  125,   41,   59,   41,    2,   41,
  125,  266,  267,  268,  269,   84,   41,   -1,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,   41,   -1,   -1,   44,  263,
  264,  265,  266,  267,  268,  269,   -1,   -1,   59,   -1,
   -1,   -1,   -1,   59,   -1,   -1,   -1,   59,   -1,   -1,
   24,   25,   -1,   27,   59,   29,   30,   31,   32,   33,
   -1,   59,   36,   37,   38,   39,   40,   41,   42,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   63,
   -1,   -1,   -1,   -1,   68,   -1,   -1,   -1,   72,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   80,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  263,  264,  265,  266,  267,
  268,  269,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,
   -1,  262,  257,  258,   -1,   -1,  263,  262,   -1,  270,
  263,   -1,  273,   -1,  275,  270,  257,  258,  273,   -1,
  275,  262,   -1,  263,  264,  265,  266,  267,  268,  270,
   -1,   -1,  273,   -1,  275,  263,  264,  265,  258,  263,
  264,  265,  266,  267,  268,  269,   -1,  271,  272,  263,
  264,  265,  266,  267,  268,  269,  263,  264,  265,  266,
  267,  268,  269,  263,  264,  265,  266,  267,  268,  269,
  263,  264,  265,  266,  267,  268,  269,  263,  264,  265,
  266,  267,  268,  269,  263,  264,  265,  266,  267,  268,
  269,  263,  264,  265,  266,  267,  268,  269,  263,  264,
  265,  266,  267,  268,  269,  263,  264,  265,  266,  267,
  268,  269,  263,  264,  265,  266,  267,  263,  264,  265,
  266,  263,  264,  265,  266,  267,  268,  269,  263,  264,
  265,  266,  267,  268,  269,  263,  264,  265,  266,  267,
  268,  269,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=275;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,null,"','",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"LITERAL","NAME","OPNAME","ERROR","DEFINE",
"PRINTLN","OP1","OP2","OP3","OP4","OP5","OP6","OP7","IF","ELSE","ELSIF","WHILE",
"VAR","RETURN",
};
final static String yyrule[] = {
"$accept : start",
"start : program",
"program : program fundecl",
"program : fundecl",
"$$1 :",
"fundecl : $$1 NAME '(' ids ')' '{' VAR idlist ';' exprs '}'",
"ids :",
"ids : ids ',' NAME",
"ids : NAME",
"idlist :",
"idlist : idlist ',' NAME",
"idlist : NAME",
"exprs : exprs expr ';'",
"exprs : expr ';'",
"args :",
"args : arglist",
"arglist : arglist ',' expr",
"arglist : expr",
"body :",
"body : '{' bodyexpr '}'",
"bodyexpr : bodyexpr expr ';'",
"bodyexpr : expr ';'",
"ifrest : ELSE body",
"ifrest : ELSIF '(' expr ')' body",
"ifrest : ELSIF '(' expr ')' body ifrest",
"expr : RETURN expr",
"expr : NAME '=' expr",
"expr : PRINTLN '(' expr ')'",
"expr : expr OP1 expr",
"expr : expr OP2 expr",
"expr : expr OP3 expr",
"expr : expr OP4 expr",
"expr : expr OP5 expr",
"expr : expr OP6 expr",
"expr : expr OP7 expr",
"expr : '(' expr ')'",
"expr : NAME",
"expr : NAME '(' args ')'",
"expr : WHILE '(' expr ')' body",
"expr : IF '(' expr ')' body",
"expr : IF '(' expr ')' body ifrest",
"expr : LITERAL",
};

//#line 130 "NanoMorpho.byaccj"

static private String name;
private NanoMorphoLexer lexer;
private int varCount;
private HashMap<String,Integer> varTable;

private void addVar( String name )
{
  if( varTable.get(name) != null )
  yyerror("Variable "+name+" already exists");
  varTable.put(name,varCount++);
}

private int varPos( String name )
	{
		Integer res = varTable.get(name);
		if( res == null )
			yyerror("Variable "+name+" does not exist");
		return res;
	}

	int last_token_read;

	private int yylex()
	{
		int yyl_return = -1;
		try
		{
			yylval = null;
			last_token_read = yyl_return = lexer.yylex();
			if( yylval==null )
				yylval = new NanoMorphoParserVal(NanoMorphoParser.yyname[yyl_return]);
		}
		catch (IOException e)
		{
			System.err.println("IO error: "+e);
		}
		return yyl_return;
	}

	public void yyerror( String error )
	{
		System.out.println("Error:  "+error);
		System.out.println("Token:  "+NanoMorphoParser.yyname[last_token_read]);
		System.exit(1);
	}
  
  public NanoMorphoParser( Reader r )
	{
		lexer = new NanoMorphoLexer(r,this);
	}

public static void main( String args[] )
throws IOException
{
  NanoMorphoParser yyparser = new NanoMorphoParser(new FileReader(args[0]));
  name = args[0].substring(0,args[0].lastIndexOf('.'));
  yyparser.yyparse();
}

public static void emit( String s )		/*@ \label{byaccgeneratorstart} @*/
{
  System.out.println(s);
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
    int varcount = (Integer)f[2];
		emit("#\""+fname+"[f"+count+"]\" =");
		emit("[");
    if( varcount!=0 ) System.out.println("(MakeVal null)");
		for( int i=0 ; i!=varcount ; i++ ) System.out.println("(Push)");    
		Object[] exprs = (Object[])f[3];
		for( Object e: exprs ) generateExpr((Object[])e);
		emit("];");
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
					emit("(GoFalse _"+endlab+")");
					generateBody((Object[])e[2]);
					emit("_"+endlab+":");
					return;
			}
	case "IF2":
			{
					// ["IF2",cond,thenpart,elsepart]
					int elslab = nextLab++;
					int endlab = nextLab++;
					generateExpr((Object[])e[1]);
					emit("(GoFalse _"+elslab+")");
					generateBody((Object[])e[2]);
					emit("(Go _"+endlab+")");
					emit("_"+elslab+":");
					generateExpr((Object[])e[3]);
					emit("_"+endlab+":");
					return;
			}
		case "IF3":
			{
					// ["IF3",elsepart]
					int elslab = nextLab++;
					int endlab = nextLab++;
					emit("_"+elslab+":");
					generateBody((Object[])e[1]);
					emit("_"+endlab+":");
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
			generateExpr((Object[])e[1]);
			System.out.println("(Return)");
			return;
	case "LITERAL":
			System.out.println("(MakeVal "+e[1]+")");
			return;
	case "PRINT":
			generateExpr((Object[])e[1]);
			emit("(Call #\""+"writeln"+"[f1]\" 1)");
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
//#line 500 "NanoMorphoParser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 27 "NanoMorpho.byaccj"
{ generateProgram(name,((Vector<Object>)(val_peek(0).obj)).toArray()); }
break;
case 2:
//#line 31 "NanoMorpho.byaccj"
{ ((Vector<Object>)(val_peek(1).obj)).add(val_peek(0).obj); yyval.obj=val_peek(1).obj; }
break;
case 3:
//#line 32 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(0).obj); }
break;
case 4:
//#line 36 "NanoMorpho.byaccj"
{
      varCount = 0;
      varTable = new HashMap<String,Integer>();
    }
break;
case 5:
//#line 44 "NanoMorpho.byaccj"
{
      yyval.obj = new Object[]{val_peek(9).sval,val_peek(7).ival,val_peek(3).ival+val_peek(7).ival,((Vector<Object>)(val_peek(1).obj)).toArray()};
    }
break;
case 6:
//#line 50 "NanoMorpho.byaccj"
{ yyval.ival=0; }
break;
case 7:
//#line 51 "NanoMorpho.byaccj"
{ addVar(val_peek(0).sval); yyval.ival=val_peek(2).ival+1; }
break;
case 8:
//#line 52 "NanoMorpho.byaccj"
{ addVar(val_peek(0).sval); yyval.ival+=1; }
break;
case 9:
//#line 56 "NanoMorpho.byaccj"
{ yyval.ival=0; }
break;
case 10:
//#line 57 "NanoMorpho.byaccj"
{ addVar(val_peek(0).sval); yyval.ival=val_peek(2).ival+1; }
break;
case 11:
//#line 58 "NanoMorpho.byaccj"
{ addVar(val_peek(0).sval); yyval.ival+=1; }
break;
case 12:
//#line 62 "NanoMorpho.byaccj"
{ ((Vector<Object>)(val_peek(2).obj)).add(val_peek(1).obj); yyval.obj=val_peek(2).obj; }
break;
case 13:
//#line 63 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(1).obj); }
break;
case 14:
//#line 67 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); }
break;
case 16:
//#line 72 "NanoMorpho.byaccj"
{ ((Vector<Object>)(val_peek(2).obj)).add(val_peek(0).obj); yyval.obj=val_peek(2).obj; }
break;
case 17:
//#line 73 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(0).obj); }
break;
case 18:
//#line 77 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); }
break;
case 19:
//#line 78 "NanoMorpho.byaccj"
{ yyval.obj=((Vector<Object>)(val_peek(1).obj)).toArray(); }
break;
case 20:
//#line 82 "NanoMorpho.byaccj"
{ ((Vector<Object>)(val_peek(2).obj)).add(val_peek(1).obj); yyval.obj=val_peek(2).obj; }
break;
case 21:
//#line 83 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(1).obj); }
break;
case 22:
//#line 87 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"IF3",val_peek(0).obj}; }
break;
case 23:
//#line 88 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"IF1",val_peek(2).obj,val_peek(0).obj}; }
break;
case 24:
//#line 89 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"IF2",val_peek(3).obj,val_peek(1).obj,val_peek(0).obj}; }
break;
case 25:
//#line 94 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"RETURN",val_peek(0).obj}; }
break;
case 26:
//#line 96 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"STORE",varPos(val_peek(2).sval),val_peek(0).obj}; }
break;
case 27:
//#line 98 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"PRINT", val_peek(1).obj}; }
break;
case 28:
//#line 100 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(1).sval,new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 29:
//#line 102 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(1).sval,new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 30:
//#line 104 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(1).sval,new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 31:
//#line 106 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(1).sval,new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 32:
//#line 108 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(1).sval,new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 33:
//#line 110 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(1).sval,new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 34:
//#line 112 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(1).sval,new Object[]{val_peek(2).obj,val_peek(0).obj}}; }
break;
case 35:
//#line 114 "NanoMorpho.byaccj"
{ yyval.obj = val_peek(1).obj; }
break;
case 36:
//#line 116 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"FETCH",varPos(val_peek(0).sval)}; }
break;
case 37:
//#line 118 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",val_peek(3).sval, ((Vector<Object>)(val_peek(1).obj)).toArray() }; }
break;
case 38:
//#line 120 "NanoMorpho.byaccj"
{ yyval.obj=new Object[]{"WHILE",val_peek(2).obj,val_peek(0).obj}; }
break;
case 39:
//#line 122 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"IF1",val_peek(2).obj,val_peek(0).obj}; }
break;
case 40:
//#line 124 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"IF2",val_peek(3).obj,val_peek(1).obj,val_peek(0).obj}; }
break;
case 41:
//#line 126 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"LITERAL",val_peek(0).sval}; }
break;
//#line 814 "NanoMorphoParser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public NanoMorphoParser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public NanoMorphoParser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
