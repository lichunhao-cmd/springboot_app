// pages/comments/index.js

const api = require('../../utils/wxutil');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    autosize: { maxHeight: 100, minHeight: 100 },

    value:0,
    message:"",
    commentName: "",
    orderId: ""

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
    this.setData({
      orderId:options.orderId
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
  // 提交按钮
  onSubmit: function() {
    
    var url = "/comments/create";
    var params = {
      "orderId":this.data.orderId,
      "commentStars":this.data.value,
      "commentContent":this.data.message,

    };
    api.post(url,{},params).then((res)=>{

      if(res.data.code == 0){
        var pages = getCurrentPages();
        var beforePage = pages[pages.length - 2];
        beforePage.onLoad(beforePage.options);
        wx.navigateBack({
          delta: 1,
        })
      }else{
        wx.showToast({
          title: '评论失败',
        })
      }

    })
  },
  onChange:function(event) {
    this.setData({
      value:event.detail
    })
  },

  onChangeText: function(event) {
    
    this.setData({
      message: event.detail
    })
  }
})