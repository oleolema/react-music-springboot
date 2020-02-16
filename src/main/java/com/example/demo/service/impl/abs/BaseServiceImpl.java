/**
 * FileName:   BaseServiceImpl
 * Author:     O了吗
 * Date:       2019/12/3 11:45
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl.abs;

import com.example.demo.service.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/3
 * @since 1.0.0
 */

public abstract class BaseServiceImpl<T> implements BaseService<T> {


    @Autowired
    private CloseableHttpClient httpclient;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 设置请求头
     *
     * @param httpRequestBase
     */
    private void setHttpHeader(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Mobile Safari/537.36");
        httpRequestBase.setHeader("Content-Type", "application/x-www-form-urlencoded");
    }

    protected void internalHttpPost(HttpPost httpPost, Map<String, ?> params) {
    }

    protected String getStringEntity(HttpPost httpPost, Map<String, ?> params) throws JsonProcessingException {
        return null;
    }

    @Override
    public HashMap<String, ?> getOriginData(String url, Map<String, ?> params) throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost(url);
        setHttpHeader(httpPost);
        if (params != null) {
            httpPost.setEntity(new StringEntity(getStringEntity(httpPost, params)));
        }
        internalHttpPost(httpPost, params);
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        return objectMapper.readValue(httpEntity.getContent(),
                new TypeReference<HashMap<String, Object>>() {
                });
    }


}