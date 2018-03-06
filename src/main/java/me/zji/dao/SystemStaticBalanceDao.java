package me.zji.dao;

import me.zji.entity.SystemStaticBalance;

/**
 * 系统静态资金信息 DAO
 * Created by imyu on 2017/3/5.
 */
public interface SystemStaticBalanceDao {
    void create(SystemStaticBalance systemStaticBalance);
    SystemStaticBalance queryByBankAccoAndMoneyType(SystemStaticBalance systemStaticBalance);
    void update(SystemStaticBalance systemStaticBalance);
}
