<template>
  <div class="myabout" v-html="content">
<!--    这里是about界面-->
  </div>
</template>

<script>
export default {
  name: "About",
  data() {
    return {
      content:'',
    }
  },
  created(){
    this.request.post("/public/about").then(res => {
      console.log(res.data)
      this.content = res.data.content
    })
  }
}
</script>

<style scoped>

.myabout {
  color: white;
}

</style>