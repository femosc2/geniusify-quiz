<template>
  <div>
    <game-countdown
      v-if="infoPage && !gameOver && isGenreChoosen"
      :song="song"
      :artist="artist"
      :image="image"
      :infoPage="infoPage"
      @countdownDone="countdownDone"
    />
    <game-info
      :isGenreChoosen="isGenreChoosen"
      :playlist="playlist"
      :song="song"
      :artist="artist"
      :image="image"
      :lyrics="lyrics"
      :name="name"
      v-if="!gameOver && !infoPage"
    />
    <button
      v-if="isGenreChoosen && !infoPage && !gameOver"
      @click="translateLyrics"
      class="translateBtn"
    >Translate to Swedish</button>
    <game-input
      v-if="isGenreChoosen && !infoPage && !gameOver"
      :words="words"
      :name="name"
      :score="score"
      :correctWords="correctWords"
      @correctGuess="addScore"
    />
    <game-progress v-if="isGenreChoosen && !gameOver"/>
    <game-game-over v-if="gameOver && isGenreChoosen" :score="score"/>
    <game-exit v-if="isGenreChoosen && !gameOver" />
  </div>
</template>

<script>
const axios = require("axios");

import GameInfo from "./GameInfo.vue";
import GameInput from "./GameInput.vue";
import GameProgress from "./GameProgress.vue";
import GameCountdown from "./GameCountdown.vue";
import GameGameOver from "./GameGameOver.vue";
import GameExit from "./GameExit.vue";

export default {
  data: function() {
    return {
      playlist: "",
      song: "",
      artist: "",
      image: "",
      lyrics: "",
      words: [],
      gameOver: false,
      yandexApiKey:
        "trnsl.1.1.20190108T134850Z.6e32519bf1b85f9c.9f5af2e7ab08f991dcc9779d57dd9994ace9305d",
      correctWords: [],
      infoPage: true,
      score: 0
    };
  },
  components: {
    GameInfo,
    GameInput,
    GameProgress,
    GameCountdown,
    GameGameOver,
    GameExit
  },
  methods: {
    getSongs() {
      // Function for retrieving the song, artist, image and lyrics from the back-end
      if (this.isGenreChoosen === "Rock") {
        this.playlist = "http://10.2.15.173:9022/genre/rock";
      } else if (this.isGenreChoosen === "Christmas") {
        this.playlist = "http://10.2.15.173:9022/genre/christmas";
      } else if (this.isGenreChoosen === "Pop") {
        this.playlist = "http://10.2.15.173:9022/genre/pop";
      } else if (this.isGenreChoosen === "Rap") {
        this.playlist = "http://10.2.15.173:9022/genre/rap";
      }
      axios
        .get(this.playlist) //get from the chosen playlist
        .then(response => {
          const resultArray = [];
          for (let key in response) {
            //organizes the json file
            resultArray.push(response[key]);
          }
          console.log(resultArray[0].words)
          this.playlist = resultArray;
          this.song = this.playlist[0].name; //Playlist[0] = Object which stores information about song
          this.artist = this.playlist[0].artist;
          this.image = this.playlist[0].imageUrl;
          this.lyrics = this.playlist[0].lyrics;
          this.words = this.playlist[0].words;
        });
    },
    setSong() {
      //function to automaticlly update the current songs to the front-end
      this.getSongs(); // Chooses a song as soon as it is clicked
      setInterval(() => {
        //Updates every 5 second to see what song is currently selected
        this.correctWords = [];
        this.getSongs();
        this.infoPage = true;
      }, 28 * 1000);
    },
    translateLyrics() {
      //Method for translating lyrics
      axios
        .get(
          "https://cors-anywhere.herokuapp.com/https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-sv&text=" +
            this.lyrics +
            "&key=" +
            this.yandexApiKey +
            "&format=plain"
        )
        .then(response => {
          const resultArray = [];
          for (let key in response) {
            // Organizes the JSON file.
            resultArray.push(response[key]);
          }
          let array = resultArray[5].response.split("");
          let splittedArray = array.splice(0, 36);
          let forgedArray = array.join();
          let fixedArray = forgedArray
            .substring(0, forgedArray.length - 5)
            .replace(/,/g, "");
          this.lyrics = fixedArray + "\n";
        })
        .catch(error => {
          console.log(error);
        });
    },
    isGameOver(boolean) {
      //Recieves data which indicates if game is over.
      this.gameOver = boolean;
    },
    countdownDone(bool) {
      this.infoPage = false;
    },
    addScore() {
      // Adds +1 to if user correctly guesses a lyric.
      this.score++;
    },
    game() {
      // Runs the game. The game is 200 seconds long and when the game is over it posts the result to the database.
      setTimeout(() => {
        let currentScore = {
          name: this.name,
          score: this.score
        };
        axios
          .post(
            "https://geniusify-quiz.firebaseio.com/Players.json",
            currentScore
          )
          .then(response => {
            console.log(response.data);
          });
        this.gameOver = true;
      }, 300 * 1000);
    }
  },
  props: ["isGenreChoosen", "name"],
  created() {
    //When the component is created run these methods.
    this.setSong();
    this.game();
  }
};
</script>

<style>
.translateBtn {
  font-size: 0.8em;
  padding: 0.8em;
}

p {
  color: white;
}
</style>