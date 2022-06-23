<template>
  <div clas="v-table">
    <el-table
      v-loading="loading"
      :data="data"
      row-key="id"
      :max-height="options.maxHeight"
      :stripe="options.stripe"
      :border="options.border"
      :default-expand-all="options.defaultExpandAll"
      :tree-props="options.treeProps"
      @selection-change="handleSelectionChange"
    >
      <!--selection选择框-->
      <el-table-column
        v-if="options.mutiSelect"
        type="selection"
        width="50"
        align="center"
      />

      <slot name="left" />
      <!--序号-->
      <el-table-column
        v-if="options.index"
        label="序号"
        type="index"
        width="50"
        align="center"
      />
      <!--数据列-->
      <template v-for="(column, index) in columns">
        <el-table-column
          :key="index"
          :prop="column.prop"
          :label="column.label"
          :align="column.align || 'center'"
          :width="column.width"
          :fixed="column.fixed"
          :sortable="column.sortable || false"
          show-overflow-tooltip
        >
          <template slot-scope="scope">

            <!-- slot 你可以其他常用项,暂时待定,没有想好 -->

            <!-- Render函数 -->
            <template v-if="!column.render">
              <template v-if="column.formatter">
                <span v-html="column.formatter(scope.row, column)" />
              </template>
              <!-- 过滤掉对应开关switch&图片image&时间戳time -->
              <template v-else-if="!column.switch&&!column.image&&!column.timestamp">
                <span>{{ scope.row[column.prop] }}</span>
              </template>
            </template>
            <template v-else>
              <expand-dom :column="column" :row="scope.row" :render="column.render" :index="index" />
            </template>

            <!-- switch 操作按钮 -->
            <template v-if="column.switch">
              <el-switch v-model="scope.row[column.prop]" @change="column.change(scope.row[column.prop],scope.row)" />
            </template>
            <!-- switch 操作按钮 -->

            <!-- image 操作按钮 -->
            <template v-if="column.image">
              <el-image v-if="scope.row[column.prop]" style="width: 50px; max-height: 50px;" :src="handleThumb(scope.row[column.prop])" :preview-src-list="handleThumbs(scope.row[column.prop])" />
            </template>
            <!-- image 操作按钮 -->

            <!-- time 操作按钮 -->
            <template v-if="column.timestamp">
              {{ scope.row[column.prop] | date }}
            </template>
            <!-- time 操作按钮 -->

            <!-- 操作 -->
            <template v-if="column.button">
              <template v-for="(btn, i) in column.group">
                <el-button
                  v-if="btn.cnd || true"
                  :key="i"
                  :type="btn.type || 'text'"
                  :size="btn.size || 'mini'"
                  :icon="btn.icon"
                  :disabled="btn.disabled"
                  :plain="btn.plain"
                  @click.stop="btn.click(scope.row, scope.$index, scope)"
                >{{ btn.name }}</el-button>
              </template>
            </template>
          <!-- 操作 -->

          </template>

        </el-table-column>
      </template>

      <slot name="right" />
    </el-table>

    <u-pagination
      v-if="page"
      v-show="data.length>0"
      :total="pagination.totalCount"
      :page.sync="pagination.page"
      :limit.sync="pagination.pageSize"
      @pagination="fetch"
    />

  </div>
</template>

<script>
/**
 * 表格组件
 */
export default {
  name: 'Table',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    // 封装Render函数
    expandDom: {
      functional: true,
      props: {
        row: Object,
        render: Function,
        index: Number,
        column: {
          type: Object,
          default: null
        }
      },
      render: (h, ctx) => {
        const params = {
          row: ctx.props.row,
          index: ctx.props.index
        }
        if (ctx.props.column) params.column = ctx.props.column
        return ctx.props.render(h, params)
      }
    }
  },
  props: {
    /** 显示的数据 */
    data: {
      type: Array,
      default: () => []
    },
    /** 表头的数据 */
    columns: {
      type: Array,
      default: () => []
    },
    /** 显示的数据 */
    loading: {
      type: Boolean,
      default: false
    },
    /** 显示分页的数据 */
    page: {
      type: Boolean,
      default: true
    },
    /** 点击分页的查询事件 */
    fetch: {
      type: Function,
      default: function() {}
    },
    pagination: {
      type: Object,
      default: data => {
        return (data = {
          page: 1,
          pageSize: 10,
          totalCount: 1
        })
      }
    },
    options: {
      type: Object,
      default: data => {
        return (data = {
          mutiSelect: false, // boolean 是否多选
          index: false, // boolean 是否展示序列号
          stripe: true, // boolean 斑马纹
          border: false, // boolean 纵向边框
          size: 'medium', // String  medium / small / mini  table尺寸
          fit: true, // 自动撑开
          defaultExpandAll: false, // 是否默认展开所有行，当 Table 包含展开行存在或者为树形表格时有效
          treeProps: {}
        })
      }
    }
  },
  data() {
    return {

    }
  },
  methods: {
    // 多选框选择变化触发事件
    handleSelectionChange(selection) {
      this.$emit('selection-change', selection)
    },
    // 获取第一张图片
    handleThumb(thumb) {
      return thumb.split(',')[0]
    },
    // 获取第一张图片
    handleThumbs(thumb) {
      return thumb.split(',')
    }
  }
}
</script>

<style scoped>

</style>
