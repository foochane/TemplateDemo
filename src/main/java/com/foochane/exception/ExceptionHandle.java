package com.foochane.exception;

import com.foochane.enums.ResultEnum;
import com.foochane.utils.Result;
import com.foochane.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fucheng on 2017/7/14.
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof PersonException) {
            PersonException girlException = (PersonException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg() );
        }
    }
}