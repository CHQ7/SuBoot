<template>
  <div class="app-container">

    <u-page title="链接管理">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="标题" prop="title">
            <el-input v-model="listQuery.title" placeholder="请输入标题" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
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
              <el-button type="text" @click="hdlDelete(row.id, row.title)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="title" label="标题">
          <el-input v-model="dataForm.title" placeholder="链接标题" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="type" label="类型">
          <el-radio-group v-model="dataForm.type" @change="typeChange">
            <el-radio label="txt">文字</el-radio>
            <el-radio label="img">图片</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item v-if="'img'===dataForm.type" prop="thumb" label="图片">
          <u-upload v-model="dataForm.thumb" name="file" />
        </el-form-item>

        <el-form-item prop="location" label="排序">
          <el-input v-model="dataForm.location" placeholder="数字越大，排名越靠前" clearable />
        </el-form-item>

        <el-form-item prop="url" label="链接">
          <el-input v-model="dataForm.url" placeholder="http://" clearable />
        </el-form-item>

        <el-form-item prop="target" label="打开方式">
          <el-radio-group v-model="dataForm.target">
            <el-radio label="_self">本页面</el-radio>
            <el-radio label="_blank">新页面</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="状态" prop="disabled">
          <el-switch v-model="dataForm.disabled" />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>
export default {
  data() {
    return {
      api: this.$u.api.PlatformLink,
      // 表格
      columns: [
        {
          prop: 'location',
          label: '顺序',
          width: '70',
          sortable: true
        },
        {
          prop: 'title',
          label: '标题'
        },
        {
          prop: 'thumb',
          label: '图片',
          image: true
        },
        {
          prop: 'url',
          label: '链接'
        },
        {
          prop: 'disabled',
          label: '状态',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.title, '1')
          }
        },
        {
          prop: 'createAt',
          label: '发布时间',
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
        title: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑链接',
        create: '新增链接'
      },
      dataForm: {},
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        type: 'txt',
        url: 'javascript:void(0);',
        target: '_blank',
        disabled: true
      }
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    typeChange(val) {
      if (val === 'txt') {
        // 类型变化了要清除数据
        this.dataForm.thumb = ''
      }
    }
  }
}
</script>
