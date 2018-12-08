# Efni

## Vision and scope
### Buisness Requirements
* Background "(What triggered this ?)"
* Buisness opportunity "(why does it look like we can be successful)
* Business objectives ("Wich benefits do we expect out of this?")
* Success metrics ("How can we tell wether are successful?")
* Vision statement ("What will the product accomplish for whom?")

### Scope and Limitations
* Major features ("What key things should the product be capable of?")
* Scope of inital release ("What should be rolled out late?")
* Limitation ("What are we not going to do")


## Basic Controller
### Dæmi um basic controller
```
@controller
public class HomeController {
    @RequestMapping(value = "/" method = RequestMethod.Get)
    home() {
        return "Index";
    }
}
```

## Java based application
### Request scope 

### Session scope
    Henntar vel til þess að geyma notenda
### Application scope
    Henntar vel til þess að geyma gögn fyrir alla notendur
### Database

## Resbonsibilytis
### Controller 
    Grípur HTTP request parameter og framkvæmir einhverja skilgreinda logic fyrir þann request. Eftir að hafa framkvæmt logic þá er það að ákvaða view.
### Service
    Heilinn á bakvið tjöldinn. Hér á sér stað útfærsla á algorithm. Einnig Gagnar umbreiting.
### Repository
    Sér um að einindi eru geymd samkvæmt uppskrift. Sér um tengingu við gagnagrunn. Framkvæmir gagnagrunns aðgerð. Breytir query result í entitys.
## Http fyrir gögn - sjá próf

## Domain Model
    Skilja óðal verkefnisins

## Design Model
    Til þess að geta sýnt hvernig lausninn okkar virkar

## UML 
### Activity diagram
### Class diagram
#### States and psudo states

## Design
### Sequnatial fit for large project

## Soft wate archtecture
### Attribute and issuse
### Quality implementation

## Jsp expression language


#### Orð
* Inception = establishing or starting point of a activity
* Criteria  = 
* 