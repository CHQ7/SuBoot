<template>
  <div class="app-container">

    <u-page :title="$route.meta.title">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="文章分类" prop="categoryId">
            <el-cascader
              v-model="listQuery.categoryId"
              :options="category"
              :props="{value:'id', label:'name', disabled:true , children:'children', expandTrigger: 'hover' }"
              placeholder="请选择文章分类"
              clearable
              @change="handleQueryChange"
            />
          </el-form-item>
          <el-form-item label="文章名称" prop="title">
            <el-input v-model="listQuery.title" placeholder="请输入文章名称" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
          </el-form-item>
          <el-dropdown>
            <el-button type="primary">
              批量操作<i class="el-icon-arrow-down el-icon--right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :disabled="selectData.length===0" @click.native="handleOpenCate">批量设置分类</el-dropdown-item>
              <el-dropdown-item :disabled="selectData.length===0" @click.native="handleOpenTime">批量设置时间</el-dropdown-item>
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
              <el-button type="text" @click="hdlDelete(row.id, row.title)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="60px">

        <el-form-item prop="categoryId" label="分类">
          <el-cascader
            v-model="dataForm.categoryId"
            :options="category"
            :props="{value:'id', label:'name', disabled:true , children:'children', expandTrigger: 'hover' }"
            placeholder="请选择分类"
            clearable
            @change="handleCascaderChange"
          />
        </el-form-item>

        <el-form-item prop="title" label="标题">
          <el-input v-model="dataForm.title" placeholder="文章标题" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="author" label="作者">
          <el-input v-model="dataForm.author" placeholder="作者" clearable />
        </el-form-item>

        <el-form-item prop="source" label="来源">
          <el-input v-model="dataForm.source" placeholder="来源" clearable />
        </el-form-item>

        <el-form-item prop="abs" label="摘要">
          <el-input v-model="dataForm.abs" type="textarea" placeholder="文章摘要" clearable maxlength="300" show-word-limit />
        </el-form-item>

        <el-form-item prop="thumb" label="图片">
          <u-upload v-model="dataForm.thumb" name="file" />
        </el-form-item>

        <el-form-item prop="content" label="内容">
          <u-tinymce v-model="dataForm.content" :height="300" />
        </el-form-item>

        <el-form-item prop="location" label="排序">
          <el-input v-model="dataForm.location" placeholder="数字越大，排名越靠前" clearable />
        </el-form-item>

        <el-form-item prop="url" label="链接">
          <el-input v-model="dataForm.url" placeholder="http://" clearable />
        </el-form-item>

        <el-form-item label="状态" prop="disabled">
          <el-switch v-model="dataForm.disabled" />
        </el-form-item>

      </el-form>
    </u-dialog>

    <u-dialog title="批量设置文章分类" :show.sync="cateFormVisible" @confirm="handleCate">
      <el-form ref="cateForm" :rules="cateRules" :model="cateForm" label-width="80px">

        <el-form-item prop="categoryId" label="文章分类">
          <el-cascader
            v-model="cateForm.categoryId"
            :options="category"
            :props="{value:'id', label:'name', disabled:true , children:'children', expandTrigger: 'hover' }"
            placeholder="请选择文章分类"
            clearable
            @change="handleCateChange"
          />
        </el-form-item>

      </el-form>
    </u-dialog>

    <u-dialog title="批量设置创建时间" :show.sync="timeFormVisible" @confirm="handleTime">
      <el-form ref="timeForm" :rules="timeRules" :model="timeForm" label-width="80px">

        <el-form-item prop="createAt" label="创建时间">
          <el-date-picker
            v-model="timeForm.createAt"
            type="datetime"
            placeholder="选择日期时间"
            default-time="12:00:00"
            value-format="timestamp"
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
      api: this.$u.api.Article,
      // 表格
      columns: [
        {
          prop: 'location',
          label: '顺序',
          width: '70',
          align: 'left',
          sortable: true
        },
        {
          prop: 'categoryId',
          label: '分类',
          align: 'left',
          render: (h, params) => {
            if (params.row.category != null) {
              return h('el-tag', { props: { size: 'small', type: 'success' }}, params.row.category.name)
            }
          }
        },
        {
          prop: 'title',
          label: '标题',
          align: 'left'
        },
        {
          prop: 'thumb',
          label: '图片',
          align: 'left',
          image: true
        },
        {
          prop: 'visit',
          align: 'left',
          label: '浏览量'
        },
        {
          prop: 'disabled',
          label: '状态',
          align: 'left',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.title, '1')
          }
        },
        {
          prop: 'createAt',
          label: '发布时间',
          align: 'left',
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
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        title: '',
        categoryId: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑文章',
        create: '新增文章'
      },
      dataForm: {},
      rules: {
        categoryId: [
          { required: true, message: '选择文章分类', trigger: ['blur'] }
        ],
        title: [
          { required: true, message: '请输入文章标题', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        disabled: true
      },
      // 分类数据
      category: [],

      // 批量分类商品
      cateFormVisible: false,
      cateForm: {},
      cateRules: {
        categoryId: [
          { required: true, message: '请选择文章分类', trigger: 'blur' }
        ]
      },
      // 批量更改创建时间
      timeFormVisible: false,
      timeForm: {},
      timeRules: {
        createAt: [
          { required: true, message: '请选择时间', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.hdlList()
    this.initCategory()
  },
  methods: {
    // 初始化分类数据
    initCategory() {
      this.api.category().then(res => {
        this.category = this.$u.tree(res.data)
      })
    },
    // 监听查询文章分类级联选择器
    handleQueryChange(value) {
      this.listQuery.categoryId = value[value.length - 1] || ''
    },
    // 监听添加修改文章分类级联选择器
    handleCascaderChange(value) {
      this.dataForm.categoryId = value[value.length - 1] || ''
    },
    // 打开批量分类弹窗
    handleOpenCate() {
      // 初始化数据源
      this.cateForm = {}
      // 打开弹框状态
      this.cateFormVisible = true
      // 清除表单验证状态
      this.$nextTick(() => {
        this.$refs['cateForm'].clearValidate()
      })
    },
    // 监听批量操作文章分类级联选择器
    handleCateChange(value) {
      this.cateForm.categoryId = value[value.length - 1] || ''
    },
    // 批量分类
    handleCate() {
      const self = this
      // 检查选中数据源
      if (self.selectData.length === 0) {
        self.$u.toast('请选择文章分类', 'error')
        return false
      }
      // 循环获取选择数据源ID
      const ids = []
      self.selectData.forEach((val) => {
        ids.push(val.id)
      })
      const { categoryId } = self.cateForm
      // 效验数据格式
      self.$refs['cateForm'].validate((valid) => {
        if (valid) {
          // 提交更新数据
          self.api.cate({ 'ids': JSON.stringify(ids), categoryId }).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.cateFormVisible = false
            // 通知信息
            self.$u.msg('批量更改成功')
          })
        }
      })
    },
    // 打开批量更改录入时间弹窗
    handleOpenTime() {
      // 初始化数据源
      this.timeForm = {}
      // 打开弹框状态
      this.timeFormVisible = true
      // 清除表单验证状态
      this.$nextTick(() => {
        this.$refs['timeForm'].clearValidate()
      })
    },
    // 批量更改录入时间事件
    handleTime() {
      const self = this
      // 检查选中数据源
      if (self.selectData.length === 0) {
        self.$u.toast('请选择文章', 'error')
        return false
      }
      // 循环获取选择数据源ID
      const ids = []
      self.selectData.forEach((val) => {
        ids.push(val.id)
      })
      const { createAt } = self.timeForm
      // 效验数据格式
      self.$refs['timeForm'].validate((valid) => {
        if (valid) {
          // 提交更新数据
          self.api.time({ 'ids': JSON.stringify(ids), 'time': createAt }).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.timeFormVisible = false
            // 通知信息
            self.$u.msg('批量更改成功')
          })
        }
      })
    }
  }
}
</script>
