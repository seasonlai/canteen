package com.season.web;

import com.season.cons.CommonConstant;
import com.season.domain.User;
import com.season.exception.MyException;
import com.season.exception.UserExistException;
import com.season.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
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
            userService.register(user, idCardImgFile);
        } catch (MyException e) {
            mav.addObject("errorMsg", e.getMessage());
            mav.setViewName("forward:/register.jsp");
            return mav;
        }
        setSessionUser(request,user);
        return mav;
    }


    @RequestMapping(value = "/login/login.html", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        return "forward:/login.jsp";
    }

    @RequestMapping(value = "/login/register.html", method = RequestMethod.GET)
    public String registerPage(HttpServletRequest request) {
        System.out.println("register================================");
        return "forward:/register.jsp";
    }

}