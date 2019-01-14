<h1 align="center">:musical_score: Genisuify-Quiz :musical_score:</h1>

## 1. Medverkande
<ul>
  <li> <a href="https://github.com/addicool">Adam Nilsson </a></li>
  <li> <a href="https://github.com/femosc2 ">Felix Morau </a></li>
  <li> <a href="https://github.com/mlindfors2 ">Mikael Lindfors </a></li>
  <li> <a href="https://github.com/VictorPersson ">Victor Persson </a></li>
</ul>

## 2. Krav
<ul>
  <li> <a href="https://nodejs.org/en/download/">Node</a></li>
  <li> <a href="https://www.npmjs.com/get-npm">npm</a></li>
</ul>
  
  
## 3. Installation
<ol>
  <li> Clone / ladda ner repo </li> 
  <li> Öppna terminal/CMD och navigera till mappen geniusify-quiz
  <li> skriv <code> npm install </code> </li>
  <li> skriv <code> $ npm install -g vue </code> </li>
  <li> Navigera till mappen client - geniusify-quiz/client </li> 
  <li> Ladda ner Java SDK och IntelliJ och installera dessa. </li>
  <li> Öppna projektet i IntelliJ som ett mavenprojekt. </li>
  <li> IntelliJ kommer att ladda ner frameworks och plugins som behövs. </li>
</ol>

## 4. Körning
### 4.1 Front-end
<ol>
  <li> Navigera till mappen client - geniusify-quiz/client </li> 
  <li> skriv <code> $ npm run serve </code> </li>
</ol>

### 4.2 Back-end
<ol>
  <li> Starta backend i intelliJ med hjälp av klassen Main. </li> 
  <li> Servern kommer automatiskt att börja lyssna på port 9022. </li>
  <li> Starta frontend genom att cd:a in i /geniusify-quiz/client och sen skriva npm run serve. </li>
  <li> <a http://localhost:8080/"> http://localhost:8080/ </a></li>
  <li> Påbörja spelet genom att skriva ditt namn och sedan klicka på SUBMIT. </li>
</ol>

## 5 API Dokumentation:

### 5.1 Genres
Endpoints för att få fram en slumpad låt från en genre.
Base URL: http://localhost

#### JavaScript kodexempel där user-agenten Axios används.
```javascript
const axios = require("axios");
axios
       .get(“http://localhost:9022/genre/{{ genre name here }}“)
       .then(response => {
         const resultArray = [];
         for (let key in response) {
           resultArray.push(response[key]);
         }
         console.log(resultArray[0])

```
#### Exempel på svar i json format från backend när ny låt hämtas.
```javascript
{
"name": "All I Want for Christmas Is You",
"artist": "Michael Bublé",
"imageUrl": "https://i.scdn.co/image/1b086a06158e162d91d209e5e4cd569b3f059371",
"lyrics": "I don't _____ a lot for Christmas \n_____ is just one thing I need \nI _____ care about the presents \nUnderneath the Christmas _____ \nI just want you for _____ own \n",
"words": ["want","There","don't","tree","my"]
}
```
### 5.2 Rock
METHOD: Get. <br>
Endpoint: /genre/rock <br>
Returns: JSON-Objekt med artist, låtnamn, album, albumomslag, låttext och borttagna ord. <br>
Statuscode 500: Backenden är offline. <br>

### 5.3 Rap
METHOD: Get. <br>
Endpoint: /genre/rap <br>
Returns: JSON-Objekt med artist, låtnamn, album, albumomslag, låttext och borttagna ord. <br>
Statuscode 500: Backenden är offline. <br>

### 5.4 Christmas
METHOD: Get. <br>
Endpoint: /genre/christmas <br>
Returns: JSON-Objekt med artist, låtnamn, album, albumomslag, låttext och borttagna ord. <br>
Statuscode 500: Backenden är offline. <br>

### 5.5 Pop
METHOD: Get. <br>
Endpoint: /genre/pop <br>
Returns: JSON-Objekt med artist, låtnamn, album, albumomslag, låttext och borttagna ord. <br>
Statuscode 500: Backenden är offline. <br>

