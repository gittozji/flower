-- ----------------------------
-- 用户
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nikename` varchar(12) DEFAULT NULL COMMENT '昵称',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型【0：客户，1：管理员】',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态【保留字段】',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 管理员详细信息
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '账号',
  `vc_netno` varchar(9) DEFAULT NULL COMMENT '网点编号',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_admininfo` (`username`),
  CONSTRAINT `fk_admininfo_user_id` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 角色
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(200) NOT NULL COMMENT '代码',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_role` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 用户-角色
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户',
  `role_id` int(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_user_role_user_id` (`user_id`, `role_id`),
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 产品信息表
-- ----------------------------
DROP TABLE IF EXISTS `flower`;
CREATE TABLE `flower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_name` varchar(64) DEFAULT NULL COMMENT '产品名称（花名）',
  `en_price` decimal(19,2) DEFAULT 0 COMMENT '价格',
  `l_count` int DEFAULT 0 COMMENT '数量',
  `vc_fid` varchar(50) DEFAULT 0 COMMENT '图片id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `flower` (`vc_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 客户信息表
-- ----------------------------
DROP TABLE IF EXISTS `custinfo`;
CREATE TABLE `custinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL COMMENT '账号',
  `vc_custno` varchar(18) NOT NULL COMMENT '客户编号',
  `c_custtype` char(1) NOT NULL COMMENT '客户类型【0：机构，1：个人】',
  `vc_custname` varchar(64) NOT NULL COMMENT '客户名称',
  `vc_identityno` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `vc_opendate` varchar(10) DEFAULT NULL COMMENT '增开时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_vc_custno_idx` (`vc_custno`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 订单表
-- ----------------------------
DROP TABLE IF EXISTS `orderform`;
CREATE TABLE `orderform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `flower_id` int(11) NOT NULL COMMENT '产品id',
  `l_count` int NOT NULL COMMENT '数量',
  `l_state` int NOT NULL COMMENT '状态【0：现金未付款，2：线上未付款，3：线上已付款，4：派送中，40：现金未支付派送中，5：已取消，6：订单结束】',
  `vc_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_orderform_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_orderform_flower` FOREIGN KEY (`flower_id`) REFERENCES `flower` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 用户附加表
-- ----------------------------
DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户主表id',
  `vc_address` varchar(60) COMMENT '地址',
  `vc_tel` varchar(11) COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_user_detail` (`user_id`),
  CONSTRAINT `fk_user_detail` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 用户
-- ----------------------------
INSERT INTO `user`(username, password, nikename, type) VALUES ('0000', 'Sn0e1BRHTkAzrCnMuGU9mw==', '开发者', 1);

-- ----------------------------
-- 管理员用户
-- ----------------------------
INSERT INTO `admininfo`(username) VALUES ('0000');

-- ----------------------------
-- 角色
-- ----------------------------
INSERT INTO `role` VALUES (null, 'custom', '客户');
INSERT INTO `role` VALUES (null, 'admin', '管理业务流程');
INSERT INTO `role` VALUES (null, 'admin_process', '管理业务流程');
INSERT INTO `role` VALUES (null, 'admin_maintain', '管理业务维护');
INSERT INTO `role` VALUES (null, 'admin_trade', '管理业务交易');
INSERT INTO `role` VALUES (null, 'admin_user', '管理账号信息');

-- ----------------------------
-- 角色表
-- ----------------------------
INSERT INTO `user_role` VALUES (null, '1', '2');
INSERT INTO `user_role` VALUES (null, '1', '3');
INSERT INTO `user_role` VALUES (null, '1', '4');
INSERT INTO `user_role` VALUES (null, '1', '5');
INSERT INTO `user_role` VALUES (null, '1', '6');