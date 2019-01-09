<template>
  <div>
      <h2>{{ name }}</h2>
      <p> {{ artist }}</p>
      <p> {{ song }} </p>
      <p> {{ lyrics }} </p>
      <img :src="image" alt="">
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

</style>
