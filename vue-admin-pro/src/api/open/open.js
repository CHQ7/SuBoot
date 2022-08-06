import http from '@/design/libs/request/request'

const BASE_URL = '/auth'

export default {
  // 登录
  login: data => http.post(BASE_URL + '/login', data),
  // 退出
  logout: () => http.post(BASE_URL + '/logout'),
  // 获取数据
  get: (token) => http.post(BASE_URL + '/index/fetch', { token }),
  // 获取数据
  info: (token) => http.post(BASE_URL + '/info', { token }),
  // 更新密码
  updatePassword: data => http.post(BASE_URL + '/updatePassword', data),
  // 验证码
  captcha: BASE_URL + '/captcha'
}

