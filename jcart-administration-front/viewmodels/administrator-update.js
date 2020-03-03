var app = new Vue({
    el: '#app',
    data: {
        administratorId: '',
        username: '',
        realName: '',
        password: '',
        email: '',
        avatarUrl: '',
        selectPic: '',
        fileList: [],
        status: '',
        statuses: [
            {value: 0, label: '禁用'},
            {value: 1, label: '启用'}
        ]
    },
    mounted(){
        console.log("view mounted");
        var url = new URL(location.href);
        this.administratorId = url.searchParams.get("administratorId");
        if(!this.administratorId){
            alert("administratorId is null");
            return;
        }
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
            axios.get('/administrator/getById',{
                params: {
                    administratorId: this.administratorId
                }
            })
            .then(function (response) {
            console.log(response);
            var administrator = response.data;
            app.username = administrator.username;
            app.password = administrator.password;
            app.realName = administrator.realName;
            app.email = administrator.email;
            app.avatarUrl = administrator.avatarUrl;
            app.status = administrator.status;
            })
            .catch(function (error) {
            console.log(error);
            });
        },
        handleUpdateClick(){
            this.updateAdministrator();
        },
        updateAdministrator(){
            axios.post('/administrator/update', {
                password: this.password,
                realName: this.realName,
                email: this.email,
                avatarUrl: this.avatarUrl,
                status: this.status,
                administratorId: this.administratorId
              })
              .then(function (response) {
                console.log(response);
                alert("修改成功");
              })
              .catch(function (error) {
                console.log(error);
              });
        }
    }
})