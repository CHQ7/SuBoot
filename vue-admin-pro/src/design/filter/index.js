// --------------------------------------
// 过滤器
// --------------------------------------
import $u from '../libs'

const install = Vue => {
  // 时间格式化，同时两个名称，date和timeFormat
  Vue.filter('timeFormat', (timestamp, format) => {
    return $u.timeFormat(timestamp, format)
  })
  Vue.filter('date', (time, format) => {
    return $u.timeFormat(time, format)
  })
  Vue.filter('amount', (number) => {
    return $u.amount.format(number)
  })
}

export default {
  install
}
