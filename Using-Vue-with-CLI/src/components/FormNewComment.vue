<template>
    <div class="form-todo form-group">
        <p><input 
                placeholder ="name" 
                type        ="text" 
                class       ="form-control"
                v-model     ="name"></p>

        <p><textarea 
                placeholder ="your comment"
                type        ="text" 
                class       ="form-control"
                v-model     ="message"></textarea></p>

        <button
                type        ="submit" 
                class       ="btn btn-primary"
                v-on:click  ="addComment">Comment</button>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                name: '',
                message: ''
            }
        },
        
        methods: {
            addComment(){
                if(this.message.trim() === ''){
                    console.log("Bad comment.");
                }else{
                    this.$emit('added-comment', {
                        name: this.name,
                        message: this.message
                    });

                    this.name = '';
                    this.message = '';
                }
                return;
            }
        }
    }
</script>