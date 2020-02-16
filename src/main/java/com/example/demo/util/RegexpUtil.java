/**
 * FileName:   RegexpUtil
 * Author:     O了吗
 * Date:       2019/12/14 21:54
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.util;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/14
 * @since 1.0.0
 */
public class RegexpUtil {

    public static String[] match(String s, Pattern pattern) {
        Matcher matcher = pattern.matcher(s);
        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list.toArray(new String[0]);
    }

    public static String matchFirst(String s, Pattern pattern) {
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static String[] matchGroup(String s, Pattern pattern) {
        Matcher matcher = pattern.matcher(s);
        ArrayList<String> list = new ArrayList<>();
        if (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                list.add(matcher.group(i));
            }
        }
        return list.toArray(new String[0]);
    }

}