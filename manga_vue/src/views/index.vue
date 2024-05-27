<template>

  <el-container>
<!--    头部      -->
    <el-header>
      <my-header></my-header>
    </el-header>
    <el-container>
    <!--    侧边栏      -->
      <el-aside width="initial">
        <Aside>
          <my-aside></my-aside>
        </Aside>
      </el-aside>
      <!--    主界面      -->
      <el-main class="main-top">
      <!--    这边应该需要变化      -->
<!--        <my-works></my-works>-->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
import Header from "@/views/manga/Header";
import Aside from "@/views/manga/Aside"
import Works from "@/views/manga/Works";

export default {
  name: "Index",
  components:{
    'my-header':Header,
    'my-aside':Aside,
    'my-works':Works,
  }
}
</script>

<style scoped>
.el-header {
  /*z-index: 999;*/
  /*position: fixed;*/
  width: 100%;
  padding: 0;
  margin-bottom: 10px;
}

.main-top {
  background-color: #000;
}
.el-main {
  /*margin-top: 20px;*/
  /*height: 100vh;*/
}

.el-aside{
  /*margin-top: 35px;*/
  /*z-index: 800;*/
  /*position: fixed;*/
}

</style>