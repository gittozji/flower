package me.zji.service;

import me.zji.dto.SelectItem;

import java.util.List;

/**
 * 选择框数据动态获取服务
 * Created by imyu on 2017/2/24.
 */
public interface DynamicSelectService {
    /**
     * 获取网点
     * @return
     */
    List<SelectItem> selectNetStation();

    /**
     * 获取TA
     * @return
     */
    List<SelectItem> selectTaCode();

    /**
     * 获取系统的银行账户账户信息
     * @return
     */
    List<SelectItem> selectBankAccoInfo();

    /**
     * 通过客户编号获取基金账号
     * @param custNo
     * @return
     */
    List<SelectItem> selectTaAccoByCustNo(String custNo);

    /**
     * 通过客户编号获取交易账号
     * @param custNo
     * @return
     */
    List<SelectItem> selectTradeAccoByCustNo(String custNo);

    /**
     * 通过基金状态查询，0：认购期，1：申购期，2：终止期，-1：全部
     * @param status
     * @return
     */
    List<SelectItem> selectProductByStatus(String status);


}
