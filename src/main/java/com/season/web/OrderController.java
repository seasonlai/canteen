package com.season.web;

import com.google.gson.Gson;
import com.season.domain.MsgBean;
import com.season.domain.ShopCar;
import com.season.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/order/list")
    @ResponseBody
    public MsgBean orderList(HttpServletRequest request) {

        List list = orderService.orderList(getSessionUser(request));

        return MsgBean.success().setData(list);
    }


    @ResponseBody
    @RequestMapping("/order/data-list")
    public MsgBean countBook(@RequestParam("foodKind") Integer foodKind
            , @RequestParam("date") Date date, @RequestParam("content") String content
            , @RequestParam("foodTime") Integer foodTime) {

        if(date==null){
            return MsgBean.error().setMsg("日期为空");
        }

        return orderService.countBook(date,foodKind,foodTime,content);
    }


}
