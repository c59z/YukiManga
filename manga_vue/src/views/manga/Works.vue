<template>
<div style="height: auto">
  <el-scrollbar style="height: 88vh;">
    <div
        class="works-top"
        v-loading="loading"
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
    >

      <div
          v-model="state_str"
          class="div_state"
      >
        {{state_str}}
      </div>

      <!--  头部搜索框   -->
      <el-select
          size="mini"
          class="mySelect"
          v-model="value"
          @change="selectChange">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <!--  主要图书内容  -->
      <el-row
          :gutter="0"
          style="width: initial;"
          v-if="loading===false"
      >
        <!--   空状态   -->
        <!--      <el-empty-->
        <!--          v-if="loading === true"-->
        <!--          description="no data"></el-empty>-->
        <el-col
            :span="6"
            v-for="book in bookInfo"

        >
          <div
              class="grid-content bg-purple Book-description"
              @mouseover="mouseIsHover(book.name,book.author)"
              @mouseleave="mouseIsLeave(book.name,book.author)"
          >
            <router-link
                style="float:left;"
                :to="{name: 'BookDetail', params:{bookId: book.id}}">
              <el-image
                  lazy
                  class="myPhoto"
                  :src="book.cover"
                  :title="book.name"
              >
              </el-image>
            </router-link>

            <div class="book-aside" style="display: inline-block">
              <router-link
                  style="color: white;text-decoration: none"
                  :to="{name: 'BookDetail', params:{bookId: book.id}}"
              >
                <div class="book-title">
                  {{ book.name }}
                </div>
              </router-link>
<!--              <router-link-->
<!--                  style="color: white;text-decoration: none"-->
<!--                  :to="{name: 'Index'}"-->
<!--              >-->
                <div class="book-author" @click="selectBook(book.author)">
                  作者:{{ book.author }}
                </div>
<!--              </router-link>-->
              <div style="text-align: center">
<!--                <router-link-->
<!--                    style="color: white;text-decoration: none"-->
<!--                    :to="{name: 'Index'}">-->
                  <el-tag
                      style="cursor: pointer"
                      v-if="book.isadult === 0"
                      class="myTagAd"
                      size="small"
                      effect="plain"
                      type="success">
                    全年龄
                  </el-tag>
                <el-tag
                    style="cursor: pointer"
                    v-if="book.isadult === 1"
                    class="myTagAd"
                    size="small"
                    effect="plain"
                    type="danger">
                  R18
                </el-tag>

<!--                </router-link>-->

              </div>
              <div style="text-align: center;height: 120px">
<!--                <router-link-->
<!--                    style="color: white;text-decoration: none;"-->
<!--                    :to="{name: 'Index'}">-->
                  <el-tag
                      style="cursor: pointer"
                      v-for="ta in book.tag"
                      class="myTags"
                      size="mini"
                      effect="plain"
                      type="info"
                      @click=selectBook(ta)
                  >{{ ta }}

                  </el-tag>
<!--                </router-link>-->

              </div>
              <div class="book-date">
                {{ book.uploaddata }}
              </div>
            </div>
          </div>
        </el-col>
      </el-row>

      <!--  分页  -->
      <div class="myPageSelect">
        <div class="block">
          <!--        <span class="demonstration">调整每页显示条数</span>-->
          <el-pagination
              :hide-on-single-page="true"
              background
              layout="prev, pager, next"
              :current-page="currentPage"
              :page-sizes="[pageSize]"
              :page-size="pageSize"
              :total="total"
              @current-change="page"
          >
          </el-pagination>
        </div>
      </div>
    </div>
  </el-scrollbar>
</div>


</template>

