// --------------------------------------
// 函数库
// --------------------------------------
import log from './function/log' // 控制台打印优化
import cookies from './function/cookies' // Cookies
import http from './request/request' // 请求
import test from './function/test' // 规则检验

import open from './function/open' // 打开新页面
import route from './function/route' // 路由跳转
import trim from './function/trim' // 去除空格
import random from './function/random' // 随机数
import uuid from './function/uuid' // 生成全局唯一uuid字符串
import timeFormat from './function/timeFormat' // 时间格式化
import timeFrom from './function/timeFrom' // 时间戳格式化,返回多久之前
import queryParams from './function/queryParams' // 对象转url参数

import toast from './function/toast' // 消息提示
import msg from './function/msg' // 通知提醒消息

import clipboard from './function/clipboard' // 剪贴板
import treeIds from './function/treeIds' // 递归获取所有的ID值
import tree from './function/tree' // 递归判断列表移除children空数组
import uppercaseFirst from './function/uppercaseFirst' // 字符串首字母大写
import numberFormatter from './function/numberFormatter' // 数字格式
import toThousandFilter from './function/toThousandFilter' // 数字千位过滤
import deepClone from './function/deepClone' // 对象深度克隆
import deepMerge from './function/deepMerge' // 对象深度合并
import toLine from './function/toLine' // 驼峰式命名转换横线
import amount from './function/amount' // 金额工具类
import trees from './function/trees' // 递归判断列表移除children空数组

import config from './config/config' // 配置信息

export default {
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
