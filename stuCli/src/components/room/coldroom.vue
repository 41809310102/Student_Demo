<template>
   <div>
       <!-- 面包屑导航 -->
       <el-breadcrumb separator-class="el-icon-arrow-right">
           <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
           <el-breadcrumb-item>仓库管理</el-breadcrumb-item>
           <el-breadcrumb-item>库存管理</el-breadcrumb-item>
       </el-breadcrumb>
       <el-card >
           <el-tabs  v-model="activeName"  type="border-card"  @tab-click="getVaccroom">
               <el-tab-pane label="1号仓库" name="1">
                   <el-tabs :tab-position="tabPosition" v-model="activeName1" style="height: 500px"  @tab-click="handleClick">
                       <el-tab-pane label="冷库疫苗" name="one">
                           <!-- 操作列表 -->
                           <el-table :data="firstvacclist" height="445px" border stripe>
                               <el-table-column label="疫苗名称" width="120px" prop="name"></el-table-column>
                               <el-table-column label="疫苗编号" width="90px" prop="vaccid"></el-table-column>
                               <el-table-column label="疫苗种类" width="90px" prop="type"></el-table-column>
                               <el-table-column label="生产商" width="80px" prop="shop"></el-table-column>
                               <el-table-column label="生产日期" width="120px" prop="data1"></el-table-column>
                               <el-table-column label="保质期" width="120px" prop="data2"></el-table-column>
                               <el-table-column label="有效期" width="80px" prop="usefultime"></el-table-column>
                               <el-table-column label="入口日期" width="120px" prop="intoroomtime"></el-table-column>
                               <el-table-column label="当前状态"  width="80px"  prop="isadmin">
                                   <template slot-scope="scope">
                                       <el-tag  v-if="scope.row.isback" type="warning">已退还</el-tag>
                                       <el-tag  v-if="scope.row.isout" type="success">已出库</el-tag>
                                       <el-tag  v-if="scope.row.isout==false && scope.row.isback==false" >储存中</el-tag>
                                   </template>
                               </el-table-column>
                           </el-table>
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
                       </el-tab-pane>
                       <el-tab-pane label="冷库容量" name="two">
                           <!-- 用来放echarts图表的容器，一定要有宽高 -->
                           <el-row :gutter="20">
                               <el-col :span="12"><div class="grid-content bg-purple">
                                   <el-card>
                                       <div id="vacc1" style="height: 500px;width: 500px;"></div>
                                   </el-card>
                               </div></el-col>
                               <el-col :span="12"><div class="grid-content bg-purple">
                                   <el-card>
                                       <div id="coldroom1" style="height: 500px;width: 500px;"></div>
                                   </el-card>
                               </div></el-col>
                           </el-row>
                       </el-tab-pane>
                       <el-tab-pane label="冷库设置" name="three">
                           <!-- 冷库设置表单 -->
                           <el-card style="width: 500px;margin-left: 28%;background-color: #04befe" >
                               <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                                   <el-form-item label="仓库名称：" prop="name">
                                       <el-input v-model="ruleForm.name"></el-input>
                                   </el-form-item>
                                   <el-form-item label="仓库编号：" prop="coldroomid">
                                       <el-input v-model="ruleForm.coldroomid" ></el-input>
                                   </el-form-item>
                                   <el-form-item label="已经发送？" prop="issend">
                                       <el-switch v-model="ruleForm.issend" style="color: greenyellow"></el-switch>
                                   </el-form-item>
                                   <el-form-item label="仓库容量：" prop="num1">
                                       <el-input v-model="ruleForm.num1"></el-input>
                                   </el-form-item>
                                   <el-form-item label="管理员邮箱：" prop="adminemail">
                                       <el-input v-model="ruleForm.adminemail"></el-input>
                                   </el-form-item>
                                   <el-form-item>
                                       <el-button type="success" @click="submitForm('ruleForm')">保存设置</el-button>
                                       <el-button type="warning" @click="resetForm('ruleForm')">重置</el-button>
                                   </el-form-item>
                               </el-form>
                           </el-card>
                       </el-tab-pane>
                   </el-tabs>
               </el-tab-pane>
               <el-tab-pane label="2号仓库" name="2">
                   <el-tabs :tab-position="tabPosition" v-model="activeName1" style="height: 500px"  @tab-click="handleClick">
                       <el-tab-pane label="冷库疫苗" name="one">
                           <!-- 操作列表 -->
                           <el-table :data="firstvacclist" height="445px" border stripe>
                               <el-table-column label="疫苗名称" width="120px" prop="name"></el-table-column>
                               <el-table-column label="疫苗编号" width="90px" prop="vaccid"></el-table-column>
                               <el-table-column label="疫苗种类" width="90px" prop="type"></el-table-column>
                               <el-table-column label="生产商" width="80px" prop="shop"></el-table-column>
                               <el-table-column label="生产日期" width="120px" prop="data1"></el-table-column>
                               <el-table-column label="保质期" width="120px" prop="data2"></el-table-column>
                               <el-table-column label="有效期" width="80px" prop="usefultime"></el-table-column>
                               <el-table-column label="入口日期" width="120px" prop="intoroomtime"></el-table-column>
                               <el-table-column label="当前状态"  width="80px"  prop="isadmin">
                                   <template slot-scope="scope">
                                       <el-tag  v-if="scope.row.isback" type="warning">已退还</el-tag>
                                       <el-tag  v-if="scope.row.isout" type="success">已出库</el-tag>
                                       <el-tag  v-if="scope.row.isout==false && scope.row.isback==false" >储存中</el-tag>
                                   </template>
                               </el-table-column>
                           </el-table>
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
                       </el-tab-pane>
                       <el-tab-pane label="冷库容量" name="two">
                           <!-- 用来放echarts图表的容器，一定要有宽高 -->
                           <el-row :gutter="20">
                               <el-col :span="12"><div class="grid-content bg-purple">
                                   <el-card>
                                       <div id="vacc2" style="height: 500px;width: 500px;"></div>
                                   </el-card>
                               </div></el-col>
                               <el-col :span="12"><div class="grid-content bg-purple">
                                   <el-card>
                                       <div id="coldroom2" style="height: 500px;width: 500px;"></div>
                                   </el-card>
                               </div></el-col>
                           </el-row>
                       </el-tab-pane>
                       <el-tab-pane label="冷库设置" name="three">
                           <!-- 冷库设置表单 -->
                           <el-card style="width: 500px;margin-left: 28%;background-color: #04befe" >
                               <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                                   <el-form-item label="仓库名称：" prop="name">
                                       <el-input v-model="ruleForm.name"></el-input>
                                   </el-form-item>
                                   <el-form-item label="仓库编号：" prop="coldroomid">
                                       <el-input v-model="ruleForm.coldroomid" ></el-input>
                                   </el-form-item>
                                   <el-form-item label="已经发送？" prop="issend">
                                       <el-switch v-model="ruleForm.issend" style="color: greenyellow"></el-switch>
                                   </el-form-item>
                                   <el-form-item label="仓库容量：" prop="num1">
                                       <el-input v-model="ruleForm.num1"></el-input>
                                   </el-form-item>
                                   <el-form-item label="管理员邮箱：" prop="adminemail">
                                       <el-input v-model="ruleForm.adminemail"></el-input>
                                   </el-form-item>
                                   <el-form-item>
                                       <el-button type="success" @click="submitForm('ruleForm')">保存设置</el-button>
                                       <el-button type="warning" @click="resetForm('ruleForm')">重置</el-button>
                                   </el-form-item>
                               </el-form>
                           </el-card>
                       </el-tab-pane>
                   </el-tabs>
               </el-tab-pane>
               <el-tab-pane label="3号仓库" name="3">
                   <el-tabs :tab-position="tabPosition" v-model="activeName1" style="height: 500px"  @tab-click="handleClick">
                       <el-tab-pane label="冷库疫苗" name="one">
                           <!-- 操作列表 -->
                           <el-table :data="firstvacclist" height="445px" border stripe>
                               <el-table-column label="疫苗名称" width="120px" prop="name"></el-table-column>
                               <el-table-column label="疫苗编号" width="90px" prop="vaccid"></el-table-column>
                               <el-table-column label="疫苗种类" width="90px" prop="type"></el-table-column>
                               <el-table-column label="生产商" width="80px" prop="shop"></el-table-column>
                               <el-table-column label="生产日期" width="120px" prop="data1"></el-table-column>
                               <el-table-column label="保质期" width="120px" prop="data2"></el-table-column>
                               <el-table-column label="有效期" width="80px" prop="usefultime"></el-table-column>
                               <el-table-column label="入口日期" width="120px" prop="intoroomtime"></el-table-column>
                               <el-table-column label="当前状态"  width="80px"  prop="isadmin">
                                   <template slot-scope="scope">
                                       <el-tag  v-if="scope.row.isback" type="warning">已退还</el-tag>
                                       <el-tag  v-if="scope.row.isout" type="success">已出库</el-tag>
                                       <el-tag  v-if="scope.row.isout==false && scope.row.isback==false" >储存中</el-tag>
                                   </template>
                               </el-table-column>
                           </el-table>
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
                       </el-tab-pane>
                       <el-tab-pane label="冷库容量" name="two">
                           <!-- 用来放echarts图表的容器，一定要有宽高 -->
                           <el-row :gutter="20">
                               <el-col :span="12"><div class="grid-content bg-purple">
                                   <el-card>
                                       <div id="vacc3" style="height: 500px;width: 500px;"></div>
                                   </el-card>
                               </div></el-col>
                               <el-col :span="12"><div class="grid-content bg-purple">
                                   <el-card>
                                       <div id="coldroom3" style="height: 500px;width: 500px;"></div>
                                   </el-card>
                               </div></el-col>
                           </el-row>
                       </el-tab-pane>
                       <el-tab-pane label="冷库设置" name="three">
                           <!-- 冷库设置表单 -->
                           <el-card style="width: 500px;margin-left: 28%;background-color: #04befe" >
                               <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                                   <el-form-item label="仓库名称：" prop="name">
                                       <el-input v-model="ruleForm.name"></el-input>
                                   </el-form-item>
                                   <el-form-item label="仓库编号：" prop="coldroomid">
                                       <el-input v-model="ruleForm.coldroomid" ></el-input>
                                   </el-form-item>
                                   <el-form-item label="已经发送？" prop="issend">
                                       <el-switch v-model="ruleForm.issend" style="color: greenyellow"></el-switch>
                                   </el-form-item>
                                   <el-form-item label="仓库容量：" prop="num1">
                                       <el-input v-model="ruleForm.num1"></el-input>
                                   </el-form-item>
                                   <el-form-item label="管理员邮箱：" prop="adminemail">
                                       <el-input v-model="ruleForm.adminemail"></el-input>
                                   </el-form-item>
                                   <el-form-item>
                                       <el-button type="success" @click="submitForm('ruleForm')">保存设置</el-button>
                                       <el-button type="warning" @click="resetForm('ruleForm')">重置</el-button>
                                   </el-form-item>
                               </el-form>
                           </el-card>
                       </el-tab-pane>
                   </el-tabs>
               </el-tab-pane>
           </el-tabs>
       </el-card>
   </div>
