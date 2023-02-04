<template>
  <div class="app-container">

    <u-page title="用户标签">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen()">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="标签组名称">
            <el-input v-model="listQuery.name" placeholder="关键词查询" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
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

        <el-form-item prop="name" label="标签名称">
          <el-input v-model="dataForm.name" placeholder="标签名称" clearable maxlength="10" show-word-limit />
        </el-form-item>

        <el-form-item prop="note" label="标签描述">
          <el-input v-model="dataForm.note" placeholder="标签描述" type="textarea" :rows="5" clearable maxlength="100" show-word-limit />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.MemberLabel,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '标签组名称'
        },
        {
          prop: 'note',
          label: '标签组描述'
        }
      ],

      list: [],
      listLoading: true,
      listOptions: {},
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        name: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑标签',
        create: '新增标签'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '请输入标签名称', trigger: ['blur'] }
        ]
      }
    }
  },
  created() {
    this.hdlList()
  }
}
</script>
