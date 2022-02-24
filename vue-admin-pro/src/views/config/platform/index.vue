<template>
  <div class="app-container">

    <u-page title="平台设置">
      <div slot="header">
        <el-button type="primary" @click="updateData">保存</el-button>
      </div>

      <el-form ref="dialogForm" :model="dataForm" :rules="rules" label-width="130px">
        <el-tabs v-model="active">

          <el-tab-pane label="基本设置" name="first">

            <el-form-item label="平台名称" prop="name">
              <el-input v-model="dataForm.name" placeholder="站点名称" maxlength="250" show-word-limit clearable />
            </el-form-item>

            <el-form-item label="域名地址" prop="domain">
              <el-input v-model="dataForm.domain" placeholder="域名地址" clearable />
            </el-form-item>

            <el-form-item label="备案号" prop="icp">
              <el-input v-model="dataForm.icp" placeholder="备案号" clearable />
            </el-form-item>

            <el-form-item prop="logo" label="LOGO">
              <u-upload v-model="dataForm.logo" name="file" />
            </el-form-item>

            <el-form-item v-if="roles[0] === 'root'" prop="cnzz" label="CNZZ地址">
              <el-input v-model="dataForm.customData.cnzz" placeholder="例如:http://xxx.com" clearable />
            </el-form-item>

            <el-form-item v-if="roles[0] === 'root'" prop="statistic" label="后台首页统计显示">
              <el-radio-group v-model="dataForm.customData.statistic">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="脚本代码" prop="script">
              <el-input v-model="dataForm.script" placeholder="脚本代码" type="textarea" :rows="3" show-word-limit clearable />
            </el-form-item>

            <el-form-item label="底部版权" prop="footer">
              <u-tinymce v-model="dataForm.footer" :height="300" />
            </el-form-item>

          </el-tab-pane>
          <el-tab-pane label="联系方式" name="second">

            <el-form-item label="平台电话" prop="mobile">
              <el-input v-model="dataForm.mobile" placeholder="平台电话" clearable />
            </el-form-item>

            <el-form-item label="平台邮箱" prop="email">
              <el-input v-model="dataForm.email" placeholder="平台邮箱" clearable />
            </el-form-item>

            <el-form-item label="平台QQ" prop="qq">
              <el-input v-model="dataForm.qq" placeholder="平台QQ" clearable />
            </el-form-item>

            <el-form-item label="平台地址" prop="address">
              <el-input v-model="dataForm.address" placeholder="平台地址" clearable />
            </el-form-item>

            <el-form-item label="平台二维码" prop="qrcode">
              <u-upload v-model="dataForm.qrcode" name="file" />
            </el-form-item>

          </el-tab-pane>
          <el-tab-pane label="SEO优化" name="third">

            <el-form-item label="SEO关键词" prop="keywords" style="margin-top: 20px;">
              <el-input v-model="dataForm.keywords" placeholder="SEO关键词" maxlength="200" show-word-limit clearable />
            </el-form-item>

            <el-form-item label="SEO描述" prop="description">
              <el-input v-model="dataForm.description" placeholder="SEO描述" type="textarea" maxlength="300" :rows="3" show-word-limit clearable />
            </el-form-item>

          </el-tab-pane>
          <el-tab-pane label="协议/政策" name="fourth">

            <el-form-item label="用户协议" prop="agreement" style="margin-top: 20px;">
              <u-tinymce v-model="dataForm.agreement" :height="300" />
            </el-form-item>

            <el-form-item label="隐私协议" prop="policy">
              <u-tinymce v-model="dataForm.policy" :height="300" />
            </el-form-item>

          </el-tab-pane>
          <el-tab-pane label="平台状态" name="fifth">

            <el-form-item label="平台状态" prop="disabled" style="margin-top: 20px;">
              <el-radio-group v-model="dataForm.disabled">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="关闭原因" prop="note">
              <el-input v-model="dataForm.note" placeholder="关闭原因" type="textarea" maxlength="200" :rows="3" show-word-limit clearable />
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane label="视频" name="sixth">

            <el-form-item label="是否显示">
              <el-radio-group v-model="video.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="视频封面">
              <u-upload v-model="video.poster" name="file" />
            </el-form-item>

            <el-form-item label="视频地址">
              <el-input v-model="video.url" clearable maxlength="300" show-word-limit />
            </el-form-item>

            <el-form-item label="上传视频">
              <u-upload-file v-model="video.url" name="file" accept=".mp4" :size="200" />
            </el-form-item>

            <el-form-item label="弹出方向">
              <el-select v-model="video.mode" filterable clearable placeholder="请选择视频弹出方向">
                <el-option v-for="item in optionsVideoMode" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>

            <el-form-item label="自动播发">
              <el-radio-group v-model="video.autoplay">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane label="音频" name="eighth">

            <el-form-item label="是否显示" prop="audioShow">
              <el-radio-group v-model="audio.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="音频地址">
              <el-input v-model="audio.url" clearable maxlength="300" show-word-limit />
            </el-form-item>

            <el-form-item label="上传音频">
              <u-upload-file v-model="audio.url" name="file" :size="50" accept=".mp3" />
            </el-form-item>

            <el-form-item label="自动播发">
              <el-radio-group v-model="audio.autoplay">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="循环播发">
              <el-radio-group v-model="audio.loop">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane v-if="roles[0] === 'root'" label="商家入驻缴费" name="seventh">

            <el-form-item label="支付风险">
              <el-radio-group v-model="risk.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="二维码">
              <u-upload v-model="risk.qrcode" name="file" />
            </el-form-item>

            <el-form-item label="风险提示内容">
              <el-input v-model="risk.content" placeholder="关闭原因" type="textarea" maxlength="200" :rows="3" show-word-limit clearable />
            </el-form-item>
          </el-tab-pane>

          <el-tab-pane v-if="roles[0] === 'root'" label="电脑端" name="ninth">
            <el-form-item label="PC商城" prop="PcShop">
              <el-radio-group v-model="dataForm.customData.PcShop">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="PC联盟" prop="show">
              <el-radio-group v-model="PcUniondata.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="PC联盟预览地址" prop="Previewurl">
              <el-input v-model="PcUniondata.Previewurl" placeholder="手机商城预览地址" clearable />
            </el-form-item>

            <el-form-item label="PC联盟管理地址" prop="AdministerUrl">
              <el-input v-model="PcUniondata.AdministerUrl" placeholder="手机商城管理地址" clearable />
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane v-if="roles[0] === 'root'" label="手机端" name="tenth">

            <el-form-item label="手机商城" prop="show">
              <el-radio-group v-model="PhoneShopdata.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="手机商城预览地址" prop="Previewurl">
              <el-input v-model="PhoneShopdata.Previewurl" placeholder="手机商城预览地址" clearable />
            </el-form-item>

            <el-form-item label="手机商城管理地址" prop="PhoneShopAdminister">
              <el-input v-model="PhoneShopdata.AdministerUrl" placeholder="手机商城管理地址" clearable />
            </el-form-item>

            <el-form-item label="小程序商城" prop="wxapp">
              <el-radio-group v-model="WxAppdata.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="小程序商城预览地址" prop="WxAppPreview">
              <u-upload v-model="WxAppdata.Previewurl" name="file" />
            </el-form-item>

            <el-form-item label="小程序商城管理地址" prop="WxAppAdminister">
              <el-input v-model="WxAppdata.AdministerUrl" placeholder="小程序商城管理地址" clearable />
            </el-form-item>

            <el-form-item label="微信公众号">
              <el-radio-group v-model="Wxdata.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="小程序商城预览地址">
              <u-upload v-model="Wxdata.Previewurl" name="file" />
            </el-form-item>

            <el-form-item label="小程序商城管理地址" prop="WxAppAdminister">
              <el-input v-model="Wxdata.AdministerUrl" placeholder="小程序商城管理地址" clearable />
            </el-form-item>

            <el-form-item label="手机联盟" prop="PhoneUnion">
              <el-radio-group v-model="PhoneUniondata.show">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="手机联盟预览地址" prop="PhoneUnionPreview">
              <el-input v-model="PhoneUniondata.Previewurl" placeholder="手机商城预览地址" clearable />
            </el-form-item>

            <el-form-item label="手机联盟管理地址" prop="PhoneUnionAdminister">
              <el-input v-model="PhoneUniondata.AdministerUrl" placeholder="手机商城管理地址" clearable />
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane v-if="roles[0] === 'root'" label="商品上传是否提示" name="ten">

            <el-form-item label="平台状态" prop="goodsTips" style="margin-top: 20px;">
              <el-radio-group v-model="dataForm.customData.goodsTips">
                <el-radio :label="true">开启</el-radio>
                <el-radio :label="false">关闭</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="提示内容" prop="goodsTipsContent">
              <el-input v-model="dataForm.customData.goodsTipsContent" placeholder="提示内容" type="textarea" maxlength="200" :rows="3" show-word-limit clearable />
            </el-form-item>
          </el-tab-pane>

        </el-tabs>
      </el-form>
    </u-page>

  </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      api: this.$u.api.ConfigPlatform,
      active: 'first',
      // 视频
      video: {
        url: '',
        poster: '',
        mode: '',
        autoplay: false,
        show: false
      },
      // 音频
      audio: {
        url: '',
        loop: false,
        autoplay: false,
        show: false
      },
      // 风险
      risk: {
        show: false,
        qrcode: '',
        content: ''
      },
      // PC联盟
      PcUniondata: {
        show: false,
        Previewurl: '',
        AdministerUrl: ''
      },
      // 手机商城
      PhoneShopdata: {
        show: false,
        Previewurl: '',
        AdministerUrl: ''
      },
      // 小程序商城
      WxAppdata: {
        show: false,
        Previewurl: '',
        AdministerUrl: ''
      },
      // 微信公众号
      Wxdata: {
        show: false,
        Previewurl: '',
        AdministerUrl: ''
      },
      // 手机联盟
      PhoneUniondata: {
        show: false,
        Previewurl: '',
        AdministerUrl: ''
      },
      dataForm: {
        customData: {
          PcShop: true,
          goodsTips: false,
          statistic: true
        }
      },
      rules: {
        name: [
          { required: true, message: '请输入平台名称', trigger: ['blur'] }
        ]
      },
      optionsVideoMode: [{
        value: 'rightTop',
        label: '右上'
      }, {
        value: 'rightBottom',
        label: '右下'
      }, {
        value: 'leftTop',
        label: '左上'
      }, {
        value: 'leftBottom',
        label: '左下'
      }]
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    this.getLoad()
  },
  methods: {
    // 初始化数据
    getLoad() {
      this.api.fetch().then(res => {
        this.dataForm = {}
        this.dataForm = res.data
        const { video, audio, customData } = this.dataForm
        // 回显视频数据
        if (video) {
          this.video = JSON.parse(video)
        }
        // 回显音频数据
        if (audio) {
          this.audio = JSON.parse(audio)
        }
        if (customData) {
          // 字符串转换对象
          const custom = this.dataForm.customData = JSON.parse(customData)
          // 后台统计显示
          this.dataForm.customData.statistic = custom.statistic !== false
          // pc商城
          this.dataForm.customData.PcShop = true
          // 初始化对象
          if (custom.risk) {
            // 风险对象
            this.risk = custom.risk
          }
          if (custom.PcUniondata) {
            // PC联盟
            this.PcUniondata = custom.PcUniondata
          }
          if (custom.PhoneShopdata) {
            // 手机商城
            this.PhoneShopdata = custom.PhoneShopdata
          }
          if (custom.WxAppdata) {
            // 小程序商城
            this.WxAppdata = custom.WxAppdata
          }
          if (custom.Wxdata) {
            // 微信公众号
            this.Wxdata = custom.Wxdata
          }
          if (custom.PhoneUniondata) {
            // 手机联盟
            this.PhoneUniondata = custom.PhoneUniondata
          }

          if (!custom.goodsTips) {
            // 商品上传提示
            this.dataForm.customData.goodsTips = false
          }
        } else {
          this.dataForm.customData = { statistic: true, PcShop: true }
        }
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
            const row = this.$u.deepClone(self.dataForm)
            // 保存视频对象
            row.video = JSON.stringify(this.video)
            // 保存音频对象
            row.audio = JSON.stringify(this.audio)
            // 自定义数据对象
            row.customData = {
              statistic: row.customData.statistic,
              cnzz: row.customData.cnzz,
              // pc商城
              PcShop: true,
              // 风险对象
              risk: this.risk,
              // PC联盟
              PcUniondata: this.PcUniondata,
              // 手机商城
              PhoneShopdata: this.PhoneShopdata,
              // 小程序商城
              WxAppdata: this.WxAppdata,
              // 微信公众号
              Wxdata: this.Wxdata,
              // 手机联盟
              PhoneUniondata: this.PhoneUniondata
            }
            // 转换字符串保存
            row.customData = JSON.stringify(row.customData)
            self.api.update(row).then(() => {
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

