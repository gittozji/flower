package me.zji.entity;

/**
 * 交易账号静态资金
 * Created by imyu on 2017/3/5.
 */
public class StaticTradeBalance extends Id {
    String tradeAcco;
    String moneyType;
    Double balance;
    Double enBalance;
    Double imBalance;

    public StaticTradeBalance(){}

    public StaticTradeBalance(String tradeAcco, String moneyType) {
        this.tradeAcco = tradeAcco;
        this.moneyType = moneyType;
    }

    public StaticTradeBalance(String tradeAcco, String moneyType, Double balance, Double enBalance, Double imBalance) {
        this.tradeAcco = tradeAcco;
        this.moneyType = moneyType;
        this.balance = balance;
        this.enBalance = enBalance;
        this.imBalance = imBalance;
    }

    public String getTradeAcco() {
        return tradeAcco;
    }

    public void setTradeAcco(String tradeAcco) {
        this.tradeAcco = tradeAcco;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getEnBalance() {
        return enBalance;
    }

    public void setEnBalance(Double enBalance) {
        this.enBalance = enBalance;
    }

    public Double getImBalance() {
        return imBalance;
    }

    public void setImBalance(Double imBalance) {
        this.imBalance = imBalance;
    }
}
