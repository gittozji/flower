package me.zji.entity;

/**
 * 动态产品信息
 * Created by qian yun on 2018/3/7.
 */
public class DynamicProductInfo extends Id {
    String productCode;
    Double stnav;

    public DynamicProductInfo(){}

    public DynamicProductInfo(String productCode, Double stnav) {
        this.productCode = productCode;
        this.stnav = stnav;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getStnav() {
        return stnav;
    }

    public void setStnav(Double stnav) {
        this.stnav = stnav;
    }
}
