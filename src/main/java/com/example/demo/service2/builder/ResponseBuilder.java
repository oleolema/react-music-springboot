/**
 * FileName:   ResponseBuilder
 * Author:     O了吗
 * Date:       2019/12/11 21:44
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service2.builder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class ResponseBuilder {

    @Getter
    @Setter
    private static CloseableHttpClient httpClient;

    private HttpUriRequest httpRequest;


    public ResponseBuilder(HttpUriRequest httpRequest) {
        this.httpRequest = httpRequest;
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
    }


    public CloseableHttpResponse build() throws IOException {
        return httpClient.execute(httpRequest);
    }

    public <S, T> MapperBuilder<Object, T> next(Class<T> toClazz) throws IOException {
        return new MapperBuilder<>(build());
    }

    public <S, T> MapperBuilder<Object, Object> next() throws IOException {
        return new MapperBuilder<>( build());
    }

}