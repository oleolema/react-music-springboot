/**
 * FileName:   LyricService
 * Author:     O了吗
 * Date:       2019/12/8 17:24
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service;

import com.example.demo.bean.Lyric;
import com.example.demo.service.exception.ResponseMapperException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */
public interface LyricService extends BaseService<Lyric> {

    /**
     * 通过歌曲id获取
     * @param id
     * @return
     * @throws ResponseMapperException
     * @throws IOException
     * @throws URISyntaxException
     */
    public Lyric getData(String id) throws ResponseMapperException, IOException, URISyntaxException;

}