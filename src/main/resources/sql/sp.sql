-- ----------------------------
-- 基金账号插入
-- ----------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addTaAcco$$
CREATE PROCEDURE sp_addTaAcco(in vc_custno varchar(18), vc_tacode varchar(8), OUT vc_taacco_out varchar(12))
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
  end$$
DELIMITER ;

-- ----------------------------
-- 交易账号插入
-- ----------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addTradeAcco$$
CREATE PROCEDURE sp_addTradeAcco(in vc_custno varchar(18), in vc_bankname varchar(60), in vc_bankacco varchar(28), in vc_password varchar(60), OUT vc_tradeacco_out varchar(17))
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
  end$$
DELIMITER ;

-- ----------------------------
-- 客户信息插入（用于开户接口）
-- ----------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addCustInfo$$
CREATE PROCEDURE sp_addCustInfo(in c_custtype char(1), vc_custname varchar(64), vc_identityno varchar(32),
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
  end$$
DELIMITER ;

-- ----------------------------
-- TA 通信表插入
-- ----------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addTaCommunication$$
CREATE PROCEDURE sp_addTaCommunication(in vc_tacode varchar(8), vc_taacco varchar(12), vc_productcode varchar(6),
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
  end$$
DELIMITER ;