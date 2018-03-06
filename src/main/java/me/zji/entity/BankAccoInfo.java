package me.zji.entity;

/**
 * 银行账户信息
 * Created by imyu on 2017/3/4.
 */
public class BankAccoInfo extends Id {
    String name;
    String personName;
    String bankName;
    String bankAcco;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
}
