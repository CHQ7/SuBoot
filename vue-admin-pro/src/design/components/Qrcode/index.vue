<template>
  <div id="qrcode" ref="qrcode" />
</template>

<script>
/**
 * 生成二维码
 */
import QRCode from 'qrcodejs2'
export default {
  name: 'Qrcode',
  props: {
    /** 二维码内容 */
    content: {
      type: String,
      default: ''
    },
    /** 宽度 */
    width: {
      type: String,
      default: '128'
    },
    /** 高度 */
    height: {
      type: String,
      default: '128'
    },
    colorDark: {
      type: String,
      default: '#000000'
    },
    colorLight: {
      type: String,
      default: '#ffffff'
    }
  },
  data() {
    return {}
  },
  mounted() {
    this.qrcode()
  },
  methods: {
    qrcode() {
      new QRCode('qrcode', {
        width: this.width,
        height: this.height,
        text: this.content, // 二维码地址
        colorDark: this.colorDark,
        colorLight: this.colorLight
      })
    }
  }
}
</script>

<style lang="scss" scoped>
#qrcode {
  box-sizing: border-box;
  display: inline-block;
  padding: 8px;
  background: #FFFFFF;
}
</style>
