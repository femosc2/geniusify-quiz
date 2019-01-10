<template>
    <div>
        <p>{{ song}}</p>
        <p>{{ artist}}</p>
        <img :src="image" alt="">
        <p> {{ countdown }} </p>
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
p {
  color: white;
  font-size: 90px;
}
</style>
