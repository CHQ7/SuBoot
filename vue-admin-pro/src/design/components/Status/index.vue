<template>
  <el-select :value="value" placeholder="请选择状态" :style="{ 'width': width }" clearable @change="change">
    <el-option value="" label="全部" />
    <el-option :value="true" label="启用" />
    <el-option :value="false" label="禁用" />
  </el-select>
</template>

<script>
/**
 * 状态选择
 */
export default {
  name: 'Status',
  props: {
    /** 数据源 */
    value: {
      type: String,
      default: ''
    },
    /** 宽度 */
    width: {
      type: String,
      default: '120px'
    }
  },
  data() {
    return {
    }
  },
  methods: {
    change(e) {
      this.$emit('input', e)
      this.$emit('change', e)
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep{
  .el-input{
    width: 100% !important;
  }
}
</style>
