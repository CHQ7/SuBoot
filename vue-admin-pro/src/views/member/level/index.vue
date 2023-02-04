<template>
  <div class="app-container">

    <u-page title="用户等级">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="等级名称" prop="name">
            <el-input v-model="listQuery.name" placeholder="关键词查询" clearable />
          </el-form-item>
          <el-form-item label="等级状态" prop="status">
            <u-status v-model="listQuery.status" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
          </el-form-item>
          <el-dropdown>
            <el-button type="primary">
              批量操作<i class="el-icon-arrow-down el-icon--right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :disabled="selectData.length===0" @click.native="hdlDel">批量删除</el-dropdown-item>
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
              <el-button v-if="row.id !== 0" type="text" @click="hdlDelete(row.id, row.name)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="id" label="等级权重">
          <el-select v-model="dataForm.id" placeholder="请选择权重" clearable>
            <el-option v-for="(item,index) in 100" :key="index" :label="index" :value="index" />
          </el-select>
          <u-tip-info title="数字越大，等级权重越高，等级权重不可重复" />
        </el-form-item>

        <el-form-item prop="name" label="等级名称">
          <el-input v-model="dataForm.name" placeholder="请输入等级名称" clearable maxlength="6" show-word-limit style="width: 300px" />
        </el-form-item>

        <el-form-item v-if="dataForm.id !== 0" label="状态" prop="status">
          <el-radio-group v-model="dataForm.status">
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
      api: this.$u.api.MemberLevel,
      // 表格
      columns: [
        {
          prop: 'id',
          label: '等级',
          formatter: (row) => {
            if (row.id === 0) {
              return '默认'
            }
            return row.id
          }
        },
        {
          prop: 'name',
          label: '等级名称'
        },
        {
          prop: 'status',
          label: '状态',
          render: (h, params) => {
            if (params.row.status) {
              return h('el-tag', { props: { type: 'success' }}, '已启用')
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '禁用')
            }
          }
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
        name: '',
        status: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑用户等级',
        create: '新增用户等级'
      },
      dataForm: {},
      rules: {
        id: [
          { required: true, message: '请选择等级权重', trigger: ['blur'] }
        ],
        name: [
          { required: true, message: '请输入等级名称', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        status: true
      }
    }
  },
  created() {
    this.hdlList()
  }
}
</script>
