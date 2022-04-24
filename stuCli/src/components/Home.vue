<template>
  <el-container class="home-container">
    <!--头部布局-->
    <el-header>
      <div>
        <img src="../assets/icon/logo.png" alt class="logo_img" />
        <span>学生服务中心</span>
      </div>
      <el-dropdown split-button type="primary" @command="handleCommand">
        {{username}}
        <el-dropdown-menu slot="dropdown">
          <!--<el-dropdown-item command="1">信息设置</el-dropdown-item>
          <el-dropdown-item command="2">修改密码</el-dropdown-item>-->
          <el-dropdown-item command="3">安全退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    <el-container>

      <!--主体布局-->
      <el-main>
          <!--路由占位符-->
          <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
    data() {
        return {
           username:'管理员名称',
          // 左侧菜单
          menuList: [
            {id:100, title:'疫苗管理',
            path:'/admin',
            slist:[{id:101, title:'疫苗信息', path:'/vaccess'},
              {id:102, title:'操作记录', path:'/log'}]},
            {id:200, title:'仓库管理',
              path:'/blog',
              slist:[{id:201, title:'库存管理', path:'/coldroom'},
                {id:202, title:'出库管理', path:'/isout'}]},
            {id:300, title:'用户信息',
              path:'/num',
              slist:[
                {id:301, title:'预约审核', path:'/wxuser'},
                {id:302, title:'退款管理', path:'/doctor'},
                {id:303, title:'接种信息', path:'/apply'}]}
                ],
            iconsObject: {
              '100':'iconfont iconguanliyuan',
              '200':'iconfont iconsport',
              '101':'iconfont icondenglu',
              '102':'iconfont iconmima',
              '103':'iconfont iconsport',
              '104':'iconfont iconshangpin',
              '201':'iconfont iconshu',
              '202':'iconfont iconkaluli',
              '203':'iconfont iconshiwu',
              '204':'iconfont icondenglu',
              '300':'iconfont icondenglu',
              '301':'iconfont icondenglu',
              '302':'iconfont icondenglu',
              '303':'iconfont icondenglu',
            },
            isCollapse:false,
            // 被激活的连接
            activePath:'',
        }
    },
  // 类似onload
  mounted(){
    this.username = window.sessionStorage.getItem('user');
  },
  created() {
     // this.getMenuList();
    //  this.username = window.sessionStorage.getItem('user');
      console.log(this.username)
      this.activePath = window.sessionStorage.getItem('activePath');// 取出session里的访问路径
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    handleCommand(command) {
      if(command==3){
        this.logout();
      }
    },
    // 获取所有的导航菜单
 /*   async getMenuList(){
      const {data:res} = await this.$http.get("menus");
      console.log(res.data);
      if( res.status != 200) return this.$message.error("操作失败！！！");
      this.menuList = res.data;
    },*/
    // 切换菜单折叠与展开
    toggleCollapase(){
      this.isCollapse = !this.isCollapse;
    },
    // 保存二级菜单的路径
    saveNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath);// 存放点击的二级菜单
      this.activePath = activePath;// 给点击的菜单添加高亮
    },
  }
};
</script>
<style lang="less" scoped>
.el-header {
  background-color: #333744;
  display: flex;
  justify-content: space-between;// 左右贴边
  padding-left: 0%;// 左边界
  align-items: center;// 水平
  color: #fff;
  font-size: 20px;
  > div { //左侧div加布局
    display: flex;
    align-items: center;
    span {
      margin-left: 10px;
    }
  }
}
.el-aside {
  background-color: #333744;
  .el-menu{
    border-right: none;// 对其右边框
  }
}
.el-main {
  background-color: #eaedf1;
}
.home-container {
  height: 100%;
}
.logo_img {
  width: 60px;
  height: 60px;
}
.iconfont{
  margin-right: 10px;
}
.toggle-button{
  background-color:#4A5064;
  font-size: 10px;
  line-height: 24px;
  color:#fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;// 显示鼠标指针为：小手
}
.headercard{
  width: 100px;
  height: 50px;
}
</style>
