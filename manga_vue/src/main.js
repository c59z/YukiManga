import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 引入element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI)

// 引入axios
import request from "./utils/request";
// import axios from "axios"
Vue.prototype.request = request

// Vue.prototype.$request=request
// Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
