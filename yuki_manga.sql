/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : yuki_library

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 27/05/2024 14:35:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_bookinfo`;
CREATE TABLE `sys_bookinfo`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uploadData` datetime(0) NULL DEFAULT NULL,
  `review` int NULL DEFAULT 0 COMMENT '评分,(1-5)分，1分最低，5分最高',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upload_by` bigint NOT NULL COMMENT '上传者的id',
  `tags` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isadult` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_bookinfo_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_bookinfo
-- ----------------------------
INSERT INTO `sys_bookinfo` VALUES (1, 'Anmi杂图1', 'Anmi', '2022-11-23 16:38:59', 5, 'Anmi杂图1', 1, '全彩,CG,', 0);
INSERT INTO `sys_bookinfo` VALUES (2, 'Hiten杂图1', 'Hiten', '2022-11-23 16:41:36', 5, 'Hiten杂图1', 1, '全彩,CG,', 0);
INSERT INTO `sys_bookinfo` VALUES (27, '留年生ちゃん', 'ろむむ', '2022-11-25 20:28:02', 0, '留年生ちゃん', 1, '全彩,CG,白毛', 0);
INSERT INTO `sys_bookinfo` VALUES (28, 'TwinBox杂图', 'TwinBox', '2022-11-27 01:44:38', 0, 'TwinBox杂图', 1, '全彩,CG', 0);
INSERT INTO `sys_bookinfo` VALUES (34, 'keta杂图', 'keta', '2022-11-30 16:26:13', 0, 'keta杂图', 1, '东方,全彩,CG', 0);
INSERT INTO `sys_bookinfo` VALUES (36, '(AC3)(Anmi)Avian Romance PINK LABEL 8 (オリジナル)', 'Anmi', '2022-11-30 18:56:37', 0, '(AC3)(Anmi)Avian Romance PINK LABEL 8 (オリジナル)', 1, 'CG,全彩,少女,芭蕾', 0);
INSERT INTO `sys_bookinfo` VALUES (37, '(C92)(Anmi) Avian Romance PINK LABEL 2', 'Anmi', '2022-12-04 08:29:29', 0, '(C92)(Anmi) Avian Romance PINK LABEL 2', 1, 'CG,全彩,少女,泳装', 0);
INSERT INTO `sys_bookinfo` VALUES (38, '(C91)(Anmi) Avian Romance PINK LABEL', 'Anmi', '2022-12-04 08:31:52', 0, '(C91)(Anmi) Avian Romance PINK LABEL', 1, 'CG,全彩,少女,萌', 0);
INSERT INTO `sys_bookinfo` VALUES (39, '(C93)(Anmi) Avian Romance PINK LABEL 3', 'Anmi', '2022-12-04 08:33:38', 0, '(C93)(Anmi) Avian Romance PINK LABEL 3', 1, 'CG,全彩,泳装,少女,旗袍', 0);
INSERT INTO `sys_bookinfo` VALUES (40, '(C94)(Anmi)Avian Romance PINK LABEL 4', 'Anmi', '2022-12-04 08:45:48', 0, '(C94)(Anmi)Avian Romance PINK LABEL 4', 1, 'CG,全彩,泳装,浴衣,乐队', 0);
INSERT INTO `sys_bookinfo` VALUES (41, '(C95)(Anmi)Avian Romance PINK LABEL 5', 'Anmi', '2022-12-04 13:43:41', 0, '(C95)(Anmi)Avian Romance PINK LABEL 5', 1, 'CG,全彩,百合,少女', 0);
INSERT INTO `sys_bookinfo` VALUES (42, '(C96)(Anmi)Avian Romance PINK LABEL 6', 'Anmi', '2022-12-04 13:48:30', 0, '(C96)(Anmi)Avian Romance PINK LABEL 6', 1, '全彩,CG,少女,百合', 0);
INSERT INTO `sys_bookinfo` VALUES (43, '(C97)(Anmi)Avian Romance PINK LABEL 7', 'Anmi', '2022-12-04 14:00:50', 0, '(C97)(Anmi)Avian Romance PINK LABEL 7', 1, '全彩,CG,少女', 0);

