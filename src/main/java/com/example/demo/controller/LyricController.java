/**
 * FileName:   LyricController
 * Author:     O了吗
 * Date:       2019/12/8 17:52
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.controller;

import com.example.demo.service2.builder.HttpBuilder;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */
@RestController
public class LyricController {

    @Autowired
    private HttpBuilder httpBuilder;

    @PostMapping("/lyric")
    public Object lyric(@RequestBody String id) throws IOException, ScriptException, ResponseMapperException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("lv", "-1");
        map.put("tv", "-1");
        return httpBuilder
                .post("https://music.163.com/weapi/song/lyric")
                .setNetEaseEntity(map)
                .nextToMapper()
                .setJsonMapper()
                .build();
    }

}