<template>
    <div class="main">
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>我的投票</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row>
            <el-col style="width: 330px;margin-left: 20px" :span="4"  v-for="(item) in votelist" :key="item" :offset="1" >
                <div style="margin-top:15px;">
                    <el-card :body-style="{ padding: '10px'}" shadow="hover" style="height: 200px">
                        <div>
                            <span style="color: blue">班级：{{item.major}}</span><br>
                            <div style="height: 50px;margin-top: 10px">
                                <span style="color:black">问题：{{item.question}}</span><br>
                            </div>
                            <el-divider></el-divider>
                            <div style="width: 300px ">
                                <span style="width: 200px"><strong>结束时间:</strong>{{item.endtime}}</span>
                                <el-button type="text" class="button" @click="getVoteofid(item)">去投票</el-button>
                            </div>
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-row>
        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <el-card style="width: 400px;">
                <el-card class="title">
                    <span style="color: black;font-weight: 500">{{title}}</span>
                </el-card>
                <el-card class="ask" v-if="show">
                    <el-radio-group v-model="radio" @change="showlabel">
                        <div v-for="item in datanode" style="height: 30px;margin-top: 20px">
                            <el-radio :label="item">{{item.title}}</el-radio>
                        </div>
                    </el-radio-group>
                    <el-card>
                        <el-button-group style="margin-left: 26%">
                            <el-button type="primary" v-if="!isok" @click="isok=true" size="mini">确定</el-button>
                            <el-button type="primary" v-if="isok" @click="show=false"  size="mini">查看结果</el-button>
                            <el-button type="primary" @click="dialogVisible=false" size="mini">取消</el-button>
                        </el-button-group>
                    </el-card>
                </el-card>
                <!--数据统计-->
                <el-card class="ask" v-if="!show">
                    <div v-for="item in datanode" style="height: 30px;margin-top: 20px">
                        <el-tooltip class="item" effect="dark" :content="item.title" placement="top">
                            <el-progress :text-inside="true" :color="customColorMethod" :stroke-width="26"  :percentage="item.num"></el-progress>
                        </el-tooltip>
                    </div>
                    <el-card>
                        <el-button-group style="margin-left: 26%">
                            <el-button type="primary" size="mini" v-if="!show" @click="show=true">取消</el-button>
                        </el-button-group>
                    </el-card>
                </el-card>
            </el-card>
        </el-dialog>
    </div>
</template>

<style lang="less" scoped>
 .title{
     width: 90%;height: 20%;background-color: #a1c4fd;margin-left: 5%
 }
 .ask{
     width: 90%;background-color: #b3d4fc;margin-left: 5%;margin-top: 5px;
 }
</style>

<script>
    export default {
        data() {
            return {
                title:"",
                dialogVisible:false,
                votelist:[],
                isok:false,
                show:true,
                radio: 0,
                myask:{  //我的选择
                    vid:0,
                    option:0,
                },
                datanode:[
                    {id:1,title:"sdcdscnds",num:20},
                    {id:2,title:"sdcdscnds",num:20},
                    {id:3,title:"sdcdscnds",num:20},
                    {id:4,title:"sdcdscnds",num:20},
                ],
                customColor: '#409eff',
                customColors: [
                    {color: '#f56c6c',},
                    {color: '#e6a23c',},
                    {color: '#5cb87a',},
                    {color: '#1989fa',},
                    {color: '#6f7ad3',}
                ]

            }
        },
        created() {
            this.getVote();
        },
        methods: {
            logout(){
                window.sessionStorage.clear();
                this.$router.push('/login');
            },
            toHome(){
                this.$router.push('/home');
            },
            showlabel(res){
                this.myask.vid = res.vid;
                this.myask.option = res.id;
            },
            //确定投票
            isokmyoption(){

            },
            //颜色选择
            customColorMethod(percentage) {
                if (percentage <= 10) {
                    return '#909399';
                } else if (percentage <= 20) {
                    return '#e6a23c';
                } else if(percentage <= 30){
                    return '#67c23a';
                }else{
                    return '#1989fa';
                }
            },
            //获取全部班级投票数据；
            async getVote(){
                var data={
                    major:window.sessionStorage.getItem("major")
                }
                const {data: res} = await this.$http.get("api/Vote/selectvoteall?major="+data.major)
                if(res.code==1){
                   this.votelist = res.data;
                }else{
                    this.$message.error(res.msg);
                }
            },

            //去投票
            async getVoteofid(item) {
                this.dialogVisible =true;
                this.title = item.question;
                this.show = true;
                this.isok = false;
                var data={
                    id:item.id,
                }
                const {data: res} = await this.$http.get("api/Vote/selectvote?id="+data.id)
                if(res.code==1){
                    this.datanode = res.data;
                }else{
                    this.$message.error(res.msg);
                }
            }
        },
    }
</script>
