package me.zji.entity;

/**
 * 用户
 * Created by imyu on 2017/1/22.
 */
public class User extends Id {
    private String username;
    private String password;
    private String nikename;
    private Integer type;
    private Integer status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        Long id = super.getId();
        if (id == null)
            return 0;
        return ((Number)id ).intValue();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User && (super.getId() == ((Id)((User) o)).getId());
    }
}
