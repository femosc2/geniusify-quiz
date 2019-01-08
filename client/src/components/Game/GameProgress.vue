<template>
<div>
    <h2>Time left</h2>
    <div class="progressBar">
      <i class="loading-icon fas fa-compact-disc fa-4x"></i>
    </div>

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
  height: 4rem;
  background-color: #ed4c67;
  border-radius: 100px;
  width: 0%;
  animation: progressBar 150s 1 linear;
  transition: 1s;
}
   .loading-icon {
    color: white;
    animation-name:            rotate;
    animation-duration:        2s;
    animation-iteration-count: infinite;
    animation-timing-function: linear;
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
