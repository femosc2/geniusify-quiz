<template>
  <div v-if="isLeaderboardShowing">
    <button @click="toggleLeaderboard" class="exitButton">
      <i class="fas fa-times"></i>
    </button>
  </div>
</template>

<script>

export default ({
    data: function() {
        return {
            isLeaderboardToggle: false
        };
    },

    methods: {
        toggleLeaderboard() {
            if (this.leaderboardToggle) {
                this.lederboardToggle = false;
                this.$emit("leaderboardToggledOff", false);
            } else {
                this.leaderboardToggle = true;
                this.$emit("leaderboardToggledOn", true)
            }
        }
    },

    props: ["isLeaderboardShowing"],
    
});


</script>

<style scoped>
.exitButton {
  position: fixed;
  left: 85rem;
}
</style>
