<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>仓库管理</el-breadcrumb-item>
            <el-breadcrumb-item>出库管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row :gutter="20">
            <el-col :span="14"><div class="grid-content bg-purple">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="领用记录" name="first">
                        <el-card>
                            <!-- 操作列表 -->
                            <el-table :data="firstvacclist" height="480px" border stripe>
                                <el-table-column label="疫苗种类" width="90px" prop="name"></el-table-column>
                                <el-table-column label="疫苗编号" width="90px" prop="vaccid"></el-table-column>
                                <el-table-column label="疫苗名称" width="90px" prop="type"></el-table-column>
                                <el-table-column label="有效期" width="80px" prop="usefultime"></el-table-column>
                                <el-table-column label="生产商" width="80px" prop="shop"></el-table-column>
                                <el-table-column label="所属冷库" width="120px" prop="coldroomid"></el-table-column>
                                <el-table-column label="销售金额" width="80px" prop="price"></el-table-column>
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
                    </el-tab-pane>
                    <el-tab-pane label="销售记录" name="second">
                        <el-card>
                            <!-- 操作列表 -->
                            <el-table :data="firstvacclist" height="480px" border stripe>
                                <el-table-column label="疫苗种类" width="90px" prop="name"></el-table-column>
                                <el-table-column label="疫苗编号" width="90px" prop="vaccid"></el-table-column>
                                <el-table-column label="疫苗名称" width="90px" prop="type"></el-table-column>
                                <el-table-column label="有效期" width="80px" prop="usefultime"></el-table-column>
                                <el-table-column label="生产商" width="80px" prop="shop"></el-table-column>
                                <el-table-column label="所属冷库" width="120px" prop="coldroomid"></el-table-column>
                                <el-table-column label="销售金额" width="80px" prop="price"></el-table-column>
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
                    </el-tab-pane>
                </el-tabs>
            </div></el-col>
            <el-col :span="10"><div class="grid-content bg-purple">
                <el-card shadow="hover" style="margin-top:60px">
                 <div>
                     <div id="myechart" style="width: 500px;height: 500px;"></div>
                 </div>
                </el-card>
            </div></el-col>
        </el-row>
    </div>
</template>
<script>
   import * as echarts from 'echarts';//引入echarts资源
    export default {
        data() {
            return {
                queryInfo: {
                    query: "一类疫苗",
                    pageNum: 1,
                    pageSize: 20,
                    style:'疫苗编号',
                    roomid:0,
                },
                activeName: 'first',
                firstvacclist:[],
                total:0,
            };
        },
        created() {
            this.getIsoutdata();
            //this.getEchartdata();
        },
        mounted() {
           this.getEchartdata();
        },
        methods: {
            async  getIsoutdata(){
                // 调用post请求

                const { data: res } = await this.$http.post("api/room/getVaccisout",this.queryInfo);
                this.firstvacclist = res.data; // 将返回数据赋值
                this.total = res.number; // 总个数
            },
            // 监听pageSize改变的事件
            handleSizeChange(newSize) {
                this.queryInfo.pageSize = newSize;
                this.getIsoutdata(); // 数据发生改变重新申请数据
            },
            // 监听pageNum改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.pageNum = newPage;
                this.getIsoutdata();// 数据发生改变重新申请数据
            },
            handleClick(tab, event) {
                if(this.activeName=='first'){
                    this.queryInfo.query = "一类疫苗"
                }else{
                    this.queryInfo.query = "二类疫苗"
                }
                this.getIsoutdata();
            },

            //获取柱状图示意
          async  getEchartdata(){
                const { data: res } = await this.$http.post("api/room/echartdata"); //调用后端接口为api/echartdata的接口

                if(res!=null){
                    //后端定义一个保存字符串的链表，通过‘,’切分,返回一个数组,或者后端返回一个数组链表
                    var xdata = res[0].split(","); //res是链表类型，第一个元素保存x轴data
                    var ydata = res[1].split(","); //res是链表类型，第二个元素保存y轴data
                }
                var chartDom = document.getElementById('myechart');
                var myChart = echarts.init(chartDom);
                var option;
                option = {
                    title: {
                        text: '出库数量检测表',
                        subtext: '以当月为准，下月清空'
                    },
                    xAxis: {
                        type: 'category',
                        data: xdata,
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: ydata,
                            type: 'bar'
                        }
                    ]
                };
                option && myChart.setOption(option);
            },

        }
    };
</script>