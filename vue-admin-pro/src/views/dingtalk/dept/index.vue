<template>
  <div class="app-container">

    <u-page title="钉钉组织管理">
      <div slot="header">
<!--        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>-->
        <el-button icon="el-icon-sort" @click="handleSort">排序</el-button>
      </div>

      <u-table
        :data="list"
        :loading="listLoading"
        :options="listOptions"
        :columns="columns"
        row-key="id"
        default-expand-all
        :tree-props="{children: 'parentId', hasChildren: 'hasChildren'}"
        :page="false"
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

        <el-form-item prop="parentId" label="上级组织">
          <el-cascader
            v-model="dataForm.parentId"
            style="width: 100%"
            :options="list"
            :props="{value:'id', label:'name', disabled:true , children:'children', checkStrictly: true, expandTrigger: 'hover'}"
            placeholder="请选择上级组织"
            :show-all-levels="false"
            clearable
            @change="handleCascaderChange"
          />
        </el-form-item>

        <el-form-item prop="name" label="组织名称">
          <el-input v-model="dataForm.name" maxlength="30" show-word-limit placeholder="请选择组织名称" clearable />
        </el-form-item>

        <el-form-item prop="code" label="唯一编码">
          <el-input v-model="dataForm.code" placeholder="请输入组织唯一编码" clearable />
        </el-form-item>

        <el-form-item prop="note" label="备注">
          <el-input v-model="dataForm.note" type="textarea" placeholder="请输入备注" clearable />
        </el-form-item>

      </el-form>
    </u-dialog>

    <u-dialog title="排序" :show.sync="sortDialogVisible" @confirm="changeSort">
      <el-tree
        ref="sortMenuTree"
        :data="sortData"
        draggable
        :allow-drop="sortAllowDrop"
        node-key="id"
        :props="{ label: 'name', value: 'id', children: 'children' }"
      >
        <span slot-scope="scope" class="custom-tree-node">
          <span>{{ scope.node.label }}</span>
        </span>
      </el-tree>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    const validateName = function(rule, value, callback) {
      const regex = new RegExp('^[a-zA-Z0-9_]{2,20}$')
      if (!regex.test(value)) {
        callback(new Error('唯一编码2-20字,允许使用字母/数字/下划线'))
      } else {
        callback()
      }
    }
    return {
      api: this.$u.api.DingtalkDept,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '组织名称',
          align: 'left'
        },
        {
          prop: 'code',
          label: '唯一编码'
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
      listOptions: {},
      listQuery: {},

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑组织',
        create: '新增组织'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '请输入组织名称', trigger: ['blur'] }
        ],
        code: [
          { required: true, message: '请输入组织唯一编码', trigger: ['blur', 'change'] },
          { validator: validateName, trigger: ['blur', 'change'] }
        ]
      },
      // 初始化信息
      newForm: {
        parentId: '',
        disabled: true
      },
      // 排序
      listData: [],
      sortDialogVisible: false,
      sortData: []
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
        // this.initData()
        // 关闭加载状态
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
      })
    },
    // (覆盖)提交更新数据事件
    hdlUpdate() {
      const self = this
      // 效验数据格式
      self.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          // fix:移除提交的表单数据包含子节点数据
          delete self.dataForm.children
          // 提交过滤后的表单数据
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
    // 打开排序弹窗
    handleSort() {
      this.sortDialogVisible = true
      // 获取数据
      this.sortData = JSON.parse(JSON.stringify(this.list))
      this.sortData = this.$u.tree(this.sortData)
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
      self.$u.treeIds(ids, self.sortData)
      self.api.sort({ ids: ids.toString() }).then(() => {
        self.sortDialogVisible = false
        self.hdlList()
        self.$u.msg('提交成功')
      })
    },
    // 初始化节点选项
    initData() {
      this.listData = JSON.parse(JSON.stringify(this.list))
      this.listData = this.$u.tree(this.listData)
    },
    // 级联选择器
    handleCascaderChange(value) {
      this.dataForm.parentId = value[value.length - 1] || ''
    }

  }
}
</script>
