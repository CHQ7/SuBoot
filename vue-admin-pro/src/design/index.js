import svg from './svg' // SVG ICON 图标库
import component from './components' // 组件库
import filter from './filter' // 过滤器
import $u from './libs' // 函数库

const install = Vue => {
  // 开发模式打印日志
  if (process.env.NODE_ENV === 'development') {
    $u.log.warning('-------------------------------')
    $u.log.warning('>>> 自动扫描组件')
    $u.log.warning('-------------------------------')
  }

  // 组件库
  component.install(Vue)
  // 过滤器
  filter.install(Vue)
  // SVG ICON 图标库
  svg.install(Vue)

  // 挂载$u对象
  Vue.prototype.$u = $u

  // 打印日志
  $u.log.success('-------------------------------')
  $u.log.success('>>> design version: v' + $u.config.v)
  $u.log.success('-------------------------------')
}

export default {
  install
}
