<template>
  <div class="app-container">

    <u-page title="用户管理">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="用户名称" prop="nickname">
            <el-input v-model="listQuery.nickname" placeholder="请输入用户名称" clearable />
          </el-form-item>
          <el-form-item label="手机号" prop="mobile">
            <el-input v-model="listQuery.mobile" placeholder="请输入手机号" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
          </el-form-item>
          <!--<el-dropdown>
            <el-button type="primary">
              批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :disabled="selectData.length===0" @click.native="hdlDel">批量删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>-->
        </el-form>
      </u-filtered>

      <u-table
        :data="list"
        :loading="listLoading"
        :options="listOptions"
        :columns="columns"
        :pagination.sync="listQuery"
        :fetch="hdlList"
        @selection-change="hdlSelectChange"
      >
        <template v-slot:right>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="{ row }">
              <el-button type="text" @click="handleEdit(row)">编辑</el-button>
              <el-button type="text" @click="handleRecharge(row)">充值</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.nickname)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <!-- 创建会员 -->
    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="hdlCreate">

      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="mobile" label="账号">
          <el-input v-model="dataForm.mobile" placeholder="账号,默认手机号格式" clearable maxlength="11" show-word-limit />
        </el-form-item>

        <el-form-item prop="nickname" label="用户名">
          <el-input v-model="dataForm.nickname" placeholder="用户名" clearable maxlength="10" show-word-limit />
        </el-form-item>

        <el-form-item prop="password" label="密码" class="item">
          <el-input v-model="dataForm.password" type="password" placeholder="登录密码" show-password clearable />
          <el-alert show-icon title="初始密码:888888" type="info" :closable="false" style="margin-left: 20px;padding: 0;background-color:initial" />
        </el-form-item>

        <el-form-item prop="disabled" label="状态">
          <el-switch v-model="dataForm.disabled" />
        </el-form-item>

      </el-form>
    </u-dialog>

    <!-- 会员充值 -->
    <u-dialog :title="rechargeTitle" :show.sync="dialogRechargeVisible" @confirm="recharge">

      <el-form ref="rechargeForm" :rules="rechargeRules" :model="rechargeForm" label-width="80px">

        <el-card shadow="hover" style="margin-bottom: 10px;border-radius:0;">
          <div slot="header">
            会员信息
          </div>
          <div style="display: flex; flex-direction: column; line-height: 28px;">
            <div>用户名：<span>{{ rechargeForm.nickname? rechargeForm.nickname : '无' }}</span></div>
            <div>实名： <span>{{ rechargeForm.realname? rechargeForm.realname : '无' }}</span></div>
            <div>手机号：<span>{{ rechargeForm.mobile }}</span></div>
          </div>
        </el-card>

        <el-tabs v-model="rechargeForm.tabsActive">
          <el-tab-pane label="充值余额" name="credit1">

            <el-form-item prop="credit1" label="当前余额" style="margin-top: 10px">
              <span style="font-weight: bold;font-size: 16px;line-height: 33px;">￥{{ rechargeForm.credit1 | amount }}</span>
            </el-form-item>

            <el-form-item prop="alter" label="变化">
              <el-radio-group v-model="rechargeForm.alter">
                <el-radio :label="0">增加</el-radio>
                <el-radio :label="1">减少</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item prop="number" label="充值数目" class="item">
              <el-input-number v-model="rechargeForm.number" :min="0" :max="10000" placeholder="请输入充值数目" controls-position="right" style="width: 300px;" />
              <el-alert show-icon title="单笔充值限1万" type="info" :closable="false" style="margin-left: 20px;padding: 0;background-color:initial" />
            </el-form-item>

            <el-form-item prop="note" label="备注">
              <el-input v-model="rechargeForm.note" type="textarea" placeholder="备注" clearable maxlength="200" show-word-limit />
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane label="积分充值" name="credit2">

            <el-form-item prop="credit1" label="当前积分" style="margin-top: 10px">
              <span style="font-weight: bold;font-size: 16px;line-height: 33px;">{{ rechargeForm.credit2 | amount }}</span>
            </el-form-item>

            <el-form-item prop="alter" label="变化">
              <el-radio-group v-model="rechargeForm.pointsAlter">
                <el-radio :label="0">增加</el-radio>
                <el-radio :label="1">减少</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item prop="number" label="充值数目" class="item">
              <el-input-number v-model="rechargeForm.pointsNumber" :min="0" :max="10000" placeholder="请输入充值数目" controls-position="right" style="width: 300px;" />
              <el-alert show-icon title="单笔充值限1万" type="info" :closable="false" style="margin-left: 20px;padding: 0;background-color:initial" />
            </el-form-item>

            <el-form-item prop="note" label="备注">
              <el-input v-model="rechargeForm.pointsNote" type="textarea" placeholder="备注" clearable maxlength="200" show-word-limit />
            </el-form-item>

          </el-tab-pane>
        </el-tabs>
      </el-form>
    </u-dialog>

    <!-- 会员详情 -->
    <u-dialog :title="detailTitle" :show.sync="dialogDetailVisible" @confirm="updateDetail">

      <el-form ref="detailForm" :rules="detailRules" :model="detailForm" label-width="80px">

        <el-tabs v-model="detailForm.tabsActive">
          <el-tab-pane label="基本信息" name="first">

            <el-form-item label="用户" style="margin-top: 10px">
              <div style="display: flex;align-items: center;">
                <el-avatar v-if="detailForm.avatar" size="medium" :src="detailForm.avatar">
                  <i class="el-icon-picture-outline" />
                </el-avatar>
                <span style="padding-left: 5px;font-weight: bold;">{{ detailForm.nickname }}</span>
              </div>
            </el-form-item>

            <el-form-item label="等级">
              <el-select v-model="detailForm.levelid" clearable placeholder="默认等级">
                <el-option
                  v-for="item in level"
                  :key="item.id"
                  :label="item.levelName"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>

            <el-form-item label="标签">
              <el-select v-model="detailForm.labelid" clearable placeholder="用户标签">
                <el-option
                  v-for="item in label"
                  :key="item.id"
                  :label="item.labelName"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>

            <el-form-item label="分组">
              <el-select v-model="detailForm.groupid" clearable placeholder="用户分组">
                <el-option
                  v-for="item in group"
                  :key="item.id"
                  :label="item.groupName"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>

            <el-form-item prop="realname" label="实名" style="margin-top: 10px">
              <div v-if="detailUp.realname">
                <span>{{ detailForm.realname }}</span>
                <el-button type="text" @click="detailUp.realname = false">修改</el-button>
              </div>
              <div v-else>
                <el-input v-model="detailForm.realname" placeholder="真实姓名" clearable>
                  <el-button slot="append" icon="el-icon-check" @click="detailUp.realname = true" />
                </el-input>
              </div>
            </el-form-item>

            <el-form-item prop="idcard" label="身份证" style="margin-top: 10px">
              <div v-if="detailUp.idcard">
                <span>{{ detailForm.idcard }}</span>
                <el-button type="text" @click="detailUp.idcard = false">修改</el-button>
              </div>
              <div v-else>
                <el-input v-model="detailForm.idcard" placeholder="身份证号" clearable maxlength="18" show-word-limit>
                  <el-button slot="append" icon="el-icon-check" @click="detailUp.idcard = true" />
                </el-input>
              </div>
            </el-form-item>

            <el-form-item prop="credit1" label="余额">
              <span style="font-weight: bold;font-size: 16px;line-height: 33px;">￥{{ detailForm.credit1 | amount }}</span>
            </el-form-item>

            <el-form-item prop="credit1" label="注册">
              {{ detailForm.createAt | date }}
            </el-form-item>

            <el-form-item prop="disabled" label="状态">
              <el-switch v-model="detailForm.disabled" />
            </el-form-item>

            <el-form-item prop="note" label="备注">
              <el-input v-model="detailForm.note" type="textarea" placeholder="备注" clearable maxlength="200" show-word-limit />
            </el-form-item>

          </el-tab-pane>

          <el-tab-pane label="账号信息" name="second">

            <el-form-item prop="mobile" label="账号" style="margin-top: 10px">
              <div v-if="detailUp.mobile">
                <span style="font-weight: bold;">{{ detailForm.mobile }}</span>
                <el-button type="text" @click="detailUp.mobile = false">修改</el-button>
              </div>
              <div v-else>
                <el-input v-model="detailForm.mobile" placeholder="账号,默认手机号格式" clearable maxlength="11" show-word-limit>
                  <el-button slot="append" icon="el-icon-check" @click="detailUp.mobile = true" />
                </el-input>
              </div>
            </el-form-item>

            <el-form-item prop="password" label="密码" class="item">
              <div v-if="detailUp.password">
                <span>{{ detailForm.password }}</span>
                <el-button type="text" @click="detailUp.password = false">修改</el-button>
                <el-alert show-icon title="密码留空则不修改" type="info" :closable="false" style="padding: 0;background-color:initial" />
              </div>
              <div v-else>
                <el-input v-model="detailForm.password" type="password" placeholder="登录密码" show-password clearable>
                  <el-button slot="append" icon="el-icon-check" @click="detailUp.password = true" />
                </el-input>
              </div>
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>
    </u-dialog>
  </div>
