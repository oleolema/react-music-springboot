/**
 * FileName:   LyricServiceImpl
 * Author:     O了吗
 * Date:       2019/12/8 17:23
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl;

import com.example.demo.bean.Lyric;
import com.example.demo.service.LyricService;
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
public class LyricServiceImpl extends NetEaseMusicServiceImpl<Lyric> implements LyricService {

    @Override
    public String getUrl() {
        return "https://music.163.com/weapi/song/lyric";
    }

    @Override
    public void setNetEaseParam(Map<String, Object> paramMap) {
        paramMap.put("lv", "-1");
        paramMap.put("tv", "-1");
    }

    @Override
    public Lyric mapper(HashMap<String, ?> map) {
        return null;
    }

    @Override
    public Lyric getData(String id) throws ResponseMapperException, IOException, URISyntaxException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        return getNetEaseObject(map);
    }


}