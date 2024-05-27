<template>
  <div class="myUploadDiv">
    <!--  表单  -->
    <el-form :model="ruleForm" ref="ruleForm">

      <!--  头部提示    -->
      <div class="upload_header">
        <!--   头部提示     -->
        <span class="span_tag">
          <i class="el-icon-edit"></i>
          <span> 看我 </span>
        </span>

        <!--    内容    -->
        <div class="text-content">
          欢迎投稿资源!在投稿之前请阅读
          <el-link
              style="display: inline-block;line-height: 20px;font-size: 20px;padding-bottom: 3px"
              type="primary"
              @click="centerDialogVisible = true"
          >投稿教程
          </el-link>
          否则可能会导致您的投稿无法通过
        </div>

      </div>
      <!--  资源简介  -->
      <div class="upload_name">
        <!--   头部提示     -->
        <span class="span_tag">
          <i class="el-icon-edit"></i>
          <span> 资源名称 </span>
        </span>
        <!--    内容    -->
        <div class="text-content">
          请规范填写资源简介
          <br>
          例如:【C100】Anmi杂图1
        </div>
        <div class="myinput_area">
          <input
              class="myinput"
              type="text"
              v-model.trim="ruleForm.name"
              placeholder="请输入资源名称:"
          >
          <input type="text"
                 class="myinput"
                 v-model.trim="ruleForm.author"
                 placeholder="请输入作者名称"
          >
          <div class="myBookTags">
            <el-tag
                style="display: inline-block"
                :key="tag"
                v-for="tag in dynamicTags"
                closable
                :disable-transitions="false"
                @close="handleClose(tag)">
              {{tag}}
            </el-tag>
            <el-input
                class="input-new-tag"
                v-if="inputVisible"
                v-model="inputValue"
                ref="saveTagInput"
                size="small"
                @keyup.enter.native="handleInputConfirm"
                @blur="handleInputConfirm"
            >
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInput">New Tag</el-button>
            <el-switch
                class="mySwitch"
                style="display: block"
                v-model="isadult"
                active-color="#ff4949"
                active-text="R18"
                inactive-color="#13ce66"
                inactive-text="全年龄">
            </el-switch>
          </div>
        </div>


      </div>
      <!--  上传资源    -->
      <div class="upload_book">
        <!--   头部提示     -->
        <span class="span_tag">
          <i class="el-icon-edit"></i>
          <span> 资源上传 </span>
        </span>
        <!--    内容    -->
        <div class="text-content">
          请规范上传文件(仅限zip格式,文件大小不能超过200MB)
        </div>
        <el-form-item prop="file">
          <div class="upload_resources">
            <el-upload
                class="upload-demo"
                drag
                action="https://jsonplaceholder.typicode.com/posts/"
                ref="uploadImport"
                :http-request="httpRequest"
                :on-remove="handleRemove"
                :file-list="fileList"
                :limit="1"
                :on-change="handleChange"
                :auto-upload="false"
                accept="application/zip,.zip"
                multiple>
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
<!--              <div style="color: white" class="el-upload__tip" slot="tip">只能上传zip文件，且不超过200MB</div>-->
            </el-upload>
          </div>
        </el-form-item>

        <el-form-item class="myupload_group">
          <el-button type="primary" @click="submitUpload">上传</el-button>
        </el-form-item>

      </div>
    </el-form>

    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
      <span>这里是投稿教程</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Upload",
  data() {
    return {
      centerDialogVisible: false,
      ruleForm: {
        name: '',
        author: '',
        file: '',
      },
      hasFile: false,
      fileList: [],
      dynamicTags: [],
      inputVisible: false,
      inputValue: '',
      isadult:false,
    }
  },
  methods: {
    handleRemove(file, fileList){
      if (fileList.length){
        this.hasFile = true
      }
      this.ruleForm.file = null
    },
    handleChange(file, fileList){
      file.clearFiles()
      if (fileList.length >= 2) {
        return;
      }
      if (fileList.length === 1) {
        this.hasFile = true;
      }
      this.ruleForm.file = file;
    },
    //确定按钮
    //调用组件upload的submit方法，从而触发httpRequest方法，实现手动上传
    submitUpload(){
      this.$refs.ruleForm.validate(valid => {
        if (valid){
          this.$refs.uploadImport.submit();
          if (this.dynamicTags.length >= 5){
            this.inputValue = false
          }
        }
      })
    },
    httpRequest(param){
      let fd = new FormData();
      this.ruleForm.tags = this.dynamicTags.join(",")

      if (this.isadult){
        this.ruleForm.isadult = 1
      }else{
        this.ruleForm.isadult = 0
      }

      fd.append('file',param.file); // 传文件
      fd.append('fileName',this.ruleForm.name) // 输入的资源名称
      fd.append('author',this.ruleForm.author) // 作者
      fd.append('tags',this.ruleForm.tags) // Tags
      fd.append('isadult',this.ruleForm.isadult) // 是否为成人内容
      this.request.post("/book/upload",fd,{
        headers: {'Content-Type': 'multipart/form-data'},//定义内容格式,很重要
        timeout: 20000, // 超过20秒后超时
      }).then(res => {
        // console.log(res)
        this.$message.success(res.msg)
        this.$router.push("/")
        //接口成功调用params上的onSuccess函数，会触发默认的successHandler函数
        //这样可以用自带的ui等
        ///params.onSuccess({name: 'eric'})
      }).catch(err => {
        console.log(err)
      })
    },

    // 删除点击的当前页
    handleClose(tag) {
      console.log("删除了一个Tag")
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      if (this.dynamicTags.length < 5){

      }
    },

    //添加一个tag
    showInput() {
      console.log("点击了new tag")
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    // 添加内容
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
      console.log(this.dynamicTags)
    }
  },
}
</script>

