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
        this.playlist = "https://api.spotify.com/v1/playlists/3qu74M0PqlkSV76f98aqTd"
      } else if (this.isGenreChoosen === "Christmas") {
        this.playlist = "https://api.spotify.com/v1/playlists/5OP7itTh52BMfZS1DJrdlv"
      } else if (this.isGenreChoosen === "Pop") {
        this.playlist = "https://api.spotify.com/v1/playlists/3ZgmfR6lsnCwdffZUan8EA"
      } else if (this.isGenreChoosen === "Rap") {
        this.playlist = "https://api.spotify.com/v1/playlists/21sgjLGbnEgNMTpjnaO2b6"
      }
      this.$http
        .get(this.playlist)
        .then(response => {
          return response.json();
        })
        .then(data => {
          const resultArray = [];
          for (let key in data) {
            resultArray.push(data[key]);
          }
          this.playlist = resultArray;
          let randomNumber = this.randomNumber();
          this.song = this.playlist[12].items[randomNumber].track.name
          this.artist = this.playlist[12].items[randomNumber].track.artists[0].name
          this.image = this.playlist[12].items[randomNumber].track.album.images[1].url
        });

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
