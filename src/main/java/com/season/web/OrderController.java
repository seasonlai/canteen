package com.season.web;

import com.season.domain.MsgBean;
import com.season.domain.ShopCar;
import com.season.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/order/myOrder.html")
    public ModelAndView orderPage() {
        ModelAndView mav = new ModelAndView("my_order");
        return mav;
    }


    @RequestMapping("/order/submit")
    public MsgBean submitOrder(List<ShopCar> shopCars) {
        orderService.submitOrder(shopCars);
        return MsgBean.success();
    }

}
