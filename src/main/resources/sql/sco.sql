/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : sco

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 19/09/2019 14:02:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_note
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note`  (
  `id` int(2) NOT NULL AUTO_INCREMENT COMMENT '备注表',
  `note` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '1老师/2毕业生/3在校4考核',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '备注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_note
-- ----------------------------
INSERT INTO `t_note` VALUES (1, '老师');
INSERT INTO `t_note` VALUES (2, '毕业生');
INSERT INTO `t_note` VALUES (3, '在校生');
INSERT INTO `t_note` VALUES (4, '考核中');

-- ----------------------------
-- Table structure for t_orientation
-- ----------------------------
DROP TABLE IF EXISTS `t_orientation`;
CREATE TABLE `t_orientation`  (
  `id` int(2) NOT NULL AUTO_INCREMENT COMMENT '方向ID',
  `orientation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方向名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '方向表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_orientation
-- ----------------------------
INSERT INTO `t_orientation` VALUES (1, '后台开发');
INSERT INTO `t_orientation` VALUES (2, '前端设计');
INSERT INTO `t_orientation` VALUES (3, 'UI设计');
INSERT INTO `t_orientation` VALUES (4, '网络安全');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL,
  `available` bit(1) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `resource_type` enum('menu','button') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, b'0', '用户管理', 'userInfo:view', 'menu', 'userInfo/list');
INSERT INTO `t_permission` VALUES (2, b'0', '用户添加', 'userInfo:add', 'button', 'user/add');
INSERT INTO `t_permission` VALUES (3, b'0', '用户删除', 'user:del', 'button', 'user/delete');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(2) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '1为超管,2为普管，3没有权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员');
INSERT INTO `t_role` VALUES (2, '普通管理员');
INSERT INTO `t_role` VALUES (3, '成员');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_role_role_foreign_key`(`role_id`) USING BTREE,
  INDEX `t_permission_permission_id_foreign_key`(`permission_id`) USING BTREE,
  CONSTRAINT `t_permission_permission_id_foreign_key` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_role_role_foreign_key` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1, 1);
INSERT INTO `t_role_permission` VALUES (2, 2, 1);
INSERT INTO `t_role_permission` VALUES (3, 3, 1);
INSERT INTO `t_role_permission` VALUES (4, 3, 2);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userUUID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成员ID',
  `stuID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成员学号/老师可用手机号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `role` int(2) NULL DEFAULT NULL COMMENT '1为超管,2为普管，3没有权限',
  PRIMARY KEY (`userUUID`) USING BTREE,
  INDEX `权限`(`role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('084560fb042b40d9b28c9e41b213d3c3', '20180404930001', 'd022646351048ac0ba397d12dfafa304', 3);
