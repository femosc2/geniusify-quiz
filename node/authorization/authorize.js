var axios = require("axios")

var client_id = '7040414f8abb424db8e774d24c0aa737'; // Your client id
var client_secret = 'f93f06559dae407cb4c3c378827dd146'; // Your secret

let spotifyToken = ""

const getToken = () => {
    axios({
    url: "https://accounts.spotify.com/api/token",
    method: "post",
    params: {
        grant_type: "client_credentials"
    },
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/x-www-form-urlencoded"
    },
    auth: {
        username: client_id,
        password: client_secret
    }
}).then(function (response) {
    spotifyToken = response.data.access_token
    console.log(spotifyToken)
    return spotifyToken
}).catch(function (error) {
});
}

getToken()

module.exports = {
    getToken
}