<script>
import EventBus from "@/EventBus";
export default {
  name: "Works",
  data() {
    return {
      bookInfo: [
        {
          // id: 1,
          // cover: "https://s2.loli.net/2022/11/13/ijZnIxDaFWfeEBY.png",
          // name: "Hiten画集",
          // author: "hiten",
          // updatedate: "2022-11-14"
        }
      ],
      loading: true,
      options: [{
        value: 1,
        label: '按照上传时间降序'
      }, {
        value: 2,
        label: '按照上传时间升序'
      }],
      value: '',
      selectMethod: 1,
      currentPage: 1,
      total: 0,
      pageSize: 12,
      state_str: 'All Works',
      selectWord: '',
    }
  },
  methods: {
    mouseIsHover(title, author) {

    },
    mouseIsLeave() {

    },
    page(currentPage,selectMethod,selectWord){
      /*
      console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
      console.log("currentPage:"+currentPage)
      console.log("selectMethod:"+selectMethod)
      console.log("selectWord:"+selectWord)
      console.log(">1>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
       */

      const _this = this
      this.request.get("/book/getBookList?currentPage="+currentPage+"&selectMethod="+this.selectMethod+"&selectWord="+this.selectWord).then(res => {
        // 从后端拉取首页的图书信息
        console.log(res.data)
        _this.bookInfo = res.data.books
        _this.total = res.data.total
        if (_this.bookInfo.length > 0){
          this.loading = false;
        }else{
          this.loading = true;
        }
        console.log(res.data)
      })
    },
    // 按照什么顺序排序
    selectChange(value){
      // this.value = value
      this.selectMethod = value
      // console.log(value)
      this.page(this.currentPage,this.selectMethod,this.selectWord)
    },
    // 点击作者后
    selectBook(value){
      EventBus.$emit('selectBook',value)
    },
  },
  mounted() {
    EventBus.$on("selectBook",value => {
      this.selectWord = value
      if (value === '' || value === undefined){
        console.log("数据为空")
        this.state_str = "All Works"
        this.page(1,this.value,"")
      }else{
        console.log("传过来的值是: "+value)
        this.state_str =　"Search by " + value
        this.page(1,this.selectMethod,value)
      }

    })
  },
  created() {
    this.value = 1  // 页面加载进来的时候设置
    this.selectWord = ''
    this.page(this.currentPage,1,null)
  }
}
</script>

<style scoped>

/*.el-row {*/
/*  margin-bottom: 20px;*/
/*}*/


/*.bg-purple-dark {*/
/*  background: #99a9bf;*/
/*}*/

/*.bg-purple {*/
/*  background: #d3dce6;*/
/*}*/

/*.bg-purple-light {*/
/*  background: #e5e9f2;*/
/*}*/

/*.grid-content {*/
/*  border-radius: 4px;*/
/*  min-height: 36px;*/
/*}*/

/*.row-bg {*/
/*  padding: 10px 0;*/
/*  background-color: #f9fafc;*/
/*}*/

.works-top {
  width: auto;
  height: auto;
}



.Book-description {
  display: inline-block;
  margin: 10px 10px;
  width: 350px;
  height: 300px;
  border: none;
}

.Book-description:hover {
  box-shadow: 2px 2px 30px rgba(255, 255, 255, 0.4);
}

.myPhoto {
  display: inline-block;
  width: 200px;
  height: 300px;
}

/*.isHover {*/
/*  width: 200px;*/
/*  height: 300px;*/
/*  background-color: rgba(0,0,0,0.5);*/
/*}*/

.book-aside {
  color: white;
  display: inline-block;
  width: 150px;
  height: 300px;
  background-color: #1d1d1d;
}

.book-title {
  margin: 10px;
  padding: 3px;
  border: 1px white solid;
  border-radius: 6px;
  text-align: center;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.book-author {
  margin: 10px;
  padding: 3px;
  border: 1px white solid;
  border-radius: 6px;
  text-align: center;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.book-date {
  margin: 10px;
  /*margin-top: 110px;*/
  padding: 2px;
}

.mySelect {
  margin: 10px;
}

.myTagAd {
  margin: 10px auto;
  text-align: center;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.myTags {
  display: inline-block;
  text-align: center;
  margin: 5px;
}
.myPageSelect {
  display: block;
  text-align: center;
  margin: 10px auto;
}
.div_state {
  margin: 10px;
  color: white;
  font-size: 1.5rem;
}

</style>
