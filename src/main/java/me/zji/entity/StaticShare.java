package me.zji.entity;

/**
 * 静态份额
 * Created by imyu on 2017/3/14.
 */
public class StaticShare extends Id {
    String productCode;
    String taAcco;
    Double share;
    Double enShare;
    Double imShare;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTaAcco() {
        return taAcco;
    }

    public void setTaAcco(String taAcco) {
        this.taAcco = taAcco;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }

    public Double getEnShare() {
        return enShare;
    }

    public void setEnShare(Double enShare) {
        this.enShare = enShare;
    }

    public Double getImShare() {
        return imShare;
    }

    public void setImShare(Double imShare) {
        this.imShare = imShare;
    }
}
