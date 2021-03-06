package com.season.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by season on 2018/4/18.
 */

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_shop_car")
public class ShopCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carId;
    @Column(name = "food_time")
    private Integer foodTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @Column(name = "subscribe_date")
    private Date subscribeDate;
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

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public Float getFoodTotalPrice() {
        return foodTotalPrice;
    }

    public void setFoodTotalPrice(Float foodTotalPrice) {
        this.foodTotalPrice = foodTotalPrice;
    }

    public Integer getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(Integer foodCount) {
        this.foodCount = foodCount;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(Integer foodTime) {
        this.foodTime = foodTime;
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
}
