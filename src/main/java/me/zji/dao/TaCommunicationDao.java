package me.zji.dao;

import me.zji.entity.TaCommunication;

import java.util.List;

/**
 * ta 通信 DAO
 * Created by imyu on 2017/3/9.
 */
public interface TaCommunicationDao {
    void create(TaCommunication taCommunication);
    List<TaCommunication> queryByExample(TaCommunication taCommunication);
    void updateStatusByserialNo(TaCommunication taCommunication);
}
