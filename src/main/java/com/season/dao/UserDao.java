package com.season.dao;

import com.season.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Wellhope on 2017/10/31.
 */
@Repository
public class UserDao extends BaseDao<User> {

    private static final String GET_USER_BY_USERNAME = "from User u where u.userName = ?";

    private static final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";

    public User getUserByUserName(String userName) {
        List list = hibernateTemplate.find(GET_USER_BY_USERNAME, userName);
        if (list != null && list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    /**
     * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
     * @param userName 用户名查询条件
     * @return 用户名前缀匹配的所有User对象
     */
    public List<User> queryUserByUserName(String userName){
        return (List<User>)hibernateTemplate.find(QUERY_USER_BY_USERNAME,userName+"%");
    }
}
