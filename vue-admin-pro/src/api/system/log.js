import http from '@/unt-ui/libs/request/request'

const BASE_URL = '/sys/log'

export const SystemLog = {
  // 获取列表数据
  list: data => http.post(BASE_URL + '/list', data)
}
