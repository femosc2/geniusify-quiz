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
      countdown: 5
    };
  },
  methods: {
    countdownTimer() {
      //Countdown timer between the different songs
      setInterval(() => {
        this.countdown--;
        if (this.countdown === 0) {
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
  font-size: 1.2rem;
  font-weight: 200;
  text-transform: uppercase;
   margin-bottom: 0;
}

.songName {
  color: #ED4C67;
  font-size: 4rem;
  margin-bottom: 0;
  margin-top: .5rem;
}

.artistName {
  color: #ED4C67;
  font-size: 1.4;
  margin-top: 0;
}

.albumCover {
 margin-bottom: 0;
 width: 17rem;
}

.countdownTimer {
  margin: 0;
  font-size: 17rem;
}
</style>