<style scoped>
* {
  color: white;
}

.myUploadDiv {
  width: 1060px;
  height: 780px;
  /*background-color: skyblue;*/
  margin: 10px auto;
}

.span_tag {
  display: inline-block;
  background-color: #4fccfa;
  translate: 3rem -15px;
  width: auto;
  padding: 0 10px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  border-radius: 0.5rem;
}


.upload_header {
  margin: 0px auto;
  width: 960px;
  min-height: 100px;
  border-radius: 10px;
  background-color: #1d1d1d;
  border: 1px solid #fff;
  box-shadow: 2px 2px 30px rgba(255, 255, 255, 0.4);
}

.text-content {
  display: block;
  margin: 0 auto;
  padding: 10px 20px;
  width: 800px;
  height: auto;
  text-align: center;
  background-color: #000;
  font-size: 20px;
  line-height: 20px;
  border-radius: 10px;
}

.upload_name {
  margin: 0px auto;
  margin-top: 50px;
  width: 960px;
  min-height: 100px;
  border-radius: 10px;
  background-color: #1d1d1d;
  border: 1px solid #fff;
  box-shadow: 2px 2px 30px rgba(255, 255, 255, 0.4);
}

.myinput_area {
  display: block;
  margin: 0 auto;
  padding: 10px 20px;
  width: 840px;
}

.myinput {
  outline: none;
  font-size: 18px;
  width: 100%;
  height: 40px;
  border: none;
  border-bottom: 1px solid rgb(94, 70, 252);
  color: white;
  background-color: rgba(0, 0, 0, 0);
}

.upload_book {
  margin: 0px auto;
  margin-top: 50px;
  width: 960px;
  min-height: 100px;
  border-radius: 10px;
  background-color: #1d1d1d;
  border: 1px solid #fff;
  box-shadow: 2px 2px 30px rgba(255, 255, 255, 0.4);
}

.upload-demo {
  text-align: center;
  display: block;
  margin: 20px auto;
}
.myupload_group {
  display: block;
  text-align: center;
  margin: -20px auto 20px;
  /*margin: 20px auto;*/
  /*margin-top: -20px;*/
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
.myBookTags {
  width: 800px;
  display: inline-block;
  margin: 10px 0px;
}

.mySwitch {
  display: inline-block;
  width: 240px;
  float: right;
  align-items: center;
  /*line-height: 32px;*/
}
.el-switch, .el-switch__core {
  margin-top: 7px;
  position: relative;
  vertical-align: middle;
}
</style>