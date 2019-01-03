<template>
    <div>
        <genre-list @genreSelected="genreChoosen" :genres="genres" />
    </div>
</template>

<script>
import GenreList from "./GenresList.vue";

export default {
    data: function() {
        return {
        genres: [
            {genreName: "Rock", id: 1},
            {genreName: "Pop", id: 2},
            {genreName: "Rap", id: 3},
            {genreName: "Christmas", id: 4}
            ],
        genreChosen: null,
        isGenreChoosen: null
        }
    },
    components: {
        GenreList
    },
    methods: {
        genreChoosen(genreSelected) {
            this.isGenreChoosen = genreSelected;
            console.log("app state " + this.isGenreChoosen);
            this.$emit("genreSelected" , this.isGenreChoosen)
    },      

}
}
</script>

<style scoped>
</style>

