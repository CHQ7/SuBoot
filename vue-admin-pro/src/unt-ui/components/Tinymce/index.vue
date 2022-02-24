<template>
  <div :class="{fullscreen:fullscreen}" class="tinymce-container" :style="{width:containerWidth}">
    <textarea :id="tinymceId" class="tinymce-textarea" />
  </div>
</template>

<script>
/**
 * docs:
 * https://panjiachen.github.io/vue-element-admin-site/feature/component/rich-editor.html#tinymce
 * 为什么使用此cdn，详细信息请参阅 https://github.com/PanJiaChen/tinymce-all-in-one
 */
import plugins from './plugins'
import toolbar from './toolbar'
import load from './dynamicLoadScript'

const tinymceCDN = 'https://cdn.jsdelivr.net/npm/tinymce@5.4.1/tinymce.min.js'
const tinymcelangsCDN = 'https://cdn.jsdelivr.net/npm/tinymce-i18n@20.4.4/langs5/zh_CN.min.js'

/**
 * 富文本组件
 */
export default {
  name: 'Tinymce',
  props: {
    id: {
      type: String,
      default: function() {
        return 'vue-tinymce-' + +new Date() + ((Math.random() * 1000).toFixed(0) + '')
      }
    },
    value: {
      type: String,
      default: ''
    },
    toolbar: {
      type: Array,
      required: false,
      default() {
        return []
      }
    },
    menubar: {
      type: String,
      default: 'file edit insert view format'
    },
    height: {
      type: [Number, String],
      required: false,
      default: 360
    },
    width: {
      type: [Number, String],
      required: false,
      default: 'auto'
    }
  },
  data() {
    return {
      uploadUrl: this.domain + '/nt/upload/image',
      hasChange: false,
      hasInit: false,
      tinymceId: this.id,
      fullscreen: false,
      languageTypeList: {
        'zh': 'zh_CN',
        'en': 'en',
        'es': 'es_MX',
        'ja': 'ja'
      }
    }
  },
  computed: {
    containerWidth() {
      const width = this.width
      if (/^[\d]+(\.[\d]+)?$/.test(width)) { // matches `100`, `'100'`
        return `${width}px`
      }
      return width
    }
  },
  watch: {
    value(val) {
      if (!this.hasChange && this.hasInit) {
        this.$nextTick(() =>
          window.tinymce.get(this.tinymceId).setContent(val || ''))
      }
    }
  },
  mounted() {
    this.init()
  },
  activated() {
    if (window.tinymce) {
      this.initTinymce()
    }
  },
  deactivated() {
    this.destroyTinymce()
  },
  destroyed() {
    this.destroyTinymce()
  },
  methods: {
    // 加载编辑器
    init() {
      // dynamic load tinymce from cdn
      load(tinymceCDN, (err) => {
        if (err) {
          this.$message.error(err.message)
          return
        }
        this.initTinymce()
      })
    },
    // 初始化编辑器
    initTinymce() {
      const _this = this
      window.tinymce.init({
        selector: `#${this.tinymceId}`, // 容器的ID
        language_url: tinymcelangsCDN, // 如果语言包不存在，指定一个语言包路径
        language: this.languageTypeList['zh'], // 语言
        height: this.height, // 高度
        body_class: 'panel-body ',
        object_resizing: false, // 图片和表格是否开启在编辑器内部缩放
        toolbar: this.toolbar.length > 0 ? this.toolbar : toolbar, // 工具栏，参数类型是个数组
        menubar: this.menubar, // 菜单栏的配置，也是数组
        plugins: plugins, // 使用到的插件，参数类型为数组
        end_container_on_empty_block: true, // 如果在空的内部块元素中按enter键，则此选项允许您拆分当前容器块元素。
        branding: false, // 去水印
        paste_data_images: true, // 设置为“true”即允许粘贴图像，而将其设置为“false”则不允许粘贴图像。
        powerpaste_word_import: 'clean', // 此设置控制如何筛选从Microsoft Word粘贴的内容
        code_dialog_height: 450,
        code_dialog_width: 1000,
        advlist_bullet_styles: 'square', // 此选项允许您在默认bullist工具栏控件中包含特定的无序列表项标记 默认值：default,circle,disc,square
        advlist_number_styles: 'default',
        imagetools_cors_hosts: ['www.tinymce.com', 'codepen.io'], // 指定跨域地址
        default_link_target: '_blank', // 链接默认打开方式
        link_title: false, // 是否允许禁用链接对话框中的链接标题输入字段
        nonbreaking_force_tab: true, // 插入不间断空格需要不间断空格插件
        convert_urls: false,
        images_upload_handler(blobInfo, succFun, failFun) {
          var xhr, formData
          var file = blobInfo.blob()// 转化为易于理解的file对象
          xhr = new XMLHttpRequest()
          xhr.withCredentials = false
          xhr.open('POST', _this.uploadUrl)
          xhr.onload = function() {
            var json
            if (xhr.status !== 200) {
              failFun('HTTP Error: ' + xhr.status)
              return
            }
            json = JSON.parse(xhr.responseText)
            if (!json || typeof json.data !== 'string') {
              failFun('Invalid JSON: ' + xhr.responseText)
              return
            }
            succFun(json.data)
          }
          formData = new FormData()
          formData.append('file', file, file.name)// 此处与源文档不一样
          xhr.send(formData)
        },
        // 初始化编辑器实例时要执行的函数
        init_instance_callback: editor => {
          if (_this.value) {
            editor.setContent(_this.value)
          }
          _this.hasInit = true
          editor.on('NodeChange Change KeyUp SetContent', () => {
            this.hasChange = true
            this.$emit('input', editor.getContent())
          })
        },
        setup(editor) {
          editor.on('FullscreenStateChanged', (e) => {
            _this.fullscreen = e.state
          })
        }
      })
    },
    // 摧毁编辑器
    destroyTinymce() {
      const tinymce = window.tinymce.get(this.tinymceId)
      if (this.fullscreen) {
        tinymce.execCommand('mceFullScreen')
      }

      if (tinymce) {
        tinymce.destroy()
      }
    },
    // 设置富文本内容
    setContent(value) {
      window.tinymce.get(this.tinymceId).setContent(value)
    },
    // 获取富文本内容
    getContent() {
      window.tinymce.get(this.tinymceId).getContent()
    }
  }
}
</script>

<style >
.tinymce-container {
  position: relative;
  line-height: normal;
}
.tinymce-container>>>.mce-fullscreen {
  z-index: 10000;
}
.tinymce-textarea {
  visibility: hidden;
  z-index: -1;
}
.editor-custom-btn-container {
  position: absolute;
  right: 4px;
  top: 4px;
  /*z-index: 2005;*/
}
.fullscreen .editor-custom-btn-container {
  z-index: 10000;
  position: fixed;
}
.tox-tinymce-aux{z-index:99999 !important;}
.tinymce.ui.FloatPanel{z-Index: 99;}
.editor-upload-btn {
  display: inline-block;
}
</style>