INSERT INTO `t_user` VALUES ('0a55f84521df4ce99a1ac9b6218c7d15', '20190404900002', 'd022646351048ac0ba397d12dfafa304', 1);
INSERT INTO `t_user` VALUES ('19ef853e4b3e4ae49de26a343f87849e', '20180409022102', 'd022646351048ac0ba397d12dfafa304', 3);
INSERT INTO `t_user` VALUES ('2e2037332ce14ef6b0029e97b01dcdc1', '20170404466666', 'd022646351048ac0ba397d12dfafa304', 1);
INSERT INTO `t_user` VALUES ('36e431a305f04014b8fa3097e9aec42a', '20190404830221', 'd022646351048ac0ba397d12dfafa304', 3);
INSERT INTO `t_user` VALUES ('3c8d8f1e675b42b1ac1088639a53e480', '20190404430318', 'd022646351048ac0ba397d12dfafa304', 3);
INSERT INTO `t_user` VALUES ('4a8d3a2d183e44a3b9bffdc26f7837b8', '20190406430302', 'd022646351048ac0ba397d12dfafa304', 3);
INSERT INTO `t_user` VALUES ('5773991f4af74d84b0aa03db50ba4b4d', '20180408430232', 'd022646351048ac0ba397d12dfafa304', 2);
INSERT INTO `t_user` VALUES ('57c98c8043304fab98314989c60a6f03', '20190404900003', 'd022646351048ac0ba397d12dfafa304', 2);
INSERT INTO `t_user` VALUES ('75192452592946699586e049c682fb2e', '20190408430203', 'd022646351048ac0ba397d12dfafa304', 2);
INSERT INTO `t_user` VALUES ('8027505d11044c86a4a471e9c8c2e410', '20190409430212', 'd022646351048ac0ba397d12dfafa304', 2);
INSERT INTO `t_user` VALUES ('93762a6a6dd04743a0eae2287695b6c8', '20170404430302', 'd022646351048ac0ba397d12dfafa304', 2);
INSERT INTO `t_user` VALUES ('95e076b7c0ba4848b75caed82ee5d91e', '20190404430333', 'd022646351048ac0ba397d12dfafa304', 2);
INSERT INTO `t_user` VALUES ('96611473753141dfb7d173854aa98a38', '20190406630115', 'd022646351048ac0ba397d12dfafa304', 1);
INSERT INTO `t_user` VALUES ('9a7ecec29d7d41abb2494e34380f4f8d', '20190408830111', 'd022646351048ac0ba397d12dfafa304', 1);
INSERT INTO `t_user` VALUES ('bc1c3bcb622d4bd19f06059cab76a965', '20190407430309', 'd022646351048ac0ba397d12dfafa304', 1);
INSERT INTO `t_user` VALUES ('c701f7843a564b6587abbae17508f6ec', '20170406830207', 'd022646351048ac0ba397d12dfafa304', 1);
INSERT INTO `t_user` VALUES ('d481721220d4478b8ffb9945e65683a6', '20170404430222', 'd022646351048ac0ba397d12dfafa304', 1);
INSERT INTO `t_user` VALUES ('edc54b51bb374e5cb608026642170919', '20190406430116', 'd022646351048ac0ba397d12dfafa304', 3);
INSERT INTO `t_user` VALUES ('f97ac97090924f3d856b373d7c6d0523', '20170404430208', 'd022646351048ac0ba397d12dfafa304', 1);

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `userInfoUUID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成员信息ID',
  `userUUID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成员ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成员名字',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `edu_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教务系统密码',
  `orientation` int(2) NULL DEFAULT NULL COMMENT '方向',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别. 1:男	0:女',
  `icon` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '头像',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `speciality` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '特长',
  `note` int(2) NULL DEFAULT NULL COMMENT '1老师/2毕业生/3在校4考核',
  PRIMARY KEY (`userInfoUUID`) USING BTREE,
  INDEX `备注`(`note`) USING BTREE,
  INDEX `方向`(`orientation`) USING BTREE,
  INDEX `用户的ID`(`userUUID`) USING BTREE,
  CONSTRAINT `t_userInfo_userUuid_froeign_key` FOREIGN KEY (`userUUID`) REFERENCES `t_user` (`userUUID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户详情信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('020018478060425294a10023793ed281', '3c8d8f1e675b42b1ac1088639a53e480', '张三', 'zs.yml@gmail.com', 'zhangsan666', 4, 1, '', '18719256668', 'My name is zhangsan', 'Java', 1);
INSERT INTO `t_user_info` VALUES ('04385a4968ba496e881f6c6b4749d002', '8027505d11044c86a4a471e9c8c2e410', '肖柳', 'li.si@gmail.com', '123', 2, 0, '/assets/images/upload/bb43ee54-f7d5-4e8e-9e50-edf359ca842d.png', '15806950609', 'lisi', 'C++', 1);
INSERT INTO `t_user_info` VALUES ('1219813778c9470e8e430de267cdb968', 'f97ac97090924f3d856b373d7c6d0523', '陈岳欣', 'chachae@qq.com', '6666', 1, 1, '', '13670459539', 'I AM CHACHAE', 'Java', 3);
INSERT INTO `t_user_info` VALUES ('122c874ddbf24fc785d05b460ab4cd7b', '9a7ecec29d7d41abb2494e34380f4f8d', '赵六', 'zhao666.src@qq.com', 'asdf123', 4, 1, NULL, '15156859602', '我叫赵6', 'Python', 3);
INSERT INTO `t_user_info` VALUES ('14082c7f75a14fe186850219d764f666', '2e2037332ce14ef6b0029e97b01dcdc1', '邹建林', 'niubi@qq.com', '123123', 2, 1, '', '15815133332', '牛逼牛逼牛逼', 'python', 3);
INSERT INTO `t_user_info` VALUES ('197d225907f34d42910c9cc57453feed', '19ef853e4b3e4ae49de26a343f87849e', '肖启宇', 'leengg@126.com', 'cus7802s', 3, 1, '/assets/images/upload/a84382fc-2333-4b8a-adf5-935cca0a0a62.png', '15815122822', 'li ning', 'Java', 3);
INSERT INTO `t_user_info` VALUES ('3369741d99974770862930e3c3a21901', '084560fb042b40d9b28c9e41b213d3c3', '张大强', 'zarqar@foxmail.com', 'zqiang9226', 4, 1, '', '13602489226', 'zq110', 'Python', 3);
INSERT INTO `t_user_info` VALUES ('48d7a985fcf34950985b13744eb4fc41', 'c701f7843a564b6587abbae17508f6ec', '蔡泽航', 'zhangyu@gmail.com', 'zhangyu45', 2, 1, '', '17133049567', 'ZY', 'Go', 3);
INSERT INTO `t_user_info` VALUES ('631039d33e11446982dacc921522240e', '5773991f4af74d84b0aa03db50ba4b4d', '郑琬婷', 'zw.teen@yahoo.com', 'zwt8760', 3, 0, NULL, '13612312739', 'I am zwleen', 'JavaScript', 3);
INSERT INTO `t_user_info` VALUES ('637aab9d02904d0b8f00d8138849d617', '93762a6a6dd04743a0eae2287695b6c8', '陈薇', 'chen.vea@126.com', 'cesnxi870', 3, 0, '/assets/images/upload/3bbc3869-51af-4926-ae0a-b0601469c78c.png', '13677607767', 'HELLO', 'Java', 3);
INSERT INTO `t_user_info` VALUES ('a40c59e7a53f4946979bca277a14750d', '75192452592946699586e049c682fb2e', '李钟攀', 'jz.lee@hnbmc.com', 'leekingze567', 1, 1, '/assets/images/upload/a491b5ca-0a06-464a-8617-ae77be7e9cab.png', '15816022222', '我是谁', 'C#', 1);
INSERT INTO `t_user_info` VALUES ('c27a580150734d119a5370b8c38410ef', '0a55f84521df4ce99a1ac9b6218c7d15', '陈齐铭', 'chen.7m@foxmail.com', 'zm123', 3, 1, '', '17013456700', '红花会', 'Java', 1);
INSERT INTO `t_user_info` VALUES ('ccf15c2e70324ddb8a93b0f709a779d0', '36e431a305f04014b8fa3097e9aec42a', '李苏', 'ly.dgbm@126.com', 'thxy', 1, 0, '', '15988775467', 'Hello World', 'Go', 3);
INSERT INTO `t_user_info` VALUES ('cf0f3093dae240ce993ef154e60a9454', '95e076b7c0ba4848b75caed82ee5d91e', '马茗挺', 'mva.ma@163.com', 'yunke', 4, 1, '/assets/images/upload/6f56c972-f7f9-4f33-a0ff-1b111597bbd3.png', '15829665778', 'Java Good', 'Swift', 3);
INSERT INTO `t_user_info` VALUES ('d9a00f39c0454889b7619f3f3e62782b', 'edc54b51bb374e5cb608026642170919', '方田羽', 'lffeexo@foxmail.com', 'yunke123', 1, 0, '', '13014945685', 'why？', 'Swift', 3);
INSERT INTO `t_user_info` VALUES ('dc1092deab7347258912002e9ed98082', '57c98c8043304fab98314989c60a6f03', '周静', 'jen.chou@gmail.com', '593zjing', 2, 0, '', '13068977125', 'Chou', 'Go', 3);
INSERT INTO `t_user_info` VALUES ('ddd98ecb5f1843dabf02088831bd3951', '96611473753141dfb7d173854aa98a38', '张泽', 'zzeeivlae@126.com', 'zhngz765', 3, 1, '', '13755623083', 'I am zhangze', 'Python', 3);
INSERT INTO `t_user_info` VALUES ('f236a3fd79eb411ab56ff0f720f61279', '4a8d3a2d183e44a3b9bffdc26f7837b8', '王昊', 'wh@hnbmc.com', 'wanghao8910', 2, 1, NULL, '13710029697', 'who？', 'C', 4);
INSERT INTO `t_user_info` VALUES ('fee33c6441bf4e3ab2509e440b0761a7', 'bc1c3bcb622d4bd19f06059cab76a965', '谢小七', 'xee.sa7@qq.com', 'gdong123', 3, 0, NULL, '15125478435', '我是谁？', 'Go', 3);
INSERT INTO `t_user_info` VALUES ('ff3170b7a15d4cc0b548d9486efe6d0e', 'd481721220d4478b8ffb9945e65683a6', '宋利杰', 'qunsong@yahoo.com.hk', 'jlen1849', 1, 0, '/assets/images/upload/604e05fd-dabd-4c19-bcc6-25fac34bf681.png', '13628710028', 'hello World', 'Java', 3);

SET FOREIGN_KEY_CHECKS = 1;
