<template>
  <div class="order-info">
    <div class="dt">
      {{ title }}
    </div>
    <div class="dd">
      {{ value }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'OrderInfo',
  props: {
    value: String,
    title: String
  }
}
</script>

<style lang="scss" scoped>
  .order-info{
    display: flex;
    justify-content: left;
    align-items: center;
    font-size: 14px;
    line-height: 28px;
    .dt{
      width: 100px;
      color: #99a9bf;
    }
  }
</style>
