timi <- read.table("dagar.txt")
# timi["V2"]
# timi

markmid <- rep(0.75,30)
dagar <- (1:30)
x = 1

for(i in markmid){
    markmid[x] = i * x
    x = x + 1
}
plot(1, type="n", xlab="Dagar", ylab="Tími sem er búinn að skrifa", 
	ylim=c(0,22.5), xlim=c(0,30))
lines(markmid, col="blue")
lines(timi["V1"], col="red")
lines(timi["V2"])
title(main="NaNoWriMor")
