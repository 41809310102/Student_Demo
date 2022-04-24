<template>
   <div>
       <!-- 面包屑导航 -->
       <el-breadcrumb separator-class="el-icon-arrow-right">
           <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
           <el-breadcrumb-item>我的活动</el-breadcrumb-item>
       </el-breadcrumb>
       <el-row :gutter="20">
           <el-col :span="3"><div class="grid-content bg-purple">

           </div></el-col>
           <el-col :span="16"><div class="grid-content bg-purple">
               <el-card>
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
                   <el-divider></el-divider>
                   <div style="margin-left:1%;margin-right:1%">
                       <el-row>
                           <el-col :span="20" v-for="(item) in actionlist" :key="item">
                               <div style="margin-top:10px;margin-left:2%;">
                                   <el-card :body-style="{ padding: '0px'}" shadow="hover" style="height: 100px">
                                       <el-row :gutter="20">
                                           <el-col :span="6"><div class="grid-content bg-purple">
                                               <el-image :src="pic" style="height: 100%;"></el-image>
                                           </div></el-col>
                                           <el-col :span="14"><div class="grid-content bg-purple">
                                               <div style="margin-left: 20%;margin-top: 3%">
                                                   <div>
                                                       <span>开始时间:{{item.starttime}}</span>
                                                   </div>
                                                   <div>
                                                       <span>结束时间:{{item.starttime}}</span>
                                                   </div>
                                                   <div>
                                                       <span>剩余名额:{{item.limitnum}}人</span>
                                                   </div>
                                               </div>
                                           </div></el-col>
                                           <el-col :span="4"><div class="grid-content bg-purple">
                                               <div style="margin-top: 2%">
                                                       <span style="color:#ff0000;font-size: 17px;font-weight: 500">可获得:{{item.jifen}}积分</span>
                                               </div>
                                               <div style="margin-top: 4%">
                                                   <el-row>
                                                       <el-button type="success" icon="el-icon-star-off" circle></el-button>
                                                       <el-button type="danger" icon="el-icon-delete" circle></el-button>
                                                   </el-row>
                                               </div>
                                           </div></el-col>
                                       </el-row>

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
                                       :page-sizes="[5,10,15]"
                                       :page-size="queryInfo.pageSize"
                                       layout="total, sizes, prev, pager, next"
                                       :total="total">
                               </el-pagination>
                           </div>
                       </div>
                   </div>
               </el-card>
           </div></el-col>
           <el-col :span="4"><div class="grid-content bg-purple">

           </div></el-col>
       </el-row>

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
</style>

<script>
    import * as echarts from 'echarts';//引入echarts资源
    export default {
        data() {
            return {
                pic:"https://tse1-mm.cn.bing.net/th/id/R-C.5f0e2c43eabbfbefcca8a213be1789f8?rik=rR1EF2xBjNccwg&riu=http%3a%2f%2fenglish.njit.edu.cn%2f__local%2f5%2fF0%2fE2%2fC43EABBFBEFCCA8A213BE1789F8_B8D0E4F6_4E3AC.jpg&ehk=Mgd9T5Uf9GKjruKEKPgYcYDYN%2bgq8nPM27fqHWtDoMQ%3d&risl=&pid=ImgRaw&r=0",
                actionlist:[],
                queryInfo: { //参数控制
                    query: "",
                    pageNum: 1,
                    pageSize: 8,
                },
                total:0,
            }

        },
        created() {
          this.getaction();
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
            //获取当前所有活动
            async  getaction(){
                //this.queryInfo.roomid = parseInt(this.activeName);
                const {data:res} = await this.$http.get("api/Action/getActionall");
                if(res.code==1){
                    this.actionlist = res.data;
                    this.total = res.numbers;
                }else{
                    this.$message.error(res.msg)
                }
            },
            //申请加入活动
            showitem(item){

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
