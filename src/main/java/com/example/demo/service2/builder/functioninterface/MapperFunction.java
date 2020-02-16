/**
 * FileName:   Function
 * Author:     O了吗
 * Date:       2019/12/14 18:13
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service2.builder.functioninterface;

import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.script.ScriptException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/14
 * @since 1.0.0
 */
@FunctionalInterface
public interface MapperFunction<T, R> {

    R apply(T s) throws ResponseMapperException, JsonProcessingException, ScriptException;

}