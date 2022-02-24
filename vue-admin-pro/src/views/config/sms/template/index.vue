<template>
  <div class="app-container">

    <u-page title="短信消息库管理">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-input v-model="listQuery.keyword" placeholder="查询模板名称" style="width: 200px;" clearable @keyup.enter.native="hdlFilter" />
        <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
        <el-button type="danger" icon="el-icon-delete" :disabled="selectData.length==0" @click="hdlDel">删除</el-button>
      </u-filtered>

      <u-table
        :data="list"
        :loading="listLoading"
        :options="listOptions"
        :columns="columns"
        :pagination.sync="listQuery"
        :fetch="hdlList"
        @selection-change="hdlSelectChange"
      />

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="name" label="模板名称">
          <el-input v-model="dataForm.name" placeholder="模版名称，例：订单创建成功通知（自定义）" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="tplId" label="模板ID">
          <el-input v-model="dataForm.tplId" placeholder="短信模板ID，例：1234（短信服务商提供的模板ID）" clearable />
        </el-form-item>

        <el-form-item prop="content" label="模板内容">
          <el-input v-model="dataForm.content" placeholder="短信模板内容,便于后台查看" clearable />
        </el-form-item>

        <el-form-item prop="signName" label="短信签名">
          <el-input v-model="dataForm.signName" clearable />
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
      api: this.$u.api.ConfigSmsTemp,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '模板名称'
        },
        {
          prop: 'content',
          label: '模板内容'
        },
        {
          prop: 'disabled',
          label: '状态',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.name)
          }
        },
        {
          prop: 'createAt',
          label: '创建时间',
          timestamp: true
        },
        {
          button: true,
          label: '操作',
          group: [
            {
              name: '编辑',
              click: row => {
                this.hdlEdit(row)
              }
            },
            {
              name: '删除',
              click: row => {
                this.hdlDelete(row.id, row.name)
              }
            }
          ]
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
        update: '编辑模板',
        create: '新增模板'
      },
      dataForm: {},
      rules: {
        name: [
          { required: true, message: '请输入模板名称,便于后台搜索查询选择', trigger: ['blur'] }
        ],
        tplId: [
          { required: true, message: '服务商提供的模板ID', trigger: ['blur'] }
        ],
        content: [
          { required: true, message: '服务商提供的模板ID', trigger: ['blur'] }
        ],
        signName: [
          { required: true, message: '请填写短信签名', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        disabled: true
      }
    }
  },
  created() {
    this.hdlList()
  }
}
</script>
