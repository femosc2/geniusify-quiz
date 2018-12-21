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



// <button v-for="genre in genres" :key="genre.id" @click="chooseGenre(genre.genreName)"> {{genre.genreName}}</button>

</script>
<style>
  .genreButton {
    padding: 5rem;
  }
</style>

