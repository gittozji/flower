package me.zji.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程控制
 * Created by imyu on 2017/2/26.
 */
public class DealProcess extends Id {
    String procedurCode;
    String name;
    Integer state;

    public String getProcedurCode() {
        return procedurCode;
    }

    public void setProcedurCode(String procedurCode) {
        this.procedurCode = procedurCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public static Map<String, DealProcess> listToMap(List<DealProcess> list) {
        Map<String, DealProcess> map = new HashMap<String, DealProcess>();
        for (DealProcess dealProcess : list) {
            map.put(dealProcess.getProcedurCode(), dealProcess);
        }
        return map;
    }
}
