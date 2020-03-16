const AdministratorUpdateRoutePage = {
    template: `<div id="this">
    <el-input v-model="username" placeholder="请输入用户名" readonly></el-input>
    <el-input v-model="password" type="password" placeholder="请输入密码"></el-input>
    <el-input v-model="realName" placeholder="请输入姓名"></el-input>
    <el-input v-model="email" placeholder="请输入邮箱"></el-input>
    <el-upload ref="upload" action="" :http-request="uploadImage" :limit="1" accept="image/*"
      :auto-upload="false" :on-change="handleOnChange" :file-list="fileList">
      <el-button slot="trigger" size="small" type="primary">选取头像</el-button>
      <el-button size="small" type="success" @click="handleUploadClick">上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
        </el-upload>
        上传后头像：<el-link type="primary">{{avatarUrl}}</el-link>
        <br>

    <el-select v-model="status" placeholder="请选择">
        <el-option
          v-for="item in statuses"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    <el-button type="primary" @click="handleUpdateClick">更新</el-button>
</div>`,
    data() {
        return {
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
                { value: 0, label: '禁用' },
                { value: 1, label: '启用' }
            ]
        }

    },
    mounted() {
        console.log("view mounted");
        // var url = new URL(location.href);
        // this.administratorId = url.searchParams.get("administratorId");
        this.administratorId = this.$route.params.administratorId;
        if (!this.administratorId) {
            alert("administratorId is null");
            return;
        }
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
            axios.get('/administrator/getById', {
                params: {
                    administratorId: this.administratorId
                }
            })
                .then((response) => {
                    console.log(response);
                    var administrator = response.data;
                    this.username = administrator.username;
                    this.password = administrator.password;
                    this.realName = administrator.realName;
                    this.email = administrator.email;
                    this.avatarUrl = administrator.avatarUrl;
                    this.status = administrator.status;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handleUpdateClick() {
            this.updateAdministrator();
        },
        updateAdministrator() {
            axios.post('/administrator/update', {
                password: this.password,
                realName: this.realName,
                email: this.email,
                avatarUrl: this.avatarUrl,
                status: this.status,
                administratorId: this.administratorId
            })
                .then((response) => {
                    console.log(response);
                    alert("修改成功");
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
}