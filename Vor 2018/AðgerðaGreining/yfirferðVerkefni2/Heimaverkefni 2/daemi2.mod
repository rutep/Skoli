set sugars; #tegund
set combinations; #blondurnar

param out_min {sugars}; #minnsti leyfilegi fjoldi af hverjum sykri i tonnum.
param cost {combinations}; #Verd fyrir hvert tonn af blondu A til G.
param amt {combinations,sugars}; #Magn af hverjum sykri i hverri blondu fyrir sig.

var blend_amt {j in combinations} >= 10; # Breyta fyrir fjolda af blondu A til G sem notad verdur, i tonnum.
minimize total_cost: sum{i in combinations} blend_amt[i] * cost[i]; #minimize summu yfir magnid af hverri blondu sinnu verd fyrir blondu

subject to min_sugar_amt {i in sugars}: # subject to Fa amk haerri gildi en eru i out_min og breyta "prosentum af tonni" i tonnagildi.
	out_min[i] <= sum {j in combinations} 0.01 * amt[j,i] * blend_amt[j]; 

