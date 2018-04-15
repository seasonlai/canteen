package com.season.web;

import com.season.dao.FoodKindDao;
import com.season.domain.Food;
import com.season.domain.FoodKind;
import com.season.domain.FoodMultipart;
import com.season.domain.MsgBean;
import com.season.exception.MyException;
import com.season.service.FoodService;
import com.season.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class FoodController extends BaseController {

    static Logger logger = Logger.getLogger(FoodController.class);

    @Autowired
    FoodService foodService;
    @Autowired
    FoodKindDao foodKindDao;

    @RequestMapping("/food/food_book")
    public String food_book() {
        return "food_book";
    }

    @RequestMapping("/food/food_manager")
    public ModelAndView food_manager() {
        ModelAndView mav =new ModelAndView("food_manager");

        List<FoodKind> foodKinds = foodKindDao.loadAll();
        mav.addObject("foodKinds",foodKinds);
        return mav;
    }


    @RequestMapping(value = "/food/food_edit-add", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean food_add(HttpServletRequest request, FoodMultipart food) {

        food.setFoodImage(getAppbaseUrl(request, "/food/img-" + food.getFoodName() + ".html"));
        try {
            foodService.publish(food);
        } catch (MyException e) {
            return new MsgBean().setCode(-1)
                    .setMsg(e.getMessage());
        }

        return new MsgBean().setCode(0)
                .setMsg("添加成功");
    }

    @RequestMapping("/food/img-{foodName}")
    public void get_food_img(@PathVariable("foodName") String foodName,
                             HttpServletResponse response) {

        File foodImg = FileUtil.getServerFile("/food/" + foodName);

        if (foodImg.exists()) {
            try (FileInputStream fis = new FileInputStream(foodImg)){
                byte[] buf = new byte[1024];
                int len;
                while ((len = fis.read(buf, 0, buf.length)) > 0) {
                    response.getOutputStream().write(buf, 0, len);
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }

}
