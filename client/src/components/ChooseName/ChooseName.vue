<template>
    <div>
        <h1>Welcome to Geniusify-Quiz</h1>
        <h2>Choose your name!</h2>
        <input type="text" v-model="name" v-on:keydown.enter="submit">
        <button @click="submit">SUBMIT</button>
    </div>
</template>

<script>
export default {
  data: function() {
    return {
      name: null
    };
  },
  methods: {
    submit() {
      this.$emit("nameChoosen", this.name);
    }
  }
};
</script>

<style>
input {
  border: none;
  background-color: rgba(255, 255, 255, 0.3);
  color: white;
  width: 50%;
  height: 100px;
  font-size: 80px;
  text-align: center;
  -webkit-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
  box-shadow: 0px 0px 30px -2px rgba(0, 0, 0, 0.5);
}
button {
  color: #0b8793;
  font-size: 2rem;
  background: white;
  padding: 1.3rem;
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
    
 }
</style>
