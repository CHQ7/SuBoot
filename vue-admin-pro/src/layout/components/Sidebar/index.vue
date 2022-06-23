<template>
  <!--  :style="{'backgroundColor':backgroundColor}" ,'sidebar-page':page==='0'}-->
  <div :class="{'has-logo':showLogo,'sidebar-page':page==='0'}" :style="{'backgroundColor':backgroundColor}">
    <logo v-if="showLogo" :collapse="isCollapse" :title="title" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <!--      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="backgroundColor"
        :text-color="textColor"
        :unique-opened="true"
        :active-text-color="color"
        :collapse-transition="false"
        mode="vertical"
        @select="handleSelect"
        @open="handleOpen"
      >
        <sidebar-item v-for="route in permission_routes" :key="route.path" :path-route="path" :item="route" :base-path="route.path" />
      </el-menu>-->

      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="true"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in permission_routes" :key="route.path" :item="route" :base-path="route.path" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'

export default {
  components: { SidebarItem, Logo },
  data() {
    return {
      path: '',
      backgroundColor: '',
      textColor: ''
    }
  },
  /*  watch: {
    page(val) {
      if (val === '0') {
        this.backgroundColor = '#FFF'
        this.textColor = '#1d1e23'
      } else {
        this.backgroundColor = '#1d1e23'
        this.textColor = '#1d1e23'
      }
    }
  },*/
  computed: {
    ...mapState({
      color: state => state.app.color,
      page: state => state.app.page
    }),
    ...mapGetters([
      'permission_routes',
      'sidebar'
    ]),
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // 如果设置路径，侧边栏将突出显示您设置的路径
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    title() {
      return 'CSMS'
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  },
  created() {
    this.path = this.$route.path.split('/')
    this.backgroundColor = this.page === '0' ? '#FFF' : '#1d1e23'
    this.textColor = this.page === '1' ? '#FFF' : '#1d1e23'
  },
  methods: {
    handleSelect(key, keyPath) {
      this.path = key.split('/')
      // this.$nextTick(() => {
      //   const active = $('.el-menu-item.is-active')
      //   const color = this.color + ' !important'
      //   // active.css('backgroundColor', color)
      // })
    },
    handleOpen(key, keyPath) {
      this.path = key.split('/')
    }
  }
}
</script>
<style lang="scss" scoped>
  .sidebar-page{
    background-color: #FFF !important;

    ::v-deep{
      .el-scrollbar{
        .el-menu-item.is-active::after{
          position: absolute;
          top: 0;
          right: 0;
          bottom: 0;
          opacity: 1;
          border-width: 2px;
          border-style: solid;
          /*border-right: 3px solid #52c41a;*/
          height: 50px;
          content: "";
        }
      }

      .el-menu-item{
        height: 48px;
        line-height: 48px;
        &::after{
          transform: scaleY(1);
          opacity: 0;
          height: 0;
          /*transition:all 0.5s;*/
          transition: transform .15s cubic-bezier(.645,.045,.355,1),opacity .15s cubic-bezier(.645,.045,.355,1);
        }
      }

      .el-submenu__title{
        line-height: 48px;
        height: 48px;
      }

    }

  }

</style>
