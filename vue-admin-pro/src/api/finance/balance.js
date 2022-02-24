import http from '@/unt-ui/libs/request/request'

const BASE_URL = '/finance/balance'

export const FinanceBalance = {
  // 获取列表数据
  list: data => http.post(BASE_URL + '/list', data)
}
