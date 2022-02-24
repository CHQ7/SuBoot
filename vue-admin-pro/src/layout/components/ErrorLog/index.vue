<template>
  <div v-if="logs.length>0">
    <el-badge :is-dot="true" style="line-height: 14px;margin-top: -3px;" @click.native="dialogTableVisible=true">
      <u-icon-svg name="bug" />
    </el-badge>

    <el-dialog :visible.sync="dialogTableVisible" width="80%" append-to-body>
      <div slot="title">
        <span style="padding-right: 10px;">错误日志</span>
        <el-button size="mini" type="primary" icon="el-icon-delete" @click="clearAll">全部清除</el-button>
      </div>
      <el-table :data="logs" border>
        <el-table-column label="消息">
          <template slot-scope="{row}">
            <div>
              <span class="message-title">提示:</span>
              <el-tag type="danger">
                {{ row.err.message }}
              </el-tag>
            </div>
            <br>
            <div>
              <span class="message-title">信息: </span>
              <el-tag type="warning">
                {{ row.vm.$vnode.tag }} error in {{ row.info }}
              </el-tag>
            </div>
            <br>
            <div>
              <span class="message-title">定位: </span>
              <el-tag type="success">
                {{ row.url }}
              </el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="栈">
          <template slot-scope="scope">
            {{ scope.row.err.stack }}
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
/**
 * 捕获全局异常错误信息
 */
export default {
  name: 'ErrorLog',
  props: {
    /** 是否显示提示框 */
    logs: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      dialogTableVisible: false
    }
  },
  methods: {
    clearAll() {
      this.dialogTableVisible = false
      this.$emit('clearAll')
    }
  }
}
</script>

<style scoped>
.message-title {
  font-size: 14px;
  padding-right: 8px;
}
</style>
