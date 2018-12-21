<template>
  <div id="app">
    <div v-if="isGenreChoosen === null">
      <Genre @genreSelected="genreChoosen"/>
    </div>
    <div v-else>
      <p @click="authorizeSpotify">Genre is chosen</p>
    </div>
  </div>
</template>

<script>
import HelloWorld from "./components/HelloWorld.vue";
import Genre from "./components/Genre/Genres.vue";

export default {
  name: "app",
  components: {
    HelloWorld,
    Genre
  },
  data: function() {
    return {
      isGenreChoosen: null,
      playlist: null
    };
  },
  methods: {
    genreChoosen(genreSelected) {
      this.isGenreChoosen = genreSelected;
      console.log("app state " + this.isGenreChoosen);
    },
    loadPlaylists() {
      this.$http.get("");
    },
    authorizeSpotify() {
      this.$http
        .get("https://api.spotify.com/v1/playlists/59ZbFPES4DQwEjBpWHzrtC")
        .then(response => {
          return response.json();
        })
        .then(data => {
          const resultArray = [];
          for (let key in data) {
            resultArray.push(data[key]);
          }
          this.playlist = resultArray;
          console.log(this.playlist[12].items[1].track.name);
        });

      // this.$http.get("https://api.spotify.com/v1/playlists/59ZbFPES4DQwEjBpWHzrtC")
      //             .then(response => {
      //                 console.log(response)
      //             }, error => {
      //             console.log(error)}
      //             )
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
  color: #2c3e50;
  margin-top: 60px;
}
</style>
