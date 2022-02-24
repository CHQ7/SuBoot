<template>
  <div class="app-container">

    <u-page :title="$route.meta.title">

      <u-filtered>
        <el-input v-model="listQuery.keyword" placeholder="查询用户手机号" style="width: 200px;" clearable @keyup.enter.native="hdlFilter" />
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

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.FinanceBalance,
      // 表格
      columns: [
        {
          prop: 'member',
          label: '昵称',
          align: 'left',
          formatter: (row, column) => {
            if (!this.$u.test.empty(row.member)) {
              return row.member.nickname
            }
          }
        },
        {
          prop: 'member',
          label: '会员信息',
          align: 'left',
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
          label: '余额变化',
          align: 'left',
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
          label: '当前余额',
          align: 'left',
          render: (h, params) => {
            return h('span', { style: { color: '#4CAF50 !important', fontWeight: 'bold' }}, this.$u.amount.format(params.row.credit1))
          }
        },
        {
          prop: 'createAt',
          label: '操作时间',
          align: 'left',
          timestamp: true
        },
        {
          prop: 'title',
          label: '标题',
          align: 'left'
        },
        {
          prop: 'note',
          label: '备注',
          align: 'left'
        }
      ],

      list: [],
      listLoading: true,
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        keyword: ''
      }

    }
  },
  created() {
    this.hdlList()
  },
  methods: {
  }
}
</script>
