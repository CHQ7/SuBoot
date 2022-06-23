<template>
  <div class="upload">
    <el-upload
      class="uploader"
      :action="uploadUrl"
      :name="name"
      :accept="accept"
      :disabled="disabled"
      :show-file-list="multiple"
      :list-type="multiple? 'picture-card':'text'"
      :before-upload="handleBeforeUpload"
      :on-success="handleImageSuccess"
      :on-error="handleImageError"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
    >
      <template v-if="!multiple">
        <img v-if="fileList.length !== 0" :src="fileList[0].url" class="image-preview">
        <i v-else class="el-icon-plus uploader-icon" />
      </template>
      <template v-if="multiple">
        <i class="el-icon-plus" />
      </template>
    </el-upload>
    <div v-if="tips" class="tips">
      {{ tips }}
    </div>
    <el-dialog :visible.sync="dialogVisible" append-to-body>
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
/**
   * 上传多图片组件
   */
export default {
  name: 'Upload',
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
      default: 'image/jpeg, image/jpg, image/png'
    },
    /** 最大允许上传个数,默认1 */
    limit: {
      type: Number,
      default: 1
    },
    /** 是否禁用,默认false */
    disabled: {
      type: Boolean,
      default: false
    },
    /** 是否上传多个,默认false */
    multiple: {
      type: Boolean,
      default: false
    },
    /** 是否禁用,默认2M */
    size: {
      type: Number,
      default: 2
    },
    /** 提示信息 */
    tips: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      // 上传地址
      uploadUrl: this.domain + '/nt/upload/image',
      // 弹框地址
      dialogImageUrl: '',
      // 弹框显示
      dialogVisible: false,
      fileList: []
    }
  },
  watch: {
    value() {
      if (!this.$u.test.isEmpty(this.value)) {
        this.fileList = this.value.split(',').map(item => {
          return {
            url: item
          }
        })
      }
    }
  },
  created() {
    if (!this.$u.test.isEmpty(this.value)) {
      this.fileList = this.value.split(',').map(item => {
        return {
          url: item
        }
      })
    }
  },
  methods: {
    handleInput(value) {
      this.fileList = value
      const list = value.map(item => {
        return item.url
      })
      this.$emit('input', list.join(','))
    },
    // 删除上传图片
    handleRemove(file, fileList) {
      this.handleInput(fileList)
    },
    // 点击文件列表中已上传的文件时的钩子
    handlePreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    // 上传成功回调
    handleImageSuccess(response, file, fileList) {
      if (response.code !== 20000) {
        this.$u.toast.error('上传失败,请重新上传')
      } else {
        if (!this.multiple) {
          fileList = []
          fileList.push({
            uid: new Date().getTime(),
            url: response.data
          })
        } else {
          file.url = response.data
        }
        this.handleInput(fileList)
      }
    },
    // 上传失败回调
    handleImageError() {
      this.$u.toast.error('上传失败,请重新上传')
      return false
    },
    // 上传文件之前处理
    handleBeforeUpload(file) {
      if (['image/jpeg', 'image/jpg', 'image/png'].indexOf(file.type) === -1) {
        this.$u.toast.error('请上传jpg, png格式的图片')
        return false
      }
      if (file.size / 1024 / 1024 > this.size) {
        this.$u.toast('上传图片大小不能超过' + this.size + 'MB', 'error')
        return false
      }
    }
  }
}
</script>

<style lang="scss" scoped>

  .upload{
    ::v-deep{
      .el-upload {
        background-color: #fbfdff;
        border: 1px dashed #c0ccda;
        border-radius: 6px;
        box-sizing: border-box;
        width: 120px;
        max-height: 120px;
        cursor: pointer;
        line-height: 120px;
        vertical-align: top;
      }
      .el-upload-list--picture-card .el-upload-list__item {
        overflow: hidden;
        background-color: #fff;
        border: 1px dashed #c0ccda;
        border-radius: 6px;
        box-sizing: border-box;
        width: 120px;
        max-height: 120px;
        margin: 0 8px 8px 0;
        display: inline-block;
      }
    }

    .uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 120px;
      height: 120px;
      line-height: 120px;
      text-align: center;
    }
    .image-preview {
      width: 100%;
      height: 100%;
      display: block;
      border-radius: 6px;
    }
    .tips{
      margin-top: 8px;
      font-size: 13px;
      line-height: 18px;
      color: #909399;
    }
  }

</style>
