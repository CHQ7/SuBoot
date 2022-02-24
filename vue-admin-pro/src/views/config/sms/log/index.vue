<template>
  <div class="app-container">

    <u-page title="短信日志">

      <u-filtered>
        <el-input v-model="listQuery.keyword" placeholder="查询手机号" style="width: 200px;" clearable @keyup.enter.native="hdlFilter" />
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
      api: this.$u.api.ConfigSmsLog,
      // 表格
      columns: [
        {
          prop: 'createAt',
          label: '时间',
          timestamp: true
        },
        {
          prop: 'mobile',
          label: '手机号'
        },
        {
          prop: 'flag',
          label: '发送状态',
          render: (h, params) => {
            let title = '失败'
            if (params.row.flag !== 0) {
              title = '成功'
            }
            return h('span', { style: { color: params.row.flag === 0 ? '#f56c6c' : '#54c952' }}, title)
          }
        },
        {
          prop: 'content',
          label: '消息'
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

      // 删除选中数据
      selectData: []

    }
  },
  created() {
    this.hdlList()
  }
}
</script>
