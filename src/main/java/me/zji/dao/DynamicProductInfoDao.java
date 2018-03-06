package me.zji.dao;

import me.zji.entity.DynamicProductInfo;

/**
 * 动态产品信息 DAO
 * Created by imyu on 2017/3/13.
 */
public interface DynamicProductInfoDao {
    void create(DynamicProductInfo dynamicProductInfo);
    void update(DynamicProductInfo dynamicProductInfo);
    DynamicProductInfo queryByProductCode(String productCode);
}
