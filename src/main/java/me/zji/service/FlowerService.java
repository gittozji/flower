package me.zji.service;

import me.zji.entity.Flower;

import java.util.List;

/**
 * Created by imyu on 2018-03-08.
 */
public interface FlowerService {
    Flower getFlower(Long id);
    void create(Flower flower);

    Flower queryByName(String name);

    List<Flower> query();

    void deleteByName(String name);

    void updateCount(Flower flower);
}
