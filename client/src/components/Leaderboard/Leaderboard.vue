<template>
    <div>
        <h4>Leaderboard</h4>
        <ul v-if="isLeaderboardShowing">
            <li v-for="(value, key) in players" :key="key">{{ key }} : {{ value }}</li>
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
        .get("https://geniusify-quiz.firebaseio.com/Players.json")
        .then(response => {
          console.log(response.data)
          this.players = response.data
        })
    } 
        },
        created() {
            this.getPlayers()
        },
        props: ["isLeaderboardShowing"]
    }
</script>

<style scoped>
    div {
        background-color: white;
        color: black;
        position: fixed;
        left: 50%;
        width: 50%;
        transform: translate(-50%, 0);
    }
</style>
