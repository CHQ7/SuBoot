import { Message } from 'element-ui'
/**
 * 消息提示
 * 封装element message
 * @param title
 * @param type
 * @param duration
 * @param showClose
 */
function toast(title, type = 'success', duration = 1500, showClose = false) {
  Message({
    message: title,
    type: type,
    duration: duration,
    showClose: showClose
  })
}

export default toast
