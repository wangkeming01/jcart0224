const RetuenSearchRoutePage = {
    template: `<div id="app">
    <el-table :data="pageInfo.list" style="width: 100%">
        <el-table-column prop="returnId" label="退货Id">
        </el-table-column>
        <el-table-column prop="orderId" label="订单Id">
        </el-table-column>
        <el-table-column prop="customerName" label="客户姓名">
        </el-table-column>
        <el-table-column prop="productCode" label="商品代号">
        </el-table-column>
        <el-table-column prop="productName" label="商品名称">
        </el-table-column>
        <el-table-column prop="status" label="状态">
        </el-table-column>
        <el-table-column prop="createTimestamp" label="申请日期">
        </el-table-column>
        <el-table-column prop="updateTimestamp" label="修改日期">
        </el-table-column>
    </el-table>

    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
    </el-pagination>
</div>`,
    data() {
        return {
            pageInfo: '',
            pageNum: 1
        }

    },
    mounted() {
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
                .then((response) =>  {
                    console.log(response);
                    this.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
}