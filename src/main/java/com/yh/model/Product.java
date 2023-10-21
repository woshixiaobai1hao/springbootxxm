package com.yh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Integer id;
    private String productNum;
    private String productName;
    private String cityName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;
//    方便页面端展示数据
    private String departureTimeStr;
    private Integer productPrice;
    private String productDesc;
    private Integer productStatus;
//    状态（0关闭 1开启）：方便页面端展示数据
    private String productStatusStr;

    public void setDepartureTimeStr(String departureTimeStr) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(departureTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.departureTime = date;
    }

    public String getDepartureTimeStr() {
        // 对日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (null != departureTime) {
            departureTimeStr = dateFormat.format(departureTime);
        }
        return departureTimeStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        if (productStatusStr.equals("关闭")){
            this.productStatus = 0;
        } else if(productStatusStr.equals("开启")){
            this.productStatus = 1;
        }
    }

    public String getProductStatusStr() {

        if (productStatus == null) {
            return "";
        }

        if (productStatus == 0){
            productStatusStr = "关闭";
        } else if(productStatus == 1){
            productStatusStr = "开启";
        }

        return productStatusStr;
    }
}
