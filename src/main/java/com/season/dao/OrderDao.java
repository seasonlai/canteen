package com.season.dao;

import com.season.domain.MyOrder;
import com.season.domain.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wellhope on 2018/4/19.
 */
@Repository
public class OrderDao extends BaseDao<MyOrder> {

    private static final String QUERY_ORDER_LIST_BY_USER =
            "from MyOrder d where d.user = ?";


    public List queryOrderByUser(User user, Integer orderStatus) {
        if (orderStatus == null || orderStatus < 0)
            return find(QUERY_ORDER_LIST_BY_USER, user);
        return find(QUERY_ORDER_LIST_BY_USER + " and d.orderStatus=?", user, orderStatus);
    }

    public Page queryOrderByCondition(Integer pageNum, Integer pageSize, Date date, Integer foodKind, Integer foodTime, String content) {
        if (date == null) {
            return null;
        }
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer("select d.food.foodName,sum(d.foodCount),d.foodTime ");
        sb.append("from MyOrder d where d.subscribeDate = ? ");
        params.add(date);
        if (foodKind != null && foodKind >= 0) {
            sb.append("and d.food.foodKind = ? ");
            params.add(foodKind);
        }
        if (foodTime != null && foodTime >= 0) {
            sb.append("and d.foodTime = ? ");
            params.add(foodTime);
        }
        if (!StringUtils.isEmpty(content)) {
            sb.append("and d.food.foodName like %")
                    .append(content)
                    .append("% ");
            params.add(content);
        }
        sb.append("and d.orderStatus = ? ");
        params.add(1);
        sb.append("group by d.food.foodName");
        Page page = pagedQuery(sb.toString(), pageNum, pageSize, params.toArray());
        return page;
    }

}
