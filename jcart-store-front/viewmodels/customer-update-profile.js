var app = new Vue({
    el: '#app',
    data: {
        username: '',
        realName: '',
        mobile: '',
        email: '',
        mobileVerified: '',
        emailVerified: ''
    },
    mounted() {
        console.log("mounted view");
        this.getMyProfile();
    },
    methods: {
        getMyProfile(){
            axios.get('/customer/getProfile')
            .then(function (response) {
                console.log(response);
                var me = response.data;
                app.username = me.username;
                app.realName = me.realName;
                app.mobile = me.mobile;
                app.mobileVerified = me.mobileVerified;
                app.email = me.email;
                app.emailVerified = me.emailVerified;
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        handleUpdateClick(){
            console.log("update view");
            this.updateMyProfile();
        },
        updateMyProfile(){
            axios.post('/customer/updateProfile', {
                realName: this.realName,
                mobile: this.mobile,
                email: this.email
            })
                .then(function (response) {
                    console.log(response);
                    alert('更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})