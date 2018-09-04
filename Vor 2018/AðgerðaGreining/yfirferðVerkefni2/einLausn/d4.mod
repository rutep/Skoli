set AD;

param f_min {AD} >= 0;
param f_max {AD} >= 0;
param cost {AD} >= 0;

param reach {AD} > 0;

var Buy {j in AD} >= f_min[j], <= f_max[j];

maximize total : sum {j in AD} reach[j]*Buy[j];

subject to Cost: sum{j in AD} cost[j]*Buy[j];

subject to F{j in AD} : f_max[j] >= f_min[j] 
