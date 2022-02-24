首先将下载的 .svg 图标放入 src/assets/svg-icons/icons 文件夹下
注意：本项目中使用的图标都是 128*128 大小规格的
然后使用组件
<u-icon-svg name="刚才的svg文件名"/>

src/assets/icons/svg 文件夹中的图标会自动注册到项目中，文件全部打包成 svg-sprite，名称注册到 Vue.$IconSvg 属性中

所以如果你需要检查项目中已经注册的所有图标，可以通过如下方式

// 在 vue 单文件组件中
console.log(this.$IconSvg)