package me.zji.service;

import me.zji.entity.Flower;

import java.util.List;

/**
 * Created by imyu on 2018-03-08.
 */
public interface FlowerService {
    void create(Flower flower);

    Flower queryByName(String name);

    List<Flower> query();
}
