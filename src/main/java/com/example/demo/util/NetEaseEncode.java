/**
 * FileName:   NeteaseUtil
 * Author:     O了吗
 * Date:       2019/12/8 16:36
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.util;

import org.apache.commons.io.IOUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */
public class NetEaseEncode {

    private static ScriptEngineManager scriptEngineManager =
            new ScriptEngineManager();
    private static ScriptEngine nashorn =
            scriptEngineManager.getEngineByName("nashorn");
    private static String mainJs = null;

    static {
        try {
            mainJs = IOUtils.toString(NetEaseEncode.class.getClassLoader().getResource("common/js/encode.js"), StandardCharsets.UTF_8);
            nashorn.eval(mainJs);
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }
    }

    public static String encode(String json) throws ScriptException {
        return (String) nashorn.eval("neteaseEncode(" + json + ")");
    }




}