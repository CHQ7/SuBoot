<template>
  <div class="app-container">

    <u-page v-show="show" title="订单管理">

      <u-filtered>
        <el-input v-model="listQuery.keyword" placeholder="查询订单号" style="width: 200px;" clearable @keyup.enter.native="hdlFilter" />
        <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
      </u-filtered>

      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane v-for="(item,index) in tabList" :key="index" :label="item.label+'('+ item.count +')'" :name="item.name">
          <el-table
            :data="list"
            style="width: 100%;font-size:13px"
          >
            <el-table-column
              type="selection"
              width="55"
            />
            <el-table-column
              label="订单"
              min-width="260"
            >
              <template slot-scope="scope">
                <div class="order">
                  <div class="order-summary cs-mb-5">
                    <span class="goods-number">订单编号：{{ scope.row.outTradeNo }}</span>
                    <span class="goods-number" style="margin-right: 10px">下单时间：{{ scope.row.createAt | date }}</span>
                  </div>
                  <div class="cell">
                    <div v-for="(goods,num) in scope.row.goodsList" :key="num" class="order-goods-list">
                      <el-image
                        style="width: 80px; height: 80px"
                        :src="handleThumb(goods.thumb)"
                      />
                      <div class="goods-info order-text">
                        <p>
                          <span class="link">
                            {{ goods.title }}
                          </span>
                        </p>
                        <p>
                          <!--                          内存:4G 网络:4G 尺码:M 颜色:银色-->
                        </p>
                        <p>
                          本店价：{{ goods.price | amount }} x {{ goods.num }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </template>
            </el-table-column>
            <el-table-column
              prop="name"
              label="订单金额"
              width="180"
            >
              <template slot-scope="scope">
                <el-button size="mini" type="text" style="color: #909399">商品价格:￥{{ scope.row.goodsprice | amount }}</el-button><br>
                <el-button size="mini" type="text" style="color: #909399">运费:￥{{ scope.row.dispatchprice | amount }}</el-button><br>
                <el-button size="mini" type="text" style="color: #f56c6c">应付款:￥{{ scope.row.totalFee | amount }}</el-button><br>
              </template>
            </el-table-column>

            <el-table-column
              prop="address"
              label="买家"
            >
              <template slot-scope="scope">
                <div class="buyer">
                  {{ scope.row.realname }}
                  <p>快递配送</p>
                  <p>
                    <el-tooltip placement="top">
                      <div slot="content">
                        姓名：{{ scope.row.realname }}<br>
                        手机：{{ scope.row.mobile }}<br>
                        姓名：{{ scope.row.province + scope.row.city + scope.row.area + scope.row.address }}
                      </div>
                      <i class="el-icon-house" />
                    </el-tooltip>
                    <el-tooltip v-if="scope.row.remark" placement="top">
                      <div slot="content">
                        卖家备注：{{ scope.row.remark }}
                      </div>
                      <i class="el-icon-chat-dot-round" />
                    </el-tooltip>
                  </p>
                </div>
              </template>
            </el-table-column>
            <el-table-column
              prop="address"
              label="交易状态"
              width="170px"
              header-align="center"
              align="center"
            >
              <template slot-scope="scope">
                <el-button type="text" style="color: #000000">{{ hdlFlag(scope.row.flag) }}</el-button>
                <br>
                <el-button type="text" @click="hadleDetails(scope.row)">查看详情</el-button>
              </template>
            </el-table-column>
            <el-table-column
              prop="address"
              label="操作"
              header-align="center"
              align="center"
            >
              <template slot-scope="scope">
                <div class="itemState">
                  <el-button v-if="scope.row.flag === -1" type="text" style="color: #606266" @click="handleFlag(scope.row,2)">删除订单</el-button>
                  <el-button v-if="scope.row.flag === 0 || scope.row.flag === 1 || scope.row.flag === 2" type="text" class="succes" @click="handleEdit(scope.row,1)">修改订单</el-button>
                  <el-button v-if="scope.row.flag === 2" type="text" class="warning" @click="handleFlag(scope.row,3)">取消配货</el-button>
                  <el-button v-if="scope.row.flag === 0 || scope.row.flag === 1 || scope.row.flag === 2" type="text" class="danger" @click="handleFlag(scope.row,0)">取消订单</el-button>
                  <el-button v-if="scope.row.flag === 1" type="text" @click="handleFlag(scope.row,1)">设置配货</el-button>
                  <el-button v-if="scope.row.flag === 2" type="text" @click="handleEdit(scope.row,2)">确定发货</el-button>
                  <el-button v-if="scope.row.flag === 3" type="text">物流信息</el-button>
                  <el-button type="text" class="info" @click="handleEdit(scope.row,0)">备注</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <u-pagination
            v-show="list.length>0"
            :total="listQuery.totalCount"
            :page.sync="listQuery.pageNumber"
            :limit.sync="listQuery.pageSize"
            @pagination="hdlList"
          />

        </el-tab-pane>
      </el-tabs>
    </u-page>

    <u-dialog title="卖家备注" class="seller_note" :show.sync="dialogFormVisible" width="30%" @confirm="handleUpdate()">
      <el-input
        v-model="dataForm.note"
        type="textarea"
        :rows="6"
        placeholder="请输入内容"
        maxlength="200"
      />
    </u-dialog>

    <u-dialog title="修改订单" :show.sync="OrderVisible" @confirm="handleUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="120px">

        <el-form-item prop="realname" label="收货人姓名">
          <el-input v-model="dataForm.realname" placeholder="请输入收货人姓名" clearable maxlength="30" show-word-limit />
        </el-form-item>

        <el-form-item prop="mobile" label="收货人手机号">
          <el-input v-model="dataForm.mobile" placeholder="请输入收货人手机号" clearable />
        </el-form-item>

        <el-form-item prop="select" label="收货区域">
          <el-cascader
            v-model="dataForm.select"
            :options="options"
            :props="{value:'label',label:'label'}"
            placeholder="请选择收货区域"
            @change="handleChange"
          />
        </el-form-item>

        <el-form-item prop="address" label="详细地址">
          <el-input v-model="dataForm.address" placeholder="请输入详细地址" clearable />
        </el-form-item>
      </el-form>
    </u-dialog>

    <u-dialog title="确定发货" width="760px" :show.sync="deliveryVisible" @confirm="handleDeliver()">
      <el-table
        :data="dataForm.goodsList"
        style="width: 100%;font-size:13px"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
        />
        <el-table-column
          label="商品"
        >
          <template slot-scope="scope">
            <div class="order">
              <div class="cell">
                <div class="order-goods-list">
                  <el-image
                    style="width: 80px; height: 80px"
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
          label="数量"
          header-align="center"
          align="center"
          width="70px"
        >
          <template slot-scope="scope">
            <p>
              {{ scope.row.num }}
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

      <div class="cs-mt">
        <div class="radiogroup">
          <el-radio-group v-model="dataForm.radio" @change="change">
            <el-radio label="1">无需配送</el-radio>
            <el-radio label="2">配送方式</el-radio>
            <el-radio label="3">快递公司</el-radio>
          </el-radio-group>
        </div>
        <el-form v-if="dataForm.radio === '2' || dataForm.radio === '3'" ref="OrderMode" :inline="true" :model="dataForm" :rules="rulesMode" class="demo-form-inline">
          <el-form-item v-if="dataForm.radio === '2'" prop="mode" courier label="配送方式">
            <el-select v-model="dataForm.mode" placeholder="请选择">
              <el-option
                v-for="item in cities"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item v-if="dataForm.radio === '3'" prop="express" courier label="快递公司">
            <el-select v-model="dataForm.express" clearable placeholder="请选择">
              <el-option
                v-for="item in express"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item prop="courier" label="快递单号">
            <el-input v-model="dataForm.courier" placeholder="请输入快递单号" clearable />
          </el-form-item>
        </el-form>
      </div>

    </u-dialog>

    <u-info v-if="!show" :row.sync="dataForm" @show="show = true" @changeEdit="handleEdit" @changeFlag="handleFlag" />

  </div>
</template>
<script>
import options from '@/design/data/region.json'
import uInfo from './components/info'
export default {
  components: { uInfo },
  data() {
    return {
      api: this.$u.api.Order,
      options: options,
      activeName: 'first',
      show: true,
      tabList: [
        {
          label: '全部',
          name: 'first',
          count: 0
        },
        {
          label: '未付款',
          name: 'second',
          count: 0
        },
        {
          label: '已付款',
          name: 'third',
          count: 0
        },
        {
          label: '代发货',
          name: 'fourth',
          count: 0
        },
        {
          label: '已发货',
          name: 'fifth',
          count: 0
        },
        {
          label: '已完成',
          name: 'sixth',
          count: 0
        },
        {
          label: '已取消',
          name: 'seventh',
          count: 0
        }
      ],

      cities: [{ // 配送方式
        value: '顺丰速运',
        label: '快递配送'
      }, {
        value: 'EMS',
        label: '物流配送'
      }, {
        value: '卖家自提',
        label: '卖家自提'
      }, {
        value: '同城配送',
        label: '同城配送'
      }],

      express: [{ // 快递公司
        value: '顺丰速运',
        label: '顺丰速运'
      }, {
        value: '百世快递',
        label: '百世快递'
      }, {
        value: '中通快递',
        label: '中通快递'
      }, {
        value: '申通快递',
        label: '申通快递'
      },
      {
        value: '圆通速递',
        label: '圆通速递'
      },
      {
        value: '韵达速递',
        label: '韵达速递'
      },
      {
        value: '邮政快递包裹',
        label: '邮政快递包裹'
      },
      {
        value: 'EMS',
        label: 'EMS'
      },
      {
        value: '京东物流',
        label: '京东物流'
      },
      {
        value: '买家自提',
        label: '买家自提'
      },
      {
        value: '同城配送',
        label: '同城配送'
      }],

      list: [],
      listLoading: true,
      listOptions: {
        stripe: false,
        defaultExpandAll: true
      },
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        keyword: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑订单',
        create: '新增订单'
      },
      dataForm: {},
      rules: {
        realname: [
          { required: true, message: '请输入收货人姓名', trigger: ['blur'] }
        ],
        mobile: [
          { required: true, message: '请输入收货人手机号', trigger: ['blur'] },
          { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号' }
        ],
        select: [
          { required: true, message: '请选择收货区域', trigger: ['blur'] }
        ],
        address: [
          { required: true, message: '请输入详细地址', trigger: ['blur'] }
        ]
      },
      rulesMode: {
        mode: [
          { required: true, message: '请选择配送方式', trigger: ['blur'] }
        ],
        express: [
          { required: true, message: '请选择快递公司', trigger: ['blur'] }
        ],
        courier: [
          { required: true, message: '请输入快递单号', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        disabled: true
      },
      // 修改订单
      OrderVisible: false,
      // 配送
      deliveryVisible: false
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    /**
     * 获取列表数据
     */
    hdlList() {
      const self = this
      // 打开加载状态
      self.listLoading = true
      // 查询分页数据
      self.api.list(self.listQuery).then(res => {
        // 获取分页列表数据
        self.list = res.data.list
        // 获取分页数据,总页数
        self.tabList[0].count = self.listQuery.totalCount = res.data.totalCount
        // 关闭加载状态
        setTimeout(() => {
          self.listLoading = false
        }, 0.5 * 1000)
      })
    },

    handleEdit(row, i) { // 修改
      this.dataForm = {}
      this.dataForm = Object.assign({}, row)
      if (i === 0) {
        this.dialogFormVisible = true
      } else if (i === 1) {
        this.dataForm.select = [this.dataForm.province, this.dataForm.city, this.dataForm.area]
        this.OrderVisible = true
      } else if (i === 2) {
        this.deliveryVisible = true
        this.dataForm.selected = []
      }
      console.log(JSON.stringify(this.dataForm))
    },
    handleDeliver() { // 发货
      if (this.dataForm.selected.length === 0) {
        this.$u.msg('请先选择你要发货的商品')
        return
      }
      if (!this.dataForm.radio) {
        this.$u.msg('请先选择配送方式')
        return
      }
      if (this.dataForm.radio !== '1') {
        this.$refs['OrderMode'].validate(valid => {
          if (valid) {
            this.deliveryVisible = false
          }
        })
      } else {
        this.deliveryVisible = false
      }
    },
    handleSelectionChange(val) { // 卖家发货选中
      this.dataForm.selected = val
    },
    handleUpdate() { // 更新
      if (this.OrderVisible) {
        this.$refs['dialogForm'].validate(valid => {
          if (valid) {
            this.OrderVisible = false
          }
        })
      } else if (this.dialogFormVisible) {
        this.dialogFormVisible = false
      }
      // this.OrderVisible = this.dialogFormVisible = false
    },
    handleFlag(row, i) { // 操作
      const self = this
      const title = i === 0 ? '此操作将取消该订单，是否继续？' : (i === 1 ? '是否确认配货？' : (i === 2 ? '此操作将删除该订单后,删除后将不能恢复，是否继续删除？' : '是否确认取消配货'))
      // 模态对话框组件
      self.$confirm(title, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {

      }).catch(() => {

      })
    },
    handleClick(tab, event) { // 监听tabs
      console.log(tab, event)
    },
    hadleDetails(row) { // 查看详情
      this.dataForm = {}
      this.dataForm = Object.assign({}, row)
      this.show = false
    },
    change(e) {
      this.dataForm.radio = e
    },
    handleChange(e) { // 监听地址变化
      this.dataForm.province = e[0]
      this.dataForm.city = e[1]
      this.dataForm.area = e[2]
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
    },
    // 获取第一张图片
    handleThumb(thumb) {
      return thumb.split(',')[0]
    }
  }
}
</script>
<style lang="scss" scoped>
  .app-container{
    ::v-deep{
      .filter{
        border: 0;
      }
      .el-table_1_column_3,.el-table_1_column_4{
        .cell{
          padding: 0;
          .el-button--text{
            padding: 0;
            font-size: 13px;
          }

        }
      }
      .cell{
        .el-button--text{
          padding: 0 15px;
          font-size: 13px;
        }
        .el-button--small {
          padding: 5px 4px;
        }
        button.el-button.el-button--text.el-button--small {
          width: 100%;
          margin-left: 0 !important;
        }
        .succes{
          color: #67c23a;
        }
        .danger{
          color: #f56c6c;
        }
        .warning{
          color: #e6a23c;
        }
        .info{
          color: #909399;
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
  }

  .goods-time {
    margin: 0 15px;
  }
  .goods-number {
    color: #8f8e8e;
  }
  ::v-deep{
    .el-dialog__header{
      background-color: #FFF;
      .el-dialog__title{
        font-size: 18px;
      }
    }
    .el-cascader--small{
      width: 100%;
    }
  }
  .cs-mt{
    padding: 20px 0;
    .radiogroup{
      margin-bottom: 20px;
    }
    ::v-deep{
      .el-select{
        width:280px;
      }
    }
  }
</style>
