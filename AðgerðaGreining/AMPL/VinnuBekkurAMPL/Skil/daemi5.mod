# Gögn
set cars;

param time{cars};
param profit{cars};
param orders{cars};
param productionTime;

# Ákvörðunarbreita
var nrCarsProduced{j in cars} >= orders[j];

# Markfall
maximize soldProfit:
	sum{j in cars} profit[j] * nrCarsProduced[j];

# Skorður
subject to timeLimit:
	sum{j in cars} time[j] * nrCarsProduced[j] <= productionTime;