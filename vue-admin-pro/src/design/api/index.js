// --------------------------------------
// 自动扫描API
// --------------------------------------
import $u from '../libs'

// 开发模式打印日志
if (process.env.NODE_ENV === 'development') {
  $u.log.warning('-------------------------------')
  $u.log.warning('>>> 自动扫描API')
  $u.log.warning('-------------------------------')
}

// 获取路径转换成驼峰式命名方式,如果目录与文件夹名称相同，则保留目录名称
function requireAll(arr) {
  // console.log(arr.length)
  let name = ''
  arr.map(v => {
    // 转换成驼峰式命名方式
    const fileName = v.toLowerCase().replace(/( |^)[a-z]/g, (L) => L.toUpperCase())
    // 如果目录与文件夹名称相同，则保留目录名称
    if (arr.length === 2) {
      if (name !== fileName) {
        name += fileName
      }
    }
  })
  return name
}

const modules = {}

// 自动化扫描API文件
try {
  const files = require.context('@/api', true, /\.js/)

  files.keys().forEach(key => {
    // 获取文件路径
    const path = key.replace(/\.\/|\.js/g, '')
    modules[requireAll(path.split('/'))] = files(key).default
  })
} catch (error) {
  console.log()
}

console.log(modules)

const install = (Vue, vm) => {
  vm.$u.api = modules
}
export default {
  install
}
