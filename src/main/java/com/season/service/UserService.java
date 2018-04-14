package com.season.service;

import com.season.dao.LoginLogDao;
import com.season.dao.UserDao;
import com.season.domain.LoginLog;
import com.season.domain.User;
import com.season.exception.MyException;
import com.season.exception.UserExistException;
import com.season.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public void register(User user, MultipartFile idCardImgFile) throws MyException {

        //判空
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            throw new MyException("用户名或密码为空");
        }
        if (idCardImgFile.isEmpty() || idCardImgFile.getSize() > 1024 * 1024) {
            throw new MyException("学生卡图片为空，或超过限制的大小");
        }

        User u = this.getUserByUserName(user.getUserName());
        if (u != null)
            throw new UserExistException("用户名已经存在");
        else {
            user.setUserType(User.NORMAL_USER);

            //上传文件路径
            String path = FileUtil.prepareWrite("images/");
            //上传文件名
//                String filename = idCardImgFile.getOriginalFilename();
            try {
                idCardImgFile.transferTo(new File(path, user.getUserName()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new MyException("上传图片失败");
            }
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
