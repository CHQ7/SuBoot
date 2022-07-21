// --------------------------------------
// SVG ICON 图标库
// --------------------------------------

// 自动化扫描SVG图标
const icons = require.context('./icons', false, /\.svg$/)
// 获取SVG相关信息
const requireAll = context => context.keys().map(context)
// 获取SVG图标集合数据
const iconMap = requireAll(icons)

const install = (Vue) => {
  // 获取SVG图标名字
  Vue.prototype.$svg = iconMap.map(e => e.default.id.slice(5))
}

export default {
  install
}
