const express = require("express");
const bodyParser = require("body-parser");

const feedRoutes = require("./routes/genre")

const app = express();

app.use(function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});


app.use(bodyParser.json());
app.use('/', feedRoutes);
console.log("app is listening on 9021")
app.listen(9021);