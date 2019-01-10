<template>
    <div v-if="!gameOver">
      <input type="text" v-model="userGuess" v-on:keydown.enter="checkWord">
      <button class="checkWordBtn" @click="checkWord">Guess</button>
      <h2> Score: {{ score }} </h2>
      <p v-for="word in correctWords"> {{ word }}</p>
      <h2>{{ this.song }}</h2>
      <div class="progressBar" />
    </div>
    <div v-else>
      <h1>GAME OVER!</h1>
      <button class="newGameBtn" @click="newGame">NEW GAME</button>
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
      gameOver: false,
      // Numb of avalible chances to guess (3)?,
    };
  },

  methods: {
    checkWord() {
      for (var i = 0; i < this.words.length; i++) {
        if (this.userGuess.toLowerCase() === this.words[i].toLowerCase()) {
          this.score++;
          this.correctWords.push(this.words[i])
          this.words.splice(i, 1)
          this.userGuess = ""
        } else {
          this.userGuess = ""
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
        this.$emit("gameOver", true)
      }, 150 * 1000);
    },
    newGame() {
      location.reload()
    }
  },
  props: ["isGenreChoosen", "words", "name", "song", "correctWords"],
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
    background-color: #ED4C67;
    border-radius: 100px;
    width: 0%;
    animation: progressBar 15s infinite linear;
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
   color: #ED4C67;
  }
</style>
