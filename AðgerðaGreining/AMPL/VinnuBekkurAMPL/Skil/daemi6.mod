# Gögn
set electronics;

param values 	{j in electronics};
param weight 	{j in electronics};
param volume	{j in electronics};
param available {j in electronics};
param maxWeight;
param maxVolume;

# Ákvörðunarbreytur
var demand 		{j in electronics};

 # Markfall
 maximize totalProfit: sum {j in electronics} demand[j] * values[j];

# Skorður
subject to weightLimit: sum{j in electronics} demand[j] * weight[j] <= maxWeight;
subject to volumeLimit: sum{j in electronics} demand[j] * volume[j] <= maxVolume;
subject to LimitOnAvalibility {j in electronics}:  0 <= demand[j] <= available[j];
 





 