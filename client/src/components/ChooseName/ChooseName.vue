<template>
    <div>
        <h1>Welcome to Geniusify-Quiz</h1>
        <input type="text" v-model="name" placeholder="What's your name?" required>
        <button @click="submit">SUBMIT</button>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            name: null,
        }
    },
    methods: {
        submit() {
            this.$emit("nameChoosen", this.name)
        }
    }
}
</script>

<style>

</style>
