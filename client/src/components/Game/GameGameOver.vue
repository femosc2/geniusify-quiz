<template>
    <div>
      <h1>GAME OVER! SCORE: {{ score }}</h1>
      <button class="newGameBtn" @click="newGame">NEW GAME</button>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            
        }
    },
    methods: {
    newGame() {
      location.reload()
    }
    },
    props: ["score"]
}
</script>

<style>

</style>
