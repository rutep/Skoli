#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "NanoMorpho.byaccj"
	import java.io.*;
	import java.util.*;
#line 9 "y.tab.c"
#define LITERAL 257
#define NAME 258
#define OPNAME 259
#define ERROR 260
#define DEFINE 261
#define PRINTLN 262
#define OP1 263
#define OP2 264
#define OP3 265
#define OP4 266
#define OP5 267
#define OP6 268
#define OP7 269
#define IF 270
#define ELSE 271
#define ELSIF 272
#define WHILE 273
#define VAR 274
#define RETURN 275
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,   11,    2,    9,    9,    9,   10,   10,
   10,    4,    4,    5,    5,    6,    6,    7,    7,    8,
    8,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,
};
short yylen[] = {                                         2,
    1,    2,    1,    0,   11,    0,    3,    1,    0,    3,
    1,    3,    2,    0,    1,    3,    1,    0,    3,    3,
    2,    2,    3,    4,    3,    3,    3,    3,    3,    3,
    3,    3,    1,    4,    5,    1,
};
short yydefred[] = {                                      4,
    0,    0,    3,    0,    2,    0,    0,    8,    0,    0,
    0,    0,    7,    0,   11,    0,    0,    0,   36,    0,
    0,    0,    0,    0,    0,    0,   10,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   13,    5,    0,    0,    0,    0,    0,    0,    0,   32,
    0,    0,    0,    0,    0,    0,   31,   12,   34,    0,
   24,    0,    0,    0,   35,    0,    0,   21,   19,    0,
   20,
};
short yydgoto[] = {                                       1,
    2,    3,   25,   26,   46,   47,   65,   67,    9,   16,
    4,
};
short yysindex[] = {                                      0,
    0,    0,    0, -253,    0,  -19, -251,    0,  -22, -111,
 -229, -225,    0, -208,    0,  -35,  -34, -196,    0,  -36,
   27,   28,  -34,  -34,  -57,  -40,    0,  -34,  -34,  -34,
  -34, -147,   -2,  -34,  -34,  -34,  -34,  -34,  -34,  -34,
    0,    0,   34, -147, -147,   24,   26,    5,   12,    0,
 -210, -210, -226, -192, -233, -197,    0,    0,    0,  -34,
    0,  -50, -147,  -34,    0,   41,  -37,    0,    0,   48,
    0,
};
short yyrindex[] = {                                      0,
    0,    1,    0,    0,    0,    0,    3,    0,    0,    0,
    0,    0,    0,  -28,    0,    0,    0,    0,    0,  -21,
    0,    0,    0,    0,    0,    0,    0,    0,   38,    0,
    0,  -31,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -27,    7,    0,   39,    0,    0,    0,
  -26,   -7,  -33,   30,   25,   19,    0,    0,    0,    0,
    0,  -14,   20,    0,    0,    0,    0,    0,    0,    0,
    0,
};
short yygindex[] = {                                      0,
    0,   79,   75,    0,    0,    0,    0,    0,    0,    0,
    0,
};
#define YYTABLESIZE 317
short yytable[] = {                                      24,
    1,   41,   24,   29,    6,   24,    8,   27,   18,   22,
   27,   12,   22,   23,   25,    9,   23,   25,   10,   33,
    7,   11,   33,   17,   28,   27,   18,   22,   13,   18,
    9,   23,   25,   26,   39,   40,   26,   33,   50,   37,
   38,   39,   40,    6,   18,   61,    6,   17,   14,   15,
   17,   26,   62,   35,   36,   37,   38,   39,   40,   30,
   16,   27,   30,   16,   59,   29,   30,   31,   29,   60,
   28,   40,   64,   28,   38,   39,   40,   30,   14,   15,
    5,    0,    0,   29,   42,    0,    0,   69,   28,    0,
    0,    0,   58,    0,    0,    0,    0,   32,   33,   68,
   43,    0,   44,   45,   48,   49,   71,    0,   51,   52,
   53,   54,   55,   56,   57,   34,   35,   36,   37,   38,
   39,   40,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   63,    0,    0,    0,   66,    0,
    0,   70,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   34,   35,   36,   37,   38,
   39,   40,    0,    0,    0,    0,   19,   20,    0,   19,
   20,   21,   19,   20,   21,    0,    0,   21,    0,   27,
   27,   27,   22,    0,   23,   22,   25,   23,   22,    0,
   23,   33,   33,   33,   33,   33,   33,   33,   18,   18,
   18,   18,   18,   18,   18,   26,    0,    0,    4,    0,
   34,   35,   36,   37,   38,   39,   40,   34,   35,   36,
   37,   38,   39,   40,   34,   35,   36,   37,   38,   39,
   40,   30,   30,   30,   30,   30,   30,   29,   29,   29,
   29,   29,   28,   28,   28,   28,   34,   35,   36,   37,
   38,   39,   40,   34,   35,   36,   37,   38,   39,   40,
   34,   35,   36,   37,   38,   39,   40,
};
short yycheck[] = {                                      40,
    0,   59,   40,   40,  258,   40,  258,   41,   44,   41,
   44,  123,   44,   41,   41,   44,   44,   44,   41,   41,
   40,   44,   44,   59,   61,   59,   41,   59,  258,   44,
   59,   59,   59,   41,  268,  269,   44,   59,   41,  266,
  267,  268,  269,   41,   59,   41,   44,   41,  274,  258,
   44,   59,   41,  264,  265,  266,  267,  268,  269,   41,
   41,  258,   44,   44,   41,   41,   40,   40,   44,   44,
   41,  269,  123,   44,  267,  268,  269,   59,   41,   41,
    2,   -1,   -1,   59,  125,   -1,   -1,  125,   59,   -1,
   -1,   -1,   59,   -1,   -1,   -1,   -1,   23,   24,   59,
   26,   -1,   28,   29,   30,   31,   59,   -1,   34,   35,
   36,   37,   38,   39,   40,  263,  264,  265,  266,  267,
  268,  269,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   -1,   -1,   -1,   64,   -1,
   -1,   67,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  263,  264,  265,  266,  267,
  268,  269,   -1,   -1,   -1,   -1,  257,  258,   -1,  257,
  258,  262,  257,  258,  262,   -1,   -1,  262,   -1,  263,
  264,  265,  273,   -1,  275,  273,  263,  275,  273,   -1,
  275,  263,  264,  265,  266,  267,  268,  269,  263,  264,
  265,  266,  267,  268,  269,  263,   -1,   -1,  258,   -1,
  263,  264,  265,  266,  267,  268,  269,  263,  264,  265,
  266,  267,  268,  269,  263,  264,  265,  266,  267,  268,
  269,  263,  264,  265,  266,  267,  268,  263,  264,  265,
  266,  267,  263,  264,  265,  266,  263,  264,  265,  266,
  267,  268,  269,  263,  264,  265,  266,  267,  268,  269,
  263,  264,  265,  266,  267,  268,  269,
};
#define YYFINAL 1
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 275
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'",0,0,"','",0,0,0,0,0,0,0,0,0,0,0,0,0,0,"';'",0,"'='",0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,"'}'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"LITERAL","NAME","OPNAME","ERROR","DEFINE","PRINTLN","OP1","OP2","OP3","OP4",
"OP5","OP6","OP7","IF","ELSE","ELSIF","WHILE","VAR","RETURN",
};
char *yyrule[] = {
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
"expr : LITERAL",
};
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 120 "NanoMorpho.byaccj"

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




