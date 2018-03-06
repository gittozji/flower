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

-- ----------------------------
-- 流程控制
-- ----------------------------
INSERT INTO `deal_process`(vc_procedurcode, vc_name, l_state) VALUES ('dayinit', '日初始化', 2);
INSERT INTO `deal_process`(vc_procedurcode, vc_name, l_state) VALUES ('receivemarket', '接收行情', 2);
INSERT INTO `deal_process`(vc_procedurcode, vc_name, l_state) VALUES ('starttuxedo', '启动交易', 2);
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('downtuxedo', '停止柜台交易');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('checkdata', '交易预处理');
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('exprequest', '导出申请数据');
INSERT INTO `deal_process`(vc_procedurcode, vc_name, l_state) VALUES ('importdata', '导入确认数据 ', 2);
INSERT INTO `deal_process`(vc_procedurcode, vc_name, l_state) VALUES ('dealdata', '处理确认数据', 2);
INSERT INTO `deal_process`(vc_procedurcode, vc_name) VALUES ('liqcarryover', '清算结转');