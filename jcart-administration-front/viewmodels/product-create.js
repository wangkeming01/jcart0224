var app = new Vue({
    el: '#app',
    data: {
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        stockQuantity: '',
        rewordPoints: '',
        description: '',
        sortOrder: '',
        selectedStatus: '',
        statuses: [
          
           { value: 0, label: '下架' },
            { value: 1, label: '上架' },
            { value: 2, label: '待审核' }
        ],
        mainPicUrl: '',
        otherPicUrls: [],
        selectedOtherPics: [],
        productAbstract: '',
        mainFileList: [],
        otherFileList: [],
        selectedMainPic: '',
        selectedOtherPics: []
    },
    methods: {
        handleCreateProduct(){
            console.log("create product");
            this.createProduct();
        },
        handleOnMainChange(val){
            this.selectedMainPic = val.raw;
        },
        handleOnOtherChange(file,fileList){
            this.selectedOtherPics = fileList;
        },
        handleOnOtherRemove(file,fileList){
            this.selectedOtherPics = fileList;
        },
        handleUploadOtherClick(){
            this.uploadOtherImage();
        },
        handleUploadMainClick(){
            this.uploadMainImage();
        },
        createProduct(){
            axios.post('/product/create', {
                productCode: this.productCode,
                productName: this.productName,
                price: this.price,
                discount: this.discount,
                stockQuantity: this.stockQuantity,
                rewordPoints: this.rewordPoints,
                description: this.description,
                sortOrder: this.sortOrder,
                status:this.selectedStatus,
                otherPicUrls: this.otherPicUrls,
                mainPicUrl: this.mainPicUrl,
                productAbstract: this.productAbstract
              })
              .then(function (response) {
                console.log(response);
                alert("创建成功");
              })
              .catch(function (error) {
                console.log(error);
              });
        },
        uploadMainImage() {
            var formData = new FormData();
            formData.append("image", this.selectedMainPic);

            axios.post('/image/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.mainPicUrl = response.data;
                    alert('上传成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('上传失败');
                });
        },
        uploadOtherImage(){
            this.selectedOtherPics.forEach(pic => {
                var formData = new FormData();
                formData.append("image", pic.raw);

                axios.post('/image/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                })
                    .then(function (response) {
                        console.log(response);
                        var url = response.data;
                        app.otherPicUrls.push(url);
                        alert('上传成功');
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert('上传失败');
                    });
            });
        }
    }
})