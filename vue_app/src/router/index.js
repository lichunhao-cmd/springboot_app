import Vue from 'vue'
import Router from 'vue-router'

import Index from '../Index'
import goodsinfo from '../view/goods/goodsinfo'
import ordermaster from '../view/orders/ordermaster'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: Index,
      children:[
        {
          path: '/goods',
          component:goodsinfo
        },{
          path:'/ordermaster',
          component:ordermaster
        }
      ]
    }
  ]
})
