;;; Fibonacci
fibo(n){
  var x;
  if(n < 0) {
    return n * ( 1 - 2); 
  } elsif( n == 0) { 
    return n * ( 1 - 2);
  } else {
    return fibo(n-1) + fibo(n-2);
  };
}

main(){
  var n;
  n = 0;
  while(n < 12){
    n = n+1;
    println(fibo(n));
  };
}