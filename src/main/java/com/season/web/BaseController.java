package com.season.web;
import com.season.cons.CommonConstant;
import com.season.domain.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

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

    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "url必须以/开头");
        return request.getContextPath() + url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
