set BILL;

param timi {BILL} >= 0;
param hagnadur {BILL} >= 0;
param pantanir {BILL} >= 0;

var fjoldi {BILL} >= 0;

maximize z:
	sum{b in BILL} hagnadur[b]*(pantanir[b]+fjoldi[b]);

subject to y:
	sum{b in BILL} (timi[b]*(pantanir[b]+fjoldi[b])) <= 120;	

