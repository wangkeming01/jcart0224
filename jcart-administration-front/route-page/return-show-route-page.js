const ReturnShowRoutePage = {
    template: `<div id="this">
    <el-divider>退货基本信息</el-divider>
    退货Id：{{returnId}} <br>
    订单Id：{{orderId}} <br>
    订单时间：{{orderTimestamp}} <br>
    客户Id：{{customerId}} <br>
    客户姓名：{{customerName}} <br>
    手机：{{mobile}} <br>
    邮箱：{{email}} <br>
    状态：{{status}} <br>
    处理方式：{{action}} <br>
    <br>

    <el-divider>退货商品信息</el-divider>
    商品代号：{{productCode}} <br>
    商品名称：{{productName}} <br>
    数量：{{quantity}} <br>
    原因：{{reason}} <br>
    是否开封：{{opened}} <br>
    备注：{{comment}} <br>
    申请时间：{{createTimestamp}} <br>
    更新时间：{{updateTimestamp}} <br>
    <br>

    <el-select v-model="selectedAction" placeholder="请选择处理方式">
        <el-option v-for="item in actions" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
    </el-select>

    <el-button type="primary" @click="handleUpdateAction">修改处理方式</el-button>
</div>
`,
    data() {
        return {
            returnId: '',
            orderId: '',
            orderTimestamp: '',
            customerId: '',
            customerName: '',
            mobile: '',
            email: '',
            status: '',
            action: '',
            productCode: '',
            productName: '',
            quantity: '',
            reason: '',
            opened: '',
            comment: '',
            createTimestamp: '',
            updateTimestamp: '',
            actions: [
                { value: 0, label: '退货' },
                { value: 1, label: '换货' },
                { value: 2, label: '修理' }
            ],
            selectedAction: ''
        }

    },
    mounted() {
        console.log('view mounted');

        this.returnId = this.$route.params.returnId;
        if (!this.returnId) {
            alert('returnId is null');
            return;
        }

        this.getReturnById();
    },
    methods: {
        handleUpdateAction() {
            console.log('update action click');
            this.updateReturnAction();
        },
        updateReturnAction() {
            axios.post('/return/updateAction', {
                returnId: this.returnId,
                action: this.selectedAction
            })
                .then((response) => {
                    console.log(response);
                    alert('处理方式更新成功');
                    this.getReturnById();
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getReturnById() {
            axios.get('/return/getById', {
                params: {
                    returnId: this.returnId
                }
            })
                .then((response) => {
                    console.log(response);
                    var aReturn = response.data;
                    this.orderId = aReturn.orderId;
                    this.orderTimestamp = aReturn.orderTimestamp;
                    this.customerId = aReturn.customerId;
                    this.customerName = aReturn.customerName;
                    this.mobile = aReturn.mobile;
                    this.email = aReturn.email;
                    this.status = aReturn.status;
                    this.action = aReturn.action;
                    this.selectedAction = aReturn.action;
                    this.productCode = aReturn.productCode;
                    this.productName = aReturn.productName;
                    this.quantity = aReturn.quantity;
                    this.reason = aReturn.reason;
                    this.opened = aReturn.opened;
                    this.comment = aReturn.comment;
                    this.createTimestamp = aReturn.createTimestamp;
                    this.updateTimestamp = aReturn.updateTimestamp;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
}