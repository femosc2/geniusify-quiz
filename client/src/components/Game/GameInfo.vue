<template>
  <div>
      <h2>{{ name }}</h2>
      <p class="preload"> {{ artist }}</p>
      <p> {{ song }} </p>
      <p> {{ lyrics }} </p>
    </div>
</template>

<script>
const axios = require("axios")

export default {

  data: function() {
    return {
    };
  },

  props: ["isGenreChoosen", "playlist", 
          "song", "artist", 
          "image", "lyrics", "name"
          ],

  methods: {

  },
};
</script>

<style scoped> 
  p {
    text-align: center;
  }
  h2 {
    color: #ED4C67;
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
