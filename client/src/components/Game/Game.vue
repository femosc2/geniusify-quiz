<template>
    <div>
        <game-info :isGenreChoosen="isGenreChoosen" />
        <game-input v-if="isGenreChoosen" />
    </div>
    
</template>

<script>
import GameInfo from "./GameInfo.vue";
import GameInput from "./GameInput.vue";

export default {
    data: function() {
        return {

        }
    },
    components: {
        GameInfo,
        GameInput
    },
    methods: {
        
    },
    props: ["isGenreChoosen"]
}
</script>
