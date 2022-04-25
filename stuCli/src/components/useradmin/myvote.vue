<template>
    <div class="main">
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>我的投票</el-breadcrumb-item>
        </el-breadcrumb>
        <el-tag type="danger">待投票:{{votelist.length-voteisoklist.length}}</el-tag>
        <el-row>
            <el-col style="width: 330px;margin-left: 20px" :span="4"  v-for="(item) in votelist"  :offset="1" >
                <div style="margin-top:15px;" v-if="!item.ispass">
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
                width="30%">
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
                            <el-button type="primary" v-if="!isok" @click="isokmyoption" size="mini">确定</el-button>
                            <el-button type="danger" v-if="isok" @click="show=false"  size="mini">查看结果</el-button>
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
        <el-divider></el-divider>
        <el-tag type="success">已投票:{{voteisoklist.length}}</el-tag>
        <el-row>
            <el-col style="width: 330px;margin-left: 20px" :span="4"  v-for="(item) in voteisoklist" :offset="1" >
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
                                <el-button type="text" class="button"  @click="ischeck(item)">查看结果</el-button>
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
                this.myask.token = res.token;
            },
            //确定投票
            async isokmyoption() {
                const {data: res} = await this.$http.get("api/Vote/getMyoption?vid=" + this.myask.vid + "&option=" +
                    this.myask.option+"&uid="+window.sessionStorage.getItem("id"))
                if (res=="success") {
                    this.$message.success("投票成功");
                    const {data: res} = await this.$http.get("api/Vote/selectvote?id="+this.myask.vid)
                    if(res.code==1){
                        this.datanode = res.data;
                        this.count();//计算百分比
                        this.getVote();//刷新页面
                    }else{
                        this.$message.error(res.msg);
                    }
                    this.isok=true;
                } else {
                    this.$message.error("投票失败");
                }
            },
            //查看投票结果
            async ischeck(item){
                this.dialogVisible =true;
                this.title = item.question;
                this.show = true;
                this.isok = true;
                var data={
                    id:item.id,
                }
                const {data: res} = await this.$http.get("api/Vote/selectvote?id="+data.id)
                if(res.code==1){
                    this.datanode = res.data;
                }else{
                    this.$message.error(res.msg);
                }
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
                const data = {
                    major: window.sessionStorage.getItem("major")
                };
                const {data: res} = await this.$http.get("api/Vote/selectvoteall?major="+data.major+"&uid="+
                window.sessionStorage.getItem("id"))
                if(res.code==1){
                   this.votelist = res.data;
                }else{
                    this.$message.error(res.msg);
                }

                const {data: res1} = await this.$http.get("api/Vote/selectvotelog?major="+data.major+"&uid="+
                    window.sessionStorage.getItem("id"))
                if(res1.code==1){
                    this.voteisoklist = res1.data;
                }else{
                    this.$message.error(res1.msg);
                }
                const  arr = this.votelist
                //删除arr中的重复对象
                const newArr = [];
                const arrId = [];
                for(const item of arr){
                    if(arrId.indexOf(item['id']) == -1){
                        arrId.push(item['id']);
                        newArr.push(item);
                    }
                }

                for(let t =0;t<newArr.length;t++){
                    newArr[t].ispass = false;
                }

                for(let t =0;t<this.voteisoklist.length;t++){
                    for(let j =0;j<newArr.length;j++){
                       if(this.voteisoklist[t].id==newArr[j].id){
                           newArr[j].ispass = true;
                       }
                    }
                }

               this.votelist = newArr;
                console.log(newArr)
            },

            //去投票
            async getVoteofid(item) {
                if(item.isout){
                    this.$message.error("当前投票已过期")
                    return ;
                }
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
