
const util = require('util');
const fs = require('fs');

// Breyta i promis api
const readFileAsync = util.promisify(fs.readFile);

// async skilar promis, vinnur með sync gögn
async function main() {
  let data = '';
  try {
    data = await readFileAsync('data.txt');
  } catch (e) {
    console.error('error', e);
  }
  console.log(data.toString('utf8'));
}

// kallar á main(). og grípur villu útaf fallið skilar promis.
main().catch(err => { console.error(err); });
