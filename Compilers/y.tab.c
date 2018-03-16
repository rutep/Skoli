#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#define OPNAME 257
#define LITERAL 258
#define NAME 259
#define IF 260
#define ELSE 261
#define ELSIF 262
#define WHILE 263
#define VAR 264
#define RETURN 265
#define AND 266
#define OR 267
#define NOT 268
#define FUN 269
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    2,    2,    3,    3,    4,    5,    1,    1,    1,
    1,    7,    7,    7,    7,    6,    6,    6,    8,    8,
    9,    9,   10,   10,   11,   11,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   13,   13,   15,   15,   14,
   14,   14,
};
short yylen[] = {                                         2,
    1,    0,    1,    3,    1,    3,    6,    3,    3,    4,
    0,    3,    1,    5,    3,    2,    3,    1,    3,    1,
    3,    1,    2,    1,    3,    1,    2,    3,    1,    4,
    3,    4,    1,    1,    5,    0,    1,    3,    1,    0,
    2,    4,
};
short yydefred[] = {                                     11,
    0,    0,    0,   33,    0,    0,    0,    0,    0,    0,
   11,    0,    0,   34,    0,    0,    0,    0,   22,    0,
   26,    0,    0,   27,    0,    0,    0,    0,    0,    0,
   16,    0,    0,    0,    0,   23,    9,    8,    0,    0,
    0,   39,    0,    0,   17,    0,   31,    0,    0,   10,
    0,    5,    0,    0,    6,   28,    0,   21,   25,   30,
    0,    0,    0,   32,   12,    0,    0,    0,    0,   38,
   41,    0,    0,    0,   35,    4,    0,   14,    7,   42,
};
short yydgoto[] = {                                       1,
    2,   53,   54,   14,   15,   16,   30,   17,   18,   19,
   20,   21,   43,   64,   44,
};
short yysindex[] = {                                      0,
    0,  -20,   13,    0,  -23,   -7,   -7, -257,   -7,  -39,
    0,   -7,    6,    0,  -48,  -41, -243, -235,    0, -216,
    0,    4,    5,    0,   -7,   -7,  -73,  -73,   -5,  -16,
    0,   14, -202,  -33,   17,    0,    0,    0,    6,    6,
   13,    0,   18,   19,    0, -247,    0,   -7, -195,    0,
 -202,    0,   24,   22,    0,    0, -235,    0,    0,    0,
   -7,  -73,   -7,    0,    0,    7,   26,  -73, -190,    0,
    0,  -73,   -7,  -73,    0,    0, -247,    0,    0,    0,
};
short yyrindex[] = {                                      0,
    0,   72,    0,    0,  -38,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -36,  -25,    0,  -32,
    0,  -38,    0,    0,   32,    0,    0,    0,   -8,    0,
    0,    0,   34,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   35,    0,   11,    0,    0,    0,    0,
   34,    0,    0,   36,    0,    0,  -19,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   11,    0,    0,    0,
};
short yygindex[] = {                                      0,
   63,   27,    0,   33,    0,   23,    0,    0,   40,   -3,
    0,    1,    0,   12,    0,
};
#define YYTABLESIZE 282
short yytable[] = {                                      13,
   33,   29,   29,   24,   18,   29,   12,   18,   24,   36,
   37,   24,   13,   62,   63,   20,   25,   38,   20,   12,
   29,   19,   18,   39,   19,   13,   24,   49,   27,   28,
   40,   31,   12,   20,   35,   13,   58,   26,   13,   19,
   41,   59,   50,   25,   33,   12,   15,   42,   45,   11,
   13,   40,   12,   51,   40,   48,   52,   56,   60,   46,
   47,   15,   61,   66,   68,   69,   74,   73,   76,   40,
   65,    1,   36,   34,    2,   37,    3,   67,   57,    0,
    0,    0,    0,   70,   29,   72,   18,    0,   80,   11,
   24,   55,    0,    0,   71,   78,    0,   20,    0,    0,
   75,    0,   11,   19,   77,    0,   79,    0,    0,    0,
    0,    0,    0,    0,    0,   11,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   11,    0,
    0,    0,    0,   40,    0,   11,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   29,   32,
    0,    0,    0,    3,    4,    5,    6,   29,   29,    7,
    8,    9,    0,   24,   24,   10,    3,    4,    5,    6,
    0,   20,    7,    8,    9,    0,    0,   19,   10,    3,
    4,    5,    6,    0,    0,    7,    0,    9,    0,    0,
    0,   23,    3,    4,   22,    6,    0,   40,    7,    3,
    4,   22,    6,    0,   23,    7,   40,   40,    0,    0,
    0,   23,
};
short yycheck[] = {                                      33,
   40,  259,   41,    3,   41,   44,   40,   44,   41,   13,
   59,   44,   33,  261,  262,   41,   40,   59,   44,   40,
   59,   41,   59,  267,   44,   33,   59,   44,    6,    7,
  266,    9,   40,   59,   12,   44,   40,   61,   33,   59,
  257,   41,   59,   40,   40,   40,   44,   25,   26,  123,
   59,   41,   40,   40,   44,   61,  259,   41,   41,   27,
   28,   59,   44,  259,   41,   44,   41,   61,  259,   59,
   48,    0,   41,   11,   41,   41,   41,   51,   39,   -1,
   -1,   -1,   -1,   61,  123,   63,  123,   -1,   77,  123,
  123,  125,   -1,   -1,   62,   73,   -1,  123,   -1,   -1,
   68,   -1,  123,  123,   72,   -1,   74,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
   -1,   -1,   -1,  123,   -1,  123,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  259,
   -1,   -1,   -1,  257,  258,  259,  260,  266,  267,  263,
  264,  265,   -1,  266,  267,  269,  257,  258,  259,  260,
   -1,  267,  263,  264,  265,   -1,   -1,  267,  269,  257,
  258,  259,  260,   -1,   -1,  263,   -1,  265,   -1,   -1,
   -1,  269,  257,  258,  259,  260,   -1,  257,  263,  257,
  258,  259,  260,   -1,  269,  263,  266,  267,   -1,   -1,
   -1,  269,
};
#define YYFINAL 1
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 269
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"'!'",0,0,0,0,0,0,"'('","')'",0,0,"','",0,0,0,0,0,0,0,0,0,0,0,0,0,0,"';'",0,
"'='",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,"'}'",0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,"OPNAME","LITERAL","NAME","IF","ELSE","ELSIF","WHILE","VAR","RETURN","AND",
"OR","NOT","FUN",
};
char *yyrule[] = {
"$accept : start",
"start : bodyexprs",
"opt_idlist :",
"opt_idlist : idlist",
"idlist : idlist ',' NAME",
"idlist : NAME",
"body : '{' bodyexprs '}'",
"fundecl : FUN NAME '(' opt_idlist ')' body",
"bodyexprs : bodyexprs expr ';'",
"bodyexprs : bodyexprs fundecl ';'",
"bodyexprs : bodyexprs VAR decl_vars ';'",
"bodyexprs :",
"decl_vars : NAME '=' expr",
"decl_vars : NAME",
"decl_vars : decl_vars ',' NAME '=' expr",
"decl_vars : decl_vars ',' NAME",
"expr : RETURN expr",
"expr : NAME '=' expr",
"expr : orexpr",
"orexpr : orexpr OR andexpr",
"orexpr : andexpr",
"andexpr : andexpr AND notexpr",
"andexpr : notexpr",
"notexpr : '!' notexpr",
"notexpr : binopexpr",
"binopexpr : binopexpr OPNAME smallexpr",
"binopexpr : smallexpr",
"smallexpr : OPNAME smallexpr",
"smallexpr : '(' expr ')'",
"smallexpr : NAME",
"smallexpr : NAME '(' args ')'",
"smallexpr : WHILE expr body",
"smallexpr : IF expr body ifrest",
"smallexpr : LITERAL",
"smallexpr : body",
"smallexpr : FUN '(' opt_idlist ')' body",
"args :",
"args : arglist",
"arglist : arglist ',' expr",
"arglist : expr",
"ifrest :",
"ifrest : ELSE body",
"ifrest : ELSIF expr body ifrest",
};
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
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
