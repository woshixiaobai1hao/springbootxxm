package com.yh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.model.Product;
import com.yh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询所有 根据分页条件展示
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("findAll")
    public String findAll(@RequestParam(name="page",defaultValue="1") int  page,
                          @RequestParam(name="size",defaultValue="5") int size,
                          Model  model){
        //分页标准
        PageHelper.startPage(page, size);
        //按上句标准分页查询
        List<Product> all = productService.findAll();
        //封装成PageInfo(包含分页的所有数据)
        PageInfo pageInfo = new PageInfo(all);
        //绑定数据
        model.addAttribute("pageInfo",pageInfo);
        return "product-list";
    }

    /**
     * 添加商品信息
     * @param product
     * @return
     */
    @PostMapping ("save")
    public String save(Product product){
         productService.add(product);
        return "redirect:/product/findAll";
    }

    /**
     * 根据id查找要修改的商品信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("updateById")
    public String updateById(@RequestParam(name="id") int id,Model  model){
            Product product= productService.findById(id);
            model.addAttribute("product",product);
            return "product-edit";
    }

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @PostMapping("edit")
    public String edit(Product product){
        productService.edit(product);
        return "redirect:findAll";

    }

    /**
     * 根据id查询商品详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("findById")
    public String findById(@RequestParam("id") int id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "product-show";
    }

    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    @GetMapping ("deleteById")
    public String deleteById(@RequestParam("id") int id){
         productService.deleteById(id);
        return "redirect:findAll";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("selectDelete")
    public String selectDelete(@RequestParam("ids") int[] ids){
         productService.selectDelete(ids);
         return  "redirect:findAll";
    }

}
