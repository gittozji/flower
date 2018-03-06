package me.zji.dao;

import me.zji.entity.BankAccoInfo;

/**
 * 银行账户信息 DAO
 * Created by imyu on 2017/3/4.
 */
public interface BankAccoInfoDao {
    /**
     * 创建一条记录
     * @param bankAccoInfo
     */
    void create(BankAccoInfo bankAccoInfo);

    /**
     * 通过银行账户查询
     * @param backAcco
     * @return
     */
    BankAccoInfo queryByBankAcco(String backAcco);
}
