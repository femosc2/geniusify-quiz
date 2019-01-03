<template>
  <div id="app">
    <div>
      <leaderboard-button @leaderboardToggledOff="toggleLeaderboard(false)" @leaderboardToggledOn="toggleLeaderboard(true)" />
      <leaderboard v-if="isLeaderboardShowing" :isLeaderboardShowing="isLeaderboardShowing" />
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
import LeaderboardButton from "./components/Leaderboard/LeaderboardButton.vue"
import Start from "./components/Start/Start.vue";

export default {
  name: "app",
  components: {
    Genre,
    Leaderboard,
    LeaderboardButton,
    Start
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
    },
    toggleLeaderboard(toggleStatus) {
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
