import http from '@/unt-ui/libs/request/request'

const BASE_URL = '/config/basic'

export const ConfigBasic = {
  // 获取数据
  fetch: () => http.post(BASE_URL + '/fetch'),
  // 更新数据
  update: data => http.post(BASE_URL + '/update', data),
  // 短信模板库
  template: data => http.post(BASE_URL + '/template', data)
}
