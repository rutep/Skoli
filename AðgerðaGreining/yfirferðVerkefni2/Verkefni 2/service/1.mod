set DAGUR; ## Vinnudagar
set VINNA; ## �ll m�guleg vaktapl�n

param vaktir {VINNA, DAGUR} binary;
param min_starfsmenn {DAGUR} >= 0;

var Starfsmenn {VINNA} >= 0, integer;

minimize total: sum {v in VINNA}Starfsmenn[v]; #Viljum finna l�marks��rf � starfsm�nnum

subject to DailyDemand{d in DAGUR}:
 sum {v in VINNA} vaktir[v,d]*Starfsmenn[v] >= min_starfsmenn[d]; 