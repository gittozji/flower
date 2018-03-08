package me.zji.entity;

/**
 * 产品（鲜花）实体
 * Created by imyu on 2018-03-08.
 */
public class Flower extends Id {
    String name;
    Float price;
    Integer count;
    String fid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
