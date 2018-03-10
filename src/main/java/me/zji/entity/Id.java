package me.zji.entity;

import java.io.Serializable;

/**
 * Id 模型
 * Created by qian yun on 2018/3/7.
 */
public class Id implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Long get(Id id) {
        return id == null ? null : id.getId();
    }

}
