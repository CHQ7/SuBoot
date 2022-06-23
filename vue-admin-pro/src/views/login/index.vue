<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">CSMS管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <u-icon-svg name="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="账号"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="大写锁定打开" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <u-icon-svg name="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="密码"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />

          <span class="show-pwd" @click="showPwd">
            <u-icon-svg :name="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>
      </el-tooltip>
      <el-button :loading="loading" class="submit" size="medium" @click.native.prevent="handleLogin">登 录</el-button>
    </el-form>

    <el-dialog
      :title="captchaTitle"
      :visible.sync="captchaVisible"
      append-to-body
      width="350px"
    >
      <el-form ref="captchaForm" :model="captchaForm" :rules="codeRules">
        <el-row class="captcha_row">
          <el-form-item prop="verifycode">
            <el-input
              v-model="captchaForm.verifycode"
              maxlength="5"
              placeholder="验证码"
              @keyup.enter.native="handleCode"
            />
          </el-form-item>
          <img id="captcha_img" :src="codeUrl" @click="changeCode">
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeCaptchaForm">取 消</el-button>
        <el-button type="primary" @click="handleCode">确 定</el-button>
      </span>
    </el-dialog>
    <u-footer :copyright="copyright" />
  </div>
</template>

<script>

export default {
  name: 'Login',
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码不能少于6位'))
      } else {
        callback()
      }
    }
    return {
      api: this.$u.api.open,
      copyright: ' Copyright © 2022 CSMS.',
      loginForm: {
        username: '',
        password: '',
        captcha: ''
      },
      loginRules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      redirect: undefined,
      otherQuery: {},

      // 默认 隐藏验证码
      captchaForm: {
        verifycode: ''
      },
      codeRules: {
        verifycode: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      },
      codeUrl: this.$u.api.open.captcha,
      captchaTitle: '请输入验证码',
      captchaVisible: false,
      name: ''
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  mounted() {
    // 开发环境显示默认账号密码
    if (process.env.NODE_ENV === 'development') {
      this.loginForm = {
        username: 'superadmin',
        password: '000000'
      }
    }
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  methods: {
    // 监听回车事件
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    // 显示或隐藏密码
    showPwd() {
      this.passwordType = this.passwordType === 'password' ? '' : 'password'
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    // 登录事件
    handleLogin() {
      // 获取验证码
      this.loginForm.captcha = this.captchaForm.verifycode
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm)
            .then(() => {
              this.$u.route(this.redirect || '/', this.otherQuery)
              this.loading = false
            })
            .catch((err) => {
              this.loading = false
              if (err.code === 50002 || err.code === 50003) {
                this.openCaptchaForm()
              } else {
                this.closeCaptchaForm()
              }
            })
        } else {
          return false
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    },
    // 打开验证码提示
    openCaptchaForm() {
      this.captchaVisible = true
      this.changeCode()
    },
    // 关闭验证码提示
    closeCaptchaForm() {
      this.captchaVisible = false
    },
    // 转换码
    changeCode() {
      this.captchaForm.verifycode = ''
      this.codeUrl = this.api.captcha + '?_=' + new Date().getTime()
    },
    // 提交验证码
    handleCode() {
      this.$refs['captchaForm'].validate(valid => {
        if (valid) {
          this.handleLogin()
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */

$bg:#283443;
$light_gray:#707070;
$cursor: #fff;
$bg_itme:#fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px #fff inset !important;
        -webkit-text-fill-color: #707070 !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: $bg_itme;
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#f0f2f5;
$dark_gray:#889aa4;
$light_gray:#515a6e;

.login-container {
  min-height: 100%;
  width: 100%;
  background: $bg url("../../assets/login_images/login-bg.jpg") no-repeat fixed;
  background-size: cover;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .submit{
    width:100%;
    padding: 15px 20px;
    margin-bottom:30px;
    color: #fff;
    background-color: #4B70E2;
    border-color: #4B70E2;
    box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  }
  .submit:hover{
    color:#fff;
    background:#5d7de2;
    border-color:#5d7de2;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
      text-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }

}

.captcha_row {
  float: left;
  margin: 10px;
  bottom: 20px;

  div {
    width: 120px;
    float: left;
  }

  img {
    width: 120px;
    margin-left: 5px;
    float: left;
  }

  #captcha_img {
    height: 32px;
    cursor: pointer;
  }
}

</style>
