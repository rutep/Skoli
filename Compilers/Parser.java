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










public class Parser
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
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
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
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short OPNAME=257;
public final static short LITERAL=258;
public final static short NAME=259;
public final static short IF=260;
public final static short ELSE=261;
public final static short ELSIF=262;
public final static short WHILE=263;
public final static short VAR=264;
public final static short RETURN=265;
public final static short AND=266;
public final static short OR=267;
public final static short NOT=268;
public final static short FUN=269;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    2,    3,    3,    4,    5,    1,    1,    1,
    1,    7,    7,    7,    7,    6,    6,    6,    8,    8,
    9,    9,   10,   10,   11,   11,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   13,   13,   15,   15,   14,
   14,   14,
};
final static short yylen[] = {                            2,
    1,    0,    1,    3,    1,    3,    6,    3,    3,    4,
    0,    3,    1,    5,    3,    2,    3,    1,    3,    1,
    3,    1,    2,    1,    3,    1,    2,    3,    1,    4,
    3,    4,    1,    1,    5,    0,    1,    3,    1,    0,
    2,    4,
};
final static short yydefred[] = {                        11,
    0,    0,    0,   33,    0,    0,    0,    0,    0,    0,
   11,    0,    0,   34,    0,    0,    0,    0,   22,    0,
   26,    0,    0,   27,    0,    0,    0,    0,    0,    0,
   16,    0,    0,    0,    0,   23,    9,    8,    0,    0,
    0,   39,    0,    0,   17,    0,   31,    0,    0,   10,
    0,    5,    0,    0,    6,   28,    0,   21,   25,   30,
    0,    0,    0,   32,   12,    0,    0,    0,    0,   38,
   41,    0,    0,    0,   35,    4,    0,   14,    7,   42,
};
final static short yydgoto[] = {                          1,
    2,   53,   54,   14,   15,   16,   30,   17,   18,   19,
   20,   21,   43,   64,   44,
};
final static short yysindex[] = {                         0,
    0,  -20,   13,    0,  -23,   -7,   -7, -257,   -7,  -39,
    0,   -7,    6,    0,  -48,  -41, -243, -235,    0, -216,
    0,    4,    5,    0,   -7,   -7,  -73,  -73,   -5,  -16,
    0,   14, -202,  -33,   17,    0,    0,    0,    6,    6,
   13,    0,   18,   19,    0, -247,    0,   -7, -195,    0,
 -202,    0,   24,   22,    0,    0, -235,    0,    0,    0,
   -7,  -73,   -7,    0,    0,    7,   26,  -73, -190,    0,
    0,  -73,   -7,  -73,    0,    0, -247,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,   72,    0,    0,  -38,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -36,  -25,    0,  -32,
    0,  -38,    0,    0,   32,    0,    0,    0,   -8,    0,
    0,    0,   34,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   35,    0,   11,    0,    0,    0,    0,
   34,    0,    0,   36,    0,    0,  -19,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   11,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   63,   27,    0,   33,    0,   23,    0,    0,   40,   -3,
    0,    1,    0,   12,    0,
};
final static int YYTABLESIZE=282;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         13,
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
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
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
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=269;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'",null,null,"','",
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
null,null,null,null,null,null,null,"OPNAME","LITERAL","NAME","IF","ELSE",
"ELSIF","WHILE","VAR","RETURN","AND","OR","NOT","FUN",
};
final static String yyrule[] = {
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
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
