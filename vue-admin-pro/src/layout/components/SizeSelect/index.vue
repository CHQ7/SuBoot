<template>
  <el-tooltip content="字体" :disabled="!tips" effect="dark" placement="bottom">
    <el-dropdown trigger="click" @command="toggleClick">
      <div>
        <u-icon-svg class-name="size-icon" name="size" />
      </div>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item v-for="item of option" :key="item.value" :disabled="size===item.value" :command="item.value">
          {{ item.label }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </el-tooltip>
</template>

<script>
/**
 * 全局字体选择组件
 */
export default {
  name: 'SizeSelect',
  props: {
    /** 是否显示提示框 */
    tips: {
      type: Boolean,
      default: true
    },
    /** 默认选项 */
    size: {
      type: String,
      default: ''
    },
    /** 下拉选项数据 */
    option: {
      type: Array,
      default: () => [
        { label: '默认', value: 'default' },
        { label: '中', value: 'medium' },
        { label: '小', value: 'small' },
        { label: '最小', value: 'mini' }
      ]
    }
  },
  methods: {
    toggleClick(size) {
      this.$emit('click', size)
    }
  }

}
</script>
