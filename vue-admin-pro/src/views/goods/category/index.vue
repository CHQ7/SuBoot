<template>
  <div class="app-container">

    <u-page title="商品分类">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="handleOpen()">
          {{ textMap['create'] }}
        </el-button>
        <el-button type="success" @click="handleBatch">批量新增分类</el-button>
        <el-button icon="el-icon-setting" type="warning" @click="handleOpenConfig">设置分类层级</el-button>
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
          <el-table-column label="操作" align="center">
            <template slot-scope="{ row }">
              <el-button v-if="listQuery.level >= 1 && row.level === 0" type="text" @click="handleCreateSub(row)">新增二级分类</el-button>
              <el-button v-if="listQuery.level >= 2 && row.level === 1" type="text" @click="handleCreateSub(row)">新增三级分类</el-button>
              <el-button type="text" @click="hdlEdit(row)">
                编辑
              </el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.name)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item v-if="createFromSub" prop="parentId" label="上级分类">
          <el-input v-model="dataForm.parentName" type="text" :disabled="true" />
        </el-form-item>

        <el-form-item prop="name" label="分类名称">
          <el-input v-model="dataForm.name" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="thumb" label="分类图片">
          <u-upload v-model="dataForm.thumb" name="file" />
        </el-form-item>

        <el-form-item prop="disabled" label="启用状态">
          <el-switch v-model="dataForm.disabled" />
        </el-form-item>
      </el-form>
    </u-dialog>

    <u-dialog :title="configTitle" :show.sync="configFormVisible" @confirm="hdlUpdateConfig">
      <el-form ref="configForm" :model="configForm" label-width="80px">

        <el-form-item prop="level" label="分类级别">
          <el-radio-group v-model="configForm.level">
            <el-radio :label="0">一级</el-radio>
            <el-radio :label="1">二级</el-radio>
            <el-radio :label="2">三级</el-radio>
          </el-radio-group>
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
        :props="props"
      >
        <span slot-scope="scope" class="custom-tree-node">
          <span>{{ scope.node.label }}</span>
        </span>
      </el-tree>
    </u-dialog>

    <u-dialog title="批量新增分类" :show.sync="batchVisible" width="600px" @confirm="batch">
      <el-form ref="batchForm" :model="batchForm" :rules="batchRules" label-position="top">

        <el-form-item v-if="listQuery.level !== 0" prop="parentId" label="上级分类">
          <el-cascader
            v-model="batchForm.parentId"
            style="width: 100%"
            :options="listData"
            :props="{value:'id', label:'name', disabled:true , children:'children', checkStrictly: true, expandTrigger: 'hover'}"
            placeholder="不选默认一级分类,请选择上级分类"
            :show-all-levels="false"
            clearable
            @change="handleCascaderBatchChange"
          />
        </el-form-item>

        <el-form-item prop="data" label="参数项">
          <el-input
            v-model="batchForm.data"
            type="textarea"
            :rows="4"
            placeholder="批量新增分类名称,每行一个"
            clearable
          />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>
export default {
  data() {
    return {
      api: this.$u.api.GoodsCategory,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '分类名称',
          align: 'left'
        },
        {
          prop: 'thumb',
          label: '分类图片',
          align: 'left',
          image: true
        },
        {
          prop: 'disabled',
          label: '状态',
          align: 'left',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.name, '1')
          }
        }
      ],

      list: [],
      listLoading: true,
      // 开关设置查询不需要分页列表数据
      listState: true,
      listOptions: {
        treeProps: {
          children: 'children',
          hasChildren: 'hasChildren1'
        }
      },
      // 查询数据
      listQuery: {
        level: 0
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑分类',
        create: '新增一级分类'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
        ]
      },
      // 子菜单开关
      createFromSub: false,
      // 排序
      sortDialogVisible: false,
      sortData: [],
      props: {
        label: 'name',
        value: 'id',
        children: 'children'
      },
      // 设置分类层级
      configTitle: '设置分类层级',
      configFormVisible: false,
      configForm: {},
      // 批量新增分类
      batchVisible: false,
      batchForm: {},
      listData: [],
      batchRules: {
        data: [
          { required: true, message: '请输入参数项', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.initConfig()
  },
  methods: {
    initConfig() {
      // 初始化配置数据源
      this.api.config().then(res => {
        // 初始化数据源
        this.configForm = res.data
        this.listQuery.level = this.configForm.level
        this.hdlList()
      })
    },
    handleOpenConfig() {
      // 打开弹框状态
      this.configFormVisible = true
    },
    /**
     * 提交配置更新数据事件
     */
    hdlUpdateConfig() {
      const self = this
      // 提交更新数据
      self.api.updateConfig(self.configForm).then(() => {
        // 刷新层级
        self.listQuery.level = self.configForm.level
        // 刷新数据列表
        self.hdlList()
        // 关闭弹窗状态
        self.configFormVisible = false
        // 通知信息
        self.$u.msg('设置更新成功')
      })
    },
    handleOpen() {
      // 设置默认信息
      this.createFromSub = false
      this.hdlOpen({
        parentId: '',
        disabled: true
      })
    },
    // 子节点新增事件
    handleCreateSub(row) {
      this.handleOpen()
      this.dataForm.parentId = row.id
      this.dataForm.parentName = row.name
      // 分类等级
      this.dataForm.level = row.level + 1
      this.createFromSub = true
    },
    // 打开排序
    handleSort() {
      this.sortDialogVisible = true
      // 获取数据
      this.sortData = Object.assign([], this.list)
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
    // 编辑事件
    hdlEdit(row) {
      this.api.fetch(row.id).then(res => {
        this.createFromSub = true
        this.dataForm = res.data
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dialogForm'].clearValidate()
        })
      })
    },
    // 打开批量弹窗事件
    handleBatch() {
      this.initBatchData()
      this.batchVisible = true
      this.batchForm = {}
      this.$nextTick(() => {
        this.$refs['batchForm'].clearValidate()
      })
    },
    // 批量级联选择器
    handleCascaderBatchChange(value) {
      this.$u.log.primary(JSON.stringify(value))
      this.batchForm.parentId = value[value.length - 1] || ''
      this.batchForm.level = value.length
      this.$u.log.primary(JSON.stringify(this.batchForm.level))
    },
    // 批量节点选项初始化
    initBatchData() {
      // 获取列表数据
      const tempLevel = this.listQuery.level - 1
      if (tempLevel < 0) {
        this.listData = []
      } else {
        this.api.list({ level: tempLevel }).then(res => {
          this.listData = this.$u.tree(res.data)
        })
      }
    },
    // 提交批量新增分类数据
    batch() {
      this.$refs['batchForm'].validate((valid) => {
        if (valid) {
          this.$u.log.primary(JSON.stringify(this.batchForm))
          this.api.batch(this.batchForm).then(() => {
            this.hdlList()
            this.batchVisible = false
            this.$u.msg('批量新增成功')
          })
        }
      })
    }

  }
}
</script>
