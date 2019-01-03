<template>
  <div id="app">
    <div v-if="isNameChosen === null">
    </div>
    <div>
      <leaderboard-button :isLeaderboardShowing="isLeaderboardShowing" @leaderboardToggledOn="leaderboardToggle(true)" @leaderboardToggledOff="leaderboardToggle(false)" />
      <leaderboard v-if="isLeaderboardShowing" :isLeaderboardShowing="isLeaderboardShowing"  />
    </div>
    <div v-if="isGenreChoosen === null">
      <Genre @genreSelected="genreChoosen"/>
    </div>
    <div v-else>
      
    </div>
  </div>
</template>

<script>
import Genre from "./components/Genre/Genres.vue";
import Leaderboard from "./components/Leaderboard/Leaderboard.vue";
import LeaderboardButton from "./components/Leaderboard/LeaderboardButton.vue";
const axios = require("axios")

export default {
  name: "app",
  components: {
    Genre,
    Leaderboard,
    LeaderboardButton
  },
  data: function() {
    return {
      playerName: null,
      isNameChosen: null,
      isGenreChoosen: null,
      isLeaderboardShowing: false
    };
  },
  methods: {
    genreChoosen(genreSelected) {
      // Function which recieves the current Genre
      this.isGenreChoosen = genreSelected
      console.log()
    },
    leaderboardToggle(toggleStatus) {
      this.isLeaderboardShowing = toggleStatus
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
