package me.zji.service;

import me.zji.entity.StaticShare;

/**
 * 静态份额服务
 * Created by imyu on 2017/3/14.
 */
public interface StaticShareService {
    void createOrUpdate(StaticShare staticShare);

    /**
     * 通过基金代码、基金账号查询
     * @param staticShare
     * @return
     */
    StaticShare queryByCodeAndAcco(StaticShare staticShare);
}
