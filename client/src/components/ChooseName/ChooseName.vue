<template>
    <div>
        <h1>Welcome to Geniusify-Quiz</h1>
        <input type="text" v-model="name">
        <button @click="submit">SUBMIT</button>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            name: null,
            isNameChoosen: false
        }
    },
    methods: {
        submit() {
            this.isNameChoosen = true
            this.$emit("nameChoosen", this.name, this.isNameChoosen)
        }
    }
}
</script>

<style>

</style>
