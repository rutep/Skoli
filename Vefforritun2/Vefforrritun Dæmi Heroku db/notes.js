const {Client} = require('pg');
const express = require('express');
const router  = express.Router();
const xss = require('xss');
const connectionString = 
process.env.DATABASE_URL || 'postgres://@localhost:5000/notes';

function ensureLoggedIn(req, res, next) {
    if (req.isAuthenticated()) {
      return next();
    }
  
    return res.redirect('/login');
  }
  
/**
 * , ensureLoggedIn
 */
router.get('/admin', (req, res) => {
    res.render(`notes`,{});
  });

async function addNote(note) {
  const client = new Client({connectionString })
  await client.connect();
  // await client.query('INSERT INTO notes (note) VALUES(1,$1)',[note]);
  // await client.end();
}  

router.post('/addnote',async (req,res) =>{
  const {note} = req.body;
  await addNote(xss(note));

  res.redirect('/admin');
});

module.exports = router;