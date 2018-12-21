<template>
  <div>
    <a class="genreButton" @click="chosenGenre(genre.genreName)" v-for="genre in genres" :key="genre.id">{{ genre.genreName }}</a>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      genreChosen: null
    }
  },

  props:["genres"],
  
  methods: {
    chosenGenre(genre) {
      this.genreChosen = genre;
      this.$emit("genreSelected" , this.genreChosen)
    }
  }
};

</script>
<style>
  .genreButton {
    color: #0b8793;
    font-size: 2rem;
    padding: 5rem;
    background: white;
    margin: 1rem;
    border-radius: 1rem;
  }
</style>

