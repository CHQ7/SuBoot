/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : suboot

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 27/11/2022 19:07:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ims_member
-- ----------------------------
DROP TABLE IF EXISTS `ims_member`;
CREATE TABLE `ims_member`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'openid',
  `sessionKey` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会话密钥',
  `nickname` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` tinyint NULL DEFAULT 0 COMMENT '性别(0-未知 1-男 2-女)',
  `age` int NULL DEFAULT 0 COMMENT '年龄',
  `birthday` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生日',
  `salt` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密盐',
  `password` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `realName` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实名',
  `idCard` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '证件号码',
  `certification` tinyint(1) NULL DEFAULT NULL COMMENT '是否实名认证',
  `credit1` int NULL DEFAULT 0 COMMENT '信贷  以分为单位',
  `credit2` int NULL DEFAULT 0 COMMENT '信贷  以分为单位',
  `credit3` int NULL DEFAULT 0 COMMENT '信贷  以分为单位',
  `credit4` int NULL DEFAULT 0 COMMENT '信贷  以分为单位',
  `nationality` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国籍',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `area` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '县/区',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `zipcode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  `loginAt` bigint NULL DEFAULT NULL COMMENT '上次登陆时间',
  `loginIp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上次登陆IP',
  `loginCount` int NULL DEFAULT NULL COMMENT '登陆次数',
  `note` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_member
-- ----------------------------
INSERT INTO `ims_member` VALUES ('7ad2d66933c740e6bd11ebcd1aa9b639', 'oreOw5IqJ32lYBkMs6iR4hjkx9yU', '+76kH9DUuG7nSt82xn0U0g==', '微信用户', '13320986077', NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', 1, 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, 0, 0, 0, NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 1667872308642, '', '', 1669259411267, 0);
INSERT INTO `ims_member` VALUES ('f08dbb1b5ca04f3a90f43e5772d5d291', 'oreOw5J8uSa3KLShTE9Vjc_AAaCg', 'xqt+O8uWn/MrGcuWVUWmtw==', '微信用户', '13880563562', NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', 0, 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, 0, 0, 0, NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', 1668073124351, '', '', 1669533079032, 0);

-- ----------------------------
-- Table structure for ims_member_label
-- ----------------------------
DROP TABLE IF EXISTS `ims_member_label`;
CREATE TABLE `ims_member_label`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签名称',
  `note` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签备注',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_member_label
-- ----------------------------
INSERT INTO `ims_member_label` VALUES ('026438b1033e4a6fa7bb1cb867acd561', '高等消费', '高等消费', '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441993785, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441993785, 0);
INSERT INTO `ims_member_label` VALUES ('2571e4d6ab644232a41474d4170a8ce9', '低等消费', '低等消费', '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667442006227, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667442006227, 0);
INSERT INTO `ims_member_label` VALUES ('6e7bf4e443c84563b855f7b0ce348aaa', '中等消费', '中等消费', '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441999588, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441999588, 0);

-- ----------------------------
-- Table structure for ims_member_level
-- ----------------------------
DROP TABLE IF EXISTS `ims_member_level`;
CREATE TABLE `ims_member_level`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `level` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级权重,数字越大，等级权重越高，等级权重不可重复',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级名字',
  `discount` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '折扣:可享折扣权益',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_member_level
-- ----------------------------
INSERT INTO `ims_member_level` VALUES ('571087747c054bf7994291c09b1d4a15', '3', '钻石会员', NULL, 1, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441946329, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441946329, 0);
INSERT INTO `ims_member_level` VALUES ('66785384822f4b76ac4c5f2e217597e0', '0', '大众会员', NULL, 1, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441924361, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441924361, 0);
INSERT INTO `ims_member_level` VALUES ('693a539f1aa14418b12f6aad2ede75c5', '1', '黄金会员', NULL, 1, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441931563, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441931563, 0);
INSERT INTO `ims_member_level` VALUES ('a547aa6251b749d5b85655a92363f880', '2', '铂金会员', NULL, 1, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441939055, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441939055, 0);

-- ----------------------------
-- Table structure for ims_platform_adv
-- ----------------------------
DROP TABLE IF EXISTS `ims_platform_adv`;
CREATE TABLE `ims_platform_adv`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '幻灯片标题',
  `thumb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '幻灯片图片',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接地址',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态,1显示 0隐藏',
  `location` int NULL DEFAULT 0 COMMENT '排序字段',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_platform_adv
-- ----------------------------
INSERT INTO `ims_platform_adv` VALUES ('4512f60e99334f569a91d24b00309f83', '2', '/attachment/image/20221102/o1nuuhb20aj7lq20cgberq1gll.jpg', NULL, 1, 0, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358455731, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358455731, 0);
INSERT INTO `ims_platform_adv` VALUES ('6240bfefdada4f4ca2b0bcf22d5719b3', '3', '/attachment/image/20221102/vu3tmr0fn2h5vohitvosj2ae8n.jpg', NULL, 1, 0, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358461476, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358461477, 0);
INSERT INTO `ims_platform_adv` VALUES ('d311137b5bc8463b9fdce45f851b3624', '1', '/attachment/image/20221102/r258gscq6mgjpr9sder36tj780.jpg', NULL, 1, 0, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358450134, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358450134, 0);

-- ----------------------------
-- Table structure for ims_platform_nav
-- ----------------------------
DROP TABLE IF EXISTS `ims_platform_nav`;
CREATE TABLE `ims_platform_nav`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航标题',
  `thumb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航图片',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接地址',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态,1显示 0隐藏',
  `location` int NULL DEFAULT 0 COMMENT '排序字段',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_platform_nav
-- ----------------------------
INSERT INTO `ims_platform_nav` VALUES ('06a069a8e72c466abbc8a9257c7b43de', '新房', '/attachment/image/20221108/t6ucpvrmeaheto5jk1dm2k14d8.png', 'pages/mls/loupan', 1, 9, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358555418, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876764183, 0);
INSERT INTO `ims_platform_nav` VALUES ('1bd8d27989c2492290bdd0da9109bdf8', '金融', '/attachment/image/20221108/roinlmkvlqgvfpcj487afvf98s.png', 'pages/mls/finance', 1, 4, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358602647, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667877095378, 0);
INSERT INTO `ims_platform_nav` VALUES ('1bf39a3d86e64748b2f7e69d30ad2ff3', '公寓', '/attachment/image/20221108/7nn461m74uinlo8cbtrq35cah4.png', 'pages/mls/apartment', 1, 7, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358573935, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876934251, 0);
INSERT INTO `ims_platform_nav` VALUES ('bf1a8b36603f40ecbef8ec615ac1db43', '预约', '/attachment/image/20221108/1fh9i7ker2ibqqe5g55unl00ji.png', 'pages/mls/order', 1, 5, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358591238, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667877084611, 0);
INSERT INTO `ims_platform_nav` VALUES ('d21aee133fbe448faf7124d32fc52cad', '二手房', '/attachment/image/20221108/697f6anm86hp2quleu18q9cb0o.png', 'pages/mls/ershoufang', 1, 10, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358548291, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876294874, 0);
INSERT INTO `ims_platform_nav` VALUES ('ee1604b9a8b24b848d03716d31e6e7ad', '商铺', '/attachment/image/20221108/4etvt2m6cojenqpr6s1qqmehpk.png', 'pages/mls/shang', 1, 6, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358582848, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876943203, 0);
INSERT INTO `ims_platform_nav` VALUES ('f56353df157f4d0ba74afd55e57a4374', '租房', '/attachment/image/20221108/374v4b7bskhbbo3q3rh86f851h.png', 'pages/mls/chuzu', 1, 8, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358563099, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876838307, 0);

-- ----------------------------
-- Table structure for ims_sys_auth_log
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_auth_log`;
CREATE TABLE `ims_sys_auth_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作人',
  `tag` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作模块',
  `msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '业务类型',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求地址',
  `method` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `browser` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `os` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求结果',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行结果',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_auth_log
