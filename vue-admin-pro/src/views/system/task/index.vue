<template>
  <div class="app-container">

    <u-page title="定时任务">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="任务名称">
            <el-input v-model="listQuery.name" placeholder="请输入任务名称" clearable />
          </el-form-item>
          <el-form-item label="任务状态" prop="disabled">
            <u-status v-model="listQuery.disabled" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
          </el-form-item>
        </el-form>
      </u-filtered>

      <u-table
        :data="list"
        :loading="listLoading"
        :columns="columns"
        :pagination.sync="listQuery"
        :fetch="hdlList"
      >
        <template v-slot:right>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="{ row }">
              <el-button type="text" @click="hdlEdit(row)">编辑</el-button>
              <el-button type="text" @click="hdlNow(row.id)">立即执行</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.name)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-position="left" label-width="120px">

        <el-form-item prop="name" label="任务名称">
          <el-input v-model="dataForm.name" placeholder="任务名称" />
        </el-form-item>

        <el-form-item prop="jobClass" label="执行类">
          <el-input v-model="dataForm.jobClass" placeholder="执行类" />
        </el-form-item>

        <el-form-item prop="cron" label="执行周期">
          <el-input v-model="dataForm.cron" placeholder="Cron表达式">
            <template slot="append">
              <!--<span   style="color: #409EFF" @click="onCron" >表达式生成器</span>-->
            </template>
          </el-input>
        </el-form-item>

        <el-form-item prop="data" label="执行参数">
          <el-input v-model="dataForm.data" placeholder="Json参数" />
        </el-form-item>

        <el-form-item label="任务描述">
          <el-input v-model="dataForm.note" type="textarea" />
        </el-form-item>

        <el-form-item prop="disabled" label="状态">
          <el-radio-group v-model="dataForm.disabled">
            <el-radio :label="true">启用</el-radio>
            <el-radio :label="false">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.SystemTask,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '任务名称'
        },
        {
          prop: 'jobClass',
          label: '执行类'
        },
        {
          prop: 'cron',
          label: '定时规则'
        },
        {
          prop: 'exeAt',
          label: '执行时间',
          timestamp: true
        },
        {
          prop: 'disabled',
          label: '状态',
          render: (h, params) => {
            if (params.row.disabled) {
              return h('el-tag', { props: { type: 'success' }}, '已启用')
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '禁用')
            }
          }
        }
      ],
      list: [],
      listLoading: true,
      listQuery: {
        pageNumber: 1,
        pageSize: 20,
        totalCount: 1,
        name: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑任务',
        create: '新建任务'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '任务名称', trigger: ['blur'] }
        ],
        jobClass: [
          { required: true, message: '执行类', trigger: ['blur'] }
        ],
        cron: [
          { required: true, message: '执行周期', trigger: ['blur'] }
        ]
      },

      newForm: {
        disabled: true
      }
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    /**
     * 立即执行
     * @param id
     * @param title
     */
    hdlNow(id) {
      const self = this
      // 模态对话框组件
      self.$confirm('确定发送执行一次命令？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        // 处理确认事件
        self.$u.api.SystemTask.run(id).then(() => {
          // 刷新数据列表
          self.hdlList()
          // 通知信息
          self.$u.msg('执行成功')
        })
      }).catch(() => {
      })
    }
  }

}
</script>

