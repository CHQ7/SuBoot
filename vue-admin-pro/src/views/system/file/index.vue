<template>
  <div class="app-container">
    <u-page title="文件管理">
      <u-filtered>

        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="时间范围">
            <u-date-time-picker v-model="searchDate" @change="changeDate" />
          </el-form-item>
          <el-form-item label="文件名称">
            <el-input v-model="listQuery.fileName" placeholder="请输入文件名称" clearable />
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
              <el-button v-if="row.fileType.match(RegExp(/image/))" type="text" @click="hdlEdit(row)">预览</el-button>
              <el-button type="text" @click="handleDownload(row.id)">下载</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.fileName)">删除</el-button>
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
          label: '文件类型'
        },
        {
          prop: 'fileName',
          label: '文件名称'
        },
        {
          prop: 'fileSuffix',
          label: '文件后缀'
        },
        {
          prop: 'fileSizeKb',
          label: '文件大小'
        },
        {
          prop: 'filePath',
          label: '文件路径',
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
      listLoading: false,
      listOptions: {
        mutiSelect: true, // boolean 是否多选
        stripe: true // boolean 斑马纹
      },
      listQuery: {
        pageNumber: 1,
        pageSize: 20,
        totalCount: 1,
        beginTime: '',
        endTime: '',
        fileName: ''
      },
      // 查询时间范围
      searchDate: [],
      dialogFormVisible: false,
      dataForm: {}
    }
  },
  methods: {
    changeDate(item) {
      if (item) {
        this.listQuery.beginTime = item[0]
        this.listQuery.endTime = item[1]
      } else {
        this.listQuery.beginTime = ''
        this.listQuery.endTime = ''
      }
    },
    // 文件下载事件
    handleDownload(id) {
      this.api.download(id)
    }
  }
}
</script>

