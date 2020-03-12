var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1,
        statuses: [
          { value: 0, label: '下架'},
          { value: 1, label: '上架'},
          { value: 2, label: '待审核'},
        ]
    },
    mounted(){
        console.log("view mounted");
        this.searchProduct();
    },
    methods: {
        searchProduct() {
            axios.get('/product/search', {
                params: {
                  pageNum : this.pageNum
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
        handlePageChange(val){
            this.pageNum = val;
            this.searchProduct(); 
        }
    }
})