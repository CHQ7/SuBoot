<template>
  <div class="app-container">

    <u-page title="参数列表">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>

        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="参数标题">
            <el-input v-model="listQuery.configName" placeholder="请输入参数标题" clearable />
          </el-form-item>
          <el-form-item label="唯一编码">
            <el-input v-model="listQuery.configKey" placeholder="请输入参数唯一编码" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
          </el-form-item>
        </el-form>

      </u-filtered>

      <u-table
        :data="list"
        :loading="listLoading"
        :columns="columns"
        :pagination.sync="listQuery"
        :fetch="hdlList"
      >
        <template v-slot:right>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="{ row }">
              <el-button type="text" @click="hdlEdit(row)">编辑</el-button>
              <el-button type="text" @click="hdlDelete(row.configKey, row.configKey)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="configName" label="参数标题">
          <el-input v-model="dataForm.configName" placeholder="请输入参数标题" clearable />
        </el-form-item>

        <el-form-item prop="configKey" label="唯一编码">
          <el-input v-model="dataForm.configKey" placeholder="请输入参数唯一编码" :disabled="dialogStatus==='update'" clearable />
        </el-form-item>

        <el-form-item prop="configValue" label="参数值">
          <el-input v-model="dataForm.configValue" type="textarea" placeholder="参请输入数值" clearable />
        </el-form-item>

        <el-form-item prop="note" label="备注">
          <el-input v-model="dataForm.note" type="textarea" />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>
export default {
  data() {
    return {
      api: this.$u.api.SystemParam,
      // 表格
      columns: [
        {
          prop: 'configName',
          label: '标题',
          align: 'left'
        },
        {
          prop: 'configKey',
          label: '参数唯一编码',
          align: 'left'
        },
        {
          prop: 'configValue',
          label: '参数值',
          align: 'left'
        },
        {
          prop: 'note',
          label: '备注',
          align: 'left'
        }
      ],

      list: [],
      listLoading: true,
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑参数',
        create: '新增参数'
      },
      dataForm: {},
      rules: {
        configName: [
          { required: true, message: '请输入参数标题', trigger: ['blur'] }
        ],
        configKey: [
          { required: true, message: '请输入参数唯一编码', trigger: ['blur'] }
        ],
        configValue: [
          { required: true, message: '请输入参数值', trigger: ['blur'] }
        ]
      }
    }
  },
  created() {
    this.hdlList()
  }
}
</script>
