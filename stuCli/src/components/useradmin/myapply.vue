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
                           <el-input placeholder="请输入活动名" v-model="queryInfo.query" clearable @clear="getaction">
                               <el-button slot="append" icon="el-icon-search" @click="getactionofcheck"></el-button>
                           </el-input>
                       </el-col>
                   </el-row>
                   <el-divider></el-divider>
                   <div style="margin-left:1%;margin-right:1%">
                       <el-row>
                           <el-col :span="20" v-for="(item) in actionlist" :key="item">
                               <div style="margin-top:10px;margin-left:2%;">
                                   <el-card :body-style="{ padding: '0px'}" shadow="hover" style="height: 100px;background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);">
                                       <el-row :gutter="20">
                                           <el-col :span="6"><div class="grid-content bg-purple">
                                               <el-image :src="pic" style="height: 100%;"></el-image>
                                           </div></el-col>
                                           <el-col :span="6"><div class="grid-content bg-purple">
                                               <div style="font-size: 17px;margin-top: 3%;font-weight: 600">
                                                   <span>{{item.title}}</span>
                                               </div>
                                           </div></el-col>
                                           <el-col :span="8"><div class="grid-content bg-purple">
                                               <div style="margin-left: 20%;margin-top: 10%">
                                                   <div>
                                                       <span style="font-size: 14px;color: #ee9900">开始时间:{{item.starttime}}</span>
                                                   </div>
                                                   <div>
                                                       <span style="font-size: 14px;color: red">结束时间:{{item.starttime}}</span>
                                                   </div>
                                                   <div>
                                                       <span style="color:blue">剩余名额:{{item.limitnum}}人</span>
                                                   </div>
                                               </div>
                                           </div></el-col>
                                           <el-col :span="4"><div class="grid-content bg-purple">
                                               <div style="margin-top: 2%">
                                                       <span style="color:#ff0000;font-size: 17px;font-weight: 500">{{item.jifen}}积分</span>
                                               </div>
                                               <div style="margin-top: 12%">
                                                   <el-row>
                                                       <el-button type="success" v-if="!item.isapply"  @click="showitem(item)" size="mini" icon="el-icon-star-off" >申请加入</el-button>
                                                       <el-button type="danger" v-if="item.isapply"  @click="delitem(item)" size="mini" icon="el-icon-star-off" >取消申请</el-button>
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
                applylist:[],
            }

        },
        created() {
          this.getaction();
        },
        methods: {

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
                    this.getapply();
                }else{
                    this.$message.error(res.msg)
                }
            },
            //搜索相关活动
            async getactionofcheck() {
                const {data: res} = await this.$http.get("api/Action/getselectActionCheck?id="+window.sessionStorage.getItem("id")+
                    "&title="+this.queryInfo.query);
                if(res.code==1){
                    this.actionlist = res.data;
                    this.total = res.numbers;
                }else{
                    this.$message.success(res.msg);
                }
            },
            //申请加入活动
            async showitem(item) {
                var apply={
                    uid:window.sessionStorage.getItem("id"),
                    aid:item.id,
                    state:"等待审核"
                }
                const {data: res} = await this.$http.post("api/Apply/addapply",apply);
                if (res.code == 1) {
                    this.$message.success(res.msg+",等待审核");
                    this.getaction();
                } else {
                    this.$message.error(res.msg);
                }
            },
            //获取仓库的详情参数
            async getapply(){
                const {data:res} = await this.$http.get("api/Apply/getapplybyid?id="+window.sessionStorage.getItem("id")
                );
                let len = this.actionlist.length;
                this.applylist = res.data;
                for(let j =0;j<len;j++){
                    this.actionlist[j].isapply = false;
                }
                if(res.code==1){
                    for(let i = 0;i<res.numbers;i++){
                        for(let j =0;j<len;j++){
                            if(this.actionlist[j].id == this.applylist[i].aid){
                                   this.actionlist[j].isapply = true;
                            }
                        }
                    }
                }
            },
            //取消申请
            async delitem(item) {
                let len = this.applylist.length;
                let id =0;
                for(let j =0;j<len;j++){
                   if( this.applylist[j].aid  == item.id){
                       id = this.applylist[j].id
                   }
                }
                const {data: res} = await this.$http.get("api/Apply/delapply?id="+id);
                if(res.code==1){
                    this.$message.success(res.msg);
                    this.getaction();
                }else{
                    this.$message.error(res.msg);
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

        },

    }
</script>
