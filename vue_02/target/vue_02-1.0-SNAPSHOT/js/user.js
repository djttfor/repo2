var vue = new Vue({
    el: "#app",
    data: {
        user: {id:"",username:"",password:""},
        userList: []
    },
    methods: {
        findAll: function () {
            var _this = this;
            axios.get("/vue_02/user/all").then(function (response) {
                _this.userList = response.data;
                console.log(_this.userList);
            }).catch(function (err) {
                console.log(err);
            });
        },
        findById: function (userid) {
            var _this = this;
            axios.get("/vue_02/user/findById", {
                params: {
                    id: userid
                }
            }).then(function (response) {
              _this.user = response.data;
                $('#myModal').modal("show");
            }).catch(function (err) {
            });

        },
        update: function (user) {
            var _this = this;
            axios.post("/vue_02/user/update",_this.user).then(function (response) {
                _this.findAll();
            }).catch(function (err) {
            });
        }
    },
    created(){
        this.findAll();
    }
});