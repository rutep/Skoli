const express = require('express');
const app = express();
app.use(express.json());

/**
 * Uppfærir færslu að öllu leiti
 */
const data = [
  { id: 1, title: 'Foo' },
  { id: 2, title: 'Bar' },
];

app.get('/', (req, res) => {
  res.json(data);
});

app.put('/:id', (req, res) => {
  const { id } = req.params;
  const { title = '' } = req.body;

  /**
   * Ath title
   */
  if (title.length === 0) {
    return res.status(400).json({
      field: 'title',
      error: 'Title must be a non-empty string',
    });
  }

  /**
   * Ath id
   */
  const item = data.find(i => i.id === parseInt(id, 10));

  /**
   * 200 ok
   */
  if (item) {
    item.title = title;
    return res.status(200).json(item);
  }

  res.status(404).json({ error: 'Not found' });
});

const port = 4000;
app.listen(port, () => {
  console.info(`Server running at http://localhost:${port}/`);
});

/*
> curl -X PUT -H "Content-Type: application/json" -d '{"title": "asdf"}' http://localhost:3000/4
{"error":"Not found"}

> curl -X PUT -H "Content-Type: application/json" -d '{"title": "asdf"}' http://localhost:3000/2
{"id":2,"title":"asdf"}
*/
