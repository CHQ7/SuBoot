// --------------------------------------
// 组件库
// --------------------------------------
import $u from '../libs'

// 自动化扫描组件
const contexts = require.context('../components', true, /\w.\.vue$/)

const install = Vue => {
  // 注册全局组件
  contexts.keys().forEach(component => {
    // 获取组件的配置信息
    const config = contexts(component)
    // 将组件的命名方式更改为驼峰式命名转换横线
    const componentName = 'u' + $u.toLine(config.default.name)
    // 注册组件
    Vue.component(componentName, config.default || config)

    // 开发模式打印日志
    if (process.env.NODE_ENV === 'development') {
      $u.log.capsule('组件', componentName, 'warning')
    }
  })
}

export default {
  install
}
