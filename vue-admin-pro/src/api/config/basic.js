import http from '@/design/libs/request/request'

const BASE_URL = '/config/basic'

export default {
  // 获取数据
  fetch: () => http.post(BASE_URL + '/fetch'),
  // 更新数据
  update: data => http.post(BASE_URL + '/update', data),
  // 短信模板库
  template: data => http.post(BASE_URL + '/template', data)
}
