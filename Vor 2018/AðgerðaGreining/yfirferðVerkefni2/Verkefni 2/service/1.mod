set DAGUR; ## Vinnudagar
set VINNA; ## Öll möguleg vaktaplön

param vaktir {VINNA, DAGUR} binary;
param min_starfsmenn {DAGUR} >= 0;

var Starfsmenn {VINNA} >= 0, integer;

minimize total: sum {v in VINNA}Starfsmenn[v]; #Viljum finna lámarksşörf á starfsmönnum

subject to DailyDemand{d in DAGUR}:
 sum {v in VINNA} vaktir[v,d]*Starfsmenn[v] >= min_starfsmenn[d]; 