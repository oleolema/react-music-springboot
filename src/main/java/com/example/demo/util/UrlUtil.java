/**
 * FileName:   UrlUtil
 * Author:     O了吗
 * Date:       2019/12/8 17:34
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.util;

import java.util.LinkedHashMap;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */
public class UrlUtil {

    public static LinkedHashMap<String,String> stringToFormData(String s) {
        String[] kvs = s.split("&");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (String kv : kvs) {
            String[] k0 = kv.split("=");
            if (k0.length == 1) {
                map.put(k0[0], "");
            } else {
                map.put(k0[0], k0[1]);
            }
        }
        return map;
    }

}