/**
 * FileName:   PlayList
 * Author:     O了吗
 * Date:       2019/12/11 13:56
 * Description:
 * History:
 * author:     oleolema
 */
package com.example.demo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/12/11
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
public class PlayList {

    ArrayList<Play> playList = new ArrayList<>();

    @Data
    @Accessors(chain = true)
    public static class Play {

    }


}