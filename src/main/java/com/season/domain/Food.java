package com.season.domain;

import org.hibernate.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by season on 2018/4/15.
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_food")
public class Food extends BaseDomain{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int foodId;
    @Column(name = "food_image")
    private String foodImage;
    @Column(name = "food_price")
    private float foodPrice;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "publish_date")
    private Date publishDate;
    @ManyToOne
    @JoinColumn(name = "kind_code")
    private FoodKind foodKind;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public FoodKind getFoodKind() {
        return foodKind;
    }

    public void setFoodKind(FoodKind foodKind) {
        this.foodKind = foodKind;
    }
}
