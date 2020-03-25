var app = new Vue({
    el: '#app',
    data: {
        email: '',
        loading: false,
        count: 60,
        buttonEnabled: true
    },
    methods: {
        handleFindBackPwdClick(){
            console.log('find back pwd click');
            this.getPwdResetCode();
            this.loading = true;
            this.buttonEnabled = false;
            this.count = 60;

            setInterval(function(){
                app.count--;
                if(app.count < 1 ){
                    app.buttonEnabled = true;
                }
            }, 1000);
        },
        getPwdResetCode(){
            axios.get('/administrator/getPwdResetCode', {
                params: {
                    email: this.email
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.loading = false;
                    alert('重置码已发送到邮箱');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})