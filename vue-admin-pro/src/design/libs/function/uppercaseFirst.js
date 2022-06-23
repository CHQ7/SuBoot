/**
 * 字符串首字母大写
 * @param {String} string
 */
const uppercaseFirst = function(string) {
  return string.charAt(0).toUpperCase() + string.slice(1)
}
export default uppercaseFirst
