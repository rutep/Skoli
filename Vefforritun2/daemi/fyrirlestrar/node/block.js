// Fall kallað eftir 1000 ms
// Event loop
setTimeout(() => {
  console.log('Finished@' + new Date().toTimeString());
}, 1000);

// Sec sem við ætlum ap geyma
const s = new Date();
console.log('Start@' + s.toTimeString());

let i = 0;
// 10 ms ætlum við að ýtra
const iterateForInMs = 1500;
while(new Date().getTime() < s.getTime() + iterateForInMs) {
   i++;
}


console.log('Exit@' + new Date().toTimeString());
console.log(i + ' iterations.');
