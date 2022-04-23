<template>
    <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>我的资料</el-breadcrumb-item>
    </el-breadcrumb>
        <el-row :gutter="10">
            <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="3"><div class="grid-content bg-purple">
                <el-card shadow="true" style="height: 650px">
                    <div class="schoolpic">
                        <el-image :src="src" style="width: 120px; height: 120px;margin-left: 28px"></el-image>
                    </div>
                    <div style="margin-left: 20px">
                        <el-rate
                                v-model="value"
                                disabled
                                show-score
                                text-color="#ff9900"
                                score-template="{value}">
                        </el-rate>
                    </div>
                    <el-divider></el-divider>
                    <span>{{desction}}</span>
                    <el-divider></el-divider>
                </el-card>

            </div></el-col>
            <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="9"><div class="grid-content bg-purple">
              <el-card shadow="true" style="height: 650px;">
                  <el-row :gutter="20">
                      <el-col :span="8"><div class="grid-content bg-purple">
                          <el-card style="width: 140px ;margin-left: 8px" >
                              <div>
                                  <i @click="dialogVisible=true" class="el-icon-edit" style="margin-left: 80px"></i>
                                  <el-image :src="user.pic" style="width: 100px; height: 130px;" ></el-image>
                                  <el-dialog
                                          title="头像更改"
                                          :visible.sync="dialogVisible"
                                          width="30%"
                                          >
                                      <el-upload
                                              class="upload-demo"
                                              action="http://localhost:9002/api/user/updateUserpic?id=1"
                                              :on-preview="handlePreview"
                                              :on-remove="handleRemove"
                                              :file-list="fileList"
                                              :on-success="handleAvatarSuccess"
                                              :limit="1"
                                              list-type="picture">
                                          <el-button size="small" type="primary">点击上传</el-button>
                                          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                                      </el-upload>
                                      <span slot="footer" class="dialog-footer">
                                      <el-button @click="dialogVisible = false">取 消</el-button>
                                      <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
                                  </el-dialog>
                              </div>
                          </el-card>
                      </div></el-col>
                      <el-col :span="16"><div class="grid-content bg-purple">
                              <div style="margin-top: 20px;color: #04befe">
                                  <span>姓名:{{user.username}}</span><br>
                              </div>
                              <div  style="margin-top: 5px;color: #04befe">
                                  <span style="margin-top: 10px">年龄:{{user.age}}</span><br>
                              </div>
                              <div style="margin-top: 5px;color: #04befe">
                                 <span style="margin-top: 10px">学号:{{user.cardid}}</span><br>
                              </div>
                              <div style="margin-top: 5px;color: #04befe">
                                  <span style="margin-top: 10px">班级:{{user.major}}</span><br>
                              </div>
                              <div style="margin-top: 5px;color: #04befe">
                                  <span style="margin-top: 10px">学院:{{user.shool_group}}</span><br>
                              </div>
                      </div></el-col>
                  </el-row>
                  <el-row :gutter="20">
                      <el-divider></el-divider>
                      <div>
                          <el-tag type="success">{{user.role}}</el-tag>
                      </div>
                      <span style="font-weight:500;font-size: 14px;color: #ee9900">{{user.desction}}}</span>
                  </el-row>
                  <el-tabs v-model="activeName" @tab-click="handleClick">
                      <el-tab-pane label="其他信息" name="first">
                          <div style="margin-top: 20px;color: #04befe">
                              <span style="margin-top: 20px">身份:{{user.role}}</span><br>
                          </div>
                          <div style="margin-top: 20px;color: #04befe">
                              <span style="margin-top: 20px">爱好:{{user.hobby}}</span><br>
                          </div>
                          <div  style="margin-top: 20px;color: #04befe">
                              <span style="margin-top: 20px">联系方式:{{user.phone}}</span><br>
                          </div>
                          <div style="margin-top: 20px;color: #04befe">
                              <span style="margin-top: 20px">所属社团:{{user.association}}</span><br>
                          </div>
                          <div style="margin-top: 20px;color: #04befe">
                              <span style="margin-top: 20px">账号状态:
                                  <div v-if="user.isdelect==1" style="margin-top: -28px;margin-left: 80px">
                                  <el-tag type="danger">已冻结</el-tag></div>
                              <div v-if="user.isdelect==0" style="margin-top: -28px;margin-left: 80px">
                                  <el-tag type="success">账号正常</el-tag></div></span><br>
                          </div>
                          <div style="color: #04befe">
                              <span>第二课堂分:{{user.grade}}</span><br>
                          </div>
                      </el-tab-pane>
                      <el-tab-pane label="资料修改" name="second">
                          <el-card style="width: 650px">
                              <el-form ref="form" :inline="true" :model="sizeForm" label-width="60px" size="mini">
                                  <el-form-item label="姓名:">
                                      <el-input v-model="sizeForm.username" style="width: 120px"></el-input>
                                  </el-form-item>
                                  <el-form-item label="学号:">
                                      <el-input v-model="sizeForm.cardid" style="width: 120px"></el-input>
                                  </el-form-item>
                                  <el-form-item label="电话:">
                                      <el-input v-model="sizeForm.phone" style="width: 120px"></el-input>
                                  </el-form-item>
                                  <el-form-item label="性别:">
                                      <el-input v-model="sizeForm.sex" style="width: 120px"></el-input>
                                  </el-form-item>
                                  <el-form-item label="年龄:">
                                      <el-input v-model="sizeForm.age" style="width: 120px"></el-input>
                                  </el-form-item>
                                  <el-form-item label="学院:">
                                      <el-select v-model="sizeForm.shool_group" placeholder="请选择活动区域">
                                          <el-option label="管理学院" value="管理学院"></el-option>
                                          <el-option label="理学院" value="理学院"></el-option>
                                          <el-option label="计算机科学学院" value="计算机科学学院"></el-option>
                                          <el-option label="机械工程学院" value="机械工程学院"></el-option>
                                          <el-option label="材料工程学院" value="材料工程学院"></el-option>
                                          <el-option label="物理学院" value="物理学院"></el-option>
                                      </el-select>
                                  </el-form-item>
                                  <el-form-item label="班级:">
                                      <el-input v-model="sizeForm.major" style="width: 120px"></el-input>
                                  </el-form-item>
                                  <el-form-item label="爱好:">
                                      <el-input v-model="sizeForm.hobby" style="width: 200px"></el-input>
                                  </el-form-item>
                                  <el-form-item label="介绍:">
                                      <el-input v-model="sizeForm.desction" ></el-input>
                                  </el-form-item>
                                  <el-form-item size="large">
                                      <el-button type="primary" @click="onSubmit">保存更改</el-button>
                                      <el-button @click="onSubmit">取消</el-button>
                                  </el-form-item>
                              </el-form>
                          </el-card>
                      </el-tab-pane>
                  </el-tabs>

              </el-card>

            </div></el-col>

        </el-row>
    </div>