-- ----------------------------
INSERT INTO `ims_sys_auth_log` VALUES ('0119a9b96c7a43adabba640368682a2b', '管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '118.112.114.21', '四川省成都市 电信', 'MicroMessenger_8.0.30', 'iPhone_16_1_1', NULL, NULL, 'b4d92f00784c493bb23f88e55b88a448', '管理员', 1669533252916, 'b4d92f00784c493bb23f88e55b88a448', '管理员', 1669533252916, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('10f7e87ce57149d7ac0fb33eb3e534ac', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871690912, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871690912, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('5b647d35219e4c4085b413d044e51371', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441901329, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441901329, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('5b680cc47edf4a4e901862aa86a2699d', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '112.193.219.41', '四川省成都市 联通', 'Chrome_107.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530410172, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530410172, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('5fb97bd4e5ea48a884354a9b9fb8612f', '超级管理员', '用户登出', '退出系统', '/nt/auth/logout', 'POST', '112.193.219.41', '四川省成都市 联通', 'Chrome_107.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530578019, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530578019, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('781a3a4bf2504fe68d39e27aa25e493b', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358423979, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358423979, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('b18679afefb240f2b01535f40578f1a7', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '127.0.0.1', ' 本机地址', 'Chrome_107.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669546291253, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669546291253, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('b53ad068e97745cebdbabfc95930183e', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327496079, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327496079, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('dfd7c4439d3940b08cb8ae64269afc09', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '127.0.0.1', ' 本机地址', 'Chrome_107.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545131426, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545131426, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('ed4ff79f342f419186ad9f4d701ca674', '超级管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '112.193.67.237', '四川省成都市 联通', 'Chrome_107.0.0.0', 'Windows_10.0', NULL, NULL, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1668670112447, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1668670112447, 0);
INSERT INTO `ims_sys_auth_log` VALUES ('fefe36e495c34278bff0e30e38f4fa28', '管理员', '用户登录', '账号登录', '/nt/auth/login', 'POST', '112.193.219.41', '四川省成都市 联通', 'Chrome_107.0.0.0', 'Windows_10.0', NULL, NULL, 'b4d92f00784c493bb23f88e55b88a448', '管理员', 1669530583980, 'b4d92f00784c493bb23f88e55b88a448', '管理员', 1669530583980, 0);

-- ----------------------------
-- Table structure for ims_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_config`;
CREATE TABLE `ims_sys_config`  (
  `configKey` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '参数key',
  `configValue` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数的值',
  `configName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`configKey`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_config
-- ----------------------------
INSERT INTO `ims_sys_config` VALUES ('AppDomain', 'http://127.0.0.1', '系统域名', NULL, '', '', 1640663994308, '', '', 1640663994308, NULL);
INSERT INTO `ims_sys_config` VALUES ('AppName', 'SnBoot', '系统名称', NULL, '', '', 1640664089763, '', '', 1640664089763, NULL);
INSERT INTO `ims_sys_config` VALUES ('AppUploadPath', '/attachment', '本地上传路径前缀', NULL, '', '', 1640664169790, '', '', 1640664169790, NULL);

-- ----------------------------
-- Table structure for ims_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_dept`;
CREATE TABLE `ims_sys_dept`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `parentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父级ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组织名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '唯一编码',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `location` int NULL DEFAULT NULL COMMENT '排序字段',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_dept
-- ----------------------------
INSERT INTO `ims_sys_dept` VALUES ('aeef6ef34bb34f7baada18366848b705', '', '总部', 'SUPER_DEPT', '总部', NULL, '', '', 1665327335355, '', '', 1665327335355, 0);

-- ----------------------------
-- Table structure for ims_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_log`;
CREATE TABLE `ims_sys_log`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `tag` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '日志标签',
  `msg` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '业务类型',
  `url` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `method` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `src` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '执行类',
  `ip` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `browser` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `os` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `param` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '请求参数',
  `result` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '执行结果',
  `status` int NULL DEFAULT 0 COMMENT '操作状态 （0正常 1失败）',
  `executeTime` bigint NULL DEFAULT 0 COMMENT '处理耗时',
  `createdById` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_log
-- ----------------------------
INSERT INTO `ims_sys_log` VALUES ('04bc3a26ba0e41e39c7f9c6906828577', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"f56353df157f4d0ba74afd55e57a4374\",\"title\":\"租房\",\"thumb\":\"/attachment/image/20221108/374v4b7bskhbbo3q3rh86f851h.png\",\"url\":\"pages/mls/chuzu\",\"status\":true,\"location\":8,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358563099,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667876838307,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667876838314\n}', 0, 75, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876838314, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876838314, NULL);
INSERT INTO `ims_sys_log` VALUES ('04e6eb1c915a4366bcd1f9df6579ca28', '用户标签', '新增', '/nt/member/label/create', 'POST', 'com.yunqi.member.controller.MemberLabelController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"026438b1033e4a6fa7bb1cb867acd561\",\"name\":\"高等消费\",\"note\":\"高等消费\",\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667441993785,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667441993785,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667441993796\n}', 0, 86, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441993796, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441993796, NULL);
INSERT INTO `ims_sys_log` VALUES ('0e47099fb6034c0285f47e5f21305c7e', '系统菜单', '新增', '/nt/sys/menu/batch', 'POST', 'com.yunqi.system.controller.SysMenuController#batch', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"parentId\":\"bff555bc8cb541f3b99259f37d223407\",\"permissionData\":[{\"value\":\"platform.adv.create\",\"label\":\"新增幻灯片\"}, {\"value\":\"platform.adv.update\",\"label\":\"修改幻灯片\"}, {\"value\":\"platform.adv.delete\",\"label\":\"删除幻灯片\"}]}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327637640\n}', 0, 98, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637640, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637640, NULL);
INSERT INTO `ims_sys_log` VALUES ('0e8a3706ef324f15903f74f796bb91a4', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d21aee133fbe448faf7124d32fc52cad\",\"title\":\"二手房\",\"thumb\":\"/attachment/image/20221102/5lgj97ter4jh5r5r6ah53celli.png\",\"status\":true,\"location\":10,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358548291,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667366863589,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667366863595\n}', 0, 74, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366863595, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366863595, NULL);
INSERT INTO `ims_sys_log` VALUES ('0fcc81a07b7c43669ce828292ef3ae45', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"af4c415540844cb08d4e7c01d43f6b71\",\"parentId\":\"06d3853a1e9343728d1d29753ee972ad\",\"name\":\"订单管理\",\"type\":\"menu\",\"router\":\"list\",\"path\":\"views/order/list\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"order.list\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328344504,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328344504,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328344509\n}', 0, 80, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328344509, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328344509, NULL);
INSERT INTO `ims_sys_log` VALUES ('14850113ac8b4d9daadc0df27cb3af0f', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"ee1604b9a8b24b848d03716d31e6e7ad\",\"title\":\"商铺\",\"thumb\":\"/attachment/image/20221108/4etvt2m6cojenqpr6s1qqmehpk.png\",\"url\":\"pages/mls/shang\",\"status\":true,\"location\":6,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358582848,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667876943203,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667876943208\n}', 0, 84, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876943208, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876943208, NULL);
INSERT INTO `ims_sys_log` VALUES ('15b1ade9e1d844cc8894a56377253790', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bd8d27989c2492290bdd0da9109bdf8\",\"title\":\"金融\",\"thumb\":\"/attachment/image/20221108/roinlmkvlqgvfpcj487afvf98s.png\",\"url\":\"pages/mls/finance\",\"status\":true,\"location\":4,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358602647,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667877095378,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667877095383\n}', 0, 87, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667877095383, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667877095383, NULL);
INSERT INTO `ims_sys_log` VALUES ('16440bb62a094156a3bf0400d41ce597', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"f56353df157f4d0ba74afd55e57a4374\",\"title\":\"租房\",\"thumb\":\"/attachment/image/20221108/374v4b7bskhbbo3q3rh86f851h.png\",\"status\":true,\"location\":8,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358563099,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667871886213,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667871886220\n}', 0, 83, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871886221, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871886221, NULL);
INSERT INTO `ims_sys_log` VALUES ('16961df34f1e413cad3063d5679e12ca', '系统菜单', '新增', '/nt/sys/menu/batch', 'POST', 'com.yunqi.system.controller.SysMenuController#batch', '127.0.0.1', ' 本机地址', 'Chrome_107.0.0.0', 'Windows_10.0', '{\"parentId\":\"34ae2583fbe44961aeb1514bd8d03d09\",\"permissionData\":[{\"value\":\"member.list.update\",\"label\":\"修改用户\"}, {\"value\":\"member.list.delete\",\"label\":\"删除用户\"}]}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1669545443296\n}', 0, 102, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545443298, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545443298, NULL);
INSERT INTO `ims_sys_log` VALUES ('1a3e28da50cd405ea5ce966c6c3511e5', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"f56353df157f4d0ba74afd55e57a4374\",\"title\":\"租房\",\"status\":true,\"location\":8,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358563099,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358608956,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358608962\n}', 0, 76, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358608962, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358608962, NULL);
INSERT INTO `ims_sys_log` VALUES ('1e0f2b30c8004d54a4ef317755487c32', '用户标签', '新增', '/nt/member/label/create', 'POST', 'com.yunqi.member.controller.MemberLabelController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"6e7bf4e443c84563b855f7b0ce348aaa\",\"name\":\"中等消费\",\"note\":\"中等消费\",\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667441999588,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667441999588,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667441999595\n}', 0, 82, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441999595, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441999595, NULL);
INSERT INTO `ims_sys_log` VALUES ('1f8835c21e624a9e907c4cfeaa59b968', '系统角色', '新增', '/nt/sys/role/create', 'POST', 'com.yunqi.system.controller.SysRoleController#create', '112.193.219.41', '四川省成都市 联通', 'Chrome_107.0.0.0', 'Windows_10.0', '{\"id\":\"292d891323dc4a26b98880f246ae8f55\",\"name\":\"管理员\",\"code\":\"ADMIN\",\"disabled\":true,\"note\":\"管理员权限\",\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1669530459829,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1669530459829,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1669530459838\n}', 0, 101, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530459842, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530459842, NULL);
INSERT INTO `ims_sys_log` VALUES ('22153622ca1e4c409137a73d2f3b8567', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"b477df402a1d431ab33c8ef71c9476cc\",\"parentId\":\"\",\"name\":\"用户\",\"type\":\"menu\",\"router\":\"member\",\"path\":\"Layout\",\"keepAlive\":false,\"target\":\"data-pjax\",\"icon\":\"member\",\"disabled\":true,\"permission\":\"member\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327753793,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327753793,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327753797\n}', 0, 85, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327753797, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327753797, NULL);
INSERT INTO `ims_sys_log` VALUES ('274bb06215a54626862a127685e584bd', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"34b4edb7e1ea4a578d9d9f78d74e64ea\",\"parentId\":\"55dd43dc1ef44890a3a297c186a7f22f\",\"name\":\"导航管理\",\"type\":\"menu\",\"router\":\"nav\",\"path\":\"views/platform/nav\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"platform.nav\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327669274,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327669274,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327669278\n}', 0, 87, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327669278, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327669278, NULL);
INSERT INTO `ims_sys_log` VALUES ('27d52b1c98d34faa94f50c15049b0d51', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,c50eb082594d4f8face94514de66dece,4c4e1778aa1341a883bf9b0b467aff5a,8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,06d3853a1e9343728d1d29753ee972ad,af4c415540844cb08d4e7c01d43f6b71,d8ebbc55dbcf467dab3f4afceb055c34,066c0b42655b4a3eb06404dc9a5cc6a8,d45b5d05165e4e7e9d5b37d24da0b398,b29a71d09c60475c8e8623873f6d9f3e,5f04a86630024fb99f6a9744e87ea8fc,f36e9d3cfbec4eb08e6e15a2fae6d63a,7bc3bed2f327465284818142adfcc376,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328680557\n}', 0, 83, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328680557, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328680557, NULL);
INSERT INTO `ims_sys_log` VALUES ('294c6580cfea4564965ea592c9dfc1c4', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"ce9c18d4f9af43e98cb698673fb391d0\",\"parentId\":\"cdbf51e9ace946a39f703717e4456cea\",\"name\":\"批量添加权限\",\"type\":\"data\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"sys.menu.batch\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327539072,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327539072,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327539079\n}', 0, 106, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327539081, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327539081, NULL);
INSERT INTO `ims_sys_log` VALUES ('2c42743325e4404da1a5deefa22ac418', '平台幻灯片', '新增', '/nt/platform/adv/create', 'POST', 'com.yunqi.platform.controller.PlatformAdvController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"6240bfefdada4f4ca2b0bcf22d5719b3\",\"title\":\"3\",\"thumb\":\"/attachment/image/20221102/vu3tmr0fn2h5vohitvosj2ae8n.jpg\",\"status\":true,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358461476,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358461477,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358461483\n}', 0, 86, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358461483, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358461483, NULL);
INSERT INTO `ims_sys_log` VALUES ('2e37527602b84827beeeab9befed4e27', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328254222\n}', 0, 91, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328254223, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328254223, NULL);
INSERT INTO `ims_sys_log` VALUES ('35d2e4514e0046b1a1eba12199aa243e', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"bff555bc8cb541f3b99259f37d223407\",\"parentId\":\"55dd43dc1ef44890a3a297c186a7f22f\",\"name\":\"幻灯片管理\",\"type\":\"menu\",\"router\":\"adv\",\"path\":\"views/platform/adv\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"platform.adv\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327611093,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327611093,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327611100\n}', 0, 81, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327611100, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327611100, NULL);
INSERT INTO `ims_sys_log` VALUES ('35e945019d6e44c8932a79fffe43f7b5', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"06d3853a1e9343728d1d29753ee972ad\",\"parentId\":\"\",\"name\":\"订单\",\"type\":\"menu\",\"router\":\"order\",\"path\":\"Layout\",\"keepAlive\":false,\"target\":\"data-pjax\",\"icon\":\"order\",\"disabled\":true,\"permission\":\"order\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328322615,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328322615,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328322620\n}', 0, 96, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328322620, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328322620, NULL);
INSERT INTO `ims_sys_log` VALUES ('3847630ac2d24424a7ee3a67c3861b5e', '系统菜单', '删除', '/nt/sys/menu/delete/c50eb082594d4f8face94514de66dece', 'POST', 'com.yunqi.system.controller.SysMenuController#delete', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '\"c50eb082594d4f8face94514de66dece\"', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667383436739\n}', 0, 73, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383436740, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383436740, NULL);
INSERT INTO `ims_sys_log` VALUES ('3852ba8c2e724297a707cafd92201e2d', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"64691d716695497785087b8764e5536a\",\"parentId\":\"55dd43dc1ef44890a3a297c186a7f22f\",\"name\":\"意见反馈\",\"type\":\"menu\",\"router\":\"feedback\",\"path\":\"views/platform/feedback\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"platform.feedback\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327719025,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327719025,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327719029\n}', 0, 92, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327719029, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327719029, NULL);
INSERT INTO `ims_sys_log` VALUES ('395f0c0bf20c46c2accbe145b5e159ea', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"7bc3bed2f327465284818142adfcc376\",\"parentId\":\"b29a71d09c60475c8e8623873f6d9f3e\",\"name\":\"积分明细\",\"type\":\"menu\",\"router\":\"points\",\"path\":\"views/finance/points\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"finance.points\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328558289,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328558289,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328558297\n}', 0, 85, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328558297, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328558297, NULL);
INSERT INTO `ims_sys_log` VALUES ('39bd5c30bb2e41d2a16b1d966415f6f9', '系统菜单', '新增', '/nt/sys/menu/batch', 'POST', 'com.yunqi.system.controller.SysMenuController#batch', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"parentId\":\"c322c9a13c1847a99c2d60db437a5cff\",\"permissionData\":[{\"value\":\"member.level.create\",\"label\":\"新增等级\"}, {\"value\":\"member.level.update\",\"label\":\"修改等级\"}, {\"value\":\"member.level.delete\",\"label\":\"删除等级\"}]}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667383478701\n}', 0, 83, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478701, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478701, NULL);
INSERT INTO `ims_sys_log` VALUES ('3b8e9bd2504f4c3fb34778ffd8c38f2e', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"ee1604b9a8b24b848d03716d31e6e7ad\",\"title\":\"商铺\",\"thumb\":\"/attachment/image/20221102/q3plsfljtui95rrfguto8k0cha.png\",\"status\":true,\"location\":6,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358582848,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667366898113,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667366898117\n}', 0, 87, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366898118, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366898118, NULL);
INSERT INTO `ims_sys_log` VALUES ('403f750bb71f4538b59234c73f422608', '用户等级', '新增', '/nt/member/level/create', 'POST', 'com.yunqi.member.controller.MemberLevelController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"a547aa6251b749d5b85655a92363f880\",\"level\":\"2\",\"name\":\"铂金会员\",\"status\":true,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667441939055,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667441939055,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667441939065\n}', 0, 85, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441939065, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441939065, NULL);
INSERT INTO `ims_sys_log` VALUES ('4041668c1fdf4218b6a5195ffdcb3086', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d3596ec42306475bac563ef49563bbd3\",\"parentId\":\"8ebabe59d484416f8cf3524f75e2d849\",\"name\":\"商品管理\",\"type\":\"menu\",\"router\":\"list\",\"path\":\"views/goods/list/index\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"goods.list\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328227935,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328227935,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328227941\n}', 0, 82, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328227941, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328227941, NULL);
INSERT INTO `ims_sys_log` VALUES ('40c65b0a7c5447acac91cf2010298dc4', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"bf1a8b36603f40ecbef8ec615ac1db43\",\"title\":\"预约\",\"thumb\":\"/attachment/image/20221108/1fh9i7ker2ibqqe5g55unl00ji.png\",\"url\":\"pages/mls/order\",\"status\":true,\"location\":5,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358591238,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667877084611,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667877084616\n}', 0, 81, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667877084616, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667877084616, NULL);
INSERT INTO `ims_sys_log` VALUES ('420e60f348c049b3b6eed41ad9d09aeb', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327829820\n}', 0, 85, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327829820, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327829820, NULL);
INSERT INTO `ims_sys_log` VALUES ('42596eb3215542beb90e0236d67ab311', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_107.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,4fb692712f92438e80af384ecfc8c5e7,23c4f25255e0490c9671e212aea9939b,c322c9a13c1847a99c2d60db437a5cff,03f39f264de54896a0c3312011759880,dcf39cd6a6474b8a8b4dc4f1bd0683bf,f0a68e2ef4bf41f7be1ef8ef36214f58,4c4e1778aa1341a883bf9b0b467aff5a,4b115ff4eba5408cac440cc491ec2817,41e4e6aa11d64ee3b9dd5418302afe19,d22a59f338194145b210d6570e728b9b,8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,06d3853a1e9343728d1d29753ee972ad,af4c415540844cb08d4e7c01d43f6b71,d8ebbc55dbcf467dab3f4afceb055c34,066c0b42655b4a3eb06404dc9a5cc6a8,d45b5d05165e4e7e9d5b37d24da0b398,b29a71d09c60475c8e8623873f6d9f3e,5f04a86630024fb99f6a9744e87ea8fc,f36e9d3cfbec4eb08e6e15a2fae6d63a,7bc3bed2f327465284818142adfcc376,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1669545453518\n}', 0, 92, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545453519, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545453519, NULL);
INSERT INTO `ims_sys_log` VALUES ('44c80efe52134c54bc24d5c2a1d5266c', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,06d3853a1e9343728d1d29753ee972ad,af4c415540844cb08d4e7c01d43f6b71,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328364468\n}', 0, 88, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328364468, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328364468, NULL);
INSERT INTO `ims_sys_log` VALUES ('49ee2735081f483cafa0af3e9c6b603f', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327648305\n}', 0, 76, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327648305, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327648305, NULL);
INSERT INTO `ims_sys_log` VALUES ('4b39a768d3974328b22af91b5ccd810e', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"8ebabe59d484416f8cf3524f75e2d849\",\"parentId\":\"\",\"name\":\"商品\",\"type\":\"menu\",\"router\":\"goods\",\"path\":\"Layout\",\"keepAlive\":false,\"target\":\"data-pjax\",\"icon\":\"goods\",\"disabled\":true,\"permission\":\"goods\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328177817,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328177817,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328177823\n}', 0, 83, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328177823, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328177823, NULL);
INSERT INTO `ims_sys_log` VALUES ('546ad9285d71406281eafe40e8450eb4', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"5f04a86630024fb99f6a9744e87ea8fc\",\"parentId\":\"b29a71d09c60475c8e8623873f6d9f3e\",\"name\":\"充值记录\",\"type\":\"menu\",\"router\":\"recharge\",\"path\":\"views/finance/log/recharge\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"finance.recharge\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328504933,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328504933,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328504938\n}', 0, 84, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328504938, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328504938, NULL);
INSERT INTO `ims_sys_log` VALUES ('56ada422242a455cb1a180d480b5dae2', '用户等级', '新增', '/nt/member/level/create', 'POST', 'com.yunqi.member.controller.MemberLevelController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"571087747c054bf7994291c09b1d4a15\",\"level\":\"3\",\"name\":\"钻石会员\",\"status\":true,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667441946329,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667441946329,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667441946337\n}', 0, 77, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441946338, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441946338, NULL);
INSERT INTO `ims_sys_log` VALUES ('585c5bc0b1094745a2b55223c57090bc', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"ee1604b9a8b24b848d03716d31e6e7ad\",\"title\":\"商铺\",\"thumb\":\"/attachment/image/20221108/4etvt2m6cojenqpr6s1qqmehpk.png\",\"status\":true,\"location\":6,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358582848,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667871861208,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667871861224\n}', 0, 107, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871861229, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871861229, NULL);
INSERT INTO `ims_sys_log` VALUES ('5996a88b602244488d83f623ecad5b01', '平台导航', '新增', '/nt/platform/nav/create', 'POST', 'com.yunqi.platform.controller.PlatformNavController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"bf1a8b36603f40ecbef8ec615ac1db43\",\"title\":\"预约\",\"status\":true,\"location\":4,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358591238,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358591238,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358591244\n}', 0, 84, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358591245, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358591245, NULL);
INSERT INTO `ims_sys_log` VALUES ('5c115bf9b61642d0a31504653a5b0100', '用户管理', '删除', '/nt/member/delete/f08dbb1b5ca04f3a90f43e5772d5d291', 'POST', 'com.yunqi.member.controller.MemberController#delete', '127.0.0.1', ' 本机地址', 'Chrome_107.0.0.0', 'Windows_10.0', '\"f08dbb1b5ca04f3a90f43e5772d5d291\"', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1669546299955\n}', 0, 94, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669546299957, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669546299957, NULL);
INSERT INTO `ims_sys_log` VALUES ('63f593a930734753a5aeb176df8b17dd', '平台导航', '新增', '/nt/platform/nav/create', 'POST', 'com.yunqi.platform.controller.PlatformNavController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bd8d27989c2492290bdd0da9109bdf8\",\"title\":\"金融\",\"status\":true,\"location\":3,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358602647,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358602647,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358602652\n}', 0, 70, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358602653, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358602653, NULL);
INSERT INTO `ims_sys_log` VALUES ('6f6c9ccf90264b88a9f56d64daacf1ee', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"bf1a8b36603f40ecbef8ec615ac1db43\",\"title\":\"预约\",\"status\":true,\"location\":5,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358591238,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358622385,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358622391\n}', 0, 39, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358622391, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358622391, NULL);
INSERT INTO `ims_sys_log` VALUES ('70473696b4d942cbb005c5c080256091', '平台导航', '新增', '/nt/platform/nav/create', 'POST', 'com.yunqi.platform.controller.PlatformNavController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"f56353df157f4d0ba74afd55e57a4374\",\"title\":\"租房\",\"status\":true,\"location\":7,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358563099,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358563099,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358563106\n}', 0, 88, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358563106, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358563106, NULL);
INSERT INTO `ims_sys_log` VALUES ('7296635c3bd246309379683bd7fd16d2', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"06a069a8e72c466abbc8a9257c7b43de\",\"title\":\"新房\",\"thumb\":\"/attachment/image/20221102/r68m8talsuiqjor7jdekn5a5sm.png\",\"status\":true,\"location\":9,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358555418,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667366872089,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667366872094\n}', 0, 74, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366872094, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366872094, NULL);
INSERT INTO `ims_sys_log` VALUES ('7472867dcd6c4b8490a5eb9990af31a8', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d8ebbc55dbcf467dab3f4afceb055c34\",\"parentId\":\"\",\"name\":\"文章\",\"type\":\"menu\",\"router\":\"article\",\"path\":\"Layout\",\"keepAlive\":false,\"target\":\"data-pjax\",\"icon\":\"article\",\"disabled\":true,\"permission\":\"article\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328635073,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328635073,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328635078\n}', 0, 129, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328635078, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328635078, NULL);
INSERT INTO `ims_sys_log` VALUES ('74fa5d9630094b15944575fe0ed92e13', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"066c0b42655b4a3eb06404dc9a5cc6a8\",\"parentId\":\"d8ebbc55dbcf467dab3f4afceb055c34\",\"name\":\"文章管理\",\"type\":\"menu\",\"router\":\"list\",\"path\":\"views/article/list\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"article.list\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328670185,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328670185,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328670190\n}', 0, 83, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328670190, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328670190, NULL);
INSERT INTO `ims_sys_log` VALUES ('766f7e0dc5b84038b7c4110769faa6a1', '平台导航', '新增', '/nt/platform/nav/create', 'POST', 'com.yunqi.platform.controller.PlatformNavController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bf39a3d86e64748b2f7e69d30ad2ff3\",\"title\":\"公寓\",\"status\":true,\"location\":6,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358573935,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358573935,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358573940\n}', 0, 86, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358573941, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358573941, NULL);
INSERT INTO `ims_sys_log` VALUES ('77e0e7aab20047cbaf205e1b625c443c', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"b29a71d09c60475c8e8623873f6d9f3e\",\"parentId\":\"\",\"name\":\"财务\",\"type\":\"menu\",\"router\":\"finance\",\"path\":\"Layout\",\"keepAlive\":false,\"target\":\"data-pjax\",\"icon\":\"finance\",\"disabled\":true,\"permission\":\"finance\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328483455,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328483455,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328483461\n}', 0, 97, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328483461, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328483461, NULL);
INSERT INTO `ims_sys_log` VALUES ('7831e55a5a0845c5bd84d24b3f1dba79', '系统菜单', '新增', '/nt/sys/menu/batch', 'POST', 'com.yunqi.system.controller.SysMenuController#batch', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"parentId\":\"34b4edb7e1ea4a578d9d9f78d74e64ea\",\"permissionData\":[{\"value\":\"platform.nav.create\",\"label\":\"新增导航\"}, {\"value\":\"platform.nav.update\",\"label\":\"修改导航\"}, {\"value\":\"platform.nav.delete\",\"label\":\"删除导航\"}]}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327692567\n}', 0, 86, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692567, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692567, NULL);
INSERT INTO `ims_sys_log` VALUES ('82824c7f76d64b6d81d0e63ed3b13f3d', '用户等级', '新增', '/nt/member/level/create', 'POST', 'com.yunqi.member.controller.MemberLevelController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"693a539f1aa14418b12f6aad2ede75c5\",\"level\":\"1\",\"name\":\"黄金会员\",\"status\":true,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667441931563,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667441931563,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667441931573\n}', 0, 87, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441931574, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441931574, NULL);
INSERT INTO `ims_sys_log` VALUES ('83c7c5bf8d664e62ace63d7e9c6ca33b', '平台幻灯片', '新增', '/nt/platform/adv/create', 'POST', 'com.yunqi.platform.controller.PlatformAdvController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"4512f60e99334f569a91d24b00309f83\",\"title\":\"2\",\"thumb\":\"/attachment/image/20221102/o1nuuhb20aj7lq20cgberq1gll.jpg\",\"status\":true,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358455731,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358455731,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358455738\n}', 0, 85, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358455738, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358455738, NULL);
INSERT INTO `ims_sys_log` VALUES ('84a5528221b94a7a844885138baacc65', '会员管理', '删除', '/nt/member/delete/7ad2d66933c740e6bd11ebcd1aa9b639', 'POST', 'com.yunqi.member.controller.MemberController#delete', '127.0.0.1', ' 本机地址', 'Chrome_107.0.0.0', 'Windows_10.0', '\"7ad2d66933c740e6bd11ebcd1aa9b639\"', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1669545679967\n}', 0, 98, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545679967, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545679967, NULL);
INSERT INTO `ims_sys_log` VALUES ('87afddd928cf400cbb4a589149360e29', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"c50eb082594d4f8face94514de66dece\",\"parentId\":\"b477df402a1d431ab33c8ef71c9476cc\",\"name\":\"用户分组\",\"type\":\"menu\",\"router\":\"group\",\"path\":\"views/member/group\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"member.group\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328416112,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328416112,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328416117\n}', 0, 73, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328416117, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328416117, NULL);
INSERT INTO `ims_sys_log` VALUES ('88210990197a4dc9b0c4a34945891efc', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bd8d27989c2492290bdd0da9109bdf8\",\"title\":\"金融\",\"thumb\":\"/attachment/image/20221108/roinlmkvlqgvfpcj487afvf98s.png\",\"status\":true,\"location\":4,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358602647,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667871953935,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667871953940\n}', 0, 85, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871953940, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871953940, NULL);
INSERT INTO `ims_sys_log` VALUES ('8866d7c11e8147f99b57716e4d2d7c23', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"06a069a8e72c466abbc8a9257c7b43de\",\"title\":\"新房\",\"thumb\":\"/attachment/image/20221108/t6ucpvrmeaheto5jk1dm2k14d8.png\",\"status\":true,\"location\":9,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358555418,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667871878338,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667871878346\n}', 0, 80, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871878347, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871878347, NULL);
INSERT INTO `ims_sys_log` VALUES ('91faf1cd06604cc69064e36af06a474d', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,c50eb082594d4f8face94514de66dece,4c4e1778aa1341a883bf9b0b467aff5a,8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,06d3853a1e9343728d1d29753ee972ad,af4c415540844cb08d4e7c01d43f6b71,b29a71d09c60475c8e8623873f6d9f3e,5f04a86630024fb99f6a9744e87ea8fc,f36e9d3cfbec4eb08e6e15a2fae6d63a,7bc3bed2f327465284818142adfcc376,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328612478\n}', 0, 104, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328612478, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328612478, NULL);
INSERT INTO `ims_sys_log` VALUES ('922a8d4c14c54b19928309b895e47689', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bf39a3d86e64748b2f7e69d30ad2ff3\",\"title\":\"公寓\",\"thumb\":\"/attachment/image/20221108/7nn461m74uinlo8cbtrq35cah4.png\",\"status\":true,\"location\":7,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358573935,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667871926492,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667871926498\n}', 0, 77, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871926499, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871926499, NULL);
INSERT INTO `ims_sys_log` VALUES ('95c977e05cdb49ada8d88549b8b05b20', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"55dd43dc1ef44890a3a297c186a7f22f\",\"parentId\":\"\",\"name\":\"平台\",\"type\":\"menu\",\"router\":\"platform\",\"path\":\"Layout\",\"keepAlive\":false,\"target\":\"data-pjax\",\"icon\":\"app_y\",\"disabled\":true,\"permission\":\"platform\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327582799,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327582799,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327582806\n}', 0, 96, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327582806, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327582806, NULL);
INSERT INTO `ims_sys_log` VALUES ('97c86d4917b74c2e9402898898670ffb', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d45b5d05165e4e7e9d5b37d24da0b398\",\"parentId\":\"d8ebbc55dbcf467dab3f4afceb055c34\",\"name\":\"文章分类\",\"type\":\"menu\",\"router\":\"category\",\"path\":\"views/article/category\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"article.category\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328652490,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328652490,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328652492\n}', 0, 82, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328652492, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328652492, NULL);
INSERT INTO `ims_sys_log` VALUES ('9dad4bf59406484f926d2eeaf37add1a', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"bf1a8b36603f40ecbef8ec615ac1db43\",\"title\":\"预约\",\"thumb\":\"/attachment/image/20221102/tv4fsvriv0g6cr9q5csinq1jlh.png\",\"status\":true,\"location\":5,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358591238,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667366905410,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667366905418\n}', 0, 86, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366905419, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366905419, NULL);
INSERT INTO `ims_sys_log` VALUES ('a1a563aea80b49758f8e4e2f6831931d', '用户标签', '新增', '/nt/member/label/create', 'POST', 'com.yunqi.member.controller.MemberLabelController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"2571e4d6ab644232a41474d4170a8ce9\",\"name\":\"低等消费\",\"note\":\"低等消费\",\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667442006227,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667442006227,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667442006233\n}', 0, 72, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667442006234, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667442006234, NULL);
INSERT INTO `ims_sys_log` VALUES ('a2c6c7a94c8144af977c0dc60b53b8d0', '平台导航', '新增', '/nt/platform/nav/create', 'POST', 'com.yunqi.platform.controller.PlatformNavController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d21aee133fbe448faf7124d32fc52cad\",\"title\":\"二手房\",\"status\":true,\"location\":10,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358548291,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358548291,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358548314\n}', 0, 86, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358548315, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358548315, NULL);
INSERT INTO `ims_sys_log` VALUES ('a55c833d2c3848b9a1b01b69d262132f', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d21aee133fbe448faf7124d32fc52cad\",\"title\":\"二手房\",\"thumb\":\"/attachment/image/20221108/697f6anm86hp2quleu18q9cb0o.png\",\"url\":\"pages/mls/ershoufang\",\"status\":true,\"location\":10,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358548291,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667876294874,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667876294880\n}', 0, 86, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876294881, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876294881, NULL);
INSERT INTO `ims_sys_log` VALUES ('a77b963b88b641d994e8a20b65e870a5', '系统菜单', '修改', '/nt/sys/menu/update', 'POST', 'com.yunqi.system.controller.SysMenuController#update', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d3596ec42306475bac563ef49563bbd3\",\"parentId\":\"8ebabe59d484416f8cf3524f75e2d849\",\"name\":\"商品管理\",\"type\":\"menu\",\"router\":\"list\",\"path\":\"views/goods/list\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"goods.list\",\"location\":1,\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328227935,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328263481,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328263531\n}', 0, 84, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328263531, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328263531, NULL);
INSERT INTO `ims_sys_log` VALUES ('aa371c84d7254b0483baaba4b60f1bd7', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"73775b5595724be38ae1ccafcef1690d\",\"parentId\":\"8ebabe59d484416f8cf3524f75e2d849\",\"name\":\"商品分类\",\"type\":\"menu\",\"router\":\"category\",\"path\":\"views/goods/category\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"goods.category\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328200152,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328200152,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328200159\n}', 0, 79, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328200160, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328200160, NULL);
INSERT INTO `ims_sys_log` VALUES ('ab168c05afc74924a5c5b81ca6e9ac8f', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d21aee133fbe448faf7124d32fc52cad\",\"title\":\"二手房\",\"thumb\":\"/attachment/image/20221108/697f6anm86hp2quleu18q9cb0o.png\",\"status\":true,\"location\":10,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358548291,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667871905776,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667871905783\n}', 0, 89, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871905783, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871905783, NULL);
INSERT INTO `ims_sys_log` VALUES ('ab71572c008e420794c7296c148adecc', '用户等级', '新增', '/nt/member/level/create', 'POST', 'com.yunqi.member.controller.MemberLevelController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"66785384822f4b76ac4c5f2e217597e0\",\"level\":\"0\",\"name\":\"大众会员\",\"status\":true,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667441924361,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667441924361,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667441924380\n}', 0, 95, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441924385, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667441924385, NULL);
INSERT INTO `ims_sys_log` VALUES ('ac5b18f7267a4c6a9279e7ba06a1f47a', '平台导航', '新增', '/nt/platform/nav/create', 'POST', 'com.yunqi.platform.controller.PlatformNavController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"ee1604b9a8b24b848d03716d31e6e7ad\",\"title\":\"商铺\",\"status\":true,\"location\":5,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358582848,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358582848,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358582854\n}', 0, 72, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358582854, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358582854, NULL);
INSERT INTO `ims_sys_log` VALUES ('afa7f9db71404e1da669bd467fe402ea', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"bf1a8b36603f40ecbef8ec615ac1db43\",\"title\":\"预约\",\"thumb\":\"/attachment/image/20221108/1fh9i7ker2ibqqe5g55unl00ji.png\",\"status\":true,\"location\":5,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358591238,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667871944759,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667871944764\n}', 0, 84, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871944764, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667871944764, NULL);
INSERT INTO `ims_sys_log` VALUES ('b37cfc80027c4e89b03517fd118a4100', '平台导航', '新增', '/nt/platform/nav/create', 'POST', 'com.yunqi.platform.controller.PlatformNavController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"06a069a8e72c466abbc8a9257c7b43de\",\"title\":\"新房\",\"status\":true,\"location\":9,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358555418,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358555418,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358555425\n}', 0, 74, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358555425, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358555425, NULL);
INSERT INTO `ims_sys_log` VALUES ('b8734809bb114b1e86ca627e239d9d82', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"06a069a8e72c466abbc8a9257c7b43de\",\"title\":\"新房\",\"thumb\":\"/attachment/image/20221108/t6ucpvrmeaheto5jk1dm2k14d8.png\",\"url\":\"pages/mls/loupan\",\"status\":true,\"location\":9,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358555418,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667876764183,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667876764189\n}', 0, 81, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876764189, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876764189, NULL);
INSERT INTO `ims_sys_log` VALUES ('b907233a16fc4db795540238a0eb2506', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.65.111', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bf39a3d86e64748b2f7e69d30ad2ff3\",\"title\":\"公寓\",\"thumb\":\"/attachment/image/20221108/7nn461m74uinlo8cbtrq35cah4.png\",\"url\":\"pages/mls/apartment\",\"status\":true,\"location\":7,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358573935,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667876934251,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667876934255\n}', 0, 89, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876934256, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667876934256, NULL);
INSERT INTO `ims_sys_log` VALUES ('bb5ae66b87484fb8bf79f1f01b90ef54', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"34ae2583fbe44961aeb1514bd8d03d09\",\"parentId\":\"b477df402a1d431ab33c8ef71c9476cc\",\"name\":\"用户管理\",\"type\":\"menu\",\"router\":\"list\",\"path\":\"views/member/list\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"member.list\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327773432,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327773432,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327773438\n}', 0, 92, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327773438, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327773438, NULL);
INSERT INTO `ims_sys_log` VALUES ('c12adfb5aff646f28118a7b5355f423c', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bd8d27989c2492290bdd0da9109bdf8\",\"title\":\"金融\",\"status\":true,\"location\":4,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358602647,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358627328,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358627335\n}', 0, 39, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358627335, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358627335, NULL);
INSERT INTO `ims_sys_log` VALUES ('c8e8bd149ac047568749069fa9fc59a2', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"f56353df157f4d0ba74afd55e57a4374\",\"title\":\"租房\",\"thumb\":\"/attachment/image/20221102/sjhk06qp7ej69qdl5rkpp7ekuo.png\",\"status\":true,\"location\":8,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358563099,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667366883778,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667366883783\n}', 0, 73, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366883783, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366883783, NULL);
INSERT INTO `ims_sys_log` VALUES ('c9d9baaf10194077a48342df92ab555e', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"4c4e1778aa1341a883bf9b0b467aff5a\",\"parentId\":\"b477df402a1d431ab33c8ef71c9476cc\",\"name\":\"用户标签\",\"type\":\"menu\",\"router\":\"label\",\"path\":\"views/member/label\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"member.label\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328434975,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328434975,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328434981\n}', 0, 90, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328434981, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328434981, NULL);
INSERT INTO `ims_sys_log` VALUES ('d00fb68a113443afab76b876ddc49b37', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"f36e9d3cfbec4eb08e6e15a2fae6d63a\",\"parentId\":\"b29a71d09c60475c8e8623873f6d9f3e\",\"name\":\"余额明细\",\"type\":\"menu\",\"router\":\"balance\",\"path\":\"views/finance/balance\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"finance.balance\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665328524094,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665328524094,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328524099\n}', 0, 84, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328524099, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328524099, NULL);
INSERT INTO `ims_sys_log` VALUES ('d4c1edd6e6a547d197af91c747f51253', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"ee1604b9a8b24b848d03716d31e6e7ad\",\"title\":\"商铺\",\"status\":true,\"location\":6,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358582848,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358617615,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358617620\n}', 0, 39, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358617620, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358617620, NULL);
INSERT INTO `ims_sys_log` VALUES ('d5d728560d554b1e953d32171b86b70d', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bf39a3d86e64748b2f7e69d30ad2ff3\",\"title\":\"公寓\",\"thumb\":\"/attachment/image/20221102/44jb4vcn68j2or2622dovus5v0.png\",\"status\":true,\"location\":7,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358573935,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667366891441,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667366891446\n}', 0, 82, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366891446, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366891446, NULL);
INSERT INTO `ims_sys_log` VALUES ('dbabf521f00446ae8f5d113dc4403095', '平台幻灯片', '新增', '/nt/platform/adv/create', 'POST', 'com.yunqi.platform.controller.PlatformAdvController#create', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"d311137b5bc8463b9fdce45f851b3624\",\"title\":\"1\",\"thumb\":\"/attachment/image/20221102/r258gscq6mgjpr9sder36tj780.jpg\",\"status\":true,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358450134,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358450134,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358450153\n}', 0, 111, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358450158, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358450158, NULL);
INSERT INTO `ims_sys_log` VALUES ('dc3ef0e801f44a86a469067c88fc76fc', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,c50eb082594d4f8face94514de66dece,4c4e1778aa1341a883bf9b0b467aff5a,8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,06d3853a1e9343728d1d29753ee972ad,af4c415540844cb08d4e7c01d43f6b71,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328442829\n}', 0, 82, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328442829, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328442829, NULL);
INSERT INTO `ims_sys_log` VALUES ('dd79520a591e418baa114055df732c7c', '系统用户', '新增', '/nt/sys/user/create', 'POST', 'com.yunqi.system.controller.SysUserController#create', '112.193.219.41', '四川省成都市 联通', 'Chrome_107.0.0.0', 'Windows_10.0', '{\"id\":\"b4d92f00784c493bb23f88e55b88a448\",\"username\":\"admin\",\"password\":\"75b48811a9aafd273dd8735a25991f77975eaccf8692544ad9554706667fca9b\",\"salt\":\"7s2971j8hkgriovg7fon4vnqr0\",\"nickname\":\"管理员\",\"sex\":0,\"online\":false,\"disabled\":true,\"note\":\"管理员账号\",\"loginCount\":0,\"deptId\":\"aeef6ef34bb34f7baada18366848b705\",\"roles\":[{\"id\":\"292d891323dc4a26b98880f246ae8f55\",\"name\":\"管理员\",\"code\":\"ADMIN\",\"disabled\":true,\"location\":0,\"note\":\"管理员权限\",\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1669530459829,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1669530459829,\"delFlag\":false}],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1669530532250,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1669530532250,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1669530532269\n}', 0, 88, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530532269, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530532269, NULL);
INSERT INTO `ims_sys_log` VALUES ('e6dd78c7583c4b58921a4a65373988e2', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,c50eb082594d4f8face94514de66dece,4c4e1778aa1341a883bf9b0b467aff5a,b29a71d09c60475c8e8623873f6d9f3e,5f04a86630024fb99f6a9744e87ea8fc,f36e9d3cfbec4eb08e6e15a2fae6d63a,7bc3bed2f327465284818142adfcc376,8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,06d3853a1e9343728d1d29753ee972ad,af4c415540844cb08d4e7c01d43f6b71,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665328568023\n}', 0, 84, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328568023, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328568023, NULL);
INSERT INTO `ims_sys_log` VALUES ('f0fba9d2fcd343268158596c5a01a12e', '系统菜单', '新增', '/nt/sys/menu/create', 'POST', 'com.yunqi.system.controller.SysMenuController#create', '127.0.0.1', ' 本机地址', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"c322c9a13c1847a99c2d60db437a5cff\",\"parentId\":\"b477df402a1d431ab33c8ef71c9476cc\",\"name\":\"用户等级\",\"type\":\"menu\",\"router\":\"level\",\"path\":\"views/member/level\",\"keepAlive\":false,\"target\":\"data-pjax\",\"disabled\":true,\"permission\":\"member.level\",\"children\":[],\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1665327795760,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1665327795760,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1665327795765\n}', 0, 90, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327795765, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327795765, NULL);
INSERT INTO `ims_sys_log` VALUES ('f42821a7e33847b589c7c9364084e6b9', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bd8d27989c2492290bdd0da9109bdf8\",\"title\":\"金融\",\"thumb\":\"/attachment/image/20221102/qp9i3d5nr0ibporcjrck61mska.png\",\"status\":true,\"location\":4,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358602647,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667366911179,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667366911184\n}', 0, 76, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366911184, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667366911184, NULL);
INSERT INTO `ims_sys_log` VALUES ('f8068eba44224f33b3a0bd6d5c201c81', '系统菜单', '排序', '/nt/sys/menu/sort', 'POST', 'com.yunqi.system.controller.SysMenuController#sort', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"ids\":\"b477df402a1d431ab33c8ef71c9476cc,34ae2583fbe44961aeb1514bd8d03d09,c322c9a13c1847a99c2d60db437a5cff,03f39f264de54896a0c3312011759880,dcf39cd6a6474b8a8b4dc4f1bd0683bf,f0a68e2ef4bf41f7be1ef8ef36214f58,4c4e1778aa1341a883bf9b0b467aff5a,4b115ff4eba5408cac440cc491ec2817,41e4e6aa11d64ee3b9dd5418302afe19,d22a59f338194145b210d6570e728b9b,8ebabe59d484416f8cf3524f75e2d849,d3596ec42306475bac563ef49563bbd3,73775b5595724be38ae1ccafcef1690d,06d3853a1e9343728d1d29753ee972ad,af4c415540844cb08d4e7c01d43f6b71,d8ebbc55dbcf467dab3f4afceb055c34,066c0b42655b4a3eb06404dc9a5cc6a8,d45b5d05165e4e7e9d5b37d24da0b398,b29a71d09c60475c8e8623873f6d9f3e,5f04a86630024fb99f6a9744e87ea8fc,f36e9d3cfbec4eb08e6e15a2fae6d63a,7bc3bed2f327465284818142adfcc376,55dd43dc1ef44890a3a297c186a7f22f,bff555bc8cb541f3b99259f37d223407,601d11debae943d6833bdc0654d796e2,93a2df92fe6743e0b7d9833c95c0713d,6d0d1bb8367d46abb32ba03a94db48c7,34b4edb7e1ea4a578d9d9f78d74e64ea,a1ec86029b2f4b789117698042a2e64a,2e1f9a56ea3846b4a932c79ab73db47e,0d1e8d392cb94723a5f91eb44566c86f,64691d716695497785087b8764e5536a,3a5e11dde1664201bda92ae55e989d6d,359f4f895c664043939e8bd80d9a5758,b210b9e4793b41ba98cb75b9ae1ca72e,abcd551142744b0b94ed932e95bcdb68,63d87113850f47f8ac9546689f64655d,5e569f106ce34d6f9a230962f8827b10,7ebc8e5ef5654012bed1f4caf6471b84,d24193c1f5cb438c8d27ecba8a39e738,132ad919d3ed483cad5d1c4f3216cdb8,a11575ca032346e5824dda15e1754a14,4ea9f2ad825647b694017356d4670593,ecced3084db7473a975109f7675fbe6d,90d9f13a89b0495c924f431f17833111,89a40a9d37fd400ab1f5e0ae4adbfffa,e5a782003638492c81b8d28b8b5a526f,b10018aa630347b9b2fecb1c70701e88,b409c62b8d504c8c89f588397aa36d5e,8c9aed13cb5046ca945d0b75f814ed67,0f7e7e6618dc43d39b4d0bffd99382e1,a2ae7df38ec344b1bfd6c3f6cc8f0c20,047822c687324b2182bd7bae1eaa06b3,e186ecbe4d074a17a5c833e234bd917f,c1279bc0845c4049a70059fe708b5e3a,7267b517d6eb4a28b9d531bb89772f29,f80d1735b00f43e3b4090797ee40c31d,f6a75d6d079c4a5580b3d67450f34f0c,cdbf51e9ace946a39f703717e4456cea,ce9c18d4f9af43e98cb698673fb391d0,dce3264e6dca44958d47a3c712067bb3,768c8ef045524aa3a9d6330e93cdce97,a7c90cc3383c4c988475961b194621e6,5b8e241b46974e8383b12261a495efbf,4d1ccc0126cd461685e397740938a523,9fbe14a07eba439188a6b2f75ef35159,83d93f46f9af431e8217f128b8dacca9,3efe1dbab27f483fb61803e00c891f54\"}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667383506133\n}', 0, 78, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383506133, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383506133, NULL);
INSERT INTO `ims_sys_log` VALUES ('fc269088df2e48219c7bba9dacb21ac0', '平台导航', '修改', '/nt/platform/nav/update', 'POST', 'com.yunqi.platform.controller.PlatformNavController#update', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"id\":\"1bf39a3d86e64748b2f7e69d30ad2ff3\",\"title\":\"公寓\",\"status\":true,\"location\":7,\"createdById\":\"6841c29452984878ac47b1b86195f46e\",\"createdBy\":\"超级管理员\",\"createdAt\":1667358573935,\"updatedById\":\"6841c29452984878ac47b1b86195f46e\",\"updatedBy\":\"超级管理员\",\"updatedAt\":1667358613616,\"delFlag\":false}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667358613620\n}', 0, 40, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358613620, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667358613620, NULL);
INSERT INTO `ims_sys_log` VALUES ('fd87d428e2d04832976ab1d025c5eccd', '系统菜单', '新增', '/nt/sys/menu/batch', 'POST', 'com.yunqi.system.controller.SysMenuController#batch', '112.193.67.117', '四川省成都市 联通', 'Chrome_106.0.0.0', 'Windows_10.0', '{\"parentId\":\"4c4e1778aa1341a883bf9b0b467aff5a\",\"permissionData\":[{\"value\":\"member.label.create\",\"label\":\"新增标签\"}, {\"value\":\"member.label.update\",\"label\":\"修改标签\"}, {\"value\":\"member.label.delete\",\"label\":\"删除标签\"}]}', '{\n   \"code\": 20000,\n   \"msg\": \"成功\",\n   \"time\": 1667383497176\n}', 0, 72, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497176, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497176, NULL);

-- ----------------------------
-- Table structure for ims_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_menu`;
CREATE TABLE `ims_sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `parentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父级ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源类型',
  `router` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由路径',
  `keepAlive` tinyint(1) NULL DEFAULT NULL COMMENT '路由缓存',
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '打开方式',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `disabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限唯一编码',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单介绍',
  `location` int NULL DEFAULT 0 COMMENT '排序字段',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_menu
-- ----------------------------
INSERT INTO `ims_sys_menu` VALUES ('03f39f264de54896a0c3312011759880', 'c322c9a13c1847a99c2d60db437a5cff', '新增等级', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.level.create', NULL, 5, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478679, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478679, 0);
INSERT INTO `ims_sys_menu` VALUES ('047822c687324b2182bd7bae1eaa06b3', '0f7e7e6618dc43d39b4d0bffd99382e1', '修改任务', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.task.update', NULL, 54, '', '', 1639892144820, '', '', 1639892144820, NULL);
INSERT INTO `ims_sys_menu` VALUES ('066c0b42655b4a3eb06404dc9a5cc6a8', 'd8ebbc55dbcf467dab3f4afceb055c34', '文章管理', 'menu', 'list', 'views/article/list', 0, 'data-pjax', NULL, 1, 'article.list', NULL, 18, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328670185, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328670185, 0);
INSERT INTO `ims_sys_menu` VALUES ('06d3853a1e9343728d1d29753ee972ad', '', '订单', 'menu', 'order', 'Layout', 0, 'data-pjax', 'order', 1, 'order', NULL, 15, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328322615, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328322615, 0);
INSERT INTO `ims_sys_menu` VALUES ('0d1e8d392cb94723a5f91eb44566c86f', '34b4edb7e1ea4a578d9d9f78d74e64ea', '删除导航', 'data', NULL, NULL, 0, NULL, NULL, 1, 'platform.nav.delete', NULL, 32, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692557, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692557, 0);
INSERT INTO `ims_sys_menu` VALUES ('0f7e7e6618dc43d39b4d0bffd99382e1', '8c9aed13cb5046ca945d0b75f814ed67', '定时任务', 'menu', 'task', 'views/system/task', 0, 'data-pjax', NULL, 1, 'sys.task', NULL, 52, '', '', 1639821076877, '', '', 1639821076877, NULL);
INSERT INTO `ims_sys_menu` VALUES ('132ad919d3ed483cad5d1c4f3216cdb8', 'd24193c1f5cb438c8d27ecba8a39e738', '添加角色', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.role.create', NULL, 42, '', '', 1639823435292, '', '', 1639823435292, NULL);
INSERT INTO `ims_sys_menu` VALUES ('23c4f25255e0490c9671e212aea9939b', '34ae2583fbe44961aeb1514bd8d03d09', '删除用户', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.list.delete', NULL, 3, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545443288, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545443288, 0);
INSERT INTO `ims_sys_menu` VALUES ('2e1f9a56ea3846b4a932c79ab73db47e', '34b4edb7e1ea4a578d9d9f78d74e64ea', '修改导航', 'data', NULL, NULL, 0, NULL, NULL, 1, 'platform.nav.update', NULL, 31, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692557, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692557, 0);
INSERT INTO `ims_sys_menu` VALUES ('34ae2583fbe44961aeb1514bd8d03d09', 'b477df402a1d431ab33c8ef71c9476cc', '用户管理', 'menu', 'list', 'views/member/list', 0, 'data-pjax', NULL, 1, 'member.list', NULL, 1, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327773432, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327773432, 0);
INSERT INTO `ims_sys_menu` VALUES ('34b4edb7e1ea4a578d9d9f78d74e64ea', '55dd43dc1ef44890a3a297c186a7f22f', '导航管理', 'menu', 'nav', 'views/platform/nav', 0, 'data-pjax', NULL, 1, 'platform.nav', NULL, 29, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327669274, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327669274, 0);
INSERT INTO `ims_sys_menu` VALUES ('359f4f895c664043939e8bd80d9a5758', '3a5e11dde1664201bda92ae55e989d6d', '系统管理', 'menu', 'system', 'views/system', 0, 'data-pjax', NULL, 1, 'sys.list', NULL, 35, '', '', 1639820827912, '', '', 1639820827912, NULL);
INSERT INTO `ims_sys_menu` VALUES ('3a5e11dde1664201bda92ae55e989d6d', '', '系统', 'menu', 'sys', 'Layout', 0, 'data-pjax', 'application', 1, 'sys', NULL, 34, '', '', 1639820788645, '', '', 1639820788645, NULL);
INSERT INTO `ims_sys_menu` VALUES ('3efe1dbab27f483fb61803e00c891f54', '4d1ccc0126cd461685e397740938a523', '删除参数', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.conf.delete', NULL, 69, '', '', 1640663118461, '', '', 1640663118461, NULL);
INSERT INTO `ims_sys_menu` VALUES ('41e4e6aa11d64ee3b9dd5418302afe19', '4c4e1778aa1341a883bf9b0b467aff5a', '修改标签', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.label.update', NULL, 10, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497165, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497165, 0);
INSERT INTO `ims_sys_menu` VALUES ('4b115ff4eba5408cac440cc491ec2817', '4c4e1778aa1341a883bf9b0b467aff5a', '新增标签', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.label.create', NULL, 9, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497160, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497160, 0);
INSERT INTO `ims_sys_menu` VALUES ('4c4e1778aa1341a883bf9b0b467aff5a', 'b477df402a1d431ab33c8ef71c9476cc', '用户标签', 'menu', 'label', 'views/member/label', 0, 'data-pjax', NULL, 1, 'member.label', NULL, 8, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328434975, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328434975, 0);
INSERT INTO `ims_sys_menu` VALUES ('4d1ccc0126cd461685e397740938a523', 'f6a75d6d079c4a5580b3d67450f34f0c', '系统参数', 'menu', 'param', 'views/system/param', 0, 'data-pjax', NULL, 1, 'sys.conf', NULL, 66, '', '', 1640661150142, '', '', 1640661150142, NULL);
INSERT INTO `ims_sys_menu` VALUES ('4ea9f2ad825647b694017356d4670593', 'd24193c1f5cb438c8d27ecba8a39e738', '删除角色', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.role.delete', NULL, 44, '', '', 1639823478391, '', '', 1639823478392, NULL);
INSERT INTO `ims_sys_menu` VALUES ('4fb692712f92438e80af384ecfc8c5e7', '34ae2583fbe44961aeb1514bd8d03d09', '修改用户', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.list.update', NULL, 2, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545443279, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669545443279, 0);
INSERT INTO `ims_sys_menu` VALUES ('55dd43dc1ef44890a3a297c186a7f22f', '', '平台', 'menu', 'platform', 'Layout', 0, 'data-pjax', 'app_y', 1, 'platform', NULL, 24, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327582799, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327582799, 0);
INSERT INTO `ims_sys_menu` VALUES ('5b8e241b46974e8383b12261a495efbf', 'cdbf51e9ace946a39f703717e4456cea', '菜单排序', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.menu.sort', NULL, 65, '', '', 1639823412436, '', '', 1639823412436, NULL);
INSERT INTO `ims_sys_menu` VALUES ('5e569f106ce34d6f9a230962f8827b10', 'b210b9e4793b41ba98cb75b9ae1ca72e', '删除组织', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.dept.delete', NULL, 39, '', '', 1639823299494, '', '', 1639823299494, NULL);
INSERT INTO `ims_sys_menu` VALUES ('5f04a86630024fb99f6a9744e87ea8fc', 'b29a71d09c60475c8e8623873f6d9f3e', '充值记录', 'menu', 'recharge', 'views/finance/log/recharge', 0, 'data-pjax', NULL, 1, 'finance.recharge', NULL, 21, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328504933, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328504933, 0);
INSERT INTO `ims_sys_menu` VALUES ('601d11debae943d6833bdc0654d796e2', 'bff555bc8cb541f3b99259f37d223407', '新增幻灯片', 'data', NULL, NULL, 0, NULL, NULL, 1, 'platform.adv.create', NULL, 26, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637622, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637622, 0);
INSERT INTO `ims_sys_menu` VALUES ('63d87113850f47f8ac9546689f64655d', 'b210b9e4793b41ba98cb75b9ae1ca72e', '修改组织', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.dept.update', NULL, 38, '', '', 1639822916765, '', '', 1639822916765, NULL);
INSERT INTO `ims_sys_menu` VALUES ('64691d716695497785087b8764e5536a', '55dd43dc1ef44890a3a297c186a7f22f', '意见反馈', 'menu', 'feedback', 'views/platform/feedback', 0, 'data-pjax', NULL, 1, 'platform.feedback', NULL, 33, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327719025, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327719025, 0);
INSERT INTO `ims_sys_menu` VALUES ('6d0d1bb8367d46abb32ba03a94db48c7', 'bff555bc8cb541f3b99259f37d223407', '删除幻灯片', 'data', NULL, NULL, 0, NULL, NULL, 1, 'platform.adv.delete', NULL, 28, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637633, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637633, 0);
INSERT INTO `ims_sys_menu` VALUES ('7267b517d6eb4a28b9d531bb89772f29', '8c9aed13cb5046ca945d0b75f814ed67', '登录日志', 'menu', 'authlog', 'views/system/authlog', 0, 'data-pjax', NULL, 1, 'sys.auth.log', NULL, 57, '', '', 1640508851329, '', '', 1640508851329, NULL);
INSERT INTO `ims_sys_menu` VALUES ('73775b5595724be38ae1ccafcef1690d', '8ebabe59d484416f8cf3524f75e2d849', '商品分类', 'menu', 'category', 'views/goods/category', 0, 'data-pjax', NULL, 1, 'goods.category', NULL, 14, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328200152, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328200152, 0);
INSERT INTO `ims_sys_menu` VALUES ('768c8ef045524aa3a9d6330e93cdce97', 'cdbf51e9ace946a39f703717e4456cea', '修改菜单', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.menu.update', NULL, 63, '', '', 1639823371366, '', '', 1639823371366, NULL);
INSERT INTO `ims_sys_menu` VALUES ('7bc3bed2f327465284818142adfcc376', 'b29a71d09c60475c8e8623873f6d9f3e', '积分明细', 'menu', 'points', 'views/finance/points', 0, 'data-pjax', NULL, 1, 'finance.points', NULL, 23, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328558289, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328558289, 0);
INSERT INTO `ims_sys_menu` VALUES ('7ebc8e5ef5654012bed1f4caf6471b84', 'b210b9e4793b41ba98cb75b9ae1ca72e', '组织排序', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.dept.sort', NULL, 40, '', '', 1639823321005, '', '', 1639823321005, NULL);
INSERT INTO `ims_sys_menu` VALUES ('83d93f46f9af431e8217f128b8dacca9', '4d1ccc0126cd461685e397740938a523', '修改参数', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.conf.update', NULL, 68, '', '', 1640663089147, '', '', 1640663089147, NULL);
INSERT INTO `ims_sys_menu` VALUES ('89a40a9d37fd400ab1f5e0ae4adbfffa', '90d9f13a89b0495c924f431f17833111', '添加用户', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.user.create', NULL, 47, '', '', 1639822721151, '', '', 1639822721151, NULL);
INSERT INTO `ims_sys_menu` VALUES ('8c9aed13cb5046ca945d0b75f814ed67', '3a5e11dde1664201bda92ae55e989d6d', '系统监控', 'menu', 'control', 'views/system', 0, 'data-pjax', NULL, 1, 'sys.monitor', NULL, 51, '', '', 1639820855260, '', '', 1639820855260, NULL);
INSERT INTO `ims_sys_menu` VALUES ('8ebabe59d484416f8cf3524f75e2d849', '', '商品', 'menu', 'goods', 'Layout', 0, 'data-pjax', 'goods', 1, 'goods', NULL, 12, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328177817, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328177817, 0);
INSERT INTO `ims_sys_menu` VALUES ('90d9f13a89b0495c924f431f17833111', '359f4f895c664043939e8bd80d9a5758', '用户管理', 'menu', 'user', 'views/system/user', 0, 'data-pjax', NULL, 1, 'sys.user', NULL, 46, '', '', 1639820983812, '', '', 1639820983812, NULL);
INSERT INTO `ims_sys_menu` VALUES ('93a2df92fe6743e0b7d9833c95c0713d', 'bff555bc8cb541f3b99259f37d223407', '修改幻灯片', 'data', NULL, NULL, 0, NULL, NULL, 1, 'platform.adv.update', NULL, 27, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637627, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327637627, 0);
INSERT INTO `ims_sys_menu` VALUES ('9fbe14a07eba439188a6b2f75ef35159', '4d1ccc0126cd461685e397740938a523', '添加参数', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.conf.create', NULL, 67, '', '', 1640663057477, '', '', 1640663057477, NULL);
INSERT INTO `ims_sys_menu` VALUES ('a11575ca032346e5824dda15e1754a14', 'd24193c1f5cb438c8d27ecba8a39e738', '修改角色', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.role.update', NULL, 43, '', '', 1639823459964, '', '', 1639823459964, NULL);
INSERT INTO `ims_sys_menu` VALUES ('a1ec86029b2f4b789117698042a2e64a', '34b4edb7e1ea4a578d9d9f78d74e64ea', '新增导航', 'data', NULL, NULL, 0, NULL, NULL, 1, 'platform.nav.create', NULL, 30, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692552, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327692552, 0);
INSERT INTO `ims_sys_menu` VALUES ('a2ae7df38ec344b1bfd6c3f6cc8f0c20', '0f7e7e6618dc43d39b4d0bffd99382e1', '添加任务', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.task.create', NULL, 53, '', '', 1639892113500, '', '', 1639892123702, NULL);
INSERT INTO `ims_sys_menu` VALUES ('a7c90cc3383c4c988475961b194621e6', 'cdbf51e9ace946a39f703717e4456cea', '删除菜单', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.menu.delete', NULL, 64, '', '', 1639823392133, '', '', 1639823392134, NULL);
INSERT INTO `ims_sys_menu` VALUES ('abcd551142744b0b94ed932e95bcdb68', 'b210b9e4793b41ba98cb75b9ae1ca72e', '添加组织', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.dept.create', NULL, 37, '', '', 1639822863684, '', '', 1639822863684, NULL);
INSERT INTO `ims_sys_menu` VALUES ('af4c415540844cb08d4e7c01d43f6b71', '06d3853a1e9343728d1d29753ee972ad', '订单管理', 'menu', 'list', 'views/order/list', 0, 'data-pjax', NULL, 1, 'order.list', NULL, 16, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328344504, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328344504, 0);
INSERT INTO `ims_sys_menu` VALUES ('b10018aa630347b9b2fecb1c70701e88', '90d9f13a89b0495c924f431f17833111', '删除用户', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.user.delete', NULL, 49, '', '', 1639822807479, '', '', 1639822807479, NULL);
INSERT INTO `ims_sys_menu` VALUES ('b210b9e4793b41ba98cb75b9ae1ca72e', '359f4f895c664043939e8bd80d9a5758', '组织管理', 'menu', 'dept', 'views/system/dept', 0, 'data-pjax', NULL, 1, 'sys.dept', NULL, 36, '', '', 1639820899712, '', '', 1639820899712, NULL);
INSERT INTO `ims_sys_menu` VALUES ('b29a71d09c60475c8e8623873f6d9f3e', '', '财务', 'menu', 'finance', 'Layout', 0, 'data-pjax', 'finance', 1, 'finance', NULL, 20, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328483455, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328483455, 0);
INSERT INTO `ims_sys_menu` VALUES ('b409c62b8d504c8c89f588397aa36d5e', '90d9f13a89b0495c924f431f17833111', '重置密码', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.user.password', NULL, 50, '', '', 1640327548517, '', '', 1640327548517, NULL);
INSERT INTO `ims_sys_menu` VALUES ('b477df402a1d431ab33c8ef71c9476cc', '', '用户', 'menu', 'member', 'Layout', 0, 'data-pjax', 'member', 1, 'member', NULL, 0, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327753793, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327753793, 0);
INSERT INTO `ims_sys_menu` VALUES ('bff555bc8cb541f3b99259f37d223407', '55dd43dc1ef44890a3a297c186a7f22f', '幻灯片管理', 'menu', 'adv', 'views/platform/adv', 0, 'data-pjax', NULL, 1, 'platform.adv', NULL, 25, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327611093, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327611093, 0);
INSERT INTO `ims_sys_menu` VALUES ('c1279bc0845c4049a70059fe708b5e3a', '0f7e7e6618dc43d39b4d0bffd99382e1', '立即执行', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.task.run', NULL, 56, '', '', 1640329181899, '', '', 1640329181899, NULL);
INSERT INTO `ims_sys_menu` VALUES ('c322c9a13c1847a99c2d60db437a5cff', 'b477df402a1d431ab33c8ef71c9476cc', '用户等级', 'menu', 'level', 'views/member/level', 0, 'data-pjax', NULL, 1, 'member.level', NULL, 4, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327795760, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327795760, 0);
INSERT INTO `ims_sys_menu` VALUES ('cdbf51e9ace946a39f703717e4456cea', 'f6a75d6d079c4a5580b3d67450f34f0c', '菜单管理', 'menu', 'menu', 'views/system/menu', 0, 'data-pjax', NULL, 1, 'sys.menu', NULL, 60, '', '', 1639820956701, '', '', 1639820956701, NULL);
INSERT INTO `ims_sys_menu` VALUES ('ce9c18d4f9af43e98cb698673fb391d0', 'cdbf51e9ace946a39f703717e4456cea', '批量添加权限', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.menu.batch', NULL, 61, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327539072, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665327539072, 0);
INSERT INTO `ims_sys_menu` VALUES ('d22a59f338194145b210d6570e728b9b', '4c4e1778aa1341a883bf9b0b467aff5a', '删除标签', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.label.delete', NULL, 11, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497168, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383497168, 0);
INSERT INTO `ims_sys_menu` VALUES ('d24193c1f5cb438c8d27ecba8a39e738', '359f4f895c664043939e8bd80d9a5758', '角色管理', 'menu', 'role', 'views/system/role', 0, 'data-pjax', NULL, 1, 'sys.role', NULL, 41, '', '', 1639820927717, '', '', 1639820927717, NULL);
INSERT INTO `ims_sys_menu` VALUES ('d3596ec42306475bac563ef49563bbd3', '8ebabe59d484416f8cf3524f75e2d849', '商品管理', 'menu', 'list', 'views/goods/list', 0, 'data-pjax', NULL, 1, 'goods.list', NULL, 13, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328227935, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328263481, 0);
INSERT INTO `ims_sys_menu` VALUES ('d45b5d05165e4e7e9d5b37d24da0b398', 'd8ebbc55dbcf467dab3f4afceb055c34', '文章分类', 'menu', 'category', 'views/article/category', 0, 'data-pjax', NULL, 1, 'article.category', NULL, 19, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328652490, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328652490, 0);
INSERT INTO `ims_sys_menu` VALUES ('d8ebbc55dbcf467dab3f4afceb055c34', '', '文章', 'menu', 'article', 'Layout', 0, 'data-pjax', 'article', 1, 'article', NULL, 17, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328635073, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328635073, 0);
INSERT INTO `ims_sys_menu` VALUES ('dce3264e6dca44958d47a3c712067bb3', 'cdbf51e9ace946a39f703717e4456cea', '添加菜单', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.menu.create', NULL, 62, '', '', 1639823349988, '', '', 1639823349988, NULL);
INSERT INTO `ims_sys_menu` VALUES ('dcf39cd6a6474b8a8b4dc4f1bd0683bf', 'c322c9a13c1847a99c2d60db437a5cff', '修改等级', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.level.update', NULL, 6, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478687, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478687, 0);
INSERT INTO `ims_sys_menu` VALUES ('e186ecbe4d074a17a5c833e234bd917f', '0f7e7e6618dc43d39b4d0bffd99382e1', '删除任务', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.task.delete', NULL, 55, '', '', 1639892165364, '', '', 1639892165364, NULL);
INSERT INTO `ims_sys_menu` VALUES ('e5a782003638492c81b8d28b8b5a526f', '90d9f13a89b0495c924f431f17833111', '修改用户', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.user.update', NULL, 48, '', '', 1639822752244, '', '', 1639822752244, NULL);
INSERT INTO `ims_sys_menu` VALUES ('ecced3084db7473a975109f7675fbe6d', 'd24193c1f5cb438c8d27ecba8a39e738', '授权菜单', 'data', NULL, NULL, 0, 'data-pjax', NULL, 1, 'sys.role.menu', NULL, 45, '', '', 1639823496916, '', '', 1639823496916, NULL);
INSERT INTO `ims_sys_menu` VALUES ('f0a68e2ef4bf41f7be1ef8ef36214f58', 'c322c9a13c1847a99c2d60db437a5cff', '删除等级', 'data', NULL, NULL, 0, NULL, NULL, 1, 'member.level.delete', NULL, 7, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478692, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1667383478692, 0);
INSERT INTO `ims_sys_menu` VALUES ('f36e9d3cfbec4eb08e6e15a2fae6d63a', 'b29a71d09c60475c8e8623873f6d9f3e', '余额明细', 'menu', 'balance', 'views/finance/balance', 0, 'data-pjax', NULL, 1, 'finance.balance', NULL, 22, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328524094, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1665328524094, 0);
INSERT INTO `ims_sys_menu` VALUES ('f6a75d6d079c4a5580b3d67450f34f0c', '3a5e11dde1664201bda92ae55e989d6d', '系统设置', 'menu', 'config', 'views/system', 0, 'data-pjax', NULL, 1, 'sys.config', NULL, 59, '', '', 1640661114801, '', '', 1640661114801, NULL);
INSERT INTO `ims_sys_menu` VALUES ('f80d1735b00f43e3b4090797ee40c31d', '8c9aed13cb5046ca945d0b75f814ed67', '操作日志', 'menu', 'log', 'views/system/log', 0, 'data-pjax', NULL, 1, 'sys.log', NULL, 58, '', '', 1639821120893, '', '', 1639821120893, NULL);

-- ----------------------------
-- Table structure for ims_sys_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_blob_triggers`;
CREATE TABLE `ims_sys_qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  CONSTRAINT `ims_sys_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `ims_sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_calendars`;
CREATE TABLE `ims_sys_qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_cron_triggers`;
CREATE TABLE `ims_sys_qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `ims_sys_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `ims_sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_fired_triggers`;
CREATE TABLE `ims_sys_qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_15`(`SCHED_NAME` ASC, `INSTANCE_NAME` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_16`(`SCHED_NAME` ASC, `INSTANCE_NAME` ASC, `REQUESTS_RECOVERY` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_17`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_18`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_19`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_20`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_job_details`;
CREATE TABLE `ims_sys_qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_01`(`SCHED_NAME` ASC, `REQUESTS_RECOVERY` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_02`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_locks`;
CREATE TABLE `ims_sys_qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_locks
-- ----------------------------
INSERT INTO `ims_sys_qrtz_locks` VALUES ('QuartzScheduler', 'STATE_ACCESS');
INSERT INTO `ims_sys_qrtz_locks` VALUES ('QuartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for ims_sys_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_paused_trigger_grps`;
CREATE TABLE `ims_sys_qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_scheduler_state`;
CREATE TABLE `ims_sys_qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `ims_sys_qrtz_scheduler_state` VALUES ('QuartzScheduler', 'DESKTOP-5I47IOV1669546289456', 1669547230783, 20000);

-- ----------------------------
-- Table structure for ims_sys_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_simple_triggers`;
CREATE TABLE `ims_sys_qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `ims_sys_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `ims_sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_simprop_triggers`;
CREATE TABLE `ims_sys_qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `INT_PROP_1` int NULL DEFAULT NULL,
  `INT_PROP_2` int NULL DEFAULT NULL,
  `LONG_PROP_1` bigint NULL DEFAULT NULL,
  `LONG_PROP_2` bigint NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `ims_sys_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `ims_sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_qrtz_triggers`;
CREATE TABLE `ims_sys_qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PRIORITY` int NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_03`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_04`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_05`(`SCHED_NAME` ASC, `CALENDAR_NAME` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_06`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_07`(`SCHED_NAME` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_08`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_09`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_10`(`SCHED_NAME` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_11`(`SCHED_NAME` ASC, `TRIGGER_STATE` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_12`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_13`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_IMS_SYS_QRTZ_14`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  CONSTRAINT `ims_sys_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `ims_sys_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_role`;
CREATE TABLE `ims_sys_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名字',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '唯一编码',
  `disabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `location` int NULL DEFAULT 0 COMMENT '排序字段',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_role
-- ----------------------------
INSERT INTO `ims_sys_role` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '超级管理员', 'SUPER_ADMIN', 1, 0, '系统最高管理员,拥有所有权限', '', '', 1665327335364, '', '', 1665327335364, 0);
INSERT INTO `ims_sys_role` VALUES ('292d891323dc4a26b98880f246ae8f55', '管理员', 'ADMIN', 1, 0, '管理员权限', '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530459829, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530459829, 0);

-- ----------------------------
-- Table structure for ims_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_role_menu`;
CREATE TABLE `ims_sys_role_menu`  (
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menuId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_role_menu
-- ----------------------------
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '047822c687324b2182bd7bae1eaa06b3');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '0f7e7e6618dc43d39b4d0bffd99382e1');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '132ad919d3ed483cad5d1c4f3216cdb8');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '359f4f895c664043939e8bd80d9a5758');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '3a5e11dde1664201bda92ae55e989d6d');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '3efe1dbab27f483fb61803e00c891f54');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '4d1ccc0126cd461685e397740938a523');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '4ea9f2ad825647b694017356d4670593');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '5b8e241b46974e8383b12261a495efbf');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '5e569f106ce34d6f9a230962f8827b10');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '63d87113850f47f8ac9546689f64655d');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '7267b517d6eb4a28b9d531bb89772f29');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '768c8ef045524aa3a9d6330e93cdce97');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '7ebc8e5ef5654012bed1f4caf6471b84');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '83d93f46f9af431e8217f128b8dacca9');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '89a40a9d37fd400ab1f5e0ae4adbfffa');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '8c9aed13cb5046ca945d0b75f814ed67');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '90d9f13a89b0495c924f431f17833111');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '9fbe14a07eba439188a6b2f75ef35159');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'a11575ca032346e5824dda15e1754a14');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'a2ae7df38ec344b1bfd6c3f6cc8f0c20');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'a7c90cc3383c4c988475961b194621e6');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'abcd551142744b0b94ed932e95bcdb68');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'b10018aa630347b9b2fecb1c70701e88');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'b210b9e4793b41ba98cb75b9ae1ca72e');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'b409c62b8d504c8c89f588397aa36d5e');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'c1279bc0845c4049a70059fe708b5e3a');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'cdbf51e9ace946a39f703717e4456cea');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'd24193c1f5cb438c8d27ecba8a39e738');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'dce3264e6dca44958d47a3c712067bb3');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'e186ecbe4d074a17a5c833e234bd917f');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'e5a782003638492c81b8d28b8b5a526f');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'ecced3084db7473a975109f7675fbe6d');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'f6a75d6d079c4a5580b3d67450f34f0c');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'f80d1735b00f43e3b4090797ee40c31d');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'ce9c18d4f9af43e98cb698673fb391d0');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '55dd43dc1ef44890a3a297c186a7f22f');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'bff555bc8cb541f3b99259f37d223407');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '601d11debae943d6833bdc0654d796e2');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '93a2df92fe6743e0b7d9833c95c0713d');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '6d0d1bb8367d46abb32ba03a94db48c7');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '34b4edb7e1ea4a578d9d9f78d74e64ea');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'a1ec86029b2f4b789117698042a2e64a');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '2e1f9a56ea3846b4a932c79ab73db47e');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '0d1e8d392cb94723a5f91eb44566c86f');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '64691d716695497785087b8764e5536a');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'b477df402a1d431ab33c8ef71c9476cc');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '34ae2583fbe44961aeb1514bd8d03d09');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'c322c9a13c1847a99c2d60db437a5cff');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '8ebabe59d484416f8cf3524f75e2d849');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '73775b5595724be38ae1ccafcef1690d');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'd3596ec42306475bac563ef49563bbd3');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '06d3853a1e9343728d1d29753ee972ad');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'af4c415540844cb08d4e7c01d43f6b71');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '4c4e1778aa1341a883bf9b0b467aff5a');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'b29a71d09c60475c8e8623873f6d9f3e');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '5f04a86630024fb99f6a9744e87ea8fc');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'f36e9d3cfbec4eb08e6e15a2fae6d63a');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '7bc3bed2f327465284818142adfcc376');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'd8ebbc55dbcf467dab3f4afceb055c34');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'd45b5d05165e4e7e9d5b37d24da0b398');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '066c0b42655b4a3eb06404dc9a5cc6a8');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '03f39f264de54896a0c3312011759880');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'dcf39cd6a6474b8a8b4dc4f1bd0683bf');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'f0a68e2ef4bf41f7be1ef8ef36214f58');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '4b115ff4eba5408cac440cc491ec2817');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '41e4e6aa11d64ee3b9dd5418302afe19');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', 'd22a59f338194145b210d6570e728b9b');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'b477df402a1d431ab33c8ef71c9476cc');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '34ae2583fbe44961aeb1514bd8d03d09');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'c322c9a13c1847a99c2d60db437a5cff');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '03f39f264de54896a0c3312011759880');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'dcf39cd6a6474b8a8b4dc4f1bd0683bf');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'f0a68e2ef4bf41f7be1ef8ef36214f58');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '4c4e1778aa1341a883bf9b0b467aff5a');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '4b115ff4eba5408cac440cc491ec2817');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '41e4e6aa11d64ee3b9dd5418302afe19');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'd22a59f338194145b210d6570e728b9b');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '55dd43dc1ef44890a3a297c186a7f22f');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'bff555bc8cb541f3b99259f37d223407');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '601d11debae943d6833bdc0654d796e2');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '93a2df92fe6743e0b7d9833c95c0713d');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '6d0d1bb8367d46abb32ba03a94db48c7');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '34b4edb7e1ea4a578d9d9f78d74e64ea');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'a1ec86029b2f4b789117698042a2e64a');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '2e1f9a56ea3846b4a932c79ab73db47e');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '0d1e8d392cb94723a5f91eb44566c86f');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '64691d716695497785087b8764e5536a');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '3a5e11dde1664201bda92ae55e989d6d');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '359f4f895c664043939e8bd80d9a5758');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'b210b9e4793b41ba98cb75b9ae1ca72e');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'abcd551142744b0b94ed932e95bcdb68');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '63d87113850f47f8ac9546689f64655d');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '5e569f106ce34d6f9a230962f8827b10');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '7ebc8e5ef5654012bed1f4caf6471b84');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'd24193c1f5cb438c8d27ecba8a39e738');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '132ad919d3ed483cad5d1c4f3216cdb8');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'a11575ca032346e5824dda15e1754a14');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '4ea9f2ad825647b694017356d4670593');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'ecced3084db7473a975109f7675fbe6d');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '90d9f13a89b0495c924f431f17833111');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '89a40a9d37fd400ab1f5e0ae4adbfffa');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'e5a782003638492c81b8d28b8b5a526f');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'b10018aa630347b9b2fecb1c70701e88');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'b409c62b8d504c8c89f588397aa36d5e');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '8c9aed13cb5046ca945d0b75f814ed67');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '0f7e7e6618dc43d39b4d0bffd99382e1');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'a2ae7df38ec344b1bfd6c3f6cc8f0c20');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '047822c687324b2182bd7bae1eaa06b3');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'e186ecbe4d074a17a5c833e234bd917f');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'c1279bc0845c4049a70059fe708b5e3a');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '7267b517d6eb4a28b9d531bb89772f29');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'f80d1735b00f43e3b4090797ee40c31d');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'f6a75d6d079c4a5580b3d67450f34f0c');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', 'cdbf51e9ace946a39f703717e4456cea');
INSERT INTO `ims_sys_role_menu` VALUES ('292d891323dc4a26b98880f246ae8f55', '4d1ccc0126cd461685e397740938a523');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '4fb692712f92438e80af384ecfc8c5e7');
INSERT INTO `ims_sys_role_menu` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '23c4f25255e0490c9671e212aea9939b');

