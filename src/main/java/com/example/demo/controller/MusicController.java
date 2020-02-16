/**
 * FileName:   MusicController
 * Author:     O了吗
 * Date:       2019/12/12 1:00
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
 * @create 2019/12/12
 * @since 1.0.0
 */
@RestController
public class MusicController {
    @Autowired
    private HttpBuilder httpBuilder;

    @PostMapping("/music")
    public Object music(@RequestBody String id) throws IOException, ScriptException, ResponseMapperException {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", "[" + id + "]");
        //编码格式
        map.put("encodeType", "aac");
        //音质
        map.put("level", "standard");
        return httpBuilder
                .post("https://music.163.com/weapi/song/enhance/player/url/v1")
                .setNetEaseEntity(map)
                .nextToMapper()
                .setJsonMapper()
                .build();
    }
}