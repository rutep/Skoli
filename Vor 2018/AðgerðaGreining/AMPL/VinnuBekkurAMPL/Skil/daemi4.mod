# Gögn
set auglisingar;

param kostn{auglisingar};
param naertil{auglisingar};
param minSeldAfSjon;
param eydsluPeningur;

# Ákvörðunarbreyta
var seldEining {j in auglisingar} >= 0;

# Markfall
maximize bestiAvinningur: sum{j in auglisingar} seldEining[j] * naertil[j];

# Skorður
subject to tilTaekraPeninga: sum{j in auglisingar}
	seldEining[j] * kostn[j] <= eydsluPeningur;
subject to skordaSjonvarpEining: minSeldAfSjon <= seldEining['sjonV'];