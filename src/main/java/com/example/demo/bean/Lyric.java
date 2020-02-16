/**
 * FileName:   Lyric
 * Author:     O了吗
 * Date:       2019/12/8 17:22
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/8
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
public class Lyric {

    private String lyric;

    private String tLyric;

}