</template>

<style lang="less" scoped>
    .schoolpic{
        border-radius: 50%;
    }
    .el-col {
        border-radius: 4px;
    }
    .bg-purple-dark {

    }
    .bg-purple {

    }
    .bg-purple-light {

    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
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
</style>

<script>
    export default {
        data() {
            return {
                dialogVisible:false,
                fileList: [],
                sizeForm: {

                },
                src:"https://s.cn.bing.net/th?id=OIP-C.52HK9IvoYOH24_rR0KWSdwHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
                desction:"陕西科技大学是我国西部地区唯一一所以轻工为特色的多科性大学，是国家“中西部高校基础能力建设工程”建设高校，是“十二五”期间陕西省重点建设的高水平大学",
                value:3.2,
                url:"",//上传图片地址
                activeName: 'first',
                user:{

                },
                // 请求数据
                queryInfo: {
                    query: "",
                    pageNum: 1,
                    pageSize: 10,
                },
             total:0,
                applytable:[],
            }
        },
        created() {
            this.getUserdatabyid();
            this.url = "http://localhost:9002/api/user/updateUserpic?id="+window.sessionStorage.getItem('id');
        },
        methods: {

            async  getUserdatabyid(){
                // 调用post请求
                let id = window.sessionStorage.getItem('id');
                const { data: res } = await this.$http.get("api/user/selectUserbyid?id="+id);
                this.user = res; // 将返回数据赋值
                this.sizeForm = res;
            },

            async onSubmit() {
                const {data: res} = await this.$http.post("api/user/updateUser", this.sizeForm);
                if(res=="success"){
                    this.$message.success("修改成功")
                }else{
                    this.$message.error("修改成功")
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
                this.getUserdatabyid();
                //this.imageUrl = URL.createObjectURL(file.raw);
            }
        },
    }
</script>
