var XB;
var XC;
maximize Profit: 25 * XB + 30 * XC;
subject to Time: (1/200) * XB + (1/140) * XC <= 40;
subject to B_limit: 0 <= XB <= 6000;
#�essi skor�a setur takm�rk � XC. Munum a� vera dugleg a� nota komment: 
subject to C_limit: 0 <= XC <= 4000;