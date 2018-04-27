package com.season.web;

import com.google.gson.Gson;
import com.season.domain.Food;
import com.season.domain.MsgBean;
import com.season.domain.MyOrder;
import com.season.domain.ShopCar;
import com.season.exception.MyException;
import com.season.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/order/myOrder.html")
    public ModelAndView orderPage() {
        ModelAndView mav = new ModelAndView("my_order");
        Gson gson = new Gson();
        mav.addObject("orderStatusObj", getOrderStatus());
        mav.addObject("orderStatus", gson.toJson(getOrderStatus()));
        mav.addObject("foodTime", gson.toJson(getMealKind()));
        return mav;
    }


    @RequestMapping(value = "/order/submit", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean submitOrder(@RequestBody List<ShopCar> shopCars) {
        orderService.submitOrder(shopCars);
        return MsgBean.success();
    }


    @RequestMapping(value = "/order/submit2", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean submitOrder2(HttpServletRequest request, ShopCar shopCar, Food food) {

        shopCar.setFood(food);
        shopCar.setUser(getSessionUser(request));
        shopCar.setSubscribeDate(new Date());

        orderService.submitOrder(Arrays.asList(shopCar));
        return MsgBean.success();
    }



    @RequestMapping("/order/list")
    @ResponseBody
    public MsgBean orderList(HttpServletRequest request,
                             @RequestParam(value = "orderStatus", required = false)
                                     Integer orderStatus,
                             @RequestParam("pageNum")Integer pageNum,
                             @RequestParam("pageSize")Integer pageSize) {

        return MsgBean.success().setData(
                orderService.orderList(getSessionUser(request), orderStatus,pageNum,pageSize));
    }

    @RequestMapping("/order/del")
    @ResponseBody
    public MsgBean orderDel(HttpServletRequest request,
                               @RequestBody MyOrder order) {

        try {
            orderService.orderDel(order);
        } catch (MyException e) {
            return MsgBean.error().setMsg(e.getMessage());
        }

        return MsgBean.success();
    }

    @RequestMapping("/order/cancel")
    @ResponseBody
    public MsgBean orderCancel(HttpServletRequest request,
                               @RequestBody MyOrder order) {

        try {
            orderService.orderCancel(order);
        } catch (MyException e) {
            return MsgBean.error().setMsg(e.getMessage());
        }

        return MsgBean.success();
    }

    @ResponseBody
    @RequestMapping("/order/data-list")
    public MsgBean countBook(@RequestParam("foodKind") Integer foodKind,
                             @RequestParam("date")
                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             @RequestParam("content") String content,
                             @RequestParam("foodTime") Integer foodTime,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("pageNum") Integer pageNum) {

        if (date == null) {
            return MsgBean.error().setMsg("日期为空");
        }

        return orderService.countBook(pageSize, pageNum, date, foodKind, foodTime, content);
    }


}
