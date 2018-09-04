%token OPNAME,LITERAL,NAME
%token IF,ELSE,ELSIF,WHILE,VAR
%token RETURN,AND,OR,NOT,FUN

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
	|	NAME '=' expr
	|	orexpr
	;

orexpr
	:	orexpr OR andexpr
	|	andexpr
	;

andexpr
	:	andexpr AND notexpr
	|	notexpr
	;

notexpr
	:	'!' notexpr
	|	binopexpr
	;

binopexpr
	:	binopexpr OPNAME smallexpr
	|	smallexpr
	;

smallexpr
	:	OPNAME smallexpr
	|	'(' expr ')'
	|	NAME
	|	NAME '(' args ')'
	|	WHILE expr body
	|	IF expr body ifrest
	|	LITERAL
	|	body
	|	FUN '(' opt_idlist ')' body
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
	:	/* empty */
	|	ELSE body
	|	ELSIF expr body ifrest
	;

%%
