// pages/queue/index.js
const api = require('../../utils/wxutil');
Page({
  /**
   * 页面的初始数据
   */
  data: {

    tableList: [],

    tableNumber:0,

    tableStatus: false,

    show: false,


    actions: [
      {
        name: '取消桌号'
      },
    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    var that = this;
    wx.request({
      url: 'http://localhost:8080/tables/list',
      success(res){
        var tableList = res.data.data;
        that.setData({
          tableList: tableList,
        })
      }
    })

    // 初始化 读取本地缓存
    var tableNumber = wx.getStorageSync('tableNumber');
    if(tableNumber){
      this.setData({
        tableStatus: true,
        tableNumber: tableNumber
      })
    }

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
      
  },

  //A
  queuingGetA: function() {

    if(wx.getStorageSync('tableNumber')){
      wx.showToast({
        title: '请取消桌位，重新选择！',
      })
      return;
    }
    var url = '/tables/create';
    var params = {
      'tableType':'A'
    }
    api.get(url,params).then((res)=>{

      var tableNumber = res.data.data;
      this.onLoad();
      wx.setStorageSync('tableNumber', tableNumber)
      this.setData({
        tableNumber:tableNumber,
        tableStatus: true
      })
    })
  },

  //B
  queuingGetB:function() {

    if(wx.getStorageSync('tableNumber')){
      wx.showToast({
        title: '请取消桌位，重新选择！',
      })
      return;
    }

    var url = '/tables/create';
    var params = {
      'tableType':'B'
    }
    api.get(url,params).then((res)=>{

      var tableNumber = res.data.data;
      console.log(tableNumber);
      this.onLoad();
      wx.setStorageSync('tableNumber', tableNumber)
      this.setData({
        tableNumber:tableNumber,
        tableStatus: true
      })
    })

  },

  //C
  queuingGetC:function() {

    if(wx.getStorageSync('tableNumber')){
      wx.showToast({
        title: '请取消桌位，重新选择！',
      })
      return;
    }
    var url = '/tables/create';
    var params = {
      'tableType':'C'
    }
    api.get(url,params).then((res)=>{

      var tableNumber = res.data.data;
      console.log(tableNumber);
      this.onLoad();
      wx.setStorageSync('tableNumber', tableNumber)
      this.setData({
        tableNumber:tableNumber,
        tableStatus: true
      })
    })
    
  },

  // 取消桌号
  onClose() {
    this.setData({show: false}); 
  },
  onSelect(event) {
    console.log(event.detail);

    var url = '/tables/delete';
    var params = {
      'tableNumber': this.data.tableNumber
    }
    // 取消桌号
    api.get(url,params).then((res)=>{

      wx.removeStorageSync('tableNumber');
      this.setData({
        tableStatus: false
      })
      this.onLoad();
    })



  },

  tableChange: function() {
    this.setData({
      show:true
    })},
    onPullDownRefresh:function() {
      this.onLoad();
    },
  
})