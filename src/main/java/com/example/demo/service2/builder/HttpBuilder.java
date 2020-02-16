/**
 * FileName:   HttpBuilder
 * Author:     O了吗
 * Date:       2019/12/12 0:34
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service2.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/12
 * @since 1.0.0
 */
@Service
public class HttpBuilder {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CloseableHttpClient httpClient;

    private Map<String, String> headers = new HashMap<>();

    @PostConstruct
    public void init() {
        RequestBuilder.setObjectMapper(objectMapper);
        ResponseBuilder.setHttpClient(httpClient);
        MapperBuilder.setObjectMapper(objectMapper);
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
    }

    public HttpBuilder() {
    }

    public RequestBuilder<HttpGet> get(String url) {
        return RequestBuilder.get(url).addHeaders(headers);
    }

    public RequestBuilder<HttpPost> post(String url) {
        return RequestBuilder.post(url).addHeaders(headers);
    }

}