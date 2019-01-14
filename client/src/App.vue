<template>
  <div id="app">
    <div v-if="!isNameChoosen">
      <choose-name @nameChoosen="setName"/>
    </div>
    <div v-if="isNameChoosen">
      <leaderboard/>
    </div>
    <div v-if="isGenreChoosen === null && isNameChoosen">
      <Genre @genreSelected="genreChoosen"/>
    </div>
    <div v-else>
      <Game :isGenreChoosen="isGenreChoosen" :name="name"/>
    </div>
  </div>
</template>

<script>
import Genre from "./components/Genre/Genres.vue";
import Leaderboard from "./components/Leaderboard/Leaderboard.vue";
import Game from "./components/Game/Game.vue";
import ChooseName from "./components/ChooseName/ChooseName.vue";

export default {
  name: "app",
  components: {
    Genre,
    Leaderboard,
    Game,
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
      // Sets the genre, parameter recieved from Genre.vue
      this.isGenreChoosen = genreSelected;
    },
    setName(name) {
      // Sets the name of the user, parameter recieved from ChooseName.vue
      this.name = name;
      this.isNameChoosen = true;
    }
  }
};
</script>

<style>

* {
  animation: blur 0.9s 1 reverse;
  transition: 1s;
  animation-timing-function: cubic-bezier(0.66, -0.16, 0.97, 0.36);
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  overflow: visible;
}

@keyframes blur {
  0% {
    filter: blur(0px);
  }
  100% {
    filter: blur(15px);
  }
}
</style>
