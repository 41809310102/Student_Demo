<template>
  <!-- 面包屑导航 -->
  <div class="test2"">
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页></el-breadcrumb-item>
  </el-breadcrumb>
  <div>
    <el-row :gutter="20">
      <el-col :span="8" >
        <div class="grid-content bg-purple">
          <el-card  shadow="hover">
            <img  src="https://desk-fd.zol-img.com.cn/t_s960x600c5/g2/M00/0F/04/Cg-4WVVtECyIIrmqABCqFyoOCYkAAEg5wDCLrsAEKov842.jpg" style="height:100px;width:100px;border-radius:50%;margin-left:30%"/>
            <div style="margin-left:30%">
              <span>学生服务平台</span><br>
            </div>
          </el-card>

          <el-card shadow="hover" style="margin-top:10px;height: 400px">
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
              <el-form-item label="名称">
                <el-input v-model="formLabelAlign.name"></el-input>
              </el-form-item>
              <el-form-item label="活动区域">
                <el-input v-model="formLabelAlign.region"></el-input>
              </el-form-item>
              <el-form-item label="活动形式">
                <el-input v-model="formLabelAlign.type"></el-input>
              </el-form-item>
            </el-form>
            <el-button type="primary" style="margin-left:20%">提交</el-button>
            <el-button type="danger" >清除</el-button>
          </el-card>
        </div>
      </el-col>
      <el-col :span="16"><div class="grid-content bg-purple">
        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/1.png" class="image">
              <span class="mytext1"><el-button type="text">我的资料</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/2.png" class="image">
              <span class="mytext1"><el-button type="text">我的成绩</el-button></span>
              <span></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/3.png" class="image">
              <span class="mytext1"><el-button type="text">获奖记录</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/4.png" class="image">
              <span class="mytext1"><el-button type="text">我的申请</el-button></span>
            </el-card>
          </div></el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 5px;">
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/5.png" class="image">
              <span class="mytext1"><el-button type="text">我的社团</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/6.png" class="image">
              <span class="mytext1"><el-button type="text">在线签到</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/7.png" class="image">
              <span class="mytext1"><el-button type="text">我的投票</el-button></span>
            </el-card>
          </div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple">
            <el-card style="width: 220px;height: 75px;" :body-style="{ padding: '0px' }">
              <img src="../assets/icon/88.png" class="image">
              <span class="mytext1"><el-button type="text">我的问卷</el-button></span>
            </el-card>
          </div></el-col>
        </el-row>
        <el-card>
          <el-row :gutter="20" style="margin-top: 5px;">
            <el-col :span="12"><div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>签到记录</span>
                  <el-button style="float: right; padding: 3px 0" type="text">补卡</el-button>
                </div>
                <div v-for="o in 4" :key="o" class="text item">
                  {{'2022-4-21 ' + o }}
                </div>
              </el-card>
            </div>
            </el-col>
            <el-col :span="12"><div class="grid-content bg-purple">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>活动信息</span>
                  <el-button style="float: right; padding: 3px 0" type="text">刷新</el-button>
                </div>
                <div v-for="o in 4" :key="o" class="text item">
                  {{'2022-4-21 ' + o }}
                </div>
              </el-card>
            </div>
            </el-col>
          </el-row>
        </el-card>
      </div></el-col>
    </el-row>

  </div>


  </div>

</template>



<style>

  .image {
    width: 60px;
    height: 60px;
    margin-left: 5%;
    display: block;
    margin-top: 5%;

  }

  .mytext1{
    display: block;
    margin-left: 110px;
    margin-top: -50px;
  }
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 465px;
    height: 400px;
  }
</style>

<script>
  import * as echarts from 'echarts'
  export default {
    name: 'test2',
    data () {
      return {
        queryInfo: {
          query: "",
          pageNum: 1,
          pageSize: 4,

        },
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        },

        queryInfof: {
          query: "",
          pageNum: 1,
          pageSize: 100,
        },
      }
    },
    mounted: function () {
      this.getdataChart1();
    },



    methods: {
      handleClick(row) {
        console.log(row);
      },

      async   getdataChart1(){
        // const {data :res} = await this.$http.post("welcome/echart1"); //后端请求接口
        var chartDom = document.getElementById('myChart1');
        var myChart = echarts.init(chartDom);
        var option;
        setTimeout(function () {
          option = {
            legend: {},
            tooltip: {
              trigger: 'axis',
              showContent: false
            },
            dataset: {
              source: [
                ['product', '2012', '2013', '2014', '2015', '2016', '2017'],
                ['团队任务量', 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
                ['个人任务量', 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
                ['任务失败数', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
                ['任务成功数', 25.2, 37.1, 41.2, 18, 33.9, 49.1]
              ]
            },
            xAxis: { type: 'category' },
            yAxis: { gridIndex: 0 },
            grid: { top: '55%' },
            series: [
              {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
              },
              {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
              },
              {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
              },
              {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
              },
              {
                type: 'pie',
                id: 'pie',
                radius: '30%',
                center: ['50%', '25%'],
                emphasis: {
                  focus: 'self'
                },
                label: {
                  formatter: '{b}: {@2012} ({d}%)'
                },
                encode: {
                  itemName: 'product',
                  value: '2012',
                  tooltip: '2012'
                }
              }
            ]
          };
          myChart.on('updateAxisPointer', function (event) {
            const xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
              const dimension = xAxisInfo.value + 1;
              myChart.setOption({
                series: {
                  id: 'pie',
                  label: {
                    formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                  },
                  encode: {
                    value: dimension,
                    tooltip: dimension
                  }
                }
              });
            }
          });
          myChart.setOption(option);
        });
        option && myChart.setOption(option);
      },
    }
  }
</script>

