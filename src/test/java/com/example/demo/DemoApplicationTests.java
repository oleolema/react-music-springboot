package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptException;
import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    GlobalController globalController;

    @Autowired
    LyricController lyricController;

    @Autowired
    MusicController musicController;

    @Autowired
    PlayListController playListController;

    @Autowired
    DiscoverImageController discoverImageController;

    @Autowired
    TopListController topListController;

    @Test
    public void test1() throws IOException, ScriptException, ResponseMapperException {
        System.out.println(musicController.music("1363948882"));
    }


    @Test
    public void test2() throws IOException, ScriptException, ResponseMapperException {
        System.out.println(lyricController.lyric("1363948882"));
    }

    @Test
    public void test3() throws IOException, ResponseMapperException {
        GlobalController.RequestGlobal requestGlobal = new GlobalController.RequestGlobal();
        requestGlobal.setUrl("https://music.163.com/weapi/song/enhance/player/url/v1?csrf_token=");
        requestGlobal.setEncode("params=JI6pXECf7VERwdJMCoJat7qwE69wZk6wrdkmBbH5Njce0qhcew5Zc1IKgpIBug30zGqlranOlTYQtoKQ3LM1knlhw8637fhzsQb9eNO%2Bw%2BjtzqM5CM%2FDUdZF%2FwnzzR96&encSecKey=adb2ee2e6ecd644cce57e58a7b4b6fc61e29acee8c8638f76447e6701f8f34c3603fdfff72fbd5ed8df767e1e87a3120aceafcf81b2147f12335d3927179169e2c69f7ebd471391774fbc54805a9e5f2fe96c69c2639c82ca9e504e9a3ca869b3b8294e00a12aa88ed3a85c304cbd3efaa72eb2b9ff33ac70babed7822c99d81");
        System.out.println(globalController.global(requestGlobal));
    }


    @Test
    public void test4() throws IOException, ResponseMapperException {
        System.out.println(topListController.playlist());

    }

    @Test
    public void test5() throws IOException, ResponseMapperException {
        System.out.println(discoverImageController.image());
    }

}
