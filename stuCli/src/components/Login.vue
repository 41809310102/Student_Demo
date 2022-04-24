<template>
  <div class="login_container" >
  <h1 style="color:#fff;font-size:65px; transform: translate(17%, 20%);">学生管理系统</h1>
    <div class="login_box">
      <!--添加表单-->
      <el-button-group class="headchange">
     <el-button type="warning" icon="el-icon-s-custom" @click="changelogin">登录</el-button>
     <el-button type="primary" @click="changedelu">注册<i class="el-icon-s-promotion"></i></el-button>
     </el-button-group>
      <div v-if="flag">
         <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginRules"
        class="login_form"
        label-width="0px"
      >
        <el-form-item prop="username">
          <el-input v-model="loginForm.username"   placeholder="请输入用户名"  prefix-icon="iconfont icondenglu" style="width:60%" clearable></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password"  placeholder="请输入密码"  prefix-icon="iconfont iconmima" type="password" clearable style="width:60%"></el-input>
        </el-form-item>
          <el-form-item prop="code">
          <el-input v-model="loginForm.code"  placeholder="验证码"  prefix-icon="el-icon-key" type="text" style="width:30%;" clearable></el-input>
           <div style="border: none;" class="code" @click="refreshCode" >
               <s-Identify :identifyCode="identifyCode"></s-Identify>
          </div>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login" style="margin-left:-130%">登录</el-button>
          <el-button type="info" @click="resetForm('loginFormRef')">重置</el-button>
        </el-form-item>
      </el-form>
      </div>

       <div v-if="flag1">
         <el-form
        ref="denluFormRef"
        :model="denluForm"
        :rules="denluRules"
        class="login_form"
        label-width="0px"
      >
        <el-form-item prop="username">
          <el-input v-model="denluForm.username"  placeholder="请填写用户名"  prefix-icon="iconfont icondenglu" style="width:60%" clearable></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="denluForm.password" placeholder="请设置密码"   prefix-icon="iconfont iconmima" type="password" clearable style="width:60%"></el-input>
        </el-form-item>
       <el-form-item prop="password1">
          <el-input v-model="denluForm.password1" placeholder="请确认密码"  prefix-icon="iconfont iconmima" type="text" clearable style="width:60%"></el-input>
        </el-form-item>
          <el-form-item prop="code">
          <el-input v-model="denluForm.code1"  placeholder="验证码" prefix-icon="el-icon-key" type="text" style="width:30%;" clearable></el-input>
           <div style="border: none;" class="code" @click="refreshCode" >
               <s-Identify :identifyCode="identifyCode"></s-Identify>
      </div>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="zhuce" style="margin-left:-130%">注册</el-button>
              <el-button type="info" @click="resetForm('denluFormRef')">重置</el-button>
        </el-form-item>
      </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import SIdentify from "@/components/token"
