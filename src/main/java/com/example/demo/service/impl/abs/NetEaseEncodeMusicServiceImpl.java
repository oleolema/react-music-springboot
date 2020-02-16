/**
 * FileName:   NetEaseEncodeMusicServiceImpl
 * Author:     O了吗
 * Date:       2019/12/10 16:27
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl.abs;

import com.example.demo.service.exception.ResponseMapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.methods.HttpPost;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 未加密的请求类
 *
 * @author O了吗
 * @create 2019/12/10
 * @since 1.0.0
 */
public abstract class NetEaseEncodeMusicServiceImpl<T> extends BaseServiceImpl<T> {

    @Override
    protected String getStringEntity(HttpPost httpPost, Map<String, ?> params) throws JsonProcessingException {
        return (String) params.get("__$data");
    }

    public T getNetEaseEncodeObject(String url, String encode) throws ResponseMapperException, IOException, URISyntaxException {
        Assert.notNull(encode, "encode not allow null");
        Map<String, Object> params = new HashMap<>();
        params.put("__$data", encode);
        return getObjectData(url, params);
    }


}