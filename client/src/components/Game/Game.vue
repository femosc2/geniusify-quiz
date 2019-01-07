<template>
  <div>
    <game-info
      :isGenreChoosen="isGenreChoosen"
      :playlist="playlist"
      :song="song"
      :artist="artist"
      :image="image"
      :lyrics="lyrics"
    />
    <button @click="translateLyrics">Btn</button>
    <game-input v-if="isGenreChoosen" :words="words"/>
    
  </div>
</template>

<script>
const axios = require("axios");

import GameInfo from "./GameInfo.vue";
import GameInput from "./GameInput.vue";

export default {
  data: function() {
    return {
      playlist: "",
      song: "",
      artist: "",
      image: "",
      lyrics: "",
      words: "",
      yandexApiKey: "trnsl.1.1.20190107T101715Z.2c54edf18f30a990.dca4f7a48c0246f5640d869c171eb040cae4e585"

    };
  },
  components: {
    GameInfo,
    GameInput
  },
  methods: {
    getSongs() {
      // Function for retrieving the song, artist, image and lyrics
      if (this.isGenreChoosen === "Rock") {
        this.playlist = "http://localhost:9022/rock";
      } else if (this.isGenreChoosen === "Christmas") {
        this.playlist = "http://localhost:9022/christmas";
      } else if (this.isGenreChoosen === "Pop") {
        this.playlist = "http://localhost:9022/pop";
      } else if (this.isGenreChoosen === "Rap") {
        this.playlist = "http://localhost:9022/rap";
      }
      axios
        .get(this.playlist) //get from the chosen playlist
        .then(response => {
          const resultArray = [];
          for (let key in response) {
            //organizes the json file
            resultArray.push(response[key]);
          }
          this.playlist = resultArray;
          this.song = this.playlist[0].Song.song; //Playlist[0] = Object which stores information about song
          this.artist = this.playlist[0].Song.artist;
          this.image = this.playlist[0].Song.image;
          // this.lyrics = this.playlist[0].Song.lyrics
          this.lyrics = "This is some lyrics";
        });
    },
    setSong() {
      //function to automaticlly update the current songs to the front-end
      this.getSongs(); // Chooses a song as soon as it is clicked
      setInterval(() => {
        //Updates every 5 second to see what song is currently selected
        this.getSongs();
      }, 15 * 1000);
    },
    translateLyrics() {
      axios
        .get(
          "https://cors-anywhere.herokuapp.com/https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-sv&text=" +
            this.lyrics +
            "&key=" +
            this.yandexApiKey +
            "&format=plain"
        )
        .then(response => {
          const resultArray = [];
          for (let key in response) {
            // Organizes the JSON file.
            resultArray.push(response[key]);
          }
          let bajs = resultArray[5].response.split("").splice(0, 25);
          console.log(resultArray[5].response.split(""));
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  props: ["isGenreChoosen"],
  created() {
    this.setSong();
  }
};
</script>
