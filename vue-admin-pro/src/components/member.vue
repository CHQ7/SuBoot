<template>
  <!-- 会员选择器 -->
  <u-dialog title="会员选择器" :show.sync="show" width="1040px" :auto-close="false" :footer="false" @cancel="cancel" @confirm="confirm">
    <!-- 条件筛选 -->
    <u-filtered>
      <el-form :inline="true" :model="listQuery" class="search-form">
        <el-form-item label="卡号" prop="cardno">
          <el-input v-model="listQuery.cardno" placeholder="请输入卡号" clearable style="width: 170px" />
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="listQuery.mobile" placeholder="请输入手机号" clearable style="width: 160px" maxlength="11" show-word-limit />
        </el-form-item>
        <el-form-item label="OPENID" prop="openid">
          <el-input v-model="listQuery.openid" placeholder="请输入OPENID" style="width: 160px" clearable />
        </el-form-item>
        <el-form-item label="等级" prop="level">
          <member-level v-model="listQuery.level" all />
        </el-form-item>
        <el-form-item label="注册渠道" prop="channel">
          <channel v-model="listQuery.channel" width="160px" />
        </el-form-item>
        <el-form-item label="注册时间" prop="inputDateRange">
          <u-date-time-picker v-model="inputDateRange" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
        </el-form-item>
      </el-form>
    </u-filtered>

    <!-- 列表显示 -->
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
        <el-table-column label="" align="center" fixed="right">
          <template slot-scope="{ row }">
            <el-button v-if="isSelect(row)" @click="add(row)">选择</el-button>
            <el-button v-else type="primary" @click="sub(row)">已选</el-button>
          </template>
        </el-table-column>
      </template>
    </u-table>
  </u-dialog>

</template>

<script>
/**
 * 业务组件 - 会员选择器
 */
import MemberLevel from './MemberLevel'
import channel from './channel'
export default {
  components: {
    MemberLevel,
    channel
  },
  name: 'Member',
  model: {
    prop: 'show',
    event: 'changeShow'
  },
  props: {
    /** 是否弹框 */
    show: {
      type: Boolean,
      default: false
    },
    /** 选中的数据列表 */
    data: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      api: this.$u.api.MemberData,
      // 状态类型
      statusList: [{ value: '', label: '全部' }, { value: '0', label: '正常' }, { value: '-1', label: '禁用' }],
      // 表格管理
      columns: [
        {
          prop: 'member_name',
          label: '姓名',
          width: '120'
        },
        {
          prop: 'card_no',
          label: '会员卡号',
          width: '120'
        },
        {
          prop: 'mobilephone',
          label: '手机号'
        },
        {
          prop: 'openid',
          label: 'OPENID',
          width: '220'
        },
        {
          prop: 'levelname',
          label: '等级'
        },
        {
          prop: 'input_date',
          label: '注册时间'
        }
      ],
      // 查询分页数据
      listQuery: {
        page: 1,
        pageSize: 10,
        totalCount: 1,
        openid: '',
        cardno: '',
        mobile: '',
        status: '0',
        level: '',
        channel: ''
      },
      list: [],
      // 加载数据加载状态
      listLoading: false,
      listOptions: {
        mutiSelect: false,
        stripe: true
      },
      inputDateRange: [],
      // 开关设置查询不需要分页列表数据
      listState: false,
      // 等级列表
      level: [],
      // 渠道列表
      channel: []
    }
  },
  methods: {
    // 是否选中
    isSelect(row) {
      // fix:因为这里没有卡号,所以匹配卡号
      return this.$u.test.empty(this.data.filter(d => d.card_no === row.card_no)[0])
    },
    // 选中事件
    add(row) {
      this.data.push(row)
      this.$emit('change', this.data)
    },
    // 取消选中事件
    sub(row) {
      this.data.splice(this.data.findIndex(item => item.card_no === row.card_no), 1)
      this.$emit('change', this.data)
    },
    // 确认
    confirm() {
      this.$emit('confirm')
    },
    // 关闭或者取消
    cancel() {
      this.$emit('changeShow', false)
      this.$emit('cancel')
    },
    // 搜索事件
    handleFilter() {
      this.dateFormat()
      this.hdlFilter()
    },
    // 时间格式转换
    dateFormat() {
      if (this.inputDateRange) {
        this.listQuery.beginTime = this.inputDateRange[0]
        this.listQuery.endTime = this.inputDateRange[1]
      } else {
        this.listQuery.beginTime = ''
        this.listQuery.endTime = ''
      }
    }
  }
}
</script>

<style scoped>

</style>