-- ----------------------------
-- Table structure for ims_sys_task
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_task`;
CREATE TABLE `ims_sys_task`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `jobClass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行类',
  `cron` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '定时规则',
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行参数',
  `exeAt` bigint NULL DEFAULT NULL COMMENT '执行时间',
  `exeResult` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行结果',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务说明',
  `disabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_task
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_task_log
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_task_log`;
CREATE TABLE `ims_sys_task_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `jobName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `jobGroup` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `invokeTarget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调用目标字符串',
  `jobMessage` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '日志信息',
  `status` int NULL DEFAULT 0 COMMENT '执行状态 （0正常 1失败）',
  `exceptionInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '异常信息',
  `startTime` bigint NULL DEFAULT NULL COMMENT '开始时间',
  `endTime` bigint NULL DEFAULT NULL COMMENT '结束时间',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_task_log
-- ----------------------------

-- ----------------------------
-- Table structure for ims_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_user`;
CREATE TABLE `ims_sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `username` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码盐',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `sex` tinyint NULL DEFAULT 0 COMMENT '性别(0-未知 1-男 2-女)',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生日',
  `expand` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '扩展字段',
  `online` tinyint(1) NULL DEFAULT NULL COMMENT '是否在线',
  `disabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `loginAt` bigint NULL DEFAULT NULL COMMENT '最后登录时间',
  `loginIp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `loginLocation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录地点',
  `loginBrowser` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `loginOs` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
  `loginCount` int NULL DEFAULT 0 COMMENT '登陆次数',
  `deptId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组织',
  `createdById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建ID',
  `createdBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdAt` bigint NULL DEFAULT NULL COMMENT '创建时间',
  `updatedById` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `updatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `updatedAt` bigint NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_user
