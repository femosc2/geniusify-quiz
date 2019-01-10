<template>
<div>
    <h2 class="loadingText">Game time left</h2>
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
  animation: progressBar 200s 1 linear;
  transition: 1s;
}
   .loading-icon {
    color: white;
    animation-name:            rotate;
    animation-duration:        2s;
    animation-iteration-count: infinite;
    animation-timing-function: linear;
 }

 .loadingText {
   color: white;
   font-weight: 200;
   margin-bottom: 0;
   text-transform: uppercase;
 }

@keyframes progressBar {
  0% {
    width: 100%;
  }
  100% {
    width: 0%;
  }
}

</style>
