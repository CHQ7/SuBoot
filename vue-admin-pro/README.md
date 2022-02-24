### NsBootAdmin

<p align="center">
  <a href="https://github.com/vuejs/vue">
    <img src="https://img.shields.io/badge/vue-2.6.10-brightgreen.svg" alt="vue">
  </a>
  <a href="https://github.com/ElemeFE/element">
    <img src="https://img.shields.io/badge/element--ui-2.7.0-brightgreen.svg" alt="element-ui">
  </a>
  <a href="https://gitee.com/umb/NsBoot/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/mashape/apistatus.svg" alt="license">
  </a>
</p>


### 简介

后台管理前端解决方案，它基于 vue 和 element-ui 实现。使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，快速搭建企业级中后台产品原型。


### 开发

```bash

# 进入项目目录
cd NsBootAdmin

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run dev
```

浏览器访问 http://127.0.0.1:75

### 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```

### 其它

```bash
# 预览发布环境效果
npm run preview

# 预览发布环境效果 + 静态资源分析
npm run preview -- --report

# svg 压缩处理优化
npm run svgo

# 代码格式检查
npm run lint

# 代码格式检查并自动修复
npm run lint -- --fix
```




### Browsers support

现代浏览器和Internet Explorer 10+.

### License

[MIT] Copyright (c) 2020 JsckChin

### 项目依赖
技术 | 名称
----|------
axios | 基于Promise的HTTP客户端，用于浏览器和node.js
clipboard | 现代复制到剪贴板
codemirror | 功能齐全的浏览器内代码编辑器
core-js | JavaScript的模块化标准库
driver.js | 轻巧，无依赖的原始JavaScript库，可在页面上吸引用户的注意力
dropzone | 为您处理文件的拖放
echarts | Apache ECharts是用于浏览器的功能强大的交互式图表和数据可视化库
element-ui | Vue.js的组件库
file-saver | HTML5 saveAs() FileSaver实现
fuse.js | 轻量级模糊搜索
js-cookie | 轻巧的JavaScript API，用于处理Cookie
jsonlint | 验证JSON
jszip | 一个用于使用JavaScript创建，读取和编辑.zip文件的库，它具有可爱且简单的API
nprogress | 适用于Ajax'y应用程序的超薄进度条。受到Google，YouTube和Medium的启发
normalize.css | CSS重置的现代替代方法
path-to-regexp | 表达RegExp实用程序的样式路径
screenfull | 用于JavaScript全屏API的跨浏览器使用的简单包装，可让您将页面或任何元素置于全屏状态
script-loader | 用于webpack的脚本加载器模块
sortablejs | Sortable是一个JavaScript库，用于可重新排序的拖放列表
vue | 适用于现代Web界面的反应式，面向组件的视图层
vue-count-to | 这是一个Vue组件，它将在指定的持续时间内计入目标数量
vue-router | Vue.js 2的官方路由器
vue-splitpane | Vue分割窗格组件
vuedraggable | Vue的可拖动组件
vuex | Vue.js的状态管理
xlsx | 各种电子表格格式的解析器和编写器

### 开发依赖
技术 | 名称
----|------
@vue/cli-plugin-babel | Babel eslint vue-cli插件
@vue/cli-plugin-eslint | eslint vue-cli插件
@vue/cli-plugin-unit-jest | unit-jest vue-cli插件
@vue/cli-service | service vue-cli插件
@vue/test-utils | test vue-cli插件
autoprefixer | 解析CSS并使用“我可以使用”网站中的值向CSS规则添加供应商前缀
babel-eslint | 允许您使用出色的ESLint整理所有有效的Babel代码
babel-jest | Jest插件可使用babel进行转换
babel-plugin-dynamic-import-node | babel插件动态导入节点
chalk | 颜色的插件
chokidar | 最小和高效的跨平台文件监视库
connect | Connect是使用称为“中间件”的“插件”为节点提供的可扩展HTTP服务器框架
eslint | ESLint是用于识别和报告ECMAScript / JavaScript代码中的模式的工具
eslint-plugin-vue | Vue.js的官方ESLint插件
html-webpack-plugin |  简化HTML文件的创建，以服务于您的Webpack捆绑包
husky |  Git钩子变得容易
lint-staged | 用于实现每次提交只检查本次提交所修改的文件
mockjs | Mock.js是一个模拟数据生成器，可帮助前端开发和原型与后端进度分开，并减少某些单调性，尤其是在编写自动化测试时
runjs | 让您的markdown运行
sass | Sass的纯JavaScript实现。Sass再次使CSS变得有趣
sass-loader | 用于Webpack的Sass加载程序
script-ext-html-webpack-plugin | 通过脚本元素的异步和延迟属性增强了html-webpack-plugin功能
serve-static | 服务静态文件
svg-sprite-loader | Webpack加载器，用于创建SVG精灵
svgo | 基于Node.js的工具，用于优化SVG矢量图形文件
vue-template-compiler | Vue 2.0的模板编译器
