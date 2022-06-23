import deepClone from './deepClone'

/**
 * 对象深度合并
 * @param target 目标对象
 * @param source 源对象
 * @returns {boolean|*[]}
 */
const deepMerge = function(target = {}, source = {}) {
  target = deepClone(target)
  if (typeof target !== 'object' || typeof source !== 'object') return false
  for (var prop in source) {
    if (!source.hasOwnProperty(prop)) continue
    if (prop in target) {
      if (typeof target[prop] !== 'object') {
        target[prop] = source[prop]
      } else {
        if (typeof source[prop] !== 'object') {
          target[prop] = source[prop]
        } else {
          if (target[prop].concat && source[prop].concat) {
            target[prop] = target[prop].concat(source[prop])
          } else {
            target[prop] = deepMerge(target[prop], source[prop])
          }
        }
      }
    } else {
      target[prop] = source[prop]
    }
  }
  return target
}

export default deepMerge
