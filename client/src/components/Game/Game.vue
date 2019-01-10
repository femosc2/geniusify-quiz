<template>
  <div>
    <game-info
      :isGenreChoosen="isGenreChoosen"
      :playlist="playlist"
      :song="song"
      :artist="artist"
      :image="image"
      :lyrics="lyrics"
      :name="name"
      v-if="!gameOver"
    />
    <button class="translateBtn" v-if="isGenreChoosen" @click="translateLyrics">Translate to Swedish</button>
    <game-input v-if="isGenreChoosen" :words="words" :name="name" :correctWords="correctWords" @gameOver="isGameOver(true)"/>
    <game-progress v-if="isGenreChoosen"/>
  </div>
</template>

<script>
const axios = require("axios");

import GameInfo from "./GameInfo.vue";
import GameInput from "./GameInput.vue";
import GameProgress from "./GameProgress.vue";

export default {
  data: function() {
    return {
      playlist: "",
      song: "",
      artist: "",
      image: "",
      lyrics: "",
      words: ["test1", "test2", "test3"],
      gameOver: false,
      yandexApiKey: "trnsl.1.1.20190108T134850Z.6e32519bf1b85f9c.9f5af2e7ab08f991dcc9779d57dd9994ace9305d",
      correctWords: []
    };
  },
  components: {
    GameInfo,
    GameInput,
    GameProgress
  },
  methods: {
      
    getSongs() {
      // Function for retrieving the song, artist, image and lyrics
      if (this.isGenreChoosen === "Rock") {
        this.playlist = "http://localhost:9022/rock";
      } else if (this.isGenreChoosen === "Christmas") {
        this.playlist = "http://localhost:9022/christmas";
      } else if (this.isGenreChoosen === "Pop") {
        this.playlist = "http://localhost:9022/pop";
      } else if (this.isGenreChoosen === "Rap") {
        this.playlist = "http://localhost:9022/rap";
      }
      axios
        .get(this.playlist) //get from the chosen playlist
        .then(response => {
          const resultArray = [];
          for (let key in response) {
            //organizes the json file
            resultArray.push(response[key]);
          }
          this.playlist = resultArray;
          this.song = this.playlist[0].Song.song; //Playlist[0] = Object which stores information about song
          this.artist = this.playlist[0].Song.artist;
          this.image = this.playlist[0].Song.image;
          this.lyrics = this.playlist[0].Song.lyrics
          this.words = ["test1", "test2", "test3"]
        });
    },
    setSong() {
      //function to automaticlly update the current songs to the front-end
      this.getSongs(); // Chooses a song as soon as it is clicked
      setInterval(() => {
        //Updates every 5 second to see what song is currently selected
        this.correctWords = []
        this.getSongs();
      }, 15 * 1000);
    },
    translateLyrics() {
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
          let fixedArray = forgedArray.substring(0, forgedArray.length - 5).replace(/,/g, '');
          this.lyrics = fixedArray + "\n"

        })
        .catch(error => {
          console.log(error);
        });
    },
    isGameOver(boolean) {
      this.gameOver = boolean
    }
  },
  props: ["isGenreChoosen", "name"],
  created() {
    this.setSong();
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