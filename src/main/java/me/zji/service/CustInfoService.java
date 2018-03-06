package me.zji.service;

import me.zji.entity.CustInfo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 客户信息服务
 * Created by imyu on 2017/3/4.
 */
public interface CustInfoService {
    /**
     * 插入并返回客户编号、TA账号和交易账号
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    Map create(Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    CustInfo queryByIdentityNo(String identityNo);

    CustInfo queryByUsername(String username);

    CustInfo queryByCustNo(String custNo);

    void update(CustInfo custInfo);

}