export default {
  data() {

    return {
      loginForm: {
        username: "",
        password: "",
        code:""
      },
        denluForm: {
        username: "",
        password0: "",
        password:"",
        code:""
      },
        loginRules: {
         username: [
          { required: false, message: "请输入用户名", trigger: "blur" },
          { min: 2, max: 8, message: "长度在 2 到 8 个字符", trigger: "blur" }
        ],
        password: [
          { required: false, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 8, message: "密码为 6~8 位", trigger: "blur" }
        ],
        code: [
          { required: false, message: "请输入验证码", trigger: "blur" },
        ],
        },
        denluRules: {
             username: [
          { required: false, message: "请输入用户名", trigger: "blur" },
          { min: 2, max: 8, message: "长度在 2 到 8 个字符", trigger: "blur" }
        ],
        password: [
          { required: false, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 8, message: "密码为 6~8 位", trigger: "blur" }
        ],
        password1: [
          { required: false, message: "请输入确认密码", trigger: "blur" },
          { min: 6, max: 8, message: "密码为 6~8 位", trigger: "blur" }
        ],
        code: [
          { required:false, message: "请输入验证码", trigger: "blur" },
        ],
         },
        show:{

         },
      flag:true,
      flag1:false,

      sYxi:'',//收到的验证信息
       identifyCodes: '1234567890',
      identifyCode: '',//找回密码图形验证码
    }
  },
       components: {
        's-Identify': SIdentify,
       },
     watch:{
       identifyCode(v) {
      this.isDebugLogin && (this.loginForm.code = v)
       },
   },

  methods: {
    resetForm(formName) {
        this.$refs[formName].resetFields();
        console.log(formName)
    },
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;
        console.log(this.loginForm.code)
        if(this.loginForm.username==""||this.loginForm.password==""){
          this.$message.error("请填写用户名或密码")
          return;
        }
        if(this.loginForm.code==this.identifyCode){
          // 调用get请求
        const {data :res} = await this.$http.post("api/user/loginuser", this.loginForm);
         if (res.code==1) {
             window.sessionStorage.setItem('user',res.user.username); // session 放置
             window.sessionStorage.setItem('id',res.user.id); // session 放置
             //window.sessionStorage.setItem('cardid',res.user.cardid); // session 放置
           window.sessionStorage.setItem('flag','ok'); // session 放置
           this.$message.success(res.msg);
           this.$router.push({ path: "/home"});
         }else{
          this.$message.error(res.msg);
         }
        }else{
          this.$message.error("验证码错误，请重新验证！")
        }
      });
    },
    zhuce() {
      this.$refs.denluFormRef.validate(async valid => {
        if (!valid) return;
        console.log(this.denluForm.code)
        if(this.denluForm.username==""||this.denluForm.password==""){
          this.$message.error("请填写用户名或密码");
          return;
        }
        if(this.denluForm.password !=this.denluForm.password1){
          this.$message.error("两次密码输入不正确，请重新输入！")
          return;
        }
        if(this.denluForm.code==this.identifyCode){
          // 调用get请求
        const {data :res} = await this.$http.post("api/user/adduser", this.denluForm);
         if(res=='success') {
          // window.sessionStorage.setItem('flag','ok'); // session 放置
           this.$message.success("注册成功！！！");
           this.$router.push({ path: "/home"});
         }else if(res=='no'){
          this.$message.error("该昵称已经存在,请重新输入昵称");
         }else{
           this.$message.error("注册失败")
         }
        }else{
          this.$message.error("验证码错误，请重新验证！")
        }
      });
    },
    changelogin(){
      this.flag1 = false;
      this.flag = true;
    },
    changedelu(){
       this.flag1 = true;
       this.flag = false;
       //this.resetLoginForm();
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
          ]
      }
       console.log(this.identifyCode)
    }
  },

  mounted(){
  //this.makeCode(this.identifyCodes,4);
  },
  created(){
    this.refreshCode();
  },
};
</script>

<style lang="less" scoped>
.headchange{
  display: flex;
  margin-left: 28%;
  margin-top: 5%;
}
// 跟节点样式
.login_container {
    display: flex;// 弹性布局
   width: 100%;
  height: 100%;
  background-image: url("../assets/OIP-C.jpg");
  background-size: cover;
  background-position: center;
  position: relative;
  box-shadow: 0 0 10px #ddd;// 盒子阴影

}
// 登录框
.login_box {
  width: 400px;
  height: 380px;
  background-color: rgba(244, 247, 250, 0.3);
  border-radius: 3px;// 圆角
  position: absolute;// 绝对定位
  left: 55%;
  top: 50%;
  transform: translate(20%, -50%);// 根据自己位置 以自己为长度位移
  // 头像框
  .avatar_box {
    width: 100px;
    height: 100px;
    border: 1px solid #eee;
    border-radius: 50%; // 加圆角
    padding: 10px;
    box-shadow: 0 0 10px #ddd;// 盒子阴影
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #0ee;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%; // 加圆角
      background-color: #eee;
    }
  }
  .btns {
    display: flex;// 弹性布局
    justify-content: flex-end; // 尾部对齐
  }
  .login_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    margin-left: 20%;
    padding: 0 10px;
    box-sizing: border-box;// 设置边框
   // background-color: rgba(78, 75, 75, 0.664);
    margin-top:-20px
  }
  .code{
    display: flex;
    margin-left:32%;
    margin-top:-42.5px;
  }
}
</style>
