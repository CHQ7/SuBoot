<template>
  <el-tooltip :content="isFullscreen? '退出全屏':'全屏'" :disabled="!tips" effect="dark" placement="bottom">
    <div>
      <u-icon-svg :name="isFullscreen? 'compress':'arrows-alt'" @click="toggle" />
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
      isFullscreen: false
    }
  },
  mounted() {
    this.init()
  },
  beforeDestroy() {
    this.destroy()
  },
  methods: {
    toggle() {
      if (!screenfull.isEnabled) {
        this.$u.toast('开启全屏失败，您的浏览器不能工作', 'warning')
        return false
      }
      screenfull.toggle()
    },
    change() {
      this.isFullscreen = screenfull.isFullscreen
    },
    init() {
      if (screenfull.isEnabled) {
        screenfull.on('change', this.change)
      }
    },
    destroy() {
      if (screenfull.isEnabled) {
        screenfull.off('change', this.change)
      }
    }
  }
}
</script>
<style scoped>

</style>
