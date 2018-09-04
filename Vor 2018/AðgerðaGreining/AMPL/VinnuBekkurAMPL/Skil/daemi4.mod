# G�gn
set auglisingar;

param kostn{auglisingar};
param naertil{auglisingar};
param minSeldAfSjon;
param eydsluPeningur;

# �kv�r�unarbreyta
var seldEining {j in auglisingar} >= 0;

# Markfall
maximize bestiAvinningur: sum{j in auglisingar} seldEining[j] * naertil[j];

# Skor�ur
subject to tilTaekraPeninga: sum{j in auglisingar}
	seldEining[j] * kostn[j] <= eydsluPeningur;
subject to skordaSjonvarpEining: minSeldAfSjon <= seldEining['sjonV'];