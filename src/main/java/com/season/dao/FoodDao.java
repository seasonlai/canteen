package com.season.dao;

import com.season.domain.Food;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by season on 2018/4/15.
 */
@Repository
public class FoodDao extends BaseDao<Food> {

    private static final String GET_FOOD_BY_USERNAME = "from Food f where f.foodName = ?";

    private static final String QUERY_FOOD_BY_USERNAME = "from Food f where f.foodName like ?";



    public Food getFoodByFoodName(String foodName) {
        List list = hibernateTemplate.find(GET_FOOD_BY_USERNAME, foodName);
        if (list != null && list.size() > 0) {
            return (Food) list.get(0);
        }
        return null;
    }

    /**
     * 根据用户名为模糊查询条件，查询出所有包含的Food对象
     * @param foodName 用户名查询条件
     * @return 用户名前缀匹配的所有User对象
     */
    public List<Food> queryFoodByFoodName(String foodName){
        return (List<Food>)hibernateTemplate.find(QUERY_FOOD_BY_USERNAME, "%"+foodName +"%");
    }

}
