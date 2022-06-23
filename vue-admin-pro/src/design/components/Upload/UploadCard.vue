<template>
  <div class="upload">
    <el-upload
      class="uploader"
      :action="uploadUrl"
      :name="name"
      :accept="accept"
      list-type="picture-card"
      :before-upload="handleBeforeUpload"
      :on-success="handleImageSuccess"
      :on-error="handleImageError"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
    >
      <i class="el-icon-plus" />
    </el-upload>
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
  name: 'UploadCard',
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
      // 上传地址
      uploadUrl: this.domain + '/nt/upload/image',
      // 弹框地址
      dialogImageUrl: '',
      // 弹框显示
      dialogVisible: false,
      fileList: []
    }
  },
  created() {
    if (this.value !== '') {
      this.fileList = this.value.split(',').map(item => {
        const obj = {}
        obj.url = item
        return obj
      })
    }
  },
  methods: {
    handleInput(fileList) {
      const list = fileList.map((item, index) => {
        return item.url
      })
      this.$emit('input', list.join(','))
    },
    // 上传成功回调
    handleImageSuccess(response, file, fileList) {
      if (response.code !== 20000) {
        this.$message.error('上传失败,请重新上传')
      } else {
        file.url = response.data
        this.handleInput(fileList)
      }
    },
    // 删除上传图片
    handleRemove(file, fileList) {
      this.handleInput(fileList)
    },
    // 上传失败回调
    handleImageError() {
      this.$message.error('上传失败,请重新上传')
      return false
    },
    // 上传文件之前处理
    handleBeforeUpload(file) {
      if (['image/jpeg', 'image/jpg', 'image/png'].indexOf(file.type) === -1) {
        this.$message.error('请上传jpg, png格式的图片')
        return false
      }
      if (file.size / 1024 / 1024 > this.size) {
        this.$message.error('上传图片大小不能超过' + this.size + 'MB')
        return false
      }
    },
    // 点击文件列表中已上传的文件时的钩子
    handlePreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    }
  }
}
</script>

<style lang="scss" scoped>
  .upload >>> .el-dialog__header {
    background-color: initial;
  }
  .upload >>> .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .upload{
    .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .uploader .el-upload:hover {
      border-color: #409EFF;
    }
    .uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 148px;
      height: 148px;
      line-height: 148px;
      text-align: center;
    }
    .image-preview {
      width: 148px;
      max-height: 148px;
      display: block;
    }
  }

</style>
