import open from '@/api/open/open'
import { getToken, setToken, removeToken } from '@/common/auth'
import router, { resetRouter } from '@/router'
import { Notification } from 'element-ui'
import defaultSettings from '@/config/settings'

const state = {
  token: getToken(),
  nickname: '',
  avatar: '',
  introduction: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, nickname) => {
    state.nickname = nickname
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  }
}

const actions = {
  // 用户登录
  login({ commit }, userInfo) {
    const { username, password, captcha } = userInfo
    return new Promise((resolve, reject) => {
      open.login({ username: username.trim(), password: password, captcha: captcha }).then(response => {
        const { data } = response

        commit('SET_TOKEN', data.token)
        setToken(data.token)

        const time = new Date()
        const hour = time.getHours()
        const thisTime =
          hour < 8
            ? '早上好'
            : hour <= 11
              ? '上午好'
              : hour <= 13
                ? '中午好'
                : hour < 18
                  ? '下午好'
                  : '晚上好'
        Notification({
          title: thisTime + '!',
          message: '欢迎登录' + defaultSettings.title,
          type: 'success',
          duration: 2000
        })
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户信息
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      // fix: vue mounted 还没有挂在问题设置延迟执行《保证挂载后执行
      setTimeout(() => {
        open.info(state.token).then(response => {
          const { data } = response
          // data.roles = ['ROLE_SYSADMIN']
          // data.menus = [{ 'id': '006edd1fed5946f988b83e691c56ea88', 'parentId': '', 'name': '交易', 'type': 'menu', 'router': 'project', 'path': 'Layout', 'keepAlive': false, 'target': 'data-pjax', 'icon': 'guide', 'disabled': true, 'permission': 'project', 'note': null, 'location': 0, 'children': [{ 'id': '4507988072544926ae66219a2eb0980b', 'parentId': '006edd1fed5946f988b83e691c56ea88', 'name': '订单管理', 'type': 'menu', 'router': 'order', 'path': 'views/order/list', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'order.list', 'note': null, 'location': 1, 'children': [], 'createdBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'createdAt': 1631004808080, 'updatedBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'updatedAt': 1631006087979, 'delFlag': false }, { 'id': 'f7404b9cabdd49558b20562c484e0739', 'parentId': '006edd1fed5946f988b83e691c56ea88', 'name': '交易管理', 'type': 'menu', 'router': 'list', 'path': 'views/project/list', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'project.list', 'note': null, 'location': 5, 'children': [], 'createdBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'createdAt': 1630824469024, 'updatedBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'updatedAt': 1630824469025, 'delFlag': false }], 'createdBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'createdAt': 1630824391881, 'updatedBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'updatedAt': 1630824391881, 'delFlag': false }, { 'id': '970f52e5787a42c48a49bd3b64c12615', 'parentId': '', 'name': '服务商', 'type': 'menu', 'router': 'company', 'path': 'Layout', 'keepAlive': false, 'target': 'data-pjax', 'icon': 'international', 'disabled': true, 'permission': 'company', 'note': null, 'location': 9, 'children': [{ 'id': 'e8f98702ea194accbb16093f96c0dc85', 'parentId': '970f52e5787a42c48a49bd3b64c12615', 'name': '服务商管理', 'type': 'menu', 'router': 'list', 'path': 'views/company/list/index', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'company.list', 'note': null, 'location': 10, 'children': [], 'createdBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'createdAt': 1629365537685, 'updatedBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'updatedAt': 1629365537685, 'delFlag': false }], 'createdBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'createdAt': 1629365469707, 'updatedBy': 'eddbbf47d09f48e7b9e09ee935767f01', 'updatedAt': 1629365469707, 'delFlag': false }, { 'id': '07946db8faf8453f8329b09748b584ab', 'parentId': '', 'name': '用户', 'type': 'menu', 'router': 'member', 'path': 'Layout', 'keepAlive': false, 'target': 'data-pjax', 'icon': 'member', 'disabled': true, 'permission': 'member', 'note': null, 'location': 24, 'children': [{ 'id': '009075998b9d4ad0a9ff94ff76a1072e', 'parentId': '07946db8faf8453f8329b09748b584ab', 'name': '用户管理', 'type': 'menu', 'router': 'list', 'path': 'views/member/list', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'member.list', 'note': null, 'location': 25, 'children': [], 'createdBy': '', 'createdAt': 1574498576373, 'updatedBy': '', 'updatedAt': 1605492745940, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574498422143, 'updatedBy': '', 'updatedAt': 1605492705807, 'delFlag': false }, { 'id': '79dac338b5bd44be80a57a3b83c5010d', 'parentId': '', 'name': '平台', 'type': 'menu', 'router': 'platform', 'path': 'Layout', 'keepAlive': false, 'target': 'data-pjax', 'icon': 'app_y', 'disabled': true, 'permission': 'platform.manage', 'note': null, 'location': 63, 'children': [{ 'id': '5cb8e25ee76d48d097fce7728da27488', 'parentId': '79dac338b5bd44be80a57a3b83c5010d', 'name': '幻灯片管理', 'type': 'menu', 'router': 'adv', 'path': 'views/platform/adv', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'platform.adv', 'note': null, 'location': 64, 'children': [], 'createdBy': '', 'createdAt': 1586250103521, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1575623264493, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '8c183776a111417d892d2605b26f99bd', 'parentId': '', 'name': '设置', 'type': 'menu', 'router': 'config', 'path': 'Layout', 'keepAlive': false, 'target': 'data-pjax', 'icon': 'setup', 'disabled': true, 'permission': 'config', 'note': null, 'location': 89, 'children': [{ 'id': 'c5d3f857ed9e4f43b1bc2f7855aa7dfe', 'parentId': '8c183776a111417d892d2605b26f99bd', 'name': '平台配置', 'type': 'menu', 'router': 'platform', 'path': 'views/config', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'platform', 'note': null, 'location': 90, 'children': [{ 'id': '1fe19e3fdd8d4b0fb1cf3c966562dcda', 'parentId': 'c5d3f857ed9e4f43b1bc2f7855aa7dfe', 'name': '基本设置', 'type': 'menu', 'router': 'basic', 'path': 'views/config/basic', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'basic.config', 'note': null, 'location': 91, 'children': [], 'createdBy': '', 'createdAt': 1591171147183, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '29f15b9ec9d34d3fad3557051e26a6b4', 'parentId': 'c5d3f857ed9e4f43b1bc2f7855aa7dfe', 'name': '平台设置', 'type': 'menu', 'router': 'platform', 'path': 'views/config/platform', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'platform.config', 'note': null, 'location': 92, 'children': [], 'createdBy': '', 'createdAt': 1583752457150, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1583752393871, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '56733486dd864f4b965909dedbc0f354', 'parentId': '8c183776a111417d892d2605b26f99bd', 'name': '支付配置', 'type': 'menu', 'router': 'pay', 'path': 'views/config', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'pay', 'note': null, 'location': 93, 'children': [{ 'id': '8f92fe4653a64381968bd2be335254db', 'parentId': '56733486dd864f4b965909dedbc0f354', 'name': '支付设置', 'type': 'menu', 'router': 'config', 'path': 'views/config/pay', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'pay.config', 'note': null, 'location': 94, 'children': [], 'createdBy': '', 'createdAt': 1591003958218, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1591003864937, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '44915233547045838176943e5e64b02c', 'parentId': '8c183776a111417d892d2605b26f99bd', 'name': '短信配置', 'type': 'menu', 'router': 'sms', 'path': 'views/config', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sms', 'note': null, 'location': 95, 'children': [{ 'id': '833159794b894f409552fc3731045a95', 'parentId': '44915233547045838176943e5e64b02c', 'name': '短信消息库', 'type': 'menu', 'router': 'temp', 'path': 'views/config/sms/template', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sms.template', 'note': null, 'location': 96, 'children': [], 'createdBy': '', 'createdAt': 1578714258384, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '949c0ac4b79b4d7d9905cf159ed78f97', 'parentId': '44915233547045838176943e5e64b02c', 'name': '短信日志', 'type': 'menu', 'router': 'log', 'path': 'views/config/sms/log', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sms.log', 'note': null, 'location': 100, 'children': [], 'createdBy': '', 'createdAt': 1591171229085, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '291134b53e5943ebaacaa5bef9ea1129', 'parentId': '44915233547045838176943e5e64b02c', 'name': '短信设置', 'type': 'menu', 'router': 'config', 'path': 'views/config/sms', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sms.config', 'note': null, 'location': 101, 'children': [], 'createdBy': '', 'createdAt': 1574388910461, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574388739670, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '1ffdbdb1aba6412a91cda0c8ae474d53', 'parentId': '8c183776a111417d892d2605b26f99bd', 'name': '邮箱配置', 'type': 'menu', 'router': 'email', 'path': 'views/config', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'email', 'note': null, 'location': 102, 'children': [{ 'id': '09d5326510f14a369ad3862e45bb0d94', 'parentId': '1ffdbdb1aba6412a91cda0c8ae474d53', 'name': '邮箱设置', 'type': 'menu', 'router': 'config', 'path': 'views/config/email', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'email.config', 'note': null, 'location': 103, 'children': [], 'createdBy': '', 'createdAt': 1574492659499, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574492593774, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': 'b0e6954f9a3946ab8f46b86df03310d9', 'parentId': '8c183776a111417d892d2605b26f99bd', 'name': '微信配置', 'type': 'menu', 'router': 'wx', 'path': 'views/config', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'wx', 'note': null, 'location': 104, 'children': [{ 'id': 'bc9cecb2d7434c8fb744009b6de864f3', 'parentId': 'b0e6954f9a3946ab8f46b86df03310d9', 'name': '微信设置', 'type': 'menu', 'router': 'config', 'path': 'views/config/wx', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'wx.config', 'note': null, 'location': 105, 'children': [], 'createdBy': '', 'createdAt': 1578136710711, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1578136683592, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574388706131, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '18aa6ddc17fb425398a42e552593a46c', 'parentId': '', 'name': '系统', 'type': 'menu', 'router': 'sys', 'path': 'Layout', 'keepAlive': false, 'target': 'data-pjax', 'icon': 'application', 'disabled': true, 'permission': 'sys', 'note': null, 'location': 106, 'children': [{ 'id': '311c79c6900f433896443733ec560581', 'parentId': '18aa6ddc17fb425398a42e552593a46c', 'name': '系统管理', 'type': 'menu', 'router': 'system', 'path': 'views/system', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.list', 'note': null, 'location': 107, 'children': [{ 'id': 'c037add7c6d343bf85dc949243eeb773', 'parentId': '311c79c6900f433896443733ec560581', 'name': '用户管理', 'type': 'menu', 'router': 'user', 'path': 'views/system/user', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.user', 'note': null, 'location': 108, 'children': [], 'createdBy': '', 'createdAt': 1574161180497, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': 'c4d41ca75e7d497fb4f7458058d6178c', 'parentId': '311c79c6900f433896443733ec560581', 'name': '机构管理', 'type': 'menu', 'router': 'dept', 'path': 'views/system/dept', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.dept', 'note': null, 'location': 113, 'children': [], 'createdBy': '', 'createdAt': 1574161025962, 'updatedBy': '', 'updatedAt': 1605768285641, 'delFlag': false }, { 'id': '4c2cf8cc6bb348f798302eb05bc064e5', 'parentId': '311c79c6900f433896443733ec560581', 'name': '角色管理', 'type': 'menu', 'router': 'role', 'path': 'views/system/role', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.role', 'note': null, 'location': 118, 'children': [], 'createdBy': '', 'createdAt': 1574161117900, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '877de34c9cc64cd1bf9bac4a4187af6d', 'parentId': '311c79c6900f433896443733ec560581', 'name': '菜单管理', 'type': 'menu', 'router': 'menu', 'path': 'views/system/menu', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.menu', 'note': null, 'location': 123, 'children': [], 'createdBy': '', 'createdAt': 1574161386646, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '32d2ec8e5e5e47af89f5aab0da69c257', 'parentId': '311c79c6900f433896443733ec560581', 'name': '文件管理', 'type': 'menu', 'router': 'file', 'path': 'views/system/file', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.file', 'note': null, 'location': 128, 'children': [], 'createdBy': '', 'createdAt': 1605610798068, 'updatedBy': '', 'updatedAt': 1605610798068, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574160877432, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '8244ce943bb94c0cb297406312879a4e', 'parentId': '18aa6ddc17fb425398a42e552593a46c', 'name': '系统监控', 'type': 'menu', 'router': 'control', 'path': 'views/system', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.monitor', 'note': null, 'location': 131, 'children': [{ 'id': 'a611332c706a4f8ca66241798bbfc418', 'parentId': '8244ce943bb94c0cb297406312879a4e', 'name': 'SQL监控', 'type': 'menu', 'router': 'druid', 'path': 'views/system/druid/index', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.druid', 'note': null, 'location': 132, 'children': [], 'createdBy': '', 'createdAt': 1595487968816, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': 'babc109965724ecd9a28cbad240eb6a4', 'parentId': '8244ce943bb94c0cb297406312879a4e', 'name': '定时任务', 'type': 'menu', 'router': 'task', 'path': 'views/system/task', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.task', 'note': null, 'location': 133, 'children': [], 'createdBy': '', 'createdAt': 1574161493278, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '3ad9f3a89bde4a6f9cf8558197fe2586', 'parentId': '8244ce943bb94c0cb297406312879a4e', 'name': '操作日志', 'type': 'menu', 'router': 'log', 'path': 'views/system/log', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.log', 'note': null, 'location': 137, 'children': [], 'createdBy': '', 'createdAt': 1574161246134, 'updatedBy': '', 'updatedAt': 1605592957450, 'delFlag': false }, { 'id': '1d4949d0216349f1a91176ce662966d4', 'parentId': '8244ce943bb94c0cb297406312879a4e', 'name': '访问日志', 'type': 'menu', 'router': 'loginlog', 'path': 'views/system/loginlog', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.loginlog', 'note': null, 'location': 138, 'children': [], 'createdBy': '', 'createdAt': 1605595571851, 'updatedBy': '', 'updatedAt': 1605595571852, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574160914980, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }, { 'id': '1ae955fd9c9e4c0f95c7fa29184ae4fa', 'parentId': '18aa6ddc17fb425398a42e552593a46c', 'name': '系统设置', 'type': 'menu', 'router': 'config', 'path': 'views/system', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.config', 'note': null, 'location': 139, 'children': [{ 'id': 'fd030c01a974447fa552ed5e640dda70', 'parentId': '1ae955fd9c9e4c0f95c7fa29184ae4fa', 'name': '系统参数', 'type': 'menu', 'router': 'param', 'path': 'views/system/param', 'keepAlive': false, 'target': 'data-pjax', 'icon': null, 'disabled': true, 'permission': 'sys.conf', 'note': null, 'location': 140, 'children': [], 'createdBy': '', 'createdAt': 1574161319712, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574160934221, 'updatedBy': '', 'updatedAt': 1605780295786, 'delFlag': false }], 'createdBy': '', 'createdAt': 1574160855122, 'updatedBy': '', 'updatedAt': 0, 'delFlag': false }]
          // data.nickname = '超级管理员'

          const { roles, nickname, avatar, introduction, menus } = data

          // roles must be a non-empty array
          if (!roles || roles.length <= 0) {
            reject('角色必须是非空数组!')
          }

          var id = 'id'
          var pid = 'parentId'
          var children = 'children'
          var idMap = {}
          var jsonTree = []
          menus.forEach(function(v) {
            idMap[v[id]] = v
          })
          menus.forEach(function(v) {
            var parent = idMap[v[pid]]
            if (parent) {
              !parent[children] && (parent[children] = [])
              parent[children].push(v)
            } else {
              jsonTree.push(v)
            }
          })

          console.log('>>' + JSON.stringify(jsonTree))

          localStorage.setItem('menus', JSON.stringify(jsonTree))

          commit('SET_ROLES', roles)
          commit('SET_NAME', nickname)
          commit('SET_AVATAR', avatar)
          commit('SET_INTRODUCTION', introduction)
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      }, 0)
    })
  },

  // 用户注销
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      open.logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()

        // 重置访问的视图和缓存的视图
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 删除标记
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // 动态修改权限
  changeRoles({ commit, dispatch }, role) {
    // eslint-disable-next-line no-async-promise-executor
    return new Promise(async resolve => {
      const token = role + '-token'

      console.log(111)

      commit('SET_TOKEN', token)
      setToken(token)

      const { roles } = await dispatch('getInfo')

      resetRouter()

      // 基于角色生成可访问路由图
      const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })

      // 动态添加可访问路由
      router.addRoutes(accessRoutes)

      // 重置访问的视图和缓存的视图
      dispatch('tagsView/delAllViews', null, { root: true })

      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
