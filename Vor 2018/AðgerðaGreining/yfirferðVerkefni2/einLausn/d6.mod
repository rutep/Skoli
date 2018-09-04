set HLUTIR;

param v{HLUTIR} > 0;
param a{HLUTIR} > 0; 	#lagmark f. thyngd hlutar
param r{HLUTIR} > 0; 	#lagmark f. rummal hlutar 
param f_min{HLUTIR} > 0;
param f_max{HLUTIR} > 0;

var X {j in HLUTIR} >= 0;

maximize TEKJUR: sum{j in HLUTIR} v[j]*X[j];

subject to WEIGHT: sum {j in HLUTIR} X[j]*a[j] <= 500;
	
subject to VOLUME : sum {j in HLUTIR} X[j]*r[j];

subject to H {j in HLUTIR}: X[j] <= f_max[j];