import Clipboard from 'clipboard'
import toast from './toast'

/**
 * 剪贴板
 * @param text
 * @param event
 * $u.clipboard('复制内容', $event)
 */
const clipboard = function(text, event) {
  const clipboard = new Clipboard(event.target, {
    text: () => text
  })
  clipboard.on('success', () => {
    toast('复制成功')
    clipboard.destroy()
  })
  clipboard.on('error', () => {
    toast('复制失败', 'error')
    clipboard.destroy()
  })
  clipboard.onClick(event)
}
export default clipboard
