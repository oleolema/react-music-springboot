/**
 * FileName:   HtmlBaseServiceImpl
 * Author:     O了吗
 * Date:       2019/12/11 13:38
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl.abs;

import com.example.demo.service.BaseService;
import com.example.demo.service.HtmlBaseService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */
public abstract class HtmlBaseServiceImpl<T> implements HtmlBaseService<T> {

    @Autowired
    private CloseableHttpClient httpclient;

    /**
     * 设置请求头
     *
     * @param httpRequestBase
     */
    private void setHttpHeader(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Mobile Safari/537.36");
        httpRequestBase.setHeader("Content-Type", "application/x-www-form-urlencoded");
    }

    protected void internalHttpPost(HttpGet httpGet) {
    }


    @Override
    public Document getOriginData(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        setHttpHeader(httpGet);
        internalHttpPost(httpGet);
        HttpResponse response = httpclient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        return Jsoup.parse(IOUtils.toString(httpEntity.getContent(), StandardCharsets.UTF_8));
    }
}