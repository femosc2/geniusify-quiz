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
            {genreName: "Rock", id: 1, image: "https://i.imgur.com/c4c59v5.jpg"},
            {genreName: "Pop", id: 2, image: "https://i.imgur.com/EynXNTa.jpg"},
            {genreName: "Rap", id: 3, image: "https://i.imgur.com/uFrYkAd.png"},
            {genreName: "Christmas", id: 4, image: "https://i.imgur.com/objKAmZ.jpg"}
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

