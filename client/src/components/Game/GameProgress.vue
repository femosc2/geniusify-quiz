<template>
    <div>{{ this.name }}</div>
</template>

<script>
export default {
    data: function() {
        return {
            song: ""
        }
    },
    props: ["name"]
}
</script>

<style scoped>
div {
    height: 100px;
    background-color: #ED4C67;
    border-radius: 30px;
    width: 0%;
    animation: progressBar 150s 1 linear;
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
