<template>
  <el-select :value="value" placeholder="选择标签" :style="{ 'width': width }" clearable @change="change">
    <el-option v-for="item in label" :key="item.value" :label="item.name" :value="item.id" />
  </el-select>
</template>

<script>
/**
 * 用户标签
 */
export default {
  name: 'MemberLabel',
  props: {
    /** 数据源 */
    value: {
      type: String,
      default: ''
    },
    /** 宽度 */
    width: {
      type: String,
      default: '130px'
    }
  },
  data() {
    return {
      api: this.$u.api.MemberData,
      // 用户标签
      label: []
    }
  },
  created() {
    // 初始化数据
    this.initData()
  },
  methods: {
    // 初始化数据
    initData() {
      this.api.label().then(res => {
        this.label = res.data

        // 添加一个全部
        this.label.unshift({
          id: '',
          name: '全部'
        })
      })
    },
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
