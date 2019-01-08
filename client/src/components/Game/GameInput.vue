<template>
    <div v-if="!gameOver">
      <input type="text" v-model="userGuess">
      <button @click="checkWord">Check</button>
      <p> {{ score }} </p>
      <div class="progressBar" />
    </div>
    <div v-else>
      <transition>
      <h1>GAME OVER!</h1>
      </transition>
    </div>
</template>

<script>
const axios = require("axios");
const _ = require("lodash")

export default {
  data: function() {
    return {
      userGuess: null,
      score: 0,
      gameOver: false
      // Numb of avalible chances to guess (3)?,
    };
  },

  methods: {
    checkWord() {
      for (var i = 0; i < this.words.length; i++) {
        if (this.userGuess === this.words[i]) {
          this.score++;
          console.log("Guess is correct");
        } else {
          console.log("Guess is incorrect");
        }
      }
    },

    game() {
      setInterval(() => {
        let score = {
          name: this.name,
          score: this.score
        }
        axios
          .post(
            "https://geniusify-quiz.firebaseio.com/Players.json"
          , score)
          .then(response => {
            console.log(response.data);
          });
        this.score = 0;
        this.gameOver = true
        setTimeout(function() {
           location.reload();
        }, 2 * 1000)
      }, 150 * 1000);
    }
  },
  props: ["isGenreChoosen", "words", "name"],
  created() {
    this.game();
  }
};
</script>

<style scoped>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
  }
  .progressBar {
    height: 100px;
    background-color: black;
    width: 0%;
    animation: progressBar 15s infinite linear;
  }
  @keyframes progressBar {
    0% {
      width: 0%;
    }
    100% {
      width: 100%;
    }
  }
</style>
