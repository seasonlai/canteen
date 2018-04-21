package com.season.web;

import com.season.cons.CommonConstant;
import com.season.domain.User;
import com.season.exception.MyException;
import com.season.exception.UserExistException;
import com.season.service.UserService;
import com.season.utils.MyFileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login/doLogin")
    public ModelAndView login(HttpServletRequest request, User user) {
        User dbUser = userService.getUserByUserName(user.getUserName());
        ModelAndView mav = new ModelAndView("forward:/login.jsp");
        if (dbUser == null) {
            mav.addObject("errorMsg", "用户名不存在");
        } else if (!dbUser.getPassword().equals(user.getPassword())) {
            mav.addObject("errorMsg", "用户名或密码不对");
        } else if (dbUser.getLocked() == User.USER_LOCK) {
            mav.addObject("errorMsg", "用户被锁定，无法登录");
        } else {
            dbUser.setLastIp(request.getRemoteAddr());
            dbUser.setLastVisit(new Date());
            userService.loginSuccess(dbUser);
            setSessionUser(request, dbUser);
            String toUrl = (String) request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
            //如果当前会话中没有保存登录之前的请求URL，则直接跳转到主页
            if (StringUtils.isEmpty(toUrl)) {
                toUrl = "/index.html";
            }
            mav.setViewName("redirect:" + toUrl);
        }
        return mav;
    }

    /**
     * 登录注销
     *
     * @param session
     * @return
     */
    @RequestMapping("/login/doLogout")
    public String logout(HttpSession session) {
        session.removeAttribute(CommonConstant.USER_CONTEXT);
        return "forward:/index.jsp";
    }

    @RequestMapping(value = "/login/doRegister", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("password") String pwd,
                                 @RequestParam("idCardImg") MultipartFile idCardImgFile) {

        ModelAndView mav = new ModelAndView("/success");
        User user = new User();
        try {
            user.setUserName(userName);
            user.setPassword(pwd);
            userService.register(user, idCardImgFile, getWholeUrl(request,"/user/idcard-" + user.getUserName()));
        } catch (MyException e) {
            mav.addObject("errorMsg", e.getMessage());
            mav.setViewName("forward:/register.jsp");
            return mav;
        }
        setSessionUser(request, user);
        return mav;
    }


    @RequestMapping(value = "/login/login.html", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        return "forward:/login.jsp";
    }

    @RequestMapping(value = "/login/register.html", method = RequestMethod.GET)
    public String registerPage(HttpServletRequest request) {
        return "forward:/register.jsp";
    }

    @RequestMapping(value = "/login/user.html", method = RequestMethod.GET)
    public String userPage(HttpServletRequest request) {
        return "user";
    }


    @RequestMapping("/user/idcard-{fileName}")
    public ResponseEntity<byte[]> getCountDataLog(@PathVariable("fileName") String fileName,
                                                  HttpServletResponse response) {

//        fileName = fileName.endsWith(".log")?fileName:fileName+".log";
        File file = MyFileUtil.getServerFile("/images/" + fileName);
        try {
            HttpHeaders headers = new HttpHeaders();
            //下载显示的文件名，解决中文名称乱码问题
            String downloadFielName = new String(fileName.getBytes("UTF-8"),
                    "iso-8859-1");
            //通知浏览器以attachment（下载方式）打开图片
//            headers.setContentDispositionFormData("attachment", downloadFielName);
            //application/octet-stream ： 二进制流数据（最常见的文件下载）。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        } catch (IOException e) {
            Logger.getLogger(LoginController.class).error(e.getMessage(), e);
        }
        return null;
    }

}