<template>
  <el-dialog
    v-if="visible"
    :title="title"
    :visible.sync="visible"
    :center="center"
    :width="width"
    :fullscreen="fullscreen"
    :modal="modal"
    :close-on-click-modal="closeOnClickModal"
    :append-to-body="appendToBody"
    @close="cancel"
  >
    <slot />
    <div v-if="footer" slot="footer" class="dialog-footer">
      <el-button @click="cancel">{{ cancelButtonText }}</el-button>
      <el-button v-if="confirmButtonText" type="primary" @click="confirm">{{ confirmButtonText }}</el-button>
    </div>
  </el-dialog>
</template>

<script>
/**
 * 对话框
 */
export default {
  name: 'Dialog',
  props: {
    /** 对话框名称 */
    title: {
      type: String,
      default: ''
    },
    /** 是否显示对话框 */
    show: {
      type: Boolean,
      default: false
    },
    /** 是否对头部和底部采用居中布局 */
    center: {
      type: Boolean,
      default: false
    },
    /** 对话框的宽度 */
    width: {
      type: String,
      default: '50%'
    },
    /** 是否为全屏对话框 */
    fullscreen: {
      type: Boolean,
      default: false
    },
    /** 是否需要遮罩层 */
    modal: {
      type: Boolean,
      default: true
    },
    /** 是否可以通过点击 modal 关闭 Dialog */
    closeOnClickModal: {
      type: Boolean,
      default: false
    },
    /** Dialog 自身是否插入至 body 元素上。嵌套的 Dialog 必须指定该属性并赋值为 true */
    appendToBody: {
      type: Boolean,
      default: false
    },
    /** 关闭按键 */
    footer: {
      type: Boolean,
      default: true
    },
    /** 取消按钮文字 */
    cancelButtonText: {
      type: String,
      default: '取 消'
    },
    /** 确认按钮文字 */
    confirmButtonText: {
      type: String,
      default: '确 定'
    }
  },
  data() {
    return {
      visible: this.show
    }
  },
  watch: {
    show() {
      this.visible = this.show
    }
  },
  methods: {
    confirm() {
      this.$emit('confirm')
    },
    cancel() {
      this.$emit('update:show', false)
      this.$emit('cancel')
    }
  }
}
</script>

<style lang="scss" scoped>

  ::v-deep{
    .el-dialog__header {
      background-color: #f4f5f9;
      padding-top: 10px;
    }

    .el-dialog__title {
      font-size: 14px;
      line-height: 14px;
    }

    .el-dialog__headerbtn{
      top: 10px;
    }
  }

</style>
