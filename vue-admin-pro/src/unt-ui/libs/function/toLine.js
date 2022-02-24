/**
 * 驼峰式命名转换横线
 * @param name
 * @returns {string}
 */
const toLine = function(name) {
  return name.replace(/([A-Z])/g, '-$1').toLowerCase()
}

export default toLine
