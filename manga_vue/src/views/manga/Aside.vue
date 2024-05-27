<template>
  <el-aside width="initial">
    <div
        @mouseover="mouseIsHover()"
        @mouseleave="mouseIsLeave()"
        style="background-color:#000000;"
    >
      <el-menu
          router
          :default-active="this.$router.path"
          :collapse="isCollapse"
          style="min-height: calc(100vh - 73px)"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#1d1d1d"
          text-color="#fff"
          active-text-color="#409eff">
        <el-menu-item index="/">
          <div class="size-icon">
            <i class="el-icon-reading"></i>
          </div>
          <span slot="title">媒体库</span>
        </el-menu-item>
        <el-menu-item index="/userCenter">
          <div class="size-icon">
            <i class="el-icon-user-solid"></i>
          </div>
          <span slot="title">个人中心</span>
        </el-menu-item>
        <el-menu-item index="/upload">
          <div class="size-icon">
            <i class="el-icon-upload"></i>
          </div>
          <span slot="title">资源投稿</span>
        </el-menu-item>

        <div class="bottom-menu">

        </div>

        <div class="bottom-menu">
          <el-menu-item index="/about">
            <div class="size-icon">
              <i class="el-icon-question"></i>
            </div>
            <span slot="title">关于</span>
          </el-menu-item>
          <el-menu-item @click="userLogout()" index="/logout">
            <div class="size-icon">
              <i class="el-icon-s-unfold"></i>
            </div>
            <span class="item-title" slot="title">登出</span>
          </el-menu-item>
        </div>

      </el-menu>
    </div>
  </el-aside>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      isCollapse: true,
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    mouseIsHover() {
      // console.log("Hover")
      this.isCollapse = false;
    },
    mouseIsLeave() {
      // console.log("Leave")
      this.isCollapse = true;
    },
    // 用户登出 事件，传递token,后端解析token,
    userLogout(){
      // 记得header中携带token
      const _this = this
      this.request.post("/user/logout").then(res => {
        // console.log(res)
        sessionStorage.clear()
        localStorage.clear()
        _this.$store.commit("RESET_STORE")
        _this.$message.success(res.msg)
        _this.$router.push("/login")
      }).catch(res => {
        console.log(res)
      })
    }
  },
  created() {

  }
}
</script>

<style scoped>

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-icon-upload:before {
  /*text-align: center;*/
  /*font-size: 32px;*/
}

.size-icon {
   width: 50px;
  display: inline-block;
}

.el-aside::-webkit-scrollbar {
  display: none;
}

.bottom-menu {
  padding-top: 270px;
}

</style>