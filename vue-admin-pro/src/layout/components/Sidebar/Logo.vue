<template>
  <div class="sider-logo">
    <transition name="sidebarLogoFade">
      <router-link key="expand" to="/">
        <img v-if="logo" :src="logo" class="logo">
        <h1 :style="{'color':page==='0'?color:'#FFF'}">{{ title }} </h1>
      </router-link>
    </transition>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'SidebarLogo',
  props: {
    /** 标题 */
    title: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      logo: require('@/assets/logo/logo.png')
    }
  },
  watch: {
    page(val) {
      if (val === '0') {
        this.backgroundColor = '#FFF'
      } else {
        this.backgroundColor = '#1d1e23'
      }
    }
  },
  created() {
    this.backgroundColor = this.page === '0' ? '#FFF' : '#1d1e23'
  },
  computed: {
    ...mapState({
      page: state => state.app.page,
      color: state => state.app.color
    })
  }
}
</script>

<style lang="scss" scoped>

$bg:#1d1e23;

.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sider-logo {
  position: relative;
  display: flex;
  align-items: center;
  padding: 16px;
  line-height: 32px;
  cursor: pointer;

  & >a {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 32px;

    & .logo {
      display: inline-block;
      height: 32px;
      vertical-align: middle;
      transition: height .2s;
    }

    & h1{
      display: inline-block;
      height: 32px;
      margin: 0 0 0 12px;
      color: #fff;
      font-weight: 600;
      font-size: 18px;
      line-height: 32px;
      vertical-align: middle;
      animation: fade-in;
      animation-duration: .2s;
      overflow: hidden;
    }

  }

}
</style>
