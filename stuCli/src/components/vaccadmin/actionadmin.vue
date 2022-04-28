<template>
    <div class="success_background">
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="width: 65%">
            <el-tabs type="border-card">
                <el-tab-pane label="活动管理">
                    <el-row :gutter="25">
                        <el-col :span="8">
                            <!-- 搜索 -->
                            <el-input placeholder="请输入活动名" v-model="queryInfo.query" clearable @clear="getVaccloglist">
                                <el-button slot="append" icon="el-icon-search" @click="getVaccloglist"></el-button>
                            </el-input>
                        </el-col>
                    </el-row>
                    <el-table :data="tableData" border style="width: 100%">
                        <el-table-column fixed prop="actiontime" label="创建时间" width="150"></el-table-column>
                        <el-table-column fixed prop="title" label="活动名称" width="150">
                            <template slot-scope="scope">
                                <el-popover placement="top-start" title="活动名称" width="100" trigger="hover" effect="dark" >{{scope.row.title}}
                                    <span v-model="scope.row.title" slot="reference" style="color:blue">{{scope.row.title| ellipsis}}</span>
                                </el-popover>
                            </template>
                        </el-table-column>
                        <el-table-column fixed prop="actionUser" label="发起人" width="150"></el-table-column>
                        <el-table-column fixed prop="starttime" label="开始时间" width="150"></el-table-column>
                        <el-table-column fixed prop="endtime" label="结束时间" width="150"></el-table-column>
                        <el-table-column fixed prop="limitnum" label="人数限制" width="150"></el-table-column>
                        <el-table-column fixed prop="jifen" label="可获积分" width="150"></el-table-column>
                        <el-table-column
                                fixed="right"
                                label="操作"
                                width="100">
                            <template slot-scope="scope">
                                <el-button @click="getdel(scope.row)" type="text" size="small">删除</el-button>
                                <el-button @click="getAction(scope.row)" type="text" size="small">编辑</el-button>
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
                    <el-dialog title="活动详情"   :visible.sync="dialogFormVisible"   width="30%">
                        <el-form :model="form">
                            <el-form-item label="活动名称" :label-width="formLabelWidth">
                                <el-input v-model="form.title" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="开始时间" :label-width="formLabelWidth">
                                <el-input v-model="form.starttime" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="结束时间" :label-width="formLabelWidth">
                                <el-input v-model="form.endtime" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="可获积分" :label-width="formLabelWidth">
                                <el-input v-model="form.jifen" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="人数限制" :label-width="formLabelWidth">
                                <el-input v-model="form.limitnum" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogFormVisible = false">取 消</el-button>
                            <el-button type="primary" @click="isupdate">确 定</el-button>
                        </div>
                    </el-dialog>
                </el-tab-pane>
                <el-tab-pane label="积分管理">
                    <el-row :gutter="25">
                        <el-col :span="8">
                            <!-- 搜索 -->
                            <el-input placeholder="请输入学生学号" v-model="queryInfo1.query" clearable @clear="getUserlist">
                                <el-button slot="append" icon="el-icon-search" @click="getUserlist"></el-button>
                            </el-input>
                        </el-col>
                    </el-row>
                    <el-table :data="Usertable" border style="width:85%">
                        <el-table-column fixed prop="id" label="序号" width="100"></el-table-column>
                        <el-table-column fixed prop="cardid" label="学号" width="150"></el-table-column>
                        <el-table-column prop="username" label="姓名" width="120"></el-table-column>
                        <el-table-column prop="major" label="班级" width="180"></el-table-column>
                        <el-table-column prop="shool_group" label="学院" width="120"></el-table-column>
                        <el-table-column prop="role" label="身份" width="120"></el-table-column>
                        <el-table-column prop="grade" label="积分" width="80">
                            <template slot-scope="scope">
                                <span style="color: red;font-weight: 600;">{{scope.row.grade}}分</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                fixed="right"
                                label="分数加减"
                                width="160">
                            <template slot-scope="scope">
                                <el-input-number v-model="scope.row.grade"  size="mini" :precision="2" :step="0.1" :max="100"></el-input-number>
                                <el-button @click="chagegrade(scope.row)" type="text" size="small">保存积分</el-button>
                                <el-button @click="openwin(scope.row)" type="text" size="small">修改信息</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="margin-left:35%">
                        <div class="block">
                            <el-pagination
                                    @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange"
                                    :current-page="queryInfo1.pageNum"
                                    :page-sizes="[5,10,15]"
                                    :page-size="queryInfo1.pageSize"
                                    layout="total, sizes, prev, pager, next"
                                    :total="total1">
                            </el-pagination>
                        </div>
                    </div>
                    <el-dialog title="活动详情"   :visible.sync="dialogFormVisible1"   width="30%">
                        <el-form :model="forms">
                            <el-form-item label="学生名称" :label-width="formLabelWidth">
                                <el-input v-model="forms.username" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="专业班级" :label-width="formLabelWidth">
                                <el-input v-model="forms.major" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="密码重置" :label-width="formLabelWidth">
                                <el-input v-model="forms.password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="学院" :label-width="formLabelWidth">
                                <el-input v-model="forms.shool_group" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="学号" :label-width="formLabelWidth">
                                <el-input v-model="forms.cardid" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogFormVisible1 = false">取 消</el-button>
                            <el-button type="primary" @click="isupdateUser">确 定</el-button>
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
                queryInfo: {
                    query: "",
                    pageNum: 1,
                    pageSize: 20,
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
                Usertable:[],
                form:{

                },
                forms:{

                },
            }
        },
        created() {
            this.getVaccloglist();
            this.getUserlist();
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
            async  getVaccloglist(){
                const {data:res} = await this.$http.post("api/Action/getselectPageadmin",this.queryInfo);
                if(res.code==1){
                    this.tableData = res.data;
                    this.total = res.number;
                    this.dialogFormVisible = false
                }else{
                    this.$message.error(res.msg)
                }
            },

            async  getUserlist(){
                const {data:res} = await this.$http.post("api/User/getUserlist",this.queryInfo1);
                if(res.code==1){
                    this.Usertable = res.data;
                    this.total1 = res.number;
                }else{
                    this.$message.error(res.msg)
                }
            },

            async  getdel(row){
                if(row.actionUser!=window.sessionStorage.getItem("user")){
                    this.$message.error("你不是该活动创建人，无法修改该活动")
                    return;
                }
                const confirmResult = await this.$confirm('是否确认删除该活动记录?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 成功删除为confirm 取消为 cancel
                if (confirmResult != 'confirm') {
                    return this.$message.info("已取消操作");
                }
                const {data:res} = await this.$http.get("api/Action/deleteAction?id="+id);
                if(res=="success"){
                    this.$message.success("删除成功")
                    this.dialogFormVisible = false
                    this.getVaccloglist();
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

            //api/user/updateUser
            async chagegrade(row) {
                const {data: res} = await this.$http.post("api/user/updateUser", row);
                if (res == "success") {
                    this.$message.success("保存成功")
                    this.getUserlist();
                } else {
                    this.$message.error("保存失败")
                }

            },

            async openwin(row) {
                this.dialogFormVisible1 = true;
                this.forms = row;
            },
            async isupdateUser() {

                const {data: res} = await this.$http.post("api/user/updateUser", this.forms);
                if (res == "success") {
                    this.$message.success("保存成功")
                    this.dialogFormVisible1 = false;
                    this.getUserlist();
                } else {
                    this.$message.error("保存失败")
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
