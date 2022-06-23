<template>
  <div class="app-container">

    <u-page :title="$route.meta.title">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-input v-model="listQuery.keyword" placeholder="查询商品名称" style="width: 200px;" clearable @keyup.enter.native="hdlFilter" />
        <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
        <el-dropdown>
          <el-button type="primary">
            批量操作<i class="el-icon-arrow-down el-icon--right" />
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item :disabled="selectData.length===0" @click.native="handleUp">批量上架</el-dropdown-item>
            <el-dropdown-item :disabled="selectData.length===0" @click.native="handleDown">批量下架</el-dropdown-item>
            <el-dropdown-item :disabled="selectData.length===0" @click.native="handleOpenCate">批量分类</el-dropdown-item>
            <el-dropdown-item :disabled="selectData.length===0" @click.native="handleOpenMerch">批量商户</el-dropdown-item>
            <el-dropdown-item :disabled="selectData.length===0" @click.native="handleOpenTime">批量时间</el-dropdown-item>
            <el-dropdown-item :disabled="selectData.length===0" @click.native="hdlDel">批量删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
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
          <el-table-column label="属性" width="280px">
            <template slot-scope="{ row }">
              <el-tag :type="row.hasNew? 'success':'info'" @click="choose(row,0)">新品</el-tag>
              <el-tag :type="row.hasHot? 'success':'info'" @click="choose(row,1)">热卖</el-tag>
              <el-tag :type="row.hasRecommend? 'success':'info'" @click="choose(row,2)">推荐</el-tag>
              <el-tag :type="row.hasSendFree? 'success':'info'" @click="choose(row,3)">包邮</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="{ row }">
              <el-button type="text" @click="handleEdit(row)">编辑</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.title)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>
    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?handleCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-tabs v-model="dataForm.tabsActive">
          <el-tab-pane label="基础信息" name="first">

            <el-form-item prop="title" label="商品名称" style="margin-top: 10px">
              <el-input v-model="dataForm.title" placeholder="请输入商品名称" clearable maxlength="40" show-word-limit />
            </el-form-item>

            <el-form-item prop="description" label="副标题">
              <el-input v-model="dataForm.description" placeholder="请输入副标题" clearable maxlength="300" show-word-limit />
            </el-form-item>

            <el-form-item prop="type" label="商品类型">
              <el-radio-group v-model="dataForm.type">
                <el-radio :label="0">实体商品</el-radio>
                <el-radio :label="1">虚拟商品</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item prop="categoryId" label="商品分类">
              <el-cascader
                v-model="dataForm.categoryId"
                style="width: 100%;"
                :options="category"
                :props="{value:'id', label:'name', disabled:true , children:'children', expandTrigger: 'hover' }"
                placeholder="请选择分类"
                clearable
                @change="handleCascaderChange"
              />
            </el-form-item>

            <el-form-item prop="thumb" label="商品封面">
              <u-upload v-model="dataForm.thumb" name="file" tips="商品封面;建议尺寸:800*800" />
            </el-form-item>

            <el-form-item prop="thumbs" label="轮播图">
              <u-upload v-model="dataForm.thumbs" multiple name="file" tips="商品轮播图;建议尺寸:800*800" />
            </el-form-item>

            <el-form-item label="营销标签">
              <el-checkbox v-model="dataForm.hasRecommend">推荐</el-checkbox>
              <el-checkbox v-model="dataForm.hasNew">新品</el-checkbox>
              <el-checkbox v-model="dataForm.hasHot">热卖</el-checkbox>
              <el-checkbox v-model="dataForm.hasSendFree">包邮</el-checkbox>
            </el-form-item>

            <el-form-item prop="price" label="价格">
              <el-input v-model="dataForm.price" style="width: 200px">
                <template slot="prepend">售价</template>
                <template slot="append">元</template>
              </el-input>
              <el-input v-model="dataForm.originprice" style="width: 200px">
                <template slot="prepend">原价</template>
                <template slot="append">元</template>
              </el-input>
              <el-input v-model="dataForm.costprice" style="width: 200px">
                <template slot="prepend">成本价</template>
                <template slot="append">元</template>
              </el-input>
            </el-form-item>

            <el-form-item prop="total" label="库存">
              <el-input v-model.number="dataForm.total" />
            </el-form-item>

            <el-form-item prop="sold" label="虚拟销量">
              <el-input v-model.number="dataForm.sold" />
            </el-form-item>

            <el-form-item prop="location" label="排序">
              <el-input v-model="dataForm.location" placeholder="数字越大，排名越靠前" clearable />
            </el-form-item>

            <el-form-item prop="disabled" label="状态">
              <el-radio-group v-model="dataForm.disabled">
                <el-radio :label="true">上架</el-radio>
                <el-radio :label="false">下架</el-radio>
              </el-radio-group>
            </el-form-item>

          </el-tab-pane>
          <el-tab-pane label="商品详情" name="second">
            <el-form-item prop="content" label="商品详情">
              <u-tinymce v-model="dataForm.content" :height="300" />
            </el-form-item>
          </el-tab-pane>
        </el-tabs>

      </el-form>
    </u-dialog>

    <u-dialog :title="cateTitle" :show.sync="cateFormVisible" @confirm="handleCate">
      <el-form ref="cateForm" :rules="cateRules" :model="cateForm" label-width="80px">
        <el-form-item prop="categoryId" label="商品分类">
          <el-cascader
            v-model="cateForm.categoryId"
            :options="category"
            :props="{value:'id', label:'name', disabled:true , children:'children', expandTrigger: 'hover' }"
            placeholder="请选择分类"
            clearable
            @change="handleCateChange"
          />
        </el-form-item>
      </el-form>
    </u-dialog>

    <u-dialog :title="timeTitle" :show.sync="timeFormVisible" @confirm="handleTime">
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

    <u-dialog :title="merchTitle" :show.sync="merchFormVisible" @confirm="handleMerch">
      <el-form ref="merchForm" :rules="merchRules" :model="merchForm" label-width="80px">
        <el-form-item prop="merchId" label="选择商户">
          <el-select v-model="merchForm.merchId" placeholder="请选择商户">
            <el-option
              v-for="(item, index) in merch"
              :key="index"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
    </u-dialog>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.Goods,
      GoodsFormVisible: false,
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
          prop: 'title',
          label: '分类',
          align: 'left',
          render: (h, params) => {
            if (!this.$u.test.empty(params.row.category)) {
              const newArr = params.row.category.map(val => {
                return val.name
              })
              return h('el-tag', newArr.join('-'))
            }
          }
        },
        {
          prop: 'merch',
          label: '商户名称',
          align: 'left',
          render: (h, params) => {
            if (!this.$u.test.empty(params.row.merch)) {
              return h('el-tag', { props: { type: 'warning' }}, params.row.merch.name)
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '自营')
            }
          }
        },
        {
          prop: 'thumb',
          label: '商品',
          width: '60',
          align: 'left',
          image: true
        },
        {
          prop: 'title',
          align: 'left',
          label: '名称'
        },
        {
          prop: 'price',
          label: '价格',
          align: 'left',
          sortable: true,
          render: (h, params) => {
            return h('span', { style: { fontWeight: 'bold', color: '#4CAF50 !important' }}, this.$u.amount.format(params.row.price))
          }
        },
        {
          prop: 'total',
          align: 'left',
          label: '库存'
        },
        {
          prop: 'sold',
          align: 'left',
          label: '销量'
        },
        {
          prop: 'soldReal',
          align: 'left',
          label: '实际销量'
        },
        {
          prop: 'disabled',
          label: '上下架',
          align: 'left',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.title, '3')
          }
        }
      ],

      list: [],
      listLoading: true,
      listOptions: {
        mutiSelect: true
      },
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        keyword: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑商品',
        create: '新增商品'
      },
      dataForm: {},
      rules: {
        title: [
          { required: true, message: '请输入商品名称', trigger: ['blur'] }
        ],
        categoryId: [
          { required: true, message: '请选择分类', trigger: ['blur'] }
        ],
        thumb: [
          { required: true, message: '请上传商品图片', trigger: ['blur'] }
        ],
        price: [
          { required: true, message: '请输入价格', trigger: ['blur'] }
        ],
        total: [
          { required: true, message: '请输入库存', trigger: ['blur'] },
          { type: 'number', message: '库存必须为数字值' }
        ]
      },
      newForm: {
        tabsActive: 'first',
        type: 0,
        thumb: '',
        price: 0,
        originprice: 0,
        costprice: 0,
        total: 0,
        sold: 0,
        disabled: true
      },
      // 分类数据
      category: [],
      // 批量分类商品
      cateTitle: '批量分类',
      cateFormVisible: false,
      cateForm: {},
      cateRules: {
        categoryId: [
          { required: true, message: '请选择商品分类', trigger: 'blur' }
        ]
      },
      // 批量更改录入时间
      timeTitle: '批量更改创建时间',
      timeFormVisible: false,
      timeForm: {},
      timeRules: {
        createAt: [
          { required: true, message: '请选择时间', trigger: 'blur' }
        ]
      },
      // 批量更改商户
      merch: [], // 商户数据
      merchTitle: '批量更改商户',
      merchFormVisible: false,
      merchForm: {},
      merchRules: {
        merchId: [
          { required: true, message: '请选择商户', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.initCategory()
    this.initMerch()
    this.hdlList()
  },
  methods: {
    // 提交创建数据事件
    handleCreate() {
      const self = this
      // 效验数据格式
      this.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          // 提交创建数据
          self.api.create(self.price(self.dataForm)).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.dialogFormVisible = false
            // 通知信息
            self.$u.msg('创建成功')
          })
        }
      })
    },
    // 提交更新数据事件
    hdlUpdate() {
      const self = this
      // 效验数据格式
      self.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          // 提交更新数据
          self.api.update(self.price(self.dataForm)).then(() => {
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
    // 格式化数据
    price(data) {
      data.price = this.$u.amount.Y2F(data.price)
      data.originprice = this.$u.amount.Y2F(data.originprice)
      data.costprice = this.$u.amount.Y2F(data.costprice)
      return data
    },
    handleEdit(row) {
      this.api.fetch(row.id).then(res => {
        this.dataForm = res.data
        this.dataForm.tabsActive = 'first'
        this.dataForm.price = this.$u.amount.F2Y(this.dataForm.price)
        this.dataForm.originprice = this.$u.amount.F2Y(this.dataForm.originprice)
        this.dataForm.costprice = this.$u.amount.F2Y(this.dataForm.costprice)
        // 打开编辑状态
        this.dialogStatus = 'update'
        // 打开弹框状态
        this.dialogFormVisible = true
        // 清除表单验证状态
        this.$nextTick(() => {
          this.$refs['dialogForm'].clearValidate()
        })

        this.dataForm.categoryId = this.dataForm.categoryId.filter(function(s) {
          return s && s.trim()
        })
      })
    },
    // 初始化分类
    initCategory() {
      this.api.category().then(res => {
        this.category = this.$u.tree(res.data)
      })
    },
    // 级联选择器
    handleCascaderChange(value) {
      this.dataForm.fcate = value[0] || ''
      this.dataForm.scate = value[1] || ''
      this.dataForm.tcate = value[2] || ''
    },
    // 获取第一张图片
    handleThumb(thumb) {
      return thumb.split(',')[0]
    },
    // 批量上架
    handleUp() {
      const self = this
      // 检查选中数据源
      if (self.selectData.length === 0) {
        self.$u.toast('请选择商品', 'error')
        return false
      }
      // 循环获取选择数据源ID
      const ids = []
      self.selectData.forEach((val) => {
        ids.push(val.id)
      })
      // 处理确认事件
      self.api.up({ 'ids': JSON.stringify(ids) }).then(() => {
        // 刷新数据列表
        self.hdlList()
        // 通知信息
        self.$u.msg('批量上架成功')
      })
    },
    // 批量下架
    handleDown() {
      const self = this
      // 检查选中数据源
      if (self.selectData.length === 0) {
        self.$u.toast('请选择商品', 'error')
        return false
      }
      // 循环获取选择数据源ID
      const ids = []
      self.selectData.forEach((val) => {
        ids.push(val.id)
      })
      // 处理确认事件
      self.api.down({ 'ids': JSON.stringify(ids) }).then(() => {
        // 刷新数据列表
        self.hdlList()
        // 通知信息
        self.$u.msg('批量下架成功')
      })
    },
    // 级联选择器
    handleCateChange(value) {
      this.cateForm.fcate = value[0] || ''
      this.cateForm.scate = value[1] || ''
      this.cateForm.tcate = value[2] || ''
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
    // 批量分类
    handleCate() {
      const self = this
      // 检查选中数据源
      if (self.selectData.length === 0) {
        self.$u.toast('请选择商品', 'error')
        return false
      }
      // 循环获取选择数据源ID
      const ids = []
      self.selectData.forEach((val) => {
        ids.push(val.id)
      })
      const { fcate, scate, tcate } = self.cateForm
      // 效验数据格式
      self.$refs['cateForm'].validate((valid) => {
        if (valid) {
          // 提交更新数据
          self.api.cate({ 'ids': JSON.stringify(ids), fcate, scate, tcate }).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.cateFormVisible = false
            // 通知信息
            self.$u.msg('批量分类成功')
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
        self.$u.toast('请选择商品', 'error')
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
            self.$u.msg('批量更改录入时间成功')
          })
        }
      })
    },
    // 选择属性
    choose(row, type) {
      const self = this
      if (type === 0) {
        row.hasNew = !row.hasNew
      } else if (type === 1) {
        row.hasHot = !row.hasHot
      } else if (type === 2) {
        row.hasRecommend = !row.hasRecommend
      } else if (type === 3) {
        row.hasSendFree = !row.hasSendFree
      }
      self.dataForm = Object.assign({}, row)
      self.api.update(self.dataForm).then(() => {
        // 刷新数据列表
        self.hdlList()
        // 通知信息
        self.$u.msg('更新成功')
      })
    },
    // 初始化商户
    initMerch() {
      this.api.merch().then(res => {
        this.merch = res.data
      })
    },
    // 打开批量更改商户
    handleOpenMerch() {
      // 初始化数据源
      this.merchForm = {}
      // 打开弹框状态
      this.merchFormVisible = true
      // 清除表单验证状态
      this.$nextTick(() => {
        this.$refs['merchForm'].clearValidate()
      })
    },
    // 批量更改更改商户
    handleMerch() {
      const self = this

      // 检查选中数据源
      if (self.selectData.length === 0) {
        self.$u.toast('请选择商品', 'error')
        return false
      }
      // 循环获取选择数据源ID
      const ids = []
      self.selectData.forEach((val) => {
        ids.push(val.id)
      })
      const { merchId } = self.merchForm
      self.$refs['merchForm'].validate((valid) => {
        if (valid) {
          // 提交更新数据
          self.api.batchMerch({ 'ids': JSON.stringify(ids), 'merchId': merchId }).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.merchFormVisible = false
            // 通知信息
            self.$u.msg('批量更改商户成功')
          })
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  .richTest >>> .ck-editor__editable { min-height: 300px; max-height: 500px;}
  ::v-deep{
    .el-tag{
      cursor: pointer;
    }
  }
</style>
