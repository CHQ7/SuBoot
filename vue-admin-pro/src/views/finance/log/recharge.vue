<template>
  <div class="app-container">

    <u-page :title="$route.meta.title">

      <u-filtered>
        <el-input v-model="listQuery.keyword" placeholder="查询充值单号" style="width: 200px;" clearable @keyup.enter.native="hdlFilter" />
        <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
      </u-filtered>

      <u-table
        :data="list"
        :loading="listLoading"
        :columns="columns"
        :pagination.sync="listQuery"
        :fetch="hdlList"
      />
    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="hdlUpdate()">
      <el-form
        ref="dialogForm"
        :model="dataForm"
        status-icon
        label-width="100px"
        class="demo-noteForm"
      >
        <el-form-item label="充值单号" prop="outTradeNo">
          <el-input v-model="dataForm.outTradeNo" :disabled="true" />
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="dataForm.note" autocomplete="off" />
        </el-form-item>
      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.FinanceRecharge,
      // 表格
      columns: [
        {
          prop: 'outTradeNo',
          label: '充值单号'
        },
        {
          prop: 'member',
          label: '昵称',
          formatter: (row, column) => {
            if (!this.$u.test.empty(row.member)) {
              return row.member.nickname
            }
          }
        },
        {
          prop: 'member',
          label: '会员信息',
          formatter: (row, column) => {
            if (!this.$u.test.empty(row.member)) {
              let realname = ''
              let mobile = ''
              if (!this.$u.test.empty(row.member.realname)) {
                realname = row.member.realname
              } else {
                realname = '匿名'
              }
              if (!this.$u.test.empty(row.member.mobile)) {
                mobile = row.member.mobile
              } else {
                mobile = '暂无'
              }

              return `${realname}<br>${mobile}`
            }
          }
        },
        {
          prop: 'money',
          label: '充值金额/元',
          render: (h, params) => {
            const money = this.$u.amount.format(params.row.money)
            if (params.row.money > 0) {
              return h('span', { style: { color: '#4CAF50 !important', fontWeight: 'bold' }}, '+' + money)
            } else {
              return h('span', { style: { color: '#F44336 !important', fontWeight: 'bold' }}, money)
            }
          }
        },
        {
          prop: 'credit1',
          label: '余额/元',
          render: (h, params) => {
            return h('span', { style: { color: '#4CAF50 !important', fontWeight: 'bold' }}, this.$u.amount.format(params.row.credit1))
          }
        },
        {
          prop: 'successTime',
          label: '充值时间',
          timestamp: true
        },
        {
          prop: 'applytype',
          label: '充值方式',
          render: (h, params) => {
            let title = ''
            if (params.row.rechargeType === 'system') {
              title = '后台'
            }
            const vnode = []
            vnode.push(h('svg-icon', { props: { iconClass: 'finance' }, style: { color: '#ffc000', marginRight: '4px', fontSize: '14px' }}))
            vnode.push(h('span', { style: { color: '#44abf7' }}, title))
            return vnode
          }
        },
        {
          prop: 'flag',
          label: '状态',
          render: (h, params) => {
            let title = '待充值'
            if (params.row.flag === 1) {
              title = '成功'
            }
            return h('span', { style: { color: params.row.flag !== 0 ? ' #54c952' : '' }}, title)
          }
        },
        {
          prop: 'note',
          label: '备注'
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
        update: '编辑充值记录',
        create: '新增充值记录'
      },
      dataForm: {},
      rules: {}
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    hdlUpdate() {
      this.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          this.api.update({ id: this.dataForm.id, note: this.dataForm.note }).then(() => {
            // 刷新数据列表
            this.hdlList()
            // 关闭弹窗状态
            this.dialogFormVisible = false
            // 通知信息
            this.$u.msg('修改成功')
          })
        }
      })
    }
  }
}
</script>
