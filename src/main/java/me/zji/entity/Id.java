package me.zji.entity;

import java.io.Serializable;

/**
 * Id 模型
 * Created by imyu on 2017/2/12.
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
