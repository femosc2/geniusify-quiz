<template>
    <div>
      <input type="text" v-model="userGuess">
      <button @click="checkWord">Check</button>
    </div>
</template>

<script>
const axios = require("axios")

export default {
    data: function() {
        return {
            userGuess: null,
            score: 0,
            // Numb of avalible chances to guess (3)?,
        }
    },

    methods: {
        checkWord() {
            for (var i = 0; i < this.words.length; i++) {
                if (this.userGuess === this.words[i]) {
                    this.score++
                    console.log("Guess is correct ∏")
                } else {
                    console.log("Guess is incorrect ∏")
                }
            }
        }
    },

    props: ["isGenreChoosen", "words"]
}
</script>

<style scoped>

</style>
