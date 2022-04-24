<template>
  <div>
      <!-- 面包屑导航 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>获奖记录</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card style="height: 200px">
          <el-carousel :interval="6000" type="card" height="150px">
              <el-carousel-item v-for="(item) in img" :key="item">
                  <img :src="item.imgs" style="height: 310px;">
              </el-carousel-item>
          </el-carousel>
      </el-card>
      <el-card shadow="true" style="margin-top: 4px">
          <el-row :gutter="25">
              <el-col :span="8">
                  <!-- 搜索 -->
                  <el-input placeholder="请输入获奖名称" v-model="queryInfo.query" clearable @clear="getOrderdata">
                      <el-button slot="append" icon="el-icon-search" @click="getOrderdata"></el-button>
                  </el-input>
              </el-col>
              <el-col :span="4">
                  <!-- 添加 -->
                  <el-button type="primary" @click="add">添加奖励</el-button>
              </el-col>
          </el-row>
      </el-card>
      <el-card>
          <div style="margin-left:1%;margin-right:1%">
              <el-row>
                  <el-col :span="4" v-for="(item) in awardlist" :key="item">
                      <div style="margin-top:15px;margin-left:2%;">
                          <el-card :body-style="{ padding: '0px'}" shadow="hover" style="height: 250px">
                             <el-image :src="item.pic" style="height: 80%;"></el-image>
                              <div>
                                  <span style="color: #ee9900;font-size: 15px">获奖名称:{{item.aname}}</span><br>
                                  <div class="bottom clearfix">
                                      <time class="time"><strong>获得时间:</strong>{{item.gettime}}</time>
                                      <el-button type="text" class="button" @click="delitem(item)">删除</el-button>
                                      <el-button type="text" class="button" @click="showitem(item)">查看</el-button>
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

      <el-dialog title="奖励详情" :visible.sync="dialogFormVisible" width="20%">
          <el-form :model="form">
              <el-form-item label="奖励名称">
                  <el-input v-model="form.aname" autocomplete="off" style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="获奖等级">
                  <el-select v-model="form.level" placeholder="" style="width: 200px">
                      <el-option label="A类" value="A类"></el-option>
                      <el-option label="B类" value="B类"></el-option>
                      <el-option label="C类" value="C类"></el-option>
                      <el-option label="D类" value="D类"></el-option>
                      <el-option label="E类" value="E类"></el-option>
                      <el-option label="F类" value="F类"></el-option>
                  </el-select>
              </el-form-item>
              <el-form-item label="获奖时间">
                  <el-input v-model="form.gettime" autocomplete="off" style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="获奖分数">
                  <el-input v-model="form.getgrade" disabled autocomplete="off" style="width: 200px"></el-input>
              </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="updateAward">保存修改</el-button>
          </div>
      </el-dialog>


      <!-- 分页 -->

      <el-drawer
              title="奖励申报"
              :visible.sync="drawer"
              :direction="direction"
              :before-close="handleClose">
              <el-form :label-position="labelPosition" label-width="130px" :model="formLabelAlign">
              <el-form-item label="奖励名称:">
                  <el-input v-model="formLabelAlign.aname" style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="获取时间:">
                  <el-input v-model="formLabelAlign.gettime"  style="width: 200px"></el-input>
              </el-form-item>
             <el-form-item label="级别:">
                                      <el-select v-model="formLabelAlign.level" placeholder="请选择奖励级别">
                                          <el-option label="A类" value="A类"></el-option>
                                          <el-option label="B类" value="B类"></el-option>
                                          <el-option label="C类" value="C类"></el-option>
                                          <el-option label="D类" value="D类"></el-option>
                                          <el-option label="E类" value="E类"></el-option>
                                          <el-option label="F类" value="F类"></el-option>
                                      </el-select>
                                  </el-form-item>
              <el-form-item label="图片:">
                       <el-upload class="upload-demo"
                                              :action="datas"
                                              :on-preview="handlePreview"
                                              :on-remove="handleRemove"
                                              :file-list="fileList"
                                              :on-success="handleAvatarSuccess"
                                              :limit="1"
                                              list-type="picture">
                                          <el-button size="small" type="primary">点击上传</el-button>
                                          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                                      </el-upload>
              </el-form-item>
                  <el-form-item size="large">
                      <el-button type="primary" @click="onSubmit">确定</el-button>
                      <el-button @click="drawer=false">取消</el-button>
                  </el-form-item>
          </el-form>
      </el-drawer>
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
        height: 80px;
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
                //
                formLabelWidth:100,
                dialogFormVisible:false,
                datas:"",
                queryInfo: { //参数控制
                    query: "",
                    pageNum: 1,
                    pageSize: 8,
                },
                total:0,
                awardlist:[],
                fileList: [],
                drawer:false,
                direction: 'rtl',
                labelPosition: 'right',
                formLabelAlign:{
                    uid:0,
                    pic:"",
                    aname:"",
                    gettime:"",
                    level:"",

                },
                img:[
                    {imgs:"https://tse1-mm.cn.bing.net/th/id/R-C.972ea47c97f3aa3e1d678d73d15886af?rik=H1g6X3XtDI0B%2bw&riu=http%3a%2f%2fbpic.588ku.com%2fback_pic%2f03%2f56%2f74%2f94579ef3f56c1f8.jpg!%2ffh%2f300%2fquality%2f90%2funsharp%2ftrue%2fcompress%2ftrue&ehk=EaRqWCB8%2bFiS01DHkr%2fzthgRXcZBEQRyMiskOdDjZI4%3d&risl=&pid=ImgRaw&r=0"
                    },
                    {imgs:"https://img95.699pic.com/photo/40032/7537.jpg_wh300.jpg!/fh/300/quality/90"
                    },
                    {imgs:"https://img.tukuppt.com/ad_preview/00/78/50/60b76ff2006bc.jpg!/fw/980"
                    }
                ],
                form:{

                }

            }
        },
        created() {
            this.getawarddata();
            this.datas = "http://localhost:9002/api/Award/uploadAwardpics?id="+
                window.sessionStorage.getItem('id');

        },
        methods: {

           async  getawarddata(){
                 // 调用post请求
                 const { data: res } = await this.$http.get("api/Award/getmyAwardofid?id="+window.sessionStorage.getItem('id'));
                 this.awardlist = res.data; // 将返回数据赋值
               this.total = res.numbers; // 总个数
               //处理路径
               console.log("处理路径")
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
            },
            //添加窗口打开
            add(){
                this.drawer =true;
            },

            async updateAward() {
                const {data: res} = await this.$http.post("api/Award/upsdateAward",this.form);
                if (res.code == 1) {
                    this.$message.success(res.msg)
                    this.getawarddata();
                } else {
                    this.$message.error(res.msg)
                }
            },
            //查看详情
            showitem(item) {
                this.dialogFormVisible = true;
                this.form = item;
            },
            //删除
            async delitem(item) {
                const confirmResult = await this.$confirm('是否确认删除该奖励?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 成功删除为confirm 取消为 cancel
                if (confirmResult != 'confirm') {
                    return this.$message.info("已取消操作");
                }
                const { data: res } = await this.$http.get("api/Award/deleAward?id="+item.id);
                if(res.code==1){
                    this.$message.success(res.msg)
                    this.getawarddata();
                }else{
                    this.$message.error(res.msg)
                }
            },

            async  onSubmit(){
                // 调用post请求
                this.formLabelAlign.uid = window.sessionStorage.getItem("id");
                const { data: res } = await this.$http.post("api/Award/addsubject",this.formLabelAlign);
                if(res.code==1){
                    this.$message.success(res.msg)
                    this.getawarddata();
                }else{
                    this.$message.error(res.msg)
                }
            },


            async  getOrderdata(){
                // 调用post请求
                const { data: res } = await this.$http.get("api/Award/getselectAwardCheck?id="+window.sessionStorage.getItem("id")
                +"&aname="+this.queryInfo.query);
                if(res.code==1){
                    this.awardlist = res.data; // 将返回数据赋值
                    this.total = res.numbers; // 总个数
                }else{
                    this.$message.error(res.msg)
                }
            },
            //头像上传
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            //头像上传
            handlePreview(file) {
                console.log(file);
            },
            //上传头像成功显示
            handleAvatarSuccess(res, file) {
                this.$message.success("上传成功")
                this.formLabelAlign.pic= res;
            }
        },
    }
</script>
