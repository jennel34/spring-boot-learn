package com.connext.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonException {

    private static final Logger logger = LoggerFactory.getLogger(CommonException.class);

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(RuntimeException exception){
        logger.info("自定义异常处理-RuntimeException");
        ModelAndView m = new ModelAndView();
        m.addObject("roncooException",exception.getMessage());
        m.setViewName("/error/501.html");
        return m;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(Exception exception){
        logger.info("自定义异常处理-Exception");
        ModelAndView m = new ModelAndView();
        m.addObject("roncooException",exception.getMessage());
        m.setViewName("/error/500.html");
        return m;
    }
}
