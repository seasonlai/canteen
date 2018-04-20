package com.season.service;

import com.season.dao.OrderDao;
import com.season.dao.Page;
import com.season.dao.ShopCarDao;
import com.season.domain.MsgBean;
import com.season.domain.MyOrder;
import com.season.domain.ShopCar;
import com.season.domain.User;
import com.season.exception.MyException;
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

    public Page orderList(User user, Integer orderStatus, Integer pageNum, Integer pageSize){
        return orderDao.queryOrderByUser(user,orderStatus,pageNum,pageSize);
    }

    public void orderDel(MyOrder order){
        MyOrder myOrder = orderDao.load(order.getOrderId());
        if(myOrder==null){
            throw new MyException("订单不存在");
        }
        orderDao.remove(myOrder);
    }

    public void orderCancel(MyOrder order){
        MyOrder myOrder = orderDao.load(order.getOrderId());
        if(myOrder==null){
            throw new MyException("订单不存在");
        }
        myOrder.setOrderStatus(2);
        orderDao.update(myOrder);
    }



    public MsgBean countBook(Integer pageSize, Integer pageNum, Date date,
                             Integer foodKind, Integer foodTime, String content){

        Page page = orderDao.queryOrderByCondition(pageNum, pageSize, date, foodKind, foodTime, content);

        return MsgBean
                .success().setData(page);
    }
}
