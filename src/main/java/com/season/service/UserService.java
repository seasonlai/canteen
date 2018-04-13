package com.season.service;

import com.season.dao.LoginLogDao;
import com.season.dao.UserDao;
import com.season.domain.LoginLog;
import com.season.domain.User;
import com.season.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wellhope on 2017/10/31.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public void register(User user) throws UserExistException {
        User u = this.getUserByUserName(user.getUserName());
        if (u != null)
            throw new UserExistException("用户名已经存在");
        else {
            user.setUserType(User.NORMAL_USER);
            userDao.save(user);
        }
    }

    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    public User getUserById(int id) {
        return userDao.get(id);
    }

    public void lockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_LOCK);
        userDao.update(user);
    }

    public void unlockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_UNLOCK);
        userDao.update(user);
    }

    public void loginSuccess(User user) {
        LoginLog log = new LoginLog();
        log.setIp(user.getLastIp());
        log.setLoginDate(user.getLastVisit());
        log.setUser(user);
        loginLogDao.save(log);
        userDao.update(user);
    }

    public List<User> getAllUser() {
        return userDao.loadAll();
    }
}
