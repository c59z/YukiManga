<template>
  <div id="login-menu">
    <!--    登录界面的标题-->
    <div id="login-div">
      <div class="login-title">
        <el-image
            :src="title_img"
        >
        </el-image>
      </div>
      <!--  登录界面的表单   -->
      <div class="login-form">

        <!--        用户名-->
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <div class="login-input">
<!--            <el-form-item prop="name">-->
              <el-input
                  prefix-icon="el-icon-user-solid"
                  placeholder="请输入用户名:"
                  class="login-input"
                  v-model="ruleForm.username"
              ></el-input>

<!--            </el-form-item>-->
<!--            <el-form-item prop="password">-->
              <el-input
                  prefix-icon="el-icon-user-solid"
                  placeholder="请输入密码:"
                  class="login-input"
                  type="password"
                  v-model="ruleForm.password">
              </el-input>
<!--            </el-form-item>-->

            <el-link class="login-a" @click="toRegister()" type="primary">没有账户?现在注册</el-link>
          </div>
          <el-button class="btn_login" type="primary" @click="submitForm('ruleForm')">用户登录</el-button>
        </el-form>

      </div>
    </div>
  </div>
</template>

<script>


export default {
  name: "Login",
  data() {
    return {
      title_img: require("../assets/image/Welcome.png"),
      ruleForm: {
        username: '',
        password: '',
      },
      rules: {
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.request.post("/user/login",this.ruleForm).then(res => {
            if (res.code != 200){
              this.$message.error(res.msg);
            }else{
              var token = res.data.token
              _this.$store.commit("SET_TOKEN",token)
              this.$message.success(res.msg)
              this.$router.push("/")
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    toRegister(){
      this.$router.push("/register")
    },
    create() {
      var a = document.getElementsByClassName("el-form-item__content")
      console.log(a)
    }
  }
}
</script>

<style scoped>

#login-div {
  width: 960px;
  max-height: 400px;
  margin: 15vh auto;
  background-color: rgba(255,255,255,70%);
  /*background-image: linear-gradient(120deg, #8ec5fc 0%,#e0c3fc  100%);*/
  border-radius: 1.5rem;
  /*background-color: skyblue;*/
}

/*.login-input .el-form-item  {*/
/*  margin-left: 0px !important;*/
/*}*/

.el-form-item__content{
  margin-left: 0 !important;
}


.login-title {
  text-align: center;
  margin: 10px auto;
}

#login-menu {
  width: 100%;
  height: 100vh;
  background-size: 100% 100%;
  background-image: url("../assets/image/bgc.jpg");
  overflow: auto;
}

.login-form {
  width: 960px;
  min-height: 500px;
  /*background-color: pink;*/
}
.login-input {
  margin: 20px auto;
  max-width: 400px;
  display: block;
}
.login-button-group {
  display: inline-block;
  text-align: center;
  margin: 20px auto;
}
.login-a {
  width: 102px;
  display: block;
  font-size: 12px;
  text-decoration: none;
  /*color: #28c8fc;*/
}
.btn_login {
  display: block;
  margin: 20px auto;
  /*background-color: #79c471;*/
}

</style>