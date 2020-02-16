/**
 * FileName:   HtmlBaseService
 * Author:     O了吗
 * Date:       2019/12/11 13:46
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service;

import com.example.demo.service.exception.ResponseMapperException;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */
public interface HtmlBaseService<T> {

    /**
     * 请求地址的url
     *
     * @return
     */
    String getUrl();

    T mapper(Document map);


    /**
     * 通过url 和 参数请求数据
     *
     * @param url
     * @return
     * @throws URISyntaxException      url 解析失败
     * @throws IOException             解析响应失败
     * @throws ResponseMapperException 响应映射到 T 映射失败
     */
    default T getObjectData(String url) throws IOException,ResponseMapperException {
        Document document = getOriginData(url);
        try {
            return mapper(document);
        } catch (Exception e) {
            throw new ResponseMapperException(e);
        }
    }


    /**
     * 通过 getUrl 和 getParam 请求数据
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ResponseMapperException
     */
    default T getObjectData() throws IOException, ResponseMapperException {
        return getObjectData(getUrl());
    }


    Document getOriginData(String url) throws IOException;

}