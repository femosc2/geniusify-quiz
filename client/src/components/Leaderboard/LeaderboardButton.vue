<template>
    <div>
        <button @click="toggleLeaderboard">
            Leaderboard
        </button>
    </div>
</template>

<script>
export default {
  data: function() {
    return {
      leaderboardToggle: false
    };
  },
  methods: {
    toggleLeaderboard() {
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
button {
  cursor: pointer;
  position: absolute;
  left: 0;
  z-index: 500;
  color: #ED4C67;
  background: white;
  margin: 1rem;
  border-radius: 1rem;
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
}
</style>
