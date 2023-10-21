package com.yh.service;

import com.yh.model.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有
     * @return
     */
    List<Product> findAll();
    /**
     * 添加商品信息
     * @param product
     * @return
     */
    void add(Product product);
    /**
     * 根据id查询商品详情
     * @param id
     * @return
     */
    Product findById(int id);
    /**
     * 修改商品信息
     * @param product
     * @return
     */
    void edit(Product product);
    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    void deleteById(int id);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    void selectDelete(int[] ids);
}
