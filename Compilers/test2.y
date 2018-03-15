%token<sval> LITERAL,NAME
%token<sval> OP1,OP2,OP3,OP4,OP5,OP6,OP7
%token IF,ELSE,ELSIF,WHILE,VAR
%token RETURN,AND,OR,NOT,FUN,UNOP

%left RETURN, '='
%left OR
%left AND
%left '!'
%left OP1
%right OP2
%left OP3
%left OP4
%left OP5
%left OP6
%left OP7
%left UNOP

%type<obj> expr


%%

start
	:	bodyexprs
	;

opt_idlist
	:	/* empty */
	|	idlist
	;

idlist
	:	idlist ',' NAME
	|	NAME
	;

body
	:	'{' bodyexprs '}'
	;

fundecl
	:	FUN NAME '(' opt_idlist ')' body
	;

bodyexprs
	:	bodyexprs expr ';'
	|	bodyexprs fundecl ';'
	|	bodyexprs VAR decl_vars ';'
	|	/* empty */
	;

decl_vars
	:	NAME '=' expr
	|	NAME
	|	decl_vars ',' NAME '=' expr
	|	decl_vars ',' NAME
	;

expr
	:	RETURN expr
		{ $$ = new Object[]{"RETURN",$2}; }
	|	NAME '=' expr
		{ $$ = new Object[]{"ASSIGN",varPos($1),$3}; }
	|	expr OR expr
		{ $$ = new Object[]{"OR",$1,$3}; }
	|   expr AND expr
		{ $$ = new Object[]{"AND",$1,$3}; }
	|   '!' expr
		{ $$ = new Object[]{"NOT",$2}; }
	|   expr OP1 expr
		{ $$ = new Object[]{"CALL",$2,new Object[]{$1,$3}}; }
	|   expr OP2 expr
		{ $$ = new Object[]{"CALL",$2,new Object[]{$1,$3}}; }
	|   expr OP3 expr
		{ $$ = new Object[]{"CALL",$2,new Object[]{$1,$3}}; }
	|   expr OP4 expr
		{ $$ = new Object[]{"CALL",$2,new Object[]{$1,$3}}; }
	|   expr OP5 expr
		{ $$ = new Object[]{"CALL",$2,new Object[]{$1,$3}}; }
	|   expr OP6 expr
		{ $$ = new Object[]{"CALL",$2,new Object[]{$1,$3}}; }
	|   expr OP7 expr
		{ $$ = new Object[]{"CALL",$2,new Object[]{$1,$3}}; }
	|   OP1 expr %prec UNOP
		{ $$ = new Object[]{"CALL",$1,new Object[]{$2}}; }
	|   OP2 expr %prec UNOP
		{ $$ = new Object[]{"CALL",$1,new Object[]{$2}}; }
	|   OP3 expr %prec UNOP
		{ $$ = new Object[]{"CALL",$1,new Object[]{$2}}; }
	|   OP4 expr %prec UNOP
		{ $$ = new Object[]{"CALL",$1,new Object[]{$2}}; }
	|   OP5 expr %prec UNOP
		{ $$ = new Object[]{"CALL",$1,new Object[]{$2}}; }
	|   OP6 expr %prec UNOP
		{ $$ = new Object[]{"CALL",$1,new Object[]{$2}}; }
	|   OP7 expr %prec UNOP
		{ $$ = new Object[]{"CALL",$1,new Object[]{$2}}; }
	|	'(' expr ')'
		{ $$ = $2; }
	|	NAME
		{ $$ = new Object[]{"FETCH",varPos($1)}; }
	|	NAME '(' args ')'
		{ $$ = new Object[]{"CALL",$1,$3}; }
	|	WHILE expr body
			{ $$=new Object[]{"WHILE",$2,$3}; }
	|	IF expr body
	|	IF expr body ifrest
	|	LITERAL
	|	body
	|	FUN '(' countvars opt_idlist ')' body	
		{ $$ = new Object[]{"MAKECLOSURE",$3,$4,$6}; }
	;

args
	:	/* empty */
	|	arglist
	;

arglist
	:	arglist ',' expr
	|	expr
	;

ifrest
	:	ELSE body
	|	ELSIF expr body
	|	ELSIF expr body ifrest
	;

%%
