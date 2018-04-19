package com.season.service;

import com.season.dao.OrderDao;
import com.season.dao.ShopCarDao;
import com.season.domain.MsgBean;
import com.season.domain.MyOrder;
import com.season.domain.ShopCar;
import com.season.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wellhope on 2018/4/19.
 */
@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    ShopCarDao shopCarDao;

    public void submitOrder(List<ShopCar> shopCarList) {

        List<ShopCar> tmpList = new ArrayList<>();
        for (ShopCar shopCar : shopCarList) {
            try {
                orderDao.save(MyOrder.newOrder(shopCar));
                tmpList.add(shopCar);
            } catch (Exception e) {
                shopCarDao.remove(tmpList);
                return;
            }
        }
        shopCarDao.remove(tmpList);
    }

    public List orderList(User user){
        return orderDao.queryOrderByUser(user);
    }



    public MsgBean countBook(Date date, Integer foodKind, Integer foodTime, String content){


        return MsgBean
                .success();
    }
}
