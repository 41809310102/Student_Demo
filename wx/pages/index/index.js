// index.js
// 获取应用实例
const app = getApp()

Page({
  data: {
    scanCode1:'扫码',
    formdata: '',  
    foundlocation:'',
    lat1:'',
    lng1:'',
    lat2:'',
    lng2:''
  },
  // 事件处理函数

  onLoad() {
    
  },


  //二维码识别
  scanCodeEvent1: function(){
    var that = this;
    wx.scanCode({
      onlyFromCamera: true,// 只允许从相机扫码
      success(res){
           // 扫码成功后  在此处理接下来的逻辑
              that.setData({
               scanCode1: res.result,
              })
              if(that.data.scanCode1!='扫码'){
                that.wxvaccdata();
              }
      }
    })    
  },

    //扫描获取设置的签到位置
    wxvaccdata: function(){
      var that = this;
      wx.showLoading({
        title: '正在开启定位',
      })
      wx.request({
        url: that.data.scanCode1,
        header: {
          // 'content-type': 'application/json'
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        success:function(res){
          console.log(res.data);
          if(res.data.code==0){
            that.setData({
              lat2:res.data.x,
              lng2:res.data.y
             })
           //这里将二维码的数据获取，然后保存，进行签到确认
           wx.hideLoading();
           that.handelmap();
          }
        }
      })  
    },
  


  // 位置定位
   handelmap(e) {
    console.log(e)
    var that = this;
    var address =null
    var name =null
    wx.getLocation({
      type: 'gcj02',
       success: function (res) {
        that.setData({
            lat1:res.latitude,
            lng1:res.longitude,
          })
        }
    })
    wx.chooseLocation({
      success: function (res) {
        console.log(res)
        address = res.address;
        name = res.name;
        console.log(name + '   ' + address)
        //这里将地址信息发送给RFL服务器
        that.setData({
          foundlocation: name,
        })
        that.juli(that.data.lat1,that.data.lng1,that.data.lat2,that.data.lng2)
     },
      fail: function (e) {
        console.log(e)
      },
    })
  },

  //计算两点之间的距离
 juli(lat1, lng1, lat2, lng2) {
   var that = this;
  var radLat1 = lat1 * Math.PI / 180.0;
  var radLat2 = lat2 * Math.PI / 180.0;
  var a = radLat1 - radLat2;
  var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
  var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
  s = s * 6378.137;
  s = Math.round(s * 10000) / 10000;
  //计算地图地位点和自己真实位置的距离
  if (Number(s>0.1)){
    wx.showModal({
      title: '温馨提示',
      content: '位置不能离你大于100米！',
      success: function (res) {
        if (res.confirm) {
        } else if (res.cancel) {
        }
      }
    });
  }else{
     that.getSign();
  }
},

//正常打卡
getSign(){
  var that = this;
  var user = wx.getStorageSync('user')
  var time = getApp().time();
  var sign={
    "addsigntime": time,
    "cardid": user.cardid,
    "isdelect": 0,
    "local": that.data.foundlocation,
    "major": user.major,
    "signtime": time,
    "uid": user.id,
    "uname": user.username
  }
  wx.request({
    url: app.data.url+'api/Sign/addSign',
    data:sign,
    header: {
      'content-type': 'application/json'
    },
    method:"POST",
    success:function(res){
      if(res.data.code==1){
        wx.showModal({
          title: '签到提示',
          content: res.data.msg,
          success: function (res) {
            if (res.confirm) {
              wx.redirectTo({
                url: '../index/index',
              })
            } else if (res.cancel) {
            }
          }
        });
      }else{
        wx.showModal({
          title: '签到提示',
          content: res.data.msg,
          success: function (res) {
            if (res.confirm) {
              wx.redirectTo({
                url: '../index/index',
              })
            } else if (res.cancel) {
            }
          }
        });
      }
    }
  })
 

},

})
