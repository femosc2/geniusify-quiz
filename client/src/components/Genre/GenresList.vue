<template>
  <div class="genreContainer">
    <div
      @click="chosenGenre(genre.genreName)"
      v-for="genre in genres"
      :key="genre.id"
      v-bind:style="{ 'background-image': 'url(' + genre.image + ') ' }"
      class="genreList"
    >
      <h3 class="genreHeader">{{ genre.genreName }}</h3>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      genreChosen: null
    };
  },
  props: ["genres"],

  methods: {
    // Method changing genreChosen from null to the selected genre.
    //$emit creates the event genreSelected and sends it with genreChosen to Genre.vue
    chosenGenre(genre) {
      // Lets the user choose a genre.
      this.genreChosen = genre;
      this.$emit("genreSelected", this.genreChosen);
    }
  }
};
</script>
<style scoped>
.genreContainer {
  width: 100%;
  overflow: hidden;
}

.genreHeader {
  font-size: 3.2rem;
  margin-top: 40rem;
  font-weight: 200;
}

.genreList {
  display: inline-block;
  width: 25%;
  cursor: pointer;
  text-align: center;
  height: 100vh;
  color: white;
  font-size: 2rem;
  animation: fadeIn 2s 1 linear;
  background-size: cover;
  background-position: center;
  transition: all 1.5s ease;
}

.genreList:hover {
  -ms-transform: scale(1.1);
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
  color: #ed4c67;
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

