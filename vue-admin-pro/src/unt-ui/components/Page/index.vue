<template>
  <div class="v-page">
    <el-card>
      <div slot="header" class="page-card">
        <div class="page-title">
          <span>{{ title }}</span>
        </div>
        <div>
          <slot name="header" />
        </div>
      </div>
      <slot />
    </el-card>
  </div>

</template>

<script>
/**
 * 单页面组件
 */
export default {
  name: 'Page',
  props: {
    /** 卡片名称 */
    title: {
      type: String,
      default: ''
    }
  }
}
</script>

<style lang="scss" scoped>
  .v-page{
    & >>> .el-card__header {
      background-color: #f5f7fa;
      padding: 7px 15px;
    }
    & >>> .el-card__body {
      padding: 15px;
    }

    .page-card{
      display:flex;
      justify-content:space-between;

      .page-title{
        display: flex;
        align-items: center;
      }
    }
  }
</style>
