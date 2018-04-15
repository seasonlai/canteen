package com.season.service;

import com.season.dao.FoodDao;
import com.season.domain.Food;
import com.season.domain.FoodMultipart;
import com.season.exception.MyException;
import com.season.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by season on 2018/4/15.
 */
@Service
public class FoodService {

    static Logger logger = Logger.getLogger(FoodService.class);

    @Autowired
    FoodDao foodDao;

    public void publish(FoodMultipart food) throws MyException {

        if (StringUtils.isEmpty(food.getFoodName())
                || StringUtils.isEmpty(food.getFoodPrice())
                || StringUtils.isEmpty(food.getFoodKind())
                ) {
            throw new MyException("存在信息未填，请检查！");
        }
        if (food.getFile().isEmpty() || food.getFile().getSize() > 1024 * 1024) {
            throw new MyException("图片为空或超过1M");
        }

        if (foodDao.getFoodByFoodName(food.getFoodName()) != null) {
            throw new MyException("餐品已存在");
        }

        String filePath = FileUtil.prepareWrite("/food");

        try {
            food.getFile().transferTo(new File(filePath, food.getFoodName()));
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }

        food.setPublishDate(new Date());
        food.setFile(null);
        foodDao.save(food);
    }

}
