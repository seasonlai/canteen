package com.season.service;

import com.season.dao.FoodDao;
import com.season.domain.Food;
import com.season.domain.FoodMultipart;
import com.season.exception.MyException;
import com.season.utils.MyFileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by season on 2018/4/15.
 */
@Service
public class FoodService {

    static Logger logger = Logger.getLogger(FoodService.class);

    @Autowired
    FoodDao foodDao;

    public void publish(FoodMultipart food) {

        if (StringUtils.isEmpty(food.getFoodName())
                || StringUtils.isEmpty(food.getFoodPrice())
                || StringUtils.isEmpty(food.getFoodKind())
                ) {
            throw new MyException("存在信息未填，请检查！");
        }
        if (food.getFile() == null || food.getFile().isEmpty() || food.getFile().getSize() > 1024 * 1024) {
            throw new MyException("图片为空或超过1M");
        }

        if (foodDao.getFoodByFoodName(food.getFoodName()) != null) {
            throw new MyException("餐品已存在");
        }

        String filePath = MyFileUtil.prepareWrite("/food");

        try {
            food.getFile().transferTo(new File(filePath, food.getFoodName()));
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new MyException("上传图片失败");
        }

        foodDao.save(food.getFood());
    }


    public void update(FoodMultipart food, String newImgUrl) {
        Food target = foodDao.load(food.getFoodId());
        if (food.getFile() != null && !food.getFile().isEmpty()) {
            String filePath = MyFileUtil.prepareWrite("/food");
            try {
                food.getFile().transferTo(new File(filePath, food.getFoodName()));
            } catch (IOException e) {
//                e.printStackTrace();
                logger.error(e.getMessage(), e);
                throw new MyException("上传图片失败");
            }
        }
        target.setFoodPrice(food.getFoodPrice());
        target.setFoodName(food.getFoodName());
        target.setFoodKind(food.getFoodKind());
        target.setFoodImage(newImgUrl);
//        target.set
        foodDao.update(target);
    }

    public List<Food> queryList(String name, int kind) {
        return foodDao.queryFoodByFoodNameAndKind(StringUtils.isEmpty(name) ? "" : name, kind);
    }

    public void del(Food food) {
        Food updateFood = foodDao.load(food.getFoodId());
        updateFood.setIsSaling(-1);
        foodDao.update(updateFood);
    }
}
