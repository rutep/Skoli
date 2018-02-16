import java.io.*;
public class nanoMorpho{



// **************************************
static class Scanner
 {
  private String nextToken;
  private double nextNumber;
  private java.io.StreamTokenizer tokenizer;

  public String getNextToken()
  {
   return nextToken;
  }

  public double getNextNumber()
  {
   return nextNumber;
  }

  public Scanner( java.io.Reader r )
  {
  // Breyta
   tokenizer = new java.io.StreamTokenizer(r);
   tokenizer.ordinaryChars(0,255);
   tokenizer.parseNumbers();
   tokenizer.ordinaryChar('-');
   advance();
  }
 
  public void advance()
  {
   try
   {
    int type = tokenizer.nextToken();
    if( type==java.io.StreamTokenizer.TT_NUMBER )
    {
     nextToken = "number";
     nextNumber = tokenizer.nval;
    }
    else if( type==java.io.StreamTokenizer.TT_EOF )
    {
     nextToken = "eof";
    }
    else
    {
     nextToken = ""+(char)type;
    }
    }
   catch( Exception e )
   {
    throw new Error(e);
   }
  }
 }



 // *************************************************************
 /*

 static double L( Scanner s )
 {
  double x=T(s);
  
  for(;;)
  {
   if( s.getNextToken().equals("*") )
   {
    s.advance();
    x = x*T(s);
   }
   else if( s.getNextToken().equals("/") )
   {
    s.advance();
    x = x/T(s);
   }
   else
    break;       
  }


  return x;
 }

 static double T(Scanner s){
  double x=P(s);
  
  while( s.getNextToken().equals("^") )
  {
   s.advance();
   x=java.lang.Math.pow(x,T(s));
  }
  return x;
 }

 static double P( Scanner s )
 {
  if( s.getNextToken().equals("(") )
  {
   s.advance();
   double x = F(s);
   if( !s.getNextToken().equals(")") )
   {
    throw new Error("expected ), found "+s.nextToken);
   }
   s.advance();
   return x;
  }
  else if( s.getNextToken().equals("number") )
  {
   double x = s.getNextNumber();
   s.advance();
   return x;
  }
  else
  {
   throw new Error("expected ( or number, found "+s.nextToken);
  }
 }
 
 static double F( Scanner s )
 {
  double x=L(s);
 
  for(;;)
  {
   if( s.getNextToken().equals("+") )
   {
    s.advance();
    x = x+L(s);
   }
   else if( s.getNextToken().equals("-") )
   {
    s.advance();
    x = x-L(s);
   }
   else
    break;       
  }
 
  return x;
 }
*/
// *************************** Formula kóði ******************



// *************************** Endir formúlu kóði ************

static double program( Scanner s )
{
  if( s.getNextToken().equals("define") )
  {
   s.advance();
   System.out.println("Virkar");
  }
  return 0.0;
}


// þetta keyrir lexgreininn:
//   <F> ::= <L> <Fm>
//   <Fm> ::= + <L> <Fm>
//   <Fm> ::= e
//   <L> ::= <T> <Lm>
//   <Lm> ::= * <T> <Lm>
//   <Lm> ::= e
//   <T> ::= ( <F> )
//   <T> ::= tala

// Þetta keiri nanoMorpho þáttari 1
//  <program>  ::= <function>
//  <function> ::= <name> (  ) | <name>(<name>)

// Þarf 4 breitur 
// 2 token breitur    nextToken og nextnextToken
// tvær lexembreitur  nextLexem og nextnextLexem

// Gera advance til þess að hliðra breitunum

// 
// over(næsta toker er þetta , bjóst við þessu)
// 
static NanoLexer lexer;

public static void main( String[] args )
  throws Exception
 {
  lexer = new NanoLexer(new FileReader(args[0]));
  int token = lexer.yylex();
  String arg = "";
  while( token!=0 )
	{
    System.out.println(token + ": " + NanoLexer.getlexeme());
    arg += NanoLexer.getlexeme();
  	token = lexer.yylex();
  }

  Scanner s = new Scanner(new java.io.StringReader(arg));
  try
  {
   // double x = F(s);
   double x = program(s);
   if( !s.nextToken.equals("eof") )
    throw new Error("expected eof or operation");
   System.out.println(" = " + x);
  }
  catch( Throwable e )
  {
   System.out.println("Error: "+e.getMessage()+", Next token: "+s.nextToken);
  }
 }

}