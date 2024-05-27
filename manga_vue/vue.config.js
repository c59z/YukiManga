const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    client: {
      overlay: false,
    },
    open: true,
    // host: '192.168.3.33',
    host: '192.168.1.107',
    // host: 'localhost',
    port: 8080,
    https: false,
    //以上的ip和端口是本机的;下面为需要跨域的
    proxy: { //配置跨域
      '/api': {
        // target: 'http://192.168.3.33:7777', //填写请求的目标地址
        target: 'http://192.168.1.107:7777', //填写请求的目标地址
        changOrigin: true, //允许跨域
        pathRewrite: {
          '^/api': '' //请求的时候使用这个api就可以
        }
      }
    }
  }
})



