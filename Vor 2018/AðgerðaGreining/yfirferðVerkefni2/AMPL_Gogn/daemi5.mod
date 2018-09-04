set P; 

param time{j in P}; 
param profit{j in P};
param orders{j in P}; 
param maxtime;

var X{j in P}; 

maximize Total_Profit: sum{j in P} profit[j]*X[j]; 
s.t. Order_limit: sum{j in P} (X[j]*time[j]) <= maxtime;
s.t. Order_min {j in P}: (X[j]-orders[j]) <= 0;
s.t. Var_min {j in P}: -X[j] <=0;