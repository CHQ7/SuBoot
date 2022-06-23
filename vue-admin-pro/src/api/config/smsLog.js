import http from '@/design/libs/request/request'

const BASE_URL = '/config/sms/log'

export default {
  // 获取列表数据
  list: data => http.post(BASE_URL + '/list', data),
  // 删除单个
  delete: (id) => http.post(BASE_URL + '/delete/' + id),
  // 删除集合
  del: data => http.post(BASE_URL + '/delete', data)
}
