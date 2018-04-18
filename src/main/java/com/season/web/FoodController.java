package com.season.web;

import com.season.cons.CommonConstant;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/food/food_book.html")
    public ModelAndView food_book() {
        ModelAndView mav =new ModelAndView("food_book");

        List<FoodKind> foodKinds = foodKindDao.loadAll();
        mav.addObject("foodKinds",foodKinds);

        List<Map> supportCount = new ArrayList<>();
        for (int i = 0; i < CommonConstant.MEAL_KIND.length; i++) {
            Map<String, Object> kv = new HashMap<>();
            kv.put("code", i);
            kv.put("name", CommonConstant.MEAL_KIND[i]);
            supportCount.add(kv);
        }

        mav.addObject("foodTime",supportCount);
        return mav;
    }

    @RequestMapping("/food/food_manager.html")
    public ModelAndView food_manager() {
        ModelAndView mav =new ModelAndView("food_manager");

        List<FoodKind> foodKinds = foodKindDao.loadAll();
        mav.addObject("foodKinds",foodKinds);
        return mav;
    }


    @RequestMapping(value = "/food/food_edit-add", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean food_add(HttpServletRequest request, FoodMultipart food) {

        FoodKind foodKind = foodKindDao.load(food.getKindCode());
        if(foodKind==null)
            return new MsgBean().setCode(-1).setMsg("不支持的类别");

        food.setFoodKind(foodKind);
        food.setFoodImage(getWholeUrl(request, "/food/img-" + food.getFoodName()));

        try {
            foodService.publish(food);
        } catch (MyException e) {
            return new MsgBean().setCode(-1)
                    .setMsg(e.getMessage());
        }

        return new MsgBean().setCode(0)
                .setMsg("添加成功");
    }


    @RequestMapping(value = "/food/food_edit-update", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean food_update(HttpServletRequest request, FoodMultipart food) {
        FoodKind foodKind = foodKindDao.load(food.getKindCode());
        if(foodKind==null)
            return new MsgBean().setCode(-1).setMsg("不支持的类别");
        food.setFoodKind(foodKind);

        try {
            foodService.update(food);
        } catch (MyException e) {
            return new MsgBean().setCode(-1)
                    .setMsg(e.getMessage());
        }

        return new MsgBean().setCode(0)
                .setMsg("修改成功");
    }

    @RequestMapping(value = "/food/food_edit-del", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean food_del(Food food) {

        try {
            foodService.del(food);
        } catch (MyException e) {
            return new MsgBean().setCode(-1)
                    .setMsg(e.getMessage());
        }
        return new MsgBean().setCode(0)
                .setMsg("删除成功");
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


    @RequestMapping("/food/food_list")
    @ResponseBody
    public MsgBean<List<Food>> query_food_list(@RequestParam("foodName")String name,@RequestParam("kind")int kind){
        List<Food> foods = foodService.queryList(name, kind);
        return new MsgBean<List<Food>>().setCode(0)
                .setData(foods);
    }


}
