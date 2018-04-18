package com.season.web;

import com.season.dao.ShopCarDao;
import com.season.domain.Food;
import com.season.domain.MsgBean;
import com.season.domain.ShopCar;
import com.season.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by season on 2018/4/19.
 */
@Controller
public class ShopCarController extends BaseController {

    @Autowired
    ShopCarDao shopCarDao;

    @RequestMapping("/shopcar/myShopCar.html")
    public ModelAndView myShopCar(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("my_shopcar");
        mav.addObject("foodTime", getMealKind());
        return mav;
    }

    @RequestMapping("/shopcar/list")
    @ResponseBody
    public MsgBean carList(HttpServletRequest request) {

        List<ShopCar> shopCars = shopCarDao.queryByUser(getSessionUser(request));

        return MsgBean.success().setData(shopCars);
    }


    @RequestMapping("/shopcar/add")
    @ResponseBody
    public MsgBean carAdd(HttpServletRequest request, ShopCar shopCar, Food food) {

        shopCar.setFood(food);
        shopCar.setUser(getSessionUser(request));

        shopCarDao.save(shopCar);

        return MsgBean.success();
    }


//    @RequestMapping("/shopcar/del")
//    public MsgBean carList(ShopCar shopCar,Integer type) {
//
//        switch (type){
//            case 1:
//                shopCarDao.save(shopCar);
//                break;
//
//            case 2:
//                shopCarDao.update(shopCar);
//                break;
//        }
//
//        return MsgBean.success();
//    }


}
