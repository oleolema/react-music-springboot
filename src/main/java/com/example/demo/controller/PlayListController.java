/**
 * FileName:   PlayListController
 * Author:     O了吗
 * Date:       2019/12/12 1:02
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.controller;

import com.example.demo.service2.builder.HttpBuilder;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import lombok.Data;
import lombok.experimental.Accessors;
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
public class PlayListController {
    @Autowired
    private HttpBuilder httpBuilder;

    @PostMapping("/playList")
    public Object playList(@RequestBody SimplePlayList simplePlayList) throws IOException, ResponseMapperException {
        return httpBuilder
                .get("https://music.163.com/api/playlist/detail?id=" + simplePlayList.id)
                .nextToMapper()
                .setJsonMapper()
                .build();
    }

    @Accessors(chain = true)
    @Data
    public static class SimplePlayList {
        String id;
    }
}