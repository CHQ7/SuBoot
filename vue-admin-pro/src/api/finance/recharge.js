import http from '@/design/libs/request/request'

const BASE_URL = '/finance/recharge'

export default {
  // 获取列表数据
  list: data => http.post(BASE_URL + '/list', data),
  // 获取数据
  fetch: (id) => http.post(BASE_URL + '/fetch/' + id),
  // 更新数据
  update: data => http.post(BASE_URL + '/update', data)
}
