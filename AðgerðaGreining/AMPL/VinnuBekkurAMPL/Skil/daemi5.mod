# G�gn
set cars;

param time{cars};
param profit{cars};
param orders{cars};
param productionTime;

# �kv�r�unarbreita
var nrCarsProduced{j in cars} >= orders[j];

# Markfall
maximize soldProfit:
	sum{j in cars} profit[j] * nrCarsProduced[j];

# Skor�ur
subject to timeLimit:
	sum{j in cars} time[j] * nrCarsProduced[j] <= productionTime;