const express = require("express");

const feedController = require("../controllers/feed")

const router = express.Router();

router.get("/pop", feedController.getPop)
router.get("/rap", feedController.getRap)
router.get("/rock", feedController.getRock)
router.get("/christmas", feedController.getChristmas)

module.exports = router;