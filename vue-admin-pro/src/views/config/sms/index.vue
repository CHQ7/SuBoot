<template>
  <div class="app-container">

    <u-page title="短信设置">
      <el-row class="el-pagination-container ">
        <el-alert
          title="短信提醒配置说明"
          type="success"
          description="参数设置：在此页面开启所需的服务商API并设置服务商提供的参数。"
          :closable="false"
        />
        <div style="margin-top: 20px">
          <el-radio-group v-model="radio">
            <el-radio label="aliyun" border>阿里云短信</el-radio>
          </el-radio-group>
        </div>
        <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="150px" style="margin-top: 20px;">
          <el-form-item label="AccessKey ID" prop="accessKey">
            <el-input v-model="dataForm.accessKey" placeholder="阿里云短信KEY" clearable />
          </el-form-item>
          <el-form-item label="AccessKey Secret" prop="accessKeySecret">
            <el-input v-model="dataForm.accessKeySecret" placeholder="阿里云短信SECRET" clearable show-password />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateData">提交</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </u-page>
  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.ConfigSms,
      radio: 'aliyun',
      dataForm: {},
      rules: {
        accessKey: [
          { required: true, message: '请填写阿里云短信AccessKey ID', trigger: ['blur'] }
        ],
        accessKeySecret: [
          { required: true, message: '请填写阿里云短信Access Key Secret', trigger: ['blur'] }
        ]
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
      this.$confirm('确认更改阿里云短信配置 ', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        self.$refs['dialogForm'].validate((valid) => {
          if (valid) {
            this.api.update(self.dataForm).then(() => {
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
