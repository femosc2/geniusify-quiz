<template>
  <div v-if="!gameOver">
    <input class="inputGuess" type="text" v-model="userGuess" v-on:keydown.enter="checkWord">
    <button class="checkWordBtn" @click="checkWord">Guess</button>
    <h2>Score: {{ score }}</h2>
    <p v-for="(word, index) in correctWords" :key="index">{{ word }}</p>
    <h2>{{ this.song }}</h2>
    <div class="progressBar"/>
  </div>
</template>

<script>
const axios = require("axios");

export default {
  data: function() {
    return {
      userGuess: null,
      gameOver: false
    };
  },
  methods: {
    checkWord() {
      // Checks if the user guess is correct.
      for (var i = 0; i < this.words.length; i++) {
        if (this.userGuess.toLowerCase() === this.words[i].toLowerCase()) {
          this.correctWords.push(this.words[i]); // adds the correct word to a new array
          this.words.splice(i, 1); // Removes the word from the words array.
          this.userGuess = ""; // Resets the user guess so the user wont have to manually delete it.
          this.$emit("correctGuess"); // Tells Game.vue that the guess was correct.
        }
      }
    }
  },
  props: ["isGenreChoosen", "words", "name", "song", "correctWords", "score"],
};
</script>

<style scoped>

.inputGuess {
  margin-top: 2rem;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

.progressBar {
  margin-top: 2rem;
  height: 2px;
  background-color: #ed4c67;
  border-radius: 100px;
  width: 0%;
  animation: progressBar 13s infinite linear;
}
.newGameBtn {
  font-size: 1.7em;
}

@keyframes progressBar {
  0% {
    width: 0%;
  }
  100% {
    width: 100%;
  }
}

h2 {
  color: #ed4c67;
}

</style>
