<template>
    <div>
        <h1>Welcome to Geniusify-Quiz</h1>
        <h2>Choose your name!</h2>
        <input type="text" v-model="name" v-on:keydown.enter="submit">
        <button @click="submit">SUBMIT</button>
        <i class="cd-icon fas fa-compact-disc fa-10x"></i>
    </div>
</template>

<script>
export default {
  data: function() {
    return {
      name: ""
    };
  },
  methods: {
    submit() {
      if (this.name === "") {
        this.name = "Anonymous"
      }
      this.$emit("nameChoosen", this.name);
    }
  }
};
</script>

<style scope>

h1 {
  text-transform: uppercase;
  font-size: 3rem;
  font-weight: 400;
  color: #ED4C67;
}

h2 {
    color: #ED4C67;
}

input {
  border: none;
  background-color: rgba(255, 255, 255, 0.3);
  color: #ED4C67;
  width: 50%;
  height: 100px;
  font-size: 80px;
  text-align: center;
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
}
button {
  cursor: pointer;
  color: #ED4C67;
  font-size: 2rem;
  background: white;
  padding: 1.1rem;
  margin: 1rem;
  border-radius: 1rem;
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  display: block;
  margin-left: auto;
  margin-right: auto;
  margin-top: 1rem;
}
button:active {
    animation: blur 1s 1;
    transition: 1s;
}

button:hover {
    background-color: black;
    color:#ED4C67;
 }

 .cd-icon {
    color: #ED4C67;
    animation-name:            rotate;
    animation-duration:        4s;
    animation-iteration-count: infinite;
    animation-timing-function: linear;
 }


 @keyframes rotate {
    from {
      transform: rotate(0deg);
    }
    to {
      transform: rotate(360deg);
    }
  }

</style>
