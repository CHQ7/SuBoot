const mixin = {
  data() {
    return {
      /** API接口 */
      api: {
        // 获取列表数据
        list: data => {},
        // 创建数据
        create: data => {},
        // 获取数据
        fetch: (id) => {},
        // 更新数据
        update: data => {},
        // 删除单个
        delete: (id) => {},
        // 删除集合
        del: data => {},
        // 更新状态
        disable: data => {}
      },
      // 加载数据加载状态
      listLoading: true,
      // 查询分页数据
      listQuery: {
        pageNumber: 1,
        pageSize: 10,
        totalCount: 1
      },
      // 打开弹窗状态
      dialogFormVisible: false,
      // 创建更新数据源
      dataForm: {},
      // 选中数据源
      selectData: [],
      // 开关设置查询不需要分页列表数据
      listState: false
    }
  },
  created() {
  },
  methods: {
    /**
     * 获取列表数据
     */
    hdlList() {
      const self = this
      // 打开加载状态
      self.listLoading = true
      if (self.listState) {
        // 查询列表数据
        self.api.list(self.listQuery).then(res => {
          // 获取列表数据
          self.list = res.data
          // 关闭加载状态
          setTimeout(() => {
            self.listLoading = false
          }, 0.5 * 1000)
        })
      } else {
        // 查询分页数据
        self.api.list(self.listQuery).then(res => {
          // 获取分页列表数据
          self.list = res.data.list
          // 获取分页数据,总页数
          self.listQuery.totalCount = res.data.totalCount
          // 关闭加载状态
          setTimeout(() => {
            self.listLoading = false
          }, 0.5 * 1000)
        })
      }
    },
    /**
     * 查询事件
     */
    hdlFilter() {
      this.listQuery.page = 1
      this.hdlList()
    },
    /**
     * 打开新增窗口事件
     * @param row
     */
    hdlOpen(row) {
      const self = this
      // 初始化数据源
      self.dataForm = Object.assign({}, row)
      // 打开新增状态
      self.dialogStatus = 'create'
      // 打开弹框状态
      self.dialogFormVisible = true
      // 清除表单验证状态
      self.$nextTick(() => {
        self.$refs['dialogForm'].clearValidate()
      })
    },
    // 编辑事件
    hdlEdit(row) {
      const self = this
      // 初始化数据源
      self.dataForm = Object.assign({}, row)
      // 打开编辑状态
      self.dialogStatus = 'update'
      // 打开弹框状态
      self.dialogFormVisible = true
      // 清除表单验证状态
      self.$nextTick(() => {
        self.$refs['dialogForm'].clearValidate()
      })
    },
    /**
     * 提交创建数据事件
     */
    hdlCreate() {
      const self = this
      // 效验数据格式
      this.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          // 提交创建数据
          self.api.create(self.dataForm).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.dialogFormVisible = false
            // 通知信息
            self.$u.msg('创建成功')
          })
        }
      })
    },
    /**
     * 提交更新数据事件
     */
    hdlUpdate() {
      const self = this
      // 效验数据格式
      self.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          // 提交更新数据
          self.api.update(self.dataForm).then(() => {
            // 刷新数据列表
            self.hdlList()
            // 关闭弹窗状态
            self.dialogFormVisible = false
            // 通知信息
            self.$u.msg('更新成功')
          })
        }
      })
    },
    /**
     * 多选事件
     * @param val
     */
    hdlSelectChange(val) {
      this.selectData = val
    },
    /**
     * 删除单个事件
     * @param id
     * @param title
     */
    hdlDelete(id, title) {
      const self = this
      // 模态对话框组件
      self.$confirm('此操作将' + title + '删除后将不能恢复，是否继续删除？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        // 处理确认事件
        self.api.delete(id).then(() => {
          // 刷新数据列表
          self.hdlList()
          // 通知信息
          self.$u.msg('删除成功')
        })
      }).catch(() => {
      })
    },
    /**
     *  多选删除事件
     * @returns {boolean}
     */
    hdlDel() {
      const self = this
      // 检查选中数据源
      if (self.selectData.length === 0) {
        self.$u.toast('请选择删除内容', 'error')
        return false
      }
      // 循环获取选择数据源ID
      const ids = []
      self.selectData.forEach((val) => {
        ids.push(val.id)
      })
      // 模态对话框组件
      self.$confirm('此操作将删除 ' + ids.length + '条消息,删除后将不能恢复，是否继续删除？', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        // 处理确认事件
        self.api.del({ 'ids': JSON.stringify(ids) }).then(() => {
          // 刷新数据列表
          self.hdlList()
          // 通知信息
          self.$u.msg('批量删除成功')
        })
      }).catch(() => {
      })
    },
    /**
     * 启用禁用状态事件
     * @param row
     * @param event
     * @param title
     * @param type
     * @param confirm
     * @param cancel
     */
    hdlDisable(row, event, title, type = '0', confirm = '启用', cancel = '禁用') {
      const self = this
      switch (type) {
        case '1':
          confirm = '显示'
          cancel = '隐藏'
          break
        case '2':
          confirm = '启用'
          cancel = '停止'
          break
        case '3':
          confirm = '上架'
          cancel = '下架'
          break
        default:
          confirm = '启用'
          cancel = '禁用'
      }

      // 提示文案
      const tips = event ? confirm : cancel
      // 模态对话框组件
      self.$confirm('是否' + tips + title, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        // 处理确认事件
        self.api.disable(row).then(() => {
          row.disabled = event
          self.$u.msg('状态更改成功')
        }).catch(() => {
          row.disabled = !event
        })
      }).catch(() => {
        row.disabled = !event
      })
    }
  }
}

export default mixin
