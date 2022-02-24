<template>
  <div class="info">
    <el-page-header content="订单信息" @back="goBack" />
    <el-card class="box-card">
      <el-row>
        <el-col :span="9">
          <div class="grid-content bg-purple">
            <p class="card-title">
              订单信息
            </p>
            <order-info v-for="(item,index) in list" :key="'item'+index" :title="item.title" :value="item.value" />
            <el-divider />
            <order-info v-for="(item1,index1) in addressList" :key="index1" :title="item1.title" :value="item1.value" />
            <el-divider />
            <order-info title="卖家备注" :value="row.note" />
          </div>
        </el-col>
        <el-col :span="15">
          <div class="grid-content bg-purple-light">
            <p class="card-title">
              订单状态
            </p>
            <el-steps :active="row.flag === -1 ?row.flag:row.flag+2">
              <el-step title="创建订单" icon="el-icon-edit-outline" :description="row.createAt | date" />
              <el-step title="付款" icon="el-icon-bank-card" :description="row.createAt | date" />
              <el-step title="配货中" icon="el-icon-receiving" :description="row.createAt | date" />
              <el-step title="发货中" icon="el-icon-truck" :description="row.createAt | date" />
              <el-step title="完成" icon="el-icon-time" :description="row.createAt | date" />
            </el-steps>
            <el-divider />
            <div>
              <el-button plain @click="changeEdit(0)">备注</el-button>
              <el-button v-if="row.flag === 0 || row.flag === 1 || row.flag === 2" plain @click="changeEdit(1)">修改订单</el-button>
              <el-button v-if="row.flag === 2" plain @click="changeFlag(3)">取消配送</el-button>
              <el-button v-if="row.flag === 2" plain @click="changeEdit(2)">确认发货</el-button>
              <el-button v-if="row.flag === 0 || row.flag === 1 || row.flag === 2" plain @click="changeFlag(0)">取消订单</el-button>
              <el-button v-if="row.flag === 2" plain>物流信息</el-button>
              <el-button v-if="row.flag === 1" plain @click="changeFlag(1)">设置配货</el-button>
              <el-button v-if="row.flag === -1" plain @click="changeFlag(2)">删除订单</el-button>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-divider />

      <el-collapse v-model="activeName" accordion>
        <el-collapse-item title="商品列表" name="1">
          <el-table
            :data="row.goodsList"
            style="width: 100%;font-size:13px"
          >
            <el-table-column
              label="商品"
            >
              <template slot-scope="scope">
                <div class="order">
                  <div class="cell">
                    <div class="order-goods-list">
                      <el-image
                        style="width: 60px; height: 60px"
                        :src="handleThumb(scope.row.thumb)"
                      />
                      <div class="goods-info order-text">
                        <p>
                          <span class="link">
                            {{ scope.row.title }}
                          </span>
                        </p>
                        <p>
                          本店价：{{ scope.row.price | amount }} x {{ scope.row.num }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </template>
            </el-table-column>

            <el-table-column
              label="单价"
              header-align="center"
              align="center"
              width="170px"
            >
              <template slot-scope="scope">
                <p>
                  {{ scope.row.price | amount }}
                </p>
              </template>
            </el-table-column>

            <el-table-column
              label="数量"
              header-align="center"
              align="center"
              width="170px"
            >
              <template slot-scope="scope">
                <p>
                  {{ scope.row.num }}
                </p>
              </template>
            </el-table-column>

            <el-table-column
              label="小计"
              header-align="center"
              align="center"
              width="170px"
            >
              <template slot-scope="scope">
                <p>
                  {{ scope.row.goodsprice | amount }}
                </p>
              </template>
            </el-table-column>

            <el-table-column
              label="状态"
              width="170px"
              header-align="center"
              align="center"
            >
              <template slot-scope="scope">
                <el-button type="text" style="color: #000000">{{ hdlFlag(scope.row.flag) }}</el-button>
              </template>
            </el-table-column>
          </el-table>

          <div class="cs-fr">
            <order-info v-for="(item,index) in price" :key="'item'+index" :title="item.title" :value="item.value" />
          </div>
        </el-collapse-item>
      </el-collapse>

      <el-collapse v-model="activelogName" class="log" accordion>
        <el-collapse-item title="订单日志" name="1">
          <el-steps :space="80" direction="vertical" process-status="wait" :active="10">
            <el-step v-for="item in 10" :key="item">
              <template slot="title">
                {{ item }}
              </template>
              <template slot="description">
                <p>{{ item }}</p>
                <p>{{ row.createAt | date }}</p>
              </template>
            </el-step>
          </el-steps>
        </el-collapse-item>
      </el-collapse>

    </el-card>
  </div>
</template>

<script>
import orderInfo from '@/views/order/list/components/orderInfo'
export default {
  name: 'UInfo',
  components: { orderInfo },
  props: {
    row: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  data() {
    return {
      list: [],
      addressList: [],
      activeName: '1',
      activelogName: '1',
      price: []
    }
  },
  created() {
    console.log('row::' + JSON.stringify(this.row))
    this.list = [
      {
        title: '订单号',
        value: this.row.outTradeNo
      },
      {
        title: '支付流水号',
        value: ''
      },
      {
        title: '支付方式',
        value: '货到付款'
      },
      {
        title: '买家',
        value: this.row.realname
      },
      {
        title: '买家备注',
        value: this.row.remark
      }]
    this.addressList = [
      {
        title: '收货人姓名',
        value: this.row.realname
      },
      {
        title: '收货地址',
        value: this.row.province + this.row.city + this.row.area + this.row.address
      },
      {
        title: '配送方式',
        value: ''
      },
      {
        title: '收货人手机',
        value: this.row.mobile
      }]
    this.price = [
      {
        title: '商品价格',
        value: this.$u.amount.format(this.row.goodsprice)
      },
      {
        title: '运费',
        value: this.$u.amount.format(this.row.dispatchprice)
      },
      {
        title: '总价',
        value: this.$u.amount.format(this.row.totalFee)
      }]
  },
  methods: {
    changeFlag(i) {
      this.$emit('changeFlag', this.row, i)
    },
    changeEdit(i) {
      this.$emit('changeEdit', this.row, i)
    },
    goBack() {
      this.$emit('show')
    },
    // 获取第一张图片
    handleThumb(thumb) {
      return thumb.split(',')[0]
    },
    hdlFlag(flag) { // 订单状态
      let name
      if (flag === -1) {
        name = '交易关闭'
      } else if (flag === 0) {
        name = '等待买家付款'
      } else if (flag === 1) {
        name = '配货中'
      } else if (flag === 2) {
        name = '发货中'
      } else if (flag === 3) {
        name = '交易成功'
      }
      return name
    }
  }
}
</script>

<style lang="scss" scoped>
  .info{
    ::v-deep{
      .el-page-header{
        padding: 10px;
        border: 1px solid #e6ebf5;
        border-bottom: 0;
        border-radius: 4px 4px 0 0;
      }
    }

    .box-card{
      box-shadow:0 0 #000;
      border-radius: 0 0 4px 4px;
      ::v-deep{
        .el-card__body{
          .el-row{
            .el-col-9{
              padding-right: 10px;
              border-right:1px solid #e6ebf5;
            }
            .el-col-15{
              padding-left:20px;
            }
          }
        }
      }

      .cs-fr{
        float: right;
        padding: 20px 0;
        font-size: 16px;

        ::v-deep{
          .order-info{
            font-size: 15px;
          }
          .dd{
            width: 150px;
            text-align: end;
          }
        }
      }

      ::v-deep{
        .el-collapse-item__content{
          overflow: hidden;
        }
        .el-collapse-item__header{
          font-size: 16px;
        }
      }

      .log{
        border-top: 0;

        ::v-deep{
          .el-step__title{
            color: #303133;
          }
          .el-step__description{
            p{
              padding: 0;
              margin: 0;
              color: #303133 !important;
              font-size: 13px;
              &:last-child{
                color: #909399 !important;
                font-size: 12px;
              }
            }
          }
        }
      }
    }

    .order{
      display: inline-block;
      .order-summary{
        padding-left: 20px;
        font-size: 13px;
        padding-top: 5px;
      }
      .cell{
        display: inline-block;
        .order-goods-list{
          .el-image{
            overflow: auto;
            min-width: 80px;
          }
          display: flex;
          padding-top: 7px;
          &:last-child{
            padding-bottom: 10px;
          }
          .goods-info{
            padding-left: 20px;
            padding-right: 40px;
            min-width: 405px;
            p{
              padding: 0;
              margin: 0;
              color: #909399;
              font-size: 13px;
              .link{
                font-size: 14px;
                color: #606266;
              }
            }
          }
        }
      }
    }
    .buyer{
      p{
        padding: 0;
        margin: 0;
        i{
          font-size: 15px;
        }
      }
    }

  .goods-time {
    margin: 0 15px;
  }
  .goods-number {
    color: #8f8e8e;
  }
  }

</style>
