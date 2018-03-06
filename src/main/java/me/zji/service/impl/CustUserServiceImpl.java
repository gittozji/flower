package me.zji.service.impl;

import me.zji.dao.CustInfoDao;
import me.zji.dao.UserDao;
import me.zji.dto.CustUser;
import me.zji.entity.CustInfo;
import me.zji.entity.User;
import me.zji.service.CustUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户用户关键信息服务
 * Created by imyu on 2017/3/5.
 */
@Service
public class CustUserServiceImpl implements CustUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    CustInfoDao custInfoDao;
    public CustUser queryByUsername(String username) {
        CustUser custUser = new CustUser();
        User user = userDao.queryByUsername(username);
        CustInfo custInfo = custInfoDao.queryByUsername(username);
        custUser.setUsername(user.getUsername());
        custUser.setNikename(user.getNikename());
        custUser.setType(user.getType());
        if (custInfo != null) {
            custUser.setCustNo(custInfo.getCustNo());
            custUser.setCustType(custInfo.getCustType());
        }
        return custUser;
    }
}
