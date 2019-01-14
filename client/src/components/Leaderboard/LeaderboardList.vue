<template>
  <div>
    <ul v-if="isLeaderboardShowing">
      <h4>Leaderboard</h4>
      <transition-group name="fade">
        <li
          v-for="(player,index) in orderedPlayers" :key="index+1">
          <!-- Index+1 is there in order for transition to work -->
          {{ player.name }} : {{ player.score }}
        </li>
      </transition-group>
    </ul>
  </div>
</template>

<script>
const axios = require("axios");
const _ = require("lodash");

export default {
  data: function() {
    return {
      players: "",
      leaderboard: ""
    };
  },
  methods: {
    getPlayers() {
      // Gets the leaderboard from firebase.
      axios
        .get("https://geniusify-quiz.firebaseio.com/Players.json")
        .then(response => {
          this.players = response.data;
        });
    }
  },
  created() {
    this.getPlayers();
  },
  props: ["isLeaderboardShowing"],
  computed: {
    orderedPlayers: function() {
      // Sorts the leaderboard by score.
      this.leaderboard = _.orderBy(this.players, "score"); // Uses Lodash in order to sort the objects.
      let reverseLeaderboard = this.leaderboard.reverse(); // Sorts the objects in the correct direction.
      return reverseLeaderboard.slice(0, 10); // Only show the top 10 players on the leaderboard
    }
  }
};
</script>

<style scoped>

div {
  color: black;
  position: fixed;
  left: 50%;
  width: 50%;
  transform: translate(-50%, 0);
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  overflow-y: scroll;
  height: 100vh;
  animation: fadeIn 0.5s 1 linear;
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
  background-color: rgba(0, 0, 0, 0.8);
}

li {
  border-bottom: 1px solid #ed4c67;
  margin: 0;
  width: 100%;
  text-align: center;
  height: 50px;
  font-size: 40px;
  color: #ed4c67;
}

h4 {
  color: #ed4c67;
  font-size: 75px;
  padding: 0;
  margin: 0 auto;
  text-decoration-line: underline;
  text-align: center;
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>
