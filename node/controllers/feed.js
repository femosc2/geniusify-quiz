const Token =  require("../authorization/authorize")
const axios = require("axios")

// token = Token.getToken()

let popsong = ""
let popartist = ""
let popimage = ""


let rapsong = ""
let rapartist = ""
let rapimage = ""


let rocksong = ""
let rockartist = ""
let rockimage = ""


let christmassong = ""
let christmasartist = ""
let christmasimage = ""


const getPlaylist = () => {
    axios.get("https://api.spotify.com/v1/playlists/3ZgmfR6lsnCwdffZUan8EA/tracks", {headers : {'Authorization' : "Bearer " + "BQALCxzb9SLVdFAJ2XzS4f3RqRZ7XkNbotlBi0bvKKBF7jRuREl0NE1iSHA8TcBWD6dHFliB6UpXeAgrxPU"}})
      .then((response) => {
          const resultArray = [];
            for (let key in response) {
            resultArray.push(response[key]);
            }
            let playlist = resultArray;
            let randomNumber = Math.floor(Math.random() * 101)
            song = playlist[5].items[randomNumber].track.name
            artist = playlist[5].items[randomNumber].track.artists[0].name
            image = playlist[5].items[randomNumber].track.album.images[1].url
            console.log(song, artist, image)
            return playlist
            // console.log(playlist)
      })
          .catch((error) => {
              console.log(error);
      })
}


exports.getPop = (req, res, next) => {
    res.status(200).json(
        {
            Song: {id: new Date().toISOString(), song: song, artist: artist, image: image}
        });
};

// exports.getHipHop = (req, res, next) => {
//     res.status(200).json(
//         {
//             Song: {id: new Date().toISOString(), song: song, artist: artist, image: image}
//         });
// };

// exports.getRock = (req, res, next) => {
//     res.status(200).json(
//         {
//             Song: {id: new Date().toISOString(), song: song, artist: artist, image: image}
//         });
// };

// exports.getChristmas = (req, res, next) => {
//     res.status(200).json(
//         {
//             Song: {id: new Date().toISOString(), song: song, artist: artist, image: image}
//         });
// };

setInterval(function(){
    getPlaylist("popsong", "popartist", "popimage")
}, 5*1000)