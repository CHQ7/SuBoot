<template>
  <div class="app-container">

    <u-page title="商户列表">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-input v-model="listQuery.keyword" placeholder="查询商户名称/联系人/联系电话" style="width: 260px;" clearable @keyup.enter.native="hdlFilter" />
        <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
      </u-filtered>

      <u-table
        :data="list"
        :loading="listLoading"
        :columns="columns"
        :pagination.sync="listQuery"
        :fetch="hdlList"
        @selection-change="hdlSelectChange"
      />

    </u-page>

    <!-- 创建商户 -->
    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="hdlCreate">

      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="110px">

        <el-form-item prop="username" label="商户账号">
          <el-input v-model="dataForm.username" placeholder="商户账号" clearable show-word-limit />
        </el-form-item>

        <el-form-item prop="password" label="商户密码" class="item">
          <el-input v-model="dataForm.password" type="password" placeholder="商户密码" show-password clearable />
          <el-alert show-icon title="初始密码:888888" type="info" :closable="false" style="margin-left: 20px;padding: 0;background-color:initial" />
        </el-form-item>

        <el-form-item prop="name" label="商户名称">
          <el-input v-model="dataForm.name" placeholder="商户名称" clearable show-word-limit />
        </el-form-item>

        <el-form-item prop="saleCase" label="主营项目">
          <el-input v-model="dataForm.saleCase" placeholder="主营项目" clearable show-word-limit />
        </el-form-item>

        <el-form-item prop="realname" label="联系人">
          <el-input v-model="dataForm.realname" placeholder="联系人" clearable show-word-limit />
        </el-form-item>

        <el-form-item prop="mobile" label="联系电话">
          <el-input v-model="dataForm.mobile" placeholder="联系电话" clearable maxlength="11" show-word-limit />
        </el-form-item>

        <el-form-item prop="accountAt" label="到期时间">
          <el-date-picker
            v-model="dataForm.accountAt"
            type="date"
            placeholder="选择日期"
            value-format="timestamp"
          />
        </el-form-item>

      </el-form>
    </u-dialog>

    <!-- 商户详情 -->
    <u-dialog :title="detailTitle" :show.sync="dialogDetailVisible" @confirm="updateDetail">

      <el-form ref="detailForm" :rules="detailRules" :model="detailForm" label-width="100px">

        <el-tabs v-model="detailForm.tabsActive">
          <el-tab-pane label="基本信息" name="first">

            <el-form-item prop="name" label="商户名称" style="margin-top: 10px">
              <el-input v-model="detailForm.name" placeholder="商户名称" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="logo" label="商户logo">
              <u-upload v-model="detailForm.logo" name="file" />
            </el-form-item>

            <el-form-item prop="saleCase" label="主营项目">
              <el-input v-model="detailForm.saleCase" placeholder="主营项目" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="realname" label="联系人">
              <el-input v-model="detailForm.realname" placeholder="联系人" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="mobile" label="联系电话">
              <el-input v-model="detailForm.mobile" placeholder="联系电话" clearable maxlength="11" show-word-limit />
            </el-form-item>

            <el-form-item prop="descs" label="商户介绍">
              <el-input v-model="detailForm.descs" type="textarea" placeholder="商户介绍" />
            </el-form-item>

            <el-form-item prop="tel" label="客服电话">
              <el-input v-model="detailForm.tel" placeholder="客服电话" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="email" label="邮箱">
              <el-input v-model="detailForm.email" placeholder="邮箱" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="region" label="商户地区">
              <el-cascader
                ref="cascaderAddr"
                v-model="detailForm.region"
                size="large"
                :props="{value:'label',label:'label'}"
                :options="options"
              />
            </el-form-item>

            <el-form-item prop="address" label="详细地址">
              <el-input v-model="detailForm.address" placeholder="详细地址" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="note" label="备注">
              <el-input v-model="detailForm.note" type="textarea" placeholder="商户备注" />
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane label="账户信息" name="third">

            <el-form-item prop="mobile" label="商户账号" style="margin-top: 10px">
              <div v-if="detailUp.username">
                <span>{{ detailForm.account.username }}</span>
                <el-button type="text" @click="detailUp.username = false">修改</el-button>
              </div>
              <div v-else>
                <el-input v-model="detailForm.account.username" placeholder="账号" clearable maxlength="11" show-word-limit>
                  <el-button slot="append" icon="el-icon-check" @click="detailUp.username = true" />
                </el-input>
              </div>
            </el-form-item>

            <el-form-item prop="password" label="登录密码" class="item">
              <div v-if="detailUp.password">
                <span>{{ detailForm.account.password }}</span>
                <el-button type="text" @click="detailUp.password = false">修改</el-button>
                <el-alert show-icon title="密码留空则不修改" type="info" :closable="false" style="padding: 0;background-color:initial" />
              </div>
              <div v-else>
                <el-input v-model="detailForm.account.password" type="password" placeholder="登录密码" show-password clearable>
                  <el-button slot="append" icon="el-icon-check" @click="detailUp.password = true" />
                </el-input>
              </div>
            </el-form-item>

            <el-form-item prop="account" label="账号状态">
              <el-switch v-model="detailForm.account.disabled" />
            </el-form-item>
          </el-tab-pane>

          <el-tab-pane label="入驻状态" name="second">

            <el-form-item prop="accountAt" label="到期时间" style="margin-top: 10px">
              <el-date-picker
                v-model="detailForm.accountAt"
                type="date"
                placeholder="选择日期"
                value-format="timestamp"
              />
            </el-form-item>

            <el-form-item prop="flag" label="入驻状态">
              <template>
                <el-radio v-model="detailForm.flag" :label="0">审核中</el-radio>
                <el-radio v-model="detailForm.flag" :label="1">允许入驻</el-radio>
                <el-radio v-model="detailForm.flag" :label="2">暂停</el-radio>
              </template>
            </el-form-item>

          </el-tab-pane>
        </el-tabs>
      </el-form>
    </u-dialog>
  </div>
