package me.zji.service.impl;

import me.zji.dao.BankAccoInfoDao;
import me.zji.entity.BankAccoInfo;
import me.zji.service.BankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 银行账户信息服务
 * Created by imyu on 2017/3/4.
 */
@Service
public class BankInfoServiceImpl implements BankInfoService {
    @Autowired
    BankAccoInfoDao bankAccoInfoDao;
    public void create(BankAccoInfo bankAccoInfo) {
        bankAccoInfoDao.create(bankAccoInfo);
    }

    public BankAccoInfo queryByBankAcco(String bankAcco) {
        return bankAccoInfoDao.queryByBankAcco(bankAcco);
    }
}
