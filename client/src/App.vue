<template>
  <div id="app">
    <div>
      <leaderboard />
    </div>
    <div v-if="isGenreChoosen === null">
      <Genre @genreSelected="genreChoosen"/>
    </div>
    <div v-else>
        <Start :isGenreChoosen="isGenreChoosen"/>
    </div>
  </div>
</template>

<script>

import Genre from "./components/Genre/Genres.vue";
import Leaderboard from "./components/Leaderboard/Leaderboard.vue";
import Start from "./components/Start/Start.vue";

export default {
  name: "app",
  components: {
    Genre,
    Leaderboard,
    Start
  },
  data: function() {
    return {
      playerName: null,
      isNameChosen: null,
      isGenreChoosen: null,

    };
  },
  methods: {
    genreChoosen(genreSelected) {
      // Function which recieves the current Genre
      this.isGenreChoosen = genreSelected
    },
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
