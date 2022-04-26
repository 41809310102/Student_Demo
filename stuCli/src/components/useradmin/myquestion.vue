<template>
    <div class="main">
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>我的问卷</el-breadcrumb-item>
        </el-breadcrumb>
        <el-tag type="danger">待填写:{{votelist.length-voteisoklist.length}}</el-tag>
        <el-row>
            <el-col style="width: 330px;margin-left: 20px" :span="4"  v-for="(item) in votelist"  :offset="1" >
                <div style="margin-top:15px;" v-if="!item.ispass">
                    <el-card :body-style="{ padding: '10px'}" shadow="hover" style="height: 200px">
                        <div>
                            <span style="color: blue">班级：{{item.major}}</span><br>
                            <div style="height: 50px;margin-top: 10px">
                                <span style="color:black">问卷主题：{{item.title}}</span><br>
                            </div>
                            <el-divider></el-divider>
                            <div style="width: 300px ">
                                <span style="width: 200px"><strong>创建时间:</strong>{{item.time}}</span><br>
                                <el-button type="text" class="button" @click="getVoteofid(item)">去填写</el-button>
                            </div>
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-row>
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
                voteisoklist:[],
                isok:false,
                show:true,
                radio: 0,
                myask:{  //我的选择
                    vid:0,
                    option:0,
                    token:"",
                },
                datanode:[
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
                const data = {
                    major: window.sessionStorage.getItem("major")
                };
                const {data: res} = await this.$http.get("api/paper/getPaperfarther?major="+data.major)
                if(res.code==1){
                    this.votelist = res.data;
                }else{
                    this.$message.error(res.msg);
                }
            },
            //计算投票百分比
            count(){
                let count = 0;
                let len = this.datanode.length;
                for(let i = 0;i<len;i++){
                    count=count+this.datanode[i].num;
                }

                for(let i = 0;i<len;i++){
                    this.datanode[i].num = (this.datanode[i].num/count*100).toFixed(2); //保留两位小数
                }

            }

        },
    }
</script>
