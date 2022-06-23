import { Notification } from 'element-ui'
/**
 * 通知提醒消息
 * 封装element Notification
 * @param title
 * @param type
 * @param duration
 * @param showClose
 */
function msg(msg, type = 'success', title = '提示', duration = 1500, position = 'top-right', showClose = true) {
  Notification({
    message: msg,
    title: title,
    type: type,
    duration: duration,
    position: position,
    showClose: showClose
  })
}

export default msg
