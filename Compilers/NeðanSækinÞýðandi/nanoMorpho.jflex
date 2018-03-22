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
_OPNAME=[!%\^]+

%%

/* Lesgreiningarreglur */

{_DELIM} {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return yycharat(0);
}

{_OPNAME} {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OPNAME;
}

{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.LITERAL;
}

"+" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP1;
}

"-" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP2;
}

"/" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP3;
}

"*" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP4;
}

"<" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP5;
}

">" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP6;
}

"==" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP7;
}

"||" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP9;
}

"&&" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.OP8;
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

"define" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.DEFINE;
}

"var" {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.VAR;
}

{_NAME} {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.NAME;
}

";;;".*$ {
}

[ \t\r\n\f] {
}

. {
yyparser.yylval = new NanoMorphoParserVal(yytext());
return NanoMorphoParser.ERROR;
}