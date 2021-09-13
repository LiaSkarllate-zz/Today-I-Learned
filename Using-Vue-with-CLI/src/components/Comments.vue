<template>
    <div class="container">
        <h1>Comments</h1>
        <hr/>
            <FormNewComment
                v-on:added-comment="addComment"/>
        <hr/>

        <div class="list-group">
            <p v-if="comments.length <= 0">No comments</p>

            <div 
                class       ="list-group-item" 
                v-for       ="(comment, index) in allComments" 
                v-bind:key  ="index">

                <span class="comment_author">
                    <strong>Author:</strong> {{comment.name}}
                </span>

                <p><strong>Message:</strong> {{comment.message}}</p>

                <a  
                    href                ="#"
                    title               ="Remove"
                    v-on:click.prevent  ="removeComment(index)">Remove</a>
            </div>
        </div>  
    </div>
</template>

<script>
    import FormNewComment from './FormNewComment'

    export default {
        components: {
            FormNewComment
        },

        data(){
            return{
                comments: [],
            }
        },

        methods: {
            addComment(new_comment){
                this.comments.push(new_comment);
                return;
            },
            removeComment(index){
                this.comments.splice(index, 1);
            }
        },

        computed: {
            allComments(){
                return this.comments.map(comment => ({
                    ...comment,
                    name: comment.name.trim() === '' ? 'Anonymous' : comment.name
                }))
            }
        },
        
        watch: {
            comments(val){
                console.log('val', val);
            }
        }
    }
</script>