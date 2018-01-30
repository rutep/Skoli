const express = require('express');
const frontmatter = require('frontmatter');
const fs = require('fs');

const app = express();
const hostname = '127.0.0.1';
const port = 3000;

/**
 *  Virkjar ejs sem view engine
 */
app.set('view engine', 'ejs');


/**
 * Lætur mann fá aðgang að skrám í public skráarsafninu
 */
app.use(express.static('public'));

/**
 * lesa skrá
 */
var artOne = fs.readFileSync('articles/batman-ipsum.md', 'utf8');

/**
 * Parse gögninn í array
 */
var artOne = frontmatter(artOne);


/**
 * Routs
 */
app.get('/', (req, res) => {
  res.render('index',{
    title: "Verkefni 1",
    artOne: artOne["data"]["title"],
    artOneDate: artOne["data"]["date"],
  });
});


app.get('/batman', (req, res) => {
  res.render('batman',{
    title: "Verkefni 1"
  });
});

app.get('/corporate', (req, res) => {
  res.render('corporate',{
    title: "Verkefni 1"
  });
});

app.get('/deloren', (req, res) => {
  res.render('deloren',{
    title: "Verkefni 1"
  });
});

app.get('/lorem', (req, res) => {
  res.render('lorem',{
    title: "Verkefni 1"
  });
});


/**
 * Býr til local þjón með ip töluna 3000
 */
app.listen(port, hostname, () => {
  console.info(`Server running at http://${hostname}:${port}/`);
});