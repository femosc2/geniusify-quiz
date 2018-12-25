var axios = require("axios")

var client_id = 'edb6f939e332455483f8e58066d110aa'; // Your client id
var client_secret = 'cadb6cd3c34f4863a85ba3b246857d2a'; // Your secret

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

}).catch(function (error) {
});
}

exports.getToken = (req, res, next) => {
    res.status(200).json(
        {
            Token: {id: spotifyToken }
        });
};

getToken()