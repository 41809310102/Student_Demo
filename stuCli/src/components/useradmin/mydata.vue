<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>数据汇总</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row :gutter="20">
                <el-col :span="16"><div class="grid-content bg-purple"></div>
                <el-table :data="tableData" height="250" border style="width: 100%">
                        <el-table-column prop="id" label="序号" width="60"></el-table-column>
                    <el-table-column prop="grade" label="活动积分" width="100">
                        <template slot-scope="scope">
                          <el-tag type="danger">{{scope.row.grade}}分</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="username" label="姓名" width="180"></el-table-column>
                    <el-table-column prop="cardid" label="学生卡" width="180"></el-table-column>
                    <el-table-column prop="shool_group" label="学院" width="180"></el-table-column>
                    <el-table-column prop="major" label="专业班级" width="180"></el-table-column>
                    <el-table-column prop="sex" label="性别" width="180"></el-table-column>
                    </el-table>
                </el-col>
                <el-col :span="8"><div class="grid-content bg-purple">
                    <div id="main" style="height: 300px;width: 500px;"></div>
                </div></el-col>
            </el-row>
        </el-card>
    </div>
</template>
<style lang="less">

</style>
<script>
    import * as echarts from 'echarts';//引入echarts资源
    export default {
        data() {
            return {
                tableData:[],
            };
        },
        created() {
          this.getStudentdata();
          this.getEchartdata();
        },
        mounted() {
           // this.getEchartdata();
        },
        methods: {

            //获取积分超过8分的学生
            async getStudentdata() {
                const {
                    data: res
                } = await this.$http.post("api/user/gradetop");
                if(res.code==1){
                    this.tableData = res.data;
                    this.$message.success(res.msg);
                }else{
                    this.$message.error(res.msg);
                }
            },


            //获取当前圆饼通过统计图
            async  getEchartdata(){
                const { data: res } = await this.$http.post("api/user/getCross"); //调用后端接口为api/echartdata的接口
                var chartDom = document.getElementById('main');
                var myChart = echarts.init(chartDom);
                var option;
                option = {
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        top: '5%',
                        left: 'center'
                    },
                    series: [
                        {
                            name: '当前人数',
                            type: 'pie',
                            radius: ['40%', '70%'],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 10,
                                borderColor: '#fff',
                                borderWidth: 2
                            },
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '40',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: res.data,
                        }
                    ]
                };
                option && myChart.setOption(option);
            },

        }
    };
</script>
