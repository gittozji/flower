package me.zji.service.impl;

import me.zji.dao.CustInfoDao;
import me.zji.entity.CustInfo;
import me.zji.security.PasswordUtils;
import me.zji.service.CustInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户信息服务
 * Created by imyu on 2017/3/4.
 */
@Service
public class CustInfoServiceImpl implements CustInfoService {
    @Autowired
    CustInfoDao custInfoDao;
    public Map create(Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        params.put("password", PasswordUtils.encryptPassword((String) params.get("password")));
        custInfoDao.create(params);
        Map map = new HashMap();
        map.put("custNo", params.get("custNo"));
        map.put("taAcco", params.get("taAcco"));
        map.put("tradeAcco", params.get("tradeAcco"));
        return map;
    }

    public CustInfo queryByIdentityNo(String identityNo) {
        return custInfoDao.queryByIdentityNo(identityNo);
    }

    public CustInfo queryByUsername(String username) {
        return custInfoDao.queryByUsername(username);
    }

    public CustInfo queryByCustNo(String custNo) {
        return custInfoDao.queryByCustNo(custNo);
    }

    public void update(CustInfo custInfo) {
        custInfoDao.update(custInfo);
    }
}
