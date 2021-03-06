package me.zji.dao;

import me.zji.entity.CustInfo;

import java.util.Map;

/**
 * 客户信息 DAO
 * Created by qian yun on 2018/3/9.
 */
public interface CustInfoDao {
    void create(Map params);

    /**
     * 目前只支持通过客户编号更新用户名（username）
     * @param custInfo
     */
    void update(CustInfo custInfo);

    CustInfo queryByIdentityNo(String identityNo);

    CustInfo queryByCustNo(String custNo);

    CustInfo queryByUsername(String username);
}
