set SUGAR;
set SUPPLIER;

param blend {SUGAR} >= 0;
param combination {SUPPLIER, SUGAR} >= 0;
param cost {SUPPLIER} > 0;

var order {s in SUPPLIER} >= 0;

minimize total_cost:
	sum{s in SUPPLIER} cost[s]*order[s];
	
subject to comb {u in SUGAR}:
	sum{s in SUPPLIER} combination[s,u]*order[s] = blend[u];
