set MOVEMENT;

param t_max{MOVEMENT} >= 0;
param cal{MOVEMENT} >= 0;
param minimum >= 0;

var time {j in MOVEMENT}  >= 0;

minimize Total_time : sum {j in MOVEMENT} time[j]*t_max[j] ;

subject to S : 2000 <= sum {j in MOVEMENT} time[j]*cal[j];
	
subject to  R {j in MOVEMENT}: time[j] <= t_max[j];



