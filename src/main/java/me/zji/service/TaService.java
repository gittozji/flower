package me.zji.service;

import me.zji.entity.Ta;

/**
 * Ta 服务
 * Created by imyu on 2017/3/1.
 */
public interface TaService {
    /**
     * 创建一条记录
     * @param ta
     */
    void create(Ta ta);

    /**
     * 通过代码查询
     * @param code
     * @return
     */
    Ta queryByCode(String code);
}
