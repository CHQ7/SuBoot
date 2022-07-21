<template>
  <div class="app-container">

    <u-page title="会员等级">
      <div slot="header">
        <el-button type="primary" icon="el-icon-refresh-left" @click="hdlSynchro()">
          同步函云数据
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
          <!--          <el-dropdown>
            <el-button type="primary">
              批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :disabled="selectData.length===0" @click.native="hdlDel">批量删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>-->
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
              <!--              <el-button type="text" @click="hdlEdit(row)">编辑</el-button>-->
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
          label: '等级类型'
        },
        {
          prop: 'levelName',
          label: '等级名称'
        },
        {
          prop: 'createdAt',
          label: '创建时间',
          sortable: true,
          timestamp: true
        }
      ],

      list: [],
      listLoading: true,
      listOptions: {
        mutiSelect: true
      },
      listQuery: {
        page: 1,
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
  },
  methods: {
    // 同步事件
    hdlSynchro() {
      const self = this
      self.$confirm('此操作将同步数据是否继续，请谨慎操作？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchro().then(res => {
          // 同步完成，刷新一下
          self.hdlList()
          self.$alert('同步数据成功', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    }
  }
}
</script>
