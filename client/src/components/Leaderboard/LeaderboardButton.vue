<template>
  <div>
    <div class="leaderboardBtn" @click="toggleLeaderboard(), show=!show">
      <i v-if="show" class="icon fas fa-list-ol"></i>
      <i v-else class="icon fas fa-times"></i>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      show: true,
      leaderboardToggle: false
    };
  },
  methods: {
    toggleLeaderboard() {
      // Toggles the visibility of the leaderboard on or off.
      if (this.leaderboardToggle) {
        this.leaderboardToggle = false;
        console.log("false");
        this.$emit("leaderboardToggledOff", false);
      } else {
        this.leaderboardToggle = true;
        console.log("true");
        this.$emit("leaderboardToggledOn", true);
      }
    }
  }
};
</script>

<style scoped>
.icon {
  height: 3rem;
  width: 3rem;
  font-size: 3rem;
  position: relative;
}

.leaderboardBtn {
  padding: 1.3rem;
  cursor: pointer;
  position: fixed;
  top: 4%;
  right: 2%;
  z-index: 500;
  color: white;
  background: #ed4c67;
  margin: 1rem;
  border-radius: 50%;
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  transition: all 0.1s;
}

.leaderboardBtn:hover {
  background-color: white;
  color: #ed4c67;
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }

  75% {
    opacity: 0;
  }

  100% {
    opacity: 1;
  }
}
</style>
