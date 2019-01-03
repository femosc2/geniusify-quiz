<template>
  <div id="app">
    <div v-if="isNameChoosen === null">
      <input type="text" v-model="playerName">
    </div>
    <div v-if="isGenreChoosen === null">
      <Genre @genreSelected="genreChoosen"/>
    </div>
    <div v-else>
      <p @click="setSong"> {{ game }}</p> <!-- Starts the game -->
      <p> {{ artist }}</p>
      <p> {{ song }}</p>
      <p> {{ lyrics }} </p>
      <img :src="image" alt="">
    </div>
  </div>
</template>

<script>
import Genre from "./components/Genre/Genres.vue";
import Leaderboard from "./components/Leaderboard/Leaderboard.vue"; 
const axios = require("axios")

export default {
  name: "app",
  components: {
    Genre,
    Leaderboard
  },
  data: function() {
    return {
      playerName: null,
      isNameChosen: null,
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
      // Function which recieves the current Genre
      this.isGenreChoosen = genreSelected
      console.log()
    },
    getSongs() {
      // Function for retrieving the song, artist, image and lyrics
      if (this.isGenreChoosen === "Rock") {
        this.playlist = "http://localhost:9022/rock"
      } else if (this.isGenreChoosen === "Christmas") {
        this.playlist = "http://localhost:9022/christmas"
      } else if (this.isGenreChoosen === "Pop") {
        this.playlist = "http://localhost:9022/pop"
      } else if (this.isGenreChoosen === "Rap") {
        this.playlist = "http://localhost:9022/rap"
      }
      axios.get(this.playlist) //get from the chosen playlist
        .then(response => {
          const resultArray = [];
          for (let key in response) { //organizes the json file
            resultArray.push(response[key]);
          }
          this.playlist = resultArray;
          this.song = this.playlist[0].Song.song //Playlist[0] = Object which stores information about song
          this.artist = this.playlist[0].Song.artist
          this.image = this.playlist[0].Song.image
          this.lyrics = this.playlist[0].Song.lyrics
        })

    },
    setSong() {
      //function to automaticlly update the current songs to the front-end
      this.getSongs() // Chooses a song as soon as it is clicked
      this.game = "What lyrics are missing?"
      setInterval(() => { //Updates every 5 second to see what song is currently selected
        this.getSongs()
      }, 15*1000)
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
