<template>
  <div class="app-container">

    <u-page title="菜单管理">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="handleCreate()">{{ textMap['create'] }}</el-button>
        <el-button type="success" @click="handlePermission">批量新增权限</el-button>
        <el-button icon="el-icon-sort" @click="handleSort">排序</el-button>
      </div>

      <u-table
        :data="list"
        :loading="listLoading"
        :options="listOptions"
        :columns="columns"
        :page="false"
      >
        <template v-slot:right>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="{ row }">
              <el-button type="text" @click="handleUpdate(row)">编辑</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.name)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>
    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" width="600px" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="type" label="类型">
          <el-radio-group v-model="dataForm.type" :disabled="dialogStatus === 'update'">
            <el-radio label="menu">菜单</el-radio>
            <el-radio label="data">权限</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="parentId" label="上级">
          <el-cascader
            v-model="dataForm.parentId"
            width="480"
            :options="menu"
            :props="{value:'id', label:'name', disabled:true , children:'children', checkStrictly: true}"
            placeholder="请选择节点"
            :show-all-levels="false"
            clearable
            @change="handleCascaderChange"
          />
        </el-form-item>

        <el-form-item prop="name" label="名称">
          <el-input v-model="dataForm.name" maxlength="100" placeholder="菜单名称" clearable />
        </el-form-item>

        <el-form-item prop="permission" label="权限">
          <el-input v-model="dataForm.permission" maxlength="100" placeholder="权限标识,如 sys.menu" clearable />
        </el-form-item>
        <el-form-item v-if="dataForm.type ==='menu' " prop="router" label="路由">
          <el-input v-model="dataForm.router" placeholder="请输入路由" clearable />
        </el-form-item>
        <el-form-item v-if="dataForm.type ==='menu' " prop="keepAlive" label="缓存">
          <el-radio-group v-model="dataForm.keepAlive">
            <el-radio :label="true">开启</el-radio>
            <el-radio :label="false">关闭</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="dataForm.type ==='menu' " prop="path" label="路径">
          <el-input v-model="dataForm.path" placeholder="请输入路径" clearable />
        </el-form-item>
        <el-form-item v-if="dataForm.type ==='menu' " prop="icon" label="图标">
          <u-icon-svg-select v-model="dataForm.icon" user-input />
          <span :class="dataForm.icon" />
        </el-form-item>

        <el-form-item prop="disabled" label="状态">
          <el-switch v-model="dataForm.disabled" />
        </el-form-item>

      </el-form>
    </u-dialog>

    <u-dialog title="菜单排序" :show.sync="sortDialogVisible" @confirm="changeSort">
      <el-tree ref="sortMenuTree" :data="menuData" draggable :allow-drop="sortAllowDrop" node-key="id" :props="props">
        <span slot-scope="scope" class="custom-tree-node">
          <span>{{ scope.node.label }}</span>
        </span>
      </el-tree>
    </u-dialog>

    <u-dialog title="批量新增权限" :show.sync="permissionVisible" width="600px" @confirm="batch">
      <el-form ref="permissionForm" :rules="permissionRules" :model="permissionForm" label-width="60px">

        <el-form-item prop="parentId" label="菜单">
          <el-cascader
            v-model="permissionForm.parentId"
            :options="menu"
            :props="{value:'id', label:'name', disabled:true , children:'children', checkStrictly: true}"
            placeholder="请选择菜单"
            :show-all-levels="false"
            clearable
            style="width: 100%"
            @change="handleCascaderPermissionChange"
          />
        </el-form-item>

        <el-form-item prop="name" label="名称">
          <el-input v-model="permissionForm.name" maxlength="100" placeholder="权限名称,例用户" clearable />
        </el-form-item>

        <el-form-item prop="permission" label="权限">
          <el-input v-model="permissionForm.permission" maxlength="100" placeholder="权限标识,例sys.user" clearable />
        </el-form-item>

        <el-form-item label="批量">
          <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAll">全选</el-checkbox>
          <el-checkbox-group v-model="permissionForm.add" @change="handleCheckedData">
            <el-checkbox v-for="d in dataTree" :key="d.value" :label="d.value">{{ d.label }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.SystemMenu,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '菜单名称',
          align: 'left'
        },
        {
          prop: 'router',
          label: '路由',
          align: 'left'
        },
        {
          prop: 'type',
          label: '类型',
          align: 'left',
          render: (h, params) => {
            if (params.row.type === 'menu') {
              return h('el-tag', { props: { type: 'success' }}, '菜单')
            } else {
              return h('el-tag', '权限')
            }
          }
        },
        {
          prop: 'permission',
          label: '权限标识',
          align: 'left'
        },
        {
          prop: 'path',
          label: '路由路径',
          align: 'left'
        },
        {
          prop: 'keepAlive',
          label: '路由缓存',
          align: 'left',
          render: (h, params) => {
            if (params.row.keepAlive) {
              return h('el-tag', { props: { type: 'success' }}, '开启')
            }
          }
        },
        {
          prop: 'disabled',
          label: '启用状态',
          align: 'left',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.name, '2')
          }
        },
        {
          prop: 'createdAt',
          label: '创建时间',
          align: 'left',
          sortable: true,
          timestamp: true
        }
      ],

      list: [],
      listLoading: true,
      // 开关设置查询不需要分页列表数据
      listState: true,
      listOptions: {
        // defaultExpandAll: true,
        treeProps: {
          children: 'children'
        }
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑菜单',
        create: '新增菜单'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '菜单名称', trigger: 'blur' }
        ],
        permission: [
          { required: true, message: '权限标识', trigger: ['blur', 'change'] }
        ],
        router: [
          { required: true, message: '请输入路由', trigger: 'blur' }
        ]
      },
      // 批量新增权限
      permissionVisible: false,
      permissionForm: {},
      menu: [],
      permissionRules: {
        parentId: [
          { required: true, message: '请选择上级菜单', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '权限名称', trigger: 'blur' },
          { pattern: /^[\u4E00-\u9FA5]+$/, message: '权限名称只能为中文' }
        ],
        permission: [
          { required: true, message: '权限标识', trigger: ['blur', 'change'] },
          { pattern: /^[a-z\.]+$/, message: '权限标识只能为小写字母加英文点' }
        ]
      },
      isIndeterminate: true,
      checkAll: false,
      // 批量添加权限数据源
      dataTree: [{ value: 'create', label: '新增' }, { value: 'update', label: '修改' }, { value: 'delete', label: '删除' }, { value: 'sort', label: '排序' }],
      // 排序
      sortDialogVisible: false,
      menuData: [],
      props: {
        label: 'name',
        value: 'id',
        children: 'children'
      }
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    // 查询列表数据
    hdlList() {
      this.api.list(this.listQuery).then(res => {
        // 获取列表数据
        this.list = res.data
        // 初始化数据
        // this.initData()
        // 关闭加载状态
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
      })
    },
    // 新增事件
    handleCreate() {
      this.hdlOpen({
        parentId: '',
        type: 'menu',
        keepAlive: false,
        target: 'data-pjax',
        disabled: true
      })
      // 初始化选项
      this.initPermission()
    },
    // 编辑事件
    handleUpdate(row) {
      // 初始化选项
      this.initPermission()
      this.api.fetch(row.id).then(res => {
        this.dataForm = res.data
        delete this.dataForm.children
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dialogForm'].clearValidate()
        })
      })
    },
    // 级联选择器
    handleCascaderChange(value) {
      this.dataForm.parentId = value[value.length - 1] || ''
    },
    // 打开排序
    handleSort() {
      this.sortDialogVisible = true
      this.menuData = this.list
    },
    // 排序树控制不可跨级拖拽
    sortAllowDrop(moveNode, inNode, type) {
      if (moveNode.data.parentId === inNode.data.parentId) {
        return type === 'prev'
      }
    },
    // 提交排序后的数据
    changeSort() {
      const self = this
      const ids = []
      self.$u.treeIds(ids, self.menuData)
      this.api.sort({ ids: ids.toString() }).then(() => {
        self.sortDialogVisible = false
        self.hdlList()
        self.$u.msg('提交成功')
      })
    },
    // 打开批量新增权限
    handlePermission() {
      // 初始化菜单权限选项
      this.initPermission()
      // 打开弹窗
      this.permissionVisible = true
      // 初始化表单属性
      this.permissionForm = {
        add: []
      }
      // 清除表单验证
      this.$nextTick(() => {
        this.$refs['permissionForm'].clearValidate()
      })
    },
    // 批量新增权限事件:级联选择器
    handleCascaderPermissionChange(value) {
      this.permissionForm.parentId = value[value.length - 1] || ''
    },
    // 批量新增权限事件:全选事件
    handleCheckAll(val) {
      const all = this.dataTree.map(item => { return item.value })
      this.permissionForm.add = val ? all : []
      this.isIndeterminate = false
    },
    // 批量新增权限事件:监听是否全选
    handleCheckedData(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.dataTree.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.dataTree.length
    },
    // 初始化选项
    initPermission() {
      // 赋值给menu
      this.menu = JSON.parse(JSON.stringify(this.list))
      this.menu = this.$u.tree(this.menu)
    },
    // 提交批量新增权限数据
    batch() {
      // 获取表单数据
      const { parentId, name, permission, add } = this.permissionForm

      // 匹配批量快捷新增权限数据
      const data = add.map(item => this.dataTree.filter(d => d.value === item)[0])
      console.log(data)
      // 格式化权限数据
      const authData = JSON.parse(JSON.stringify(data))
      authData.map(item => {
        item.label = item.value === 'sort' ? name + item.label : item.label + name
        item.value = permission + '.' + item.value
      })
      // 组装请求数据
      const d = { parentId: parentId, permissionData: authData }

      this.$refs['permissionForm'].validate((valid) => {
        if (valid) {
          this.api.batch(d).then(() => {
            this.hdlList()
            this.permissionVisible = false
            this.$u.msg('批量新增成功')
          })
        }
      })
    }
  }
}
</script>

