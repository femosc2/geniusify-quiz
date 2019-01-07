<template>
    <div>
      <input type="text" v-model="userGuess">
      <button @click="checkWord">Check</button>
      <p> {{ score }} </p>
    </div>
</template>

<script>
const axios = require("axios");

export default {
  data: function() {
    return {
      userGuess: null,
      score: 0
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
        axios
          .put(
            "https://geniusify-quiz.firebaseio.com/Players/" + this.name + ".json"
          , this.score)
          .then(response => {
            console.log(response.data);
          });
        this.score = 0;
        setTimeout(function() {
            location.reload();
        }, 2*1000)
      }, 10 * 1000);
    }
  },

  props: ["isGenreChoosen", "words", "name"],

  created() {
    this.game();
  }
};
</script>

<style scoped>
</style>
