import axios from 'axios'
import qs from 'qs'
import deepMerge from '../function/deepMerge'
import validate from '../function/test'

class Request {
  /**
   * 设置全局默认配置
   * @param customConfig
   */
  setConfig(customConfig) {
    // 深度合并对象，否则会造成对象深层属性丢失
    this.config = deepMerge(this.config, customConfig)
  }

  /**
   * 主要请求部分
   * @param options
   * @returns {Promise<unknown>}
   */
  request(options = {}) {
    /**
     * 检查请求拦截
     * 如果存在请求拦截
     * 返回一个处于pending状态中的Promise，来取消原promise，避免进入then()回调
     */
    if (this.interceptors.request && typeof this.interceptors.request === 'function') {
      const interceptorRequest = this.interceptors.request(options)
      if (interceptorRequest === false) {
        return new Promise(() => {})
      }

      this.options = interceptorRequest
    }

    /**
     * 检查请求前参数
     * @type {*|string}
     */
    options.url = options.url || ''
    options.headers = deepMerge(this.config.headers, options.headers)
    options.method = options.method || this.config.method
    // 判断用户传递的URL是否/开头,如果不是,加上/，这里使用了uView的test.js验证库的url()方法
    options.url = validate.url(options.url) ? options.url : (this.config.baseURL + (options.url.indexOf('/') === 0 ? options.url : '/' + options.url))

    // 判断数据请求是否是表单请求方式
    if (this.config.dataType === 'form') {
      options.data = qs.stringify(options.data)
    }

    /**
     * 发起请求
     * 判断是否存在拦截器
     * 存在拦截器:
     * 如果拦截器不返回 false，就将拦截器返回的内容给this.$u.post的then回调
     * 如果拦截器返回 false，意味着拦截器定义者认为返回有问题，直接接入catch回调
     * 不存在拦截器:
     * 返回最原始的数据
     * 异常:
     * 处理请求异常问题,判断是否存在拦截器,如果不存在拦截器设置直接接入catch回调
     * 如果拦截器存在不返回false，就将拦截器返回的内容给then回调
     * 如果拦截器存在返回false，意味着拦截器定义者认为返回有问题，直接接入catch回调
     */
    return new Promise((resolve, reject) => {
      axios(options).then(response => {
        if (this.interceptors.response && typeof this.interceptors.response === 'function') {
          const resInterceptors = this.interceptors.response(response)
          if (resInterceptors !== false) {
            resolve(resInterceptors)
          } else {
            reject(response.data)
          }
        } else {
          resolve(response)
        }
      }).catch(err => {
        if (this.interceptors.response && typeof this.interceptors.response === 'function') {
          const resInterceptors = this.interceptors.response(err)
          if (resInterceptors !== false) {
            resolve(resInterceptors)
          } else {
            reject(err)
          }
        } else {
          reject(err)
        }
      })
    })
  }

  /**
   * 创建请求对象
   */
  constructor() {
    /**
     * 请求默认参数
     * @type {{headers: {}, baseURL: string, method: string, withCredentials: boolean, dataType: string, timeout: string}}
     */
    this.config = {
      baseURL: '', // 请求的根域名
      headers: {
        'content-type': 'application/json;charset=UTF-8'
      }, // 默认的请求头
      method: 'POST',
      timeout: '6000', // 请求超时时间（毫秒）
      dataType: 'json',
      withCredentials: false // 是否携带cookie信息
    }

    /**
     * 拦截器
     */
    this.interceptors = {
      // 请求前的拦截
      request: null,
      // 请求后的拦截
      response: null
    }

    /**
     * get请求
     * @param url
     * @param data
     * @param headers
     * @returns {Promise<unknown>}
     */
    this.get = (url, data = {}, headers = {}) => {
      return this.request({
        method: 'GET',
        url,
        headers,
        data
      })
    }

    /**
     * post请求
     * @param url
     * @param data
     * @param headers
     * @returns {Promise<unknown>}
     */
    this.post = (url, data = {}, headers = {}) => {
      return this.request({
        url,
        method: 'POST',
        headers,
        data
      })
    }

    /**
     * put请求
     * @param url
     * @param data
     * @param headers
     * @returns {Promise<unknown>}
     */
    this.put = (url, data = {}, headers = {}) => {
      return this.request({
        url,
        method: 'PUT',
        headers,
        data
      })
    }

    /**
     * delete请求
     * @param url
     * @param data
     * @param headers
     * @returns {Promise<unknown>}
     */
    this.delete = (url, data = {}, headers = {}) => {
      return this.request({
        url,
        method: 'DELETE',
        headers,
        data
      })
    }

    /**
     * download 下载
     * @param url
     * @param data
     * @param headers
     * @returns {Promise<unknown>}
     */
    this.download = (url, data = {}, headers = {}) => {
      let options = {
        url,
        method: 'GET',
        headers,
        data
      }
      /**
       * 检查请求拦截
       * 如果存在请求拦截
       * 返回一个处于pending状态中的Promise，来取消原promise，避免进入then()回调
       */
      if (this.interceptors.request && typeof this.interceptors.request === 'function') {
        const interceptorRequest = this.interceptors.request(options)
        if (interceptorRequest === false) {
          return new Promise(() => {})
        }
        options = interceptorRequest
      }

      /**
       * 检查请求前参数
       * @type {*|string}
       */
      options.url = url || ''
      options.headers = deepMerge(this.config.headers, options.headers)
      options.method = options.method || this.config.method
      options.url = validate.url(options.url) ? options.url : (this.config.baseURL + (options.url.indexOf('/') === 0 ? options.url : '/' + options.url))
      window.open(options.url)
    }
  }
}
export default new Request()
