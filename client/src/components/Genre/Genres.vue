<template>
    <div>
        <button v-for="genre in genres" :key="genre.id" @click="chooseGenre(genre.genreName)"> {{genre.genreName}}</button>
    </div>
</template>

<script>

export default {
    data: function() {
        return {
        genres: [
            {genreName: "Rock", id: 1},
            {genreName: "Pop", id: 2},
            {genreName: "Rap", id: 3},
            {genreName: "Christmas", id: 4}
            ],
        genreChosen: null
        }
    },
    methods: {
        chooseGenre(genre) {
            this.genreChosen = genre;
            console.log(genre);
            this.$emit("genreChoosen", this.genreChosen)
        }
    }
}
</script>

<style>

</style>

