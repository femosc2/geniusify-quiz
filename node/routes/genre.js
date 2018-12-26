const express = require("express");

const feedController = require("../controllers/genre")
const tokenController = require("../controllers/authorize")

const router = express.Router();

router.get("/pop", feedController.getPop)
router.get("/rap", feedController.getRap)
router.get("/rock", feedController.getRock)
router.get("/christmas", feedController.getChristmas)
router.get("/token", tokenController.getToken)

module.exports = router;