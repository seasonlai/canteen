package com.season.dao;

import com.season.domain.MyOrder;
import com.season.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Wellhope on 2018/4/19.
 */
@Repository
public class OrderDao extends BaseDao<MyOrder> {

    private static final String QUERY_ORDER_LIST_BY_USER =
            "from MyOrder d where d.user = ?";


    public List queryOrderByUser(User user) {
        return find(QUERY_ORDER_LIST_BY_USER, user);
    }

    public List queryOrderByCondition(Date date, Integer foodKind, Integer foodTime, String content){

        StringBuffer sb = new StringBuffer("select ");

        return null;
    }

}
