package me.zji.entity;

/**
 * TA 账号
 * Created by imyu on 2017/3/4.
 */
public class TaAcco extends Id {
    String taAcco;
    String custNo;
    String taCode;
    String openDate;

    public TaAcco(){}

    public TaAcco(String taAcco, String custNo, String taCode, String openDate) {
        this.taAcco = taAcco;
        this.custNo = custNo;
        this.taCode = taCode;
        this.openDate = openDate;
    }

    public String getTaAcco() {
        return taAcco;
    }

    public void setTaAcco(String taAcco) {
        this.taAcco = taAcco;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getTaCode() {
        return taCode;
    }

    public void setTaCode(String taCode) {
        this.taCode = taCode;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
}
