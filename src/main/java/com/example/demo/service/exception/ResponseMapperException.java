/**
 * FileName:   ResponseMapperException
 * Author:     O了吗
 * Date:       2019/12/3 15:45
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.service.exception;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/3
 * @since 1.0.0
 */
public class ResponseMapperException extends Throwable{

    public ResponseMapperException(Throwable cause) {
        super(cause);
    }

    public ResponseMapperException() {
    }

    @Override
    public String getMessage() {
        return "响应映射异常  ";
    }


}