package me.zji.service.impl;

import me.zji.dao.DynamicProductInfoDao;
import me.zji.entity.DynamicProductInfo;
import me.zji.service.DynamicProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 动态产品信息服务
 * Created by imyu on 2017/3/13.
 */
@Service
public class DynamicProductInfoServiceImpl implements DynamicProductInfoService {
    @Autowired
    DynamicProductInfoDao dynamicProductInfoDao;
    public void createOrUpdate(DynamicProductInfo dynamicProductInfo) {
        if (dynamicProductInfoDao.queryByProductCode(dynamicProductInfo.getProductCode()) == null) {
            dynamicProductInfoDao.create(dynamicProductInfo);
        } else {
            dynamicProductInfoDao.update(dynamicProductInfo);
        }
    }

    public DynamicProductInfo queryByProductCode(String productCode) {
        return dynamicProductInfoDao.queryByProductCode(productCode);
    }
}
