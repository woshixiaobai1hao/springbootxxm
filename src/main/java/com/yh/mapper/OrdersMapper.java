package com.yh.mapper;

import com.yh.model.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersMapper {
    /**
     * 查询所有订单信息
     * @return
     */
    List<Orders>  findAll();
    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    Orders findById(Integer orderId);

    /**
     * 添加订单信息
     * @param orders
     */
    void add(Orders orders);

    /**
     * 添加订单和游客的关联信息
     * @param orderId
     * @param travellerId
     */
    void memberAndTraveller(@Param("orderId") int orderId, @Param("travellerId") int travellerId);

    /**
     * 删除订单
     * @param id
     */
    void deleteById(int id);

    /**
     * 删除订单和游客的关联信息
     * @param id
     */
    void deleteOrderAndTraveller(Integer id);

    /**
     * 修改订单
     * @param orders
     */
    void update(Orders orders);
}
