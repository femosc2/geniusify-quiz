<template>
  <div class="genreContainer">
    <!-- When click on genreButton, run function chosenGenre which loops over genres. Displays name of each Genres-->
    <div @click="chosenGenre(genre.genreName)" v-for="genre in genres" :key="genre.id" v-bind:style="{ 'background-image': 'url(' + genre.image + ') ' }" class="genreList">{{ genre.genreName }} </div>
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

  .genreContainer {
    width: 100%;
    overflow: hidden;

  }
  .genreList {
    display: inline-block;
    width: 25%;
    cursor: pointer;
    text-align: center;
    height: 100vh;
    border: 1px #ED4C67;
    color: #ED4C67;
    font-size: 2rem;
    animation: fadeIn 2s 1 linear;
    background-size: cover;
    background-position: center;
    transition: all 3s ease;
  }

  .genreList:hover {
  -ms-transform: scale(1.1);
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
  
   }

@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

</style>

