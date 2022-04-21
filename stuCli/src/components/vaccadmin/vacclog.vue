<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>疫苗管理</el-breadcrumb-item>
            <el-breadcrumb-item>历史操作</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row :gutter="20">
                <el-col :span="16"><div class="grid-content bg-purple">
                    <el-card>
                        <el-row :gutter="20">
                            <el-col :span="5"><div class="grid-content bg-purple">
                                <el-dropdown split-button type="primary" @command="handleCommand">{{queryInfo.style}}
                                    <el-dropdown-menu slot="dropdown">
                                        <el-dropdown-item command="1">管理员id</el-dropdown-item>
                                        <el-dropdown-item command="2">疫苗编号</el-dropdown-item>
                                        <el-dropdown-item command="3">疫苗状态</el-dropdown-item>
                                    </el-dropdown-menu>
                                </el-dropdown>
                            </div></el-col>
                            <el-col :span="10"><div class="grid-content bg-purple">
                                <!-- 搜索添加 -->
                                <el-input style="margin-left: -30px" placeholder="请输入数据" v-model="queryInfo.query" clearable @clear="">
                                    <el-button slot="append" icon="el-icon-search" @click=""></el-button>
                                </el-input>
                            </div></el-col>
                        </el-row>
                        <!-- 操作列表 -->
                        <el-table :data="vacclist" height="500" border stripe>
                            <el-table-column label="疫苗名称" width="120px" prop="name"></el-table-column>
                            <el-table-column label="管理员id" width="80px" prop="rootid"></el-table-column>
                            <el-table-column label="疫苗编号" width="90px" prop="vaccid"></el-table-column>
                            <el-table-column label="操作日期" width="80px" prop="data3"></el-table-column>
                            <el-table-column label="原因" width="90px" prop="because"></el-table-column>
                            <el-table-column label="当前状态:"  width="80px"  prop="isadmin">
                                <template slot-scope="scope">
                                    <el-tag  v-if="scope.row.isback" type="warning">已报废</el-tag>
                                    <el-tag  v-if="scope.row.isout" type="success">已出库</el-tag>
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
                </div></el-col>
                <el-col :span="8"><div class="grid-content bg-purple">
                   <el-card>

                   </el-card>
                </div></el-col>
            </el-row>
        </el-card>
    </div>
</template>


<style lang="less" scoped>
</style>
<script>
    export default {
        data(){
            return {
                queryInfo: {
                    query: "",
                    pageNum: 1,
                    pageSize: 20,
                    style:'疫苗编号',
                },
                vacclist: [],// 用户列表
                total: 0, // 最大数据记录
            }

        },
        created() {
            this.getVaccloglist();
        },
        methods: {
            handleCommand(command) {
                if(command==1){
                    this.queryInfo.style="管理员id"
                }
                if(command==2){
                    this.queryInfo.style="疫苗编号"
                }
                if(command==3){
                    this.queryInfo.style="疫苗状态"
                }
            },
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
          async  getVaccloglist(){
                const {data:res} = await this.$http.post("api/vacc/log",this.queryInfo);
                if(res!=null){
                   this.vacclist = res.data;
                   this.total = res.number;
                }
            }
        },
    }

</script>