<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="app-container">

    <u-page title="钉钉用户管理">
      <div slot="header">
        <!--        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>-->
      </div>
      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="姓名">
            <el-input v-model="listQuery.name" placeholder="请输入姓名" clearable />
          </el-form-item>

          <el-form-item label="工号">
            <el-input v-model="listQuery.number" placeholder="请输入工号" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
          </el-form-item>
        </el-form>
      </u-filtered>

      <u-table
        :data="list"
        :loading="listLoading"
        :options="listOptions"
        :columns="columns"
        :pagination.sync="listQuery"
        :fetch="hdlList"
      >
        <template v-slot:right>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="{ row }">
              <el-button type="text" @click="hdlEdit(row)">编辑</el-button>

              <el-dropdown>
                <el-button type="text">更多</el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="hdlSynchroUser(row.id)">同步钉钉数据</el-dropdown-item>
                  <el-dropdown-item divided @click.native="hdlDelete(row.id, row.name)">删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </template>
      </u-table>
    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">

      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="100px">

        <el-row>
          <el-col :span="12">
            <el-form-item prop="name" label="姓名">
              <el-input v-model="dataForm.name" placeholder="请输入姓名" clearable maxlength="20" show-word-limit disabled />
            </el-form-item>

            <el-form-item prop="number" label="工号">
              <el-input v-model="dataForm.number" placeholder="请输入工号" clearable disabled />
            </el-form-item>

            <el-form-item prop="mobile" label="手机号">
              <el-input v-model="dataForm.mobile" placeholder="请输入手机号" maxlength="11" clearable disabled />
            </el-form-item>

            <el-form-item prop="deptIds" label="组织">
              <el-select v-model="dataForm.deptIds" multiple placeholder="请选择组织" style="width: 100%" disabled>
                <el-option
                  v-for="item in deptData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                  :disabled="!item.disabled"
                />
              </el-select>
            </el-form-item>

            <el-form-item prop="roleIds" label="角色">
              <el-select v-model="dataForm.roleIds" multiple placeholder="请选择角色" style="width: 100%" disabled>
                <el-option
                  v-for="item in roleData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                  :disabled="!item.disabled"
                />
              </el-select>
            </el-form-item>

            <el-form-item prop="disabled" label="状态">
              <el-radio-group v-model="dataForm.disabled">
                <el-radio :label="true">启用</el-radio>
                <el-radio :label="false">禁用</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-col>
          <el-col :span="12">
            <el-form-item prop="userid" label="用户ID">
              <el-input v-model="dataForm.userid" placeholder="钉钉用户ID" disabled clearable />
            </el-form-item>

            <el-form-item prop="UNIONID" label="UNIONID">
              <el-input v-model="dataForm.unionid" placeholder="钉钉UNIONID" disabled clearable />
            </el-form-item>

            <el-form-item prop="email" label="邮箱">
              <el-input v-model="dataForm.email" disabled clearable />
            </el-form-item>

            <el-form-item prop="sex" label="性别">
              <el-radio-group v-model="dataForm.sex" size="small" disabled>
                <el-radio-button :label="0">保密</el-radio-button>
                <el-radio-button :label="1">男</el-radio-button>
                <el-radio-button :label="2">女</el-radio-button>
              </el-radio-group>
            </el-form-item>

            <el-form-item prop="hired_date" label="入职时间">
              <el-input :value="`${$u.timeFormat(dataForm.hired_date, 'yyyy-mm-dd')}`" disabled clearable />
            </el-form-item>

          </el-col>
        </el-row>

        <el-form-item prop="note" label="备注">
          <el-input v-model="dataForm.note" type="textarea" placeholder="请输入备注" clearable disabled />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    const self = this
    const validateName = function(rule, value, callback) {
      const regex = new RegExp('^[a-zA-Z0-9]{4,20}$')
      if (!regex.test(value)) {
        callback(new Error('账号以字母开头4-20字,允许使用字母/数字'))
      } else {
        callback()
      }
    }
    const validatePass = function(rule, value, callback) {
      if (value !== self.dataForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      api: this.$u.api.DingtalkUser,
      // 表格
      columns: [
        {
          prop: 'number',
          label: '工号',
          align: 'left'
        },
        {
          prop: 'name',
          label: '姓名',
          align: 'left'
        },
        {
          prop: 'mobile',
          label: '手机号',
          align: 'left'
        },
        {
          prop: 'sex',
          label: '性别',
          align: 'left',
          formatter: (row, column) => {
            return this.sexList[row.sex]
          }
        },
        {
          prop: 'disabled',
          label: '状态',
          render: (h, params) => {
            if (params.row.disabled) {
              return h('el-tag', { props: { type: 'success' }}, '正常')
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '禁用')
            }
          }
        },
        {
          prop: 'hired_date',
          label: '入职时间',
          sortable: true,
          timestamp: true
        },
        {
          prop: 'note',
          label: '备注',
          align: 'left'
        },
        {
          prop: 'createdAt',
          label: '创建时间',
          sortable: true,
          timestamp: true
        }
      ],

      list: [],
      listLoading: true,
      listOptions: {
        mutiSelect: true, // boolean 是否多选
        stripe: true // boolean 斑马纹
      },
      listQuery: {
        page: 1,
        pageSize: 20,
        totalCount: 1,
        name: '',
        number: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑用户',
        create: '新增用户'
      },
      dataForm: {},
      rules: {
        /* deptId: [
          { required: true, message: '请选择机构', trigger: ['blur', 'change'] }
        ],*/
        name: [
          { required: true, message: '请输入账号', trigger: ['blur', 'change'] },
          { validator: validateName, trigger: ['blur', 'change'] }
        ],
        nickname: [
          { required: true, message: '请输入姓名', trigger: ['blur', 'change'] }
        ],
        email: [
          { required: false, message: '请输入邮箱', trigger: ['blur', 'change'] },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        mobile: [
          { required: false, message: '请输入手机号码', trigger: ['blur', 'change'] },
          { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度必须是6-20位', trigger: 'change' }
        ],
        passwordAgain: [
          { required: true, message: '请再次输入新密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'change' }
        ]
      },

      // 组织:渲染数据
      deptData: [],
      // 角色:渲染数据
      roleData: [],

      // 默认数据
      newForm: {
        sex: 0,
        disabled: true
      },
      sexList: ['保密', '男', '女']

    }
  },
  created() {
    this.hdlList()
    // this.loadDept()
    this.loadrRole()
  },
  methods: {
    // 初始化机构选项
    loadDept() {
      this.api.data().then(res => {
        this.deptData = this.$u.tree(res.data)
      })
    },
    // 初始化角色选项
    loadrRole() {
      this.api.data().then(res => {
        const { roles, depts } = res.data
        this.roleData = roles
        this.deptData = depts
      })
    },
    // 获取列表数据
    hdlList() {
      const self = this
      // 打开加载状态
      self.listLoading = true
      // 查询分页数据
      self.api.list(self.listQuery).then(res => {
        // 获取分页列表数据
        self.list = res.data.list

        // 遍历获取IDS
        self.list.forEach((item, index) => {
          self.list[index].roleIds = item.roles.map(v => {
            return v.id
          })
          self.list[index].deptIds = item.depts.map(v => {
            return v.id
          })
        })
        console.log(JSON.stringify(self.list))
        /* item.roles.map(item => })
         if (item1.id === item) {
           item1.fcate = fcate
           item1.scate = scate
           item1.tcate = tcate
         }*/
        // 获取分页数据,总页数
        self.listQuery.totalCount = res.data.totalCount
        // 关闭加载状态
        setTimeout(() => {
          self.listLoading = false
        }, 0.5 * 1000)
      })
    },
    // 提交创建数据事件
    hdlCreate() {
      const self = this
      // 效验数据格式
      this.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          const data = self.dataForm
          data.roleIds = data.roleIds.toString()
          // 提交创建数据
          self.api.create(data).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.dialogFormVisible = false
            // 通知信息
            self.$u.msg('创建成功')
          })
        }
      })
    },
    // 提交更新数据事件
    hdlUpdate() {
      const self = this
      // 效验数据格式
      self.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          const data = Object.assign({}, self.dataForm)
          data.dept = {}
          delete data.roles
          data.roleIds = data.roleIds.toString()
          // 提交更新数据
          self.api.update(data).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.dialogFormVisible = false
            // 通知信息
            self.$u.msg('更新成功')
          })
        }
      })
    },
    // 重置用户密码事件
    handleResetPassword(row) {
      const self = this
      self.$confirm('此操作将重置用户 【' + row.nickname + '】 的密码，是否继续？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.resetPassword(row.id).then(res => {
          self.$alert('新密码为 ' + res.data, '新密码', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    },
    // 监听机构变化
    handleCascaderChange(value) {
      this.dataForm.deptId = value[value.length - 1] || ''
    },
    // 同步钉钉用户数据
    hdlSynchroUser(id) {
      const self = this
      self.$confirm('此操作将同步钉钉用户信息数据是否继续，请谨慎操作？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchro(id).then(res => {
          self.$alert('同步数据中，请等待几分钟后查看', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  ::v-deep{
    .el-table-expand {
      font-size: 0;

      label {
        width: 90px;
        color: #99a9bf;
        font-size: 12px;
      }

      .el-form-item{
        margin-bottom: 2px;
        margin-right: 0;
        vertical-align: top;
      }

      .el-form-item__content {
        margin-right: 0;
        margin-bottom: 0;
        font-size: 12px;
        display: inline-block;
        vertical-align: top;
        position: relative;
      }
    }
  }
</style>

