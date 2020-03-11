var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1
    },
    mounted(){
        this.searchReturn();
    },
    methods: {
        handlePageChange(val) {
            this.pageNum = val;
            this.searchReturn();
        },
        searchReturn() {
            axios.get('/return/search', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})