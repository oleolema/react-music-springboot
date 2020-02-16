/**
 * FileName:   Test2
 * Author:     O了吗
 * Date:       2019/12/8 17:50
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.nospring;

import com.example.demo.service.LyricService;
import com.example.demo.service.impl.LyricServiceImpl;
import com.example.demo.service2.builder.MapperBuilder;
import com.example.demo.service2.builder.RequestBuilder;
import com.example.demo.service2.builder.ResponseBuilder;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.example.demo.util.RegexpUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */
public class Test2 {


    @Test
    public void test1() throws IOException, ResponseMapperException {
        Object obj = RequestBuilder
                .get("https://music.163.com/api/v2/discovery/recommend/songs")
                .addCookie("MUSIC_U", "c13096c8c6b2bee66539eb55bb5ddf1424b43b277c39a79f5cede7b807d705fa354854624140130d8452bf9c7beb46f62d66926399c3d4a6e1501950a4179fa79f7b3d797d5889c9de39c620ce8469a8")
                .next()
                .next()
                .setJsonMapper(in -> in)
                .build();

        System.out.println(obj);
    }

    @Test
    public void test2() {
        String[] a = RegexpUtil.match("id=456456", Pattern.compile("(?<=id=)\\d+"));

        System.out.println(Arrays.toString(a));

    }


}