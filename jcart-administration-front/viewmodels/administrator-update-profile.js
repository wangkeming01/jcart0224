var app = new Vue({
    el: '#app',
    data: {
        administratorId: '',
        username: '',
        password: '',
        realName: '',
        email: '',
        avatarUrl: '',
        fileList: [],
        selectPic: '',
        createTimeStamp: ''
    },
    mounted(){
        console.log("view mounted");
        this.getAdministratorById();
    },
    methods: {
        handleOnChange(val){
            this.selectPic = val.raw;
        },
        handleUploadClick(){
            this.uploadImage();
        },
        uploadImage(){
            var formData = new FormData();
            formData.append("image", this.selectPic);

            axios.post('/image/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.avatarUrl = response.data;
                    alert('上传成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('上传失败');
                });
        },
        getAdministratorById(){
            axios.get('/administrator/getProfile')
            .then(function (response) {
            console.log(response);
            var administrator = response.data;
            app.username = administrator.username;
            app.realName = administrator.realName;
            app.email = administrator.email;
            app.avatarUrl = administrator.avatarUrl;
            app.administratorId = administrator.administratorId;
            app.createTimeStamp = administrator.createTimeStamp;
            })
            .catch(function (error) {
            console.log(error);
            });
        },
        handleUpdateClick(){
            console.log("view update");
            this.updateAdministrator();
        },
        updateAdministrator(){
            axios.post('/administrator/updateProfile', {
                realName: this.realName,
                email: this.email,
                avatarUrl : this.avatarUrl
              })
              .then(function (response) {
                console.log("更新成功");
              })
              .catch(function (error) {
                console.log(error);
              });
        }

    }
})