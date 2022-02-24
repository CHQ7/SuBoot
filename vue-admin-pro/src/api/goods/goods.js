import http from '@/unt-ui/libs/request/request'

const BASE_URL = '/goods'

export const Goods = {
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
  // 移除单个
  remove: (id) => http.post(BASE_URL + '/remove/' + id),
  // 恢复单个
  restore: (id) => http.post(BASE_URL + '/restore/' + id),
  // 获取分类数据
  category: data => http.post(BASE_URL + '/category', data),
  // 获取商户列表
  merch: data => http.post(BASE_URL + '/merch', data),
  // 批量上架商品
  up: data => http.post(BASE_URL + '/up', data),
  // 批量下架商品
  down: data => http.post(BASE_URL + '/down', data),
  // 批量更改录入时间
  time: data => http.post(BASE_URL + '/time', data),
  // 批量分类商品
  cate: data => http.post(BASE_URL + '/cate', data),
  // 批量更改商户商品
  batchMerch: data => http.post(BASE_URL + '/batchMerch', data)
}
