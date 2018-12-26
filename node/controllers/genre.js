const axios = require("axios") // user-agent

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

let token = ""

const getToken = () => {
    //Function for retreiving the token to Spotify
    axios.get("http://localhost:9021/token") // Make sure to update the port to the one specified in app.js
        .then((response) => {
            token = response.data.Token.id;
        })
        .catch((error) => {
            console.log(error);
        })
}

const getPopPlaylist = () => {
    //Function for retrieving the Pop playlist
    axios.get("https://api.spotify.com/v1/playlists/3ZgmfR6lsnCwdffZUan8EA/tracks", {
            headers: {
                'Authorization': "Bearer " + token
            }
        })
        .then((response) => {
            const resultArray = [];
            for (let key in response) { // Organizes the JSON file.
                resultArray.push(response[key]);
            }
            let playlist = resultArray;
            let randomNumber = Math.floor(Math.random() * 101) // randomly choose a number to select a song
            song = playlist[5].items[randomNumber].track.name //Playlist[5] = object which holds information about the song
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
    axios.get("https://api.spotify.com/v1/playlists/21sgjLGbnEgNMTpjnaO2b6/tracks", {
            headers: {
                'Authorization': "Bearer " + token
            }
        })
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
    axios.get("https://api.spotify.com/v1/playlists/3qu74M0PqlkSV76f98aqTd/tracks", {
            headers: {
                'Authorization': "Bearer " + token
            }
        })
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
    axios.get("https://api.spotify.com/v1/playlists/5OP7itTh52BMfZS1DJrdlv/tracks", {
            headers: {
                'Authorization': "Bearer " + token
            }
        })
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
    // Creates a new API endpoint for the Popsong
    res.status(200).json({
        Song: {
            id: new Date().toISOString(),
            song: song,
            artist: artist,
            image: image,
            lyrics: "The lyrics of the pop song"
        }
    });
};

exports.getRap = (req, res, next) => {
    // Creates a new API endpoint for the Rapsong
    res.status(200).json({
        Song: {
            id: new Date().toISOString(),
            song: rapSong,
            artist: rapArtist,
            image: rapImage,
            lyrics: "The lyrics of the rap song"
        }
    });
};

exports.getRock = (req, res, next) => {
    // Creates a new API endpoint for the Rocksong
    res.status(200).json({
        Song: {
            id: new Date().toISOString(),
            song: rockSong,
            artist: rockArtist,
            image: rockImage,
            lyrics: "The lyrics of the rock song"
        }
    });
};

exports.getChristmas = (req, res, next) => {
    // Creates a new API endpoint for the Christmassong
    res.status(200).json({
        Song: {
            id: new Date().toISOString(),
            song: christmasSong,
            artist: christmasArtist,
            image: christmasImage,
            lyrics: "The lyrics of the christmas song"
        }
    });
};

setInterval(function () {
    // Function for retrieving a new token every second,
    // needs to be faster than the timeout and interval functions below
    // otherwise playlist functions wont be able to retrieve a song.
    getToken()
}, 1 * 1000)

setTimeout(function () {
    // For the start function in the front-end,
    // needs to be slower than the getToken interval otherwise
    // wont be able to retrieve song
    getPopPlaylist()
    getRapPlaylist()
    getRockPlaylist()
    getChristmasPlaylist()
}, 2 * 1000)

setInterval(function () {
    // Every 15 second the songs get updated
    getPopPlaylist()
    getRapPlaylist()
    getRockPlaylist()
    getChristmasPlaylist()
}, 15 * 1000)