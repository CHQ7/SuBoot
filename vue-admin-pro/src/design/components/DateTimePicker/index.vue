<template>
  <el-date-picker
    ref="datePicker"
    v-model="currentVal"
    :type="type"
    :range-separator="rangeSeparator"
    :start-placeholder="startPlaceholder"
    :end-placeholder="endPlaceholder"
    :default-time="defaultTime"
    :value-format="valueFormat"
    :clearable="clearable"
    :picker-options="pickerOptions"
    @change="handleChange"
  />
</template>

<script>
/**
 * 时间选择器
 */
export default {
  name: 'DateTimePicker',
  model: {
    prop: 'value',
    event: 'change'
  },
  props: {
    /**
     * 绑定值
     */
    value: {
      type: [Date, String, Array],
      default: () => []
    },
    /**
     * 可选，绑定值的格式。不指定则绑定值为 Date 对象
     * https://element.eleme.cn/#/zh-CN/component/date-picker#ri-qi-ge-shi
     */
    valueFormat: {
      type: String,
      default: 'timestamp'
    },
    /**
     * 显示类型
     * year/month/date/week/ datetime/datetimerange/daterange
     */
    type: {
      type: String,
      default: 'datetimerange'
    },
    /**
     * 选择范围时的分隔符
     */
    rangeSeparator: {
      type: String,
      default: '-'
    },
    /**
     * 范围选择时开始日期的占位内容
     */
    startPlaceholder: {
      type: String,
      default: '开始日期'
    },
    /**
     * 范围选择时结束日期的占位内容
     */
    endPlaceholder: {
      type: String,
      default: '结束日期'
    },
    /**
     * 选中日期后的默认具体时刻
     */
    defaultTime: {
      type: Array,
      default: () => ['00:00:00', '23:59:59']
    },
    /**
     * 是否显示清除按钮
     */
    clearable: {
      type: Boolean,
      default: true
    },
    /**
     * 当前时间日期选择器特有的选项参考下表
     */
    pickerOptions: {
      type: Object,
      default: data => {
        return (data = {
          shortcuts: [
            {
              text: '今日',
              onClick(picker) {
                const nowDate = new Date()
                const start = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 00:00:00`)
                const end = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 23:59:59`)
                picker.$emit('pick', [start, end])
              }
            },
            {
              text: '咋日',
              onClick(picker) {
                const nowDate = new Date()
                const start = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 00:00:00`)
                const end = new Date(`${start.getFullYear()}-${start.getMonth() + 1}-${start.getDate()} 23:59:59`)
                start.setTime(start.getTime() - 3600 * 1000 * 24)
                end.setTime(end.getTime() - 3600 * 1000 * 24)
                picker.$emit('pick', [start, end])
              }
            },
            {
              text: '最近一周',
              onClick(picker) {
                const nowDate = new Date()
                const start = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 00:00:00`)
                const end = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 23:59:59`)
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
                picker.$emit('pick', [start, end])
              }
            },
            {
              text: '最近一个月',
              onClick(picker) {
                const nowDate = new Date()
                const start = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 00:00:00`)
                const end = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 23:59:59`)
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
                picker.$emit('pick', [start, end])
              }
            },
            {
              text: '最近三个月',
              onClick(picker) {
                const nowDate = new Date()
                const start = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 00:00:00`)
                const end = new Date(`${nowDate.getFullYear()}-${nowDate.getMonth() + 1}-${nowDate.getDate()} 23:59:59`)
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
                picker.$emit('pick', [start, end])
              }
            }
          ]
        })
      }
    }
  },
  data() {
    return {
      currentVal: []
    }
  },
  watch: {
    value: {
      immediate: true,
      handler(val) {
        if (val != null && val.length > 0) {
          this.currentVal = val
        }
      }
    }
  },
  methods: {
    handleChange(val) {
      this.$emit('change', val)
    }
  }
}
</script>

