/* útfæra greina virkni */
/**
 * MarkdownIt Notað til þess að breyta conent í grein yfir í html
 */

const MarkdownIt = require('markdown-it');
const md = new MarkdownIt();
module.exports = function(app,fs,frontmatter){

    
    
    /**
     * lesa skrá
     */
    var artOne = fs.readFileSync('articles/batman-ipsum.md', 'utf8');
    var artTwo = fs.readFileSync('articles/corporate-ipsum.md', 'utf-8');
    var artThree = fs.readFileSync('articles/deloren-ipsum.md', 'utf-8');
    var artFour = fs.readFileSync('articles/lorem-ipsum.md', 'utf-8');
    
    /**
     * Parse gögninn í array
     */
    var artOne = frontmatter(artOne);
    var artTwo = frontmatter(artTwo);
    var artThree = frontmatter(artThree);
    var artFour = frontmatter(artFour);
    
    /**
     * Routs
     */
    app.get('/', (req, res) => {
    res.render('index',{
            title: "Verkefni 1",
            artOne: artOne["data"]["title"],
            artOneDate: artOne["data"]["date"].substr(0,15),
            slugBat: artOne["data"]["slug"],
        
            artTwo: ['data']['title'],
            artTwoDate: artTwo['data']['date'].substr(0,15),
            slugCorp: artTwo['data']['slug'],
            imgCorp:artTwo['data']['image'],
        
            artThree: artThree['data']['title'],
            artThreeDate: artThree['data']['date'].substr(0,15),
            slugFlux: artThree['data']['slug'],

            artFour: artFour['data']['title'],
            artFourDate: artFour['data']['date'].substr(0,15),
            slugLor: artFour['data']['slug'],
            imgLor: artFour['data']['image']
        

        });
    });

    app.get('/'+artFour['data']['slug'], (req, res) => {
        res.render(artFour['data']['slug'],{
            title: artFour['data']['title'],
            content: '<div class="articleText"> <div class="articleTextBox">'+md.render(artFour['content'])
        });
    });

    app.get('/'+artThree['data']['slug'], (req, res) => {
        res.render(artThree['data']['slug'],{
            title: artThree['data']['title'],
            content: '<div class="articleText"> <div class="articleTextBox">'+md.render(artThree['content'])
        });
    });

    app.get('/'+artTwo['data']['slug'], (req, res) => {
        res.render(artTwo['data']['slug'],{
            title: artTwo['data']['title'],
            content: '<div class="articleText"> <div class="articleTextBox">'+md.render(artTwo['content'])
        });
    });


    app.get('/'+artOne['data']['slug'], (req, res) => {
        res.render(artOne['data']['slug'],{
          title: artOne['data']['title'],
          content: '<div class="articleText"> <div class="articleTextBox">'+md.render(artOne['content'])
        });
      });

    /**
     * Meðhöndla villu 404
     */
    app.use(function(req, res, next) {
        if(req.accepts('html') && res.status(404)) {
            res.render('404.ejs', {
                title: 'Fannst ekki'
            });
        }
    });
}



  