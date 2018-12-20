<template>
  <div id="app">
    <Genre />
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
