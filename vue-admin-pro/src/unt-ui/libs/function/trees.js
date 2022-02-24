/**
 * 递归判断列表移除children空数组
 * fix:children[] 出现空面板的问题
 * @param data
 * @returns {*}
 */
const trees = function(data, config) {
  console.log('>>' + JSON.stringify(data))
  var id = config.id || 'id'
  var pid = config.pid || 'pid'
  var children = config.children || 'children'
  // var label=config.label || 'LctnName';
  var idMap = {}
  var jsonTree = []
  data.forEach(function(v) {
    idMap[v[id]] = v
  })
  data.forEach(function(v) {
    var parent = idMap[v[pid]]
    if (parent) {
      !parent[children] && (parent[children] = [])
      parent[children].push(v)
    } else {
      jsonTree.push(v)
    }
  })
  return jsonTree
}

export default trees
