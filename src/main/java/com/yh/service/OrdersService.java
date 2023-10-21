package com.yh.service;

import com.yh.model.Orders;

import java.util.List;

public interface OrdersService {
    /*
    查询所有订单信息
    * */
    List<Orders> findAll();

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    Orders findById(Integer orderId);

    /**
     *  添加订单
     * @param orders
     */
    void add(Orders orders);

    /**
     * 添加订单和游客的关联信息
     * @param orderId
     * @param travellerIds
     */
    void memberAndTraveller(Integer orderId,List<Integer> travellerIds);

    /**
     * 根据id删除订单信息
     * @param id
     */

     void deleteById(int id);

    /**
     * 修改订单信息
     * @param orders
     */
     void update(Orders orders);

    /**
     * 批量删除
     * @param ids
     */
     void selectDelete(int[] ids);
}