-- ----------------------------
INSERT INTO `ims_sys_user` VALUES ('6841c29452984878ac47b1b86195f46e', 'superadmin', 'dd02eeb82a6d4084fde09796915afbb79f7a4d0afd3ed211d52ab1a736e91ade', '61ho2vejfah1pq6dh781jds0t5', '超级管理员', NULL, NULL, NULL, 0, NULL, NULL, 1, 1, '超级管理员,拥有所有操作权限 ^_^', 1669546291049, '127.0.0.1', ' 本机地址', 'Chrome_107.0.0.0', 'Windows_10.0', 8, 'aeef6ef34bb34f7baada18366848b705', '', '', 1665327335370, '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669546291178, 0);
INSERT INTO `ims_sys_user` VALUES ('b4d92f00784c493bb23f88e55b88a448', 'admin', '75b48811a9aafd273dd8735a25991f77975eaccf8692544ad9554706667fca9b', '7s2971j8hkgriovg7fon4vnqr0', '管理员', NULL, NULL, NULL, 0, NULL, NULL, 1, 1, '管理员账号', 1669533252777, '118.112.114.21', '四川省成都市 电信', 'MicroMessenger_8.0.30', 'iPhone_16_1_1', 2, 'aeef6ef34bb34f7baada18366848b705', '6841c29452984878ac47b1b86195f46e', '超级管理员', 1669530532250, 'b4d92f00784c493bb23f88e55b88a448', '管理员', 1669533252865, 0);

-- ----------------------------
-- Table structure for ims_sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_user_dept`;
CREATE TABLE `ims_sys_user_dept`  (
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deptId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_user_dept
-- ----------------------------
INSERT INTO `ims_sys_user_dept` VALUES ('6841c29452984878ac47b1b86195f46e', 'aeef6ef34bb34f7baada18366848b705');

-- ----------------------------
-- Table structure for ims_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ims_sys_user_role`;
CREATE TABLE `ims_sys_user_role`  (
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ims_sys_user_role
-- ----------------------------
INSERT INTO `ims_sys_user_role` VALUES ('2058e15fecc14f4f8270fc8f6fc0b250', '6841c29452984878ac47b1b86195f46e');
INSERT INTO `ims_sys_user_role` VALUES ('292d891323dc4a26b98880f246ae8f55', 'b4d92f00784c493bb23f88e55b88a448');

SET FOREIGN_KEY_CHECKS = 1;
