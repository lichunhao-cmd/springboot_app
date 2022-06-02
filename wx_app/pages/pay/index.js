// pages/pay/index.js

import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
const api = require('../../utils/wxutil');
Page({

  /**
   * 页面的初始数据
   */
  data: {
      // 订单商品
      goodsList: [],
      allGoodsPrice: 0,
      remarks: "",
      // 备注
      
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var carList = wx.getStorageSync('carList');
    this.setData({
      goodsList: carList
    })
    this.getTotalPrice();
    console.log(carList)
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
  // 计算总价格
  getTotalPrice: function() {

    // 重置总价格
    this.data.allGoodsPrice = 0;

    var allGoodsPrice = this.data.allGoodsPrice
    var goodsList = this.data.goodsList
    for(var i in goodsList){
      allGoodsPrice += goodsList[i].goodsPrice * goodsList[i].quantity;
    }
    this.setData({
      allGoodsPrice: parseFloat(allGoodsPrice.toFixed(2)),
    })
},

  // 提交支付订单
  onSubmit: function() {
  
    // 生成订单,订单详情，用户的身份信息
    var tableNumber = wx.getStorageSync('tableNumber');
    if(!tableNumber){
      wx.showToast({
        title: '请去取号',
      })
      return
    }
    var url = '/order/create';
    var params = {
      'orderDTOS':this.data.goodsList,
      'allGoodsPrice':this.data.allGoodsPrice,
      'tableNumber': tableNumber,
      'buyerRemarks':this.data.remarks
    }

    api.post(url,{},params).then((res)=>{
      // 调用微信支付 
    
        wx.showToast({
          title: '支付成功！',
        })
        // 清空购物车 缓存
        wx.removeStorageSync('carList');
        setTimeout(function () {
          // 返回首页并刷新
          wx.reLaunch({
            url: '/pages/index/index',
          })
       }, 1500);
    })
  },
   // 获取备注
   getRemarks:function(e) {
    var remarks = e.detail;
    this.setData({
      remarks: remarks
    })
    
   }
})