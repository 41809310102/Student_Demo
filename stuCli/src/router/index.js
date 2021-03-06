import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import success from '../components/Success.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import myapply from '../components/useradmin/myapply.vue'
import isout from '../components/room/isout.vue'
import actionadmin from '../components/vaccadmin/actionadmin.vue'
import wxuser from '../components/useradmin/wxuser.vue'
import mygrade from '../components/useradmin/mygrade.vue'
import mylog from '../components/useradmin/mylog.vue'
import myvote from "../components/useradmin/myvote.vue";
import question from "../components/useradmin/question.vue";
import myquestion from "../components/useradmin/myquestion.vue";
import mydata from "../components/useradmin/mydata.vue";
import sign from "../components/vaccadmin/signapply.vue";
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
      { path:'/myvote', component:  myvote},
      { path:'/question', component:  question},
      { path:'/myquestion', component:  myquestion},
      { path:'/mydata', component:  mydata},
      { path:'/mylog', component:mylog},
      { path:'/mygrade', component: mygrade},
      { path:'/wxuser', component: wxuser},
      { path:'/sign', component: sign},
    ],
  },

  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},
      { path:'/actionadmin', component: actionadmin},
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
// ????????????????????????
router.beforeEach((to, from, next) => {
  // to:?????????????????????
  // from:????????????????????????
  // next:???????????????????????????????????????
  //    next??????????????? next???'/URL'???????????????????????????
  if (to.path == '/login') return next();// ?????????????????????
  // ??????flag
  const flagStr = window.sessionStorage.getItem("flag");// session??????
  if (!flagStr) return next('/login');// ??????????????????
  next();
})


export default router// ????????????
