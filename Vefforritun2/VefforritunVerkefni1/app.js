/**
 * frontmatter notað til þess að ná gögn úr grein sem er svo geimt í js obj
 */
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
app.use(express.static('articles'));

/**
 * Býr til local þjón með ip töluna 3000
 */
app.listen(port, hostname, () => {
  console.info(`Server running at http://${hostname}:${port}/`);
});

/**
 * export til þess að nota app,fs og frontmatter í articles
 */
require('./articles')(app,fs,frontmatter);