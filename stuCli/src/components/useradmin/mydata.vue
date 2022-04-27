<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>数据汇总</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
</template>
<script>
    import * as echarts from 'echarts';//引入echarts资源
    export default {
        data() {
            return {

            };
        },
        created() {

        },
        mounted() {
           // this.getEchartdata();
        },
        methods: {

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
