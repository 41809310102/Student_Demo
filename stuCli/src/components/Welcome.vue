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
              <span class="mytext1"><el-button type="text" @click="mygrade">我的成绩</el-button></span>
              <span></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/3.png" class="image">
              <span class="mytext1"><el-button type="text" @click="mylog">获奖记录</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/4.png" class="image">
              <span class="mytext1"><el-button @click="myapply" type="text">我的活动</el-button></span>
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
                <template>
                  <el-table
                          :data="signlog"
                          style="width: 100%"
                          :row-class-name="tableRowClassName">
                    <el-table-column
                            prop="signtime"
                            label="签到时间"
                            width="180">
                      <template slot-scope="scope">
                        <span v-model="scope.row.signtime" slot="reference" style="color: red">{{scope.row.signtime}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column
                            prop="uname"
                            label="姓名"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            label="签到位置">
                      <template slot-scope="scope">
                        <el-popover
                                placement="top-start"
                                title="签到位置"
                                width="100"
                                trigger="hover"
                                effect="dark" >
                          {{scope.row.local}}
                          <span v-model="scope.row.local" slot="reference" style="color:blue">{{scope.row.local| ellipsis}}</span>
                        </el-popover>
                      </template>
                    </el-table-column>
                  </el-table>
                </template>
              </el-card>
            </div>
            </el-col>
            <el-col :span="12"><div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>活动信息</span>
                  <el-button style="float: right; padding: 3px 0" type="text">刷新</el-button>
                </div>
                <div class="block">
                  <div class="radio">
                    排序：
                    <el-radio-group v-model="reverse">
                      <el-radio :label="true">倒序</el-radio>
                      <el-radio :label="false">正序</el-radio>
                    </el-radio-group>
                  </div>
                    <el-timeline :reverse="reverse">
                      <el-timeline-item
                              v-for="(activity, index) in activities"
                              :key="index"
                              :timestamp="activity.timestamp">
                        {{activity.content}}
                      </el-timeline-item>
                    </el-timeline>
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

  export default {
    data () {
      return {
        codeurl:'', //二维码路由
        signlog:[],//签到记录
        dialogVisible:false,
        queryInfo: {
          query: "",
          pageNum: 1,
          pageSize: 4,

        },
        reverse: true,
        activities: [{
          content: '活动按期开始',
          timestamp: '2018-04-15'
        }, {
          content: '通过审核',
          timestamp: '2018-04-13'
        }, {
          content: '创建成功',
          timestamp: '2018-04-11'
        }, {
          content: '创建成功',
          timestamp: '2018-04-11'
        }],
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

    created() {
      this.getlog();
    },

    methods: {
      handleClick(row) {
        console.log(row);
      },

      //进入我的资料页面
       mydata(){
         this.$router.push("/wxuser");
       },
      //进入我的成绩界面
       mygrade(){
        this.$router.push("/mygrade");
      },
      //进入我的获奖界面
      mylog(){
        this.$router.push("/mylog");
      },
      //进入我的活动界面
      myapply(){
        this.$router.push("/myapply");
      },
       //获取二维码
      async   getCode(){
        // 调用post请求
        this.dialogVisible = true;
        const { data: res } = await this.$http.post("/api/ercode/getCodeUrl");
        this.codeurl= res; // 将返回数据赋值
      },

      //获取用户打卡记录
      async   getlog(){
        // 调用post请求
        const { data: res } = await this.$http.get("api/Sign/getUserSignlog?id="+window.sessionStorage.getItem('id'));
        if(res.code==1){
          this.signlog= res.log; // 将返回数据赋值
        }else{
          this.$message.error("获取签到记录失败");
        }
      },
    },
    //只显示前10个字符
    filters:{
      ellipsis(value){
        if (!value) return '';
        if (value.length > 8) {
          return value.slice(0,8) + '...'
        }
        return value
      }
    }

  }
</script>

