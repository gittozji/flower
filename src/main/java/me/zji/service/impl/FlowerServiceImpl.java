package me.zji.service.impl;

import me.zji.dao.FlowerDao;
import me.zji.entity.Flower;
import me.zji.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by qian yun on 2018-03-08.
 */
@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    FlowerDao flowerDao;

    public Flower getFlower(Long id) {
        return flowerDao.selectById(id);
    }

    public void create(Flower flower) {
        flowerDao.create(flower);
    }

    public Flower queryByName(String name) {
        return flowerDao.selectByName(name);
    }

    public List<Flower> query() {
        List<Flower> list = flowerDao.select();
        return list == null ? Collections.EMPTY_LIST : list;
    }

    public void deleteByName(String name) {
        flowerDao.deleteByName(name);
    }

    public void updateCount(Flower flower) {
        flowerDao.updateCount(flower);
    }


}
