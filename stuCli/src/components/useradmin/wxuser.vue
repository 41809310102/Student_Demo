<template>
    <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户信息</el-breadcrumb-item>
        <el-breadcrumb-item>预约审核</el-breadcrumb-item>
    </el-breadcrumb>
        <el-card>
        <el-table height="500"
                  :data="applytable"
                  border
                  style="width: 100%">
            <el-table-column
                    prop="id"
                    label="预约编号"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="wxusername"
                    label="受种用户姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="local"
                    width="150"
                    label="接种地点">
            </el-table-column>
            <el-table-column
                    prop="time"
                    width="120"
                    label="接种时间">
            </el-table-column>
            <el-table-column
                    prop="vaccid"
                    width="120"
                    label="预约疫苗编号">
            </el-table-column>
            <el-table-column label="处理结果:"  width="80px" >
                <template slot-scope="scope">
                    <el-tag  v-if="scope.row.success" type="success">已同意</el-tag>
                    <el-tag  v-if="scope.row.fail" type="danger">已拒绝</el-tag>
                    <el-tag  v-if="scope.row.fail==scope.row.success" type="warning">未处理</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    prop="state"
                    width="120"
                    label="预约状态？">
            </el-table-column>
            <el-table-column label="支付状态？"  width="120px" >
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.istopay"    size="mini" active-color="green"></el-switch>
                </template>
            </el-table-column>
            <el-table-column label="操作"  width="250px" header-align="center">
                <template slot-scope="scope">
                    <!-- 修改 -->
                    <el-button-group>
                        <el-tooltip effect="dark" content="拒绝该预约后，发邮件给该预约人" placement="top-start" :enterable="false">
                            <el-button type="danger"  size="mini" @click="deleteApply(scope.row)"  icon="el-icon-warning">拒绝</el-button>
                        </el-tooltip>
                        <el-button type="success"  size="mini" @click="SuccessApply(scope.row)"  icon="el-icon-edit">同意</el-button>
                        <el-tooltip effect="dark" content="通知付款" placement="top-start" :enterable="false">
                        <el-button type="primary"  size="mini" @click="topay(scope.row)"  icon="el-icon-edit">通知</el-button>
                        </el-tooltip>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>
        </el-card>
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
                // 请求数据
                queryInfo: {
                    query: "",
                    pageNum: 1,
                    pageSize: 10,
                },
             total:0,
                applytable:[],
            }
        },
        created() {
            this.getApplydata();
        },
        methods: {

            async  getApplydata(){
                // 调用post请求
                const { data: res } = await this.$http.post("api/apply/allapply",this.queryInfo);
                this.applytable = res.data; // 将返回数据赋值
                this.total = res.numbers; // 总个数
            },
            // 监听pageSize改变的事件
            handleSizeChange(newSize) {
                this.queryInfo.pageSize = newSize;
                this.getApplydata(); // 数据发生改变重新申请数据
            },
            // 监听pageNum改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.pageNum = newPage;
                this.getApplydata();// 数据发生改变重新申请数据
            },
            //拒绝
            async deleteApply(row){
                // 调用post请求
                const { data: res } = await this.$http.post("api/apply/fail",row);
                if(res=="success"){
                    this.$message.success("该预约已经被拒绝");
                    this.getApplydata();
                }else{
                    this.$message.error("操作失败！")
                }
            },

            //同意
            async SuccessApply(row){
                // 调用post请求
                const { data: res } = await this.$http.post("api/apply/success",row);
                if(res=="success"){
                    this.$message.success("该预约审核通过！");
                    this.getApplydata();
                }else{
                    this.$message.error("操作失败！")
                }
            },
            async topay(row){
                // 调用post请求
                const { data: res } = await this.$http.post("api/apply/message",row);
                if(res=="success"){
                    this.$message.success("提醒支付成功！");
                    this.getApplydata();
                }else{
                    this.$message.error("操作失败！")
                }
            }
        },
    }
</script>