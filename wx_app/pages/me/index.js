// pages/me/index.js
const api = require('../../utils/wxutil');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasUserInfo: false,
    userInfo: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 页面加载时使用用户授权逻辑，弹出确认的框  
    this.userAuthorized()
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
  userAuthorized() {
    wx.getSetting({
      success: data => {
        if (data.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: data => {
              this.setData({
                hasUserInfo: true,
                userInfo: data.userInfo
              })
            }
          })
        } else {
          this.setData({
            hasUserInfo: false
          })
        }
      }
    })
  },

  // 登录
  onGetUserInfo(e) {

    var that = this;
    
    const userInfo = e.detail.userInfo
    if (userInfo) {
      // 1. 小程序通过wx.login()获取code
      wx.login({
        success: function(login_res) {
          //获取用户信息
          wx.getUserInfo({
            success: function(info_res) {
              // 2. 小程序通过wx.request()发送code到开发者服务器
              var url = '/user/wx_login';
            var header =  {
              'content-type': 'application/x-www-form-urlencoded'
            }; 
            var params =  {
              code: login_res.code, //临时登录凭证
              rawData: info_res.rawData, //用户非敏感信息
              signature: info_res.signature, //签名
              encrypteData: info_res.encryptedData, //用户敏感信息
              iv: info_res.iv //解密算法的向量
            };
            // 请求后台
            api.post(url,header,params).then((res)=>{
                if (res.data.code == 0) {
                  //console.log(res.data.data)
                  that.setData({
                    hasUserInfo: true,
                    userInfo: userInfo
                  })
                 wx.setStorageSync('token',res.data.data);
                
                } else{
                  console.log('服务器异常');
                }
              })
                    
            }
          })
        }
      })
    }
  
  },
   //button按钮事件 -> 获取用户信息
   onGotUserInfo: function(e) {
    if (e.detail.userInfo) {
      var user = e.detail.userInfo;
      this.setData({
        isShowUserName: true,
        userInfo: e.detail.userInfo,
      })
      //user.openid = app.globalData.openid;
      //app._saveUserInfo(user);
    } else{
      app._showSettingToast('登录需要允许授权');
    }
  },
})
