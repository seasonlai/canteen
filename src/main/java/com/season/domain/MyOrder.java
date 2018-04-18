package com.season.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by season on 2018/4/19.
 */

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_order")
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "food_time")
    private Integer foodTime;
    @Column(name = "order_status")
    private Integer orderStatus;
    @Column(name = "food_count")
    private Integer foodCount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "subscribe_date")
    private Date subscribeDate;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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
}
