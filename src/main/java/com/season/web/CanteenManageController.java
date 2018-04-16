package com.season.web;
import com.season.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Wellhope on 2017/11/1.
 */
@Controller
public class CanteenManageController extends BaseController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/index.html"}, method = RequestMethod.GET)
    public String indexPage(HttpServletRequest request) {
        return "home";
    }



}
