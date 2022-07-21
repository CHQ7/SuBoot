import http from '@/design/libs/request/request'

const BASE_URL = '/dingtalk/config'

export default {
  // 同步钉钉组织到本地
  synchroDingtalkDept: () => http.post(BASE_URL + '/synchroDingtalkDept'),
  // 同步钉钉角色到本地
  synchroDingtalkRole: () => http.post(BASE_URL + '/synchroDingtalkRole'),
  // 同步钉钉用户到本地
  synchroDingtalkUser: () => http.post(BASE_URL + '/synchroDingtalkUser'),
  // 同步本地钉钉组织数据
  synchroLocalDept: () => http.post(BASE_URL + '/synchroLocalDept'),
  // 同步本地钉钉用户数据
  synchroLocalSysUser: () => http.post(BASE_URL + '/synchroLocalSysUser')
}
