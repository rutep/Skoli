const { Client } = require('pg')
const connectionString = 
'postgres://peturd:1234@localhost/v2'; const client = new Client({ 
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
