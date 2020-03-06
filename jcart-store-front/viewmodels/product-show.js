var app = new Vue({
    el: '#app',
    data: {
        productId: '',
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        description: '',
        stockQuantity: '',
        mainPicUrl: '',
        otherPicUrls: [],
        myShoppingCart: [],
        quantity: 1
    },
    computed: {
        otherPicUrlsJson(){
            return this.otherPicUrls.toString();
        }
    },
    mounted(){
       var url =  new URL(location.href);
       this.productId = url.searchParams.get("productId");
       if(!this.productId){
        alert('productId is null');
        return;
       }
       this.getProductById();
    },
    methods: {
        handleAddToCartClick(){
            var myShoppingCartJson = localStorage['myShoppingCartJson'];
            this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
            var cartProduct = this.myShoppingCart.find(p => p.productId === this.productId);
            if(cartProduct){
                var originQuantity = parseInt(cartProduct.productId);
                var addQuantity = parseInt(this.quantity);
                cartProduct.quantity = originQuantity + addQuantity;
            }else{
                cartProduct = {
                    productId : this.productId,
                    productCode : this.productCode,
                    productName: this.productName,
                    mainPicUrl: this.mainPicUrl,
                    unitPrice: this.price,
                    discount: this.discount,
                    quantity: this.quantity
                }
                this.myShoppingCart.push(cartProduct);
            }
            localStorage['myShoppingCartJson'] = JSON.stringify(this.myShoppingCart);
            this.$message.success('添加购物车成功');
        },
        getProductById(){
            axios.get('/product/getById', {
                params: {
                    productId: this.productId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var product = response.data;
                    app.productId = product.productId;
                    app.productCode = product.productCode;
                    app.productName = product.productName;
                    app.price = product.price;
                    app.discount = product.discount;
                    app.mainPicUrl = product.mainPicUrl;
                    app.otherPicUrls = product.otherPicUrls;
                    app.description = product.description;
                    app.stockQuantity = product.stockQuantity;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})