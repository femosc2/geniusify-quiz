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
        }
    },
    methods: {
        toggleLeaderboard() {
            if (this.leaderboardToggle) {
                this.leaderboardToggle = false
                console.log("false")
                this.$emit("leaderboardToggledOff", false);
            } else {
                this.leaderboardToggle = true
                console.log("true")
                this.$emit("leaderboardToggledOn", true);
            }
        }
    },
    props: ["isLeaderBoardShowing"]
}
</script>

<style scoped>
button {
    float: right;
    z-index: 500;
}
</style>
