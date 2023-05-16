package com.example.filrougefo.exception.viewhandler;

import ch.qos.logback.classic.LoggerContext;
import com.example.filrougefo.exception.CategoryControllerException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class ExceptionViewHandler {
    private LoggerContext LogFactory;
    public final Logger logger = LogFactory.getLogger(getClass());

    @ExceptionHandler(CategoryControllerException.class)
    public ModelAndView handleError(HttpServletRequest req, CategoryControllerException ex) {

        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        //mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
