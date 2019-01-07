<template>
  <div>
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
          "image", "lyrics"
          ],

  methods: {

  },
};
</script>

<style scoped> 
  p {
    text-align: center;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }

</style>
