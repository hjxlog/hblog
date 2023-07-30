## 简介

hblog是一个前后端分离的博客系统，基于Spring Boot 2 + Vue 3

## 后端

1. 核心框架：[Spring Boot](https://github.com/spring-projects/spring-boot)
2. 安全框架：[Spring Security](https://github.com/spring-projects/spring-security)
3. Token 认证：[jjwt](https://github.com/jwtk/jjwt)
4. 持久层框架：[MyBatis Plus](https://baomidou.com/)
5. 缓存：[Redis](https://github.com/redis/redis)
6. 离线 IP 地址库：[ip2region](https://github.com/lionsoul2014/ip2region)

## 前端

核心框架：Vue3.x、Vue Router、Vuex

UI部分：[Element Plus](https://element-plus.gitee.io/zh-CN/)，前台View、后台 CMS 完全基于Element Plus开发

## 快速开始

1. 创建 MySQL 数据库`hblog`，并运行`\hblog-api\docs\db\hblog.sql`初始化表数据
2. 修改配置信息
3. 安装 Redis 并启动
4. 启动后端SpringBoot两个服务（hblog-admin，hblog-serve）
5. 分别在`hblog-cms`和`hblog-view`目录下执行`npm install`安装依赖
6. 分别在`hblog-cms`和`hblog-view`目录下执行`npm run serve`启动前后台页面
