
const fs = require('fs');
const MarkdownIt = require('markdown-it');

const input = 'test.md';
const output = 'test.html';
const encoding = 'utf8';

const data = fs.readFileSync(input);

// Núna er ég með data buffer
console.log('data',data);


// Til þess að breyta því sem er í bufferinum í sting
console.log('data',data.toString('utf8'));

// npmjs.com 
// find package markdown node
// Skoða pakka, finna það besta gott documentation 
// er gott hinnt. Ath stjörnu.
// Ath statistics.

// >npm init // byrjar node
// >npm install --save markdown-it// forrit verður að vera

const md = new MarkdownIt();

const result = md.render(data.toString(encoding));

console.log('prufa');
console.log(result);
// hlutur encoding með strengum utf8
fs.writeFileSync(output, result, {encoding});