</template>
<script>

export default {

  data() {
    return {
      api: this.$u.api.Member,
      // 表格
      columns: [
        {
          prop: 'nickname',
          label: '用户',
          align: 'left',
          width: '150'
        },
        {
          prop: 'mobile',
          align: 'left',
          label: '账号'
        },
        {
          prop: 'level',
          align: 'left',
          label: '等级',
          formatter: (row, column) => {
            if (row.level != null) {
              return row.level.levelName
            } else {
              return '默认等级'
            }
          }
        },
        {
          prop: 'label',
          align: 'left',
          label: '标签',
          formatter: (row, column) => {
            if (row.label != null) {
              return row.label.labelName
            }
          }
        },
        {
          prop: 'group',
          align: 'left',
          label: '分组',
          formatter: (row, column) => {
            if (row.group != null) {
              return row.group.groupName
            }
          }
        },
        {
          prop: 'credit1',
          label: '余额',
          align: 'left',
          render: (h, params) => {
            return h('span', { style: { fontWeight: 'bold' }}, '￥' + this.$u.amount.format(params.row.credit1))
          }
        },
        {
          prop: 'disabled',
          label: '启用状态',
          align: 'left',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.nickname)
          }
        },
        {
          prop: 'createAt',
          label: '注册时间',
          align: 'left',
          timestamp: true
        }
      ],

      list: [],
      listLoading: true,
      listOptions: {
        mutiSelect: true,
        stripe: true
      },
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        nickname: '',
        mobile: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑用户',
        create: '新增用户'
      },
      dataForm: {},
      rules: {
        mobile: [
          { required: true, message: '请输入账号', trigger: ['blur'] }
        ],
        nickname: [
          { required: true, message: '请输入用户名', trigger: ['blur'] }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      // 设置默认数据
      newForm: {
        password: '888888',
        disabled: true
      },

      // 充值
      rechargeTitle: '会员充值',
      dialogRechargeVisible: false,
      rechargeForm: {},
      rechargeRules: {
        number: [
          { required: true, message: '请输入金额', trigger: ['blur'] }
        ]
      },

      // 详情
      detailTitle: '会员充值',
      dialogDetailVisible: false,
      detailForm: {},
      detailRules: {},
      // 等级列表
      level: [],
      label: [],
      group: [],
      // 详情修改控制器
      detailUp: {
        mobile: true,
        password: true,
        realname: true,
        idcard: true
      }
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    // 加载数据
    loadData() {
      this.initData()
      this.hdlList()
    },
    //  初始化数据
    initData() {
      this.api.data().then(res => {
        this.level = res.data.level
        this.label = res.data.label
        this.group = res.data.group
      })
    },
    // 打开编辑事件
    handleEdit(row) {
      this.detailForm = Object.assign({}, row) // copy obj
      this.detailForm.tabsActive = 'first'
      this.detailForm.password = '' // 清空密码
      this.detailForm.level = {} // 初始化关联对象
      this.dialogDetailVisible = true
      this.$nextTick(() => {
        this.$refs['detailForm'].clearValidate()
      })
      this.detailUp = {
        mobile: true,
        password: true,
        realname: true,
        idcard: true
      }
    },
    // 提交更新详情数据
    updateDetail() {
      this.$refs['detailForm'].validate((valid) => {
        if (valid) {
          this.api.update(this.detailForm).then(() => {
            this.hdlList()
            this.dialogDetailVisible = false
            this.$u.msg('更新成功')
          })
        }
      })
    },
    // 会员充值
    handleRecharge(row) {
      // 初始化信息
      this.rechargeForm = {
        uid: row.id,
        nickname: row.nickname,
        realname: row.realname,
        mobile: row.mobile,
        avatar: row.avatar,
        credit1: row.credit1,
        credit2: row.credit2,
        tabsActive: 'credit1',
        number: 0,
        note: '',
        alter: 0,
        pointsNumber: 0,
        pointsAlter: 0,
        pointsNote: ''
      }
      this.dialogRechargeVisible = true
      this.$nextTick(() => {
        this.$refs['rechargeForm'].clearValidate()
      })
    },
    // 充值接口
    recharge() {
      const { tabsActive, number, pointsNumber } = this.rechargeForm
      const self = this
      if (tabsActive === 'credit1') {
        if (number < 1) {
          this.$u.msg('充值金额不能小于1分', 'error')
          return
        }
        const tips = '您确定充值' + number + '元,请谨慎操作'
        self.$confirm(tips, '提示', { type: 'warning', confirmButtonText: '确定', cancelButtonText: '取消' }).then(() => {
          self.$refs['rechargeForm'].validate((valid) => {
            if (valid) {
              // 转换金额
              const data = {
                uid: self.rechargeForm.uid,
                alter: self.rechargeForm.alter,
                money: self.$u.amount.Y2F(number),
                note: self.rechargeForm.note
              }
              self.api.recharge(data).then(() => {
                // 刷新数据列表
                self.hdlList()
                // 关闭弹窗状态
                self.dialogRechargeVisible = false
                // 通知信息
                self.$u.msg('充值成功')
              }).catch(() => {
              })
            }
          })
        }).catch(() => {
        })
      } else {
        if (pointsNumber < 1) {
          this.$u.msg('充值积分不能小于1分', 'error')
          return
        }
        const tips = '您确定充值' + pointsNumber + '积分,请谨慎操作'
        self.$confirm(tips, '提示', { type: 'warning', confirmButtonText: '确定', cancelButtonText: '取消' }).then(() => {
          self.$refs['rechargeForm'].validate((valid) => {
            if (valid) {
              // 转换数值
              const data = {
                uid: self.rechargeForm.uid,
                alter: self.rechargeForm.pointsAlter,
                money: self.$u.amount.Y2F(pointsNumber),
                note: self.rechargeForm.pointsNote
              }
              self.api.points(data).then(() => {
                // 刷新数据列表
                self.hdlList()
                // 关闭弹窗状态
                self.dialogRechargeVisible = false
                // 通知信息
                self.$u.msg('充值成功')
              }).catch(() => {
              })
            }
          })
        }).catch(() => {
        })
      }
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
</style>
