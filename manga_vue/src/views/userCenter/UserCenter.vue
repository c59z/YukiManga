<template>
  <div class="my-center">
    <!--  用户信息的头部  -->
    <div class="my-userinfo">
      <el-avatar class="my-avatar" :size="75" shape="circle" :src="userinfo.avatar"></el-avatar>
      <span class="my-username" >{{userinfo.username}}</span>
    </div>
    <!--  用户导航  -->
    <div class="my-nav">
      <el-tabs
          :stretch=true
          class="my-tabs"
          v-model="activeName"
          @tab-click="handleClick">
        <el-tab-pane label="总览" name="first">
          <my-userdetail></my-userdetail>
        </el-tab-pane>
        <el-tab-pane label="收藏" name="second">
          <my-favorite></my-favorite>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import UserDetail from "./UserDetail"
import UserFavorite from "@/views/userCenter/UserFavorite";
export default {
  name: "UserCenter",
  components:{
    'my-userdetail':UserDetail,
    'my-favorite': UserFavorite,
  },
  data() {
    return {
      activeName: 'first',
      userinfo :{
        id: '',
        username: '',
        birthday: '',
        email: '',
        avatar: '',
        rolename: '',
        createdate: '',
        updatedate: '',
      }
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  },
  created(){
    this.request.get("/userCenter/info").then(res => {
      // console.log(res.data)
      this.userinfo = res.data
    })
  }
}
</script>

<style scoped>
body {

}
.my-center{
  align-items: center;
  max-width: 1260px;
  margin: 10px auto;
}
.my-userinfo {
  max-width: 960px;
  margin: 0px auto;
  text-align: center;
}
.my-avatar {
}
.my-username {
  margin-top: 10px;
  margin-bottom: 15px;
  display: block;
  color: white;
  font-size: 28px;
  align-items: center;
}
.my-nav {
  background-color: #fff;
  /*background-color: #505050;*/
  border-radius: 30px;
  max-height: 615px;
  /*height: 615px;*/
}
.my-tabs {
  /*text-align: center;*/
  padding: 10px;
}
.my-btn {
  display: inline-block;
  align-items: center;
}

</style>