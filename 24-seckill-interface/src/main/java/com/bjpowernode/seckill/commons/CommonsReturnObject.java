package com.bjpowernode.seckill.commons;

import java.io.Serializable;

/**
 * ClassName:CommonsReturnObject
 * <p>company:www.bjpowernode.com</p>
 *
 * @Date 2018/4/3 11:23
 * @Author 724班
 */
public class CommonsReturnObject implements Serializable {

    private static final long serialVersionUID = 1165683405454096168L;

    private String errorCode;

    private String errorMessage;

    private Object data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}