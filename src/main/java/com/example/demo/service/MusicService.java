package com.example.demo.service;

import com.example.demo.bean.Music;
import com.example.demo.service.exception.ResponseMapperException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author oleolema
 * @create 2019-12-08 17:11
 */
public interface MusicService extends NetEaseMusicService<Music>  {

    /**
     * 通过id获取歌曲
     * @param id 歌曲id
     * @return
     * @throws ResponseMapperException
     * @throws IOException
     * @throws URISyntaxException
     */
    Music getMusic(String id) throws ResponseMapperException, IOException, URISyntaxException;

}
