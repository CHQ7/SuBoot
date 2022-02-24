<template>
  <div class="app-container">

    <u-page title="基础设置">
      <div slot="header">
        <el-button type="primary" @click="updateData">保存</el-button>
      </div>

      <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="120px">
        <el-tabs v-model="active">

          <el-tab-pane label="搜索设置" name="first">

            <el-form-item label="搜索热词" prop="hotWord">
              <el-input v-model="dataForm.hotWord" placeholder="一行一个搜索词" clearable type="textarea" :rows="6" />
            </el-form-item>

          </el-tab-pane>
          <el-tab-pane label="绑定短信模板" name="second">

            <el-form-item label="短信验证码">
              <el-select v-model="dataForm.captcha" clearable placeholder="从短信消息库中选择">
                <el-option
                  v-for="item in telData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
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
      api: this.$u.api.ConfigBasic,
      active: 'first',
      dataForm: {},
      rules: {
      },
      telData: []
    }
  },
  created() {
    this.initTemplate()
    this.getLoad()
  },
  methods: {
    // 初始化数据
    getLoad() {
      this.api.fetch().then(res => {
        this.dataForm = res.data
      })
    },
    // 初始化短信模板数据源
    initTemplate() {
      this.api.template().then(res => {
        this.telData = res.data
      })
    },
    // 提交编辑数据
    updateData() {
      const self = this
      this.$confirm('确认更改平台基础配置 ', '提示', {
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

