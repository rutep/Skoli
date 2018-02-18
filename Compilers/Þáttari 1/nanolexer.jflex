/**
	JFlex lexgreiningard�mi byggt � lesgreini fyrir NanoLisp.
	H�fundur: Snorri Agnarsson, jan�ar 2017

	�ennan lesgreini m� ���a og keyra me� skipununum
		java -jar JFlex-1.6.0.jar nanolexer.jflex
		javac NanoLexer.java
		java NanoLexer inntaksskr� > �ttaksskr�
	Einnig m� nota forriti� 'make', ef vi�eigandi 'makefile'
	er til sta�ar:
		make test
 */

import java.io.*;

%%

%public
%class NanoLexer
%unicode
%byaccj

%{

// Skilgreiningar a tokum (tokens):
final static int ERROR = -1;
final static int IF = 1001;
final static int DEFINE = 1002;
final static int NAME = 1003;
final static int LITERAL = 1004;
final static int WHILE = 1005;
final static int VAR = 1006;
final static int ELSIF = 1007;
final static int RETURN = 1008;
final static int ELSE = 1009;


// Breyta sem mun innihalda les (lexeme):
public static String lexeme;

// �etta keyrir lexgreininn:
public static void main( String[] args ) throws Exception
{
	NanoLexer lexer = new NanoLexer(new FileReader(args[0]));
	int token = lexer.yylex();
	while( token!=0 )
	{
		System.out.println(""+token+": \'"+lexeme+"\'");
		token = lexer.yylex();
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
_NAME=([:letter:]+{_DIGIT}*)
_OPNAME=[\+\-*/!%&=><\^\|\|]+

%%

  /* Lesgreiningarreglur */

{_DELIM} {
	lexeme = yytext();
	return yycharat(0);
}

{_OPNAME} {
	lexeme = yytext();
	return yycharat(0);
}

{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
	lexeme = yytext();
	return LITERAL;
}

"return" {
	lexeme = yytext();
	return RETURN;
}

"else" {
	lexeme = yytext();
	return ELSE;
}

"elsif" {
	lexeme = yytext();
	return ELSIF;
}

"while" {
	lexeme = yytext();
	return WHILE;
}

"if" {
	lexeme = yytext();
	return IF;
}

"define" {
	lexeme = yytext();
	return DEFINE;
}

"var" {
	lexeme = yytext();
	return VAR;
}

{_NAME} {
	lexeme = yytext();
	return NAME;
}

";;;".*$ {
}

[ \t\r\n\f] {
}

. {
	lexeme = yytext();
	return ERROR;
}
