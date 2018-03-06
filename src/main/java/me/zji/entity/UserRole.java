package me.zji.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户-角色
 * Created by imyu on 2017/2/12.
 */
public class UserRole extends Id {
    private Long userId;
    private Long roleId;
    private User user;
    private Role role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    /**
     * 从UserRole集合中提取Role集合
     * @param userRoleList
     * @return
     */
    public static List<Role> getRoleList(List<UserRole> userRoleList) {
        List<Role> roleList = new ArrayList<Role>();
        for (UserRole userRole: userRoleList) {
            roleList.add(userRole.getRole());
        }
        return roleList;
    }
}
