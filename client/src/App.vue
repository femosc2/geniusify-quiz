<template>
  <div id="app">
    <div v-if="!isNameChoosen">
      <choose-name @nameChoosen="setName" />
    </div>
    <div v-if="isNameChoosen">
      <leaderboard />
    </div>
    <div v-if="isGenreChoosen === null && isNameChoosen">
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
import ChooseName from "./components/ChooseName/ChooseName.vue"

export default {
  name: "app",
  components: {
    Genre,
    Leaderboard,
    Start,
    ChooseName

  },
  data: function() {
    return {
      playerName: null,
      isNameChoosen: false,
      isGenreChoosen: null,
      name: null

    };
  },
  methods: {
    genreChoosen(genreSelected) {
      // Function which recieves the current Genre
      this.isGenreChoosen = genreSelected
    },
    setName(name) {
      this.name = name
      this.isNameChoosen = true
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
