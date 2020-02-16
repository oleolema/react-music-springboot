/**
 * FileName:   SpringBootConfig
 * Author:     O了吗
 * Date:       2019/12/3 20:02
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.config;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/3
 * @since 1.0.0
 */
@Configuration
public class SpringBootConfig {


    @Bean
//    @Scope("session")
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public ScriptEngine scriptEngine() {
        ScriptEngineManager scriptEngineManager =
                new ScriptEngineManager();
        return scriptEngineManager.getEngineByName("nashorn");
    }


}