<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Array,
      required: true
    },
    visit: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      chart: null,
      date: []
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  created() {
    this.getdate()
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions(this.chartData)
    },
    getdate() { // 获取日期
      var d = new Date()
      let date = d.getMonth() + 1 + '-' + d.getDate()
      for (let i = 2; i < 31; i++) {
        if (i < 31) {
          var month
          var r = d.getDate() - 1
          d.setDate(Math.abs(r))
          var day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
          r < 0 ? month = d.getMonth() < 10 ? '0' + d.getMonth() : d.getMonth() : month = d.getMonth() + 1 < 10 ? '0' + parseInt(d.getMonth() + 1) : d.getMonth() + 1
          // var year = new Date().getFullYear()
          const time = ',' + month + '/' + day
          date += time
        }
      }
      return date.split(',').reverse()
    },
    setOptions(expectedData) {
      this.chart.setOption({
        xAxis: {
          type: 'category',
          data: this.getdate(),
          boundaryGap: false,
          axisTick: {
            show: false
          }
        },
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          axisTick: {
            show: true
          }
        },
        title: {
          text: '近30日访问统计',
          left: 'center'
        },
        series: [{
          name: '访问量',
          itemStyle: {
            normal: {
              color: '#4b9afc',
              lineStyle: {
                color: '#4b9afc',
                width: 2
              }
            }
          },
          areaStyle: {},
          smooth: false,
          type: 'line',
          data: expectedData,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        }]
      })
    }
  }
}
</script>
