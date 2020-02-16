/**
 * FileName:   ResponseUtil
 * Author:     O了吗
 * Date:       2019/12/3 21:35
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/3
 * @since 1.0.0
 */
public class ResponseUtil {


    public static Map<String, Object> success(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(ResponseType.CODE, ResponseType.success.getCode());
        map.put(ResponseType.DATA, data);
        return map;
    }

    public static Map<String, Object> error(String errMsg, Throwable throwable) {
        Map<String, Object> map = error(errMsg);
        map.put(ResponseType.THROW, throwable);
        return map;
    }

    public static Map<String, Object> error(String errMsg) {
        Map<String, Object> map = new HashMap<>();
        map.put(ResponseType.CODE, ResponseType.error.getCode());
        map.put(ResponseType.ERR_MSG, errMsg);
        return map;
    }

}