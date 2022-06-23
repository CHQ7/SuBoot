import http from '@/design/libs/request/request'

const BASE_URL = '/config/email'

export default {
  // 获取数据
  fetch: () => http.post(BASE_URL + '/fetch'),
  // 更新数据
  update: data => http.post(BASE_URL + '/update', data),
  // 发送测试
  test: data => http.post(BASE_URL + '/test', data)
}
