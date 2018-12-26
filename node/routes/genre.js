const express = require("express");

const feedController = require("../controllers/genre")
const tokenController = require("../controllers/authorize")
const lyricController = require("../controllers/lyrics")

const router = express.Router();

router.get("/pop", feedController.getPop, lyricController.getPopLyrics) // Route to the Pop song
router.get("/rap", feedController.getRap) //Route to the Rap song
router.get("/rock", feedController.getRock) // Route to the Rock song
router.get("/christmas", feedController.getChristmas) // Route to the Christmas song
router.get("/token", tokenController.getToken) // Route to the token

module.exports = router;