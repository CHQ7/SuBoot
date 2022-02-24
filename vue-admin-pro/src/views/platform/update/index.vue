<template>
  <div class="app-container">

    <u-page title="更新日志">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="平台" prop="platform">
            <el-radio-group v-model="listQuery.platform" size="small">
              <el-radio-button label="">全部</el-radio-button>
              <el-radio-button label="platform">系统</el-radio-button>
              <el-radio-button label="android">Android平台</el-radio-button>
              <el-radio-button label="ios">IOS平台</el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="标题" prop="title">
            <el-input v-model="listQuery.title" placeholder="请输入标题" clearable />
          </el-form-item>
          <el-form-item label="版本" prop="version">
            <el-input v-model="listQuery.version" placeholder="请输入版本" clearable />
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
        <el-form-item prop="os" label="发布平台">
          <el-radio-group v-model="dataForm.platform">
            <el-radio label="platform">系统</el-radio>
            <el-radio label="android">Android平台</el-radio>
            <el-radio label="ios">IOS平台</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="title" label="发布标题">
          <el-input v-model="dataForm.title" placeholder="发布版本标题" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="version" label="版本号">
          <el-input v-model="dataForm.version" placeholder="1.0.0" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="note" label="版本内容">
          <u-tinymce v-model="dataForm.note" :height="300" />
        </el-form-item>

        <el-form-item v-if=" dataForm.platform === 'android' || dataForm.platform === 'ios'" prop="url" label="更新地址">
          <el-input v-model="dataForm.url" clearable maxlength="300" show-word-limit />
        </el-form-item>

        <el-form-item v-if=" dataForm.platform === 'android' || dataForm.platform === 'ios'" prop="url" label="上传文件">
          <u-upload-file v-model="dataForm.url" name="file" accept=".apk,.wgt" :size="30" />
        </el-form-item>

        <el-form-item v-if=" dataForm.platform === 'android' || dataForm.platform === 'ios'" prop="method" label="强制更新">
          <el-switch v-model="dataForm.method" />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>
export default {
  data() {
    return {
      api: this.$u.api.PlatformUpdate,
      // 表格
      columns: [
        {
          prop: 'platform',
          label: '平台',
          render: (h, params) => {
            let title = ''
            if (params.row.platform === 'android') {
              title = 'Android平台'
            } else if (params.row.platform === 'ios') {
              title = 'IOS平台'
            } else {
              title = '系统'
            }
            return h('el-tag', { props: { type: 'success' }}, title)
          }
        },
        {
          prop: 'version',
          label: '版本',
          sortable: true
        },
        {
          prop: 'title',
          label: '标题'
        },
        {
          prop: 'url',
          label: '链接'
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
        title: '',
        version: '',
        platform: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑版本',
        create: '新增版本'
      },
      dataForm: {},
      rules: {
        platform: [
          { required: true, message: '请选择发布平台', trigger: ['blur'] }
        ],
        title: [
          { required: true, message: '请输入标题', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        platform: 'platform'
      }
    }
  },
  created() {
    this.hdlList()
  }
}
</script>
