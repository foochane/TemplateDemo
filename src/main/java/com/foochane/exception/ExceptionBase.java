package com.foochane.exception;

import com.foochane.enums.ResultEnum;

/**
 * Created by fucheng on 2017/7/14.
 */
public class ExceptionBase extends RuntimeException{

    private Integer code;

    public ExceptionBase(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
