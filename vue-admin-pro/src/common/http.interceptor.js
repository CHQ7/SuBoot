// 这里的vm，就是我们在vue文件里面的this，所以我们能在这里获取vuex的变量，比如存放在里面的token
// 同时，我们也可以在此使用getApp().globalData，如果你把token放在getApp().globalData的话，也是可以使用的

import { MessageBox } from 'element-ui'
import store from '@/store'

const install = (Vue, vm) => {
  Vue.prototype.$u.http.setConfig({
    // 请求的根域名
    baseURL: vm.domain + '/nt',
    headers: {
      'content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    dataType: 'form'
  })
  // 请求拦截，配置Token等参数
  Vue.prototype.$u.http.interceptors.request = (config) => {
    config.headers['x-token2'] = '2222222'
    return config
  }
  // 响应拦截，判断状态码是否通过
  Vue.prototype.$u.http.interceptors.response = (response) => {
    if (response.status === 200) {
      const res = response.data
      // 如果标识不是20000，则判断为错误.
      if (res.code !== 20000) {
        // 标识是50002,50003，则判断为验证码错误.
        if (res.code === 50002 || res.code === 50003) {
          return false
        }
        // 标识是50009,用户验证失败.
        if (res.code === 50009) {
          vm.$u.toast(res.msg || 'Error', 'error')
          return res
        }

        // 50008:非法令牌; 50014:令牌已过期;
        if (res.code === 50008 || res.code === 50014) {
          // to re-login
          MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
            confirmButtonText: '重新登录',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            store.dispatch('user/resetToken').then(() => {
              location.reload()
            })
          })
        } else {
          // 错误提示信息 50010:无权限;50001:错误
          vm.$u.toast(res.msg || 'Error', 'error')
        }
        return false
      } else {
        return res
      }
    } else {
      // 服务器状态码不为200,则提示
      if (response) {
        switch (response.response.status) {
          case 400:
            response.message = '错误请求'
            break
          case 401:
            response.message = '未授权,请重新登录'
            break
          case 403:
            response.message = '拒绝访问'
            break
          case 404:
            response.message = '请求错误,未找到该资源'
            break
          case 405:
            response.message = '请求方法未允许'
            break
          case 408:
            response.message = '请求超时'
            break
          case 500:
            response.message = '服务器异常'
            break
          case 502:
            response.message = '网络错误'
            break
          case 504:
            response.message = '网络超时'
            break
          case 505:
            response.message = 'HTTP版本不受支持'
            break
          default:
            response.message = '错误代码:' + response.response.status
        }
      } else {
        response.message = '网络出现问题,请稍后再试'
      }
      // 提示信息
      vm.$u.toast(response.message || 'Error', 'error')
      return false
    }
  }
}

export default {
  install
}
