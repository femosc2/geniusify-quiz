<template>
<div>
    <h2>Time left</h2>
    <div class="progressBar" />
</div>
</template>

<script>
export default {
  data: function() {
    return {
      song: ""
    };
  },
  props: ["name"]
};
</script>

<style scoped>
.progressBar {
  height: 100px;
  background-color: #ed4c67;
  border-radius: 100px;
  width: 0%;
  animation: progressBar 150s 1 linear;
  transition: 1s;
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
