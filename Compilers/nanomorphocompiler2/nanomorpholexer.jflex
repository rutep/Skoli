/**	
	Höfundur: Snorri Agnarsson, febrúar 2017

	Þennan þáttara má þýða og keyra með skipununum
		java -jar JFlex-1.6.1.jar nanomorpholexer.jflex
		byacc -Jclass=NanoMorphoParser nanomorphoparser.byaccj
		javac NanoMorphoLexer.java NanoMorphoParser.java
		java NanoMorphoParser inntaksskrá
	Einnig má nota forritið 'make', ef viðeigandi 'makefile'
	er til staðar:
		make test
 */

import java.io.*;

%%

%public
%class NanoMorphoLexer
%unicode
%byaccj
%line
%column

%{

NanoMorphoParser yyparser;
public NanoMorphoLexer( Reader r, NanoMorphoParser p )
{
	this(r);
	yyparser = p;
}
public int getLine() { return yyline+1; }
public int getColumn() { return yycolumn+1; }

static int priority( String opname )
{
	switch( opname.charAt(0) )
	{
	case '^':
	case '?':
	case '~':
		return 1;
	case ':':
		return 2;
	case '|':
		return 3;
	case '&':
		return 4;
	case '!':
	case '=':
	case '<':
	case '>':
		return 5;
	case '+':
	case '-':
		return 6;
	case '*':
	case '/':
	case '%':
		return 7;
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
_DELIM=[(){}\[\],;=]
_NAME=([:letter:]|{_DIGIT})+
_OPNAME=[\+\-*/!%&=><\:\^\~&|?]+

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

"if" {
	return NanoMorphoParser.IF;
}

"else" {
	return NanoMorphoParser.ELSE;
}

"elsif" {
	return NanoMorphoParser.ELSIF;
}

"while" {
	return NanoMorphoParser.WHILE;
}

"var" {
	return NanoMorphoParser.VAR;
}

"return" {
	return NanoMorphoParser.RETURN;
}

"go" {
	return NanoMorphoParser.GO;
}

"fun" {
	return NanoMorphoParser.FUN;
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
	case '^':
	case '?':
	case '~':
		return NanoMorphoParser.OP1;
	case ':':
		return NanoMorphoParser.OP2;
	case '|':
		return NanoMorphoParser.OP3;
	case '&':
		return NanoMorphoParser.OP4;
	case '!':
	case '=':
	case '<':
	case '>':
		return NanoMorphoParser.OP5;
	case '+':
	case '-':
		return NanoMorphoParser.OP6;
	case '*':
	case '/':
	case '%':
		return NanoMorphoParser.OP7;
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
