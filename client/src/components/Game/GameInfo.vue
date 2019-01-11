<template>
  <div>
    <h2 class="playerName">{{ name }}</h2>
    <p class="song">{{ song }}</p>
    <p class="lyrics">{{ lyrics }}</p>
  </div>
</template>

<script>
const axios = require("axios");

export default {
  data: function() {
    return {};
  },

  props: [
    "isGenreChoosen",
    "playlist",
    "song",
    "artist",
    "image",
    "lyrics",
    "name"
  ]
};
</script>

<style scoped>

p {
  text-align: center;
}


.playerName {
 color: white;
 font-weight: 200;
 margin-bottom: 1rem;
}

.song {
  color: #ED4C67;
  font-size: 3rem;
  margin-bottom: .5;
  margin-top: 0;
}

.lyrics {
  margin-top: 0;
  font-size: 2rem;
  margin-left: 12rem;
  margin-right: 12rem;
}

.preload {
  animation: load 1s 1 linear;
  animation-fill-mode: forwards;
}

@keyframes load {
  0% {
    opacity: 1;
    display: block;
  }
  95% {
    height: inherit;
    width: inherit;
  }
  100% {
    opacity: 0;
    height: 0px;
    width: 0px;
  }
}
</style>
