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
                  <img src="../assets/icon/m1.png" @click="getMoney" class="image">
                  <span class="mtext">班费明细</span>
                </div>
                <el-dialog title="班费明细" :visible.sync="Money" width="60%">
                  <el-row :gutter="20">
                    <el-col :span="3"><div class="grid-content bg-purple">
                      <el-button type="danger" @click="innerVisible=true">记账</el-button>
                    </div></el-col>
                    <el-col :span="18" v-if="innerVisible"><div class="grid-content bg-purple">
                      <el-form :inline="true" :model="formInline" class="demo-form-inline">
                        <el-form-item label="姓名:">
                          <el-input v-model="formInline.name"  clearable placeholder="输入缴纳/支出人姓名" style="width: 120px;"></el-input>
                        </el-form-item>
                        <el-form-item label="费用：">
                          <el-input v-model="formInline.money"  clearable placeholder="金额" style="width: 120px;"></el-input>
                        </el-form-item>
                        <el-form-item label="备注：">
                          <el-input v-model="formInline.bz"  clearable placeholder="备注" style="width: 120px;"></el-input>
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
                  <el-row :gutter="20">
                    <el-col :span="14"><div class="grid-content bg-purple">
                      <el-table :data="MoneyData" height="350" border style="width: 100%"  :default-sort = "{prop: 'time', order: 'descending'}">
                        <el-table-column prop="time" sortable label="日期" width="140"></el-table-column>
                        <el-table-column prop="name" label="姓名" width="80"></el-table-column>
                        <el-table-column prop="money" label="缴纳/元" width="80"></el-table-column>
                        <el-table-column prop="bz" label="备注" width="100">
                          <template slot-scope="scope">
                            <el-popover placement="top-start" title="" width="100" trigger="hover" effect="dark" >{{scope.row.bz}}
                              <span v-model="scope.row.bz" slot="reference" style="color:blue">{{scope.row.bz| ellipsis}}</span>
                            </el-popover>
                          </template>
                        </el-table-column>
                        <el-table-column prop="addusername" label="记账人" width="100"></el-table-column>
                        <el-table-column
                                fixed="right"
                                label="操作"
                                width="100">
                          <template slot-scope="scope">
                            <el-button  @click="updateMoney(scope.row)" type="text" size="small">修改</el-button>
                            <el-button type="text" @click="delMoney(scope.row.id)" size="small">删除</el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                    </div></el-col>
                    <el-col :span="8"><div class="grid-content bg-purple">
                      <el-card>
                          <div><el-tag type="success">班费数据统计表:</el-tag></div>
                          <div style="height: 300px;width: 300px" id="main">
                          </div>
                      </el-card>
                    </div></el-col>
                  </el-row>
                </el-dialog>
              </div></el-col>
              <el-col v-if="studentadmin" :span="6"><div class="grid-content bg-purple">
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
              <el-col v-if="studentadmin" :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m7.png" @click="myquestion" class="image">
                <span class="mtext">发起问卷</span>
              </div></el-col>
              <el-col v-if="studentadmin" :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m5.png" @click="addactionwin = true" class="image">
                <span  class="mtext">新建活动</span>
                <el-dialog title="新建活动" :visible.sync="addactionwin" width="40%">
                  <el-form :model="actionform">
                    <el-form-item label="活动名称:" label-width="120" >
                      <el-input v-model="actionform.title" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间:" label-width="120">
                      <el-input  type="date" v-model="actionform.starttime" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="结束时间:" label-width="120">
                      <el-input type="date"  v-model="actionform.endtime" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="人数上限:" label-width="120">
                      <el-input v-model="actionform.limitnum" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="活动积分:" label-width="120">
                      <el-input v-model="actionform.jifen" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="addactionwin = false">取 消</el-button>
                    <el-button type="primary" @click="addactionofSen">确 定</el-button>
                  </div>
                  </el-dialog>
              </div></el-col>
            </el-row>
            <el-divider></el-divider>
            <el-row :gutter="20">
              <el-col v-if="teacheradmin" :span="6"><div class="grid-content bg-purple">
                <div>
                  <img src="../assets/icon/m4.png" @click="qiandao" class="image">
                  <span class="mtext">签到管理</span>
                </div>
              </div></el-col>
              <el-col v-if="teacheradmin" :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m8.png" @click="jifen" class="image">
                <span class="mtext">活动管理</span>
              </div></el-col>
              <el-col v-if="teacheradmin" :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m9.png" @click="assocation" class="image">
                <span class="mtext">社团管理</span>
                <el-dialog title="社团管理" :visible.sync="setuan">
                  <el-row :gutter="20">
                    <el-col :span="16"><div class="grid-content bg-purple">
                      <el-table :data="assoc" border   style="width: 80%">
                        <el-table-column property="name" label="社团名称" width="100"></el-table-column>
                        <el-table-column property="createuser" label="创建人" width="80"></el-table-column>
                        <el-table-column property="desction" label="社团描述"  width="100"></el-table-column>
                        <el-table-column fixed="right" label="操作" width="130">
                          <template slot-scope="scope">
                            <el-button v-if="!quxiao" @click="xiougaituandui(scope.row)" type="text" size="small"><el-tag size="mini" type="success">修改</el-tag></el-button>
                            <el-button v-if="quxiao" @click="quxiaofun" type="text" size="small"><el-tag size="mini" type="success">取消</el-tag></el-button>
                            <el-button @click="delTuandui(scope.row)" type="text" size="small"><el-tag size="mini" type="danger">删除</el-tag></el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                    </div></el-col>
                    <el-col :span="8"><div class="grid-content bg-purple">
                      <el-card style="margin-left:-50px">
                        <el-form :model="formshetuan">
                          <el-form-item label="社团名称:" >
                            <el-input v-model="formshetuan.name" autocomplete="off"></el-input>
                          </el-form-item>
                          <el-form-item label="创建人:" >
                            <el-input v-model="formshetuan.createuser" autocomplete="off"></el-input>
                          </el-form-item>
                          <el-form-item label="描述:">
                            <el-input v-model="formshetuan.desction" autocomplete="off"></el-input>
                          </el-form-item>
                          <el-form-item>
                            <el-button type="primary" @click="updatasetuan">确定</el-button>
                          </el-form-item>
                        </el-form>
                      </el-card>
                    </div></el-col>
                  </el-row>
                </el-dialog>
              </div></el-col>
              <el-col v-if="teacheradmin" :span="6"><div class="grid-content bg-purple">
                <img src="../assets/icon/m2.png" @click="addSuject=true" class="image">
                <span class="mtext">成绩导入</span>
                <el-dialog title="成绩导入" :visible.sync="addSuject" width="40%">
                  <el-upload
                          class="upload-demo"
                          drag
                          action="http://localhost:9002/api/subject/upload"
                          :on-success="addSuccess"
                          multiple>
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip">只能上传xlsx文件，且不超过500kb</div>
                  </el-upload>
                </el-dialog>
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
              <span class="mytext1"><el-button @click="mydatas" type="text">数据汇总</el-button></span>
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
              <span class="mytext1"><el-button @click="myquestions" type="text">我的问卷</el-button></span>
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
                              :timestamp="activity.starttime">
                        {{activity.title}}
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
  import * as echarts from 'echarts';
  export default {
    data () {
      return {
        studentadmin:false,//班干部权限
        teacheradmin:false,//教师权限
        addSuject:false,//成绩导入
        quxiao:false,
        setuan:false,//创建社团管理页面
        addactionwin:false,//新建活动窗口
        publicMoney:false,//班费公开，私密权限
        Money:false,//班费明细
        innerVisible:false,//记账窗口
        votewin:false,//投票创建窗口开关
        codeurl:'', //二维码路由
        signlog:[],//签到记录
        formshetuan:{ //社团表单
          id:0,
          createuser:"",
          desction:"",
          name:""
        },
        assoc:[],//社团信息记录
        dialogVisible:false,
        queryInfo: {
          query: "",
          pageNum: 1,
          pageSize: 4,

        },
        inputVisible: false,
        inputValue: '',
        reverse: true,
        actionform:{
          actionUser: "",
          actiontime: "",
          starttime: "",
          endtime: "",
          jifen: "",
          limitnum: 0,
          title: ""
        },
        activities: [{
             title:"dsnsdnd",
             starttime: "2022-4-23 10：00",
             endtime: "2022-4-23 11：00",
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
        MoneyData:[
        ],
        //记账表单
        formInline:{
          id:0,
          name:"",
          time:"",
          money:"",
          addusername:"",
          major:"",
          bz:"",
          config:false,
        },
      }
    },
    mounted: function () {

    },

    created() {
      this.getlog();
      this.getroot();
      this.getactions();
    },

    methods: {
      handleClick(row) {
        console.log(row);
      },

      //进入我的资料页面
       mydatas(){
         this.$router.push("/mydata");
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
      //进入问卷创建页面
      myquestion(){
        this.$router.push("/question")
      },
      //进入问卷填写页面
      myquestions(){
        this.$router.push("/myquestion")
      },
      //进入数据统计页面
      mydata(){
        this.$router.push("/wxuser")
      },
      jifen(){
        this.$router.push("/actionadmin")
      },
      qiandao(){
        this.$router.push("/sign")
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
      async addMoney() {
        // 调用post请求
        //先判断是编辑还是添加
        this.formInline.addusername =window.sessionStorage.getItem("user");
        if(this.formInline.id==0){
          this.formInline.major =window.sessionStorage.getItem("major");
          const {data: res} = await this.$http.post("api/classfee/addclassfee" ,this.formInline);
          if(res.code==1){
            this.$message.success(res.msg)
            await this.getMoney();
          }else{
            this.$message.error(res.msg)
          }
        }else{
          const {data: res} = await this.$http.post("api/classfee/updateclassfee" ,this.formInline);
          if(res.code==1){
            this.$message.success(res.msg)
            await this.getMoney();
          }else{
            this.$message.error(res.msg)
          }
        }
      },
      //记账记录获取
      async getMoney() {
        this.Money = true;
        const {data: res} = await this.$http.get("api/Classfee/getallClass?major=" + window.sessionStorage.getItem("major"));
        if (res.code == 1){
          this.MoneyData = res.data;
          this.getchart();//获取数据报表
        } else{
          this.$message.error(res.msg)
        }
      },
      //记账编辑
      updateMoney(row){
        this.innerVisible = !this.innerVisible
        this.formInline.name=row.name;
        this.formInline.money =row.money;
        this.formInline.id = row.id;
      },
      //记账删除
      async delMoney(id) {
        const confirmResult = await this.$confirm('是否确认删除该班费记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err => err)
        // 成功删除为confirm 取消为 cancel
        if (confirmResult != 'confirm') {
          return this.$message.info("已取消操作");
        }
        const {data: res} = await this.$http.get("api/classfee/delclassfee?id=" +id);
        if (res.code == 1) {
          this.$message.success(res.msg)
          await this.getMoney();
        } else {
          this.$message.error(res.msg)
        }
      },
      //创建活动逻辑
      async addactionofSen(){
        this.actionform.actionUser = window.sessionStorage.getItem("user");
        const {data: res} = await this.$http.post("api/Action/addAction" ,this.actionform);
        if (res.code == 1) {
          this.$message.success(res.msg);
          this.addactionwin = false;
        } else {
          this.$message.error(res.msg)
        }
      },
      //获取当前社团信息
      async assocation() {
        this.setuan = true;
        const {data: res} = await this.$http.post("api/association/SelectAss")
        if (res.code == 0) {
          this.assoc = res.data;
        } else {
          this.$message.error(res.msg);
        }
      },

      //获取当前用户参加活动清单
      async getactions() {
        const {data: res} = await this.$http.get("api/Apply/getapplybyid?id="+window.sessionStorage.getItem("id"))
        if (res.code == 1) {
          this.activities= res.data;
        } else {
          this.$message.error(res.msg);
        }
      },

      //编辑社团信息
      xiougaituandui(row) {
        this.quxiao = true;
        this.formshetuan = row;
      },
      quxiaofun(){
        this.quxiao = false;
        const arr = { //社团表单
          id: 0,
          createuser: "",
          desction: "",
          name: ""
        };
        this.formshetuan=arr;
      },
      //删除社团
      async delTuandui(row) {
        if(row.createuser!=window.sessionStorage.getItem("user")){
          this.$message.error("你不是该社团创建人，无法修改该社团")
          return;
        }
        const confirmResult = await this.$confirm('是否确认删除该活动记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err => err)
        // 成功删除为confirm 取消为 cancel
        if (confirmResult != 'confirm') {
          return this.$message.info("已取消操作");
        }
        const {data: res} = await this.$http.get("api/association/delSelect?id="+row.id)
        if (res.code == 1) {
           this.$message.success(res.msg)
           await this.assocation();
        } else {
          this.$message.error(res.msg);
        }
      },
      //保存社团或添加社团

      async updatasetuan() {
        if (this.formshetuan.id == 0){
          const {data: res} = await this.$http.post("api/association/addAss",this.formshetuan)
          if (res.code == 1) {
            this.$message.success(res.msg);
            const arr = { //社团表单
              id: 0,
              createuser: "",
              desction: "",
              name: ""
            };
            this.formshetuan=arr;
          } else {
            this.$message.error(res.msg);
          }
        } else {
          const {data: res} = await this.$http.post("api/association/updateAss",this.formshetuan)
          if (res.code == 1) {
            this.$message.success(res.msg);
            const arr = { //社团表单
              id: 0,
              createuser: "",
              desction: "",
              name: ""
            };
            this.formshetuan=arr;
          } else {
            this.$message.error(res.msg);
          }
        }
        this.quxiao = false;
        await this.assocation();
      },

      //获取班费支出报表明细
      async getchart() {
        //获取id并初始化图表
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;
        this.$message.success("正在统计班费使用情况")
        const {data: res} = await this.$http.get("api/Echart/getallClass?major=" + window.sessionStorage.getItem("major"));
        if (res.code == 1) {
          option = {
            tooltip: {
              trigger: 'item'
            },
            legend: {
              top: '5%',
              left: 'center'
            },
            series: [
              {
                name: '班费统计:',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                  borderRadius: 10,
                  borderColor: '#fff',
                  borderWidth: 2
                },
                label: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  label: {
                    show: true,
                    fontSize: '40',
                    fontWeight: 'bold'
                  }
                },
                labelLine: {
                  show: false
                },
                data: res.data
              }
            ]
          };
          option && myChart.setOption(option);
        } else {
          this.$message.error("删除失败")
        }

      },
      //判断当前用户的身份
      getroot(){
        const role = window.sessionStorage.getItem("role");
        if(role==="班干部"){
          this.studentadmin = true;
        }else if(role==="教师"){
          this.studentadmin = true;
          this.teacheradmin = true;
        }
      },

      //上传成绩
      addSuccess(res){
          this.$message.success(res);
          this.addSuject = false;
      }
    },
    //只显示前10个字符
    filters:{
      ellipsis(value){
        if (!value) return '';
        if (value.length > 8) {
          return value.slice(0,6) + '...'
        }
        return value
      }
    }

  }
</script>

