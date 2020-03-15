Vue.component('jc-product-search-page', {

    template: `<div id="app">
    <el-table :data="pageInfo.list" style="width: 100%">
        <el-table-column  label="主图"width="180">
          <template slot-scope="scope">
            <el-image style="width: 100px; height: 100px" :src="scope.row.mainPicUrl" fit="fill"></el-image>
        </template>
        </el-table-column>
        <el-table-column prop="productCode" label="商品码"width="180">
        </el-table-column>
        <el-table-column prop="productName" label="商品名称"width="180">
        </el-table-column>
        <el-table-column  label="价格"width="180">
          <template slot-scope="scope">
           <s> {{scope.row.price}}</s><br>
           {{(scope.row.price * scope.row.discount).toFixed(2)}}
          </template>
        </el-table-column>
        <!-- <el-table-column prop="discount" label="折扣" width="180">
        </el-table-column> -->
        <el-table-column prop="stockQuantity" label="库存" width="180">
        </el-table-column>
        <el-table-column prop="status" label="状态" width="180">
          <template slot-scope="scope">
            {{statuses[scope.row.status].label}}
        </template>
        </el-table-column>
    
      
    
    
      </el-table>
    
      <el-pagination :page-size="pageInfo.pageSize" layout="prev, pager, next" :total="pageInfo.total" 
      @current-change="handlePageChange">
    </el-pagination>
     
    </div>
    `,
    data() {
        return {
            pageInfo: '',
            pageNum: 1,
            statuses: [
                { value: 0, label: '下架' },
                { value: 1, label: '上架' },
                { value: 2, label: '待审核' },
            ]
        }

    },
    mounted() {
        console.log("view mounted");
        this.searchProduct();
    },
    methods: {
        searchProduct() {
            axios.get('/product/search', {
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
        handlePageChange(val) {
            this.pageNum = val;
            this.searchProduct();
        }
    }
})