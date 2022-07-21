<template>
  <div class="app-container">

    <u-page title="钉钉配置">

      <el-card shadow="never" class="panel">
        <div class="panel-body">
          <div class="panel-header">
            <h4>同步钉钉组织到本地</h4>
            <span>开启后将同步钉钉组织数据至本地钉钉组织中</span>
          </div>
          <div>
            <el-button type="primary" icon="el-icon-refresh-left" @click="hdlSynchroDingtalkDept()">同步数据</el-button>
          </div>
        </div>
      </el-card>

      <el-card shadow="never" class="panel">
        <div class="panel-body">
          <div class="panel-header">
            <h4>同步钉钉角色到本地</h4>
            <span>开启后将同步钉钉角色数据至本地钉钉角色中</span>
          </div>
          <div>
            <el-button type="primary" icon="el-icon-refresh-left" @click="hdlSynchroDingtalkRole()">同步数据</el-button>
          </div>
        </div>
      </el-card>

      <el-card shadow="never" class="panel">
        <div class="panel-body">
          <div class="panel-header">
            <h4>同步钉钉用户到本地</h4>
            <span>开启后将同步钉钉用户数据至本地钉钉用户中</span>
          </div>
          <div>
            <el-button type="primary" icon="el-icon-refresh-left" @click="hdlSynchroDingtalkUser()">同步数据</el-button>
          </div>
        </div>
      </el-card>

      <el-card shadow="never" class="panel">
        <div class="panel-body">
          <div class="panel-header">
            <h4>同步钉钉组织到系统组织</h4>
            <span>开启后将同步本地钉钉组织数据至系统组织中</span>
          </div>
          <div>
            <el-button type="primary" icon="el-icon-refresh-left" @click="hdlSynchroLocalDept()">同步数据</el-button>
          </div>
        </div>
      </el-card>

      <el-card shadow="never" class="panel">
        <div class="panel-body">
          <div class="panel-header">
            <h4>同步钉钉用户到系统用户</h4>
            <span>开启后将同步本地关联钉钉角色的用户数据至系统用户中</span>
          </div>
          <div>
            <el-button type="primary" icon="el-icon-refresh-left" @click="hdlSynchroLocalSysUser()">同步数据</el-button>
          </div>
        </div>
      </el-card>

    </u-page>
  </div>
</template>
<script>

export default {
  data() {
    return {
      api: this.$u.api.DingtalkConfig
    }
  },
  methods: {
    // 同步钉钉组织
    hdlSynchroDingtalkDept() {
      const self = this
      self.$confirm('此操作将清空本地钉钉组织数据在同步钉钉角色数据是否继续，请谨慎操作？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchroDingtalkDept().then(res => {
          self.$alert('同步数据中，请等待几分钟后查看', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    },
    // 同步钉钉角色
    hdlSynchroDingtalkRole() {
      const self = this
      self.$confirm('此操作将清空本地钉钉角色组、角色数据在同步钉钉角色数据是否继续，请谨慎操作？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchroDingtalkRole().then(res => {
          self.$alert('同步数据中，请等待几分钟后查看', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    },
    // 同步钉钉用户
    hdlSynchroDingtalkUser() {
      const self = this
      self.$confirm('此操作将清空本地钉钉用户数据在同步钉钉用户数据是否继续，请谨慎操作？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchroDingtalkUser().then(res => {
          self.$alert('同步数据中，请等待几分钟后查看', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    },
    // 同步本地钉钉组织到系统组织数据
    hdlSynchroLocalDept() {
      const self = this
      self.$confirm('此操作将清空系统组织数据在同步本地钉钉组织数据是否继续，请谨慎操作？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchroLocalDept().then(res => {
          self.$alert('同步数据中，请等待几分钟后查看', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    },
    // 同步本地钉钉用户到系统用户数据
    hdlSynchroLocalSysUser() {
      const self = this
      self.$confirm('此操作将清空系统用户数据在同步本地钉钉用户数据是否继续，请谨慎操作？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        self.api.synchroLocalSysUser().then(res => {
          self.$alert('同步数据中，请等待几分钟后查看', '提示', {
            confirmButtonText: '知道了'
          })
        })
      }).catch(() => {
      })
    }
  }
}
</script>
<style lang="scss" scoped>

  .panel{
    margin-bottom: 20px;
    .panel-body{
      display:flex;
      justify-content:space-between;

      .panel-header{
        display: flex;
        flex-flow: column;
        color: #999;
        font-size: 12px;

        h4{
          font-size: 14px;
          color: #000;
        }
      }

      .panel-switch{
        margin-top: 20px;
      }
    }
  }

</style>
