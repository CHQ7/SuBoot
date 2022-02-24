<template>
  <div class="app-container">

    <u-page title="商户设置">
      <div slot="header">
        <el-button type="primary" @click="updateData">保存</el-button>
      </div>

      <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="80px">
        <el-tabs v-model="active">
          <el-tab-pane label="入驻流程" name="first">
            <el-form-item label="入驻流程" prop="step">
              <u-tinymce v-model="dataForm.step" :height="300" />
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="入驻标准" name="second">
            <el-form-item label="入驻标准" prop="standard">
              <u-tinymce v-model="dataForm.standard" :height="300" />
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="入驻资格" name="third">
            <el-form-item label="入驻资格" prop="qualification">
              <u-tinymce v-model="dataForm.qualification" :height="300" />
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="协议/政策" name="fourth">
            <el-form-item label="入驻协议" prop="policy">
              <u-tinymce v-model="dataForm.policy" :height="300" />
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>
    </u-page>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.MerchConfig,
      active: 'first',
      dataForm: {},
      rules: {
      }
    }
  },
  created() {
    this.getLoad()
  },
  methods: {
    // 初始化数据
    getLoad() {
      this.api.fetch().then(res => {
        this.dataForm = res.data
      })
    },
    // 提交编辑数据
    updateData() {
      const self = this
      this.$confirm('确认更改平台配置 ', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        self.$refs['dialogForm'].validate((valid) => {
          if (valid) {
            self.api.update(self.dataForm).then(() => {
              self.getLoad()
              self.$u.msg('保存成功')
            })
          }
        })
      }).catch(() => {
      })
    }
  }

}
</script>

