<template>
  <div class="app-container">

    <u-page title="用户等级">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="等级名称" prop="levelName">
            <el-input v-model="listQuery.levelName" placeholder="请输入等级名称" clearable />
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
              <el-button type="text" @click="hdlDelete(row.id, row.levelName)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="level" label="级别">
          <el-select v-model="dataForm.level" placeholder="请选择,数字越大等级越高" clearable>
            <el-option
              v-for="(item,index) in 100"
              :key="index"
              :label="index"
              :value="index"
            />
          </el-select>
        </el-form-item>

        <el-form-item prop="levelName" label="等级名称">
          <el-input v-model="dataForm.levelName" placeholder="请输入等级名称" clearable />
        </el-form-item>

        <el-form-item prop="discount" label="折扣">
          <el-tooltip effect="dark" content="请输入0.1~10之间的数字,值为空代表不设置折扣" placement="top">
            <el-input v-model="dataForm.discount" maxlength="20" placeholder="折扣">
              <template slot="append">折</template>
            </el-input>
          </el-tooltip>
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
      api: this.$u.api.MemberLevel,
      // 表格
      columns: [
        {
          prop: 'level',
          label: '等级'
        },
        {
          prop: 'levelName',
          label: '等级名称'
        },
        {
          prop: 'discount',
          label: '折扣'
        },
        {
          prop: 'disabled',
          label: '状态',
          switch: true,
          change: (event, row) => {
            this.hdlDisable(row, event, row.levelName)
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
        levelName: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑用户等级',
        create: '新增用户等级'
      },
      dataForm: {},
      rules: {
        level: [
          { required: true, message: '请选择等级级别', trigger: ['blur'] }
        ],
        levelName: [
          { required: true, message: '请输入等级名称', trigger: ['blur'] }
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
