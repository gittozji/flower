package me.zji.service;

import me.zji.entity.TaCommunication;

/**
 * TA 通信服务
 * Created by imyu on 2017/3/9.
 */
public interface TaCommunicationService {
    /**
     * 插入并返回插入后的结果
     * @param taCommunication
     * @return
     */
    TaCommunication create(TaCommunication taCommunication);

    /**
     * 导出文件
     * @return
     */
    boolean taOutput();

    /**
     * 接收行情文件
     * @return
     */
    boolean receiveMarket();

    /**
     * 导入申请数据
     * @return
     */
    boolean importData();

    /**
     * 交易预处理（插入基金行情申请信息到tacommunication表）
     * @return
     */
    boolean checkData();
}
