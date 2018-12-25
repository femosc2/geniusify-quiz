const express = require("express");
const bodyParser = require("body-parser");

const feedRoutes = require("./routes/feed")

const app = express();

app.use(bodyParser.json());
app.use('/', feedRoutes);


app.listen(9099);