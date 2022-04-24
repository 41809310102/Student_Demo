import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import success from '../components/Success.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import vaccess from '../components/vaccadmin/vaccess.vue'
import log from '../components/vaccadmin/vacclog.vue'
import myapply from '../components/useradmin/myapply.vue'
import isout from '../components/room/isout.vue'
import Aiadmin from '../components/room/Aiadmin.vue'
import wxuser from '../components/useradmin/wxuser.vue'
import mygrade from '../components/useradmin/mygrade.vue'
import mylog from '../components/useradmin/mylog.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/success',
    component: success
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
      { path:'/vaccess', component: vaccess},
      { path:'/log', component: log},
    ],
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
      { path:'/myapply', component: myapply},
      { path:'/isout', component: isout},
      { path:'/Aiadmin', component:  Aiadmin},
    ],
  },

  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
      { path:'/mylog', component:mylog},
      { path:'/mygrade', component: mygrade},
      { path:'/wxuser', component: wxuser},
    ],
  },


  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
    ],
  },

  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
    ],
  },

  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
    ],
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
    ],
  },
]

const router = new VueRouter({
  routes
})
// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to:将要访问的路径
  // from:从哪里访问的路径
  // next:之后要做的任务，是一个函数
  //    next（）放行， next（'/URL'）强制跳转的路径。
  if (to.path == '/login') return next();// 访问路径为登录
  // 获取flag
  const flagStr = window.sessionStorage.getItem("flag");// session取值
  if (!flagStr) return next('/login');// 没登录去登录
  next();
})

export default router// 暴露出去
