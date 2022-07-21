// --------------------------------------
// vue  配置
// 所有配置项解释都可以在 https://cli.vuejs.org/config/
// --------------------------------------
// 如果计划在子路径下部署站点，则需要设置publicPath，
// 例如GitHub页面。如果您计划将站点部署到https://foo.github.io/bar/，
// 那么publicPath应该设置为“/bar/”。
// 在大多数情况下，请使用“/”！！！
// 详细信息: https://cli.vuejs.org/config/#publicpath
// --------------------------------------
// JavaScript 严格模式
'use strict'

// 获取配置文件的网站名称、端口号
const { title, devPort } = require('./src/config/settings.js')
const resolve = dir => { return require('path').join(__dirname, dir) }

module.exports = {

  publicPath: '/',
  // 打包输出文件目录
  outputDir: 'dist',
  // 打包后生成的静态资源目录
  assetsDir: 'static',
  // 是否在保存的时候检查
  lintOnSave: process.env.NODE_ENV === 'development',
  // 生产环境的 source map
  productionSourceMap: false,
  // 反向代理(解决前后端跨域问题)
  devServer: {
    // 默认打开端口号
    port: devPort,
    // 自动打开浏览器
    open: true,
    // 设置让浏览器 overlay 同时显示错误
    overlay: {
      warnings: false,
      errors: true
    },
    // 跳过检查host
    disableHostCheck: true,
    // 代理配置
    proxy: {
      '/nt': {
        target: 'http://127.0.0.1:90',
        ws: true,
        changeOrigin: true
      },
      '/attachment': {
        target: 'http://127.0.0.1:90',
        ws: true,
        changeOrigin: true
      },
      '/druid': {
        target: 'http://127.0.0.1:90',
        ws: true,
        changeOrigin: true
      }
    }
  },
  configureWebpack: {
    // 在webpack的name字段中提供应用程序的标题，以便
    // 可以在index.html中访问它以插入正确的标题。
    name: title,
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  },
  chainWebpack(config) {
    // 它可以提高第一屏的速度，建议开启预加载
    config.plugin('preload').tap(() => [
      {
        rel: 'preload',
        // 忽略 runtime.js
        // https://github.com/vuejs/vue-cli/blob/dev/packages/@vue/cli-service/lib/config/app.js#L171
        fileBlacklist: [/\.map$/, /hot-update\.js$/, /runtime\..*\.js$/],
        include: 'initial'
      }
    ])

    // 当页面太多时，会导致太多无意义的请求
    config.plugins.delete('prefetch')

    // 设置svg加载程序
    config.module
      .rule('svg')
      .exclude.add(resolve('src/design/svg/icons'))
      .end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/design/svg/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()

    config
      .when(process.env.NODE_ENV !== 'development',
        config => {
          config
            .plugin('ScriptExtHtmlWebpackPlugin')
            .after('html')
            .use('script-ext-html-webpack-plugin', [{
            // `runtime` must same as runtimeChunk name. default is `runtime`
              inline: /runtime\..*\.js$/
            }])
            .end()
          config
            .optimization.splitChunks({
              chunks: 'all',
              cacheGroups: {
                libs: {
                  name: 'chunk-libs',
                  test: /[\\/]node_modules[\\/]/,
                  priority: 10,
                  chunks: 'initial' // 仅包最初依赖的第三方
                },
                elementUI: {
                  name: 'chunk-elementUI', // 将elementUI拆分为一个包
                  priority: 20, // 重量需要大于libs和app，否则将打包为libs或app
                  test: /[\\/]node_modules[\\/]_?element-ui(.*)/ // 为了适应cnpm
                },
                commons: {
                  name: 'chunk-commons',
                  test: resolve('src/components'), // 可以自定义规则
                  minChunks: 3, // 最小公共数
                  priority: 5,
                  reuseExistingChunk: true
                }
              }
            })
          // https:// webpack.js.org/configuration/optimization/#optimizationruntimechunk
          config.optimization.runtimeChunk('single')
        }
      )
  }
}
