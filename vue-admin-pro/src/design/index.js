import '@/assets/svg-icons' // svg 图标
import log from './libs/function/log' // 控制台打印优化
import cookies from './libs/function/cookies' // Cookies
import open from './libs/function/open' // 打开新页面
import http from '@/design/libs/request/request' // 请求
import route from '@/design/libs/function/route' // 路由跳转
import trim from './libs/function/trim' // 去除空格
import test from './libs/function/test' // 规则检验
import random from './libs/function/random' // 随机数
import uuid from './libs/function/uuid' // 生成全局唯一uuid字符串
import timeFormat from './libs/function/timeFormat' // 时间格式化
import timeFrom from './libs/function/timeFrom' // 时间戳格式化,返回多久之前
import queryParams from './libs/function/queryParams' // 对象转url参数

import toast from './libs/function/toast' // 消息提示
import msg from './libs/function/msg' // 通知提醒消息

import clipboard from './libs/function/clipboard' // 剪贴板
import treeIds from './libs/function/treeIds' // 递归获取所有的ID值
import tree from './libs/function/tree' // 递归判断列表移除children空数组
import uppercaseFirst from './libs/function/uppercaseFirst' // 字符串首字母大写
import numberFormatter from './libs/function/numberFormatter' // 数字格式
import toThousandFilter from './libs/function/toThousandFilter' // 数字千位过滤
import deepClone from './libs/function/deepClone' // 对象深度克隆
import deepMerge from './libs/function/deepMerge' // 对象深度合并
import toLine from './libs/function/toLine' // 驼峰式命名转换横线
import amount from './libs/function/amount' // 金额工具类
import trees from './libs/function/trees' // 递归判断列表移除children空数组

import config from './libs/config/config' // 配置信息

import V from '../../package.json'
// 注册全局事件
const $u = {
  log,
  cookies,
  open,
  http,
  route,
  get: http.get,
  post: http.post,
  config,
  trim,
  test,
  random,
  uuid,
  timeFormat,
  timeFrom,
  queryParams,
  toast,
  msg,
  clipboard,
  treeIds,
  tree,
  amount,
  uppercaseFirst,
  numberFormatter,
  toThousandFilter,
  deepClone,
  deepMerge,
  toLine,
  trees
}

// 其组件的相对路径, 是否查询子目录, 匹配基础组件文件名的正则表达式
const requireComponent = require.context('./components', true, /\w.\.vue$/)
const install = Vue => {
  // 时间格式化，同时两个名称，date和timeFormat
  Vue.filter('timeFormat', (timestamp, format) => {
    return timeFormat(timestamp, format)
  })
  Vue.filter('date', (time, format) => {
    return timeFormat(time, format)
  })
  Vue.filter('amount', (number) => {
    return amount.format(number)
  })
  // 挂载$u对象
  Vue.prototype.$u = $u

  $u.log.success('design version:' + V.version)
  // 注册全局组件
  requireComponent.keys().forEach(fileName => {
    const config = requireComponent(fileName)
    const componentName = 'u' + $u.toLine(config.default.name)
    if (process.env.NODE_ENV === 'development') {
      $u.log.capsule('Component', componentName, 'warning')
    }
    Vue.component(componentName, config.default || config)
  })
}

export default {
  install
}
