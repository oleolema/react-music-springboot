/**
 * FileName:   MapsUtil
 * Author:     O了吗
 * Date:       2019/12/3 16:06
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.util;

import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/3
 * @since 1.0.0
 */
public class MapsUtil {


    /**
     * map = {a:1,b:2,c:{d:[0,0,1]}}
     * 依次获取map中的内容  getIn(map, "c","d",2);      //1
     * getIn(map, "b");      //2
     *
     * @param obj
     * @param key
     * @return
     */

    public static <T> T getIn(Object obj, Class<T> clazz, Object... key) {
        check(obj);
        if (String.class.isAssignableFrom(clazz)) {
            return (T) getIn0(obj, 0, key).toString();
        }
        return (T) getIn0(obj, 0, key);
    }

    public static Object getIn(Object obj, Object... key) {
        check(obj);
        return getIn0(obj, 0, key);
    }

    private static boolean check(Object obj) {
        if (obj instanceof Map || obj instanceof List) {
            return true;
        }
        throw new RuntimeException("只能是List 或者 Map");
    }


    private static Object getIn0(Object obj, int index, Object... key) {
        if (obj == null) {
            return null;
        }
        if (index == key.length) {
            return obj;
        }
        if (obj instanceof Map) {
            return getIn0(((Map) obj).get(key[index]), index + 1, key);
        } else if (obj instanceof List) {
            try {
                return getIn0(((List) obj).get(Integer.parseInt(key[index].toString())), index + 1, key);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return null;
    }


}