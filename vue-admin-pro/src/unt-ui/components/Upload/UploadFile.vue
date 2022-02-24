<template>
  <div class="upload">

    <el-upload
      drag
      :action="uploadUrl"
      :limit="limit"
      :name="name"
      :accept="accept"
      :disabled="disabled"
      :before-upload="handleBeforeUpload"
      :on-success="handleImageSuccess"
      :on-error="handleImageError"
    >
      <i class="el-icon-upload" />
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div slot="tip" class="el-upload__tip">只能上传{{ accept }}文件，且不超过{{ size }}M</div>
    </el-upload>
  </div>
</template>

<script>

import { Notification } from 'element-ui'
/**
 * 上传文件组件
 */
export default {
  name: 'UploadFile',
  props: {
    value: {
      type: String,
      default: ''
    },
    /** 上传的文件字段名 */
    name: {
      type: String,
      default: ''
    },
    /** 接受上传的文件类型 */
    accept: {
      type: String,
      default: ''
    },
    /** 最大允许上传个数,默认1 */
    limit: {
      type: Number,
      default: 1
    },
    /** 最是否禁用,默认false */
    disabled: {
      type: Boolean,
      default: false
    },
    /** 最是否禁用,默认2M */
    size: {
      type: Number,
      default: 2
    }
  },
  data() {
    return {
      uploadUrl: this.domain + '/nt/upload/file'
    }
  },
  computed: {
    imageUrl() {
      return this.value
    }
  },
  methods: {
    emitInput(val) {
      this.$emit('input', val)
    },
    // 上传成功回调
    handleImageSuccess(response, file, fileList) {
      if (response.code !== 20000) {
        Notification.error({
          title: '错误',
          message: response.msg || '上传失败'
        })
      } else {
        this.emitInput(response.data)
      }
    },
    // 上传失败回调
    handleImageError(err, file, fileList) {
      Notification.error({
        title: '错误',
        message: err.msg || '上传失败'
      })
    },
    // 上传文件之前处理
    handleBeforeUpload(file) {
      if (file.size / 1024 / 1024 > this.size) {
        Notification.error({
          title: '错误',
          message: '上传图片大小不能超过' + this.size + 'MB'
        })
        return false
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
