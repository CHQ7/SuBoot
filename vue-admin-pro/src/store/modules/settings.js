import variables from '@/styles/element-variables.scss'
import defaultSettings from '@/config/settings'

const { title, name, copyright, showSettings, tagsView, fixedHeader, sidebarLogo } = defaultSettings

const state = {
  title: title,
  name: name,
  copyright: copyright,
  theme: variables.theme,
  showSettings: showSettings,
  tagsView: tagsView,
  fixedHeader: fixedHeader,
  sidebarLogo: sidebarLogo
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  }
}

const actions = {
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

