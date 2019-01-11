<template>
  <div>
    <h1 class="gameOver">GAME OVER!</h1>
    <h2 class="score">SCORE: {{ score }}</h2>
    <button class="newGameBtn" @click="newGame">NEW GAME</button>
  </div>
</template>

<script>
export default {
  methods: {
    newGame() {
      location.reload();
    }
  },
  props: ["score"]
};
</script>

<style>
.gameOver {
  text-transform: uppercase;
  font-size: 6rem;
  font-weight: 200;
  color: #ed4c67;
}

.score {
  color: white;
  font-size: 2.4rem;
}
</style>
