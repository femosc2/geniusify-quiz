<template>
  <div>
    <leaderboard-button
      @leaderboardToggledOff="toggleLeaderboard(false)"
      @leaderboardToggledOn="toggleLeaderboard(true)"
    />
    <leaderboard-list 
      v-if="isLeaderboardShowing" 
      :isLeaderboardShowing="isLeaderboardShowing"
    />
    <leaderboard-exit
      @leaderboardToggledOff="toggleLeaderboard(false)"
      @leaderboardToggledOn="toggleLeaderboard(true)"
      v-if="isLeaderboardShowing"
      :isLeaderboardShowing="isLeaderboardShowing"
    />
  </div>
</template>

<script>
import LeaderboardList from "./LeaderboardList.vue";
import LeaderboardButton from "./LeaderboardButton.vue";

export default {
  data: function() {
    return {
      isLeaderboardShowing: false
    };
  },
  methods: {
    toggleLeaderboard(toggleStatus) {
      // Simply hides / shows the leaderboard
      this.isLeaderboardShowing = toggleStatus;
    }
  },
  components: {
    LeaderboardList,
    LeaderboardButton
  }
};
</script>
