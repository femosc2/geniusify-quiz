<template>
  <div>
    <!-- When click on genreButton, run function chosenGenre which loops over genres. Displays name of each Genres-->
    <div @click="chosenGenre(genre.genreName)" v-for="genre in genres" :key="genre.id" class="genreList" v-bind:style="{ 'background-image': 'url(' + genre.image + ') ' }">{{ genre.genreName }}</div>
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
<style scoped>
  .genreList {
    display: inline-block;
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center; 
    cursor: pointer;
    width: 280px;
    text-align: center;
    height: 47rem;
    border: 1px #ED4C67;
    color: #ED4C67;
    font-size: 2rem;
    padding: 5rem;
    background: white;
    margin: 1rem;
    border-radius: 1rem;
  }
</style>

