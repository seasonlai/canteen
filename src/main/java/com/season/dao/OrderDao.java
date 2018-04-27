package com.season.dao;

import com.season.domain.MyOrder;
import com.season.domain.User;
import com.season.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
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

    private static final String QUERY_ORDER_LIST_BY_DATE =
            "from MyOrder d where d.subscribeDate = ? ";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public Page queryOrderByUser(User user, Integer orderStatus, Integer pageNum, Integer pageSize) {
        if (orderStatus == null || orderStatus < 0)
            return pagedQuery(QUERY_ORDER_LIST_BY_USER,pageNum,pageSize, user);
        return pagedQuery(QUERY_ORDER_LIST_BY_USER + " and d.orderStatus=?",pageNum,pageSize, user, orderStatus);
    }

    public Page queryOrderByCondition(Integer pageNum, Integer pageSize, Date date, Integer foodKind, Integer foodTime, String content) {
        if (date == null) {
            return null;
        }
        List<Object> params_hql = new ArrayList<>();
        List<Object> params_sql = new ArrayList<>();
        StringBuffer sb_hql = new StringBuffer("select d.food.foodName,sum(d.foodCount),d.foodTime ");
        StringBuffer sb_sql = new StringBuffer("select count(*) from (select t.food_id ");
        sb_hql.append("from MyOrder d where d.subscribeDate = ? ");
        sb_sql.append("from t_order t LEFT JOIN t_food f on t.food_id = f.food_id where t.subscribe_date = ? ");
        params_hql.add(date);
        params_sql.add(DateUtil.convert2String(date, DATE_FORMAT));
        if (foodKind != null && foodKind >= 0) {
            sb_hql.append("and d.food.foodKind.kindCode = ? ");
            sb_sql.append("and f.kind_code = ? ");
            params_hql.add(foodKind);
            params_sql.add(foodKind);
        }
        if (foodTime != null && foodTime >= 0) {
            sb_hql.append("and d.foodTime = ? ");
            sb_sql.append("and t.food_time = ? ");
            params_hql.add(foodTime);
            params_sql.add(foodTime);
        }
        if (!StringUtils.isEmpty(content)) {
            sb_hql.append("and d.food.foodName like ?");
            sb_sql.append("and f.food_name like ?");
            params_hql.add("%"+content+"%");
            params_sql.add("%"+content+"%");
        }
        sb_hql.append("and d.orderStatus = ? ");
        sb_sql.append("and t.order_status = ? ");
        params_hql.add(1);
        params_sql.add(1);
        sb_hql.append("group by d.food.foodId");
        sb_sql.append("group by t.food_id) r");

        List list = runSql(sb_sql.toString(), params_sql.toArray());
        BigInteger count = list.size() > 0 ? (BigInteger) list.get(0) : BigInteger.ZERO;

        Page page = pagedQuery(sb_hql.toString(), count.longValue(), pageNum, pageSize, params_hql.toArray());
        return page;
    }
    public Page queryOrderByCondition2(Integer pageNum, Integer pageSize, Date date, String content) {
        if (date == null) {
            return null;
        }
        List<Object> params_hql = new ArrayList<>();
        StringBuffer sb_hql = new StringBuffer(QUERY_ORDER_LIST_BY_DATE);
        params_hql.add(date);
        if (!StringUtils.isEmpty(content)) {
            sb_hql.append(" and d.food.foodName like ?");
            params_hql.add("%"+content+"%");
        }
        Page page = pagedQuery(sb_hql.toString(),
                 pageNum, pageSize, params_hql.toArray());
        return page;
    }
}
