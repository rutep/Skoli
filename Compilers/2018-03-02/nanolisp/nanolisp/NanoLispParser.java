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






//#line 11 "nanolisp.byaccj"
	import java.io.*;
	import java.util.*;
//#line 20 "NanoLispParser.java"




public class NanoLispParser
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
//public class NanoLispParserVal is defined in NanoLispParserVal.java


String   yytext;//user variable to return contextual strings
NanoLispParserVal yyval; //used to return semantic vals from action routines
NanoLispParserVal yylval;//the 'lval' (result) I got from yylex()
NanoLispParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new NanoLispParserVal[YYSTACKSIZE];
  yyval=new NanoLispParserVal();
  yylval=new NanoLispParserVal();
  valptr=-1;
}
void val_push(NanoLispParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
NanoLispParserVal val_pop()
{
  if (valptr<0)
    return new NanoLispParserVal();
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
NanoLispParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new NanoLispParserVal();
  return valstk[ptr];
}
final NanoLispParserVal dup_yyval(NanoLispParserVal val)
{
  NanoLispParserVal dup = new NanoLispParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short LITERAL=257;
public final static short NAME=258;
public final static short IF=259;
public final static short DEFINE=260;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    6,    2,    5,    5,    3,    3,    3,
    3,    4,    4,
};
final static short yylen[] = {                            2,
    1,    2,    1,    0,    9,    0,    2,    1,    1,    6,
    4,    0,    2,
};
final static short yydefred[] = {                         4,
    0,    0,    3,    0,    2,    0,    0,    0,    6,    0,
    7,    0,    9,    8,    0,    0,   12,    0,    5,    0,
    0,   11,   13,    0,    0,   10,
};
final static short yydgoto[] = {                          1,
    2,    3,   16,   20,   10,    4,
};
final static short yysindex[] = {                         0,
    0,    0,    0,  -34,    0, -248,  -27, -244,    0,  -37,
    0,  -38,    0,    0, -249,  -26,    0,  -38,    0,  -40,
  -38,    0,    0,  -38,  -25,    0,
};
final static short yyrindex[] = {                         0,
    0,    3,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   15,  -13,    0,    0,    0,
};
final static int YYTABLESIZE=221;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         15,
   22,   15,    1,   12,   21,    6,   23,   24,   17,   18,
   25,    7,    8,    9,   19,   26,    5,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   13,   14,   13,   14,
   11,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   41,   40,    0,   41,   18,   40,   20,   21,  258,  259,
   24,  260,   40,  258,   41,   41,    2,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   40,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  257,  258,
  258,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=260;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,"LITERAL","NAME","IF","DEFINE",
};
final static String yyrule[] = {
"$accept : start",
"start : program",
"program : program fundecl",
"program : fundecl",
"$$1 :",
"fundecl : $$1 '(' DEFINE '(' NAME ids ')' expr ')'",
"ids :",
"ids : ids NAME",
"expr : NAME",
"expr : LITERAL",
"expr : '(' IF expr expr expr ')'",
"expr : '(' NAME args ')'",
"args :",
"args : args expr",
};

//#line 60 "nanolisp.byaccj"

	enum Code
	{
		IF, LITERAL, NAME, CALL
	};

	static private String name;
	private NanoLispLexer lexer;
	private int varCount;
	private HashMap<String,Integer> varTable;

	private void addVar( String name )
	{
		if( varTable.get(name) != null )
			yyerror("Variable "+name+" already exists");
		varTable.put(name,varCount++);
	}

	private int findVar( String name )
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
				yylval = new NanoLispParserVal(NanoLispParser.yyname[yyl_return]);
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
		System.out.println("Token:  "+NanoLispParser.yyname[last_token_read]);
		System.exit(1);
	}

	public NanoLispParser( Reader r )
	{
		lexer = new NanoLispLexer(r,this);
	}

	public static void main( String args[] )
	  	throws IOException
	{
		NanoLispParser yyparser = new NanoLispParser(new FileReader(args[0]));
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
		emit("#\""+fname+"[f"+count+"]\" =");
		emit("[");
		generateExprR((Object[])f[2]);
		emit("];");
	}

	static int nextLab = 0;

	static int newLab()
	{
		return nextLab++;
	}

	static void generateExpr( Object[] e )
	{
		switch( (Code)e[0] )
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
			generateExpr((Object[])e[1]);
			int labElse = newLab();
			int labEnd = newLab();
			emit("(GoFalse _"+labElse+")");
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

	static void generateExprR( Object[] e )
	{
		switch( (Code)e[0] )
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
			generateExpr((Object[])e[1]);
			int labElse = newLab();
			emit("(GoFalse _"+labElse+")");
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

	static void generateExprP( Object[] e )
	{
		switch( (Code)e[0] )
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
			generateExprP((Object[])e[1]);
			int labElse = newLab();
			int labEnd = newLab();
			emit("(GoFalse _"+labElse+")");
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
	}			/*@ \label{byaccgeneratorend} @*/
//#line 423 "NanoLispParser.java"
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
//#line 23 "nanolisp.byaccj"
{ generateProgram(name,((Vector<Object>)(val_peek(0).obj)).toArray()); }
break;
case 2:
//#line 27 "nanolisp.byaccj"
{ ((Vector<Object>)(val_peek(1).obj)).add(val_peek(0).obj); yyval.obj=val_peek(1).obj; }
break;
case 3:
//#line 28 "nanolisp.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(val_peek(0).obj); }
break;
case 4:
//#line 32 "nanolisp.byaccj"
{
				varCount = 0;
				varTable = new HashMap<String,Integer>();
			}
break;
case 5:
//#line 37 "nanolisp.byaccj"
{
				yyval.obj=new Object[]{val_peek(4).sval,val_peek(3).ival,val_peek(1).obj};
			}
break;
case 6:
//#line 43 "nanolisp.byaccj"
{ yyval.ival=0; }
break;
case 7:
//#line 44 "nanolisp.byaccj"
{ addVar(val_peek(0).sval); yyval.ival=val_peek(1).ival+1; }
break;
case 8:
//#line 48 "nanolisp.byaccj"
{ yyval.obj=new Object[]{Code.NAME,findVar(val_peek(0).sval)}; }
break;
case 9:
//#line 49 "nanolisp.byaccj"
{ yyval.obj=new Object[]{Code.LITERAL,val_peek(0).sval}; }
break;
case 10:
//#line 50 "nanolisp.byaccj"
{ yyval.obj=new Object[]{Code.IF,val_peek(3).obj,val_peek(2).obj,val_peek(1).obj}; }
break;
case 11:
//#line 51 "nanolisp.byaccj"
{ yyval.obj=new Object[]{Code.CALL,val_peek(2).sval,((Vector<Object>)(val_peek(1).obj)).toArray()}; }
break;
case 12:
//#line 55 "nanolisp.byaccj"
{ yyval.obj=new Vector<Object>(); }
break;
case 13:
//#line 56 "nanolisp.byaccj"
{ ((Vector<Object>)(val_peek(1).obj)).add(val_peek(0).obj); yyval.obj=val_peek(1).obj; }
break;
//#line 629 "NanoLispParser.java"
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
public NanoLispParser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public NanoLispParser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
