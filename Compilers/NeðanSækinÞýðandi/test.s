;;; Fibonacci
fibo(n){
  var x;
  if(n < 0) {
    return n * -1; 
  } elsif( n == 0) { 
    return n * ( 1 - 2);
  } else {
    return fibo(n-1) + fibo(n-2);
  };
}

main(){
  var n,y;
  n = 0;
  y = 1;
  while(n < 12){
    n = n+1;
    println(fibo(n));
  };
  println(-y);
  println( 1 == 1);
  println( 1 < 1);
  println( 2 > 1);
  println( 1 != 2);
  println( 1 !> 2);
  
}