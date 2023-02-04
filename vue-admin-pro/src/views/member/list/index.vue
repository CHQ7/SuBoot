<template>
  <div class="app-container">

    <u-page title="用户管理">

      <u-filtered>
        <el-form :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="手机号" prop="mobile">
            <el-input v-model="listQuery.mobile" placeholder="关键词" clearable style="width: 160px" maxlength="11" show-word-limit />
          </el-form-item>
          <el-form-item label="用户等级" prop="levelId">
            <member-level v-model="listQuery.levelId" all />
          </el-form-item>
          <el-form-item label="用户标签" prop="label">
            <member-label v-model="listQuery.label" />
          </el-form-item>
          <el-form-item label="注册时间" prop="regDate">
            <u-date-time-picker v-model="regDate" @change="regDateChange" />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="listQuery.status" placeholder="请选择状态" class="status" clearable>
              <el-option value="" label="全部" />
              <el-option :value="true" label="正常" />
              <el-option :value="false" label="禁用" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">查询</el-button>
          </el-form-item>
          <el-dropdown>
            <el-button type="primary">
              更多操作<i class="el-icon-arrow-down el-icon--right" />
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
              <el-dropdown>
                <el-button type="text">更多</el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="hdlDelete(row.id, row.nickname)">删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <!-- 会员创建/更新 -->
    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">

      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item prop="nickname" label="昵称">
              <el-input v-model="dataForm.nickname" placeholder="请输入昵称" clearable minlength="1" maxlength="20" show-word-limit />
            </el-form-item>

            <el-form-item prop="mobile" label="手机号">
              <el-input v-model="dataForm.mobile" placeholder="请输入手机号" maxlength="11" clearable show-word-limit />
            </el-form-item>

            <el-form-item prop="birthday" label="生日">
              <el-date-picker v-model="dataForm.birthday" type="date" value-format="yyyy-MM-dd" placeholder="选择生日日期" />
            </el-form-item>

            <el-form-item prop="sex" label="性别">
              <el-radio-group v-model="dataForm.sex">
                <el-radio-button :label="0">保密</el-radio-button>
                <el-radio-button :label="1">男</el-radio-button>
                <el-radio-button :label="2">女</el-radio-button>
              </el-radio-group>
            </el-form-item>

          </el-col>
          <el-col :span="12">
            <el-form-item prop="level" label="会员等级">
              <member-level v-model="dataForm.levelId" />
            </el-form-item>

            <el-form-item prop="openid" label="OPENID">
              <el-input :value="dataForm.openid" placeholder="请输入微信公众号OPENID" disabled clearable />
            </el-form-item>

            <el-form-item prop="age" label="年龄">
              <el-input :value="dataForm.age" placeholder="暂未计算" disabled clearable />
            </el-form-item>

            <el-form-item prop="createdAt" label="注册时间">
              <el-input :value="dataForm.createdAt | date" disabled clearable />
            </el-form-item>

          </el-col>
        </el-row>

        <el-form-item prop="status" label="状态">
          <el-radio-group v-model="dataForm.status">
            <el-radio :label="true">正常</el-radio>
            <el-radio :label="false">禁用</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="note" label="备注">
          <el-input v-model="dataForm.note" type="textarea" placeholder="请输入后台备注" clearable maxlength="200" show-word-limit />
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>
import MemberLevel from '@/components/MemberLevel'
import MemberLabel from '@/components/MemberLabel'
export default {
  components: {
    MemberLevel,
    MemberLabel
  },
  data() {
    return {
      api: this.$u.api.Member,
      // 表格
      columns: [
        {
          prop: 'nickname',
          label: '昵称',
          width: '80'
        },
        {
          prop: 'mobile',
          label: '手机号'
        },
        {
          prop: 'openid',
          label: 'OPENID',
          width: '220'
        },
        {
          prop: 'level',
          label: '等级',
          formatter: (row) => {
            const { level } = row
            if (level) {
              return level.name
            }
          }
        },
        {
          prop: 'status',
          label: '状态',
          render: (h, params) => {
            if (params.row.status) {
              return h('el-tag', { props: { type: 'success' }}, '正常')
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '禁用')
            }
          }
        },
        {
          prop: 'createdAt',
          label: '注册时间',
          timestamp: true
        },
        {
          prop: 'note',
          label: '备注'
        }
      ],

      list: [],
      listLoading: false,
      listOptions: {
        mutiSelect: true,
        stripe: true
      },
      listQuery: {
        page: 1,
        pageSize: 10,
        totalCount: 1,
        openid: '',
        mobile: '',
        status: '',
        levelId: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑用户',
        create: '新增用户'
      },
      dataForm: {},
      rules: {
        mobile: [
          { required: true, message: '请输入账号', trigger: ['blur'] }
        ],
        nickname: [
          { required: true, message: '请输入用户名', trigger: ['blur'] }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: ['blur'] }
        ]
      },
      // 注册时间范围
      regDate: []
    }
  },
  created() {
  },
  methods: {
    // 注册时间格式转换
    regDateChange(val) {
      if (val) {
        this.listQuery.beginTime = val[0]
        this.listQuery.endTime = val[1]
      } else {
        this.listQuery.beginTime = ''
        this.listQuery.endTime = ''
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.status {
  ::v-deep{
    .el-input{
      width: 140px;
    }
  }
}
</style>
