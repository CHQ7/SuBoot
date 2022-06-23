/**
 * 金额工具类
 */
const amount = {}

/**
 * 分转元
 * @param number
 * @returns {number}
 * @constructor
 */
amount.F2Y = function(number) {
  let num = !isFinite(+number) ? 0 : number
  if ((typeof number === 'string') && number.constructor === String) {
    // 分到元
    num = parseInt(number) / 100
  } else {
    num = number / 100
  }
  return num
}

/**
 * 元转分
 * @param number
 * @returns {number}
 * @constructor
 */
amount.Y2F = function(number) {
  const yuan = !isFinite(+number) ? 0 : number
  const digit = 100
  let m = 0
  const s1 = yuan.toString()
  const s2 = digit.toString()
  // eslint-disable-next-line no-empty
  try { m += s1.split('.')[1].length } catch (e) { }
  // eslint-disable-next-line no-empty
  try { m += s2.split('.')[1].length } catch (e) { }
  return Number(s1.replace('.', '')) * Number(s2.replace('.', '')) / Math.pow(10, m)
}

/**
 * 格式化人民币
 * @param number
 * @returns {number}
 */
amount.format = function(number, decimals, dec_point, thousands_sep) {
  number = (number + '').replace(/[^0-9+-Ee.]/g, '')
  const n = !isFinite(+number) ? 0 : number / 100
  const prec = !isFinite(decimals) ? 2 : Math.abs(decimals)
  const sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep
  const dec = (typeof dec_point === 'undefined') ? '.' : dec_point
  let s = ''
  const toFixedFix = function(n, prec) {
    const k = Math.pow(10, prec)
    return '' + Math.ceil(n * k) / k
  }

  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.')
  const re = /(-?\d+)(\d{3})/
  while (re.test(s[0])) {
    s[0] = s[0].replace(re, '$1' + sep + '$2')
  }

  if ((s[1] || '').length < prec) {
    s[1] = s[1] || ''
    s[1] += new Array(prec - s[1].length + 1).join('0')
  }
  return s.join(dec)
}

export default amount
