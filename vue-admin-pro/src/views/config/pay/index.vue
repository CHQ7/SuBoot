<template>
  <div class="app-container">

    <u-page title="支付设置">
      <div slot="header">
        <el-button type="primary" @click="updateData">保存</el-button>
      </div>

      <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="100px">

        <el-card shadow="never" class="panel">
          <div class="panel-body">
            <div class="panel-header">
              <h4>微信支付</h4>
              <span> 开启后在平台中可以调起微信进行支付</span>
            </div>
            <div>
              <el-switch v-model="dataForm.weixin" class="panel-switch" />
            </div>
          </div>

          <div v-if="dataForm.weixin" style="margin-top: 20px;">

            <el-form-item label="APPID" prop="appid">
              <el-input v-model="dataForm.appid" placeholder="公众号或应用APPID" clearable />
            </el-form-item>

            <el-form-item label="商户ID" prop="mchId">
              <el-input v-model="dataForm.mchId" placeholder="微信商户号" clearable />
            </el-form-item>

            <el-form-item label="商户密钥" prop="apikey">
              <el-input v-model="dataForm.apikey" placeholder="微信商户API密钥" clearable />
            </el-form-item>
          </div>

        </el-card>

        <el-card shadow="never" class="panel">
          <div class="panel-body">
            <div class="panel-header">
              <h4>支付宝</h4>
              <span> 开启后在平台中可以调起微信进行支付</span>
            </div>
            <div>
              <el-switch v-model="dataForm.alipay" class="panel-switch" />
            </div>
          </div>
          <div v-if="dataForm.alipay" style="margin-top: 20px;">

            <el-form-item label="APPID" prop="aliAppid">
              <el-input v-model="dataForm.aliAppid" placeholder="开放平台应用id" clearable />
            </el-form-item>

            <el-form-item label="验签方式">
              RSA2
            </el-form-item>

            <el-form-item label="应用私钥" prop="aliPrivateKey">
              <el-input v-model="dataForm.aliPrivateKey" placeholder="应用私钥(PRIVATE_KEY),一行且不能有空格" clearable type="textarea" :rows="12" />
            </el-form-item>

            <el-form-item label="支付宝公钥" prop="aliPublicKey">
              <el-input v-model="dataForm.aliPublicKey" placeholder="支付宝公钥(PUBLIC_KEY),一行且不能有空格" clearable type="textarea" :rows="6" />
            </el-form-item>

          </div>
        </el-card>

        <el-card shadow="never" class="panel">
          <div class="panel-body">
            <div class="panel-header">
              <h4>余额支付</h4>
              <span>开启后，会员可以用账户余额去平台消费.</span>
            </div>
            <div>
              <el-switch v-model="dataForm.credit" class="panel-switch" />
            </div>
          </div>
        </el-card>

        <el-card shadow="never" class="panel">
          <div class="panel-body">
            <div class="panel-header">
              <h4>货到付款</h4>
              <span>如果要支持货到付款,也需要将商品设置成货到付款.</span>
            </div>
            <div>
              <el-switch v-model="dataForm.cash" class="panel-switch" />
            </div>
          </div>
        </el-card>

      </el-form>

    </u-page>
  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.ConfigPay,
      dataForm: {},
      value: false,
      rules: {
        appid: [
          { required: true, message: '请输入公众号或应用APPID', trigger: ['blur'] }
        ],
        mchId: [
          { required: true, message: '请输入微信商户号', trigger: ['blur'] }
        ],
        apikey: [
          { required: true, message: '请输入微信商户API密钥', trigger: ['blur'] }
        ],
        aliAppid: [
          { required: true, message: '请输入支付宝开放平台应用ID', trigger: ['blur'] }
        ],
        aliPrivateKey: [
          { required: true, message: '请输入支付宝应用私钥', trigger: ['blur'] }
        ],
        aliPublicKey: [
          { required: true, message: '请输入支付宝公钥', trigger: ['blur'] }
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
      this.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          this.api.update(this.dataForm).then(() => {
            this.getLoad()
            this.$u.msg('保存成功')
          })
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>

  .panel{
    margin-bottom: 20px;
    .panel-body{
      display:flex;
      justify-content:space-between;

      .panel-header{
        display: flex;
        flex-flow: column;
        color: #999;
        font-size: 12px;

        h4{
          font-size: 14px;
          color: #000;
        }
      }

      .panel-switch{
        margin-top: 20px;
      }
    }
  }

</style>
