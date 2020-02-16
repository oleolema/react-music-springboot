/**
 * FileName:   PlayListServiceImpl
 * Author:     O了吗
 * Date:       2019/12/11 13:56
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl;

import com.example.demo.service.PlayListService;
import com.example.demo.service.impl.abs.BaseServiceImpl;
import com.example.demo.service.impl.abs.HtmlBaseServiceImpl;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * o
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */

@Service
public class PlayListServiceImpl extends BaseServiceImpl<Object> implements PlayListService {


    @Override
    public String getUrl() {
        return "https://music.163.com/api/playlist/detail";
    }

    @Override
    public Object mapper(HashMap<String, ?> map) {
        return map;
    }

    @Override
    public Object getData(String id) throws IOException, URISyntaxException {
        return getOriginData(getUrl() + "?id=" + id, null);
    }
}