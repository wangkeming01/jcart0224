const CustomerSearchRoutePage = {
    template: `<div id="app">
    <el-table :data="pageInfo.list" style="width: 100%">
        <el-table-column prop="username" label="客户用户名">
        </el-table-column>
        <el-table-column prop="realName" label="客户姓名">
        </el-table-column>
        <el-table-column prop="mobile" label="手机">
        </el-table-column>
        <el-table-column prop="email" label="邮箱">
        </el-table-column>
        <el-table-column label="状态">
            <template slot-scope="scope">
                <el-select v-model="scope.row.status" placeholder="请选择状态">
                    <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
            </template>
        </el-table-column>
        <el-table-column prop="createTimestamp" label="注册日期">
            <template slot-scope="scope">
                {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button type="primary" size="mini" @click="handleUpdateStatus(scope.$index,scope.row)">更新状态</el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
    </el-pagination>
</div>`,
    data() {
        return {
            pageInfo: '',
            pageNum: 1,
            statuses: [
                { value: 0, label: '禁用' },
                { value: 1, label: '启用' },
                { value: 2, label: '不安全' }
            ]
        }
    },
    mounted() {
        console.log('view mounted');
        this.searchCustomer();
    },
    methods: {
        searchCustomer() {
            axios.get('/customer/search', {
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
        handlePageChange(val) {
            this.pageNum = val;
            this.searchCustomer();
        },
        handleUpdateStatus(index, row) {
            console.log('update status click');
            this.updateCustomerStatus(row.customerId, row.status);
        },
        updateCustomerStatus(customerId, status) {
            axios.post('/customer/setStatus', {
                customerId: customerId,
                status: status
            })
                .then((response) =>  {
                    console.log(response);
                    alert('状态更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
}