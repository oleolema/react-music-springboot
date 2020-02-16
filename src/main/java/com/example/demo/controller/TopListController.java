/**
 * FileName:   TopListController
 * Author:     O了吗
 * Date:       2020/1/3 12:45
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.controller;

import com.example.demo.service2.builder.HttpBuilder;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.example.demo.util.RegexpUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2020/1/3
 * @since 1.0.0
 */
@RestController
public class TopListController {
    @Autowired
    private HttpBuilder httpBuilder;

    @PostMapping("/topList")
    public Object playlist() throws IOException, ResponseMapperException {
        return httpBuilder.get("https://music.163.com/discover/toplist")
                .nextToMapper()
                .setDocumentMapper(document -> {
                    Elements uls = document.select("#toplist .n-minelst.n-minelst-2 ul.f-cb");
                    LinkedHashSet<SimpleTop> simpleTops = new LinkedHashSet<>();
                    for (Element ul : uls) {
                        for (Element li : ul.children()) {
                            SimpleTop simpleTop = new SimpleTop()
                                    .setId(li.attr("data-res-id"))
                                    .setTitle(li.selectFirst(".name").text())
                                    .setDesc(li.selectFirst(".s-fc4").text())
                                    .setPicUrl(li.selectFirst("img").attr("src"));
                            simpleTops.add(simpleTop);
                        }
                    }
                    return simpleTops;
                })
                .build();
    }

    @Data
    @Accessors(chain = true)
    static class SimpleTop {
        String id;
        String title;
        String desc;
        String picUrl;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SimpleTop simpleTop = (SimpleTop) o;

            return id != null ? id.equals(simpleTop.id) : simpleTop.id == null;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }


}