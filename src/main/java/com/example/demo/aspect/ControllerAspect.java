/**
 * FileName:   ControllerAspect
 * Author:     O了吗
 * Date:       2019/12/6 16:37
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.aspect;


import com.example.demo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/6
 * @since 1.0.0
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(public * com.example.demo.controller.*Controller.*(..))")
    public void controller() {
    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void getMapping() {
    }

    @Around("getMapping()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
            if (isEmpty(result)) {
                return ResponseUtil.error("没有请求到任何数据");
            }
//            if(result instanceof LinkedHashMap){
//                result = ((LinkedHashMap) result).get("data");
//
//            }
            log.debug(result.toString());
            return ResponseUtil.success(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ResponseUtil.error("服务器错误", throwable);
        }

    }

    public boolean isEmpty(Object result) {
        if (result instanceof String) {
            String resultStr = (String) result;
            if (resultStr.isEmpty()) {
                return true;
            }
        }
        if (result instanceof Map) {
            Map resultMap = (Map) result;
            if (resultMap.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}