//post请求 url：请求路径，请求header，params请求参数，app全局变量

const baseUrl = "http://localhost:8080";
function post(url, headers, params) {
  
  let promise = new Promise(function (resolve, reject) {
    var token = wx.getStorageSync('token');
    console.log(token)
    if(token != null){
      headers.token = token;
      console.log(headers) 
      wx.request({
        url: baseUrl+url,
        header: headers,
        data: params,
        method: 'POST',
        success: function (res) {
        if(res.data.code == 401){
          wx.showToast({
            title: 'token已过期请重新登录！',
            icon: 'none',
            duration: 1500
          })
        }else{
          //自行处理返回结果
          console.log('返回结果：')
          console.log(res.data)
          resolve(res)
        } }
  
      })
    }else{
      wx.showToast({
        title: 'token已过！',
        icon: 'none',
        duration: 1500
      })
    }
    
});
return promise;
}


//get请求
function get(url, params) {
  let promise = new Promise(function (resolve, reject) {
    var token = wx.getStorageSync('token');
    console.log(token)
    var headers = {
      'token': token
    }
    if(token != null){
    wx.request({
      url: baseUrl+url,
      header: headers,
      data: params,
      method: 'GET', 
      success: function (res) {
        if(res.data.code == 401){
          wx.showToast({
            title: 'token已过期请重新登录！',
            icon: 'none',
            duration: 1500
          })
        }else{
          //自行处理返回结果
          console.log('返回结果：')
          console.log(res.data)
          resolve(res)
        } }

      })
  
    }  });
  return promise;
}    

module.exports = {
    get: get,
    post: post
}