</template>

<style lang="less" scoped>

</style>

<script>
    import * as echarts from 'echarts';//引入echarts资源
    export default {
        data() {
            return {
                queryInfo: {
                    query: "",
                    pageNum: 1,
                    pageSize: 20,
                    style:'疫苗编号',
                    roomid:0,
                },
                tabPosition: 'left',
                activeName: '1',
                activeName1: 'one',
                firstvacclist:[],
                total:0,
                ruleForm:{
                    id: '',
                    name: '',
                    coldroomid: '',
                    num1: '',
                    num2: '',
                    adminemail: '',
                    issend: false,
                },
                rules: {
                    name: [
                        { required: true, message: '请输入仓库名称', trigger: 'blur' },
                    ],
                    coldroomid: [
                        { required: true, message: '请确定仓库编号', trigger: 'change' }
                    ],
                    num1: [
                        { required: true, message: '请设置仓库容量', trigger: 'change' }
                    ],
                    adminemail: [
                        { required: true, message: '请设置仓库管理员邮箱号码', trigger: 'change' }
                    ],
                }
            }

        },
        created() {
            this.getVaccroom();
        },
        methods: {
            handleCommand(command) {
                if(command==1){
                    this.queryInfo.style="管理员id"
                }
                if(command==2){
                    this.queryInfo.style="疫苗编号"
                }
                if(command==3){
                    this.queryInfo.style="疫苗状态"
                }
            },
            //获得操作信息
            // 监听pageSize改变的事件
            handleSizeChange(newSize) {
                this.queryInfo.pageSize = newSize;
                this.getVaccloglist(); // 数据发生改变重新申请数据
            },
            // 监听pageNum改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.pageNum = newPage;
                this.getVaccloglist();// 数据发生改变重新申请数据
            },
            //获取对应仓库的疫苗数据
            async  getVaccroom(){
                this.queryInfo.roomid = parseInt(this.activeName);
                const {data:res} = await this.$http.post("api/room/getdata",this.queryInfo);
                if(res!=null){
                    this.firstvacclist = res.data;
                    this.total = res.number;
                    this.handleClick();
                }
            },
            //判断当前所选仓库
            handleClick() {
                if(this.activeName1=='two'){
                    this.queryInfo.roomid = parseInt(this.activeName);
                    this.getRoomVacapaity();
                    this.getRoomcapaity();
                }
                if(this.activeName1=='three'){
                    this.queryInfo.roomid = parseInt(this.activeName);
                    this.getRoomdata();
                }
            },
            //获取仓库不同种类疫苗的容量；
            async getRoomVacapaity(){
                var name = "vacc"+this.activeName;
                const {data:res} = await this.$http.post("api/room/chart",this.queryInfo);
                var mydata = res;
                var chartDom = document.getElementById(name);
                var myChart = echarts.init(chartDom);
                var option;
                option = {
                    title: {
                        text: '仓库疫苗对应种类现存容量',
                        subtext: '实时数据',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: '疫苗种类:容量（支）',
                            type: 'pie',
                            radius: '50%',
                            data:mydata,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                option && myChart.setOption(option);

            },
            //获取仓库不同种类疫苗的容量；
            async getRoomcapaity(){
                var name = "coldroom"+this.activeName;
                const {data:res} = await this.$http.post("api/room/chart1",this.queryInfo);
                var mydata = res.data;
                this.$message.success(res.res1);
                var chartDom = document.getElementById(name);
                var myChart = echarts.init(chartDom);
                var option;
                option = {
                    title: {
                        text: '仓库容量',
                        subtext: '实时数据',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: '当前数据',
                            type: 'pie',
                            radius: '50%',
                            data:mydata,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                option && myChart.setOption(option);

            },
            //获取仓库的详情参数
            async getRoomdata(){
                const {data:res} = await this.$http.post("api/room/getColdroomdata",this.queryInfo);
                if(res!=null){
                    this.ruleForm.id = res[0].id;this.ruleForm.name = res[0].name;
                    this.ruleForm.coldroomid = res[0].coldroomid;this.ruleForm.num1 = res[0].num1;
                    this.ruleForm.issend = res[0].issend;this.ruleForm.adminemail = res[0].adminemail;
                }
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.updatacloddata();
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            //保存设置
            async  updatacloddata(){
                const {data:res} = await this.$http.post("api/room/updateColdroomdata",this.ruleForm);
                if(res=="success"){
                    this.$message.success("仓库属性设置成功!")
                }else{
                    this.$message.error("仓库属性设置失败")
                }
            }
        },

    }
</script>