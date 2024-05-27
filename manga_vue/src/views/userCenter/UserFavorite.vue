<template>
  <div>
    用户收藏
    <div>
      <el-select
          :popper-append-to-body="false"
          size="mini"
          class="mySelect"
          value="选择进度"
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
          <i v-show="progress === item.value" class="el-icon-s-management"></i>
        </el-option>
      </el-select>
    </div>

    <el-scrollbar style="height: 500px">
      <div class="FavoriteManga">
        <ul class="infinite-list" v-infinite-scroll="load" style="overflow-y:scroll;padding-right: 40px">
<!--          <li v-for="i in count" class="infinite-list-item">{{ i }}</li>-->
<!--          每一个漫画都作为一列-->
<!--          <li v-for="book in mangaInfo" class="infinite-list-item">{{ book.name }}</li>-->

          <el-popover
              v-for="book in mangaInfo"
              placement="top"
              trigger="hover"
          >

            <div
                class="grid-content bg-purple Book-description"
                @mouseover="mouseIsHover(book.name,book.author)"
                @mouseleave="mouseIsLeave(book.name,book.author)"
            >
              <router-link
                  style="float:left;"
                  :to="{name: 'BookDetail', params:{bookId: book.manga_id}}">
                <el-image
                    class="myPhoto"
                    :src="book.cover"
                    :title="book.name"
                >
                </el-image>
              </router-link>

              <div class="book-aside" style="display: inline-block">
                <router-link
                    style="color: white;text-decoration: none"
                    :to="{name: 'BookDetail', params:{bookId: book.manga_id}}"
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
                      v-if="book.isadult === 0"
                      class="myTagAd"
                      size="small"
                      effect="plain"
                      type="success">
                    全年龄
                  </el-tag>
                  <el-tag
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

            <el-alert
                class="myAlertList"
                slot="reference"
                :title=book.name
                type="success"
                :closable="false">
            </el-alert>
          </el-popover>

<!--          <li v-for="i in count" class="infinite-list-item">{{ i }}</li>-->
        </ul>
      </div>
    </el-scrollbar>

  </div>
</template>

<script>
import EventBus from "@/EventBus";

export default {
  name: "UserFavorite",
  data() {
    return {
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
      mangaInfo: [
        {
          name: "Anmi杂图",
        },
        {
          name: "(C97)(Anmi)Avian Romance PINK LABEL 7",
        },
        {
          name: "(C96)(Anmi)Avian Romance PINK LABEL 6",
        },
        {
          name: "(C95)(Anmi)Avian Romance PINK LABEL 5",
        },
      ],
      count: 0,
      progress: -1,
    }
  },
  methods: {
    handleCommand(command){
      // 选择哪一个分类就获取那些进度的漫画
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
      this.progress = command
      this.load()
      console.log(command)
    },
    selectBook(search_str){
      EventBus.$emit('selectBook',search_str)
    },
    load () {
      // 向后端发送请求
      this.request.get("/book/favorite/getMangaByProgress/"+this.progress).then(res => {
        this.mangaInfo = res.data
        console.log(this.mangaInfo)
      }).catch(res => {

      })
    }
  },
  created() {
    this.load();
  }

}

</script>

<style scoped>
.FavoriteManga{
  margin-top: 10px;
  margin-bottom: 10px;
  height: 500px;
  width: 1260px;
  /*overflow-y:auto;*/
  /*background-color: skyblue;*/
}
.myAlertList {
  margin: 12px 0;
}
.myAlertList:hover {
  cursor: pointer;
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
