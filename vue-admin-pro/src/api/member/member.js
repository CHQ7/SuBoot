import http from '@/design/libs/request/request'

const BASE_URL = '/member'

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
  del: data => http.post(BASE_URL + '/delete', data),
  // 更新状态
  disable: data => http.post(BASE_URL + '/disable', data),
  // 检查健康
  health: data => http.post(BASE_URL + '/health', data),
  // 查询单个会员
  query: data => http.post(BASE_URL + '/query', data),
  // 等级
  data: data => http.post(BASE_URL + '/data', data),
  // 会员合并
  merge: data => http.post(BASE_URL + '/merge', data),
  // 会员解绑OPENID
  unbind: data => http.post(BASE_URL + '/unbind', data),
  // 积分列表
  creditList: data => http.post(BASE_URL + '/creditList', data),
  // 积分操作
  credit: data => http.post(BASE_URL + '/credit', data)
}
