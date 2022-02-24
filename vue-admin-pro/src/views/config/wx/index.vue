<template>
  <div class="app-container">

    <u-page title="微信设置">
      <el-row class="el-pagination-container ">

        <el-alert
          title="微信提醒配置说明"
          type="success"
          description="参数设置：公网可访问地址，[系统] - [参数配置] - - [参数列表] - 修改 AppDomain 配置项。"
          :closable="false"
        />

        <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="150px" style="margin-top: 20px;">

          <el-form-item label="服务器地址">
            <el-tag>{{ domain ? domain : 'http://127.0.0.1' }}/wx/api/{{ dataForm.id }}</el-tag>
          </el-form-item>

          <el-form-item label="公众号名称" prop="appname">
            <el-input v-model="dataForm.appname" placeholder="公众号名称" clearable />
          </el-form-item>

          <el-form-item label="原始ID" prop="ghid">
            <el-input v-model="dataForm.ghid" placeholder="原始ID" clearable />
          </el-form-item>

          <el-form-item label="AppId" prop="appid">
            <el-input v-model="dataForm.appid" placeholder="公众号/应用号AppId" clearable />
          </el-form-item>

          <el-form-item label="AppSecret" prop="appsecret">
            <el-input v-model="dataForm.appsecret" placeholder="公众号/应用号AppSecret" clearable show-password />
          </el-form-item>

          <el-form-item label="Token" prop="token">
            <el-input v-model="dataForm.token" placeholder="Token" clearable />
          </el-form-item>

          <el-form-item label="EncodingAESKey" prop="encodingAESKey">
            <el-input v-model="dataForm.encodingAESKey" placeholder="EncodingAESKey" clearable />
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
      api: this.$u.api.ConfigWx,
      domain: this.domain,
      dataForm: {},
      rules: {
        appname: [
          { required: true, message: '请输入公众号名称', trigger: ['blur'] }
        ],
        ghid: [
          { required: true, message: '请输入公众号原始ID', trigger: ['blur'] }
        ],
        appid: [
          { required: true, message: '请输入AppId', trigger: ['blur'] }
        ],
        appsecret: [
          { required: true, message: '请输入AppSecret', trigger: ['blur'] }
        ],
        token: [
          { required: true, message: '请输入token', trigger: ['blur'] }
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
      self.$confirm('确认更改微信配置', '提示', {
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
