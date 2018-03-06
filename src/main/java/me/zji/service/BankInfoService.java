package me.zji.service;

import me.zji.entity.BankAccoInfo;

/**
 * 银行账户信息服务
 * Created by imyu on 2017/3/4.
 */
public interface BankInfoService {
    /**
     * 新增一条记录
     */
    void create(BankAccoInfo bankAccoInfo);

    BankAccoInfo queryByBankAcco(String bankAcco);
}
