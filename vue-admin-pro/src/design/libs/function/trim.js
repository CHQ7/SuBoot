/**
 * 去除空格
 * @param str
 * @param pos
 * @returns {*}
 */
const trim = function(str, pos = 'all') {
  if (pos === 'both') {
    return str.replace(/^\s+|\s+$/g, '')
  } else if (pos === 'left') {
    return str.replace(/^\s*/, '')
  } else if (pos === 'right') {
    return str.replace(/(\s*$)/g, '')
  } else if (pos === 'all') {
    return str.replace(/\s+/g, '')
  } else {
    return str
  }
}

export default trim
