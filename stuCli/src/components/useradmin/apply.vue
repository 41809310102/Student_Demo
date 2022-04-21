<template>
  <div>
      <!-- 面包屑导航 -->
      <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>用户信息</el-breadcrumb-item>
          <el-breadcrumb-item>接种信息</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card>
          <el-row :gutter="25">
              <el-col :span="8">
                  <!-- 搜索添加 -->
                  <el-input placeholder="请输入数据" v-model="queryInfo.query" clearable @clear="getOrderdata">
                      <el-button slot="append" icon="el-icon-search" @click="getOrderdata"></el-button>
                  </el-input>
              </el-col>
          </el-row>
          <el-table height="500"
                    :data="Ordertable"
                    border
                    style="width: 80%">
              <el-table-column
                      prop="id"
                      label="预约编号"
                      width="80">
              </el-table-column>
              <el-table-column
                      prop="wxusername"
                      label="受种用户姓名"
                      width="120">
              </el-table-column>
              <el-table-column
                      prop="local"
                      width="120"
                      label="接种地点">
              </el-table-column>
              <el-table-column
                      prop="ordertime"
                      width="120"
                      label="接种时间">
              </el-table-column>
              <el-table-column
                      prop="vaccid"
                      width="120"
                      label="接种时间">
              </el-table-column>
              <el-table-column label="受种者档案编号"   width="140px" prop="userid">
                  <template slot-scope="scope">
                      <el-button type="text" @click="getUserdata(scope.row.userid)">{{scope.row.userid}}</el-button>
                  </template>
              </el-table-column>
              <el-table-column label="接种医生编号"   width="140px" prop="doctorid">
                  <template slot-scope="scope">
                      <el-button type="text" @click="getDoctorid(scope.row.doctorid)">{{scope.row.doctorid}}</el-button>
                  </template>
              </el-table-column>
              <el-table-column
                      prop="doctorname"
                      width="120"
                      label="接种医生姓名">
              </el-table-column>
          </el-table>
      </el-card>
      <!-- 分页 -->
      <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="queryInfo.pageNum"
              :page-sizes="[10, 20, 40, 80]"
              :page-size="queryInfo.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
      ></el-pagination>
      <!-- 用户档案详细 -->
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
              <el-form-item label=" 户籍地址:">
                  <el-input v-model="formLabelAlign.homeinfor" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="人群分类:">
                  <el-input v-model="formLabelAlign.persontype" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="工作单位:" >
                  <el-input v-model="formLabelAlign.worklocal" disabled style="width: 200px"></el-input>
              </el-form-item>
          </el-form>
      </el-drawer>
      <!-- 医生档案详细 -->
      <el-drawer
              title="医生档案详细"
              :visible.sync="drawer1"
              :direction="direction1"
              :before-close="handleClose">
          <el-form :label-position="labelPosition1" label-width="130px" :model="formLabelAlign1">
              <el-form-item label="姓名:">
                  <el-input v-model="formLabelAlign1.username" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="证件类型:">
                  <el-input v-model="formLabelAlign1.idtype" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="证件号:">
                  <el-input v-model="formLabelAlign1.idnum" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="性别:">
                  <el-input v-model="formLabelAlign1.sex" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="出生日期:">
                  <el-input v-model="formLabelAlign1.birthday" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="手机号:">
                  <el-input v-model="formLabelAlign1.phone" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="现居住地址:">
                  <el-input v-model="formLabelAlign1.nowlivelocal" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label=" 户籍地址:">
                  <el-input v-model="formLabelAlign1.homeinfor" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="人群分类:">
                  <el-input v-model="formLabelAlign1.persontype" disabled style="width: 200px"></el-input>
              </el-form-item>
              <el-form-item label="工作单位:" >
                  <el-input v-model="formLabelAlign1.worklocal" disabled style="width: 200px"></el-input>
              </el-form-item>
          </el-form>
      </el-drawer>
  </div>
</template>

<style lang="less" scoped>
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
              Ordertable:[],
                // 请求数据
                queryInfo: {
                    query: "",
                    pageNum: 1,
                    pageSize: 10,
                },
                total:0,
                drawer:false,
                direction: 'rtl',
                labelPosition: 'right',
                formLabelAlign:{
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
                drawer1:false,
                direction1: 'rtl',
                labelPosition1: 'right',
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
            }
        },
        created() {
            this.getOrderdata();
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
                this.getOrderdata(); // 数据发生改变重新申请数据
            },
            // 监听pageNum改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.pageNum = newPage;
                this.getOrderdata();// 数据发生改变重新申请数据
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