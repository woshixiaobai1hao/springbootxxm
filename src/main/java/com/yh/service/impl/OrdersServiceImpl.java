package com.yh.service.impl;

import com.yh.mapper.OrdersMapper;
import com.yh.model.Orders;
import com.yh.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
   @Autowired
   private OrdersMapper ordersMapper;
    @Override
    public List<Orders> findAll() {
        return ordersMapper.findAll();
    }

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    @Override
    public Orders findById(Integer id) {
        return ordersMapper.findById(id);
    }

    /**
     * 添加订单信息
     * @param orders
     */
    @Override
    public void add(Orders orders) {
        ordersMapper.add(orders);
    }

    /**
     * 添加订单和游客的关联信息
     * @param orderId
     * @param travellerIds
     */
    @Override
    public void memberAndTraveller(Integer orderId, List<Integer> travellerIds) {
        for(Integer travellerId : travellerIds) {
            ordersMapper.memberAndTraveller(orderId, travellerId);
        }
    }

    /**
     * 根据id删除订单
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //删除订单和游客的关联信息
       ordersMapper.deleteOrderAndTraveller(id);
       //删除订单
       ordersMapper.deleteById(id);
    }

    /**
     * 修改订单信息
     * @param orders
     */
    @Override
    public void update(Orders orders) {
        ordersMapper.update(orders);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void selectDelete(int[] ids) {
        for(Integer id : ids){
            deleteById(id);
        }
    }
}
