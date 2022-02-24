<template>
  <div class="app-container">

    <u-page title="文章分类">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
        <el-button type="success" @click="handleBatch">批量新增分类</el-button>
        <el-button icon="el-icon-sort" @click="handleSort">排序</el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="状态">
            <el-select v-model="listQuery.disabled" placeholder="请选择状态" clearable>
              <el-option
                v-for="item in statusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="分类名称">
            <el-input v-model="listQuery.name" placeholder="请输入分类名称" clearable />
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

        <el-form-item prop="parentId" label="上级分类">
          <el-cascader
            v-model="dataForm.parentId"
            style="width: 100%"
            :options="listData"
            :props="{value:'id', label:'name', disabled:true , children:'children', checkStrictly: true, expandTrigger: 'hover'}"
            placeholder="请选择上级分类"
            :show-all-levels="false"
            clearable
            @change="handleCascaderChange"
          />
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

        <el-form-item prop="parentId" label="上级分类">
          <el-cascader
            v-model="batchForm.parentId"
            style="width: 100%"
            :options="listData"
            :props="{value:'id', label:'name', disabled:true , children:'children', checkStrictly: true, expandTrigger: 'hover'}"
            placeholder="请选择上级分类"
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
      api: this.$u.api.ArticleCategory,
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
      listOptions: {},
      listQuery: {
        name: ''
      },
      statusOptions: [{
        value: 'true',
        label: '显示'
      }, {
        value: 'false',
        label: '隐藏'
      }],

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑分类',
        create: '新增分类'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '请输入文章分类名称', trigger: 'blur' }
        ]
      },
      // 初始化信息
      newForm: {
        parentId: '',
        disabled: true
      },
      // 排序
      sortDialogVisible: false,
      sortData: [],
      props: {
        label: 'name',
        value: 'id',
        children: 'children'
      },
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
    this.hdlList()
  },
  methods: {
    // 查询列表数据
    hdlList() {
      this.api.list(this.listQuery).then(res => {
        // 获取列表数据
        this.list = res.data
        // 初始化数据
        this.initData()
        // 关闭加载状态
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
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
    },
    // 打开批量弹窗事件
    handleBatch() {
      this.batchVisible = true
      this.batchForm = {}
      this.$nextTick(() => {
        this.$refs['batchForm'].clearValidate()
      })
    },
    // 批量级联选择器
    handleCascaderBatchChange(value) {
      this.batchForm.parentId = value[value.length - 1] || ''
    },
    // 提交批量新增分类数据
    batch() {
      this.$refs['batchForm'].validate((valid) => {
        if (valid) {
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
