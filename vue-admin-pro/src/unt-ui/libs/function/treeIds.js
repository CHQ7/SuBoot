/**
 * 递归获取所有的ID值
 * @param ids
 * @param data
 */
const treeIds = function(ids, data) {
  data.forEach(function(obj) {
    ids.push(obj.id)
    if (obj.children && obj.children.length > 0) {
      treeIds(ids, obj.children)
    }
  })
}

export default treeIds
