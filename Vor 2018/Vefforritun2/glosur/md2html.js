
// Promis: Útfærsla
const fs = require('fs');
const MarkdownIt = require('markdown-it');
const util = require('util');

const input = 'test.md';
const output = 'test.html';
const encoding = 'utf8';

const readFileAsync = util.promisify(fs.readFile);
const writeFileAsync = util.promisify(fs.readFile);

// Bæta og gera með callback en ekki FyleSync
async function read(file) {
    // Á að hætta að vera sync og vera async
    // Promis 2
    return readFileAsync(file).then
        (data => data.toString(encoding));
    };


// async
async function write(filepath, content, cb) {
    const md = new MarkdownIt();
    const result = md.render(content);

    // Skilar promise 1
    return writeFileAsync(filepath, result, {encoding});

    
}

async function main(){
    const data = await read(input);
    await write(output,data);
    console.log('Done witing', output);
}

main();

// Lesa input og svo log data
/*
read(input)
    .then(data => write(output, data))
    .then(data => console.log('Done writing ', output));
*/
/*
// Þetta fall keirist bara þegar stýrikerfið er 
// búið að lesa úr skrá og í skrá
read(input, (data) => {
    console.log(data);
    write(output, data, (data) => {
        console.info('Done writing', input);
    });
});
*/
/* sync
function write(filepath, content){
    const md = new MarkdownIt();
    const result = md.render(content);    
    fs.writeFileSync(filepath, result, {encoding});
}
*/

/*
Async: Útfærsla
const fs = require('fs');
const MarkdownIt = require('markdown-it');

const input = 'test.md';
const output = 'test.html';
const encoding = 'utf8';

// Bæta og gera með callback en ekki FyleSync
function read(file,cb){
    // Á að hætta að vera sync og vera async
    // Promis
    const data = fs.readFile(file,(err, data) => {
        cb(data.toString(encoding));
    });
}

// async
function write(filepath, content,cb){
    const md = new MarkdownIt();
    const result = md.render(content);

    fs.writeFile(filepath, result, {encoding}, (err,data) => {
        cb();
    });
}

/* sync
function write(filepath, content){
    const md = new MarkdownIt();
    const result = md.render(content);    
    fs.writeFileSync(filepath, result, {encoding});
}
// Þetta fall keirist bara þegar stýrikerfið er 
// búið að lesa úr skrá og í skrá
read(input, (data) => {
    console.log(data);
    write(output,data, (data) => {
        console.info('Done writing', input);
    });
});
*/



/*
Sync: útfærsla
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
fs.writeFileSync(output, result, {encoding}); */