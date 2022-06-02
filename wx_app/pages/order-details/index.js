// pages/order-details/index.js

const api = require('../../utils/wxutil');
Page({

  /**
   * 页面的初始数据
   */
  data: {

    baseGoodsUrl: "http://localhost:8080/goodsImg",
    orderList:[],
    orderListNull: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var urlA = '/order/list'
    api.get(urlA,{}).then((res)=>{
      var orderList = res.data.data;
      this.setData({
        orderList:orderList
      })
    })

    var urlB = '/order/comment_null'
    api.get(urlB,{}).then((res)=>{
      var orderListNull = res.data.data;
      this.setData({
        orderListNull:orderListNull
      })
    })

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

  // 立即支付
  toPay: function() {
    
    wx.showToast({
      title: '支付成功',
    })
  },

  // 取消订单
  toPayCancel: function() {
    wx.showToast({
      title: '取消订单',
    })
  },
  
  // 下拉刷新
  onPullDownRefresh() {
    this.onLoad();
  },

  // to 评价
  toComment: function(e) {
    
    var orderId = e.target.dataset.id;
    wx.navigateTo({
      url: '/pages/comments/index?orderId='+orderId
    })
  }
})