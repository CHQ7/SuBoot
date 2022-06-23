<template>
  <div class="app-container">

    <u-page title="会员渠道">
      <div slot="header">
        <el-button type="primary" icon="el-icon-refresh-left" @click="hdlSynchro()">
          同步会员渠道
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="渠道名称" prop="name">
            <el-input v-model="listQuery.name" placeholder="请输入渠道名称" clearable />
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
        @selection-change="hdlSelectChange"
      >
        <template v-slot:right>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="{ row }">
              <el-button type="text" @click="hdlEdit(row)">编辑</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.name)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="type" label="渠道类型">
          <el-input v-model="dataForm.type" placeholder="请输入渠道类型" disabled />
        </el-form-item>

        <el-form-item prop="name" label="渠道名称">
          <el-input v-model="dataForm.name" placeholder="请输入渠道名称" clearable />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.MemberChannel,
      // 表格
      columns: [
        {
          prop: 'type',
          label: '渠道类型'
        },
        {
          prop: 'name',
          label: '渠道名称'
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
        mutiSelect: true
      },
      listQuery: {
        page: 1,
        pageSize: 10,
        totalCount: 1,
        name: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑会员渠道',
        create: '新增会员渠道'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '请输入会员渠道名称', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        disabled: true
      }
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    // 同步事件
    hdlSynchro() {
      const self = this
      self.$confirm('此操作将同步数据是否继续？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchro().then(res => {
          // 同步完成，刷新一下
          this.hdlList()
          self.$alert('同步数据成功', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    }
  }
}
</script>
