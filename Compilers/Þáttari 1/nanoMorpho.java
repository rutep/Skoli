import java.io.*;
public class nanoMorpho{

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
final static int OPNAME = 1010;

public static NanoLexer lexer;
public static int token;
public static int nextToken;
public static String lexem;
public static String nextLexem;

public static void init()
  throws Exception
  {
  lexer = new NanoLexer(new FileReader("test.s"));
  token = lexer.yylex();
  lexem = NanoLexer.getlexeme();
  nextToken = lexer.yylex();
  nextLexem = NanoLexer.getlexeme();
}

public static void advance(){
  try{
    if(token != 0){
      token = nextToken;
      lexem = nextLexem;
      nextToken = lexer.yylex();
      nextLexem = NanoLexer.getlexeme();
    }
  } catch(Exception e) {
    throw new Error(e);
  }
}

public static void println(String message){
  System.out.println(message);
}
public static void printlexeme(){
  System.out.println(lexem);
}


public static void over(String s){
  if(s.equals(lexem)){
    advance();
  } else {
    println("Villa fann " + lexem + " bjóst við " + s);
  }
}

// <program> ::= <function> | 
public static void program(){
  if(token == DEFINE){
    while(token == DEFINE){
      advance();
      function();
    } 
  } else {
    println("Bjóst við falli fann " + lexem);
  }
}

// <function> ::= NAME(<names)
//                [VAR [NAME,]*NAME ;]*
//                [[<expr>,]*<expr>;]*
public static void function(){
  if(NAME == token){
    advance();
    over("(");
    names();
    over(")");
  } else {
    println("Bjóst við nafni á falli fann " + lexem);
  }
  over("{");
  if(VAR == token){
    advance();
    if(NAME == token){
      advance();
      while(",".equals(lexem) && nextToken == NAME){
        over(",");
        advance();
      }
    } else {
      println("Villa vanntar breytunafn fann " + lexem);
      token = -1;
    }
    over(";");
  }
  if(!"}".equals(lexem)){
    while(!"}".equals(lexem)){
      expr();
      over(";");
    }
  } else {
    println("Það vanntar útleiðslu forrits");
    token = -1;
  }
  over("}");
}


// <expr> ::= RETURN<expr> | NAME = <expr> | <binopexpr>
public static void expr(){
  if(RETURN == token){
    advance();
    expr();
  }
  if(NAME == token){
    if(nextLexem.equals("=")){
      advance();
      over("=");
      expr();
    } 
  }
  binopexpr();
}

// <binopexpr> ::= <smallexp> | [<smallexp><op>]+<smallexp>
public static void binopexpr(){
  if (token == LITERAL && nextToken == OPNAME) {
    while (token == LITERAL && nextToken == OPNAME) {
      smallexpr();
    }
  } else {
    smallexpr();
  }
}

// <smallexpr> ::= (<expr>) | LITERAL | NAME | OPNAME<smallexpr>
public static void smallexpr(){
  if ("(".equals(lexem)) {
    over("(");
    expr();
    over(")");
  }
  if (LITERAL == token) {
    advance();
  }
  if (OPNAME == token) {
    advance();
    smallexpr();
  }
  if (NAME == token) {
    advance();
  }
}

// <names> ::= <name>,<names> | name | ""
public static void names(){
  if(NAME == token){
    advance();
    if(",".equals(lexem) && nextToken == NAME){
      over(",");
      names();
    }
  }
}


public static void main( String[] args )
  throws Exception
 {
  init();
  program();
 }
}