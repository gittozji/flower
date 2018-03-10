package me.zji.dao;

import me.zji.entity.Flower;

import java.util.List;

/**
 * Created by qian yun on 2018-03-08.
 */
public interface FlowerDao {
    Flower selectById(Long id);
    void create(Flower flower);
    Flower selectByName(String name);
    List<Flower> select();
    void deleteByName(String name);
    void updateCount(Flower flower);
}
