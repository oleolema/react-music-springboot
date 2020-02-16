/**
 * FileName:   DiscoverImageController
 * Author:     O了吗
 * Date:       2019/12/14 17:20
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.controller;

import com.example.demo.service2.builder.HttpBuilder;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.example.demo.util.RegexpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptEngine;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/discover")
public class DiscoverImageController {

    @Autowired
    private HttpBuilder httpBuilder;

    @Autowired
    private ScriptEngine scriptEngine;

    @Autowired
    private ObjectMapper objectMapper;

    static final Pattern ID_PATTERN = Pattern.compile("(?<=id=)\\d+");

    @PostMapping("/image")
    public Object image() throws IOException, ResponseMapperException {
        return httpBuilder.get("https://music.163.com/discover")
                .nextToMapper()
                .setDocumentMapper(document -> {
                    Elements scripts = document.select("script[type]");
                    String imageScript = null;
                    for (Element script : scripts) {
                        //找到script为图片的脚本
                        if (script.html().length() >= 20 && script.html().substring(0, 20).contains("Gbanners")) {
                            imageScript = script.html();
                            break;
                        }
                    }
                    //将返回结果转换成字符串
                    imageScript = "var window = this;" + imageScript + "JSON.stringify(window.Gbanners) ";
                    String result = (String) scriptEngine.eval(imageScript);
                    return objectMapper.readValue(result, Object[].class);
                })
                .build();
    }

    @PostMapping("/playlist")
    public Object playlist() throws IOException, ResponseMapperException {
        return httpBuilder.get("https://music.163.com/discover/playlist")
                .nextToMapper()
                .setDocumentMapper(document -> {
                    Elements ul = document.selectFirst("#m-pl-container").children();
                    HashSet<SimplePlay> simplePlays = new HashSet<>();
                    for (Element li : ul) {
                        SimplePlay simplePlay = new SimplePlay();
                        Element msk = li.selectFirst(".msk");
                        simplePlay
                                .setPicUrl(li.selectFirst(".j-flag").attr("src"))
                                .setTitle(msk.attr("title"))
                                .setId(RegexpUtil.matchFirst(msk.attr("href"), ID_PATTERN))
                                .setUsername(li.selectFirst(".nm.nm-icn.f-thide").attr("title"))
                                .setAmount(li.selectFirst(".nb").text());
                        simplePlays.add(simplePlay);
                    }
                    return simplePlays;
                })
                .build();
    }


    @Accessors(chain = true)
    @Data
    static class SimplePlay implements Serializable {
        private String id;
        private String picUrl;
        private String title;

        private String amount;
        private String username;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SimplePlay that = (SimplePlay) o;
            return that.id.equals(id);
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

}