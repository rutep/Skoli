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
#define IF 268
#define ELSE 269
#define ELSIF 270
#define WHILE 271
#define VAR 272
#define RETURN 273
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    7,    2,    5,    5,    5,    6,    6,
    6,    4,    4,    3,    3,    3,    3,    3,    3,    3,
    3,
};
short yylen[] = {                                         2,
    1,    2,    1,    0,   11,    0,    3,    1,    0,    3,
    1,    3,    2,    2,    3,    4,    3,    3,    1,    4,
    1,
};
short yydefred[] = {                                      4,
    0,    0,    3,    0,    2,    0,    0,    8,    0,    0,
    0,    0,    7,    0,   11,    0,    0,    0,   21,    0,
    0,    0,    0,    0,   10,    0,    0,    0,    0,    0,
    0,   13,    5,    0,    0,    0,    0,    0,    0,   12,
   20,   16,
};
short yydgoto[] = {                                       1,
    2,    3,   23,   24,    9,   16,    4,
};
short yysindex[] = {                                      0,
    0,    0,    0, -248,    0,  -12, -223,    0,  -22,  -86,
 -219, -231,    0, -214,    0,  -32, -241, -213,    0,  -31,
    6, -241,  -56, -117,    0, -241, -241, -241, -249, -241,
 -241,    0,    0,  -54, -249,  -41,  -37, -217, -217,    0,
    0,    0,
};
short yyrindex[] = {                                      0,
    0,    1,    0,    0,    0,    0,  -18,    0,    0,    0,
    0,    0,    0,  -26,    0,    0,    0,    0,    0,  -39,
    0,    0,    0,    0,    0,    0,    0,    0,  -30,    0,
    0,    0,    0,    0,  -28,    0,    0,  -35,  -34,    0,
    0,    0,
};
short yygindex[] = {                                      0,
    0,   47,   12,   23,    0,    0,    0,
};
#define YYTABLESIZE 259
short yytable[] = {                                      41,
    1,   19,   32,   42,   40,   17,   18,   33,   27,    6,
   14,   18,   15,   30,   31,   19,   20,    9,   10,   19,
   21,   11,    6,   17,   18,    6,   17,    7,   14,   26,
   15,   22,    9,   29,    8,   34,   12,   35,   13,   37,
   14,   38,   39,   15,   25,   28,   31,   34,    5,   36,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   19,
   20,    0,    0,    0,   21,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   22,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   30,   31,   30,   31,
    0,    0,    0,    0,    0,   19,   20,    0,    0,    0,
   21,    0,    0,   19,   19,   30,   31,   17,   18,    0,
    0,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    4,
};
short yycheck[] = {                                      41,
    0,   41,   59,   41,   59,   41,   41,  125,   40,  258,
   41,   44,   41,  263,  264,  257,  258,   44,   41,   59,
  262,   44,   41,   59,   59,   44,   59,   40,   59,   61,
   59,  273,   59,   22,  258,   24,  123,   26,  258,   28,
  272,   30,   31,  258,  258,   40,  264,   36,    2,   27,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  263,  264,  263,  264,
   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,   -1,
  262,   -1,   -1,  263,  264,  263,  264,  263,  263,   -1,
   -1,  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
};
#define YYFINAL 1
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 273
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
"OP5","IF","ELSE","ELSIF","WHILE","VAR","RETURN",
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
"expr : RETURN expr",
"expr : NAME '=' expr",
"expr : PRINTLN '(' expr ')'",
"expr : expr OP1 expr",
"expr : expr OP2 expr",
"expr : NAME",
"expr : NAME '(' exprs ')'",
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
#line 84 "NanoMorpho.byaccj"

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




#line 318 "y.tab.c"
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
#line 25 "NanoMorpho.byaccj"
{ generateProgram(name,((Vector<Object>)(yyvsp[0].obj)).toArray()); }
break;
case 2:
#line 29 "NanoMorpho.byaccj"
{ ((Vector<Object>)(yyvsp[-1].obj)).add(yyvsp[0].obj); yyval.obj=yyvsp[-1].obj; }
break;
case 3:
#line 30 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(yyvsp[0].obj); }
break;
case 4:
#line 34 "NanoMorpho.byaccj"
{
      varCount = 0;
      varTable = new HashMap<String,Integer>();
    }
break;
case 5:
#line 42 "NanoMorpho.byaccj"
{
      yyval.obj = new Object[]{yyvsp[-9].sval,yyvsp[-7].ival,yyvsp[-3].ival+yyvsp[-7].ival,((Vector<Object>)(yyvsp[-1].obj)).toArray()};
    }
break;
case 6:
#line 48 "NanoMorpho.byaccj"
{ yyval.ival=0; }
break;
case 7:
#line 49 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival=yyvsp[-2].ival+1; }
break;
case 8:
#line 50 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival+=1; }
break;
case 9:
#line 54 "NanoMorpho.byaccj"
{ yyval.ival=0; }
break;
case 10:
#line 55 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival=yyvsp[-2].ival+1; }
break;
case 11:
#line 56 "NanoMorpho.byaccj"
{ addVar(yyvsp[0].sval); yyval.ival+=1; }
break;
case 12:
#line 60 "NanoMorpho.byaccj"
{ ((Vector<Object>)(yyvsp[-2].obj)).add(yyvsp[-1].obj); yyval.obj=yyvsp[-2].obj; }
break;
case 13:
#line 61 "NanoMorpho.byaccj"
{ yyval.obj=new Vector<Object>(); ((Vector<Object>)(yyval.obj)).add(yyvsp[-1].obj); }
break;
case 14:
#line 66 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"RETURN",yyvsp[0].obj}; }
break;
case 15:
#line 68 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"STORE",varPos(yyvsp[-2].sval),yyvsp[0].obj}; }
break;
case 16:
#line 70 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"PRINT", yyvsp[-1].obj}; }
break;
case 17:
#line 72 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 18:
#line 74 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-1].sval,new Object[]{yyvsp[-2].obj,yyvsp[0].obj}}; }
break;
case 19:
#line 76 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"FETCH",varPos(yyvsp[0].sval)}; }
break;
case 20:
#line 78 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"CALL",yyvsp[-3].sval,yyvsp[-1].obj}; }
break;
case 21:
#line 80 "NanoMorpho.byaccj"
{ yyval.obj = new Object[]{"LITERAL",yyvsp[0].sval}; }
break;
#line 547 "y.tab.c"
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
