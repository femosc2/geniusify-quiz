<template>
  <div>
    <div class="exitBtn" @click="quit">
      <i class="icon fas fa-times"></i>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {

    };
  },
  methods: {
    quit() {
    // Method which reloads the page
      location.reload()
    }
  }
};
</script>

<style scoped>
.icon {
  height: 2rem;
  width: 2rem;
  font-size: 2rem;
  position: relative;
}

.exitBtn {
  padding: 1.3rem;
  cursor: pointer;
  position: fixed;
  top: 2%;
  right: 1%;
  z-index: 500;
  color: white;
  background: #ed4c67;
  margin: 1rem;
  border-radius: 50%;
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  transition: all 0.1s;
}

.exitBtn:hover {
  background-color: white;
  color: #ed4c67;
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }

  75% {
    opacity: 0;
  }

  100% {
    opacity: 1;
  }
}
</style>
