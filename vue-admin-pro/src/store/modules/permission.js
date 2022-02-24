// import { asyncRoutes, constantRoutes } from '@/router'
import { constantRoutes } from '@/router'
/**
 * 使用元角色确定当前用户是否具有权限
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []
  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })
  return res
}

/**
 * 递归过滤异步路由表-后台控制路由
 * @param menu
 * @returns {[]}
 */
export const filterAllRoutes = menu => {
  const aRoutes = []
  menu.forEach(aMenu => {
    const { path, name, icon, router, children, keepAlive } = aMenu
    if (path) {
      const oRouter = {
        path: router,
        name: router,
        meta: {
          title: name,
          icon: icon,
          noCache: keepAlive
        },
        component: path,
        children: children && children.length ? filterAllRoutes(children) : []
      }

      if (path === 'Layout') {
        oRouter.path = '/' + router
        oRouter.alwaysShow = true
        oRouter.component = (resolve) => require(['@/layout/index'], resolve)
      } else {
        let component
        if (new RegExp('^/views/.*$').test(path)) {
          component = path.replace('/views/', '')
        } else if (new RegExp('^views/.*$').test(path)) {
          component = path.replace('views/', '')
        } else {
          component = path
        }
        oRouter.component = (resolve) => require([`@/views/${component}`], resolve)
      }
      if (oRouter.children && oRouter.children.length === 0) {
        delete oRouter.children
      }
      aRoutes.push(oRouter)
    }
  })
  return aRoutes
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}
const index = {
  path: '/',
  component: (resolve) => require(['@/layout/index'], resolve),
  redirect: '/index',
  children: [
    {
      path: '/index',
      name: 'index',
      meta: { title: '首页', icon: 'dashboard', affix: true },
      component: () => import('@/views/index/index')
    }
  ]
}

const actions = {
  generateRoutes({ commit }, roles) {
    return new Promise(resolve => {
      const menus = JSON.parse(localStorage.getItem('menus'))
      let accessedRoutes
      if (roles.includes('admin')) {
        accessedRoutes = filterAllRoutes(menus) || []
      } else {
        accessedRoutes = filterAllRoutes(menus)
      }
      accessedRoutes.unshift(index)
      // if (roles[0] === 'root') {
      //   accessedRoutes.push(batch)
      // }
      accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
