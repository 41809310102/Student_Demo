<template>
  <div>
      <!-- 面包屑导航 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>获奖记录</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card style="height: 200px">
          <el-carousel :interval="6000" type="card" height="150px">
              <el-carousel-item v-for="item in img" :key="item">
                  <img :src="item.img" style="height: 180px;"  alt="">
              </el-carousel-item>
          </el-carousel>
      </el-card>
      <el-card shadow="true" style="margin-top: 4px">
          <el-row :gutter="25">
              <el-col :span="8">
                  <!-- 搜索添加 -->
                  <el-input placeholder="请输入获奖名称" v-model="queryInfo.query" clearable @clear="getOrderdata">
                      <el-button slot="append" icon="el-icon-search" @click="getOrderdata"></el-button>
                  </el-input>
              </el-col>
          </el-row>
      </el-card>
      <el-card>
          <div style="margin-left:1%;margin-right:1%">
              <el-row>
                  <el-col :span="4" v-for="(item) in 8" :key="item.classId" :offset="1" >
                      <div style="margin-top:15px">
                          <el-card :body-style="{ padding: '0px'}" shadow="hover">
                              <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
                              <div>
                                  <span style="color: #ee9900">获奖名称:</span><br>
                                  <div class="bottom clearfix">
                                      <time class="time"><strong>获得时间:</strong>{{item.classCreatetime}}</time>
                                      <el-button type="text" class="button" @click="add(item)">查看</el-button>
                                  </div>
                              </div>
                          </el-card>
                      </div>
                  </el-col>
              </el-row>
              <div style="margin-left:35%">
                  <div class="block">
                      <el-pagination
                              @size-change="handleSizeChange"
                              @current-change="handleCurrentChange"
                              :current-page="queryInfo.pageNum"
                              :page-sizes="[4,8,12]"
                              :page-size="queryInfo.pageSize"
                              layout="total, sizes, prev, pager, next"
                              :total="total">
                      </el-pagination>
                  </div>
              </div>
          </div>
      </el-card>
      <!-- 分页 -->

      <!-- 获奖详细
      <el-drawer
              title="用户档案详细"
              :visible.sync="drawer"
              :direction="direction"
              :before-close="handleClose">
          <el-form :label-position="labelPosition" label-width="130px" :model="formLabelAlign">
              <el-form-item label="姓名:">
                  <el-input v-model="formLabelAlign.username" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="证件类型:">
                  <el-input v-model="formLabelAlign.idtype" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="证件号:">
                  <el-input v-model="formLabelAlign.idnum" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="性别:">
                  <el-input v-model="formLabelAlign.sex" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="出生日期:">
                  <el-input v-model="formLabelAlign.birthday" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="手机号:">
                  <el-input v-model="formLabelAlign.phone" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="现居住地址:">
                  <el-input v-model="formLabelAlign.nowlivelocal" disabled style="width: 200px"></el-input>
              </el-form-item>
          </el-form>
      </el-drawer>-->
  </div>
</template>

<style lang="less" scoped>
    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .button {
        padding: 0;
        float: right;
    }

    .image {
        width: 90%;
        display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
    .success_massage{
        width: 300px;
        margin: 0 40%;
        background-color: aqua;
        font-size: 30px;
        text-align: center;
    }
    .go_home{
        margin-top:15px;
        margin-bottom: 10px;
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>

<script>
    export default {
        data() {
            return {
              Ordertable:[],
                // 请求数据
                queryInfo: { //参数控制
                    query: "",
                    pageNum: 1,
                    pageSize: 8,
                },
                total:8,
                drawer:false,
                direction: 'rtl',
                labelPosition: 'right',
                formLabelAlign1:{
                    username: '',
                    idtype: '',
                    idnum: '',
                    sex: '',
                    birthday: '',
                    phone: '',
                    nowlivelocal: '',
                    homeinfor: '',
                    persontype: '',
                    worklocal: '',
                },
                img:[
                    {
                        img:"https://tse1-mm.cn.bing.net/th/id/R-C.972ea47c97f3aa3e1d678d73d15886af?rik=H1g6X3XtDI0B%2bw&riu=http%3a%2f%2fbpic.588ku.com%2fback_pic%2f03%2f56%2f74%2f94579ef3f56c1f8.jpg!%2ffh%2f300%2fquality%2f90%2funsharp%2ftrue%2fcompress%2ftrue&ehk=EaRqWCB8%2bFiS01DHkr%2fzthgRXcZBEQRyMiskOdDjZI4%3d&risl=&pid=ImgRaw&r=0"
                    },
                    {
                        img:"https://img95.699pic.com/photo/40032/7537.jpg_wh300.jpg!/fh/300/quality/90"
                    },
                    {
                        img:"https://img.tukuppt.com/ad_preview/00/78/50/60b76ff2006bc.jpg!/fw/980"
                    }
                ]



            }
        },
        created() {
            //this.getOrderdata();
        },
        methods: {

           async  getOrderdata(){
                 // 调用post请求
                 const { data: res } = await this.$http.post("api/user/toOrder",this.queryInfo);
                 this.Ordertable = res.data; // 将返回数据赋值
                 this.total = res.numbers; // 总个数
            },
            // 监听pageSize改变的事件
            handleSizeChange(newSize) {
                this.queryInfo.pageSize = newSize;
                //this.getOrderdata(); // 数据发生改变重新申请数据
            },
            // 监听pageNum改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.pageNum = newPage;
               // this.getOrderdata();// 数据发生改变重新申请数据
            },

            handleClose() {
              this.drawer = false;
              this.drawer1 = false;
            },

            async  getUserdata(userid){
                this.drawer = true;
                // 调用post请求
                const { data: res } = await this.$http.get("api/wxuser/dangan?userid="+userid);
                if(res!=null){
                    this.formLabelAlign = res[0];
                    console.log(res)
                }
            },
            async  getDoctorid(doctorid){
                this.drawer1 = true;
                // 调用post请求
                const { data: res } = await this.$http.get("api/doctor/dangan?doctorid="+doctorid);
                if(res!=null){
                    this.formLabelAlign1 = res[0];
                    console.log(res)
                }
            }
        },
    }
</script>
