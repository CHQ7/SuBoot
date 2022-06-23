import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './styles/element-variables.scss'

import '@/styles/index.scss' // global css

import App from './App'
import store from '@/store'
import router from './router'

// 引入全局design ui
import design from './design'
Vue.use(design)

import './config/permission' // permission control
import './common/error' // error log

// 设置全局字体默认大小
Vue.use(Element, {
  size: Cookies.get('size') || 'small'
})

// 注册全局组件
import mixin from './mixin/mixin'
Vue.mixin(mixin)

// 引入域名配置
let domain = window.g.domain
if (process.env.NODE_ENV === 'development') {
  domain = ''
}

Vue.prototype.domain = domain

Vue.config.productionTip = false

const app = new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})

// http拦截器，将此部分放在new Vue()和app.$mount()之间，才能App.vue中正常使用
import httpInterceptor from '@/common/http.interceptor'
Vue.use(httpInterceptor, app)

// http接口API抽离，免于写url或者一些固定的参数
import api from '@/common/http.api'
Vue.use(api, app)
app.$mount()
