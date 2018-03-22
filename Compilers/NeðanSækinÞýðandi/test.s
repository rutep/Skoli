sum(x,b){
  var heidi;
  return x+b;
}

main(){
  var a,b;
  b = 0;

  if ( a = 1 + 2){
    println("ja");
  } else {
    println("nei");
  };
  
  while(b < 10){
    b = sum(b,1);
    println(b);
  };

  println( (1==1) || (1==2)); 
  println( (1==1) && (1==2)); 
  println( "true" || "false" );
  return 0;
}