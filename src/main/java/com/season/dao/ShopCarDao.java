package com.season.dao;

import com.season.domain.ShopCar;
import com.season.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by season on 2018/4/19.
 */
@Repository
public class ShopCarDao extends BaseDao<ShopCar> {

    private final String QUERY_CAR_BY_USER =
            "from ShopCar s where s.user = ?";


    public List<ShopCar> queryByUser(User user) {

        return find(QUERY_CAR_BY_USER, user);
    }

}
