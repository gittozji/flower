package me.zji.entity;

/**
 * Created by qian yun on 2018/3/7.
 */
public class NetStation extends Id {
    String netNo;
    String netName;
    String address;

    public String getNetNo() {
        return netNo;
    }

    public void setNetNo(String netNo) {
        this.netNo = netNo;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
