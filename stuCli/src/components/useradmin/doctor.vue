<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户信息</el-breadcrumb-item>
            <el-breadcrumb-item>退款管理</el-breadcrumb-item>
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
                            <el-tooltip effect="dark" content="拒绝退款" placement="top-start" :enterable="false">
                                <el-button type="danger"  size="mini" @click="deleteApply(scope.row)"  icon="el-icon-warning">拒绝</el-button>
                            </el-tooltip>
                            <el-button type="success"  size="mini" @click="SuccessApply(scope.row)"  icon="el-icon-edit">同意</el-button>
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
                    query: "退款中",
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
                const { data: res } = await this.$http.post("api/apply/getBackMoneys",this.queryInfo);
                this.applytable = res.apply; // 将返回数据赋值
                this.total = res.number; // 总个数
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
                const { data: res } = await this.$http.post("api/apply/isFailBack",row);
                if(res=="success"){
                    this.$message.success("拒绝成功");
                    this.getApplydata();
                }else{
                    this.$message.error("操作失败！")
                }
            },

            //同意
            async SuccessApply(row){
                // 调用post请求
                const { data: res } = await this.$http.post("api/apply/isSuccessBack",row);
                if(res=="success"){
                    this.$message.success("退款成功！");
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