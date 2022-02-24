<template>
  <div class="app-container">

    <u-page title="用户分组">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen()">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="分组名称">
            <el-input v-model="listQuery.groupName" placeholder="请输入分组名称" clearable />
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
              <el-button type="text" @click="hdlDelete(row.id, row.groupName)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="groupName" label="分组名称">
          <el-input v-model="dataForm.groupName" placeholder="请输入分组名称" clearable />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.MemberGroup,
      // 表格
      columns: [
        {
          prop: 'groupName',
          label: '分组名称',
          align: 'left'
        }
      ],

      list: [],
      listLoading: true,
      listOptions: {},
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        groupName: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑分组',
        create: '新增分组'
      },
      dataForm: {},
      rules: {
        groupName: [
          { required: true, message: '请输入分组名称', trigger: ['blur'] }
        ]
      }
    }
  },
  created() {
    this.hdlList()
  }
}
</script>
