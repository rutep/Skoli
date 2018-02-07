
function asyncFunc(nr) {
    return new Promise((resolve, reject) => { // (A)
    setTimeout(() => resolve('DONE'), nr); // (B)
	});
}
asyncFunc(10000)
.then(x => console.log(petur))
.catch(er => console.log(er));

var petur;
console.log(petur)

asyncFunc(5000)
.then(x => petur = x);