</template>

<script>
import region from '@/design/data/region'
export default {
  data() {
    return {
      api: this.$u.api.Merch,
      options: region,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '商户名称'
        },
        {
          prop: 'logo',
          label: '商户Logo',
          image: true
        },
        {
          prop: 'saleCase',
          label: '主营项目'
        },
        {
          prop: 'realname',
          label: '联系人'
        },
        {
          prop: 'accountAt',
          label: '到期时间',
          timestamp: true
        },
        {
          prop: 'flag',
          label: '入驻状态',
          render: (h, params) => {
            if (params.row.flag === 0) {
              return h('el-tag', { props: { type: 'warning' }}, '审核中')
            } else if (params.row.flag === 1) {
              return h('el-tag', { props: { type: 'success' }}, '允许入驻')
            } else if (params.row.flag === 2) {
              return h('el-tag', { props: { type: 'info' }}, '暂停')
            }
          }
        },
        {
          button: true,
          label: '操作',
          group: [
            {
              name: '编辑',
              click: row => {
                this.handleEdit(row)
              }
            },
            {
              name: '删除',
              click: row => {
                this.hdlDelete(row.id, row.name)
              }
            }
          ]
        }
      ],

      list: [],
      listLoading: true,
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        keyword: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑商户',
        create: '新增商户'
      },
      dataForm: {},
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: ['blur'] },
          { pattern: /^[0-9a-zA-Z]*$/, message: '账号以字母/数字组合' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: ['blur'] },
          { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入商户名称', trigger: ['blur'] }
        ],
        saleCase: [
          { required: true, message: '请输入商户主营项目', trigger: ['blur'] }
        ],
        realname: [
          { required: true, message: '请输入联系人姓名', trigger: ['blur'] }
        ],
        mobile: [
          { required: true, message: '联系人', trigger: ['blur', 'change'] },
          { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的联系人手机号码' }
        ],
        accountAt: [
          { required: true, message: '请选择到期时间', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      // 设置默认数据
      newForm: {
        password: '888888',
        flag: '0',
        accountAt: ''
      },

      // 详情
      detailTitle: '商户详情',
      dialogDetailVisible: false,
      detailForm: {
        account: {}
      },
      detailRules: {
        money: [
          { required: true, message: '请输入金额', trigger: ['blur'] }
        ]
      },
      // 等级列表
      levelData: [],
      detailUp: {}
    }
  },
  created() {
    this.hdlList()
    this.newForm.accountAt = this.getTime()
  },
  methods: {
    getTime() {
      var myDate = new Date()
      var year = myDate.getFullYear() + 1 // 获取当前年
      var mon = myDate.getMonth() + 1 // 获取当前月
      var date = myDate.getDate()
      const time = year + '-' + mon + '-' + date
      return new Date(time).getTime()
    },
    // 打开编辑事件
    handleEdit(row) {
      this.detailForm = this.$u.deepClone(row) // copy obj
      this.detailForm.account = this.detailForm.account || {}
      this.detailForm.account.password = ''
      this.detailForm.tabsActive = 'first'
      this.dialogDetailVisible = true
      this.detailForm.region = [this.detailForm.province, this.detailForm.city, this.detailForm.area]
      this.$nextTick(() => {
        this.$refs['detailForm'].clearValidate()
      })
      this.detailUp = {
        username: true,
        password: true
      }
    },
    // 提交更新详情数据
    updateDetail() {
      this.detailForm.province = this.detailForm.region[0]
      this.detailForm.city = this.detailForm.region[1]
      this.detailForm.area = this.detailForm.region[2]
      this.$refs['detailForm'].validate((valid) => {
        if (valid) {
          this.api.update({ merch: JSON.stringify(this.detailForm) }).then(() => {
            this.hdlList()
            this.dialogDetailVisible = false
            this.$u.msg('更新成功')
          })
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  .app-container{
    .item >>> .el-form-item__content {
      display: flex;
    }
  }
  ::v-deep{
    .el-cascader{
      width: 100%;
    }
  }

</style>
