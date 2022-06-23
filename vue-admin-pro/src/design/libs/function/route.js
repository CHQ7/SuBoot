import router from '@/router'
/**
 * 路由跳转
 * 注意:本方法没有对跳转的回调函数进行封装
 */
const route = function(options = {}, query = {}) {
  if (typeof options === 'string') {
    return router.push({ path: options, query: query }).catch(() => {})
  }
}

route.replace = function(options = {}, query = {}) {
  if (typeof options === 'string') {
    return router.replace({ path: options, query: query }).catch(() => {})
  }
}

export default route