#line 360 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 27 "NanoMorpho.byaccj"
{ generateProgram(name,((Vector<Object>)(yyvsp[0].obj)).toArray()); }
break;
case 2:
#line 31 "NanoMorpho.byaccj"
{ ((Vector<Object>)(yyvsp[-1].obj)).add(yyvsp[0].obj); yyval.obj=yyvsp[-1].obj; }
break;
case 3:
#line 32 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(yyvsp[0].obj); }
break;
case 4:
#line 36 "NanoMorpho.byaccj"
{
      varCount = 0;
      varTable = new HashMap<String,Integer>();
    }
break;
case 5:
#line 44 "NanoMorpho.byaccj"
{
      yyval.obj = new Object[]{yyvsp[-9].sval,yyvsp[-7].ival,yyvsp[-3].ival+yyvsp[-7].ival,((Vector<Object>)(yyvsp[-1].obj)).toArray()};
    }
break;
case 6:
#line 50 "NanoMorpho.byaccj"
{ yyval.ival=0; }
break;
case 7:
#line 51 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival=yyvsp[-2].ival+1; }
break;
case 8:
#line 52 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival+=1; }
break;
case 9:
#line 56 "NanoMorpho.byaccj"
{ yyval.ival=0; }
break;
case 10:
#line 57 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival=yyvsp[-2].ival+1; }
break;
case 11:
#line 58 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival+=1; }
break;
case 12:
#line 62 "NanoMorpho.byaccj"
{ ((Vector<Object>)(yyvsp[-2].obj)).add(yyvsp[-1].obj); yyval.obj=yyvsp[-2].obj; }
break;
case 13:
#line 63 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(yyvsp[-1].obj); }
break;
case 14:
#line 67 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); }
break;
case 16:
#line 72 "NanoMorpho.byaccj"
{ ((Vector<Object>)(yyvsp[-2].obj)).add(yyvsp[0].obj); yyval.obj=yyvsp[-2].obj; }
break;
case 17:
#line 73 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(yyvsp[0].obj); }
break;
case 18:
#line 77 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); }
break;
case 19:
#line 78 "NanoMorpho.byaccj"
{ yyval.obj=((Vector<Object>)(yyvsp[-1].obj)).toArray(); }
break;
case 20:
#line 82 "NanoMorpho.byaccj"
{ ((Vector<Object>)(yyvsp[-2].obj)).add(yyvsp[-1].obj); yyval.obj=yyvsp[-2].obj; }
break;
case 21:
#line 83 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(yyvsp[-1].obj); }
break;
case 22:
#line 88 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"RETURN",yyvsp[0].obj}; }
break;
case 23:
#line 90 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"STORE",varPos(yyvsp[-2].sval),yyvsp[0].obj}; }
break;
case 24:
#line 92 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"PRINT", yyvsp[-1].obj}; }
break;
case 25:
#line 94 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 26:
#line 96 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 27:
#line 98 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 28:
#line 100 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 29:
#line 102 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 30:
#line 104 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 31:
#line 106 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 32:
#line 108 "NanoMorpho.byaccj"
{ yyval.obj = yyvsp[-1].obj; }
break;
case 33:
#line 110 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"FETCH",varPos(yyvsp[0].sval)}; }
break;
case 34:
#line 112 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-3].sval, ((Vector<Object>)(yyvsp[-1].obj)).toArray() }; }
break;
case 35:
#line 114 "NanoMorpho.byaccj"
{ yyval.obj=new Object[]{"WHILE",yyvsp[-2].obj,yyvsp[0].obj}; }
break;
case 36:
#line 116 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"LITERAL",yyvsp[0].sval}; }
break;
#line 645 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
