<template>
  <div id="app">
    <!-- <div v-if="isNameChoosen === null">
      <input type="text" v-model="playerName">
    </div> -->
    <div v-if="isGenreChoosen === null">
      <Genre @genreSelected="genreChoosen"/>
    </div>
    <div v-else>
      <p @click="authorizeSpotify"> What song is it?</p>
      <p> {{ artist }}</p>
      <p> {{ song }}</p>
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
      image: ""
    };
  },
  methods: {
    // Method GET's playlist
    loadPlaylists() {
      this.$http.get("");
    },
    //
    genreChoosen(genreSelected) {
      this.isGenreChoosen = genreSelected
      console.log()
    },
    authorizeSpotify() {
      if (this.isGenreChoosen === "Rock") {
        this.playlist = "http://localhost:9020/rock"
      } else if (this.isGenreChoosen === "Christmas") {
        this.playlist = "http://localhost:9020/christmas"
      } else if (this.isGenreChoosen === "Pop") {
        this.playlist = "http://localhost:9020/pop"
      } else if (this.isGenreChoosen === "Rap") {
        this.playlist = "http://localhost:9020/rap"
      }
      axios.get(this.playlist)
        .then(response => {
          const resultArray = [];
          for (let key in response) {
            resultArray.push(response[key]);
          }
          this.playlist = resultArray;
          console.log(resultArray)
          this.song = this.playlist[0].Song.song
          this.artist = this.playlist[0].Song.artist
          this.image = this.playlist[0].Song.image //test
        })

    },
    randomNumber() {
      let randomNumber = Math.floor(Math.random() * 101)
      return randomNumber
    }
  },
  created() {
    this.authorizeSpotify();
  }
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
