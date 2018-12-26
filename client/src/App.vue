<template>
  <div id="app">
    <!-- <div v-if="isNameChoosen === null">
      <input type="text" v-model="playerName">
    </div> -->
    <div v-if="isGenreChoosen === null">
      <Genre @genreSelected="genreChoosen" @click="setSong"/>
    </div>
    <div v-else>
      <p @click="setSong"> {{ game }}</p>
      <p> {{ artist }}</p>
      <p> {{ song }}</p>
      <p> {{ lyrics }} </p>
      <img :src="image" alt="">
    </div>
  </div>
</template>

<script>
import Genre from "./components/Genre/Genres.vue";
const axios = require("axios")

export default {
  name: "app",
  components: {
    Genre
  },
  data: function() {
    return {
      playerName: null,
      isGenreChoosen: null,
      playlist: "",
      song: "",
      artist: "",
      image: "",
      game: "Start!",
      lyrics: ""
    };
  },
  methods: {
    genreChoosen(genreSelected) {
      this.isGenreChoosen = genreSelected
      console.log()
    },
    getSongs() {
      if (this.isGenreChoosen === "Rock") {
        this.playlist = "http://localhost:9021/rock"
      } else if (this.isGenreChoosen === "Christmas") {
        this.playlist = "http://localhost:9021/christmas"
      } else if (this.isGenreChoosen === "Pop") {
        this.playlist = "http://localhost:9021/pop"
      } else if (this.isGenreChoosen === "Rap") {
        this.playlist = "http://localhost:9021/rap"
      }
      axios.get(this.playlist)
        .then(response => {
          const resultArray = [];
          for (let key in response) {
            resultArray.push(response[key]);
          }
          this.playlist = resultArray;
          this.song = this.playlist[0].Song.song
          this.artist = this.playlist[0].Song.artist
          this.image = this.playlist[0].Song.image //test
          this.lyrics = this.playlist[0].Song.lyrics
        })

    },
    setSong() {
      this.getSongs()
      this.game = "What lyrics are missing?"
      setInterval(() => {
        this.getSongs()
      }, 5*1000)
    }
  },
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 60px;
  background: linear-gradient(to right, #360033,  #0b8793);
}
</style>
