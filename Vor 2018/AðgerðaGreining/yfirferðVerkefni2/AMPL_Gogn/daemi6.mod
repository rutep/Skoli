set P; 

param Value{j in P}; 
param Weight{j in P};
param Volume{j in P};
param Available{j in P};
param maxpound;
param maxcubic;


 

var X{j in P}; 


maximize 	Total_profit: 	sum{j in P} Value[j]*X[j]; 
s.t. 		Weight_limit: 	sum{j in P} Weight[j]*X[j] <= maxpound;
s.t. 		Cubic_limit: 	sum{j in P} Volume[j]*X[j] <= maxcubic;
s.t. 		Amount_limit {j in P}: (X[j] - Available[j]) <= 0;
s.t. 		Sold_min {j in P}:  -X[j] <= 0;