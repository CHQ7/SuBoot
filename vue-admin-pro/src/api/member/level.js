import http from '@/design/libs/request/request'

const BASE_URL = '/member/level'

export default {
  // 获取列表数据
  list: data => http.post(BASE_URL + '/list', data),
  // 创建数据
  create: data => http.post(BASE_URL + '/create', data),
  // 获取数据
  fetch: (id) => http.post(BASE_URL + '/fetch/' + id),
  // 更新数据
  update: data => http.post(BASE_URL + '/update', data),
  // 删除单个
  delete: (id) => http.post(BASE_URL + '/delete/' + id),
  // 删除集合
  del: data => http.post(BASE_URL + '/delete', data)
}
