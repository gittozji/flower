package me.zji.service.impl;

import me.zji.dao.DynamicSelectDao;
import me.zji.dao.ProductInfoDao;
import me.zji.dao.TaAccoDao;
import me.zji.dao.TradeAccoDao;
import me.zji.dto.SelectItem;
import me.zji.entity.ProductInfo;
import me.zji.entity.TaAcco;
import me.zji.entity.TradeAcco;
import me.zji.service.DynamicSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选择框数据动态获取服务
 * Created by imyu on 2017/2/24.
 */
@Service
public class DynamicSelectServiceImpl implements DynamicSelectService {
    @Autowired
    DynamicSelectDao dynamicSelectDao;
    @Autowired
    TaAccoDao taAccoDao;
    @Autowired
    TradeAccoDao tradeAccoDao;
    @Autowired
    ProductInfoDao productInfoDao;

    public List<SelectItem> selectNetStation() {
        return dynamicSelectDao.selectNetStation();
    }

    public List<SelectItem> selectTaCode() {
        return dynamicSelectDao.selectTaCode();
    }

    public List<SelectItem> selectBankAccoInfo() {
        return dynamicSelectDao.selectBankAccoInfo();
    }

    public List<SelectItem> selectTaAccoByCustNo(String custNo) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<TaAcco> taAccoList = taAccoDao.queryByCustNo(custNo);
        for (TaAcco taAcco : taAccoList) {
            SelectItem selectItem = new SelectItem();
            selectItem.setItem(taAcco.getTaAcco());
            selectItem.setCaption(taAcco.getTaAcco());
            selectItems.add(selectItem);
        }
        return selectItems;
    }

    /**
     * 通过客户编号获取交易账号
     *
     * @param custNo
     * @return
     */
    public List<SelectItem> selectTradeAccoByCustNo(String custNo) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<TradeAcco> tradeAccoList = tradeAccoDao.queryByCustNo(custNo);
        for (TradeAcco tradeAcco : tradeAccoList) {
            SelectItem selectItem = new SelectItem();
            selectItem.setItem(tradeAcco.getTradeAcco());
            selectItem.setCaption(tradeAcco.getTradeAcco());
            selectItems.add(selectItem);
        }
        return selectItems;
    }

    /**
     * 通过基金状态查询，0：认购期，1：申购期，2：终止期，-1：全部
     * @param status
     * @return
     */
    public List<SelectItem> selectProductByStatus(String status) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<ProductInfo> productInfoList = null;
        if ("-1".equals(status)) {
            productInfoList = productInfoDao.queryAll();
        } else {
            Map map = new HashMap();
            map.put("productStatus", status);
            productInfoList = productInfoDao.queryByProductStatus(map);
        }

        for (ProductInfo productInfo : productInfoList) {
            SelectItem selectItem = new SelectItem();
            selectItem.setItem(productInfo.getProductCode());
            selectItem.setCaption(productInfo.getProductName());
            selectItems.add(selectItem);
        }
        return selectItems;
    }
}
