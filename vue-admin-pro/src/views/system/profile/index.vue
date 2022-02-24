<template>
  <div class="app-container">
    <el-row>
      <el-col :span="14">
        <el-card>
          <el-tabs>
            <el-tab-pane label="更改密码">
              <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="80px" style="margin-top: 20px;">
                <el-form-item label="旧密码" prop="oldPassword">
                  <el-input v-model="dataForm.oldPassword" placeholder="请输入旧密码" clearable show-password />
                </el-form-item>
                <el-form-item label="新密码" prop="newPassword">
                  <el-input v-model="dataForm.newPassword" placeholder="请输入新密码" clearable show-password />
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                  <el-input v-model="dataForm.confirmPassword" placeholder="确认密码" clearable show-password />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateData">提交</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>
<script>

export default {
  data() {
    // 两次输入密码要一致
    const validatePass = (rule, value, callback) => {
      if (value !== this.dataForm.newPassword) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    return {
      api: this.$u.api.SystemUser,
      dataForm: {},
      rules: {
        oldPassword: [
          { required: true, message: '请填写旧密码', trigger: ['blur'] }
        ],
        newPassword: [
          { required: true, message: '请填写新密码', trigger: ['blur'] },
          { min: 6, max: 20, message: '密码长度必须是6-20位', trigger: ['blur', 'change'] }
        ],
        confirmPassword: [
          { required: true, message: '请填写确认密码', trigger: ['blur'] },
          { validator: validatePass, trigger: ['blur', 'change'] }
        ]
      }
    }
  },
  methods: {
    // 提交编辑数据
    updateData() {
      const self = this
      self.$confirm('确认更改密码 ', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.$refs['dialogForm'].validate((valid) => {
          if (valid) {
            self.api.changePassword(self.dataForm).then(() => {
              self.$u.msg('更改成功')
              self.dataForm = {}
            })
          }
        })
      }).catch(() => {
      })
    }
  }
}
</script>

