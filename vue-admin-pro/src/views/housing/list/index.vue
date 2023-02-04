<template>
  <div class="app-container">

    <u-page title="房源管理">
      <div slot="header">
        <el-button type="primary" icon="el-icon-edit" @click="hdlOpen(newForm)">
          {{ textMap['create'] }}
        </el-button>
      </div>

      <u-filtered>
        <el-form ref="listQuery" :inline="true" :model="listQuery" class="search-form">
          <el-form-item label="标题" prop="title">
            <el-input v-model="listQuery.title" placeholder="关键词查询" clearable />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <u-status v-model="listQuery.status" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="hdlFilter">搜索</el-button>
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
              <el-button type="text" @click="handleEdit(row)">编辑</el-button>
              <el-button type="text" @click="hdlDelete(row.id, row.title)">删除</el-button>
            </template>
          </el-table-column>
        </template>
      </u-table>

    </u-page>

    <u-dialog :title="textMap[dialogStatus]" :show.sync="dialogFormVisible" @confirm="dialogStatus==='create'?hdlCreate():hdlUpdate()">
      <el-form ref="dialogForm" :rules="rules" :model="dataForm" label-width="80px">

        <el-form-item prop="type" label="房源类型">
          <el-radio-group v-model="dataForm.type">
            <el-radio label="二手房">二手房</el-radio>
            <el-radio label="出租房">出租房</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="thumb" label="房源图片">
          <u-upload v-model="dataForm.thumb" name="file" tips="建议尺寸:640 * 640 , 请将所有房源图片尺寸保持一致" />
        </el-form-item>

        <el-row>
          <el-col :span="12">
            <el-form-item prop="name" label="房源名称">
              <el-input v-model="dataForm.name" placeholder="房源名称" clearable maxlength="30" show-word-limit />
            </el-form-item>

            <el-form-item prop="region" label="所属区域">
              <u-region v-model="dataForm.region" @change="changeRegion" />
            </el-form-item>

            <el-form-item prop="address" label="房源地址">
              <el-input v-model="dataForm.address" placeholder="房源地址" clearable />
            </el-form-item>

            <el-form-item prop="contacts" label="联系人">
              <el-input v-model="dataForm.contacts" placeholder="联系人" clearable maxlength="10" show-word-limit />
            </el-form-item>

            <el-form-item prop="contact_phone" label="联系电话">
              <el-input v-model="dataForm.contact_phone" placeholder="联系电话" clearable maxlength="11" show-word-limit />
            </el-form-item>

            <el-form-item prop="house_type" label="物业类型">
              <el-radio-group v-model="dataForm.house_type">
                <el-radio label="住宅">住宅</el-radio>
                <el-radio label="别墅">别墅</el-radio>
                <el-radio label="商铺">商铺</el-radio>
                <el-radio label="写字楼">写字楼</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item prop="renovation" label="装修情况">
              <el-radio-group v-model="dataForm.renovation">
                <el-radio label="毛坯">毛坯</el-radio>
                <el-radio label="简装">简装</el-radio>
                <el-radio label="精装">精装</el-radio>
                <el-radio label="豪装">豪装</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="code" label="编码">
              <el-input v-model="dataForm.code" placeholder="房源编码" clearable />
            </el-form-item>

            <el-form-item prop="acreage" label="房源面积">
              <el-input v-model="dataForm.acreage" placeholder="房源面积" clearable>
                <template slot="append">㎡</template>
              </el-input>
            </el-form-item>

            <el-form-item v-if="dataForm.type === '二手房'" prop="price" label="总价">
              <el-input v-model="dataForm.price" placeholder="总价" clearable>
                <template slot="append">万</template>
              </el-input>
            </el-form-item>
            <el-form-item v-if="dataForm.type === '出租房'" prop="price" label="月租">
              <el-input v-model="dataForm.price" placeholder="月租" clearable>
                <template slot="append">元/月</template>
              </el-input>
            </el-form-item>

            <el-form-item label="楼层">
              <el-input v-model="dataForm.storey" placeholder="20" class="input-with-select">
                <el-select slot="prepend" v-model="dataForm.storeytype" placeholder="请选择">
                  <el-option label="底层" value="底层" />
                  <el-option label="中层" value="中层" />
                  <el-option label="高层" value="高层" />
                </el-select>
                <template slot="append">层</template>
              </el-input>
            </el-form-item>

            <el-form-item prop="houseType" label="房源户型">
              <div style="display: flex;flex-direction: row;">
                <el-input v-model="dataForm.room" placeholder="" clearable style="width: 100px" /><span style="padding-left: 10px; padding-right: 10px">室</span>
                <el-input v-model="dataForm.living_room" placeholder="" clearable style="width: 100px" /><span style="padding-left: 10px;padding-right: 10px">厅</span>
                <el-input v-model="dataForm.toilet" placeholder="" clearable style="width: 100px" /><span style="padding-left: 10px">卫</span>
              </div>
            </el-form-item>
            <el-form-item prop="orientation" label="朝向">
              <el-select v-model="dataForm.orientation" placeholder="请选择" style="width: 100%">
                <el-option label="东西" value="东西" />
                <el-option label="南北" value="南北" />
                <el-option label="西南" value="西南" />
                <el-option label="西北" value="西北" />
                <el-option label="东北" value="东北" />
                <el-option label="东北" value="东北" />
                <el-option label="东南" value="东南" />
                <el-option label="北" value="北" />
                <el-option label="西" value="西" />
                <el-option label="南" value="南" />
                <el-option label="东" value="东" />
              </el-select>
            </el-form-item>

            <el-form-item v-if="dataForm.type === '二手房'" prop="first_pay" label="首付比例">
              <el-input v-model="dataForm.first_pay" placeholder="例：30" clearable>
                <template slot="append">%</template>
              </el-input>
            </el-form-item>

            <el-form-item v-if="dataForm.type === '出租房'" prop="rent_type" label="租赁方式">
              <el-select v-model="dataForm.rent_type" placeholder="请选择" style="width: 100%">
                <el-option label="整租" value="整租" />
                <el-option label="合租" value="合租" />
              </el-select>
            </el-form-item>

            <el-form-item v-if="dataForm.type === '出租房'" prop="rent_type" label="支付方式">
              <el-select v-model="dataForm.pay_type" placeholder="请选择" style="width: 100%">
                <el-option label="押一付三" value="押一付三" />
                <el-option label="押二付三" value="押二付三" />
                <el-option label="押一付一" value="押一付一" />
              </el-select>
            </el-form-item>

          </el-col>
        </el-row>

        <el-form-item prop="tags" label="特色标签">
          <el-input v-model="dataForm.tags" type="textarea" placeholder="多个标签用','分隔" clearable rows="3" />
        </el-form-item>

        <el-form-item prop="content" label="房源简介">
          <u-tinymce v-model="dataForm.content" :height="300" />
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="dataForm.status">
            <el-radio :label="true">启用</el-radio>
            <el-radio :label="false">禁用</el-radio>
          </el-radio-group>
        </el-form-item>

      </el-form>
    </u-dialog>

  </div>
