package com.season.exception;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 */
public class CanteenHandlerExceptionResolver extends SimpleMappingExceptionResolver {

    static final Logger logger = Logger.getLogger(CanteenHandlerExceptionResolver.class);

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object o, Exception e) {

        e.printStackTrace();
        if (e instanceof MyException){
            request.setAttribute("ex", e);
        }else {
            request.setAttribute("ex", e);
            logger.error(e.getMessage(), e);
        }
        return super.doResolveException(request, response, o, e);
    }
}
