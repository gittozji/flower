package me.zji.service;

import me.zji.dto.CustUser;

/**
 * 客户用户关键信息服务
 * Created by qian yun on 2018/3/8.
 */
public interface CustUserService {
    CustUser queryByUsername(String username);
}
