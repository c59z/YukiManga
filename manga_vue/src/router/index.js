import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "../views/index";
import BookDetail from "../views/manga/BookDetail"
import Login from "../views/Login"
import Works from "../views/manga/Works"
import Register from "../views/Register"
import UserCenter from "../views/userCenter/UserCenter"
import About from "../views/about/About"
import Upload from "@/views/upload/Upload";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    children: [
        // 媒体库
      {
        path: '/',
        name: 'Works',
        component: Works
      },
        // 用户中心
      {
        path: '/userCenter',
        name: 'UserCenter',
        component: UserCenter,
        // 里面应该需要内嵌几个子路由
        // children: []
      },
        // 关于 页
      {
        path: '/about',
        name: 'About',
        component: About,
        // 里面应该需要内嵌几个子路由
        // children: []
      },
      {
        path: '/upload',
        name: "Upload",
        component: Upload
      },
      {
        path: '/book/:bookId',
        name: 'BookDetail',
        component: BookDetail
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
