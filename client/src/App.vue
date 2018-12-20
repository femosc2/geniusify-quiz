<template>
  <div id="app">
    <div v-if="isGenreChoosen === null">
      <Genre @genreSelected="genreChoosen" />
    </div>
    <div v-else>
      <p> Genre is chosen </p>
    </div>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Genre from "./components/Genre/Genres.vue"

export default {
  name: 'app',
  components: {
    HelloWorld,
    Genre
  },
  data: function() {
    return {
    isGenreChoosen: null
    }
  },
  methods: {
    genreChoosen(genreSelected) {
      this.isGenreChoosen = genreSelected
      console.log("app state" + this.isGenreChoosen)
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
