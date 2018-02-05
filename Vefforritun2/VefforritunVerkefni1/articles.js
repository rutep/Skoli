
/* útfæra greina virkni */
/**
 * MarkdownIt Notað til þess að breyta conent í grein yfir í html
 */
 
const MarkdownIt = require('markdown-it');
const md = new MarkdownIt();
const articles = ['articles/batman-ipsum.md','articles/corporate-ipsum.md',
'articles/deloren-ipsum.md','articles/lorem-ipsum.md'];
const encoding = 'utf8';
let errorRead;
 
module.exports = function(app,fs,frontmatter){

    /**
     * Notkun: var a = await read(art);
     * Fyrir:
     * @param {*string} art slóð á grein 
     * Eftir: Búið er að lesa úr skrá
     */
    async function read(art){
        return new Promise((resolve,reject) => {
            resolve(fs.readFileSync(art, encoding));
            reject(true);
        });
    }

    /**
     * Notkun var a = await read(art)
     * Fyrir:
     * @param {*string} data 
     * Eftir: Búið er að flokka gögn í hlut
     */
    async function parseArtObject(data){
        return new Promise((resolve,reject) => {
            resolve(frontmatter(data));
        });
    }


    async function main(){
        
        let artOne, artTwo, artThree, artFour;
        /**
         * Gagnar lestur
         */
        try{
            try{
                artOne = await parseArtObject(await read(articles[0]));
                artTwo = await parseArtObject(await read(articles[1]));
                artThree = await parseArtObject(await read(articles[2]));
                artFour = await parseArtObject(await read(articles[3]));
            } catch (e) {
                errorRead = true;
            }
        } catch(e){
            errorRead = true;
        }
           
        /**
         * Routs
         */ 
        app.get('/', (req, res) => {
            if(errorRead != true){
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
            } else {
                res.render('Onei.ejs', {
                    title: 'Vill kom upp'
                });
            }
            
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
    main();
}



  