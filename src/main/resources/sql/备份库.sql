/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : fund_trade

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-03-16 19:11:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admininfo`
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO `admininfo` VALUES ('1', '0000', null, null, null, null);

-- ----------------------------
-- Table structure for `bankaccoinfo`
-- ----------------------------
DROP TABLE IF EXISTS `bankaccoinfo`;
CREATE TABLE `bankaccoinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_name` varchar(64) NOT NULL COMMENT '类型名字',
  `vc_personname` varchar(64) NOT NULL COMMENT '开户人名称',
  `vc_bankname` varchar(60) NOT NULL COMMENT '银行名称',
  `vc_bankacco` varchar(28) NOT NULL COMMENT '银行卡号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_bkaccoinfo_idx` (`vc_bankacco`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bankaccoinfo
-- ----------------------------
INSERT INTO `bankaccoinfo` VALUES ('1', '系统资金清算账户', '开发者', '中国工商', '100000');
INSERT INTO `bankaccoinfo` VALUES ('2', '系统辅助资金清算账户', '开发者', '中国建设', '100001');

-- ----------------------------
-- Table structure for `custinfo`
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custinfo
-- ----------------------------
INSERT INTO `custinfo` VALUES ('1', null, '000000000000000000', '0', '第一客户', '0001', '0001', '', '', '', '2017-03-16');

-- ----------------------------
-- Table structure for `day`
-- ----------------------------
DROP TABLE IF EXISTS `day`;
CREATE TABLE `day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_date` varchar(10) NOT NULL DEFAULT '' COMMENT '日期',
  `l_workflag` int(1) DEFAULT '0' COMMENT '状态【0：工作日，1：休息日】',
  PRIMARY KEY (`id`,`vc_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of day
-- ----------------------------

-- ----------------------------
-- Table structure for `deal_process`
-- ----------------------------
DROP TABLE IF EXISTS `deal_process`;
CREATE TABLE `deal_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_procedurcode` varchar(40) NOT NULL COMMENT '代码',
  `vc_name` varchar(40) NOT NULL COMMENT '名称',
  `l_state` int(1) DEFAULT '0' COMMENT '状态【0：未执行，1：执行中，2：执行完毕】',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_deal_process_id` (`vc_procedurcode`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deal_process
-- ----------------------------
INSERT INTO `deal_process` VALUES ('1', 'dayinit', '日初始化', '2');
INSERT INTO `deal_process` VALUES ('2', 'receivemarket', '接收行情', '2');
INSERT INTO `deal_process` VALUES ('3', 'starttuxedo', '启动交易', '2');
INSERT INTO `deal_process` VALUES ('4', 'downtuxedo', '停止柜台交易', '0');
INSERT INTO `deal_process` VALUES ('5', 'checkdata', '交易预处理', '0');
INSERT INTO `deal_process` VALUES ('6', 'exprequest', '导出申请数据', '0');
INSERT INTO `deal_process` VALUES ('7', 'importdata', '导入确认数据 ', '2');
INSERT INTO `deal_process` VALUES ('8', 'dealdata', '处理确认数据', '2');
INSERT INTO `deal_process` VALUES ('9', 'liqcarryover', '清算结转', '0');

-- ----------------------------
-- Table structure for `dynamicproductinfo`
-- ----------------------------
DROP TABLE IF EXISTS `dynamicproductinfo`;
CREATE TABLE `dynamicproductinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_productcode` varchar(6) NOT NULL COMMENT '产品代码',
  `en_stnav` decimal(9,4) DEFAULT NULL COMMENT '净值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_dynamicproductinfo_idx` (`vc_productcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamicproductinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `netstation`
-- ----------------------------
DROP TABLE IF EXISTS `netstation`;
CREATE TABLE `netstation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_netno` varchar(9) NOT NULL COMMENT '编号',
  `vc_netname` varchar(64) DEFAULT NULL COMMENT '名称',
  `vc_address` varchar(60) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sale_inetstation` (`vc_netno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of netstation
-- ----------------------------
INSERT INTO `netstation` VALUES ('1', '0001', '境内网点', '中国境内');

-- ----------------------------
-- Table structure for `product_info`
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_productcode` varchar(6) DEFAULT NULL COMMENT '产品代码',
  `vc_productname` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',
  `c_productcategory` char(1) NOT NULL COMMENT '产品类别【0：公募，1：私募，2：专户，3：集合理财】',
  `c_productoperation` char(1) NOT NULL COMMENT '运作方式【0：开放式，1：封闭式，2：ETF,3：LOF】',
  `c_investregion` char(1) NOT NULL COMMENT '投资区域【0：境内，1：境外】',
  `c_investdirection` char(1) NOT NULL COMMENT '投资方向【0：股票，1：指数，2：货币，3：保本，4：混合，5：短期理财】',
  `c_moneytype` varchar(3) NOT NULL COMMENT '币种【105：人民币，344：港币，840美元，978：欧元】',
  `en_manageratio` decimal(5,4) DEFAULT NULL COMMENT '管理费率',
  `c_chargetype` char(1) NOT NULL COMMENT '收费类型【0：前收费，1：后收费，2：水平收费】',
  `c_productstatus` char(1) NOT NULL COMMENT '产品状态【0：认购，1：申购，2：基金终止】',
  `c_productrisklevel` char(1) DEFAULT NULL COMMENT '风险水平【0：低，1：中，2：高】',
  `vc_bankacco` varchar(28) DEFAULT '' COMMENT 'Ta清算账户',
  `c_dividendmethod` char(1) DEFAULT NULL COMMENT '分红方式【0：红利再投，1：现金红利】',
  `vc_issuestartdate` varchar(10) DEFAULT NULL COMMENT '成立日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sale_iproductinfo` (`vc_productcode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1', '0001', '第一基金（认购期）', '0001', '0', '0', '0', '0', '105', '0.0100', '0', '0', '0', '100000', '0', '2017-03-16');
INSERT INTO `product_info` VALUES ('2', '0002', '第二基金（申购期）', '0001', '0', '0', '0', '0', '105', '0.0200', '0', '1', '0', '100000', '0', '2017-03-16');
INSERT INTO `product_info` VALUES ('3', '0003', '第三基金（终止期）', '0001', '0', '0', '0', '0', '105', '0.0300', '0', '2', '0', '100000', '0', '2017-03-16');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(200) NOT NULL COMMENT '代码',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_role` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'custom', '客户');
INSERT INTO `role` VALUES ('2', 'admin', '管理业务流程');
INSERT INTO `role` VALUES ('3', 'admin_process', '管理业务流程');
INSERT INTO `role` VALUES ('4', 'admin_maintain', '管理业务维护');
INSERT INTO `role` VALUES ('5', 'admin_trade', '管理业务交易');
INSERT INTO `role` VALUES ('6', 'admin_user', '管理账号信息');

-- ----------------------------
-- Table structure for `staticshare`
-- ----------------------------
DROP TABLE IF EXISTS `staticshare`;
CREATE TABLE `staticshare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_productcode` varchar(6) NOT NULL COMMENT '产品代码',
  `vc_taacco` varchar(12) NOT NULL COMMENT '基金账号',
  `en_share` decimal(19,2) DEFAULT '0.00' COMMENT '份额【总份额=可用份额+冻结份额】',
  `en_enshare` decimal(19,2) DEFAULT NULL COMMENT '可用份额',
  `en_imshare` decimal(19,2) DEFAULT NULL COMMENT '冻结份额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_staticshare_idx` (`vc_productcode`,`vc_taacco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staticshare
-- ----------------------------

-- ----------------------------
-- Table structure for `statictradebalance`
-- ----------------------------
DROP TABLE IF EXISTS `statictradebalance`;
CREATE TABLE `statictradebalance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_tradeacco` varchar(17) NOT NULL COMMENT '交易账号',
  `c_moneytype` varchar(3) NOT NULL COMMENT '币种【105：人民币，344：港币，840美元，978：欧元】',
  `en_balance` decimal(19,2) DEFAULT '0.00' COMMENT '余额【余额=可用余额+冻结余额】',
  `en_enbalance` decimal(19,2) DEFAULT NULL COMMENT '可用余额',
  `en_imbalance` decimal(19,2) DEFAULT NULL COMMENT '冻结余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_statictradebalance_idx` (`vc_tradeacco`,`c_moneytype`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of statictradebalance
-- ----------------------------
INSERT INTO `statictradebalance` VALUES ('1', '00000000000000000', '105', '1000.00', '689.00', '311.00');

-- ----------------------------
-- Table structure for `systemstaticbalance`
-- ----------------------------
DROP TABLE IF EXISTS `systemstaticbalance`;
CREATE TABLE `systemstaticbalance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_bankacco` varchar(28) NOT NULL COMMENT '银行卡号',
  `c_moneytype` varchar(3) NOT NULL COMMENT '币种【105：人民币，344：港币，840美元，978：欧元】',
  `en_balance` decimal(19,2) DEFAULT '0.00' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_systemstaticbalance_idx` (`vc_bankacco`,`c_moneytype`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemstaticbalance
-- ----------------------------
INSERT INTO `systemstaticbalance` VALUES ('1', '100000', '105', '1000.00');

-- ----------------------------
-- Table structure for `ta`
-- ----------------------------
DROP TABLE IF EXISTS `ta`;
CREATE TABLE `ta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_tacode` varchar(8) NOT NULL COMMENT '编号',
  `vc_taname` varchar(64) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sale_itainfo` (`vc_tacode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ta
-- ----------------------------
INSERT INTO `ta` VALUES ('1', '0001', '第一TA');

-- ----------------------------
-- Table structure for `taacco`
-- ----------------------------
DROP TABLE IF EXISTS `taacco`;
CREATE TABLE `taacco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_taacco` varchar(12) NOT NULL COMMENT '基金账号',
  `vc_custno` varchar(18) NOT NULL COMMENT '客户编号',
  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',
  `vc_opendate` varchar(10) DEFAULT NULL COMMENT '增开时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_taacco_idx` (`vc_taacco`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taacco
-- ----------------------------
INSERT INTO `taacco` VALUES ('1', '000000000000', '000000000000000000', '0001', '2017-03-16');

-- ----------------------------
-- Table structure for `tacommunication`
-- ----------------------------
DROP TABLE IF EXISTS `tacommunication`;
CREATE TABLE `tacommunication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',
  `vc_taacco` varchar(12) DEFAULT NULL COMMENT '基金账号',
  `vc_productcode` varchar(6) NOT NULL COMMENT '产品代码',
  `c_businflag` varchar(3) NOT NULL COMMENT '业务代码【020：认购，022：申购，024：赎回，888：基金行情】',
  `c_status` char(1) NOT NULL COMMENT '状态【0：未导给ta，1：成功，2：失败】',
  `vc_serialno` varchar(18) NOT NULL COMMENT '流水编号',
  `vc_occurdate` varchar(10) NOT NULL COMMENT '发生时间',
  `vc_tradeacco` varchar(17) DEFAULT NULL COMMENT '交易账号',
  `c_moneytype` varchar(3) DEFAULT NULL COMMENT '币种【105：人民币，344：港币，840美元，978：欧元】',
  `en_balance` decimal(19,2) DEFAULT NULL COMMENT '发生金额',
  `en_share` decimal(19,2) DEFAULT NULL COMMENT '发生份额',
  `en_stnav` decimal(9,4) DEFAULT NULL COMMENT '净值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_tacommunication_idx` (`vc_serialno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tacommunication
-- ----------------------------
INSERT INTO `tacommunication` VALUES ('1', '0001', '000000000000', '0001', '020', '0', '000000000000000000', '2017-03-16', '00000000000000000', '105', '10.00', null, null);
INSERT INTO `tacommunication` VALUES ('2', '0001', '000000000000', '0001', '020', '0', '000000000000000001', '2017-03-16', '00000000000000000', '105', '1.00', null, null);
INSERT INTO `tacommunication` VALUES ('3', '0001', '000000000000', '0002', '022', '0', '000000000000000002', '2017-03-16', '00000000000000000', '105', '100.00', null, null);
INSERT INTO `tacommunication` VALUES ('4', '0001', '000000000000', '0002', '022', '0', '000000000000000003', '2017-03-16', '00000000000000000', '105', '200.00', null, null);

-- ----------------------------
-- Table structure for `tradeacco`
-- ----------------------------
DROP TABLE IF EXISTS `tradeacco`;
CREATE TABLE `tradeacco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vc_tradeacco` varchar(17) NOT NULL COMMENT '交易账号',
  `vc_custno` varchar(18) NOT NULL COMMENT '客户编号',
  `vc_bankname` varchar(60) NOT NULL COMMENT '银行名称',
  `vc_bankacco` varchar(28) NOT NULL COMMENT '银行卡号',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `vc_opendate` varchar(10) DEFAULT NULL COMMENT '增开时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_tradeacco_idx` (`vc_tradeacco`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tradeacco
-- ----------------------------
INSERT INTO `tradeacco` VALUES ('1', '00000000000000000', '000000000000000000', '中国工商', '0001', 'Sn0e1BRHTkAzrCnMuGU9mw==', '2017-03-16');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nikename` varchar(12) DEFAULT NULL COMMENT '昵称',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '类型【0：客户，1：管理员】',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态【保留字段】',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '0000', 'Sn0e1BRHTkAzrCnMuGU9mw==', '开发者', '1', '0');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户',
  `role_id` int(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  KEY `fk_user_role_user_id` (`user_id`),
  KEY `fk_user_role_role_id` (`role_id`),
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '2');
INSERT INTO `user_role` VALUES ('2', '1', '3');
INSERT INTO `user_role` VALUES ('3', '1', '4');
INSERT INTO `user_role` VALUES ('4', '1', '5');
INSERT INTO `user_role` VALUES ('5', '1', '6');

-- ----------------------------
-- Procedure structure for `sp_addCustInfo`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_addCustInfo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_addCustInfo`(in c_custtype char(1), vc_custname varchar(64), vc_identityno varchar(32),
                                   vc_tacode varchar(8), mobile varchar(15), email varchar(50),
                                   address varchar(200), vc_bankname varchar(60), vc_bankacco varchar(28), vc_password varchar(60),
                                out vc_custno_out varchar(18), out vc_taacco_out varchar(12), out vc_tradeacco_out varchar(17))
begin
    declare vc_custno_previous varchar(18) default null; -- 上一条记录
    declare vc_custno_current varchar(18) default null; -- 当前记录
    declare vc_opendate varchar(10) default null;
    declare vc_tradeacco varchar(17) default null;
    declare vc_taacco varchar(12) default null;
    select vc_custno into vc_custno_previous from custinfo order by vc_custno desc limit 1;
    set vc_opendate = CURDATE();
    if vc_custno_previous is null
    then
      set vc_custno_current = '000000000000000000';
    ELSE
      set vc_custno_current = vc_custno_previous + 1;
      set vc_custno_current = LPAD(vc_custno_current,18,'0');
    END IF;
    call sp_addTaAcco(vc_custno_current, vc_tacode, vc_taacco); -- 基金账号表
    call sp_addTradeAcco(vc_custno_current, vc_bankname, vc_bankacco, vc_password, vc_tradeacco); -- 交易账号表
    INSERT INTO custinfo(vc_custno, c_custtype, vc_custname, vc_identityno, vc_tacode, mobile, email, address, vc_opendate)
    VALUES (vc_custno_current,c_custtype,vc_custname,vc_identityno,vc_tacode,mobile,email,address,vc_opendate);
    set vc_custno_out = vc_custno_current;
    set vc_taacco_out = vc_taacco;
    set vc_tradeacco_out = vc_tradeacco;
  end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `sp_addTaAcco`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_addTaAcco`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_addTaAcco`(in vc_custno varchar(18), vc_tacode varchar(8), OUT vc_taacco_out varchar(12))
begin
    declare vc_taacco_previous varchar(12) default null; -- 上一条记录
    declare vc_taacco_current varchar(12) default null; -- 当前记录
    declare vc_opendate varchar(10) default null;
    select vc_taacco into vc_taacco_previous from taacco order by vc_taacco desc limit 1;
    set vc_opendate = CURDATE();
    if vc_taacco_previous is null
    then
      set vc_taacco_current = '000000000000';
    ELSE
      set vc_taacco_current = vc_taacco_previous + 1;
      set vc_taacco_current = LPAD(vc_taacco_current,12,'0');
    END IF;
    INSERT INTO taacco(vc_custno, vc_taacco, vc_opendate, vc_tacode)
    VALUES (vc_custno,vc_taacco_current,vc_opendate,vc_tacode);
    set vc_taacco_out = vc_taacco_current;
  end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `sp_addTaCommunication`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_addTaCommunication`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_addTaCommunication`(in vc_tacode varchar(8), vc_taacco varchar(12), vc_productcode varchar(6),
                                          c_businflag varchar(3), vc_tradeacco varchar(17), c_moneytype varchar(3),
                                          en_balance decimal(19,2), en_share decimal(19,2), en_stnav decimal(9, 4), OUT vc_serialno_out varchar(18))
begin
    declare vc_serialno_previous varchar(18) default null; -- 上一条记录
    declare vc_serialno_current varchar(18) default null; -- 当前记录
    declare vc_occurdate varchar(10) default null;
    select vc_serialno into vc_serialno_previous from tacommunication order by vc_serialno desc limit 1;
    set vc_occurdate = CURDATE();
    if vc_serialno_previous is null
    then
      set vc_serialno_current = '000000000000000000';
    ELSE
      set vc_serialno_current = vc_serialno_previous + 1;
      set vc_serialno_current = LPAD(vc_serialno_current,18,'0');
    END IF;
    INSERT INTO tacommunication(vc_tacode, vc_taacco, vc_productcode, c_businflag, c_status, vc_serialno,
                                vc_occurdate, vc_tradeacco, c_moneytype, en_balance, en_share, en_stnav)
    VALUES (vc_tacode, vc_taacco, vc_productcode, c_businflag, '0', vc_serialno_current,
                       vc_occurdate, vc_tradeacco, c_moneytype, en_balance, en_share, en_stnav);
    set vc_serialno_out = vc_serialno_current;
  end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `sp_addTradeAcco`
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_addTradeAcco`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_addTradeAcco`(in vc_custno varchar(18), in vc_bankname varchar(60), in vc_bankacco varchar(28), in vc_password varchar(60), OUT vc_tradeacco_out varchar(17))
begin
    declare vc_tradeacco_previous varchar(17) default null; -- 上一条记录
    declare vc_tradeacco_current varchar(17) default null; -- 当前记录
    declare vc_opendate varchar(10) default null;
    select vc_tradeacco into vc_tradeacco_previous from tradeacco order by vc_tradeacco desc limit 1;
    set vc_opendate = CURDATE();
    if vc_tradeacco_previous is null
    then
      set vc_tradeacco_current = '00000000000000000';
    ELSE
      set vc_tradeacco_current = vc_tradeacco_previous + 1;
      set vc_tradeacco_current = LPAD(vc_tradeacco_current,17,'0');
    END IF;
    INSERT INTO tradeacco(vc_custno, vc_tradeacco, vc_opendate, vc_bankname, vc_bankacco,password)
    VALUES (vc_custno,vc_tradeacco_current,vc_opendate,vc_bankname,vc_bankacco,vc_password);
    set vc_tradeacco_out = vc_tradeacco_current;
  end
;;
DELIMITER ;
