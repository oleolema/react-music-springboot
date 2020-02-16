package com.example.demo.service;

import com.example.demo.service.exception.ResponseMapperException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author oleolema
 * @create 2019-12-03 10:29
 */
public interface BaseService<T> {

    /**
     * 请求地址的url
     *
     * @return
     */
    String getUrl();

    /**
     * 请求参数
     *
     * @return
     */
    default Map<String, ?> getParam() {
        return null;
    }

    /**
     * 响应数据 与 T 的映射
     *
     * @param map
     * @return
     */
    T mapper(HashMap<String, ?> map);

    /**
     * 获取url的原始数据
     *
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    HashMap<String, ?> getOriginData(String url, Map<String, ?> params) throws URISyntaxException, IOException;

    /**
     * 通过url 和 参数请求数据
     *
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException      url 解析失败
     * @throws IOException             解析响应失败
     * @throws ResponseMapperException 响应映射到 T 映射失败
     */
    default T getObjectData(String url, Map<String, ?> params) throws URISyntaxException, IOException, ResponseMapperException {
        HashMap<String, ?> map = getOriginData(url, params);
        try {
            return mapper(map);
        } catch (Exception e) {
            throw new ResponseMapperException(e);
        }
    }

    /**
     * 通过 getUrl 和 参数 请求数据
     *
     * @param params
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ResponseMapperException
     */
    default T getObjectData(Map<String, ?> params) throws IOException, URISyntaxException, ResponseMapperException {
        return getObjectData(getUrl(), params);
    }

    /**
     * 通过 getUrl 和 getParam 请求数据
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ResponseMapperException
     */
    default T getObjectData() throws IOException, URISyntaxException, ResponseMapperException {
        return getObjectData(getUrl(), getParam());
    }


}
