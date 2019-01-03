<template>
    <div>
        <h4>Leaderboard</h4>
        <ul>
            <li v-for="(player, index) in players" :key="index"></li>
        </ul>
    </div>
</template>

<script>
const axios = require("axios")
// https://geniusify-quiz.firebaseio.com/
export default {
    data: function() {
        return {
            players: "",
        }
    },
    methods: {
        getPlayers() {
      axios
        .get("https://geniusify-quiz.firebaseio.com")
        .then(response => {
          return response.json();
        })
        .then(data => {
          const resultArray = [];
          for (let key in data) {
            resultArray.push(data[key]);
          }
          this.players = resultArray
          console.log(this.players)
        });
    } 
        }
    }
</script>

<style>

</style>
