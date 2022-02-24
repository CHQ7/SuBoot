/**
 * @description 打开新页面
 * @param {String} url 地址
 */
const open = function(url) {
  const a = document.createElement('a')
  a.setAttribute('href', url)
  a.setAttribute('target', '_blank')
  a.setAttribute('id', 'link-temp')
  document.body.appendChild(a)
  a.click()
  document.body.removeChild(document.getElementById('link-temp'))
}
export default open
