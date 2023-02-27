<template>
  <div class="app-container">
    <u-page title="操作日志">
      <u-filtered>

        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="时间范围">
            <u-date-time-picker v-model="searchDate" @change="changeDate" />
          </el-form-item>
          <el-form-item label="操作人">
            <el-input v-model="listQuery.name" placeholder="请输入操作人" clearable />
          </el-form-item>
          <el-form-item label="操作地址">
            <el-input v-model="listQuery.ip" placeholder="请输入操作地址" clearable />
          </el-form-item>
          <el-form-item label="操作地点">
            <el-input v-model="listQuery.location" placeholder="请输入操作地点" clearable />
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
              <el-button type="text" @click="hdlEdit(row)">详情</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog title="日志详情" :show.sync="dialogFormVisible" :footer="false">
      <el-form ref="dialogForm" :model="dataForm" label-width="80px">

        <el-form-item label="操作时间">
          {{ dataForm.createdAt | date }}
        </el-form-item>

        <el-form-item label="操作模块">
          {{ dataForm.tag }}
        </el-form-item>

        <el-form-item label="执行动作">
          {{ dataForm.msg }}
        </el-form-item>

        <el-form-item label="操作信息">
          {{ dataForm.createdBy }} / {{ dataForm.ip }} / {{ dataForm.location }}
        </el-form-item>

        <el-form-item label="请求地址">
          {{ dataForm.method }} {{ dataForm.url }}
        </el-form-item>

        <el-form-item label="请求终端">
          {{ dataForm.os }} / {{ dataForm.browser }}
        </el-form-item>

        <el-form-item label="请求方法">
          {{ dataForm.src }}
        </el-form-item>

        <el-form-item label="请求参数">
          <el-alert :title="dataForm.param" type="info" :closable="false" />
        </el-form-item>

        <el-form-item label="响应结果">
          <el-alert :title="dataForm.result" type="info" :closable="false" />
        </el-form-item>

        <el-form-item label="请求耗时">
          <el-tag type="success">{{ dataForm.executeTime }} ms</el-tag>
        </el-form-item>

      </el-form>
    </u-dialog>
  </div>
</template>
<script>

export default {
  data: function() {
    return {
      api: this.$u.api.SystemLog,
      // 表格
      columns: [
        {
          prop: 'tag',
          label: '操作模块',
          align: 'left'
        },
        {
          prop: 'msg',
          label: '操作类型',
          align: 'left'
        },
        {
          prop: 'createdBy',
          label: '操作人',
          align: 'left'
        },
        {
          prop: 'ip',
          label: '操作地址',
          align: 'left'
        },
        {
          prop: 'location',
          label: '操作地点',
          align: 'left'
        },
        {
          prop: 'success',
          label: '状态',
          align: 'left',
          render: (h, params) => {
            let title = '异常'
            let type = 'danger'
            if (params.row.success) {
              title = '正常'
              type = 'success'
            }
            return h('el-tag', { props: { size: 'small', type: type }}, title)
          }
        },
        {
          prop: 'createdAt',
          label: '操作时间',
          sortable: true,
          timestamp: true,
          align: 'left'
        },
        {
          prop: 'executeTime',
          label: '耗时',
          align: 'left',
          render: (h, params) => {
            return h('el-tag', { props: { size: 'small', type: 'success' }}, params.row.executeTime + 'ms')
          }
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
      dialogFormVisible: false,
      dataForm: {}

    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    changeDate(item) {
      if (item) {
        this.listQuery.beginTime = item[0]
        this.listQuery.endTime = item[1]
      } else {
        this.listQuery.beginTime = ''
        this.listQuery.endTime = ''
      }
    }
  }
}
</script>

