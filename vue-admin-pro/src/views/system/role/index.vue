<template>
  <div class="app-container">

    <u-page title="角色管理">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="角色名称">
            <el-input v-model="listQuery.name" placeholder="请输入角色名称" clearable />
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
              <el-dropdown>
                <el-button type="text">更多</el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="handleMenu(row)">授权菜单</el-dropdown-item>
                  <el-dropdown-item divided @click.native="hdlDelete(row.id, row.name)">删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog append-to-body :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="name" label="角色名称">
          <el-input v-model="dataForm.name" placeholder="请输入角色名称" clearable maxlength="30" show-word-limit />
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

    <u-dialog :title="menuDialogTitle" :show.sync="menuDialogVisible" @confirm="menuSubmit()">
      <el-row style="margin-bottom: 3px;">
        <el-button size="mini" @click="menuRoleSelAll">全选</el-button>
        <el-button size="mini" @click="menuRoleSelClear">清空</el-button>
      </el-row>
      <el-tree
        ref="doMenuTree"
        :data="doMenuData"
        :default-checked-keys="doMenuCheckedData"
        show-checkbox
        node-key="id"
        :props="defaultProps"
      >
        <span slot-scope="scope" class="custom-tree-node">
          <span>{{ scope.node.label }}</span>
          <span>
            <el-tag v-if="scope.data.type==='menu'" type="success">菜单</el-tag>
            <el-tag v-if="scope.data.type==='data'">权限</el-tag>
          </span>
        </span>
      </el-tree>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    const validateName = function(rule, value, callback) {
      const regex = new RegExp('^[a-zA-Z][a-zA-Z0-9_]{1,19}$')
      if (!regex.test(value)) {
        callback(new Error('唯一编码以字母开头2-20字,允许使用字母/数字/下划线'))
      } else {
        callback()
      }
    }
    return {
      api: this.$u.api.SystemRole,

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
          label: '角色名称'
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
        update: '编辑角色',
        create: '新增角色'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '角色名称', trigger: ['blur', 'change'] }
        ],
        code: [
          { required: true, message: '唯一编码', trigger: ['blur', 'change'] },
          { validator: validateName, trigger: ['blur', 'change'] }
        ]
      },

      newForm: {
        disabled: true
      },

      // 权限
      addMenuData: [],
      doMenuData: [],
      // 已分配的权限选中状态
      doMenuCheckedData: [],
      defaultProps: {
        children: 'children',
        label: 'name',
        disabled: true
      },

      menuDialogTitle: '授权菜单',
      menuDialogVisible: false, // 分配权限
      roleId: '' // 当前角色ID
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    // 提交更新数据事件
    hdlUpdate() {
      const self = this
      // 效验数据格式
      self.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          delete self.dataForm.menus
          delete self.dataForm.users
          // 提交更新数据
          self.api.update(self.dataForm).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.dialogFormVisible = false
            // 通知信息
            self.$u.msg('更新成功')
          })
        }
      })
    },
    // 授权菜单:打开窗口
    handleMenu(row) {
      this.menuDialogVisible = true
      this.roleId = row.id
      this.menuLoad()
    },
    // 授权菜单:加载数据
    menuLoad() {
      const self = this
      this.api.menu(this.roleId).then(res => {
        self.doMenuData = res.data.menu
        self.doMenuCheckedData = res.data.cmenu
      })
    },
    // 授权菜单:全选
    menuRoleSelAll() {
      const ids = []
      this.$u.treeIds(ids, this.doMenuData)
      this.$refs['doMenuTree'].setCheckedKeys(ids)
    },
    // 授权菜单:清空
    menuRoleSelClear() {
      this.$refs['doMenuTree'].setCheckedKeys([])
    },
    // 授权菜单:提交数据
    menuSubmit() {
      const self = this
      const ids = self.$refs['doMenuTree'].getCheckedKeys()
      if (!ids || ids.length === 0) {
        self.$u.toast('请选择菜单或数据权限', 'warning')
        return
      }
      this.api.menuUpdate({ roleId: self.roleId, menuIds: ids.toString() }).then(res => {
        self.menuDialogVisible = false
        self.$u.msg('编辑成功')
      })
    }
  }
}
</script>
