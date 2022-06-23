// 任何你想使用的插件都必须导入
// 详细插件列表请参见 https://www.tinymce.com/docs/plugins/
// 自定义生成请参见 https://www.tinymce.com/download/custom-builds/
/**
 * advlist 高级列表插件,创建和定义UL和OL列表
 * anchor  锚点插件,插入锚点（也有叫书签的）
 * autolink 自动链接插件,自动创建超链接
 * autoresize 编辑器大小自适应,自动调整Tinymce大小以适应内容
 * autosave 自动存稿,定时自动将编辑内容保存到浏览器本地存储中（Local Storage）
 * bbcode 为TinyMCE添加基于BBCode的输入输出功能。
 * charmap 特殊字符插件,在tinymce中插入特殊字符
 * code 编辑源码,编辑内容生成的HTML源码
 * codesample 代码示例插件,提供代码高亮功能用于在内容区展示程序源代码
 * directionality 文字方向,设置文字从左到右或从右到左
 * emoticons 表情插件,在内容区插入unicode字符表情
 * fullpage 文档属性,编辑元数据和文档属性，如标题、关键字、描述、编码。
 * fullscreen 全屏,将编辑器铺满当前窗口大小。
 * help 帮助,打开使用帮助窗口
 * hr 水平分割线,插入一个水平分割线
 * image 插入编辑图片
 * importcss 引入css,将class加入“格式”下拉菜单中
 * insertdatetime 插入当前日期时间
 * legacyoutput 输出HTML4
 * link 超链接,插入或编辑超链接
 * lists 列表插件,设置有序列表和无序列表
 * media 插入编辑媒体,插入H5的audio和video标签
 * nonbreaking 插入不间断空格
 * noneditable 不可编辑元素
 * pagebreak 插入分页符
 * paste 粘贴插件,用于从Word复制和粘贴内容的标准版功能。
 * preview  预览,在弹出的拟态窗口中预览当前编辑区的内容
 * print 打印,打印当前编辑区的内容
 * quickbars 快速工具栏,在光标当前位置出现的快速工具栏
 * save 保存,在工具栏添加一个保存提交按钮
 * searchreplace 查找替换,查找替换编辑区的内容
 * spellchecker 拼写检查,启用TinyMCE的拼写检查功能
 * tabfocus tab切入切出,按tab切入切出TinyMCE
 * table 表格插件.,提供插入及编辑表格的能力
 * template 内容模板
 * textcolor 文字颜色
 * textpattern 快速排版（类似markdown）,匹配文本中的特殊文本标记，并对其执行格式化或执行编辑器命令
 * toc 目录生成器,根据当前内容生成目录
 * visualblocks 显示块元素范围,汉化包中翻译为显示区块边框
 * visualchars 显示不可见字符
 * wordcount 字数统计,统计当前内容区文字个数
 * @type {string[]}
 */
const plugins = ['advlist charmap anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace tabfocus table template textcolor textpattern visualblocks visualchars wordcount']

export default plugins
