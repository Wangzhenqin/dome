package com.imooc.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhenqin on 2019/6/14.
 */
@ControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private Map<String,Object> exceptionHandler(HttpServletRequest req,Exception e) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", false);
        modelMap.put("errMsq", e.getMessage());
        return modelMap;
    }
}
