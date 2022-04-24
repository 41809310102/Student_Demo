<template>

    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>我的成绩</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 卡片视图区 -->
        <el-card>
            <el-row :gutter="25">
                <el-col :span="3">
                    <el-dropdown split-button type="primary" @command="handleCommand">
                        {{queryInfo.type}}
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="1">大一</el-dropdown-item>
                            <el-dropdown-item command="2">大二</el-dropdown-item>
                            <el-dropdown-item command="3">大三</el-dropdown-item>
                            <el-dropdown-item command="4">大四</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-col>
                <el-col :span="3">
                    <el-dropdown split-button type="primary" @command="handleCommand">
                        {{queryInfo.xid}}
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="5">第一学期</el-dropdown-item>
                            <el-dropdown-item command="6">第二学期</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-col>
                <el-col :span="3">
                    <el-button  type="success" round @click="seacher">确定</el-button>
                </el-col>
                <el-col :span="2">
                    <el-button  type="success" v-if="root==1" round @click="openaddshow">添加信息</el-button>
                </el-col>
            </el-row>
            <div>
                <!-- 详情信息-->
                <el-dialog title="疫苗详情" :visible.sync="descVisible">
                    <el-form ref="form"  :inline="true" :model="form" label-width="80px" class="demo-form-inline">
                        <el-form-item label="疫苗编号:">
                            <el-input v-model="form.vaccid"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗名称:">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗种类:">
                            <el-input v-model="form.type"></el-input>
                        </el-form-item>
                        <el-form-item label="冷库编号:">
                            <el-input v-model="form.coldroomid"></el-input>
                        </el-form-item>
                        <el-form-item label="存储温度:">
                            <el-input v-model="form.temp2"></el-input>
                        </el-form-item>
                        <el-form-item label="运输温度:">
                            <el-input v-model="form.temp2"></el-input>
                        </el-form-item>
                        <el-form-item label="保质期:">
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="选择日期" disabled v-model="form.data1" style="width: 100%;"></el-date-picker>
                            </el-col>
                            <el-col class="line" :span="2">-</el-col>
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="选择日期" disabled v-model="form.data2" style="width: 100%;"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="生产厂商:">
                            <el-input v-model="form.shop"></el-input>
                        </el-form-item>
                        <el-form-item label="入库日期:">
                            <el-input v-model="form.intoroomtime"></el-input>
                        </el-form-item>
                        <el-form-item label="管理员id:">
                            <el-input v-model="form.rootid"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗规格:">
                            <el-input v-model="form.guige"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗费用:">
                            <el-input v-model="form.price"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗简介:">
                            <el-input v-model="form.desction"></el-input>
                        </el-form-item>
                        <el-form-item label="接种对象:">
                            <el-input v-model="form.canperson"></el-input>
                        </el-form-item>
                        <el-form-item label="接种部位:">
                            <el-input v-model="form.body"></el-input>
                        </el-form-item>
                        <el-form-item label="接种程序:">
                            <el-input v-model="form.way"></el-input>
                        </el-form-item>
                        <el-form-item label="有效期:">
                            <el-input v-model="form.usefultime"></el-input>
                        </el-form-item>
                        <el-form-item label="注意事项:">
                            <el-input v-model="form.warn"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="updateVacc">保存更改</el-button>
                            <el-button @click="descVisible=false">取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>

            <div>
                <!--疫苗信息添加-->
                <el-dialog title="疫苗信息添加" :visible.sync="addVisible">
                    <el-form ref="form"  :rule="addFormRules"  :inline="true" :model="form" label-width="80px" class="demo-form-inline">
                        <el-form-item label="疫苗编号:">
                            <el-input v-model="form.vaccid"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗名称:">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗种类:">
                            <el-input v-model="form.type"></el-input>
                        </el-form-item>
                        <el-form-item label="冷库编号:">
                            <el-input v-model="form.coldroomid"></el-input>
                        </el-form-item>
                        <el-form-item label="存储温度:">
                            <el-input v-model="form.temp1"></el-input>
                        </el-form-item>
                        <el-form-item label="运输温度:">
                            <el-input v-model="form.temp2"></el-input>
                        </el-form-item>
                        <el-form-item label="保质期:">
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="选择日期" v-model="form.createtime" style="width: 100%;"></el-date-picker>
                            </el-col>
                            <el-col class="line" :span="2">-</el-col>
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="选择日期" v-model="form.keep" style="width: 100%;"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="生产厂商:">
                            <el-input v-model="form.shop"></el-input>
                        </el-form-item>
                        <el-form-item label="入库日期:">
                            <el-input v-model="form.intoroomtime"></el-input>
                        </el-form-item>
                        <el-form-item label="管理员id:">
                            <el-input v-model="form.rootid"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗规格:">
                            <el-input v-model="form.guige"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗费用:">
                            <el-input v-model="form.price"></el-input>
                        </el-form-item>
                        <el-form-item label="疫苗简介:">
                            <el-input v-model="form.desction"></el-input>
                        </el-form-item>
                        <el-form-item label="接种对象:">
                            <el-input v-model="form.canperson"></el-input>
                        </el-form-item>
                        <el-form-item label="接种部位:">
                            <el-input v-model="form.body"></el-input>
                        </el-form-item>
                        <el-form-item label="接种程序:">
                            <el-input v-model="form.way"></el-input>
                        </el-form-item>
                        <el-form-item label="有效期:">
                            <el-input v-model="form.usefultime"></el-input>
                        </el-form-item>
                        <el-form-item label="注意事项:">
                            <el-input v-model="form.warn"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addVacc">添加信息</el-button>
                            <el-button @click="addVisible=false">取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>
            <!-- 疫苗列表 -->
            <el-table :data="vacclist" border stripe height="500px">
                <el-table-column label="学年"   width="80px" prop="xid"></el-table-column>
                <el-table-column label="学期" width="80px" prop="type"></el-table-column>
                <el-table-column label="课程名" width="140px" prop="subjectname"></el-table-column>
                <el-table-column label="开设学院" width="120px"  prop="school_group"></el-table-column>
                <el-table-column label="课程类型"  width="100px"  prop="subjecttype"></el-table-column>
                <el-table-column label="授课教师:" width="120px" prop="teacherid"></el-table-column>
                <el-table-column label="学分:" width="120px" prop="xuefen"></el-table-column>
                <el-table-column label="当前绩点:" width="120px" prop="jidian"></el-table-column>
                <el-table-column label="成绩:" width="120px" prop="grade"></el-table-column>
                <el-table-column label="评价:" width="120px" prop="ispass"></el-table-column>
                <el-table-column label="操作" v-if="root==1" width="250px" header-align="center">
                    <template slot-scope="scope">
                        <!-- 修改 -->
                        <el-button-group>
                            <el-tooltip effect="dark" content="报废过期疫苗" placement="top-start" :enterable="false">
                                <el-button type="danger"  size="mini" @click="deleteVacc(scope.row.vaccid)"  icon="el-icon-warning">报废</el-button>
                            </el-tooltip>
                            <el-button type="success"  size="mini" @click="isback(scope.row)"  icon="el-icon-edit">退还</el-button>
                            <el-button type="primary"  size="mini" @click="isout(scope.row)"  icon="el-icon-edit">出库</el-button>
                        </el-button-group>
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
        </el-card>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                // 请求数据
                root:0,//权限控制器
                queryInfo: { //参数控制
                    query: "",
                    pageNum: 1,
                    pageSize: 20,
                    type:'选择学年',
                    xid:"选择学期"
                },
                vacclist: [],// 用户列表
                total: 0, // 最大数据记录
                addDialogVisible: false ,// 添加对话框显示
                descVisible:false,//疫苗详情对话框
                // 控制修改用户对话框显示/隐藏
                editDialogVisible:false,
                addVisible:false,//添加疫苗详情
                //详情页面表单
                form:{
                    id: '',
                    vaccid: '',
                    name: '',
                    type: '',
                    temp1: '',
                    temp2: '',
                    coldroomid: '',
                    createtime: '',
                    keep: '',
                    shop: '',
                    intoroomtime: '',
                    rootid: '',
                    guige: '',
                    price: '',
                    desction: '',
                    canperson: '',
                    body: '',
                    way: '',
                    usefultime: '',
                    warn: '',
                    isback: '',
                    isout: '',
                    data1:'',
                    data2:''
                },




            };
        },
        created() {
            this.getVaccList();
        },
        methods: {
            handleCommand(command) {
                if(command==1){
                    this.queryInfo.type="大一学年"
                }
                if(command==2){
                    this.queryInfo.type="大二学年"
                }
                if(command==3){
                    this.queryInfo.type="大三学年"
                }
                if(command==4){
                    this.queryInfo.type="大四学年"
                }
                if(command==5){
                    this.queryInfo.xid="第一学期"
                }
                if(command==6){
                    this.queryInfo.xid="第二学期"
                }
            },
            async getVaccList() {
                // 调用get请求
                const { data: res } = await this.$http.get("api/subject/getsubject?id="+window.sessionStorage.getItem("id"));
                this.vacclist = res.data; // 将返回数据赋值
                this.total = res.numbers; // 总个数
                console.log(this.vacclist)
            },

            // 监听pageSize改变的事件
            handleSizeChange(newSize) {
                this.queryInfo.pageSize = newSize;
                this.getVaccList(); // 数据发生改变重新申请数据
            },
            // 监听pageNum改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.pageNum = newPage;
                this.getVaccList();// 数据发生改变重新申请数据
            },
            // 修改用户状态flowStateChanged(scope.row)
            async flowStateChanged(flewinfo) {
                const { data: res } = await this.$http.put(
                    `flewError?id=${flewinfo.id}&error=${flewinfo.error}`
                );
                if (res != "success") {
                    flewinfo.error = !flewinfo.error;
                    return this.$message.error("该流量日志状态修改失败！！！");
                }
                this.$message.success("该流量日志状态修改成功！！！");
            },
            // 监听添加用户
            addDialogClosed(){
                this.$refs.addFormRef.resetFields();// 重置表单项
            },
            openaddshow(){
                this.addVisible = true;
                /*         this.form.id = "";this.form.name = "";this.form.vaccid = "";
                         this.form.temp1 = "";this.form.temp2 = "";this.form.type = "";
                         this.form.createtime = "";this.form.keep = "";this.form.data1 = "";
                         this.form.data2 = "";this.form.desction = "";this.form.shop = "";
                         this.form.intoroomtime = "";this.form.warn = "";this.form.usefultime = "";this.form.price = "";
                         this.form.body = "";this.form.canperson = "";this.form.guige = "";
                         this.form.rootid = "";this.form.way = "";this.form.coldroomid = "";*/
            },
            //条件查询
            async seacher() {
                var arr =[];
                this.vacclist = arr;
                const {data: res} = await this.$http.get("api/subject/getselectCheck?id=" + window.sessionStorage.getItem("id") +
                    "&type=" + this.queryInfo.type + "&xid=" + this.queryInfo.xid);
                this.vacclist = res.data; // 将返回数据赋值
                this.total = res.numbers; // 总个数
                console.log(this.vacclist)
            },
            // 添加用户
            async  addVacc() {
                // 发起请求
                const { data: res } = await this.$http.post("api/vacc/addvaccine",this.form);
                if (res != "success") {
                    return this.$message.error("添加失败！！！");
                }
                this.$message.success("添加成功！！！");
                //隐藏
                this.addVisible = false;
                this.getVaccList();
            },



            addperson(){
                this.addDialogVisible = true;
            },
            // 关闭窗口
            editDialogClosed(){
                this.$refs.editFormRef.resetFields();
            },
            // 报废按钮
            async deleteVacc(id){
                // 弹框
                const confirmResult = await this.$confirm('是否选择报废该疫苗, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 成功删除为confirm 取消为 cancel
                if(confirmResult!='confirm'){
                    return this.$message.info("已取消操作");
                }
                const {data:res} = await this.$http.get("api/vacc/delectofvaccid?vaccid="+id);
                if (res == "no") {
                    return this.$message.error("编号为"+id+"疫苗未过保质期,无法报废");
                }else if(res=='success'){
                    this.$message.success("报废成功！！！");
                    this.getVaccList();
                }else{
                    this.$message.error("报废操作失败，服务器出现问题");
                }
            },
            async isback(row){
                const confirmResult = await this.$confirm('是否确认退还?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 成功删除为confirm 取消为 cancel
                if(confirmResult!='confirm'){
                    return this.$message.info("已取消操作");
                }
                const {data:res} = await this.$http.post("api/vacc/isback",row);
                if (res == "error") {
                    return this.$message.error("编号为"+id+"退还失败");
                }else if(res=='success'){
                    this.$message.success("退还成功！！！");
                    this.getVaccList();
                }else{
                    this.$message.error("操作失败，服务器出现问题");
                }
            },
            //出库管理
            async isout(row){
                const confirmResult = await this.$confirm('是否确认出库?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).catch(err => err)
                // 成功删除为confirm 取消为 cancel
                if(confirmResult!='confirm'){
                    return this.$message.info("已取消操作");
                }
                const {data:res} = await this.$http.post("api/vacc/isout",row);
                if (res == "error") {
                    return this.$message.error("编号为"+id+"出库失败");
                }else if(res=='success'){
                    this.$message.success("出库成功！！！");
                    this.getVaccList();
                }else{
                    this.$message.error("操作失败，服务器出现问题");
                }
            },
            //查看疫苗详情信息
            async getvaccdataofvaccid(vaccid){
                this.descVisible = true;
                const { data: res } = await this.$http.get("api/vacc/getdataofvaccid?vaccid="+vaccid);
                if(res=="no"){
                    this.$message.success("疫苗信息不存在，无法查看")
                }else if(res=="error"){
                    this.$message.error("非法输入");
                }else{
                    this.form.id  = res.id;this.form.name = res.name;this.form.vaccid = res.vaccid;
                    this.form.temp1 =res.temp1;this.form.temp2 = res.temp2;this.form.type = res.type;
                    this.form.createtime = res.createtime; this.form.keep = res.keep;
                    this.form.data1 = res.data1;this.form.data2 = res.data2;
                    this.form.desction = res.desction;this.form.shop = res.shop;this.form.intoroomtime = res.intoroomtime;
                    this.form.warn = res.warn;this.form.usefultime = res.usefultime;this.form.price = res.price;
                    this.form.body = res.body;this.form.canperson = res.canperson;this.form.guige = res.guige;
                    this.form.rootid=res.rootid;this.form.way = res.way;this.form.coldroomid = res.coldroomid;
                }
                console.log(this.from)

            },
            //保存修改信息
            async updateVacc(){
                const { data: res } = await this.$http.post("api/vacc/updatevacc",this.form);
                if(res=="success"){
                    this.$message.success("保存修改成功");
                    this.descVisible = false;
                    this.getVaccList();
                }else{
                    this.$message.error("保存失败！");
                }
            },

            //通过名称搜索疫苗
            async getdataformname(){
                const {data:res} = await this.$http.post("api/vacc/getdataofname",this.queryInfo);
                if(res=="error"){
                    this.$message.error("参数错误");
                }else{
                    this.vacclist = res.data;
                    this.total = res.number;
                }
            },
            //修改用户使用账号状态
            async applyIsdelectChanged(row){
                const { data: res } = await this.$http.get("api/user/delect?id="+row.id+"&&isdelect="+row.isdelect);
                if(res=="success"){
                    this.$message.success("修改"+res)
                }else{
                    this.$message.error("修改"+res)
                }
            }
        }
    };
</script>
<style lang="less" scoped>
    .butt1{
        width: 40px;
        height: 25px;
    }
</style>
