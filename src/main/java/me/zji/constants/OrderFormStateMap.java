package me.zji.constants;

import java.util.HashMap;
import java.util.Map;

public class OrderFormStateMap {
    public static final Map map = new HashMap();
    static {
        for (OrderFormState orderFormState: OrderFormState.values()){
            map.put(orderFormState.value, orderFormState.name);
        }
    }
}
