package com.carl.study.function.jdk8;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author changez
 * @desc 城市
 * @datetime 2019/9/23 19:37
 */
@Data
@Accessors(chain = true)
public class Province {

    private  String name;
    private String firstLetter;
    private String pinyin;
}
