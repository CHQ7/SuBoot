import Cookies from 'js-cookie'

const state = {
  sidebar: {
    opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
    withoutAnimation: false
  },
  page: Cookies.get('page') || '1',
  color: Cookies.get('color') || '#52c41a',
  device: 'desktop',
  mode: Cookies.get('mode') || '0',
  size: Cookies.get('size') || 'small'
}

const mutations = {
  TOGGLE_SIDEBAR: state => {
    state.sidebar.opened = !state.sidebar.opened
    state.sidebar.withoutAnimation = false
    if (state.sidebar.opened) {
      Cookies.set('sidebarStatus', 1)
    } else {
      Cookies.set('sidebarStatus', 0)
    }
  },
  CLOSE_SIDEBAR: (state, withoutAnimation) => {
    Cookies.set('sidebarStatus', 0)
    state.sidebar.opened = false
    state.sidebar.withoutAnimation = withoutAnimation
  },
  TOGGLE_DEVICE: (state, device) => {
    state.device = device
  },
  SET_SIZE: (state, size) => {
    state.size = size
    Cookies.set('size', size)
  },
  SET_COLOR: (state, color) => {
    state.color = color
    Cookies.set('color', color)
  },
  SET_PAGE: (state, page) => {
    state.page = page
    Cookies.set('page', page)
  },
  SET_MODE: (state, mode) => {
    state.mode = mode
    Cookies.set('mode', mode)
  }
}

const actions = {
  toggleSideBar({ commit }) {
    commit('TOGGLE_SIDEBAR')
  },
  closeSideBar({ commit }, { withoutAnimation }) {
    commit('CLOSE_SIDEBAR', withoutAnimation)
  },
  toggleDevice({ commit }, device) {
    commit('TOGGLE_DEVICE', device)
  },
  setSize({ commit }, size) {
    commit('SET_SIZE', size)
  },
  setColor({ commit }, color) {
    commit('SET_COLOR', color)
  },
  setPage({ commit }, page) {
    commit('SET_PAGE', page)
  },
  setMode({ commit }, mode) {
    commit('SET_MODE', mode)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
