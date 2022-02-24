/**
 * 数字千位过滤
 * 10000 => "10,000"
 * @param {number} num
 */
const toThousandFilter = function(num) {
  return (+num || 0).toString().replace(/^-?\d+/g, m => m.replace(/(?=(?!\b)(\d{3})+$)/g, ','))
}
export default toThousandFilter
