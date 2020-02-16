/**
 * FileName:   ArtistDetailControll
 * Author:     O了吗
 * Date:       2019/12/17 0:04
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.controller;

import com.example.demo.service2.builder.HttpBuilder;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/17
 * @since 1.0.0
 */
@RestController
public class ArtistDetailController {

    @Autowired
    HttpBuilder httpBuilder;

    @Autowired
    ObjectMapper objectMapper;


    @PostMapping("/artistDetail")
    public Object artistDetail(@RequestBody RequestBodyArtist artist) throws IOException, ResponseMapperException {
        //song-list-pre-data
        return httpBuilder.get("https://music.163.com/artist?id=" + artist.id)
                .nextToMapper()
                .setDocumentMapper(document -> {
                    String data = document.selectFirst("#song-list-pre-data").text();
                    return objectMapper.readValue(data, Object.class);
                })
                .build();

    }

    @Accessors(chain = true)
    @Data
    public static class RequestBodyArtist {
        String id;
    }


}