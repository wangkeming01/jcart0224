const OrderSearchRoutePage = {
    template: `<div id="app">
    <el-table :data="pageInfo.list" style="width: 100%">
        <el-table-column prop="orderId" label="订单Id">
        </el-table-column>
        <el-table-column prop="customerName" label="客户姓名">
        </el-table-column>
        <el-table-column prop="status" label="状态">
        </el-table-column>
        <el-table-column prop="totalPrice" label="总价">
        </el-table-column>
        <el-table-column label="下单时间">
            <template slot-scope="scope">
                {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
            </template>
        </el-table-column>
        <el-table-column label="修改时间">
            <template slot-scope="scope">
                {{(new Date(scope.row.updateTimestamp)).toLocaleString()}}
            </template>
        </el-table-column>
    </el-table>

    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handleChangePage">
    </el-pagination>
</div>`,
    data() {
        return {
            pageInfo: '',
            pageNum: 1
        }

    },
    mounted() {
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
                .then((response) =>  {
                    console.log(response);
                    this.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handleChangePage(val) {
            this.pageNum = val;
            this.searchOrder();
        }
    }
}