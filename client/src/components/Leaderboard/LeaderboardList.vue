<template>
    <div>
        <ul v-if="isLeaderboardShowing">
            <h4>Leaderboard</h4>
            <transition-group name="fade">
                <li v-for="(player,index) in players" :key="index+1">{{ player.name }} {{ player.score }}</li>
                </transition-group>
        </ul>
    </div>
</template>

<script>
const axios = require("axios");
// https://geniusify-quiz.firebaseio.com/
export default {
  data: function() {
    return {
      players: "",
    };
  },
  methods: {
    getPlayers() {
      axios
        .get("https://geniusify-quiz.firebaseio.com/Players.json")
        .then(response => {
          console.log(response.data);
          this.players = response.data;
          this.leaderboard = _.sortBy(this.players, "key")
        });
    },
  },
  created() {
    this.getPlayers();
  },
  props: ["isLeaderboardShowing"]
}
</script>

<style scoped>
div {
  background-color: rgba(255, 255, 255, 0.8);
  color: black;
  position: fixed;
  left: 50%;
  width: 50%;
  transform: translate(-50%, 0);
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
}
.fade-enter-active,
.fade-leave-active {
  transition: all 1s, height 1s, opacity 0.5s;
  overflow: hidden;
  max-height: 1000px;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
  max-height: 0px;
}
ul {
    list-style-type: none;
    text-align: center;
}
h4 {
    text-align: center;
}
</style>
