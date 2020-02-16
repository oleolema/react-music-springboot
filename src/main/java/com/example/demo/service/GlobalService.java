/**
 * FileName:   GlobalService
 * Author:     O了吗
 * Date:       2019/12/10 9:54
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service;

import com.example.demo.service.exception.ResponseMapperException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/10
 * @since 1.0.0
 */
public interface GlobalService extends BaseService<Object> {


    /**
     * 通过url 和 encode 直接请求数据
     *
     * @param url
     * @param encode
     * @return
     */
    Object getAllObject(String url, String encode) throws IOException, URISyntaxException, ResponseMapperException;

}