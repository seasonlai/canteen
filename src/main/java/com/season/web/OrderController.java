package com.season.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class OrderController {

    @RequestMapping("/order/myOrder.html")
    public ModelAndView orderPage(){
        ModelAndView mav =new ModelAndView("my_order");
        return mav;
    }

}
