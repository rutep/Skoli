%token<sval> LITERAL,NAME
%token<sval> OP1,OP2,OP3,OP4,OP5
%token IF,ELSE,ELSIF,WHILE,VAR
%token RETURN

%left RETURN, '='
%left OP1
%right OP2
%left OP3
%left OP4
%left OP5

%type <obj> program ,fundecl
%type <ival> ids

%%

start										/*@ \label{grammarstart} @*/
  : program { generateProgram(name,((Vector<Object>)($1)).toArray()); }
  | /* empty */
	;

program
  : program fundecl { ((Vector<Object>)($1)).add($2); $$=$1; }
  | fundecl         { $$=new Vector<Object>(); ((Vector<Object>)($$)).add($1); }
	;

fundecl
  : {
      varCount = 0;
      varTable = new HashMap<String,Integer>();
    }
    NAME '(' ids ')' '{'
      VAR ids ';'
    '}'
    {
      $$ = new Object[]{$2,$4,$8};
    }
  ;

ids
	:	/* empty */			{ $$=0; }
	|	ids NAME				{ addVar($2); $$=$1+1; }
	;

%%

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

public static void main( String args[] )
throws IOException
{
  NanoLispParser yyparser = new Parser(new FileReader(args[0]));
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

public static void main( String args[] )
	  	throws IOException
	{
		NanoLispParser yyparser = new NanoLispParser(new FileReader(args[0]));
		name = args[0].substring(0,args[0].lastIndexOf('.'));
		yyparser.yyparse();
	}