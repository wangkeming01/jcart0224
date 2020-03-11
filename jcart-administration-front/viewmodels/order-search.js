var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1
    },
    mounted(){
        console.log('view mounted');
        this.searchOrder();
    },
    methods: {
       
        searchOrder() {
            axios.get('/order/search', {
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
        },
        handleChangePage(val){
            this.pageNum = val;
            this.searchOrder();
        }
    }
})