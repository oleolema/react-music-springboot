/**
 * FileName:   Test1
 * Author:     O了吗
 * Date:       2019/12/8 16:07
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.nospring;

import com.example.demo.util.NetEaseEncode;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import javax.script.Invocable;
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
public class Test1 {

    @Test
    public void test1() throws ScriptException, IOException, NoSuchMethodException {
        ScriptEngineManager scriptEngineManager =
                new ScriptEngineManager();
        ScriptEngine nashorn =
                scriptEngineManager.getEngineByName("nashorn");

        Invocable invocable = (Invocable) nashorn;

        String mainjs = IOUtils.toString(Test1.class.getClassLoader().getResource("common/js/encode.js"), StandardCharsets.UTF_8);
        nashorn.eval(mainjs);

        System.out.println(nashorn.eval("neteaseEncode({a:1})"));

    }

    @Test
    public void test2() throws ScriptException {
        String a = NetEaseEncode.encode("{encodeType: \"aac\"," +
                "ids: \"[1381755293]\"," +
                "level: \"standard\"}");
        System.out.println(a);



    }

}