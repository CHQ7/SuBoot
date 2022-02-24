import http from '@/unt-ui/libs/request/request'

const BASE_URL = '/sys/file'

export const SystemFile = {
  // 获取列表数据
  list: data => http.post(BASE_URL + '/list', data),
  // 文件下载
  download: (id) => http.download(BASE_URL + '/download/' + id),
  // 删除单个
  delete: (id) => http.post(BASE_URL + '/delete/' + id),
  // 删除集合
  del: data => http.post(BASE_URL + '/delete', data)
}
