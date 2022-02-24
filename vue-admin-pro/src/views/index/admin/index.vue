<template>
  <div class="dashboard-editor-container">
    <BarCard v-if="dataForm.customData.statistic" class="Malldata" :describe="lineData" style="padding: 0 !important;">
      <template v-slot:content>
        <u-chart :list="chartList" />
      </template>
    </BarCard>
    <BarCard class="Malldata" :describe="useddata">
      <template v-slot:content>
        <router-link v-for="(item,index) in usedList" :key="index" :to="item.path">
          <div class="used">
            <div class="use svg" :class="item.svg" />
            <div class="use title">
              {{ item.title }}
            </div>
          </div>
        </router-link>
      </template>
    </BarCard>
  </div>
</template>

<script>

import BarCard from '@/views/index/admin/components/BarCard'
export default {
  name: 'DashboardAdmin',
  components: { BarCard },
  data() {
    return {
      lineChartData: [],
      usedList: [
        {
          title: '发布产品',
          svg: 'product',
          path: '/goods/list'
        },
        {
          title: '订单管理',
          svg: 'order',
          path: '/order/list'
        },
        {
          title: '会员管理',
          svg: 'member',
          path: '/member/list'
        },
        {
          title: '财务记录',
          svg: 'finance',
          path: '/finance/recharge'
        },
        {
          title: '平台配置',
          svg: 'config',
          path: '/config/platform/platform'
        },
        {
          title: '文章管理',
          svg: 'article',
          path: '/article/list'
        },
        {
          title: '平台设置',
          svg: 'set',
          path: '/platform/adv'
        }
      ],
      dataList: [],
      useddata: {
        title: '热门功能',
        more: ''
      },
      Malldescribe: {
        title: '商城概况',
        more: ''
      },
      api: this.$u.api.ConfigPlatform,
      open: this.$u.api.open,
      dataForm: {
        logo: '',
        customData: {
          statistic: false
        }
      },
      dialogVisible: false
    }
  },
  created() {
  },
  methods: {
    // 提交编辑数据
    updateData() {
      const self = this
      self.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$confirm('确认更改平台配置 ', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            self.api.update(self.dataForm).then(() => {
              self.getLoad()
              self.$u.msg('保存成功')
            })
          }).catch(() => {
          })
        }
      })
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
    // 修改网站名称
    handleEdit() {
      this.dialogVisible = true
      this.dialogForm = Object.assign({}, this.dataForm)
      // 清除表单验证状态
      this.$nextTick(() => {
        this.$refs['dialogForm'].clearValidate()
      })
    },
    // 提交修改
    handledata() {
      const self = this
      self.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          self.dialogVisible = false
        }
      })
    },
    // 初始化数据
    getLoad() {
      const list = this.getdate()
      this.open.get().then(res => {
        if (res.code === 20000) {
          this.lineChartData = Object.values(res.data.statistic).reverse()
          this.lineChartData.forEach((item, index) => {
            const obj = {
              genre: item,
              sold: list[index]
            }
            this.chartList.push(obj)
          })
          this.ScaleChartList = [
            {
              item: '今日',
              count: res.data.visit.today,
              percent: res.data.visit.today / res.data.visit.total
            },
            {
              item: '昨日',
              count: res.data.visit.yesterday,
              percent: res.data.visit.yesterday / res.data.visit.total
            },
            {
              item: '近7日',
              count: res.data.visit.lastsevenday,
              percent: res.data.visit.lastsevenday / res.data.visit.total
            },
            {
              item: '近30日',
              count: res.data.visit.lastthirtyday,
              percent: res.data.visit.lastthirtyday / res.data.visit.total
            },
            {
              item: '总计',
              count: res.data.visit.total,
              percent: res.data.visit.total / res.data.visit.total
            }
          ]
          // this.lineData.title += '(总访问量：' + res.data.visit.total + ')'
          this.visit = res.data.visit
          this.dataList = [
            {
              shelf: '产品出售中',
              number: res.data.product1
            },
            {
              shelf: '产品上架中',
              number: res.data.product2
            },
            {
              shelf: '产品下架中',
              number: res.data.product3
            },
            {
              shelf: '产品购买中',
              number: res.data.product4
            },
            {
              shelf: '商家产品',
              number: res.data.product5
            }
          ]
        }
      })
      this.api.fetch().then(res => {
        this.dataForm = res.data
        localStorage.setItem('platform', JSON.stringify(this.dataForm))
        this.dataForm.domain = window.location.protocol + '//' + window.location.host
        if (this.dataForm.customData) {
          this.dataForm.customData = JSON.parse(this.dataForm.customData) || {}
          this.dataForm.customData.statistic = this.dataForm.customData.statistic !== false
          this.lineData.url = this.dataForm.customData.cnzz
          this.surveyList[1].data = this.dataForm.customData.PhoneShopdata
          this.surveyList[2].data = this.dataForm.customData.WxAppdata
          this.surveyList[3].data = this.dataForm.customData.Wxdata
          this.surveyList[4].data = this.dataForm.customData.PcUniondata
          this.surveyList[5].data = this.dataForm.customData.PhoneUniondata
          if (this.dataForm.customData.statistic) {
            this.dataList.push({
              shelf: '网站总访问量',
              number: this.visit.total
            })
            console.log(this.dataList)
          }
        } else {
          this.dataForm.customData = {}
          this.dataForm.customData.statistic = false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .dashboard-container{
    background-color: #f1f1f1;
  }
  .mall-left{
    width: 924px;
    min-width: 924px;
  }
  .mall-right{
    margin-left: 20px;
    min-width: 300px;
    .set{
      display: flex;
      .set-right{
        padding: 5px;
        font-size: 14px;
        margin-left: 10px;
        span{
          display: block;
          .el-icon-edit{
            cursor: pointer;
          }
          margin-bottom: 10px;
        }
      }
    }
  }
  .box{
    margin-top: 20px;
    background-color: #FFF;

    ::v-deep{
      .el-button--small.is-round {
        padding: 9px 15px;
        width: 100px;
        height: 41px;
        font-size: 16px;
      }
    }
  }
.dashboard-editor-container{
  padding: 20px ;
}

.Malldata{
  overflow: hidden;
  .dataList{
    float: left;
    text-align: center;
    /*width: 16%;*/
    .dataItem{
      margin-top: 20px;
    }
    .number{
      margin-bottom: 20px;
    }
    .title{
      font-size: 14px;
      font-weight: 400;
      color: rgba(153,153,153,1);
      line-height: 1;
    }
    .number{
      font-size: 24px;
      font-family: ArialMT;
      font-weight: 400;
      color: rgba(85,85,85,1);
      line-height: 1;
    }
  }
  .used{
    float: left;
    padding:20px 30px;
    cursor: pointer;
    /*width: 13%;*/
    margin: 10px 30px 10px 0;
    text-align: center;
    &:active,&:hover{
      box-shadow: 5px 5px 5px 5px #F6F6F6;
    }
    .svg{
      width: 40px;
      height: 40px;
      margin: 0 auto;
      background-size: cover !important;
    }
    .title{
      margin-top: 10px;
    }
    .product{
      background: url(../../../assets/svg/product.svg) no-repeat;
    }
    .order{
      background: url(../../../assets/svg/order.svg) no-repeat ;
    }
    .member{
      background: url(../../../assets/svg/member.svg) no-repeat;
    }
    .finance{
      background: url(../../../assets/svg/finance.svg) no-repeat;
    }
    .config{
      background: url(../../../assets/svg/config.svg) no-repeat ;

    }
    .article{
      background: url(../../../assets/svg/article.svg) no-repeat;
    }
    .set{
      background: url(../../../assets/svg/set.svg) no-repeat;
    }
  }
}

</style>
