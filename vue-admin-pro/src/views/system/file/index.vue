<template>
  <div class="app-container">
    <u-page title="文件管理">
      <u-filtered>

        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="时间范围">
            <el-date-picker
              v-model="timestampDate"
              type="datetimerange"
              :picker-options="pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              :default-time="['00:00:00', '23:59:59']"
              value-format="yyyy-MM-dd HH:mm:ss"
              style="width:300px"
            />
          </el-form-item>
          <el-form-item label="文件名称">
            <el-input v-model="listQuery.fileName" placeholder="请输入文件名称" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
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
        :fetch="getList"
        @selection-change="hdlSelectChange"
      >
        <template v-slot:right>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="{ row }">
              <el-button type="text" @click="handleDownload(row.id)">下载</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.fileName)">删除</el-button>
              <el-button v-if="row.fileType.match(RegExp(/image/))" type="text" @click="hdlEdit(row)">预览</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog title="预览模式" :show.sync="dialogFormVisible" :footer="false">
      <el-form ref="dialogForm" :model="dataForm">
        <el-image :src="dataForm.filePath" />
      </el-form>
    </u-dialog>
  </div>
</template>
<script>

export default {
  data: function() {
    return {
      api: this.$u.api.SystemFile,
      // 表格
      columns: [
        {
          prop: 'fileType',
          label: '文件类型',
          align: 'left'
        },
        {
          prop: 'fileName',
          label: '文件名称',
          align: 'left'
        },
        {
          prop: 'fileSuffix',
          label: '文件后缀',
          align: 'left'
        },
        {
          prop: 'fileSizeKb',
          label: '文件大小',
          align: 'left'
        },
        {
          prop: 'filePath',
          label: '文件路径',
          align: 'left'
        },
        {
          prop: 'createAt',
          label: '创建时间',
          sortable: true,
          timestamp: true,
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
        pageNumber: 1,
        pageSize: 20,
        totalCount: 1,
        time: '',
        fileName: ''
      },
      // 删除选中数据
      selectData: [],
      // 查询时间戳
      timestampDate: [],
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      dialogFormVisible: false,
      dataForm: {}

    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 初始化数据
    getList() {
      this.listLoading = true
      if (this.timestampDate) {
        this.listQuery.time = this.timestampDate.toString()
      } else {
        this.listQuery.time = ''
      }
      this.hdlList()
    },
    // 搜索事件
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    // 文件下载事件
    handleDownload(id) {
      this.api.download(id)
    }
  }
}
</script>

