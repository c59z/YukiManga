<template>
  <div class="MyBookDetail">

    <div class="bookHeader">
      <!--  封面图  -->
      <div class="bookCover">
        <el-image :src="bookInfo.cover">

        </el-image>
      </div>
      <div class="bookRight">
        <!--  漫画名称  -->
        <div class="bookName">
          {{ bookInfo.name }}
        </div>
        <!--  作者  -->
        <div class="bookAuthor">
          <el-link type="info">{{ bookInfo.author }}</el-link>
        </div>
        <!--  Tags  -->
        <div class="bookTag">
          <el-tag class="bookTag"
                  v-for="t in bookInfo.tag"
                  type="info">
            <el-link style="color: white" type="info">{{ t }}</el-link>
          </el-tag>
        </div>
        <!--  状态  -->
        <el-select
            :popper-append-to-body="false"
            size="mini"
            class="mySelect"
            value="标记进度"
            @change="handleCommand"
        >
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
            <span style="margin-right: 7rem;">
              {{item.label}}
            </span>
              <i v-show="mangaProgressData.progress === item.value" class="el-icon-s-management"></i>
          </el-option>
        </el-select>

<!--        <div class="myDropdown">-->
<!--          <el-dropdown size="medium" class="mySelect" @command="handleCommand">-->
<!--          <span style="color: #606266;" class="el-dropdown-link">-->
<!--            标记进度-->
<!--            <i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
<!--          </span>-->
<!--            <el-dropdown-menu-->
<!--                slot="dropdown"-->
<!--            >-->
<!--              <el-dropdown-item command="1">-->
<!--                想看-->
<!--                <i v-show="mangaProgress === 1" class="el-icon-s-management"></i>-->
<!--              </el-dropdown-item>-->
<!--              <el-dropdown-item command="2">-->
<!--                再看-->
<!--                <i v-show="mangaProgress === 2" class="el-icon-s-management"></i>-->
<!--              </el-dropdown-item>-->
<!--              <el-dropdown-item command="3">-->
<!--                看过-->
<!--                <i v-show="mangaProgress === 3" class="el-icon-s-management"></i>-->
<!--              </el-dropdown-item>-->
<!--              <el-dropdown-item command="4">-->
<!--                搁置-->
<!--                <i v-show="mangaProgress === 4" class="el-icon-s-management"></i>-->
<!--              </el-dropdown-item>-->
<!--              <el-dropdown-item command="5">-->
<!--                重看-->
<!--                <i v-show="mangaProgress === 5" class="el-icon-s-management"></i>-->
<!--              </el-dropdown-item>-->
<!--            </el-dropdown-menu>-->
<!--          </el-dropdown>-->
<!--        </div>-->

      </div>
    </div>

    <i class="el-icon-s-management"></i>

    <!--  分页  -->
    <div class="myPageSelect">
      <div class="block">
        <span class="demonstration">调整每页显示条数</span>
        <el-pagination
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
    <el-divider></el-divider>
    <div class="mangaContent">
      <div class="bookPhoto" v-for="book in bookInfo.content">
<!--        <a-->
<!--            href="#"-->
<!--        >-->
          <el-image
              class="bookPhoto"
              :preview-src-list="bookInfo.content"
              :src="book"
          >

          </el-image>
<!--        </a>-->
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "BookDetail",
  data() {
    return {
      bookInfo: {
        // cover: "https://s2.loli.net/2022/11/27/DS2eXtq9QoZi3ja.jpg",
        // name: "TwinBox杂图",
        // author: "TwinBox",
        // tag: ["纯爱", "CG", "全彩"]
      },
      currentPage: 1,
      total: 0,
      pageSize: 20,
      cover: "",
      options: [
        {
          value: 1,
          label: '想看'
        },
        {
          value: 2,
          label: '在看'
        },
        {
          value: 3,
          label: '看过'
        },
        {
          value: 4,
          label: '搁置'
        },
        {
          value: 5,
          label: '重看'
        }
      ],
      mangaProgressData: {
        mangaId:null,
        progress: 0
      }
    }
  },
  created() {
    this.page(1,1,null)
  },
  methods: {
    page(currentPage){
      console.log(currentPage)
      const bookid =this.$route.params.bookId
      const _this = this
      this.request.get("/book/detail/"+bookid+"/"+currentPage).then(res => {
        console.log(res)
        this.bookInfo = res.data.book
        _this.cover = _this.bookInfo.content[0]
        _this.total = res.data.total
        _this.mangaProgressData.progress = res.data.progress
        // console.log(_this.total)
      }).catch(res => {

      });
    },
    handleCommand(command) {
      let str = "";
      switch (command){
        case 1:
          str = "想看"
          break;
        case 2:
          str = "在看"
          break;
        case 3:
          str = "看过"
          break;
        case 4:
          str = "搁置"
          break;
        case 5:
          str = "重看"
          break;
      }
      this.mangaProgressData.progress = command
      this.mangaProgressData.mangaId = this.bookInfo.id


      this.request.put("/book/updateProgress",this.mangaProgressData).then(res => {
        console.log("-----更新进度-------")
        console.log(res)
      })
      this.$message.success('进度更新成功,当前状态:  ' + str);
    }
  }
}
</script>

<style scoped>
.MyBookDetail {
  /*background-color: pink;*/
  width: 1220px;
  height: 780px;
}

.bookHeader {
  border: 1px white solid;
  border-radius: 10px;
  padding: 10px;
  display: block;
  float: none;
  height: 370px;
}

.bookCover {
  display: inline-block;
  float: left;
  width: 250px;
}

.bookRight {
  width: 780px;
  display: inline-block;
  float: left;
  margin-left: 20px;
}

.my-dropdown-item {
  width: 100px;
}

.bookName {
  float: top;
  display: inline-block;
  width: 800px;
  font-size: 24px;
  line-height: 24px;
  color: white;
  margin: 10px;
}

.bookAuthor {
  margin: 10px;
}

.bookTag {
  color: white !important;
  margin: 5px;
}

.bookTag span {
  background-color: #424242;
}

/*.mySelect {*/
/*  margin: 10px;*/
/*  color: white;*/
/*}*/

/*.myDropdown {*/
/*  background-color: white;*/
/*  margin: 10px;*/
/*  width: 7rem;*/
/*  border-radius: 5px;*/
/*}*/

/*.el-dropdown-link {*/
/*  cursor: pointer;*/
/*  color: #409EFF;*/
/*}*/
/*.el-icon-arrow-down {*/
/*  font-size: 12px;*/
/*}*/

.myPageSelect {
  display: block;
  text-align: center;
  margin: 0 auto;
}
.mangaContent {
  display: inline-block;
  border: 1px white solid;
  width: 1220px;
  /*height: 420px; !*这里以后可以变*!*/
  border-radius: 10px;
}
.bookPhoto {
  display: inline-block !important;
  margin: 10px;
  width: 100px;
  max-height: 144px;
  min-height: 70px;
}

</style>
