// index.js

    //所有商品列表
    let categories = []
    const api = require('../../utils/wxutil');
Page({
  data:{
    
    // 评论
    commentList: [],
    
    //
    baseGoodsUrl: "http://localhost:8080/goodsImg",
    // 购物车本地缓存
    
    // 轮播图
    carouselList: [],
    imgurl: "http://localhost:8080/images",
    // 左菜单，类目列表
    menu_list: [],
    //展示菜品
    foodList: [], 
    // 购物车
    carList: [],
    // 是否显示购物车
    showCartPop: false,
    shopIsOpened: true,
    // 总价格
    totalPrice: 0.0,
    // 评分
    rateValue: 3,

  },
  // 初始化数据
  onLoad: function(){

    var that = this
    // 左分类菜单
    var menu_list = this.data.menu_list;
    // 右菜品菜单
    var foodList = this.data.foodList;
    // 获取左侧分类菜单数据
    categories = [];
    // 获取右侧菜品列表数据
    var resFood = [];

    // 列表
    api.get('/goods/list',{}).then((res)=>{
      if (res && res.data && res.data.data && res.data.data.length > 0){
        let dataList = res.data.data;
        dataList.forEach((item, index) => {
          item.id = index;
          console.log(item);
          categories.push(item);
          if (index == 0) {
            //默认选中第一项
            resFood = item.goods; 
          }
          if(item.goods != null){
      
          item.goods.forEach((food, index) => {
            food.quantity = 0;
          });
        }else{
          item.goods = null;
        }
        });

        that.setData({
          menu_list: categories,
          foodList: resFood,
        })
      } else {
        that.setData({
          list: []
        })

      }
    })
   
    // 轮播图
    api.get('/carousels/list',{}).then((res)=>{
      if(res.data.code == 0){
        that.setData({
          carouselList: res.data.data
        })
      }
    })
    
    // 购物车缓存
    
  },
  // 点击切换右侧数据
  changeRightMenu: function(e) {
    // 获取点击项的id
    var classify = e.target.dataset.id;
    var foodList = this.data.foodList;
    let foods = categories[classify].goods;
    this.setData({
      // 右侧菜单当前显示第curNav项
      curNav: classify,
      foodList: foods
    })

  },

  // 加入购物车
  addCart1: function(e) {

    console.log(this.data.foodList)
    var id = e.target.dataset.id;
    var carList = this.data.carList;
    var totalPrice = this.data.totalPrice;
    
    // 判断购物车是否有该商品
    for(var i in carList){
      if(carList[i].goodsId == id){
        carList[i].quantity += 1;
        totalPrice += carList[i].goodsPrice;
        this.setData({
          totalPrice: parseFloat(totalPrice.toFixed(2)),
          carList: carList
        })
        return;
      }
    }
    var foodList = this.data.foodList;
    // 购物车没有该商品 查找该商品
    for(var i in foodList){
      if(foodList[i].goodsId == id){
        var goods = foodList[i];
        goods.quantity += 1;
        console.log(goods)
        totalPrice += goods.goodsPrice;
        carList.push(goods);
        this.setData({
          totalPrice: parseFloat(totalPrice.toFixed(2)),
          carList: carList
        })
        console.log(this.data.foodList)
        return;
      }
    }
    
  },

  // 计算总价格
  getTotalPrice: function() {

      // 重置总价格
      this.data.totalPrice = 0;

      var totalPrice = this.data.totalPrice
      var carList = this.data.carList
      for(var i in carList){
        totalPrice += carList[i].goodsPrice * carList[i].quantity;
      }
      this.setData({
        totalPrice: parseFloat(totalPrice.toFixed(2)),
      })
  },

  // 显示购物车
  showCartPop: function() {
     var showCartPop = this.data.showCartPop;
     showCartPop = true;

     this.setData({
       showCartPop: showCartPop
     })

  },

  // 隐藏购物车
  hideCartPop: function () {
     var showCartPop = this.data.showCartPop;
     showCartPop = false;

     this.setData({
      showCartPop: showCartPop
    })
  },

  // 购物车商品数量增加和减少
  cartStepChange: function(e) {

    var quantity = e.detail;
    var id = e.target.dataset.id;
    var carList = this.data.carList;

    // 判断 为数量为0
    if(quantity == 0){
      carList.pop(id);
      this.setData({
        carList:carList
      })
      this.getTotalPrice();
      if(this.data.carList.length == 0){
        this.setData({
          showCartPop: false
        })
      }
      return;
    }
    // 遍历查找 修改商品数量
    for(var i in carList){
      if(carList[i].id == id){
        carList[i].quantity = quantity;
        this.setData({
          carList:carList
        })
      }
    }
    this.getTotalPrice();
   
  },

  // 清空购物车
  clearCart: function() {

    for (var i in this.data.foodList) {
      this.data.foodList[i].quantity = 0;
    }
    this.setData({
      foodList: this.data.foodList,
      carList: [],
      totalPrice: 0,
      showCartPop: false
    })
    console.log(this.data.carList)
  },
  
  // 跳转支付
  goPay: function(){
    wx.setStorageSync('carList', this.data.carList);
    wx.navigateTo({
      url: '/pages/pay/index',
    })
  },

  //评论
  toComments:function () {

    wx.navigateTo({
      url: '/pages/comment_index/index',
    })

  },
  
  onPullDownRefresh:function() {
    this.onLoad();
  },

},
)


