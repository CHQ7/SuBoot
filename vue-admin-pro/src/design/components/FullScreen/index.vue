<template>
  <el-tooltip :content="isFullscreen? '退出全屏':'全屏'" :disabled="!tips" effect="dark" placement="bottom">
    <div>
      <u-icon-svg :name="isFullscreen? 'click-to-fold':'full-screen'" @click="toggle" />
    </div>
  </el-tooltip>
</template>

<script>
import screenfull from 'screenfull'

/**
 * 全屏组件
 */
export default {
  name: 'FullScreen',
  props: {
    /** 是否显示提示框 */
    tips: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      // 全屏状态
      isFullscreen: false
    }
  },
  // 初始化页面
  mounted() {
    this.init()
  },
  // 离开当前路由
  beforeDestroy() {
    this.destroy()
  },
  methods: {
    // 点击切换全屏状态
    toggle() {
      if (!screenfull.isEnabled) {
        this.$u.toast('开启全屏失败，您的浏览器不能工作', 'warning')
        return false
      }
      screenfull.toggle()
    },
    // 监听全屏状态变化
    change() {
      this.isFullscreen = screenfull.isFullscreen
    },
    // 初始化全屏状态事件
    init() {
      if (screenfull.isEnabled) {
        screenfull.on('change', this.change)
      }
    },
    // 关闭全屏状态事件
    destroy() {
      if (screenfull.isEnabled) {
        screenfull.off('change', this.change)
      }
    }
  }
}
</script>
<style lang="scss" scoped>

</style>
