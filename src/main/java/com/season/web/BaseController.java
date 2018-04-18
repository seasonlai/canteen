package com.season.web;

import com.season.cons.CommonConstant;
import com.season.domain.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wellhope on 2017/10/28.
 */
public class BaseController {

    protected static final String ERROR_MSG_KEY = "errorMsg";

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }

    protected void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
    }

    /**
     * 返回/canteen/url
     *
     * @param request
     * @param url
     * @return
     */
    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "url必须以/开头");
        return request.getContextPath() + url;
    }

    /**
     * 返回http://xxxx:8080/canteen/url
     *
     * @param request
     * @param url
     * @return
     */
    public final String getWholeUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "url必须以/开头");
        StringBuffer sb = request.getRequestURL();
        int i = sb.indexOf(request.getContextPath());
        sb.delete(i + request.getContextPath().length(), sb.length());
        return sb.append(url).toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(MultipartFile.class, new StringTrimmerEditor(true));
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }


    public List<Map> getMealKind(){
        List<Map> supportCount = new ArrayList<>();
        for (int i = 0; i < CommonConstant.MEAL_KIND.length; i++) {
            Map<String, Object> kv = new HashMap<>();
            kv.put("code", i);
            kv.put("name", CommonConstant.MEAL_KIND[i]);
            supportCount.add(kv);
        }
        return supportCount;
    }
}
