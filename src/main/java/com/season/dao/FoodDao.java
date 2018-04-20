package com.season.dao;

import com.season.domain.Food;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by season on 2018/4/15.
 */
@Repository
public class FoodDao extends BaseDao<Food> {

    private static final String GET_FOOD_BY_FOODNAME = "from Food f where f.isSaling = 0 and f.foodName = ?";

    private static final String QUERY_FOOD_BY_FOODNAME = "from Food f where f.isSaling = 0 and f.foodName like ?";

    private static final String QUERY_FOOD_BY_USERNAME_KIND = "from Food f where f.isSaling = 0 and f.foodName like ? and f.foodKind.kindCode = ?";



    public Food getFoodByFoodName(String foodName) {
        List list = hibernateTemplate.find(GET_FOOD_BY_FOODNAME, foodName);
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
    public List<Food> queryFoodByFoodNameAndKind(String foodName,int... kindCode){

        if(kindCode==null||kindCode[0]<=0){
            return (List<Food>)hibernateTemplate.find(QUERY_FOOD_BY_FOODNAME, "%"+foodName +"%");
        }


        return  (List<Food>)hibernateTemplate.find(QUERY_FOOD_BY_USERNAME_KIND,"%"+foodName +"%",kindCode[0]);
    }

}
