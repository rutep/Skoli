

const express = require('express');
const frontmatter = require('frontmatter');
const fs = require('fs');

const app = express('article');


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
 * Býr til local þjón með ip töluna 3000
 */
app.listen(port, hostname, () => {
  console.info(`Server running at http://${hostname}:${port}/`);
});

require('./articles')(app,fs,frontmatter);
