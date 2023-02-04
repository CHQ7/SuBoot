<template>
  <div class="u-tip-info">
    <span v-if="title">{{ title }}</span>
    <slot />
  </div>
</template>

<script>
/**
 * 表单提示信息
 */
export default {
  name: 'TipInfo',
  props: {
    /** 数据源 */
    title: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
    }
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
.u-tip-info{
  font-size: 12px;
  line-height: 16px;
  padding-top: 10px;
  color: #939799;
  position: relative;

  span{
    font-size: 12px;
    line-height: 16px;
    padding-top: 10px;
    color: #939799;
  }
}
</style>
