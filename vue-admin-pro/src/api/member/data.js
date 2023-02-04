import http from '@/design/libs/request/request'

const BASE_URL = '/member/data'

export default {
  // 获取用户等级
  level: data => http.post(BASE_URL + '/level', data),
  // 获取用户标签
  label: data => http.post(BASE_URL + '/label', data)
}
