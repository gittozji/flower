package me.zji.constants;

public enum OrderFormState {
    CASH_NON("现金未付款", 0), ONLINE_NON("线上未付款", 2), ONLINE("线上已付款", 3), DISPATCHING("派送中", 4),
    DISPATCHING_NON_PAY("现金未支付派送中", 40), CANCEL("已取消", 5), FINISH("订单完成", 6);
    String name;
    int value;

    OrderFormState(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
