package me.zji.dao;

import me.zji.entity.TaAcco;

import java.util.List;

/**
 * 基金账号 Dao
 * Created by imyu on 2017/3/4.
 */
public interface TaAccoDao {
    void create(TaAcco taAcco);
    List<TaAcco> queryByCustNo(String custNo);
    TaAcco queryByTaAcco(String taAcco);
}
