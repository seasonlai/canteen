package com.season.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by season on 2018/4/15.
 */
public class FoodMultipart extends  Food{
    private MultipartFile file;

    private int kindCode;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {

        this.file = file;
    }

    public int getKindCode() {
        return kindCode;
    }

    public void setKindCode(int kindCode) {
        this.kindCode = kindCode;
    }

    public Food getFood(){
        Food food = new Food();
        food.setFoodId(getFoodId());
        food.setFoodImage(getFoodImage());
        food.setFoodKind(getFoodKind());
        food.setFoodName(getFoodName());
        food.setPublishDate(new Date());
        food.setFoodPrice(getFoodPrice());
        return food;
    }
}
