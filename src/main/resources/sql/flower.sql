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