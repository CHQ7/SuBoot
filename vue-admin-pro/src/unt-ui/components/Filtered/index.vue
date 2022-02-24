<template>
  <div class="filter">
    <slot />
  </div>
</template>

<script>
/**
 * 查询
 */
export default {
  name: 'Filtered'
}
</script>

<style lang="scss" scoped>
  .filter {
    padding: 15px 0;
    border-bottom: 1px solid #dfe6ec;

    >>> .el-input {
      display: inline-block;
      vertical-align: middle;
    }

    >>> .el-button {
      display: inline-block;
      vertical-align: middle;
    }
    >>> .el-select{
      display: inline-block;
      vertical-align: middle;
    }

    >>> .el-date-editor{
      display: inline-block;
      vertical-align: middle;
    }

    .filter-item {
      display: inline-block;
      vertical-align: middle;
      margin-bottom: 10px;
    }
  }
</style>
