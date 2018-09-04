/* original parser id follows */
/* yysccsid[] = "@(#)yaccpar	1.9 (Berkeley) 02/21/93" */
/* (use YYMAJOR/YYMINOR for ifdefs dependent on parser version) */

#define YYBYACC 1
#define YYMAJOR 1
#define YYMINOR 9
#define YYPATCH 20140715

#define YYEMPTY        (-1)
#define yyclearin      (yychar = YYEMPTY)
#define yyerrok        (yyerrflag = 0)
#define YYRECOVERING() (yyerrflag != 0)
#define YYENOMEM       (-2)
#define YYEOF          0
#define YYPREFIX "yy"

#define YYPURE 0


#if ! defined(YYSTYPE) && ! defined(YYSTYPE_IS_DECLARED)
/* Default: YYSTYPE is the semantic value type. */
typedef int YYSTYPE;
# define YYSTYPE_IS_DECLARED 1
#endif

/* compatibility with bison */
#ifdef YYPARSE_PARAM
/* compatibility with FreeBSD */
# ifdef YYPARSE_PARAM_TYPE
#  define YYPARSE_DECL() yyparse(YYPARSE_PARAM_TYPE YYPARSE_PARAM)
# else
#  define YYPARSE_DECL() yyparse(void *YYPARSE_PARAM)
# endif
#else
# define YYPARSE_DECL() yyparse(void)
#endif

/* Parameters sent to lex. */
#ifdef YYLEX_PARAM
# define YYLEX_DECL() yylex(void *YYLEX_PARAM)
# define YYLEX yylex(YYLEX_PARAM)
#else
# define YYLEX_DECL() yylex(void)
# define YYLEX yylex()
#endif

/* Parameters sent to yyerror. */
#ifndef YYERROR_DECL
#define YYERROR_DECL() yyerror(const char *s)
#endif
#ifndef YYERROR_CALL
#define YYERROR_CALL(msg) yyerror(msg)
#endif

