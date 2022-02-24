import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

import { error } from './module/error'

/**
 * 注意：子菜单仅在route children.length>=1时出现
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   如果设置为true，则项目将不会显示在侧边栏中（默认为false）
 * alwaysShow: true               如果设置为true，将始终显示根菜单
 *                                如果不始终设置Show，则当项具有多个子路由时，
 *                                它将成为嵌套模式，否则不显示根菜单
 * redirect: noRedirect           如果设置为no redirect，则breadcrumb中不会重定向
 * name:'router-name'             该名称由<keep alive>使用（必须设置！!!)
 * meta : {
    roles: ['admin','editor']    控制页面角色（可以设置多个角色）
    title: 'title'               侧边栏和面包屑中显示的名称（推荐集）
    icon: 'svg-name'             图标显示在侧栏中
    noCache: true                如果设置为true，则不会缓存该页（默认为false）
    affix: true                  如果设置为true，则标记将粘贴在“标记”视图中
    breadcrumb: false            如果设置为false，则该项将隐藏在breadcrumb中（默认为true）
    activeMenu: '/example/list'  如果设置路径，侧边栏将突出显示您设置的路径
  }
 */

/**
 * constantRoutes
 * 没有权限要求的基页
 * 可以访问所有角色
 */
export const constantRoutes = [
  {
    path: '/login',
    hidden: true,
    component: () => import('@/views/login/index')
  },
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  ...error
]

/**
 * asyncRoutes 需要根据用户角色动态加载的路由
 */
export const asyncRoutes = [

  {
    path: '/',
    component: Layout,
    redirect: '/index',
    children: [
      {
        path: '/index',
        name: 'index',
        meta: { title: '首页', icon: 'dashboard', affix: true },
        component: () => import('@/views/index/index')
      }
    ]
  },

  // 404页必须放在末尾 !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history' 'hash'
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
