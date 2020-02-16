/**
 * FileName:   MusicServiceImpl
 * Author:     O了吗
 * Date:       2019/12/8 17:11
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl;

import com.example.demo.bean.Music;
import com.example.demo.service.MusicService;
import com.example.demo.service.exception.ResponseMapperException;
import com.example.demo.service.impl.abs.NetEaseMusicServiceImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */
@Service
public class MusicServiceImpl extends NetEaseMusicServiceImpl<Music> implements MusicService {


    @Override
    public String getUrl() {
        return "https://music.163.com/weapi/song/enhance/player/url/v1?csrf_token=";
    }

    @Override
    protected void setNetEaseParam(Map<String, Object> paramMap) {
        //编码格式
        paramMap.put("encodeType", "aac");
        //音质
        paramMap.put("level", "standard");
    }

    @Override
    public Music mapper(HashMap<String, ?> map) {
        return null;
    }

    @Override
    public Music getMusic(String id) throws ResponseMapperException, IOException, URISyntaxException {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", "[" + id + "]");
        return getNetEaseObject(map);
    }

}