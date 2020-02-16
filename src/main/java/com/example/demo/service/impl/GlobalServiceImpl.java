/**
 * FileName:   GlobalServiceImpl
 * Author:     O了吗
 * Date:       2019/12/10 9:55
 * Description:]\
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl;

import com.example.demo.service.GlobalService;
import com.example.demo.service.exception.ResponseMapperException;
import com.example.demo.service.impl.abs.NetEaseEncodeMusicServiceImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * 请求任意url encode 的类
 * @author O了吗
 * @create 2019/12/10
 * @since 1.0.0
 */
@Service
public class GlobalServiceImpl extends NetEaseEncodeMusicServiceImpl<Object> implements GlobalService {

    /**
     * 禁用url
     *
     * @return
     */
    @Override
    public String getUrl() {
        throw new RuntimeException("You can only use this getAllObject(url, encode)");
    }

    @Override
    public Object mapper(HashMap<String, ?> map) {
        return map;
    }


    @Override
    public Object getAllObject(String url, String encode) throws IOException, URISyntaxException, ResponseMapperException {
        return getNetEaseEncodeObject(url, encode);
    }
}