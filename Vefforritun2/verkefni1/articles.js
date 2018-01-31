/* útfæra greina virkni */
/**
 * 
 * 
 */

const MarkdownIt = require('markdown-it');
const md = new MarkdownIt();
module.exports = function(app,fs,frontmatter){

    /**
     * lesa skrá
     */
    var artOne = fs.readFileSync('articles/batman-ipsum.md', 'utf8');
    var artTwo = fs.readFileSync('articles/corporate-ipsum.md', 'utf-8');
    /**
     * Parse gögninn í array
     */
    var artOne = frontmatter(artOne);
    var artTwo = frontmatter(artTwo);

    console.log(md.render(artTwo['content']));

    /**
     * Routs
     */
    app.get('/', (req, res) => {
    res.render('index',{
            title: "Verkefni 1",
            artOne: artOne["data"]["title"],
            artOneDate: artOne["data"]["date"].substr(0,15),
            slugBat: artOne["data"]["slug"]
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

    app.get('/'+artTwo['data']['slug'], (req, res) => {
        res.render(artTwo['data']['slug'],{
            title: artTwo['data']['title'],
            content: '<div class="articleText">'+md.render(artTwo['content'])
        });
    });


    app.get('/'+artOne['data']['slug'], (req, res) => {
        res.render(artOne['data']['slug'],{
          title: artOne['data']['title'],
          content: '<div class="articleText">'+md.render(artOne['content'])
        });
      });
}



  