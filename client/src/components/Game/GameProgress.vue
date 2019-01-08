<template>
    <div />
</template>

<script>
export default {
    
}
</script>

<style scoped>
div {
    height: 100px;
    background-color: black;
    width: 0%;
    animation: progressBar 150s infinite linear;
  }
  @keyframes progressBar {
    0% {
      width: 0%;
    }
    100% {
      width: 100%;
    }
  }
</style>
