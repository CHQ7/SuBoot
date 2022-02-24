import http from '@/unt-ui/libs/request/request'

const BASE_URL = '/config/sms'

export const ConfigSms = {
  // 获取数据
  fetch: () => http.post(BASE_URL + '/fetch'),
  // 更新数据
  update: data => http.post(BASE_URL + '/update', data)
}
