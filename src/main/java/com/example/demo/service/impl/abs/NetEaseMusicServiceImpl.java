/**
 * FileName:   NetEaseMusicServiceImpl
 * Author:     O了吗
 * Date:       2019/12/8 19:19
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.impl.abs;

import com.example.demo.service.BaseService;
import com.example.demo.service.exception.ResponseMapperException;
import com.example.demo.util.NetEaseEncode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 自动加密的请求类
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */
@Service
public abstract class NetEaseMusicServiceImpl<T> extends BaseServiceImpl<T> implements BaseService<T> {


    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 设置请求数据
     *
     * @param paramMap
     */
    abstract protected void setNetEaseParam(Map<String, Object> paramMap);


    @Override
    protected String getStringEntity(HttpPost httpPost, Map<String, ?> paramMap) throws JsonProcessingException {
        try {
            setNetEaseParam((Map<String, Object>) paramMap);
            return NetEaseEncode.encode(objectMapper.writeValueAsString(paramMap));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected T getNetEaseObject(Map<String, Object> params) throws ResponseMapperException, IOException, URISyntaxException {
        return getObjectData(params);
    }


}