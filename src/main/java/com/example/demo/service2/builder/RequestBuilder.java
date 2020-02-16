/**
 * FileName:   RequestBuilder
 * Author:     O了吗
 * Date:       2019/12/11 20:54
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service2.builder;

import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.example.demo.util.NetEaseEncode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class RequestBuilder<T extends HttpUriRequest> {

    private T httpRequest;

    private Map<String, String> cookies;

    public static RequestBuilder<HttpGet> get(String url) {

        RequestBuilder<HttpGet> builder = new RequestBuilder<>(new HttpGet(url));
        return builder;
    }

    public static RequestBuilder<HttpPost> post(String url) {
        RequestBuilder<HttpPost> builder = new RequestBuilder<>(new HttpPost(url));
        builder.getHttpRequest().setHeader("Content-Type", "application/json");
        return builder;
    }

    @Setter
    private static ObjectMapper objectMapper = new ObjectMapper();

    private RequestBuilder(T httpRequest) {
        this.httpRequest = httpRequest;
        this.cookies = new HashMap<>();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }

    public RequestBuilder() {
    }

    public RequestBuilder<T> addHeaders(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpRequest.setHeader(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public RequestBuilder<T> addCookie(String key, String value) {
        cookies.put(key, value);
        return this;
    }

    public RequestBuilder<T> addCookies(Map<String, String> map) {
        cookies.putAll(map);
        return this;
    }

    private String cookiesToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private HttpPost getPost() {
        if (httpRequest instanceof HttpPost) {
            return ((HttpPost) httpRequest);
        }
        throw new RuntimeException("不是post 请求");
    }

    private HttpGet getGet() {
        if (httpRequest instanceof HttpGet) {
            return ((HttpGet) httpRequest);
        }
        throw new RuntimeException("不是get 请求");
    }

    public RequestBuilder<T> setHttpEntity(HttpEntity httpEntity) {
        getPost().setEntity(httpEntity);
        return this;
    }

    public RequestBuilder<T> setJsonEntity(String s) throws UnsupportedEncodingException {
        getPost().setEntity(new StringEntity(s));
        return this;
    }

    public RequestBuilder<T> setFormDataEntity(String encode) throws UnsupportedEncodingException {
        HttpPost httpPost = getPost();
        httpPost.setEntity(new StringEntity(encode));
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        return this;
    }

    public RequestBuilder<T> setNetEaseEntity(Map<String, Object> map) throws UnsupportedEncodingException, JsonProcessingException, ScriptException {
        HttpPost httpPost = getPost();
        httpPost.setEntity(new StringEntity(NetEaseEncode.encode(objectMapper.writeValueAsString(map))));
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        return this;
    }

    public T build() {
        //设置cookie
        httpRequest.setHeader("Cookie", cookiesToString());
        return httpRequest;
    }

    public ResponseBuilder next() {
        return new ResponseBuilder(build());
    }

    public MapperBuilder<Object, Object> nextToMapper() throws IOException {
        return new MapperBuilder<>(next().build());
    }

    public Object nextToResult() throws IOException, ResponseMapperException {
        return nextToMapper().build();
    }


}