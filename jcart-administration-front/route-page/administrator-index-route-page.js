const AdministratorIndexRoutePage = {
    template: `
    <div id="app">
    <el-button type="danger" @click="handleBatchDeleteClick">批量删除</el-button>
    <el-button type="primary" @click="handleCreateClick">添加</el-button>
    <el-table :data="pageInfo.list" style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column prop="administratorId" label="Id">
        </el-table-column>
        <el-table-column prop="username" label="用户名">
        </el-table-column>
        <el-table-column prop="realName" label="姓名">
        </el-table-column>
        <el-table-column label="状态">
            <template slot-scope="scope">
                {{statuses[scope.row.status]}}
            </template>
        </el-table-column>
        <el-table-column label="创建时间">
            <template slot-scope="scope">
                {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
    </el-pagination>
</div>
`,
    data() {
        return {
            pageInfo: '',
            pageNum: 1,
            selectedAdministrators: [],
            statuses: ['禁用', '启用']
        }

    },
    computed: {
        selectedAdministratorIds() {
            return this.selectedAdministrators.map(a => a.administratorId);
        }
    },
    mounted() {
        console.log(" mounted view");
        this.getAdministrators();
    },
    methods: {
        handleCreateClick() {
            this.$router.push('/administrator/create');
        },
        handleEdit(index, row) {
            this.$router.push('/administrator/update/' + row.administratorId);
        }
        ,
        getAdministrators() {
            axios.get('/administrator/getList', {
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
        },
        handleBatchDeleteClick() {
            console.log('batchDelete click');

            if (confirm("确认删除？")) {
                this.batchDeleteAdministrator();
            }
        },
        handleSelectionChange(val) {
            this.selectedAdministrators = val;
        },
        handlePageChange(val) {
            this.pageNum = val;
            this.getAdministrators();
        },
        handleDelete(index, row) {
            console.log('delete click');

            if (confirm("确认删除？")) {
                this.deleteAdministrator(row.administratorId);
            }
        },
        deleteAdministrator(administratorId) {
            axios.post('/administrator/delete', administratorId, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then((response) => {
                    console.log(response);
                    alert('删除成功');
                    location.reload();
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        batchDeleteAdministrator() {
            axios.post('/administrator/batchDelete', this.selectedAdministratorIds)
                .then((response) => {
                    console.log(response);
                    alert('批删成功');
                    location.reload();
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
}