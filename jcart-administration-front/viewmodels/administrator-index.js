var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1,
        selectedAdministrators: [],
        statuses: ['禁用','启用']
    },
    computed: {
        selectedAdministratorIds() {
            return this.selectedAdministrators.map(a => a.administratorId);
        }
    },
    mounted(){
        console.log(" mounted view");
        this.getAdministrators();
    },
    methods: {
        getAdministrators(){
            axios.get('/administrator/getList', {
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
        handleBatchDeleteClick(){
            console.log('batchDelete click');

            if(confirm("确认删除？")){
                this.batchDeleteAdministrator();
            }
        },
        handleSelectionChange(val){
            this.selectedAdministrators = val;
        },
        handlePageChange(val){
            this.pageNum = val;
            this.getAdministrators();
        },
        handleDelete(index,row){
            console.log('delete click');

            if(confirm("确认删除？")){
                this.deleteAdministrator(row.administratorId);
            }
        },
        deleteAdministrator(administratorId){
            axios.post('/administrator/delete', administratorId, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(function (response) {
                    console.log(response);
                    alert('删除成功');
                    location.reload();
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        batchDeleteAdministrator(){
            axios.post('/administrator/batchDelete', this.selectedAdministratorIds)
            .then(function (response) {
                console.log(response);
                alert('批删成功');
                location.reload();
            })
            .catch(function (error) {
                console.log(error);
            });
        }
    }
})