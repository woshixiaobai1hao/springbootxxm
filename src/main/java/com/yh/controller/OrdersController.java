package com.yh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.model.Member;
import com.yh.model.Orders;
import com.yh.model.Product;
import com.yh.model.Traveller;
import com.yh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TravellerService travellerService;


    /**
     * 查询所有 根据分页进行展示
      * @param model
     * @param page
     * @param size
     * @return
     */
        // @RolesAllowed({"ADMIN","USER"}) // JSR-250注解
        // @RolesAllowed("ADMIN") // JSR-250注解
        // @Secured("ROLE_ADMIN") // Secured注解
        @PreAuthorize("authentication.principal.username == 'jack'")//只有jack才可以访问
        @RequestMapping("findAll")
        public String findAll( Model model ,
                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "size",defaultValue = "5") Integer size){

            PageHelper.startPage(page,size);
            List<Orders> orders = ordersService.findAll();

            PageInfo pageInfo = new PageInfo(orders);

            model.addAttribute("pageInfo",pageInfo);
            return "order-list";
        }

    /**
     * 根据id查询订单详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("findById")
    public String findById(Integer id, Model model) {
        Orders order = ordersService.findById(id);
        model.addAttribute("order",order);
        return "order-show";
    }

    /**
     * 添加界面
     * @param model
     * @return
     */
    @GetMapping ("add")
    public String add(Model model){

        //下拉查询 product, 在产品中已写过方法
        List<Product> productList = productService.findAll();

        //下拉查询 member
        List<Member> memberList = memberService.findAll();

        //下拉查询 Traveller
        List<Traveller> travellerList = travellerService.findAll();

        //显示到order-add.html
        model.addAttribute("productList",productList);
        model.addAttribute("memberList",memberList);
        model.addAttribute("travellerList",travellerList);

        return "order-add";

    }
    /**
     * 添加订单
     * @param orders
     * @return
     */
    @PostMapping("save")
    public String save(Orders orders) {
            ordersService.add(orders);

            ordersService.memberAndTraveller(orders.getId(),orders.getTravellerId());
         return "redirect:findAll";
    }

    /**
     * 根据id查找要修改的订单详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("editById")
    public String editById(@RequestParam(name="id") Integer id, Model model) {
        Orders order= ordersService.findById(id);
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        model.addAttribute("order",order);
        return "order-edit";
    }

    /**
     * 修改订单
     * @param orders
     * @return
     */
    @PostMapping("edit")
    public String edit(Orders orders) {
        ordersService.update(orders);
        return "redirect:findAll";
    }

    /**
     * 根据id删除订单
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public String deleteById(@RequestParam(name="id") int id) {
        ordersService.deleteById(id);
        return "redirect:findAll";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping ("selectDelete")
    public String selectDelete(@RequestParam("ids") int[] ids) {
        ordersService.selectDelete(ids);
        return  "redirect:findAll";
    }

}
