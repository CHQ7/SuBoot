<template>
  <div class="v-footer" :class="[fixed?'v-fixed':'']" :style="{backgroundColor:backgroundColor}">
    <div class="v-footer-copyright" :style="{color:color,fontSize:size+'rpx'}">
      {{ copyright }}
    </div>

  </div>
</template>

<script>
/**
 * 页面底部组件
 */
export default {
  name: 'Footer',
  props: {
    /** 底部文本 */
    copyright: {
      type: String,
      default: 'All Rights Reserved.'
    },
    /** 字体颜色 */
    color: {
      type: String,
      default: '#A7A7A7'
    },
    /** 字体大小 */
    size: {
      type: Number,
      default: 24
    },
    /** footer背景颜色 */
    backgroundColor: {
      type: String,
      default: 'transparent'
    },
    /** 是否固定在底部 */
    fixed: {
      type: Boolean,
      default: true
    }
  }
}
</script>

<style lang="scss" scoped>
  .v-footer{
    width: 100%;
    overflow: hidden;
    padding: 60px 48px;
    box-sizing: border-box;

    .v-footer-copyright {
      font-size: 12px;
      color: #A7A7A7;
      line-height: 1;
      text-align: center;
      padding-top: 32px;
    }
  }

  .v-fixed {
    position: fixed;
    z-index: 9999;
    bottom: 0;
    left: 0;
  }
</style>
