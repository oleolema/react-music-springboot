/**
 * FileName:   ResponseType
 * Author:     O了吗
 * Date:       2019/12/3 21:36
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.util;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/3
 * @since 1.0.0
 */

public enum ResponseType {
    //响应成功
    success("success", 0),

    //响应失败
    error("error", 1);

    private String key;
    private int code;
    //响应状态码
    public static String CODE = "code";
    //错误信息
    public static String ERR_MSG = "errMsg";
    //数据
    public static String DATA = "data";
    //异常信息
    public static String THROW = "throw";

    ResponseType(String key, int code) {
        this.key = key;
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}