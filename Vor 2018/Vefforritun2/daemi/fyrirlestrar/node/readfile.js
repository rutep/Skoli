const fs = require('fs');

// Les úr skrá og prentar út innihald buffers
// Async
// Hugsa ólínulega


fs.readFile('data.txt', (err, data) => {
  if (err) {
    console.log('error', err);
  } else {
    console.log(data);
  }
});
