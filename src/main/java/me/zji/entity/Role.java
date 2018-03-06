package me.zji.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色
 * Created by imyu on 2017/2/12.
 */
public class Role extends Id {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 从角色List集合中得到角色代码Set集合
     * @param roleList
     * @return
     */
    public static Set<String> getRoleCode(List<Role> roleList) {
        Set roleSet = new HashSet();
        for (Role role: roleList) {
            roleSet.add(role.getCode());
        }
        return roleSet;
    }
}
