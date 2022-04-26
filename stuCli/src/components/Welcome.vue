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
            <img  src="../assets/icon/avatar35.png" style="height:100px;width:100px;border-radius:50%;margin-left:30%"/>
            <div style="margin-left:30%">
              <span>学生服务平台</span><br>
            </div>
          </el-card>

          <el-card shadow="hover" style="margin-top:10px;height: 400px">
            <el-row :gutter="20">
              <el-col :span="6"><div class="grid-content bg-purple">
                <div>
                  <img src="../assets/icon/m1.png" @click="Money=true" class="image">
                  <span class="mtext">班费明细</span>
                </div>
                <el-dialog title="班费明细" :visible.sync="Money" width="60%">
                  <el-row :gutter="20">
                    <el-col :span="3"><div class="grid-content bg-purple">
                      <el-button type="danger" @click="innerVisible=true">记账</el-button>
                    </div></el-col>
                    <el-col :span="18" v-if="innerVisible"><div class="grid-content bg-purple">
                      <el-form :inline="true" :model="formInline" class="demo-form-inline">
                        <el-form-item label="缴费人:">
                          <el-input v-model="formInline.name"  clearable placeholder="输入缴费人姓名"></el-input>
                        </el-form-item>
                        <el-form-item label="费用：">
                          <el-input v-model="formInline.money"  clearable placeholder="输入缴费金额"></el-input>
                        </el-form-item>
                        <el-form-item>
                          <el-button type="primary" @click="addMoney">保存</el-button>
                        </el-form-item>
                      </el-form>
                    </div></el-col>
                    <el-col :span="3"><div class="grid-content bg-purple">
                      <div style="margin-top: 10px">公开?<el-switch
                              v-model="publicMoney"
                              active-color="#13ce66"
                              inactive-color="#ff4949">
                                </el-switch></div>
                    </div></el-col>
                  </el-row>
                  <el-table :data="MoneyData" height="350" border style="width: 60%"  :default-sort = "{prop: 'time', order: 'descending'}">
                    <el-table-column prop="time" sortable label="日期" width="180"></el-table-column>
                    <el-table-column prop="name" label="姓名" width="80"></el-table-column>
                    <el-table-column prop="money" label="缴纳/元" width="80"></el-table-column>
                    <el-table-column prop="addusername" label="记账人" width="100"></el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="100">
                      <template slot-scope="scope">
                        <el-button  @click="updateMoney(scope.row)" type="text" size="small">修改</el-button>
                        <el-button type="text" @click="delMoney(scope.row)" size="small">删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-dialog>
              </div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m6.png" @click="votewin=true" class="image">
                <span class="mtext">发起投票</span>
                <el-dialog title="添加投票" :visible.sync="votewin" width="30%">
                  <el-form :model="voteform">
                    <el-form-item label="问题：">
                      <el-input  v-model="voteform.question" autocomplete="off" style="width: 300px"></el-input>
                    </el-form-item>
                    <el-form-item label="截止时间：">
                      <el-col :span="8">
                        <el-date-picker type="date" placeholder="选择日期" v-model="voteform.endtime" style="width: 100%;"></el-date-picker>
                      </el-col>
                      <el-col class="line" :span="2">-</el-col>
                      <el-col :span="8">
                        <el-time-picker placeholder="选择时间" v-model="voteform.date2" style="width: 80%;"></el-time-picker>
                      </el-col>
                    </el-form-item>
                    <el-form-item label="选项：">
                      <el-tag
                              :key="tag"
                              v-for="tag in voteform.children"
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
                      <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 投票选项</el-button>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="votewin = false">取 消</el-button>
                    <el-button type="primary" @click="addVote">确 定</el-button>
                  </div>
                </el-dialog>

              </div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m7.png" @click="votewin=true" class="image">
                <span class="mtext">发起问卷</span>
              </div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m5.png" @click="mydata" class="image">
                <span class="mtext">新建活动</span>
              </div></el-col>
            </el-row>
            <el-divider></el-divider>
            <el-row :gutter="20">
              <el-col :span="6"><div class="grid-content bg-purple">
                <div>
                  <img src="../assets/icon/m4.png" @click="mydata" class="image">
                  <span class="mtext">签到管理</span>
                </div>
              </div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m8.png" @click="mydata" class="image">
                <span class="mtext">积分管理</span>
              </div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m9.png" @click="mydata" class="image">
                <span class="mtext">社团管理</span>
              </div></el-col>
              <el-col :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m2.png" @click="mydata" class="image">
                <span class="mtext">成绩导入</span>
              </div></el-col>
            </el-row>
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
              <span class="mytext1"><el-button @click="myvote" type="text">我的投票</el-button></span>
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
  .mtext{
    margin-top: 60px;
    font-weight: 500;
    margin-left: 2%;
    font-size: 15px;
  }
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
        publicMoney:false,//班费公开，私密权限
        Money:false,//班费明细
        innerVisible:false,//记账窗口
        votewin:false,//投票创建窗口开关
        codeurl:'', //二维码路由
        signlog:[],//签到记录
        dialogVisible:false,
        queryInfo: {
          query: "",
          pageNum: 1,
          pageSize: 4,

        },
        inputVisible: false,
        inputValue: '',
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
        //投票创建
        voteform:{
          question:"",
          children:[],
          endtime:"",
          date2:"",
          name:"",
          major:"",
        },
        //班费记录
        MoneyData:[{time:"2022-04-26T16:00:00.000Z",name:"胡俊杰",money:65.5,addusername:"王小明",id:1},
          {time:"2022-04-26T16:00:00.000Z",name:"胡俊杰",money:65.5,addusername:"王小明",id:2},
          {time:"2022-04-23T16:00:00.000Z",name:"胡俊杰",money:65.5,addusername:"王小明",id:3},
          {time:"2022-04-26T16:00:00.000Z",name:"胡俊杰",money:65.5,addusername:"王小明",id:4},
          {time:"2022-04-25T16:00:00.000Z",name:"胡俊杰",money:65.5,addusername:"王小明",id:5},
          {time:"2022-04-26T16:00:00.000Z",name:"胡俊杰",money:65.5,addusername:"王小明",id:6},
          {time:"2022-04-24T16:00:00.000Z",name:"胡俊杰",money:65.5,addusername:"王小明",id:7},
        ],
        //记账表单
        formInline:{
          id:0,
          name:"",
          time:"",
          money:"",
          addusername:"",
          major:"",
          config:false,
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
      //进入投片页面
      myvote(){
        this.$router.push("/myvote")
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
      //删除投票选项
      handleClose(tag) {
        this.voteform.children.splice(this.voteform.children.indexOf(tag), 1);
      },
      //输入投票
      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
      //将选项放入数组
      handleInputConfirm() {
        let inputValue = this.inputValue;
        const arr = ["A:","B:","C:","D:"]
        let len = this.voteform.children.length
        if(len==4){
          this.$message.error("最多只能添加4个选项偶！")
        }else{
          if (inputValue) {
            this.voteform.children.push(arr[len]+inputValue);
          }
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      //添加投票到后台
      async addVote() {
        // 调用post请求
        this.voteform.name =window.sessionStorage.getItem("user");
        this.voteform.major =window.sessionStorage.getItem("major");
        const {data: res} = await this.$http.post("api/Vote/addvote" ,this.voteform);
        if(res=="success"){
          this.$message.success("发布成功");
          this.votewin = false;
        }else{
          this.$message.error("发布失败")
        }
      },
      //记账添加
      addMoney(){

      },

      //记账编辑
      updateMoney(row){
        this.innerVisible = !this.innerVisible
        this.formInline.name=row.name;
        this.formInline.money =row.money;
        this.formInline.id = row.id;

      },

      //记账删除
      delMoney(id){

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

