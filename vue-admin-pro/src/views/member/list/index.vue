<template>
  <div class="app-container">

    <u-page title="会员管理">
      <!--      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>-->

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="卡号" prop="cardno">
            <el-input v-model="listQuery.cardno" placeholder="请输入卡号" clearable style="width: 170px" />
          </el-form-item>
          <el-form-item label="手机号" prop="mobile">
            <el-input v-model="listQuery.mobile" placeholder="请输入手机号" clearable style="width: 160px" maxlength="11" show-word-limit />
          </el-form-item>
          <el-form-item label="OPENID" prop="openid">
            <el-input v-model="listQuery.openid" placeholder="请输入OPENID" clearable />
          </el-form-item>
          <el-form-item label="等级" prop="status">
            <el-select v-model="listQuery.level" placeholder="请选择等级" class="status" clearable>
              <el-option
                v-for="item in level"
                :key="item.value"
                :label="item.levelName"
                :value="item.level"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="listQuery.status" placeholder="请选择状态" class="status" clearable>
              <el-option
                v-for="item in statusList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
          </el-form-item>
          <el-dropdown>
            <el-button type="primary">
              更多操作<i class="el-icon-arrow-down el-icon--right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="openMerge">会员合并</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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
              <el-button type="text" @click="hdlEdit(row)">编辑</el-button>
              <el-dropdown>
                <el-button type="text">更多</el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="handleOpenDisable(row)">更改会员状态</el-dropdown-item>
                  <el-dropdown-item @click.native="handleHealth(row)">检查健康状态</el-dropdown-item>
                  <el-dropdown-item @click.native="handleUnbindOpenid(row)">解绑OPENID</el-dropdown-item>
                  <el-dropdown-item divided @click.native="handleOpenCredit(row)">积分操作</el-dropdown-item>
                  <el-dropdown-item @click.native="handleCreditDetaile(row)">积分明细</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <!-- 创建更新会员 -->
    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">

      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item prop="member_name" label="姓名">
              <el-input v-model="dataForm.member_name" placeholder="请输入姓名" clearable minlength="1" maxlength="20" show-word-limit />
            </el-form-item>

            <el-form-item prop="mobilephone" label="手机号">
              <el-input v-model="dataForm.mobilephone" placeholder="请输入手机号" maxlength="11" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="openid" label="OPENID">
              <el-input v-model="dataForm.openid" placeholder="请输入微信公众号OPENID" disabled clearable />
            </el-form-item>

            <el-form-item prop="identity_id" label="证件号">
              <el-input v-model="dataForm.identity_id" placeholder="请输入证件号(身份证)" maxlength="18" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="sex" label="性别">
              <el-radio-group v-model="dataForm.sex" size="small">
                <el-radio-button :label="2">未知</el-radio-button>
                <el-radio-button :label="0">男</el-radio-button>
                <el-radio-button :label="1">女</el-radio-button>
              </el-radio-group>
            </el-form-item>

            <el-form-item prop="birthday" label="生日">
              <el-date-picker v-model="dataForm.birthday" type="date" value-format="yyyy-MM-dd" placeholder="选择生日日期" />
            </el-form-item>

            <el-form-item prop="age" label="年龄">
              <el-input :value="dataForm.age" placeholder="暂未计算" disabled clearable />
            </el-form-item>

            <el-form-item prop="contact_address" label="通讯地址">
              <el-input v-model="dataForm.contact_address" type="textarea" placeholder="请输入通讯地址" clearable maxlength="200" show-word-limit />
            </el-form-item>

            <el-form-item prop="status" label="状态">
              <el-radio-group v-model="dataForm.status" disabled>
                <el-radio :label="0">正常</el-radio>
                <el-radio :label="-1">禁用</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-col>
          <el-col :span="12">
            <el-form-item prop="card_no" label="会员卡号">
              <el-input v-model="dataForm.card_no" placeholder="会员卡号" disabled clearable />
            </el-form-item>

            <el-form-item prop="card_type" label="会员等级">
              <el-input :value="getLevel(dataForm.card_type)" placeholder="会员等级" disabled clearable />
            </el-form-item>

            <el-form-item prop="card_id" label="会员卡ID">
              <el-input v-model="dataForm.card_id" placeholder="会员卡ID" disabled clearable />
            </el-form-item>

            <el-form-item prop="consum_maxdate" label="最近消费时间">
              <el-input v-model="dataForm.consum_maxdate" disabled clearable />
            </el-form-item>

            <el-form-item prop="uporde_date" label="升降级日期">
              <el-input v-model="dataForm.uporde_date" disabled clearable />
            </el-form-item>

            <el-form-item prop="input_date" label="注册时间">
              <el-input v-model="dataForm.input_date" disabled clearable />
            </el-form-item>

            <el-form-item prop="mark_open" label="注册渠道">
              <el-input :value="getChannel(dataForm.mark_open)" disabled clearable />
            </el-form-item>

            <el-form-item prop="chanl_id" label="门店ID">
              <el-input v-model="dataForm.chanl_id" disabled clearable />
            </el-form-item>

            <el-form-item prop="credit" label="积分">
              <el-input v-model="dataForm.credit" disabled clearable />
            </el-form-item>

            <el-form-item prop="clear_credit" label="本年清零积分">
              <el-input v-model="dataForm.clear_credit" disabled clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item prop="remark" label="备注">
          <el-input v-model="dataForm.remark" type="textarea" placeholder="请输入备注" clearable maxlength="200" show-word-limit />
        </el-form-item>

      </el-form>
    </u-dialog>

    <!-- 更改会员状态 -->
    <u-dialog :title="statusTitle" :show.sync="dialogStatusVisible" @confirm="handleDisable">
      <el-form ref="dataStatusForm" :rules="dataStatusRules" :model="dataStatusForm" label-width="100px">

        <el-row>
          <el-col :span="12">
            <el-form-item prop="member_name" label="姓名">
              <el-input v-model="dataStatusForm.member_name" placeholder="请输入姓名" clearable disabled minlength="1" maxlength="20" show-word-limit />
            </el-form-item>

            <el-form-item prop="mobilephone" label="手机号">
              <el-input v-model="dataStatusForm.mobilephone" placeholder="请输入手机号" disabled maxlength="11" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="openid" label="OPENID">
              <el-input v-model="dataStatusForm.openid" placeholder="请输入微信公众号OPENID" disabled clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="card_no" label="会员卡号">
              <el-input v-model="dataStatusForm.card_no" placeholder="会员卡号" disabled clearable />
            </el-form-item>

            <el-form-item prop="card_type" label="会员等级">
              <el-input :value="getLevel(dataStatusForm.card_type)" placeholder="会员等级" disabled clearable />
            </el-form-item>

            <el-form-item prop="card_id" label="会员卡ID">
              <el-input v-model="dataStatusForm.card_id" placeholder="会员卡ID" disabled clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item prop="status" label="状态">
          <el-radio-group v-model="dataStatusForm.status">
            <el-radio :label="0">正常</el-radio>
            <el-radio :label="-1">禁用</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="remark" label="备注">
          <el-input v-model="dataStatusForm.remark" type="textarea" placeholder="请输入备注以方便追溯历史原因(必填)" clearable maxlength="200" show-word-limit />
        </el-form-item>

      </el-form>
    </u-dialog>

    <!-- 会员合并 -->
    <u-dialog title="会员合并" :show.sync="dialogMergeVisible" @confirm="handleMerge">

      <el-form ref="dataMergeForm" :rules="dataMergeRules" :model="dataMergeForm" label-width="100px">
        <el-row>
          <el-form-item prop="name" label="查询条件">
            <el-input v-model="dataMergeQuery.content" placeholder="请输入查询内容" clearable>
              <el-select slot="prepend" v-model="dataMergeQuery.type" placeholder="请选择条件查询" clearable>
                <el-option label="根据保留手机号查询会员" :value="1" />
                <el-option label="根据保留卡号查询会员" :value="2" />
                <el-option label="根据作废手机号查询会员" :value="3" />
                <el-option label="根据作废卡号查询会员" :value="4" />
              </el-select>
              <el-button slot="append" icon="el-icon-search" @click="handleQuery()" />
            </el-input>
          </el-form-item>

          <el-col :span="12">
            <el-form-item prop="card_no" label="保留卡号">
              <el-input v-model="dataMergeForm.card_no" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="mobilephone" label="保留手机号">
              <el-input v-model="dataMergeForm.mobilephone" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="card_type" label="会员等级">
              <el-input :value="getLevel(dataMergeForm.card_type)" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="openid" label="保留OPENID">
              <el-input :value="dataMergeForm.openid" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="credit" label="积分">
              <el-input :value="dataMergeForm.credit" placeholder="无数据" disabled clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="oldcard_no" label="作废卡号">
              <el-input v-model="dataMergeForm.oldcard_no" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="oldmobilephone" label="作废手机号">
              <el-input v-model="dataMergeForm.oldmobilephone" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="oldcard_type" label="会员等级">
              <el-input :value="getLevel(dataMergeForm.oldcard_type)" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="oldopenid" label="作废OPENID">
              <el-input :value="dataMergeForm.oldopenid" placeholder="无数据" disabled clearable />
            </el-form-item>
            <el-form-item prop="oldcredit" label="积分">
              <el-input :value="dataMergeForm.oldcredit" placeholder="无数据" disabled clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item prop="note" label="积分权益">
          <el-tooltip class="item" effect="dark" content="是否合并积分权益" placement="top-start">
            <el-switch v-model="dataMergeForm.transfer_credit" />
          </el-tooltip>
        </el-form-item>

      </el-form>

      <el-alert
        title="温馨提示"
        type="success"
        description="保留会员卡会检查会员状态是否正常，如果是作废状态则会更改为正常状态；作废会员卡会解除OPNEID绑定，并重置手机号为11111111111，并将原有的手机号设定到姓名字段，状态更改为作废状态。"
        show-icon
        :closable="false"
      />
    </u-dialog>

    <!-- 积分明细 -->
    <u-dialog :title="creditTitle" :show.sync="dialogCreditVisible" :footer="false">
      <u-table
        :data="creditList"
        :loading="creditListLoading"
        :options="listOptions"
        :columns="creditColumns"
        :pagination.sync="creditListQuery"
        :fetch="handleCreditList"
        @selection-change="hdlSelectChange"
      />
    </u-dialog>

    <!-- 积分操作 -->
    <u-dialog title="积分操作" :show.sync="dialogRechargeVisible" @confirm="handleCredit">
      <el-form ref="rechargeForm" :rules="rechargeRules" :model="rechargeForm" label-width="100px">

        <el-row>
          <el-col :span="12">
            <el-form-item prop="member_name" label="姓名">
              <el-input v-model="rechargeForm.member_name" placeholder="请输入姓名" clearable disabled minlength="1" maxlength="20" show-word-limit />
            </el-form-item>

            <el-form-item prop="mobilephone" label="手机号">
              <el-input v-model="rechargeForm.mobilephone" placeholder="请输入手机号" disabled maxlength="11" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="openid" label="OPENID">
              <el-input v-model="rechargeForm.openid" placeholder="请输入微信公众号OPENID" disabled clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="card_no" label="会员卡号">
              <el-input v-model="rechargeForm.card_no" placeholder="会员卡号" disabled clearable />
            </el-form-item>

            <el-form-item prop="card_type" label="会员等级">
              <el-input :value="getLevel(rechargeForm.card_type)" placeholder="会员等级" disabled clearable />
            </el-form-item>

            <el-form-item prop="card_id" label="会员卡ID">
              <el-input v-model="rechargeForm.card_id" placeholder="会员卡ID" disabled clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item prop="status" label="操作">
          <el-radio-group v-model="rechargeForm.status">
            <el-radio :label="0">充值</el-radio>
            <el-radio :label="1">扣减</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="credit" label="积分">
          <el-input v-model="rechargeForm.credit" placeholder="请输入积分(必填)" clearable />
        </el-form-item>

        <el-form-item prop="note" label="备注">
          <el-input v-model="rechargeForm.note" placeholder="请输入备注以方便追溯历史原因(必填)" clearable maxlength="10" show-word-limit />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      statusList: [
        {
          value: '',
          label: '全部'
        }, {
          value: '0',
          label: '正常'
        }, {
          value: '-1',
          label: '禁用'
        }
      ],
      api: this.$u.api.Member,
      // 表格
      columns: [
        {
          prop: 'member_name',
          label: '姓名',
          width: '80'
        },
        {
          prop: 'card_no',
          label: '会员卡号',
          width: '120'
        },
        {
          prop: 'mobilephone',
          label: '手机号'
        },
        {
          prop: 'openid',
          label: 'OPENID',
          width: '220'
        },
        {
          prop: 'card_type',
          align: 'left',
          label: '等级',
          formatter: (row, column) => {
            return this.getLevel(row.card_type)
          }
        },
        {
          prop: 'credit',
          label: '积分',
          render: (h, params) => {
            return h('span', { style: { fontWeight: 'bold' }}, params.row.credit)
          }
        },
        {
          prop: 'status',
          label: '状态',
          render: (h, params) => {
            if (params.row.status === 0) {
              return h('el-tag', { props: { type: 'success' }}, '正常')
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '禁用')
            }
          }
        },
        {
          prop: 'input_date',
          label: '注册时间'
        },
        {
          prop: 'mark_open',
          label: '注册渠道',
          formatter: (row, column) => {
            return this.getChannel(row.mark_open)
          }
        },
        {
          prop: 'remark',
          label: '备注'
        }
      ],

      list: [],
      listLoading: false,
      listOptions: {
        mutiSelect: true,
        stripe: true
      },
      listQuery: {
        page: 1,
        pageSize: 10,
        totalCount: 1,
        openid: '',
        cardno: '',
        mobile: '',
        status: '0',
        level: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑会员',
        create: '新增会员'
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
      // 更改会员状态
      statusTitle: '更改会员状态',
      dialogStatusVisible: false,
      dataStatusForm: {},
      dataStatusRules: {
        remark: [
          { required: true, message: '请输入备注以方便追溯历史原因', trigger: ['blur'] }
        ]
      },
      // 会员合并模块
      dialogMergeVisible: false,
      dataMergeForm: {
        card_id: '',
        card_no: '',
        card_type: '',
        mobilephone: '',
        openid: '',
        credit: '',
        oldcard_id: '',
        oldcard_no: '',
        oldcard_type: '',
        oldmobilephone: '',
        oldopenid: '',
        oldcredit: '',
        transfer_credit: false
      },
      dataMergeQuery: {
        type: 1,
        content: ''
      },
      dataMergeRules: {
        card_no: [
          { required: true, message: '请查询保留会员卡号', trigger: ['blur'] }
        ],
        oldcard_no: [
          { required: true, message: '请查询作废会员卡号', trigger: ['blur'] }
        ]
      },
      // 积分明细
      creditTitle: '积分明细：查询【】',
      dialogCreditVisible: false,
      creditList: [],
      creditListLoading: true,
      creditListQuery: {
        page: 1,
        pageSize: 10,
        totalCount: 1,
        card_id: ''
      },
      creditColumns: [
        {
          prop: 'proc_date',
          label: '时间',
          width: '150'
        },
        {
          prop: 'digest',
          label: '备注',
          width: '120'
        },
        {
          prop: 'benefit_change_amo',
          label: '积分',
          render: (h, params) => {
            const credit = params.row.benefit_change_amo
            if (credit > 0) {
              return h('el-tag', { props: { type: 'success' }}, '+' + credit)
            } else {
              return h('el-tag', { props: { type: 'danger' }}, credit)
            }
          }
        },
        {
          prop: 'oper_pern',
          label: '操作人',
          width: '150'
        }
      ],
      // 积分操作
      rechargeTitle: '积分操作',
      dialogRechargeVisible: false,
      rechargeForm: {},
      rechargeRules: {
        credit: [
          { required: true, message: '请输入积分', trigger: ['blur'] }
        ],
        note: [
          { required: true, message: '请输入备注', trigger: ['blur'] }
        ]
      },

      // 详情
      detailTitle: '会员详情',
      dialogDetailVisible: false,
      detailForm: {},
      detailRules: {},
      // 等级列表
      level: [],
      // 渠道列表
      channel: [],
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
      // this.hdlList()
    },
    // 初始化数据
    initData() {
      this.api.data().then(res => {
        this.level = res.data.level
        this.channel = res.data.channel
        /* this.label = res.data.label
        this.group = res.data.group */
      })
    },
    // 初始化会员等级数据源
    getLevel(type) {
      let title = ''
      if (type != null) {
        this.level.map(v => {
          if (parseInt(v.level) === parseInt(type)) {
            title = v.levelName
          }
        })
      }
      return title
    },
    // 初始化会员渠道数据源
    getChannel(type) {
      let title = ''
      if (type != null) {
        this.channel.map(v => {
          if (parseInt(v.type) === parseInt(type)) {
            title = v.name
          }
        })
      }
      return title
    },
    // 打开更改会员状态弹窗
    handleOpenDisable(row) {
      const self = this
      if (row.member_name) {
        self.statusTitle = '更改【' + row.member_name + '】会员状态'
      }
      // 初始化数据源
      self.dataStatusForm = Object.assign({}, row)
      // 打开弹框状态
      self.dialogStatusVisible = true
      // 清除表单验证状态
      self.$nextTick(() => {
        self.$refs['dataStatusForm'].clearValidate()
      })
    },
    // 提交更新数据事件
    hdlUpdate() {
      const self = this
      self.$confirm('此操作将修改 【' + self.dataForm.member_name + '】 信息，是否继续？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        // 效验数据格式
        self.$refs['dialogForm'].validate((valid) => {
          if (valid) {
            // 提交更新数据
            self.api.update(self.dataForm).then(() => {
              // 刷新数据列表
              self.hdlList()
              // 关闭弹窗状态
              self.dialogFormVisible = false
              // 通知信息
              self.$u.msg('更新成功')
            })
          }
        })
      }).catch(() => {
      })
    },
    // 更改会员事件
    handleDisable() {
      const self = this
      self.$confirm('此操作将更改 【' + self.dataStatusForm.member_name + '】 的会员状态，是否继续？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        // 效验数据格式
        self.$refs['dataStatusForm'].validate((valid) => {
          if (valid) {
            // 提交更新数据
            self.api.disable(self.dataStatusForm).then(() => {
              // 刷新数据列表
              self.hdlList()
              // 关闭弹窗状态
              self.dialogStatusVisible = false
              // 通知信息
              self.$u.msg('更改成功')
            })
          }
        })
      }).catch(() => {
      })
    },
    // 检查会员健康状态
    handleHealth(row) {
      const self = this
      self.$confirm('此操作将检测 【' + row.member_name + '】 的健康状态，是否继续？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.health(row).then(res => {
          const { isMobile, isOpenid } = res.data
          const content = '检测手机号:' + (isMobile ? '已重复' : '正常')
          const content2 = '检测OPENID:' + (isOpenid ? '已重复' : '正常')

          self.$alert(content + '<br/>' + content2, '检测 【' + row.member_name + '】 的健康状态结果', {
            dangerouslyUseHTMLString: true,
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    },
    // 查询单个会员数据
    handleQuery() {
      this.api.query(this.dataMergeQuery).then(res => {
        const { card_id, card_no, card_type, mobilephone, openid, credit } = res.data

        if (this.dataMergeQuery.type === 1 || this.dataMergeQuery.type === 2) {
          const d = {
            card_id,
            card_no,
            card_type,
            mobilephone,
            openid,
            credit
          }
          // 保留会员
          this.dataMergeForm = Object.assign(this.dataMergeForm, d)
        } else {
          const d = {
            oldcard_id: card_id,
            oldcard_no: card_no,
            oldcard_type: card_type,
            oldmobilephone: mobilephone,
            oldopenid: openid,
            oldcredit: credit
          }
          // 作废会员
          this.dataMergeForm = Object.assign(this.dataMergeForm, d)
        }
        console.log('打印数据：' + JSON.stringify(this.dataMergeForm))
        this.$u.msg('查询成功')
      })
    },
    // 打开会员合并模块
    openMerge() {
      const self = this
      // 初始化查询数据
      self.dataMergeQuery = {
        type: 1,
        content: ''
      }
      // 初始化数据源
      self.dataMergeForm = {
        card_id: '',
        card_no: '',
        card_type: '',
        mobilephone: '',
        openid: '',
        credit: '',
        oldcard_id: '',
        oldcard_no: '',
        oldcard_type: '',
        oldmobilephone: '',
        oldopenid: '',
        oldcredit: '',
        transfer_credit: false
      }
      // 打开弹框状态
      self.dialogMergeVisible = true
      // 清除表单验证状态
      self.$nextTick(() => {
        self.$refs['dataMergeForm'].clearValidate()
      })
    },
    // 提交会员合并数据
    handleMerge() {
      const self = this
      self.$refs['dataMergeForm'].validate((valid) => {
        if (valid) {
          self.$confirm('此操作将合并保留 【' + self.dataMergeForm.card_no + '】，作废【' + self.dataMergeForm.oldcard_no + '】，是否继续？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
            self.api.merge(self.dataMergeForm).then(() => {
              self.hdlList()
              self.dialogMergeVisible = false
              self.$u.msg('合并成功')
            })
          }).catch(() => {
          })
        }
      })
    },
    // 解绑会员OPENID记录
    handleUnbindOpenid(row) {
      const self = this
      self.$confirm('此操作将解除 【' + row.member_name + '】 的OPENID绑定，是否继续？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.unbind(row).then(res => {
          this.hdlList()
          this.$u.msg('解绑成功')
        })
      }).catch(() => {
      })
    },
    // 打开积分明细弹窗
    handleCreditDetaile(row) {
      const self = this
      const { card_id, member_name } = row
      self.creditListQuery.card_id = card_id
      if (member_name) {
        self.creditTitle = '积分明细：查询【' + row.member_name + '】'
      }
      // 打开弹框状态
      self.dialogCreditVisible = true
      self.handleCreditList()
    },
    // 获取积分明细列表
    handleCreditList() {
      const self = this
      // 打开加载状态
      self.creditListLoading = true
      // 查询分页数据
      self.api.creditList(self.creditListQuery).then(res => {
        // 获取分页列表数据
        self.creditList = res.data.list
        // 获取分页数据,总页数
        self.creditListQuery.totalCount = res.data.totalCount
        // 关闭加载状态
        setTimeout(() => {
          self.creditListLoading = false
        }, 0.5 * 1000)
      })
    },
    // 打开积分操作弹窗
    handleOpenCredit(row) {
      const self = this
      row.status = 0
      row.credit = 0
      row.note = '权益直充与扣减'
      self.rechargeForm = Object.assign({}, row)
      // 打开弹框状态
      self.dialogRechargeVisible = true
      this.$nextTick(() => {
        this.$refs['rechargeForm'].clearValidate()
      })
    },
    // 提交积分操作事件
    handleCredit() {
      const self = this
      const { card_id, card_no, status, credit, note } = self.rechargeForm
      if (!self.$u.test.digits(credit)) {
        this.$u.msg('积分必须为整数', 'error')
        return
      }
      if (!self.$u.test.range(credit, [1, 1000000])) {
        this.$u.msg('积分不在"1-1000000"这个范围内', 'error')
        return
      }
      let tips = '您确定充值' + credit + '积分,请谨慎操作'
      if (status === 1) {
        tips = '您确定扣除' + credit + '积分,请谨慎操作'
      }
      self.$confirm(tips, '提示', { type: 'warning', confirmButtonText: '确定', cancelButtonText: '取消' }).then(() => {
        self.$refs['rechargeForm'].validate((valid) => {
          if (valid) {
            // 转换数值
            const credit2 = status === 1 ? -credit : credit
            const data = { card_id, card_no, 'credit': credit2, note }
            self.api.credit(data).then(() => {
              // 刷新数据列表
              self.hdlList()
              // 关闭弹窗状态
              self.dialogRechargeVisible = false
              // 通知信息
              self.$u.msg('操作成功')
            }).catch(() => {
            })
          }
        })
      }).catch(() => {
      })
    }
  }
}
</script>
<style>
.el-select .el-input {
  width: 220px;
}
</style>
<style lang="scss" scoped>
  .app-container{
    .item >>> .el-form-item__content {
      display: flex;
    }
    .status {
      ::v-deep{
        .el-input{
          width: 100px;
        }
      }
    }
  }
</style>
