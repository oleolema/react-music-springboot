/**
 * FileName:   GlobalController
 * Author:     O了吗
 * Date:       2019/12/10 17:06
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.controller;

import com.example.demo.service.GlobalService;
import com.example.demo.service2.builder.HttpBuilder;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.io.IOException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/10
 * @since 1.0.0
 */
@RestController
public class GlobalController {


    @Autowired
    private HttpBuilder httpBuilder;


    @PostMapping("/global")
    public Object global(@RequestBody RequestGlobal requestGlobal) throws IOException, ResponseMapperException {
        Object result = httpBuilder.post(requestGlobal.url)
                .setFormDataEntity(requestGlobal.encode)
                .nextToMapper()
                .setJsonMapper()
                .build();
        return result;
    }

    @Data
    public static class RequestGlobal {
        private String url;
        private String encode;
    }

}