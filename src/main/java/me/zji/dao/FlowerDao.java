package me.zji.dao;

import me.zji.entity.Flower;

import java.util.List;

/**
 * Created by imyu on 2018-03-08.
 */
public interface FlowerDao {
    void create(Flower flower);
    Flower selectByName(String name);
    List<Flower> select();
}
