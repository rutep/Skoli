const { Client } = require('pg')
const connectionString = 
'postgres://notandi:123@localhost/test'; const client = new Client({ 
connectionString });
client.connect(); 
client.query('SELECT * FROM test;', 

(err, res) => {
  if (err) {
    console.error(err);
  } else {
    console.log(res.rows);
  }
  client.end();
});
