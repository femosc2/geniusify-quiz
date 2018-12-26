const axios = require("axios") // user-agent

let apiSeedsKey = "gbt2aH1lxH46xNKUp7Au3qpw9YgLeCw9HedMYC3bFeFVhF6x19C97Dk8AXwaGZMT" //TODO GITIGNORE
let popLyrics = ""

let popSong = ""
let popArtist = ""

const getPopSong = () => {
axios.get("http://localhost:9021/pop", {
    })
    .then((response) => {
        const resultArray = [];
        for (let key in response) { // Organizes the JSON file.
            resultArray.push(response[key]);
        }
        popSong = resultArray[5].Song.song
        popArtist = resultArray[5].Song.artist
    })
    .catch((error) => {
        console.log("error");
    })
}

const getPopLyrics = () => {
    //Function for retrieving the Pop playlist

    axios.get("https://orion.apiseeds.com/api/music/lyric/" + popArtist + "/" + popSong + "?apikey=" + apiSeedsKey, {
        })
        .then((response) => {
            const resultArray = [];
            for (let key in response) { // Organizes the JSON file.
                resultArray.push(response[key]);
            }
            popLyrics = resultArray[5].result.track.text
            console.log(popLyrics)
        })
        .catch((error) => {
            console.log(error);
        })
    }

exports.getPopLyrics = (req, res, next) => {
    // Creates a new API endpoint for the Rapsong
    res.status(200).json({
        Song: {
            lyrics: lyrics
        }
    });
};

// setInterval(() => {
//     getPopLyrics()
// }, 10*1000)
// setInterval(() => {
//     getPopSong()
//     getPopLyrics()
// }, 1*1000)