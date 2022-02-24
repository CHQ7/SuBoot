<template>
  <div class="app-container">

    <u-page title="意见反馈">
      <div slot="header">
        <!--<el-button type="primary" icon="el-icon-edit" @click="hdlOpen()">
          {{ textMap['create'] }}
        </el-button>-->
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
              <el-button type="text" @click="handlePreview(row)">预览</el-button>
              <el-button type="text" @click="handleReply(row)">回复</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.title)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="60px">

        <el-form-item prop="title" label="标题">
          <el-input v-model="dataForm.title" placeholder="标题" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="content" label="内容">
          <el-input v-model="dataForm.content" type="textarea" placeholder="反馈内容" clearable maxlength="300" show-word-limit />
        </el-form-item>

        <el-form-item prop="mobile" label="手机号">
          <el-input v-model="dataForm.mobile" placeholder="选填,手机号" clearable />
        </el-form-item>

      </el-form>
    </u-dialog>

    <u-dialog :title="dialogTitle" :show.sync="dialogReplyFormVisible" @confirm="replyData">
      <el-form ref="dialogReplyForm" :rules="replyRules" :model="replyForm" label-width="80px">

        <el-form-item prop="title" label="标题">
          {{ replyForm.title }}
        </el-form-item>

        <el-form-item prop="content" label="内容">
          <el-input v-model="replyForm.content" type="textarea" placeholder="反馈内容" disabled />
        </el-form-item>

        <el-form-item prop="note" label="回复">
          <el-input v-model="replyForm.note" type="textarea" placeholder="回复内容" clearable maxlength="200" show-word-limit />
        </el-form-item>

      </el-form>
    </u-dialog>

    <el-drawer
      title="预览"
      :visible.sync="drawerPreview"
      :with-header="false"
      size="50%"
    >
      <el-row style="padding: 20px">
        <el-form class="el-table-expand" label-position="left" label-width="80px">
          <el-form-item label="会员">
            <span v-if="drawerForm.member != null">
              <el-tag size="small">{{ drawerForm.member.nickname }}</el-tag>
            </span>
          </el-form-item>
          <el-form-item label="客户端">
            {{ drawerForm.client }}
          </el-form-item>
          <el-form-item label="反馈时间">
            {{ drawerForm.createAt | date }}
          </el-form-item>

          <el-form-item label="截图">
            <el-image v-for="(url,index) in picurlList" :key="index" :src="url" lazy />
          </el-form-item>

          <el-form-item label="回复">
            {{ drawerForm.note }}
          </el-form-item>
        </el-form>
      </el-row>
    </el-drawer>

  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.PlatformFeedback,
      // 表格
      columns: [
        {
          prop: 'member',
          label: '会员信息',
          render: (h, params) => {
            if (params.row.member != null) {
              return h('span', { props: { type: 'success' }}, params.row.member.nickname)
            }
          }
        },
        {
          prop: 'title',
          label: '标题'
        },
        {
          prop: 'content',
          label: '内容'
        },
        {
          prop: 'client',
          label: '客户端'
        },
        {
          prop: 'createAt',
          label: '反馈时间',
          timestamp: true
        },
        {
          prop: 'note',
          label: '回复'
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
        update: '编辑意见',
        create: '新增意见'
      },
      dataForm: {},
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: ['blur'] }
        ],
        content: [
          { required: true, message: '请输入内容', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],

      // 预览
      drawerPreview: false,
      picurlList: [],
      drawerForm: {
        thumb: ''
      },
      // 回复
      dialogTitle: '回复',
      dialogReplyFormVisible: false,
      replyForm: {},
      replyRules: {
        note: [
          { required: true, message: '请输入回复内容', trigger: ['blur'] }
        ]
      }

    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    // 预览
    handlePreview(row) {
      this.drawerPreview = true
      this.drawerForm = row
      this.picurlList = []
      if (this.drawerForm.thumb) {
        this.picurlList = this.drawerForm.thumb.split(',')
      }
    },
    // 回复事件
    handleReply(row) {
      if (row.member != null) {
        this.dialogTitle = '回复' + '【' + row.member.nickname + '】'
      }
      this.replyForm = Object.assign({}, row) // copy obj
      this.dialogReplyFormVisible = true
      this.$nextTick(() => {
        this.$refs['dialogReplyForm'].clearValidate()
      })
    },
    // 提交回复数据
    replyData() {
      this.$refs['dialogReplyForm'].validate((valid) => {
        if (valid) {
          this.api.update({ id: this.replyForm.id, note: this.replyForm.note }).then(() => {
            this.hdlList()
            this.dialogReplyFormVisible = false
            this.$u.msg('回复成功')
          })
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  .app-container{

    & >>> .el-drawer__body {
      overflow: auto;
    }

  }
</style>
