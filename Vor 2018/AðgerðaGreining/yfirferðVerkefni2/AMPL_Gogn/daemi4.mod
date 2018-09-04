set P; 

param a{j in P}; 
param b;
param c{j in P}; 

var X{j in P}; 


maximize Total_Customers: sum{j in P} c[j]*X[j]; 
s.t. TV_limit: X['tv'] >= 10;
s.t. MAG_limit: X['magazine'] >= 0;
s.t. Budget: sum {j in P} a[j]*X[j] <= b;
