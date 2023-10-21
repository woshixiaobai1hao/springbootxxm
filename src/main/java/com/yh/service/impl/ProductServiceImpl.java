package com.yh.service.impl;

import com.yh.mapper.ProductMapper;
import com.yh.model.Product;
import com.yh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }
    /**
     * 添加商品信息
     * @param product
     * @return
     */
    @Override
    public void add(Product product) {
        productMapper.add(product);
    }
    /**
     * 根据id查询商品详情
     * @param id
     * @return
     */
    @Override
    public Product findById(int id) {
        return productMapper.findById(id);
    }
    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @Override
    public void edit(Product product) {
        productMapper.edit(product);
    }
    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    @Override
    public void deleteById(int id) {
        //删除订单中商品外键
        productMapper.editOrderByProductId(id);
        productMapper.deleteById(id);
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public void selectDelete(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}
