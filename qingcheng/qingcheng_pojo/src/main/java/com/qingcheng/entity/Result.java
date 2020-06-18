package com.qingcheng.entity;

import java.io.Serializable;

/**
 * 插入更新操作的返回结果类
 */
public class Result implements Serializable {
    private Integer code; //1代表成功,0代表失败
    private String message;//返回的信息

    public Result() {
        this.code = 1;
        this.message = "成功";
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
