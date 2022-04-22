<template>
  <!-- 面包屑导航 -->
  <div class="test2">
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页></el-breadcrumb-item>
  </el-breadcrumb>
  <div>
    <el-row :gutter="20">
      <el-col :span="8" >
        <div class="grid-content bg-purple">
          <el-card  shadow="hover">
            <img  src="https://desk-fd.zol-img.com.cn/t_s960x600c5/g2/M00/0F/04/Cg-4WVVtECyIIrmqABCqFyoOCYkAAEg5wDCLrsAEKov842.jpg" style="height:100px;width:100px;border-radius:50%;margin-left:30%"/>
            <div style="margin-left:30%">
              <span>学生服务平台</span><br>
            </div>
          </el-card>

          <el-card shadow="hover" style="margin-top:10px;height: 400px">
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
              <el-form-item label="名称">
                <el-input v-model="formLabelAlign.name"></el-input>
              </el-form-item>
              <el-form-item label="活动区域">
                <el-input v-model="formLabelAlign.region"></el-input>
              </el-form-item>
              <el-form-item label="活动形式">
                <el-input v-model="formLabelAlign.type"></el-input>
              </el-form-item>
            </el-form>
            <el-button type="primary" style="margin-left:20%">提交</el-button>
            <el-button type="danger" >清除</el-button>
          </el-card>
        </div>
      </el-col>
      <el-col :span="16"><div class="grid-content bg-purple">
        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/1.png" class="image">
              <span class="mytext1"><el-button @click="mydata" type="text">我的资料</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/2.png" class="image">
              <span class="mytext1"><el-button type="text">我的成绩</el-button></span>
              <span></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/3.png" class="image">
              <span class="mytext1"><el-button type="text">获奖记录</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/4.png" class="image">
              <span class="mytext1"><el-button type="text">我的申请</el-button></span>
            </el-card>
          </div></el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 5px;">
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/5.png" class="image">
              <span class="mytext1"><el-button type="text">我的社团</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/6.png" class="image">
              <span class="mytext1"><el-button @click="getCode" type="text">扫码签到</el-button></span>
            </el-card>
          </div></el-col>
          <el-dialog
                  title="签到二维码"
                  :visible.sync="dialogVisible"
                  width="20%"
                  :before-close="handleClose">
            <div class="demo-image">
                <el-image style="width: 180px; height: 180px;margin-left: 80px" :src="codeurl"></el-image>
            </div>
            <span slot="footer" class="dialog-footer">
           <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </span>
          </el-dialog>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/7.png" class="image">
              <span class="mytext1"><el-button type="text">我的投票</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/88.png" class="image">
              <span class="mytext1"><el-button type="text">我的问卷</el-button></span>
            </el-card>
          </div></el-col>
        </el-row>
        <el-card>
          <el-row :gutter="20" style="margin-top: 5px;">
            <el-col :span="12"><div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>签到记录</span>
                  <el-button style="float: right; padding: 3px 0" type="text">补卡</el-button>
                </div>
                <div v-for="o in 4" :key="o" class="text item">
                  {{'2022-4-21 ' + o }}
                </div>
              </el-card>
            </div>
            </el-col>
            <el-col :span="12"><div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>活动信息</span>
                  <el-button style="float: right; padding: 3px 0" type="text">刷新</el-button>
                </div>
                <div v-for="o in 4" :key="o" class="text item">
                  {{'2022-4-21 ' + o }}
                </div>
              </el-card>
            </div>
            </el-col>
          </el-row>
        </el-card>
      </div></el-col>
    </el-row>
  </div>
  </div>
</template>



<style>

  .image {
    width: 60px;
    height: 60px;
    margin-left: 5%;
    display: block;
    margin-top: 5%;

  }

  .mytext1{
    display: block;
    margin-left: 110px;
    margin-top: -50px;
  }
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 465px;
    height: 400px;
  }
</style>

<script>
  import * as echarts from 'echarts'
  export default {
    data () {
      return {
        codeurl:'', //二维码路由
        dialogVisible:false,
        queryInfo: {
          query: "",
          pageNum: 1,
          pageSize: 4,

        },
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        },

        queryInfof: {
          query: "",
          pageNum: 1,
          pageSize: 100,
        },
      }
    },
    mounted: function () {

    },



    methods: {
      handleClick(row) {
        console.log(row);
      },

      //进入我的资料页面
       mydata(){
         this.$router.push("/wxuser");
       },
       //获取二维码
      async   getCode(){
        // 调用post请求
        this.dialogVisible = true;
        const { data: res } = await this.$http.post("/api/ercode/getCodeUrl");
        this.codeurl= res; // 将返回数据赋值
      }
    }

  }
</script>

