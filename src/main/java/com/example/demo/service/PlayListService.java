/**
 * FileName:   PlayListService
 * Author:     O了吗
 * Date:       2019/12/11 13:56
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */
public interface PlayListService extends BaseService<Object> {

    Object getData(String id) throws IOException, URISyntaxException;
}