extern int YYPARSE_DECL();

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
typedef short YYINT;
static const YYINT yylhs[] = {                           -1,
    0,    2,    2,    3,    3,    4,    5,    1,    1,    1,
    1,    7,    7,    7,    7,    6,    6,    6,    8,    8,
    9,    9,   10,   10,   11,   11,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   13,   13,   15,   15,   14,
   14,   14,
};
static const YYINT yylen[] = {                            2,
    1,    0,    1,    3,    1,    3,    6,    3,    3,    4,
    0,    3,    1,    5,    3,    2,    3,    1,    3,    1,
    3,    1,    2,    1,    3,    1,    2,    3,    1,    4,
    3,    4,    1,    1,    5,    0,    1,    3,    1,    0,
    2,    4,
};
static const YYINT yydefred[] = {                        11,
    0,    0,    0,   33,    0,    0,    0,    0,    0,    0,
   11,    0,    0,   34,    0,    0,    0,    0,   22,    0,
   26,    0,    0,   27,    0,    0,    0,    0,    0,    0,
   16,    0,    0,    0,    0,   23,    9,    8,    0,    0,
    0,   39,    0,    0,   17,    0,   31,    0,    0,   10,
    0,    5,    0,    0,    6,   28,    0,   21,   25,   30,
    0,    0,    0,   32,   12,    0,    0,    0,    0,   38,
   41,    0,    0,    0,   35,    4,    0,   14,    7,   42,
};
static const YYINT yydgoto[] = {                          1,
    2,   53,   54,   14,   15,   16,   30,   17,   18,   19,
   20,   21,   43,   64,   44,
};
static const YYINT yysindex[] = {                         0,
    0,  -20,   13,    0,  -23,   -7,   -7, -257,   -7,  -39,
    0,   -7,    6,    0,  -48,  -41, -243, -235,    0, -216,
    0,    4,    5,    0,   -7,   -7,  -73,  -73,   -5,  -16,
    0,   14, -202,  -33,   17,    0,    0,    0,    6,    6,
   13,    0,   18,   19,    0, -247,    0,   -7, -195,    0,
 -202,    0,   24,   22,    0,    0, -235,    0,    0,    0,
   -7,  -73,   -7,    0,    0,    7,   26,  -73, -190,    0,
    0,  -73,   -7,  -73,    0,    0, -247,    0,    0,    0,
};
static const YYINT yyrindex[] = {                         0,
    0,   72,    0,    0,  -38,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -36,  -25,    0,  -32,
    0,  -38,    0,    0,   32,    0,    0,    0,   -8,    0,
    0,    0,   34,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   35,    0,   11,    0,    0,    0,    0,
   34,    0,    0,   36,    0,    0,  -19,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   11,    0,    0,    0,
};
static const YYINT yygindex[] = {                         0,
   63,   27,    0,   33,    0,   23,    0,    0,   40,   -3,
    0,    1,    0,   12,    0,
};
#define YYTABLESIZE 282
static const YYINT yytable[] = {                         13,
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
static const YYINT yycheck[] = {                         33,
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
#define YYUNDFTOKEN 287
#define YYTRANSLATE(a) ((a) > YYMAXTOKEN ? YYUNDFTOKEN : (a))
#if YYDEBUG
static const char *const yyname[] = {

"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"'!'",0,0,0,0,0,0,"'('","')'",0,0,"','",0,0,0,0,0,0,0,0,0,0,0,0,0,0,"';'",0,
"'='",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,"'}'",0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,"OPNAME","LITERAL","NAME","IF","ELSE","ELSIF","WHILE","VAR","RETURN","AND",
"OR","NOT","FUN",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"illegal-symbol",
};
static const char *const yyrule[] = {
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

int      yydebug;
int      yynerrs;

int      yyerrflag;
int      yychar;
YYSTYPE  yyval;
YYSTYPE  yylval;

/* define the initial stack-sizes */
#ifdef YYSTACKSIZE
#undef YYMAXDEPTH
#define YYMAXDEPTH  YYSTACKSIZE
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 10000
#define YYMAXDEPTH  10000
#endif
#endif

#define YYINITSTACKSIZE 200

typedef struct {
    unsigned stacksize;
    YYINT    *s_base;
    YYINT    *s_mark;
    YYINT    *s_last;
    YYSTYPE  *l_base;
    YYSTYPE  *l_mark;
} YYSTACKDATA;
/* variables for the parser stack */
static YYSTACKDATA yystack;
#line 97 "test.y"

#line 292 "y.tab.c"

#if YYDEBUG
#include <stdio.h>		/* needed for printf */
#endif

#include <stdlib.h>	/* needed for malloc, etc */
#include <string.h>	/* needed for memset */

/* allocate initial stack or double stack size, up to YYMAXDEPTH */
static int yygrowstack(YYSTACKDATA *data)
{
    int i;
    unsigned newsize;
    YYINT *newss;
    YYSTYPE *newvs;

    if ((newsize = data->stacksize) == 0)
        newsize = YYINITSTACKSIZE;
    else if (newsize >= YYMAXDEPTH)
        return YYENOMEM;
    else if ((newsize *= 2) > YYMAXDEPTH)
        newsize = YYMAXDEPTH;

    i = (int) (data->s_mark - data->s_base);
    newss = (YYINT *)realloc(data->s_base, newsize * sizeof(*newss));
    if (newss == 0)
        return YYENOMEM;

    data->s_base = newss;
    data->s_mark = newss + i;

    newvs = (YYSTYPE *)realloc(data->l_base, newsize * sizeof(*newvs));
    if (newvs == 0)
        return YYENOMEM;

    data->l_base = newvs;
    data->l_mark = newvs + i;

    data->stacksize = newsize;
    data->s_last = data->s_base + newsize - 1;
    return 0;
}

#if YYPURE || defined(YY_NO_LEAKS)
static void yyfreestack(YYSTACKDATA *data)
{
    free(data->s_base);
    free(data->l_base);
    memset(data, 0, sizeof(*data));
}
#else
#define yyfreestack(data) /* nothing */
#endif

#define YYABORT  goto yyabort
#define YYREJECT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR  goto yyerrlab

int
YYPARSE_DECL()
{
    int yym, yyn, yystate;
#if YYDEBUG
    const char *yys;

    if ((yys = getenv("YYDEBUG")) != 0)
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = YYEMPTY;
    yystate = 0;

#if YYPURE
    memset(&yystack, 0, sizeof(yystack));
#endif

    if (yystack.s_base == NULL && yygrowstack(&yystack) == YYENOMEM) goto yyoverflow;
    yystack.s_mark = yystack.s_base;
    yystack.l_mark = yystack.l_base;
    yystate = 0;
    *yystack.s_mark = 0;

yyloop:
    if ((yyn = yydefred[yystate]) != 0) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = YYLEX) < 0) yychar = YYEOF;
#if YYDEBUG
        if (yydebug)
        {
            yys = yyname[YYTRANSLATE(yychar)];
            printf("%sdebug: state %d, reading %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: state %d, shifting to state %d\n",
                    YYPREFIX, yystate, yytable[yyn]);
#endif
        if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack) == YYENOMEM)
        {
            goto yyoverflow;
        }
        yystate = yytable[yyn];
        *++yystack.s_mark = yytable[yyn];
        *++yystack.l_mark = yylval;
        yychar = YYEMPTY;
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

    YYERROR_CALL("syntax error");

    goto yyerrlab;

yyerrlab:
    ++yynerrs;

yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yystack.s_mark]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: state %d, error recovery shifting\
 to state %d\n", YYPREFIX, *yystack.s_mark, yytable[yyn]);
