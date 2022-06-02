/**
* 左边菜单
*/
<template>
  <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b">
    <div class="logobox">
      <img class="logoimg" src="../assets/img/logo.png" alt="">
    </div>
    <el-submenu v-for="menu in allmenu" :key="menu.menuid" :index="menu.menuname">
      <template slot="title">
        <i class="iconfont" :class="menu.icon"></i>
        <span>{{menu.menuname}}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="chmenu in menu.menus" :index="'/'+chmenu.url" :key="chmenu.menuid">
          <i class="iconfont" :class="chmenu.icon"></i>
          <span>{{chmenu.menuname}}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>

export default {
  name: 'leftnav',
  data() {
    return {
      collapsed: false,
      allmenu: []
    }
  },
  // 创建完毕状态(里面是操作)
  created() {
    // 获取图形验证码
    let res = {
      success: true,
      data: [
        {
          menuid: 1,
          menuname: '菜品管理',
          url: null,
          hasThird: null,
          menus: [
            {
              menuid: 34,
              menuname: '全部菜品',
              hasThird: 'N',
              url: 'goods',
              menus: null
            },
          ]
        },
        // {
        //   menuid: 33,
        //   menuname: '菜品类别',
        //   hasThird: null,
        //   url: null,
        //   menus: [
        //     {
        //       menuid: 34,
        //       menuname: '全部类别',
        //       hasThird: 'N',
        //       url: 'goodscategory',
        //       menus: null
        //     }
        //   ]
        // },
        {
          menuid: 33,
          menuname: '订单管理',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 34,
              menuname: '全部订单',
              hasThird: 'N',
              url: 'ordermaster',
              menus: null
            }
          ]
        },
        // {
        //   menuid: 33,
        //   menuname: '评论管理',
        //   hasThird: null,
        //   url: null,
        //   menus: [
        //     {
        //       menuid: 34,
        //       menuname: '全部评论',
        //       hasThird: 'N',
        //       url: 'comment',
        //       menus: null
        //     }
        //   ]
        // },{
        //   menuid: 33,
        //   menuname: '座位管理',
        //   hasThird: null,
        //   url: null,
        //   menus: [
        //     {
        //       menuid: 34,
        //       menuname: '全部座位',
        //       hasThird: 'N',
        //       url: 'table',
        //       menus: null
        //     }
        //   ]
        // },{
        //   menuid: 33,
        //   menuname: '轮播图',
        //   hasThird: null,
        //   url: null,
        //   menus: [
        //     {
        //       menuid: 34,
        //       menuname: '轮播图',
        //       hasThird: 'N',
        //       url: 'carousel',
        //       menus: null
        //     }
        //   ]
        // }


      ],
      msg: 'success'
    }
    this.allmenu = res.data

    // menu(localStorage.getItem('logintoken'))
    //   .then(res => {
    //     console.log(JSON.stringify(res))
    //     if (res.success) {
    //       this.allmenu = res.data
    //     } else {
    //       this.$message.error(res.msg)
    //       return false
    //     }
    //   })
    //   .catch(err => {
    //     this.$message.error('菜单加载失败，请稍后再试！')
    //   })
    // 监听
    this.$root.Bus.$on('toggle', value => {
      this.collapsed = !value
    })
  }
}
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
  text-align: left;
}
.el-menu-item-group__title {
  padding: 0px;
}
.el-menu-bg {
  background-color: #1f2d3d !important;
}
.el-menu {
  border: none;
}
.logobox {
  height: 40px;
  line-height: 40px;
  color: #9d9d9d;
  font-size: 20px;
  text-align: center;
  padding: 20px 0px;
}
.logoimg {
  height: 40px;
}
</style>
