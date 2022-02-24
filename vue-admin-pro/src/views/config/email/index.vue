<template>
  <div class="app-container">

    <u-page title="邮件设置">
      <el-row class="el-pagination-container ">

        <el-alert
          title="短信提醒配置说明"
          type="success"
          description="参数设置：在此页面开启所需的参数,需要服务商提供的参数。"
          :closable="false"
        />

        <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="150px" style="margin-top: 20px;">
          <el-form-item label="SMTP邮箱服务器" prop="hostname">
            <el-input v-model="dataForm.hostname" placeholder="SMTP邮箱服务器" clearable />
          </el-form-item>
          <el-form-item label="端口" prop="smtpport">
            <el-input v-model="dataForm.smtpport" placeholder="端口" clearable />
          </el-form-item>
          <el-form-item label="邮箱账号" prop="username">
            <el-input v-model="dataForm.username" placeholder="邮箱账号" clearable />
          </el-form-item>
          <el-form-item label="授权密码" prop="password">
            <el-input v-model="dataForm.password" placeholder="登录密码/授权密码" clearable show-password />
          </el-form-item>
          <el-form-item label="SSL" prop="sslonconnect">
            <el-switch v-model="dataForm.sslonconnect" />
          </el-form-item>
          <el-form-item label="邮箱昵称" prop="fromname">
            <el-input v-model="dataForm.fromname" placeholder="邮箱昵称" clearable />
          </el-form-item>
          <el-form-item label="邮件编码" prop="charset">
            <el-input v-model="dataForm.charset" placeholder="默认UTF-8" clearable />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="updateData">提交</el-button>
            <el-button @click="handleTest">提交后在发送测试邮件</el-button>
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
      api: this.$u.api.ConfigEmail,
      radio: 'aliyun',
      dataForm: {},
      rules: {
        hostname: [
          { required: true, message: '请填写SMTP邮箱服务器', trigger: ['blur'] }
        ],
        smtpport: [
          { required: true, message: '请填写邮箱服务器端口', trigger: ['blur'] }
        ],
        username: [
          { required: true, message: '请填写邮箱账号', trigger: ['blur'] }
        ],
        password: [
          { required: true, message: '请填写邮箱登录密码或授权密码', trigger: ['blur'] }
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
      this.$confirm('确认更改邮箱配置', '提示', {
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
    },
    // 提交测试邮件
    handleTest() {
      this.$prompt('请输入接收者邮箱', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        this.api.test({ value }).then(() => {
          this.getLoad()
          this.$u.msg('发送成功')
        })
      }).catch(() => {
      })
    }
  }
}
</script>
