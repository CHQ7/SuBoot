<template>
  <div class="navbar">
    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />
    <div style="flex: 1 1 0%;" />

    <div class="right-menu">
      <template v-if="device!=='mobile'">

        <!-- 异常组件 -->
        <error-log class="right-menu-item hover-effect" :logs="$store.getters.errorLogs" @clearAll="clearAll" />

        <!-- 全屏组件 -->
        <u-full-screen class="right-menu-item hover-effect" />

        <!-- 字体选择组件
        <size-select id="size-select" class="right-menu-item hover-effect" :size="$store.getters.size" @click="handleSetSize" />
        -->

        <!-- 打开主题
        <palette v-if="showSettings" class="right-menu-item hover-effect" @click="drawer= true" />
        -->

        <!-- 右面板 -->
        <el-drawer append-to-body :visible.sync="drawer" direction="rtl">
          <settings />
        </el-drawer>

      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <!-- <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">-->
          {{ nickname }}
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="openPassword">更改密码</el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <u-dialog title="更改密码" :show.sync="showPassword" :footer="false">
      <Password />
    </u-dialog>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import Hamburger from './Hamburger'
import ErrorLog from './ErrorLog'
import Settings from './Settings'
import Password from './Password'
import path from 'path'
import { isExternal } from '@/layout/components/Sidebar/SidebarItem'
export default {
  components: {
    ErrorLog,
    Hamburger,
    Settings,
    Password
  },
  data() {
    return {
      drawer: false,
      showPassword: false,
      backgroundColor: '',
      onlyOneChild: null,
      list: [
        { 'path': '/member', 'name': 'member', 'meta': { 'title': '会员', 'icon': 'member', 'noCache': false }, 'children': [{ 'path': 'list', 'name': 'list', 'meta': { 'title': '会员管理', 'icon': null, 'noCache': false }}, { 'path': 'level', 'name': 'level', 'meta': { 'title': '会员等级', 'icon': null, 'noCache': false }}], 'alwaysShow': true }
      ]
    }
  },
  created() {
    this.backgroundColor = this.page === '0' ? '#FFF' : '#1d1e23'
  },
  computed: {
    ...mapState({
      showSettings: state => state.settings.showSettings,
      page: state => state.app.page
    }),
    ...mapGetters([
      'permission_routes',
      'sidebar',
      'avatar',
      'nickname',
      'device'
    ])
  },
  methods: {
    resolvePath(routePath) {
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      return path.resolve(this.basePath, routePath)
    },
    hasOneShowingChild(children = [], parent) {
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          // 临时设置（只有一个显示子项时使用）
          this.onlyOneChild = item
          return true
        }
      })

      // 当只有一个子路由器时，默认显示子路由器
      if (showingChildren.length === 1) {
        return true
      }

      // 如果没有要显示的子路由器，则显示父路由器
      if (showingChildren.length === 0) {
        this.onlyOneChild = { ... parent, path: '', noShowingChildren: true }
        return true
      }
      return false
    },
    // 打开更改密码弹窗
    openPassword() {
      this.showPassword = true
    },
    // 关闭更改密码弹窗
    cancel() {
      this.showPassword = false
    },
    // 清空错误日志
    clearAll() {
      this.$store.dispatch('errorLog/clearErrorLog')
    },
    // 选择字体
    handleSetSize(size) {
      this.$ELEMENT.size = size
      this.$store.dispatch('app/setSize', size)
      this.refreshView()
      this.$u.toast('设置成功', 'success')
    },
    // 刷新视图
    refreshView() {
      // 以便重新呈现缓存页
      this.$store.dispatch('tagsView/delAllCachedViews', this.$route)
      const { fullPath } = this.$route
      this.$nextTick(() => {
        this.$u.route.replace('/redirect' + fullPath)
      })
    },
    // 切换汉堡菜单
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    // 退出事件
    logout() {
      this.$confirm('您确定要退出', '温馨提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        const fullPath = this.$route.fullPath
        this.$store.dispatch('user/logout').then(() => {
          this.$u.route(`/login?redirect=${fullPath}`)
        })
      }).catch(() => {
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  $bg:#1d1e23;
  ::v-deep{
    .el-drawer{
      width: 350px !important;
      .el-drawer__header{
        margin-bottom: 0;
        padding-bottom: 0;
      }
    }
  }
.navbar {
  display: flex;
  align-items: center;
  height: 48px;
  overflow: hidden;
  position: relative;
  background: $bg;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
/*    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;*/

    display: flex;
    align-items: center;
    height: 100%;
    padding: 0 12px;
    cursor: pointer;
    transition: all .3s;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }
  .mode2{
    float: left;
    height: 100%;
    line-height: 50px;
  }

  .right-menu {
    display: flex;
    float: right;
    height: 100%;
    margin-left: auto;
    overflow: hidden;
    line-height: 48px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: flex;
      align-items: center;
      height: 100%;
      padding: 0 12px;
      cursor: pointer;
      transition: all .3s;
      color: #ffffff;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 15px;
      padding-right: 0;

      .avatar-wrapper {
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
