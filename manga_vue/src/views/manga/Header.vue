<template>
  <div class="myheader">
    <div class="left_box" style="padding:10px 0">
      <el-button @click="" icon="el-icon-s-grid" style="border: 0;font-size: 24px;  margin-right: 10px;background-color:#1d1d1d;"></el-button>
<!--      <span style="width: 76%;" >    <span style="color: white">Pron</span> <span style="color: orange">hub</span>　</span>-->
      <span style="width: 76%;" >    Manga　</span>
<!--      <span>　動く必要のない時には眠るのも立派な仕事ですよ。</span>-->
      <el-input
          style="width: 300px;height: 50px;"
          v-model.trim="checkCode"
          placeholder="请输入要搜索的内容"
          @input="handleSelect"
      >
        <template #suffix>
          <!--     清除输入框中的内容       -->
          <el-button @click="clearContent(checkCode)" icon="el-icon-close"  style="border: 0;"></el-button>
        </template>
      </el-input>

    </div>
  </div>
</template>

<script>
import EventBus from "@/EventBus";
export default {
  name: "Header",
  data() {
    return {
      checkCode: '',
    }
  },
  methods: {
    handleSelect(value){
      // if (value==''){
      //   EventBus.$emit('selectBook');
      // }else{
        EventBus.$emit('selectBook',value);
      // }
      // console.log(value)
    },
    clearContent(checkCode){
      this.checkCode = '';
      EventBus.$emit('selectBook');
    }
  },
}
</script>

<style scoped>
.left_box {
  display: flex;
  font-size: 21px;
  color: white;
  /* 侧轴居中对齐 */
  align-items: center;
}
.right_box {
  display: flex;
  font-size: 21px;
  color: white;
  /* 侧轴居中对齐 */
  align-items: center;
}

.myheader {
  background-color: #1d1d1d;
}

</style>
