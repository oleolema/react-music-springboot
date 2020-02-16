/**
 * FileName:   MapperBuilder
 * Author:     O了吗
 * Date:       2019/12/11 21:48
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service2.builder;

import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.example.demo.service2.builder.functioninterface.MapperFunction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;


/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */

@Accessors(chain = true)
@Slf4j
public class MapperBuilder<S, T> {

    private CloseableHttpResponse response;

    private MapperFunction<S, T> mapper;

    private String type = "object";


    @Setter
    private static ObjectMapper objectMapper;

    public MapperBuilder(CloseableHttpResponse response, MapperFunction<S, T> mapper) {
        this.response = response;
        this.mapper = mapper;
        if (this.mapper == null) {
            this.mapper = s -> (T) s;
        }
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }

    public MapperBuilder(CloseableHttpResponse response) {
        this(response, null);
    }

    public MapperBuilder<S, T> setStringMapper(MapperFunction<String, T> mapper) {
        this.mapper = (MapperFunction<S, T>) mapper;
        type = "string";
        return this;
    }

    public MapperBuilder<S, T> setInputStreamMapper(MapperFunction<InputStream, T> mapper) {
        this.mapper = (MapperFunction<S, T>) mapper;
        type = "inputStream";
        return this;
    }

    public MapperBuilder<S, T> setDocumentMapper(MapperFunction<Document, T> mapper) {
        this.mapper = (MapperFunction<S, T>) mapper;
        type = "document";
        return this;
    }

    public MapperBuilder<S, T> setJsonMapper() {
        setJsonMapper((MapperFunction<LinkedHashMap<String, Object>, T>) this.mapper);
        type = "json";
        return this;
    }

    public MapperBuilder<S, T> setJsonMapper(MapperFunction<LinkedHashMap<String, Object>, T> mapper) {
        this.mapper = (MapperFunction<S, T>) mapper;
        type = "json";
        return this;
    }


    public T build() throws IOException, ResponseMapperException {
        S s = null;
        try {
            InputStream in = response.getEntity().getContent();
            switch (type) {
                case "inputStream":
                    s = (S) in;
                    break;
                case "string":
                    s = (S) IOUtils.toString(in, StandardCharsets.UTF_8);
                    break;
                case "document":
                    s = (S) Jsoup.parse(IOUtils.toString(in, StandardCharsets.UTF_8));
                    break;
                case "json":
                    s = (S) objectMapper.readValue(IOUtils.toString(in, StandardCharsets.UTF_8), new TypeReference<LinkedHashMap<String, Object>>() {
                    });
                    break;
                //默认使用字符串
                default:
                    s = (S) IOUtils.toString(in, StandardCharsets.UTF_8);
            }
            return mapper.apply(s);
        } catch (Exception e) {
            String s1 = "" + s;
            if (s1.length() > 100) {
                s1 = (s1.substring(0, 100));
            }
            log.error("错误信息：" + s1);
            throw new ResponseMapperException(e);
        } finally {
            response.close();
        }
    }


}