package com.qingcheng.web.handler;

import com.qingcheng.entity.Result;
import com.qingcheng.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Result handleKnownException(BaseException e){
        logger.error(e.getMessage(),e);
        return new Result(e.getCode(),e.getMsg());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleUnknownException(Exception e){
        logger.error(e.getMessage(),e);
        return new Result(0,e.getMessage());
    }
}
