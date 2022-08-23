<div align="center">
    <br/>
    <h1>SuBoot</h1>

[![SpringBoot](https://img.shields.io/badge/SpringBoot-v2.6.x-blue)](https://spring.io/projects/spring-boot/)
[![License](https://img.shields.io/badge/License-MIT-grenn.svg)](LICENSE)
</div>

## 🚀 项目介绍

[SuBoot](https://github.com/JsckChin/SuBoot) 完全免费，且可商用，基于 [SpringBoot](https://spring.io/projects/spring-boot/) 的中后台解决方案，它使用了最新的前后端技术栈，并提炼了典型的业务模型，页面，包括二次封装组件、动态菜单、权限校验、粒子化权限控制等功能，它可以帮助你快速搭建企业级中后台项目， 相信不管是从新技术使用还是其他方面，都能帮助到你。
 
## 🚀开发工具
*   IntelliJ IDEA
*   Node 12.13.0 +
*   Maven 3.6.3 +
*   Git

## 🚀运行环境

*   JDK 11 + 或 OpenJDK 11 +
*   Redis 4.0.8 +
*   MySql 5.8

## 项目截图

![输入图片说明](doc/static/login.png)

![输入图片说明](doc/static/image.png)



## 项目使用
```
# 启动项目
mvn compile spring-boot:run

# 项目打包
mvn clean package

# 项目打包跳过测试环节
mvn clean package -Dmaven.test.skip=true
```

# 项目部署
```
# 直接运行
java -jar xxx.jar

# 若需要设置jvm内存大小等参数:
java -Xmx512m -Xms512m -jar xxx.jar

# 多环境加载问题
java -jar xxx.jar --spring.profiles.actvie=dev 

# Linux 后台运行
nohup  java -Xmx512m -Xms512m -jar xxx.jar >nohup.log&

```

## 如何贡献

非常欢迎你的加入！[提一个 Issue](https://github.com/JsckChin/SuBoot/issues) 或者提交一个 Pull Request。

**Pull Request:**

1. Fork 代码!
2. 创建自己的分支: `git checkout -b feat/xxxx`
3. 提交你的修改: `git commit -am 'feat(function): add xxxxx'`
4. 推送您的分支: `git push origin feat/xxxx`
5. 提交`pull request`

## Git 贡献提交规范

- 参考 [vue](https://github.com/vuejs/vue/blob/dev/.github/COMMIT_CONVENTION.md) 规范 ([Angular](https://github.com/conventional-changelog/conventional-changelog/tree/master/packages/conventional-changelog-angular))

    - `feat` 增加新功能
    - `fix` 修复问题/BUG
    - `style` 代码风格相关无影响运行结果的
    - `perf` 优化/性能提升
    - `refactor` 重构
    - `revert` 撤销修改
    - `test` 测试相关
    - `docs` 文档/注释
    - `chore` 依赖更新/脚手架配置修改等
    - `workflow` 工作流改进
    - `ci` 持续集成
    - `types` 类型定义文件更改
    - `wip` 开发中
 