-- ----------------------------
-- Table structure for sys_manga_progress
-- ----------------------------
DROP TABLE IF EXISTS `sys_manga_progress`;
CREATE TABLE `sys_manga_progress`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` bigint NOT NULL,
  `mangaId` bigint NOT NULL,
  `progress` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_manga_progress
-- ----------------------------
INSERT INTO `sys_manga_progress` VALUES (1, 1, 1, '2');
INSERT INTO `sys_manga_progress` VALUES (2, 1, 2, '1');
INSERT INTO `sys_manga_progress` VALUES (3, 1, 43, '1');
INSERT INTO `sys_manga_progress` VALUES (4, 1, 41, '1');
INSERT INTO `sys_manga_progress` VALUES (5, 1, 42, '5');
INSERT INTO `sys_manga_progress` VALUES (6, 1, 36, '3');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `perm` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_menu_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '删除漫画', 'sys:manga:delete');
INSERT INTO `sys_menu` VALUES (2, '查看漫画', 'sys:manga:list');
INSERT INTO `sys_menu` VALUES (3, '内部测试', 'sys:test');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_menu_role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES (1, 1, 1);
INSERT INTO `sys_menu_role` VALUES (2, 1, 2);
INSERT INTO `sys_menu_role` VALUES (3, 2, 1);
INSERT INTO `sys_menu_role` VALUES (4, 2, 2);
INSERT INTO `sys_menu_role` VALUES (5, 1, 3);

-- ----------------------------
-- Table structure for sys_public_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_public_article`;
CREATE TABLE `sys_public_article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int NOT NULL,
  `created` date NULL DEFAULT NULL,
  `status` int NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_public_article_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_public_article
-- ----------------------------
INSERT INTO `sys_public_article` VALUES (1, '# About\n\n<br>\n\n## Yuki-manga\n<br>\n\n### 技术栈:\n\n<br>\n\n##### 后端\n  - SpringBoot\n  - SpringSecurity\n  - Mybatis  - jwt\n  - redis\n  - Mysql\n  - lombok\n\n##### 前端\n - Vue\n - axios\n - Element Ui\n', 'about', 1, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色的标识符',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明该身份可以做什么事情',
  `createDate` date NOT NULL,
  `updated` date NOT NULL,
  `state` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '拥有管理该网站的一切权限', '2022-11-14', '2022-11-14', 1);
INSERT INTO `sys_role` VALUES (2, '普通用户', 'normal', '普通用户,可以阅读,上传图书,为图书评分,添加收藏', '2022-11-14', '2022-11-14', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int NOT NULL,
  `state` int NOT NULL DEFAULT 1,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `updateDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'yuki', '$2a$10$e2lIQjAnUqz2B2Zrp2arYu/mr94/UU2pgxtMl3uKklKjSdi8UhMN6', '2004-03-25', 'bzhnb@gmail.com', 'https://s2.loli.net/2022/10/23/wXGlZb21iDnkQcO.jpg', 1, 1, '2022-11-14 00:00:00', NULL);
INSERT INTO `sys_user` VALUES (3, 'user', '$2a$10$e2lIQjAnUqz2B2Zrp2arYu/mr94/UU2pgxtMl3uKklKjSdi8UhMN6', '2022-11-13', 'user@163.com', 'https://s2.loli.net/2022/11/03/ZbjQJeDhzENP9Os.png', 2, 1, '2022-11-14 00:00:00', NULL);
INSERT INTO `sys_user` VALUES (4, 'test', '$2a$10$O418xtLim/V6W1zARLZ99.qmexZ0NNyiY9pHENnPos01aXaPr2Pey', '2004-03-24', '123123test@qq.com', 'https://s2.loli.net/2022/11/10/ibBrTgcAfwzURvM.jpg', 2, 1, '2022-11-18 09:05:04', '2022-11-21 00:00:00');
INSERT INTO `sys_user` VALUES (6, 'bzh666', '$2a$10$5D2mhly.y6zO.LbLJjJbJOjnD0JbmEfq8JRPlREbqUIvibKJQkzNy', NULL, NULL, NULL, 2, 1, '2023-09-05 10:11:42', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 3, 2);

SET FOREIGN_KEY_CHECKS = 1;
