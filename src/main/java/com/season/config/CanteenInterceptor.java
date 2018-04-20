package com.season.config;

import com.season.cons.CommonConstant;
import com.season.domain.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.season.cons.CommonConstant.LOGIN_TO_URL;

/**
 * Created by Wellhope on 2018/4/19.
 */
public class CanteenInterceptor extends HandlerInterceptorAdapter {

    // ① 不需要登录即可访问的URI资源
    private static final String[] INHERENT_ESCAPE_URIS = {"/index.jsp",
            "/index.html", "/login.jsp", "/login/doLogin",
            "/register.jsp", "/register.html", "/login/doRegister",
    };

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //一系列处理后发现session已经失效
        if (req.getHeader("x-requested-with") != null
                && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有x-requested-with
            HttpServletRequest httpRequest = (HttpServletRequest) req;
            User userContext = (User) httpRequest.getSession().getAttribute(CommonConstant.USER_CONTEXT);
            // ②-3 用户未登录, 且当前URI资源需要登录才能访问
            if (userContext == null
                    && !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
                String toUrl = httpRequest.getRequestURL().toString();
                if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
                    toUrl += "?" + httpRequest.getQueryString();
                }

                // ②-4将用户的请求URL保存在session中，用于登录成功之后，跳到目标URL
                httpRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl);
                // 重定向
                String path = req.getContextPath();
                String basePath = req.getScheme() + "://" + req.getServerName() + ":"
                        + req.getServerPort() + path + "/";

                // ②-5转发到登录页面
                resp.setHeader("SESSIONSTATUS", "TIMEOUT");
                resp.setHeader("CONTEXTPATH", basePath + "login.jsp");
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);//403 禁止
                return false;
            }
        } else {
            //非ajax请求时，session失效的处理
        }
        return true;
    }

    private boolean isURILogin(String requestURI, HttpServletRequest request) {
        if (request.getContextPath().equalsIgnoreCase(requestURI)
                || (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
            return true;
        for (String uri : INHERENT_ESCAPE_URIS) {
            if (requestURI != null && requestURI.contains(uri)) {
                return true;
            }
        }

        return false;
    }
}
