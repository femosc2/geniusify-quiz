<template>
    <div>
        <h1 class="countdownText">Guess the missing lyrics off</h1>
        <h1 class="songName">{{ song }}</h1>
        <h2 class="artistName">{{ artist }}</h2>
        <img :src="image" class="albumCover" alt="Album cover">
        <p class="countdownTimer"> {{ countdown }} </p>
    </div>
</template>

<script>
export default {
  data: function() {
    return {
      countdown: 3
    };
  },
  methods: {
    countdownTimer() {
      setInterval(() => {
        this.countdown--;
        if (this.countdown === -50) {
          this.$emit("countdownDone", false)
      }
      }, 1 * 1000);
    }
  },
  created() {
    this.countdownTimer();
  },
  props: ["image", "song", "artist", "infoPage"]
};
</script>

<style scoped>

.countdownText {
  color: white;
  font-size: 1.5rem;
  text-transform: none;
}

.songName {
  margin-bottom: 0;
}

.artistName {
  margin-top: 0;
}

.albumCover {
 margin-bottom: 0;
}

.countdownTimer {
  margin: 0;
  font-size: 20rem;
}
</style>
