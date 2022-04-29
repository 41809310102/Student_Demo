<template>
    <div class="success_background">
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>签到管理</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="width: 65%">
            <el-tabs type="border-card">
                <el-tab-pane label="签到记录">
                    <el-row :gutter="25">
                        <el-col :span="6">
                            <el-form ref="form" :inline="true" :model="acction" label-width="60px" size="mini">
                                <el-form-item label="选择活动:" label-width="200">
                                    <el-select v-model="acction.name" placeholder="请选择活动">
                                        <div v-for="item in acctionlist">
                                            <el-option :label="item.title" :value="item.title"></el-option>
                                        </div>
                                    </el-select>
                                </el-form-item>
                                </el-form>
                        </el-col>
                        <el-col :span="3">
                            <el-button type="success" @click="getallSignlog" size="mini">确定</el-button>
                            <el-button type="danger" @click="addSign" size="mini">补签</el-button>
                        </el-col>
                    </el-row>
                    <el-table :data="tableData" border style="width: 100%">
                        <el-table-column fixed prop="id" label="序号" width="150"></el-table-column>
                        <el-table-column fixed prop="major" label="专业班级" width="170"></el-table-column>
                        <el-table-column fixed prop="local" label="签到地点" width="150">
                            <template slot-scope="scope">
                                <el-popover placement="top-start" title="" width="100" trigger="hover" effect="dark" >{{scope.row.local}}
                                    <span v-model="scope.row.local" slot="reference" style="color:blue">{{scope.row.local| ellipsis}}</span>
                                </el-popover>
                            </template>
                        </el-table-column>
                        <el-table-column fixed prop="signtime" label="签到时间" width="170"></el-table-column>
                        <el-table-column fixed prop="uname" label="签到人" width="150"></el-table-column>
                        <el-table-column fixed prop="cardid" label="学号" width="150"></el-table-column>
                        <el-table-column
                                fixed="right"
                                label="操作"
                                width="100">
                            <template slot-scope="scope">
                                <el-button @click="getdel(scope.row)" type="text" size="small">删除</el-button>
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
                    <el-dialog title="学生补签"   :visible.sync="dialogFormVisible"   width="30%">
                        <el-form :model="form">
                            <el-form-item label="学生姓名" :label-width="200">
                                <el-input v-model="form.uname" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="学生学号" :label-width="200">
                                <el-input v-model="form.cardid" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="班级" :label-width="200">
                                <el-input v-model="form.major"  autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="打卡时间" :label-width="200">
                                <el-input v-model="form.signtime" disabled autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="补卡时间" :label-width="200">
                                <el-input type="date" v-model="form.addsigntime" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogFormVisible = false">取 消</el-button>
                            <el-button type="primary" @click="updateSign">确 定</el-button>
                        </div>
                    </el-dialog>
                </el-tab-pane>
            </el-tabs>
        </div>


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
                num: 1,
                acction:{
                    name:"",
                },
                acctionlist:[],
                queryInfo: {
                    query: "",
                    pageNum: 1,
                    pageSize: 20,
                    value:"",
                },

                queryInfo1: {
                    query: "",
                    pageNum: 1,
                    pageSize: 20,
                },

                dialogFormVisible: false,
                dialogFormVisible1:false,
                total: 0, // 最大数据记录
                total1:0,
                tableData: [],
                form:{
                     addsigntime: "",
                     aid: 0,
                     cardid: "",
                     isdelect: 0,
                     local: "补卡状态，无签到位置",
                     major: "",
                     signtime: "当前学生为补卡学生",
                     uname: ""
                },
                forms:{

                },
            }
        },
        created() {
            this.acctionlists();
        },
        methods: {
            //获得操作信息
            // 监听pageSize改变的事件
            handleSizeChange(newSize) {
                this.queryInfo.pageSize = newSize;
                this.getVaccloglist(); // 数据发生改变重新申请数据
                this.getUserlist();
            },
            // 监听pageNum改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.pageNum = newPage;
                this.getVaccloglist();// 数据发生改变重新申请数据
            },

            //获取签到记录
            async  getallSignlog(){
                for (let i = 0; i < this.acctionlist.length; i++) {
                    if (this.acctionlist[i].title == this.acction.name) {
                        this.queryInfo.id=this.acctionlist[i].id
                    }
                }
                const {data:res} = await this.$http.post("api/Sign/getSignlist",this.queryInfo);
                if(res.code==1){
                    this.tableData = res.data;
                    this.total = res.number;
                }else{
                    this.$message.error(res.msg)
                }
            },
            //获取活动列表
            async  acctionlists(){
                const {data:res} = await this.$http.get("api/Action/getActionall");
                if(res.code==1){
                    this.acctionlist = res.data;
                }else{
                    this.$message.error(res.msg)
                }
            },

            async  getdel(row){
                const confirmResult = await this.$confirm('是否确认删除该签到记录?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 成功删除为confirm 取消为 cancel
                if (confirmResult != 'confirm') {
                    return this.$message.info("已取消操作");
                }
                const {data:res} = await this.$http.get("api/Sign/dellog?id="+row.id);
                if(res.code==1){
                    this.$message.success("删除成功")
                    await this.getallSignlog();
                }else{
                    this.$message.error("删除失败")
                }
            },

            getAction(row){
                this.dialogFormVisible = true;
                this.form = row;
            },
            //保存活动编辑
            async isupdate() {
                const {data: res} = await this.$http.post("api/Action/updateAction",this.form);
                if (res.code==1) {
                    this.$message.success(res.msg)
                    this.getVaccloglist();
                } else {
                    this.$message.error(res.msg)
                }
            },

            //补签
            addSign(){
                this.dialogFormVisible = true;
            },

            //提交补卡清单
            async updateSign() {
                if(this.acction.name==""){
                    this.$message.error("当前未选择补卡活动")
                    return ;
                }
                for (let i = 0; i < this.acctionlist.length; i++) {
                    if (this.acctionlist[i].title == this.acction.name) {
                        this.form.aid = this.acctionlist[i].id
                    }
                }
                const {data: res} = await this.$http.post("api/Sign/addSign", this.form);
                if (res.code == 1) {
                    this.$message.success("补卡成功")
                    this.dialogFormVisible = false
                    this.getallSignlog();
                } else {
                    this.$message.error(res.msg)
                }

            },



        },
        //只显示前10个字符
        filters:{
            ellipsis(value){
                if (!value) return '';
                if (value.length > 8) {
                    return value.slice(0,8) + '...'
                }
                return value
            }
        }
    }
</script>
