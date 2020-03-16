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
        <template slot-scope="scope">
            {{statuses[scope.row.status].label}}
        </template>
    </el-table-column>
    <el-table-column label="申请日期">
        <template slot-scope="scope">
            {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
        </template>
    </el-table-column>
    <el-table-column label="修改日期">
        <template slot-scope="scope">
            {{(new Date(scope.row.updateTimestamp)).toLocaleString()}}
        </template>
    </el-table-column>
    <el-table-column label="操作">
        <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleEditClick(scope.$index, scope.row)">编辑</el-button>
        </template>
    </el-table-column>
    </el-table>

    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
    </el-pagination>
</div>`,
    data() {
        return {
            pageInfo: '',
            returnId: '',
            orderId: '',
            customerName: '',
            productCode: '',
            productName: '',
            selectedStatus: '',
            statuses: [
                { value: 0, label: '待处理' },
                { value: 1, label: '待取货' },
                { value: 2, label: '正在处理' },
                { value: 3, label: '完成' },
                { value: 4, label: '拒绝' }
            ],
            startTime: '',
            endTime: '',
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
        handleEditClick(index, row) {
            this.$router.push('/return/edit/' + row.returnId + '/show');
        },
        searchReturn() {
            axios.get('/return/search', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then((response) => {
                    console.log(response);
                    this.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
}