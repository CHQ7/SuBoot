<template>
  <div class="app-container">

    <u-page title="钉钉角色组管理">
      <div slot="header">
      <!--
      <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
       -->
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="角色组名称">
            <el-input v-model="listQuery.name" placeholder="请输入角色组名称" clearable />
          </el-form-item>

          <el-form-item label="唯一编码">
            <el-input v-model="listQuery.code" placeholder="请输入唯一编码" clearable />
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
          <el-table-column label="操作" align="center">
            <template slot-scope="{ row }">
              <el-button type="text" @click="hdlEdit(row)">编辑</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.name)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog append-to-body :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="100px">

        <el-form-item prop="name" label="角色组名称">
          <el-input v-model="dataForm.name" placeholder="请输入角色组名称" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="code" label="唯一编码">
          <el-input v-model="dataForm.code" placeholder="请输入唯一编码" clearable />
        </el-form-item>

        <el-form-item prop="location" label="排序">
          <el-input v-model="dataForm.location" placeholder="数字越大，排名越靠前" clearable />
        </el-form-item>

        <el-form-item prop="note" label="备注">
          <el-input v-model="dataForm.note" type="textarea" placeholder="请输入备注" clearable />
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
    const validateName = function(rule, value, callback) {
      const regex = new RegExp('^[a-zA-Z0-9_]{2,20}$')
      if (!regex.test(value)) {
        callback(new Error('唯一编码以字母开头2-20字,允许使用字母/数字/下划线'))
      } else {
        callback()
      }
    }
    return {
      api: this.$u.api.DingtalkRolegroup,

      // 表格
      columns: [
        {
          prop: 'location',
          label: '排序',
          width: '70',
          sortable: true
        },
        {
          prop: 'name',
          label: '角色组名称'
        },
        {
          prop: 'code',
          label: '唯一编码'
        },
        {
          prop: 'disabled',
          label: '状态',
          render: (h, params) => {
            if (params.row.disabled) {
              return h('el-tag', { props: { type: 'success' }}, '正常')
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '禁用')
            }
          }
        },
        {
          prop: 'note',
          label: '备注',
          align: 'left'
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
        mutiSelect: true, // boolean 是否多选
        stripe: true // boolean 斑马纹
      },
      listQuery: {
        page: 1,
        pageSize: 20,
        totalCount: 1,
        name: '',
        code: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑角色组',
        create: '新增角色组'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '角色组名称', trigger: ['blur', 'change'] }
        ],
        code: [
          { required: true, message: '唯一编码', trigger: ['blur', 'change'] },
          { validator: validateName, trigger: ['blur', 'change'] }
        ]
      },

      newForm: {
        disabled: true
      }
    }
  },
  created() {
    this.hdlList()
  }
}
</script>
