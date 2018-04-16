import java.io.*;

%%

%public
%class NanoMorphoLexer
%unicode
%byaccj
%line
%column

%{


public static String lexeme;

public NanoMorphoParser yyparser;

public NanoMorphoLexer( java.io.Reader r, NanoMorphoParser yyparser )
{
	this(r);
	this.yyparser = yyparser;
}

static int priority( String opname )
{
	switch( opname.charAt(0) )
	{
	case '|':
		return 1;
	case '&':
		return 2;
	case '!':
	case '=':
	case '<':
	case '>':
		return 3;
	case '+':
	case '-':
		return 4;
	case '*':
	case '/':
	case '%':
		return 5;
	default:
		throw new Error("Invalid opname");
	}
}

%}

/* Reglulegar skilgreiningar */

/* Regular definitions */

_DIGIT=[0-9]
_FLOAT={_DIGIT}+\.{_DIGIT}+([eE][+-]?{_DIGIT}+)?
_INT={_DIGIT}+
_STRING=\"([^\"\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|\\[0-7][0-7]|\\[0-7])*\"
_CHAR=\'([^\'\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|(\\[0-7][0-7])|(\\[0-7]))\'
_DELIM=[={},()\[\];]
_NAME=([:letter:]|{_DIGIT})+
_OPNAME=[\+\-*/!%&=><&|]+

%%

/* Lesgreiningarreglur */

{_DELIM} {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return yycharat(0);
}


{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.LITERAL;
}

"println" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.PRINTLN;	
}

"return" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.RETURN;
}

"else" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.ELSE;
}

"elsif" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.ELSIF;
}

"while" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.WHILE;
}

"if" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.IF;
}

"var" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.VAR;
}

{_NAME} {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.NAME;
}

"&&" {
	return NanoMorphoParser.AND;
}

"||" {
	return NanoMorphoParser.OR;
}

"!" {
	return NanoMorphoParser.NOT;
}

{_OPNAME} {
	yyparser.yylval = new NanoMorphoParserVal(yytext());
	switch( yytext().charAt(0) )
	{
	case '|':
		return NanoMorphoParser.OP1;
	case '&':
		return NanoMorphoParser.OP2;
	case '!':
	case '=':
	case '<':
	case '>':
		return NanoMorphoParser.OP3;
	case '+':
	case '-':
		return NanoMorphoParser.OP4;
	case '*':
	case '/':
	case '%':
		return NanoMorphoParser.OP5;
	default:
		throw new Error("Invalid operation name");
	}
}


";;;".*$ {
}

[ \t\r\n\f] {
}

. {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.ERROR;
}