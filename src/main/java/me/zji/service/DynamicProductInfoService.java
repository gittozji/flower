package me.zji.service;

import me.zji.entity.DynamicProductInfo;

/**
 * 动态产品信息服务
 * Created by imyu on 2017/3/13.
 */
public interface DynamicProductInfoService {
    void createOrUpdate(DynamicProductInfo dynamicProductInfo);
    DynamicProductInfo queryByProductCode(String productCode);
}
