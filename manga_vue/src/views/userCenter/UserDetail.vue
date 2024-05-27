<template>

  <div class="myDiv">
    <table class="my-table">

      <tr>
        <td class="table-header">昵称</td>
        <td class="table-date">{{userInfo.username}}</td>
      </tr>
      <tr>
        <td class="table-header">UID</td>
        <td class="table-date">{{userInfo.id}}</td>
      </tr>
      <tr>
        <td class="table-header">邮箱</td>
        <td class="table-date">{{userInfo.email}}</td>
      </tr>

      <tr>
        <td class="table-header">生日</td>
        <td class="table-date">{{userInfo.birthday}}</td>
      </tr>

      <tr>
        <td class="table-header">权限</td>
        <td class="table-date">{{userInfo.rolename}}</td>
      </tr>

      <tr>
        <td class="table-header">创建日期</td>
        <td class="table-date">{{userInfo.createdate}}</td>
      </tr>

      <tr>
        <td class="table-header">更新日期</td>
        <td class="table-date">{{userInfo.updatedate}}</td>
      </tr>
      <br>

      <el-button @click="centerDialogVisible = true" id="my-user-editbtn" type="primary" icon="el-icon-edit">修改个人信息</el-button>
    </table>


<!--    修改信息-->
    <el-dialog
        title="信息修改"
        :visible.sync="centerDialogVisible"
        width="50%"
        center>
      <!--  需要提交的表单   -->

      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="头像URL:" prop="avatar">
          <el-input placeholder="请输入头像的URL" v-model="ruleForm.avatar"></el-input>
        </el-form-item>
        <el-form-item label="用户名:" prop="username">
          <el-input placeholder="请输入用户的姓名,必填" v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input placeholder="请输入密码,不填默认不修改原密码" type="password" v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input placeholder="请输入邮箱" v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="ruleForm.birthday"
              style="width: 100%;"
              value-format="yyyy-MM-dd"
              @change="getBirthDate"
          ></el-date-picker>
        </el-form-item>

<!--        <el-form-item>-->
<!--          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>-->
<!--          <el-button @click="resetForm('ruleForm')">重置</el-button>-->
<!--        </el-form-item>-->

      </el-form>
      <!--  这是内容分割线，上面是内容，下面是两个按钮，不可缺少    -->
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">返 回</el-button>
    <el-button type="primary" @click="submitForm('ruleForm')">提 交</el-button>
  </span>
    </el-dialog>

  </div>

</template>

<script>
import Aside from '../manga/Aside'
export default {
  name: "UserDetail",
  data() {
    return {
      userInfo: {
        id: 0,
        username: "",
        birthday: "",
        email: "",
        avatar: "",
        rolename: "",
        createdate: "",
        updatedate: "null",
      },
      centerDialogVisible: false,
      ruleForm: {
        username: '',
        password: '',
        birthday: '',
        email: '',
        avatar:'',
      },
      rules: {
        // username: [
        //   {required: true, message: '请输入用户名', trigger: 'blur'},
        //   {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        // ],
        // password: [
        //   {required: true, message: '请输入密码', trigger: 'blur'},
        //   {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        // ],
        // birthday: [
        //   { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
        // ],
        // email: [
        //   {required: true, message: '请输入邮箱地址', trigger: 'blur'},
        //   {min: 6, max: 30, message: '长度在 6 到 30 个字符', trigger: 'blur'}
        // ],
        // avatar: [
        //   {required: true, message: '请输入头像的url', trigger: 'blur'},
        //   {min: 6, max: 100, message: '长度在 6 到 100 个字符', trigger: 'blur'}
        // ],
      }
    }
  },
  created() {
    this.request.get("/userCenter/info").then(res => {
      // console.log(res.data)
      this.userInfo = res.data
      this.ruleForm = res.data
    })
  },
  methods: {
    getBirthDate (date) {
      this.ruleForm.birthday = date
    },
    // 发送编辑用户信息的内容
    submitForm(formName){
      const _this = this
      this.$prompt('请输入当前密码', '验证您的账户', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputType:"password",
        // 下面这行代码是关于邮箱格式验证的
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '密码错误'
      }).then(({ value }) => {
        // 发送post请求
        _this.request.post("/userCenter/userAuthentication",value).then(res => {
          // 如果发送成功
          console.log(this.ruleForm.birthday)
          if (res.code == 200 ){
            // 走到这一步说明验证成功,继续发送post请求修改用户内容
            _this.request.post("/userCenter/update",this.ruleForm).then(res => {
              if (res.code == 200){
                // 如果返回了正常的数据,弹出错误提示,并返回登录界面
                _this.request.post("/user/logout").then(res => {
                  // console.log(res)
                  sessionStorage.clear()
                  localStorage.clear()
                  _this.$store.commit("RESET_STORE")
                  _this.$message.success(res.msg)
                  _this.$router.push("/login")
                })
                _this.$message({
                  type: 'success',
                  message: '修改成功!请重新登录!'
                });

              }else{
                // 修改用户数据失败，弹出提示
                _this.$message({
                  type: 'error',
                  message: res.msg,
                });
              }
            })
          }else{
            _this.$message({
              type: 'error',
              message: '修改失败,密码错误'
            });
          }
        })
      });
    }
  }
}
</script>

<style scoped>
.my-table {
  margin: 20px auto;
  width: 900px;
}
.table-header {
  display: inline-block;
  font-size: 24px;
  color: #444;
  width: 140px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.table-date {
  display: inline-block;
  align-items: center;
  margin-top: 4px;
  margin-left: 50px;
  color: #666;
}
tr:nth-child(odd) {
  background: #f1f1f1;
}
.myDiv {
  /*background-image: linear-gradient(120deg, #8ec5fc 0%,#e0c3fc  100%);*/
}
#my-user-editbtn {
  display: block;
  text-align: center;
  margin: 5px auto;
}
</style>