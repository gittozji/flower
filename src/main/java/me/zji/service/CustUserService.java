package me.zji.service;

import me.zji.dto.CustUser;

/**
 * 客户用户关键信息服务
 * Created by imyu on 2017/3/5.
 */
public interface CustUserService {
    CustUser queryByUsername(String username);
}
