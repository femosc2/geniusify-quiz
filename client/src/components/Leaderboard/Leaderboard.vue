<template>
    <div>
      <leaderboard-button @leaderboardToggledOff="toggleLeaderboard(false)" @leaderboardToggledOn="toggleLeaderboard(true)" />
      <leaderboard-list v-if="isLeaderboardShowing" :isLeaderboardShowing="isLeaderboardShowing" />
      <leaderboard-exit @leaderboardToggledOff="toggleLeaderboard(false)" @leaderboardToggledOn="toggleLeaderboard(true)" v-if="isLeaderboardShowing" :isLeaderboardShowing="isLeaderboardShowing"/>
    </div>
</template>

<script>
import LeaderboardList from "./LeaderboardList.vue";
import LeaderboardButton from "./LeaderboardButton.vue";
import LeaderboardExit from "./LeaderboardExit.vue";

export default {
  data: function() {
      return {
          isLeaderboardShowing: false
      }
  },
  methods: {
    toggleLeaderboard(toggleStatus) {
      this.isLeaderboardShowing = toggleStatus;
    }
  },
  components: {
      LeaderboardList,
      LeaderboardButton,
      LeaderboardExit
  }
};
</script>

<style>

</style>
