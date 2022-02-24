<template>
  <el-tooltip content="主题" :disabled="!tips" effect="dark" placement="bottom">
    <div>
      <u-icon-svg name="palette" @click="toggleClick" />
    </div>
  </el-tooltip>
</template>

<script>

/**
 * 主题
 */
export default {
  name: 'Palette',
  props: {
    /** 是否显示提示框 */
    tips: {
      type: Boolean,
      default: true
    }
  },
  methods: {
    toggleClick() {
      this.$emit('click')
    }
  }
}
</script>
<style scoped>

</style>
