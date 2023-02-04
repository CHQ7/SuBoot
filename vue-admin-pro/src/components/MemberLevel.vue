<template>
  <el-select :value="value" placeholder="选择等级" :style="{ 'width': width }" clearable @change="change">
    <el-option v-for="item in level" :key="item.id" :label="item.name" :value="item.id" />
  </el-select>
</template>

<script>
/**
 * 用户等级
 */
export default {
  name: 'MemberLevel',
  props: {
    /** 数据源 */
    value: {
      type: [Number, String],
      default: ''
    },
    /** 宽度 */
    width: {
      type: String,
      default: '130px'
    },
    /** 是否添加全部 */
    all: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      api: this.$u.api.MemberData,
      // 用户等级
      level: []
    }
  },
  created() {
    // 初始化数据
    this.initData()
  },
  methods: {
    // 初始化数据
    initData() {
      this.api.level().then(res => {
        this.level = res.data

        if (this.all) {
          // 添加一个全部
          this.level.unshift({
            id: '',
            name: '全部'
          })
        }
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