#endif
                if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack) == YYENOMEM)
                {
                    goto yyoverflow;
                }
                yystate = yytable[yyn];
                *++yystack.s_mark = yytable[yyn];
                *++yystack.l_mark = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: error recovery discarding state %d\n",
                            YYPREFIX, *yystack.s_mark);
#endif
                if (yystack.s_mark <= yystack.s_base) goto yyabort;
                --yystack.s_mark;
                --yystack.l_mark;
            }
        }
    }
    else
    {
        if (yychar == YYEOF) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = yyname[YYTRANSLATE(yychar)];
            printf("%sdebug: state %d, error recovery discards token %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
        yychar = YYEMPTY;
        goto yyloop;
    }

yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: state %d, reducing by rule %d (%s)\n",
                YYPREFIX, yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    if (yym)
        yyval = yystack.l_mark[1-yym];
    else
        memset(&yyval, 0, sizeof yyval);
    switch (yyn)
    {
    }
    yystack.s_mark -= yym;
    yystate = *yystack.s_mark;
    yystack.l_mark -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: after reduction, shifting from state 0 to\
 state %d\n", YYPREFIX, YYFINAL);
#endif
        yystate = YYFINAL;
        *++yystack.s_mark = YYFINAL;
        *++yystack.l_mark = yyval;
        if (yychar < 0)
        {
            if ((yychar = YYLEX) < 0) yychar = YYEOF;
#if YYDEBUG
            if (yydebug)
            {
                yys = yyname[YYTRANSLATE(yychar)];
                printf("%sdebug: state %d, reading %d (%s)\n",
                        YYPREFIX, YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == YYEOF) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: after reduction, shifting from state %d \
to state %d\n", YYPREFIX, *yystack.s_mark, yystate);
#endif
    if (yystack.s_mark >= yystack.s_last && yygrowstack(&yystack) == YYENOMEM)
    {
        goto yyoverflow;
    }
    *++yystack.s_mark = (YYINT) yystate;
    *++yystack.l_mark = yyval;
    goto yyloop;

yyoverflow:
    YYERROR_CALL("yacc stack overflow");

yyabort:
    yyfreestack(&yystack);
    return (1);

yyaccept:
    yyfreestack(&yystack);
    return (0);
}