</template>
<script>
export default {
  data() {
    return {
      api: this.$u.api.Housing,
      // 表格
      columns: [
        {
          prop: 'name',
          label: '标题'
        },
        {
          prop: 'thumb',
          label: '图片',
          image: true
        },
        {
          prop: 'acreage',
          label: '面积'
        },
        {
          prop: 'acreage',
          label: '总价'
        },
        {
          prop: 'location',
          label: '排序',
          width: '70'
        },
        {
          prop: 'status',
          label: '状态',
          render: (h, params) => {
            if (params.row.status) {
              return h('el-tag', { props: { type: 'success' }}, '已启用')
            } else {
              return h('el-tag', { props: { type: 'danger' }}, '禁用')
            }
          }
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
        mutiSelect: true, // boolean 是否多选
        stripe: true // boolean 斑马纹
      },
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1,
        title: '',
        status: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑房源',
        create: '新增房源'
      },
      dataForm: {
        region: [],
        room: '',
        living_room: '',
        toilet: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: ['blur'] }
        ]
      },
      // 删除选中数据
      selectData: [],
      newForm: {
        type: '二手房',
        status: true
      }
    }
  },
  created() {
    this.hdlList()
  },
  methods: {
    handleEdit(row) {
      const { province, city, area } = row
      row.region = [province, city, area]
      this.hdlEdit(row)
    },
    // 获取地区
    changeRegion(item) {
      if (item) {
        this.dataForm.province = item[0]
        this.dataForm.city = item[1]
        this.dataForm.area = item[2]
      }
    }
  }
}
</script>
<style>
.input-with-select .el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>
