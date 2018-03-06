package me.zji.entity;

/**
 * 系统静态资金信息
 * Created by imyu on 2017/3/5.
 */
public class SystemStaticBalance extends Id {
    String bankAcco;
    String moneyType;
    Double balance;

    public SystemStaticBalance(){}

    public SystemStaticBalance(String bankAcco, String moneyType) {
        this.bankAcco = bankAcco;
        this.moneyType = moneyType;
    }

    public SystemStaticBalance(String bankAcco, String moneyType, Double balance) {
        this.bankAcco = bankAcco;
        this.moneyType = moneyType;
        this.balance = balance;
    }

    public String getBankAcco() {
        return bankAcco;
    }

    public void setBankAcco(String bankAcco) {
        this.bankAcco = bankAcco;
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
}
