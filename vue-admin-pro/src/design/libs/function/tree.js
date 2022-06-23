/**
 * 递归判断列表移除children空数组
 * fix:children[] 出现空面板的问题
 * @param data
 * @returns {*}
 */
const tree = function(data) {
  for (let i = 0; i < data.length; i++) {
    if (data[i].children && data[i].children.length === 0) {
      delete data[i].children
    } else {
      tree(data[i].children)
    }
  }
  return data
}

export default tree
