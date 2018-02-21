const {Client} = require('pg');
const express = require('express');
const router  = express.Router();
const xss = require('xss');
const connectionString = 
process.env.DATABASE_URL || "postgresql://@localhost/notes";
// import module
const sqlite3 = require('sqlite3').verbose();
// Create db object


function ensureLoggedIn(req, res, next) {
    if (req.isAuthenticated()) {
      return next();
    }
    return res.redirect('/login');
  }
  
/**
 * , ensureLoggedIn
 */
router.get('/admin',async (req, res) => {
    const client = new sqlite3.Database(process.env.DATABASE_URL || './notes.db');
    await client.all('select * from Notes',function(err,rows){
    // let notes = fetchNotes();
    let notes = rows;
    
    res.render(`notes`, {notes});
    });
});


async function fetchNotes(){
  // const client = new sqlite3.Database(process.env.DATABASE_URL || './notes.db');
  // const client = new Client({connectionString })
  // await client.connect();
  // await client.query('insert into Notes (notes) values($1)',[note]);
}

async function addNote(note) {
  const client = new sqlite3.Database(process.env.DATABASE_URL || './notes.db');
  let stmt = await client.prepare('INSERT INTO Notes VALUES(?,?)');
  await stmt.run('2',note);
  
  // const client = new Client({connectionString })
  // await client.connect();
  // const result = await client.query('insert into notes (id,note) values(1,$1)', [note]);
  // await client.end();
}  

router.post('/addnote',async (req,res) =>{
  const {note} = req.body;
  console.log(note);
  
  await addNote(xss(note));
  res.redirect('/admin');
});

module.exports = router;
