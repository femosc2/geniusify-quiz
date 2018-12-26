const axios = require("axios")

// token = Token.getToken()

let song = ""
let artist = ""
let image = ""

let rapSong = ""
let rapArtist = ""
let rapImage = ""

let rockSong = ""
let rockArtist = ""
let rockImage = ""

let christmasSong = ""
let christmasArtist = ""
let christmasImage = ""

let token = "asd"

const getToken = () => {
    axios.get("http://localhost:9020/token")
      .then((response) => {
          token = response.data.Token.id;
      })
          .catch((error) => {
              console.log(error);
      })
}

const getPopPlaylist = () => {
    axios.get("https://api.spotify.com/v1/playlists/3ZgmfR6lsnCwdffZUan8EA/tracks", {headers : {'Authorization' : "Bearer " + token}})
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
            console.log("Pop song updated!")
            return playlist
            // console.log(playlist)
      })
          .catch((error) => {
              console.log("Access token not yet updated, please wait 5 seconds.");
      })
}

const getRapPlaylist = () => {
    axios.get("https://api.spotify.com/v1/playlists/21sgjLGbnEgNMTpjnaO2b6/tracks", {headers : {'Authorization' : "Bearer " + token}})
      .then((response) => {
          const resultArray = [];
            for (let key in response) {
            resultArray.push(response[key]);
            }
            let playlist = resultArray;
            let randomNumber = Math.floor(Math.random() * 101)
            rapSong = playlist[5].items[randomNumber].track.name
            rapArtist = playlist[5].items[randomNumber].track.artists[0].name
            rapImage = playlist[5].items[randomNumber].track.album.images[1].url
            console.log("Rap song updated!")
            return playlist
            // console.log(playlist)
      })
          .catch((error) => {
              console.log("Access token not yet updated, please wait 5 seconds.");
      })
}

const getRockPlaylist = () => {
    axios.get("https://api.spotify.com/v1/playlists/3qu74M0PqlkSV76f98aqTd/tracks", {headers : {'Authorization' : "Bearer " + token}})
      .then((response) => {
          const resultArray = [];
            for (let key in response) {
            resultArray.push(response[key]);
            }
            let playlist = resultArray;
            let randomNumber = Math.floor(Math.random() * 101)
            rockSong = playlist[5].items[randomNumber].track.name
            rockArtist = playlist[5].items[randomNumber].track.artists[0].name
            rockImage = playlist[5].items[randomNumber].track.album.images[1].url
            console.log("Rock song updated!")
            return playlist
            // console.log(playlist)
      })
          .catch((error) => {
              console.log("Access token not yet updated, please wait 5 seconds.");
      })
}

const getChristmasPlaylist = () => {
    axios.get("https://api.spotify.com/v1/playlists/5OP7itTh52BMfZS1DJrdlv/tracks", {headers : {'Authorization' : "Bearer " + token}})
      .then((response) => {
          const resultArray = [];
            for (let key in response) {
            resultArray.push(response[key]);
            }
            let playlist = resultArray;
            let randomNumber = Math.floor(Math.random() * 101)
            christmasSong = playlist[5].items[randomNumber].track.name
            christmasArtist = playlist[5].items[randomNumber].track.artists[0].name
            christmasImage = playlist[5].items[randomNumber].track.album.images[1].url
            console.log("Christmas song updated!")
            return playlist
            // console.log(playlist)
      })
          .catch((error) => {
              console.log("Access token not yet updated, please wait 5 seconds.");
      })
}

exports.getPop = (req, res, next) => {
    res.status(200).json(
        {
            Song: {id: new Date().toISOString(), song: song, artist: artist, image: image}
        });
};

exports.getRap = (req, res, next) => {
    res.status(200).json(
        {
            Song: {id: new Date().toISOString(), song: rapSong, artist: rapArtist, image: rapImage}
        });
};

exports.getRock = (req, res, next) => {
    res.status(200).json(
        {
            Song: {id: new Date().toISOString(), song: rockSong, artist: rockArtist, image: rockImage}
        });
};

exports.getChristmas = (req, res, next) => {
    res.status(200).json(
        {
            Song: {id: new Date().toISOString(), song: christmasSong, artist: christmasArtist, image: christmasImage}
        });
};

setInterval(function() {
    getToken()
}, 1*1000)

setInterval(function(){
    getPopPlaylist()
    getRapPlaylist()
    getRockPlaylist()
    getChristmasPlaylist()
}, 5*1000)