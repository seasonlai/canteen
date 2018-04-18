package com.season.web;

import com.season.dao.ShopCarDao;
import com.season.domain.MsgBean;
import com.season.domain.ShopCar;
import com.season.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by season on 2018/4/19.
 */
@Controller
public class ShopCarController {

    @Autowired
    ShopCarDao shopCarDao;

    @RequestMapping("/shopcar/list")
    public MsgBean carList(User user) {

        List<ShopCar> shopCars = shopCarDao.queryByUser(user);

        return MsgBean.success().setData(shopCars);
    }


    @RequestMapping("/shopcar/modify")
    public MsgBean carList(ShopCar shopCar,Integer type) {

        switch (type){
            case 1:
                shopCarDao.save(shopCar);
                break;

            case 2:
                shopCarDao.update(shopCar);
                break;
        }

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
