<template>
    <div class="main">
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>我的问卷</el-breadcrumb-item>
        </el-breadcrumb>
        <el-tag type="danger" v-if="!showwin">待填写:{{votelist.length}}</el-tag>
        <el-row v-if="!showwin">
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
         <el-card v-if="showwin" style="width: 60%;margin-left: 20%;background-image: linear-gradient(135deg, #fdfcfb 0%, #e2d1c3 100%);">
             <div>
                 <span style="margin-left: 10%;font-weight: 600;margin-top: 10%;font-size: 20px;color: blue">
                     主题:{{topics[0].title}}</span>
             </div>
             <el-divider></el-divider>
             <div v-for="(item, index)  in topics" :key="index">
                 <el-card style="width: 60%;margin-left: 20%">
                     <span style="color: black;font-size: 16px;font-weight: 600"> 第{{ index+1 }}题,题目:{{ item.questionName }}</span><br>
                     <div style="margin-top: 10px">
                             <el-radio-group v-model="radio" @change="showlabel">
                                 <div v-for="items in item.answers" style="margin-top: 10px">
                                     <el-radio :label="items.value">{{items.value}}</el-radio>
                                 </div>
                             </el-radio-group>
                     </div>
                     <div style="margin-top: 10px;margin-left: 80%">
                         <el-button type="success" v-if="!finsh" size="mini" @click="updatemyoption">确定选择</el-button>
                     </div>
                 </el-card>
                 <el-button type="danger" size="mini"  v-if="finsh" @click="">返回首页</el-button>
                 <el-divider></el-divider>
             </div>
         </el-card>
        <el-divider></el-divider>
        <el-tag type="success" v-if="!showwin">已填写:{{votelist1.length}}</el-tag>
        <el-row v-if="!showwin">
            <el-col style="width: 330px;margin-left: 20px" :span="4"  v-for="(item) in votelist1"  :offset="1" >
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
                                <el-tag type="success" v-if="!showwin">已填写</el-tag>
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
                showwin:false,
                finsh:false,
                title:"",
                glodate:"",
                dialogVisible:false,
                votelist:[],
                votelist1:[],
                myoption:[],//持久化选择
                radio: 0,
                count:0,//确定次数
                voteisoklist:[],
                isok:false,
                show:true,
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
                ],

                topics: [{
                    questid:"123",
                    title:"今天你学习了吗",
                    username:"",
                    major:'',
                    type: '',
                    questionName: 'cscscsdcsdcsdcsdcsdcsdc',
                    answers: [{ value: '1' },{ value: '2' },{ value: '3' },{ value: '4' }]
                }, {
                    questid:"123",
                    title:"哈哈哈",
                    username:"",
                    major:'',
                    type: '',
                    questionName: 'sdcsdcsdcdscsdcsdcsdcsdcsd',
                    answers: [{ value: 'sssss1' },{ value: 'ssss3' },{ value: 'sssssws' },{ value: 'ssssxas' }]
                }],

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
            //获取全部班级问卷；
            async getVote(){
                const data = {
                    major: window.sessionStorage.getItem("major")
                };
                const {data: res} = await this.$http.get("api/paper/getPaperfarther?major="+data.major+"&myname="+window.sessionStorage.getItem("user"))
                if(res.code==1){
                    this.votelist = res.data //没有回答的问卷
                    this.votelist1 = res.data1;//回答的问卷
                }else{
                    this.$message.error(res.msg);
                }
            },

            async getVoteofid(item) {
                const id = item.questid; //获取问卷id;
                this.dialogVisible = true;
                this.showwin = true;
                const {data: res} = await this.$http.get("api/paper/getPaper?questid=" + id)
                if (res.code == 1) {
                    this.topics = res.data;
                } else {
                    this.$message.error(res.msg);
                }
                this.myoption =[];
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


            },
            //单选选择
            showlabel(res){
              this.glodate = res;
            },
            //确认选择
            async updatemyoption() {
                let i = this.count;
                this.myoption[i] = this.glodate;
                if (i == this.topics.length - 1) {
                    this.$message.success("恭喜你，已经完成所有问题")
                    //提交答案
                    const {data: res} = await this.$http.get("api/paper/uploadmyask?myoption=" +this.myoption+"&major="+
                        window.sessionStorage.getItem("major")+ "&questid="+this.topics[0].questid+"&myname="+window.sessionStorage.getItem("user")+"&title="+this.topics[0].title);
                    if (res=="success") {
                        this.getVote();
                        this.dialogVisible = false;
                        this.showwin =false;
                    } else {
                        console.log("数据保存失败")
                    }

                } else {
                    this.$message.success("保存成功")
                    this.count = i + 1;
                }
            },

        },
    }
</script>
