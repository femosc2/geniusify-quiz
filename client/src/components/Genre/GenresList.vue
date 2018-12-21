<template>
  <div>
    <!-- When click on genreButton, run function chosenGenre which loops over genres. Displays name of each Genres-->
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
  // props collects a list of genres from parent Ganre.vue
  props:["genres"],
  
  methods: {
    // Method changing genreChosen from null to the selected genre. 
    //$emit creates the event genreSelected and sends it with genreChosen to Genre.vue
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

