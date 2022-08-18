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

          <el-form-item label="状态" prop="disabled">
            <el-select v-model="listQuery.disabled" placeholder="请选择状态" class="status" clearable>
              <el-option
                v-for="item in statusList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
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
            <el-radio :label="true">正常</el-radio>
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
        ref="tree"
        node-key="id"
        :data="doMenuData"
        :default-checked-keys="doMenuCheckedData"
        show-checkbox
        highlight-current
        accordion
        check-strictly
        :props="{ children: 'children',label: 'name',value:'id',disabled:true }"
        :default-expand-all="true"
        @check="clickDeal"
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
          prop: 'name',
          label: '角色名称'
        },
        {
          prop: 'code',
          label: '唯一编码'
        },
        {
          prop: 'location',
          label: '排序',
          width: '70'
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
        code: '',
        disabled: ''
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

      menuDialogTitle: '授权菜单',
      menuDialogVisible: false, // 分配权限
      roleId: '', // 当前角色ID
      statusList: [
        {
          value: true,
          label: '正常'
        }, {
          value: false,
          label: '禁用'
        }
      ]
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
        const { menu, cmenu } = res.data
        self.doMenuData = menu
        self.doMenuCheckedData = cmenu
      })
    },
    // 授权菜单:全选
    menuRoleSelAll() {
      const ids = []
      this.$u.treeIds(ids, this.doMenuData)
      this.$refs.tree.setCheckedKeys(ids)
    },
    // 授权菜单:清空
    menuRoleSelClear() {
      this.$refs.tree.setCheckedKeys([])
    },
    // 授权菜单:提交数据
    menuSubmit() {
      const self = this
      const ids = self.$refs.tree.getCheckedKeys()

      this.api.menuUpdate({ roleId: self.roleId, menuIds: ids.toString() }).then(res => {
        self.menuDialogVisible = false
        self.$u.msg('编辑成功')
      })
    },
    clickDeal(currentObj, treeStatus) {
      // 用于：父子节点严格互不关联时，父节点勾选变化时通知子节点同步变化，实现单向关联。
      const selected = treeStatus.checkedKeys.indexOf(currentObj.id) // -1未选中
      // 选中
      if (selected !== -1) {
        // 子节点只要被选中父节点就被选中
        this.selectedParent(currentObj)
        // 统一处理子节点为相同的勾选状态
        this.uniteChildSame(currentObj, true)
      } else {
        // 未选中 处理子节点全部未选中
        if (currentObj.children.length !== 0) {
          this.uniteChildSame(currentObj, false)
        }
      }
    },
    // 统一处理子节点为相同的勾选状态
    uniteChildSame(treeList, isSelected) {
      this.$refs.tree.setChecked(treeList.id, isSelected)
      if (treeList.children) {
        for (let i = 0; i < treeList.children.length; i++) {
          this.uniteChildSame(treeList.children[i], isSelected)
        }
      }
    },
    // 统一处理父节点为选中
    selectedParent(currentObj) {
      const currentNode = this.$refs.tree.getNode(currentObj)
      if (currentNode.parent.key !== undefined) {
        this.$refs.tree.setChecked(currentNode.parent, true)
        this.selectedParent(currentNode.parent)
      }
    }
  }
}
</script>
