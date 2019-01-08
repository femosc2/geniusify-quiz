<template>
    <div>
        <ul v-if="isLeaderboardShowing">
            <h4>Leaderboard</h4>
            <transition-group name="fade">
                <li v-for="(player,index) in orderedPlayers" :key="index+1">{{ player.name }} : {{ player.score }}</li>
            </transition-group>
        </ul>
    </div>
</template>

<script>
const axios = require("axios");
const _ = require("lodash")

export default {
  data: function() {
    return {
      players: "",
      leaderboard: "",
    };
  },
  methods: {
    getPlayers() {
      axios
        .get("https://geniusify-quiz.firebaseio.com/Players.json")
        .then(response => {
          console.log(response.data);
          this.players = response.data;
        });
    },
  },
  created() {
    this.getPlayers();
  },
  props: ["isLeaderboardShowing"],
  computed: {
  orderedPlayers: function () {
    this.leaderboard = _.orderBy(this.players, 'score')
    return this.leaderboard.reverse()
  }
}
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
  overflow: visible;
}
.fade-enter-active,
.fade-leave-active {
  transition: all 10s, height 10s, opacity 0.5s;
  max-height: 5000px;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
  max-height: 0px;
}
ul {
    list-style-type: none;
    text-align: center;
    padding: 0;
    overflow: scroll;
}
h4 {
    text-align: center;
}
li {
  border: 1px solid #ed4c67;
  margin: 0;
  width: 100%;
  text-align: center;
  height: 100px;
  font-size: 75px;
  color: #ed4c67;
}
h4 {
  color: #ed4c67;
  font-size: 100px;
  padding: 0;
  margin: 0 auto;
  text-decoration-line: underline
}
</style>
