const AdministratorUpdateProfileRoutePage = {
    template: `<div id="this">
    <el-input v-model="username" readonly placeholder="请输入用户名"></el-input>
    <el-input v-model="realName" placeholder="请输入姓名"></el-input>
    <el-input v-model="email" placeholder="请输入邮箱"></el-input>
    创建时间：{{(new Date(createTimeStamp)).toLocaleString()}}
    <el-upload ref="upload" action="" :http-request="uploadImage" :limit="1" accept="image/*"
      :auto-upload="false" :on-change="handleOnChange" :file-list="fileList">
      <el-button slot="trigger" size="small" type="primary">选取头像</el-button>
      <el-button size="small" type="success" @click="handleUploadClick">上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
        </el-upload>
        上传后头像：<el-link type="primary">{{avatarUrl}}</el-link>
        <br>

    <el-button type="primary" @click="handleUpdateClick">更新</el-button>
</div>`,
    data() {
        return {
            administratorId: '',
            username: '',
            password: '',
            realName: '',
            email: '',
            avatarUrl: '',
            fileList: [],
            selectPic: '',
            createTimeStamp: ''
        }

    },
    mounted() {
        console.log("view mounted");
        this.getAdministratorById();
    },
    methods: {
        handleOnChange(val) {
            this.selectPic = val.raw;
        },
        handleUploadClick() {
            this.uploadImage();
        },
        uploadImage() {
            var formData = new FormData();
            formData.thisend("image", this.selectPic);

            axios.post('/image/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then((response) => {
                    console.log(response);
                    this.avatarUrl = response.data;
                    alert('上传成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('上传失败');
                });
        },
        getAdministratorById() {
            axios.get('/administrator/getProfile')
                .then((response) => {
                    console.log(response);
                    var administrator = response.data;
                    this.username = administrator.username;
                    this.realName = administrator.realName;
                    this.email = administrator.email;
                    this.avatarUrl = administrator.avatarUrl;
                    this.administratorId = administrator.administratorId;
                    this.createTimeStamp = administrator.createTimeStamp;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handleUpdateClick() {
            console.log("view update");
            this.updateAdministrator();
        },
        updateAdministrator() {
            axios.post('/administrator/updateProfile', {
                realName: this.realName,
                email: this.email,
                avatarUrl: this.avatarUrl
            })
                .then((response) => {
                    console.log("更新成功");
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    }
}