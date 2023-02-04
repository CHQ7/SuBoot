<template>
  <el-select :value="value" placeholder="请选择渠道" :style="{ 'width': width }" clearable @change="change">
    <el-option v-for="item in channel" :key="item.value" :label="item.name" :value="item.type" />
  </el-select>
</template>

<script>
/**
 * 业务组件 - 会员注册渠道
 */
export default {
  name: 'Channel',
  props: {
    /** 数据源 */
    value: {
      type: String,
      default: ''
    },
    /** 宽度 */
    width: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      api: this.$u.api.MemberData,
      // 数据列表
      channel: []
    }
  },
  created() {
    // 初始化数据
    this.initData()
  },
  methods: {
    // 初始化数据
    initData() {
      this.api.channel().then(res => {
        this.channel = res.data
      })
    },
    change(e) {
      this.$emit('input', e)
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
