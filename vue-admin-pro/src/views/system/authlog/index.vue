<template>
  <div class="app-container">
    <u-page title="登录日志">
      <u-filtered>

        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="时间范围">
            <el-date-picker
              v-model="searchDate"
              type="daterange"
              :picker-options="pickerOptions"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              value-format="timestamp"
              style="width:300px"
            />
          </el-form-item>
          <el-form-item label="登录用户">
            <el-input v-model="listQuery.name" placeholder="请输入登录用户" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
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
              <el-button type="text" @click="hdlEdit(row)">详情</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog title="日志详情" :show.sync="dialogFormVisible" :footer="false">
      <el-form ref="dialogForm" :model="dataForm" label-width="80px">

        <el-form-item label="操作模块">
          {{ dataForm.tag }} / {{ dataForm.msg }}
        </el-form-item>

        <el-form-item label="登录信息">
          {{ dataForm.name }} / {{ dataForm.ip }} / {{ dataForm.location }}
        </el-form-item>

        <el-form-item label="请求地址">
          {{ dataForm.method }} {{ dataForm.url }}
        </el-form-item>

        <el-form-item label="操作终端">
          {{ dataForm.browser }} / {{ dataForm.os }}
        </el-form-item>

      </el-form>
    </u-dialog>
  </div>
</template>
<script>

export default {
  data: function() {
    return {
      api: this.$u.api.SystemAuthlog,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '登录用户',
          sortable: true,
          align: 'left'
        },
        {
          prop: 'ip',
          label: 'IP',
          align: 'left'
        },
        {
          prop: 'location',
          label: 'IP地址',
          align: 'left'
        },
        {
          prop: 'browser',
          label: '浏览器',
          align: 'left'
        },
        {
          prop: 'os',
          label: '操作系统',
          align: 'left'
        },
        {
          prop: 'msg',
          label: '操作信息',
          align: 'left'
        },
        {
          prop: 'createdAt',
          label: '操作时间',
          sortable: true,
          timestamp: true,
          align: 'left'
        }
      ],

      list: [],
      listLoading: true,
      listOptions: {
        mutiSelect: true, // boolean 是否多选
        stripe: true // boolean 斑马纹
      },
      listQuery: {
        pageNumber: 1,
        pageSize: 20,
        totalCount: 1,
        beginTime: '',
        endTime: '',
        name: '',
        ip: '',
        location: ''
      },
      searchDate: [],
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      dialogFormVisible: false,
      dataForm: {}

    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    // 搜索事件
    handleFilter() {
      // 获取时间控件的开始日期至结束日期
      if (this.searchDate) {
        this.listQuery.beginTime = this.searchDate[0]
        this.listQuery.endTime = this.searchDate[1]
      } else {
        this.listQuery.beginTime = ''
        this.listQuery.endTime = ''
      }
      this.hdlFilter()
    }
  }
}
</script>

