package me.zji.entity;

/**
 * 交易账号
 * Created by imyu on 2017/3/4.
 */
public class TradeAcco extends Id {
    String tradeAcco;
    String custNo;
    String bankName;
    String bankAcco;
    String openDate;
    String password;

    public TradeAcco(){}

    public TradeAcco(String tradeAcco, String custNo, String bankName, String bankAcco, String openDate) {
        this.tradeAcco = tradeAcco;
        this.custNo = custNo;
        this.bankName = bankName;
        this.bankAcco = bankAcco;
        this.openDate = openDate;
    }

    public String getTradeAcco() {
        return tradeAcco;
    }

    public void setTradeAcco(String tradeAcco) {
        this.tradeAcco = tradeAcco;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAcco() {
        return bankAcco;
    }

    public void setBankAcco(String bankAcco) {
        this.bankAcco = bankAcco;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
