package com.season.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by season on 2018/4/19.
 */

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_order")
public class MyOrder {

    @Id
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "food_time")
    private Integer foodTime;
    @Column(name = "order_status")
    private Integer orderStatus;
    @Column(name = "food_count")
    private Integer foodCount;
    @Column(name = "food_total_price")
    private Float foodTotalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
    @Column(name = "order_date")
    private Date orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @Column(name = "subscribe_date")
    private Date subscribeDate;

    public Float getFoodTotalPrice() {
        return foodTotalPrice;
    }

    public void setFoodTotalPrice(Float foodTotalPrice) {
        this.foodTotalPrice = foodTotalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(Integer foodTime) {
        this.foodTime = foodTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(Integer foodCount) {
        this.foodCount = foodCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {

        this.subscribeDate = subscribeDate;
    }

    public static MyOrder newOrder(ShopCar shopCar) {
        if (shopCar == null)
            return null;
        MyOrder order = new MyOrder();
        order.setOrderId(UUID.randomUUID().toString());
        order.setFood(shopCar.getFood());
        order.setFoodCount(shopCar.getFoodCount());
        order.setFoodTime(shopCar.getFoodTime());
        order.setFoodTotalPrice(shopCar.getFoodTotalPrice());
        order.setUser(shopCar.getUser());
        order.setSubscribeDate(shopCar.getSubscribeDate());
        order.setOrderStatus(1);//未完成状态
        order.setOrderDate(new Date());
        return order;
    }


//    public static void main(String[] args){
//        System.out.println(UUID.randomUUID().toString());
//    }
}
