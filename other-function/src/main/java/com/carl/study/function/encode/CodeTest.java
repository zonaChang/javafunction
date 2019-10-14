package com.carl.study.function.encode;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changez
 * @desc
 * @datetime 2019/10/9 13:59
 */
public class CodeTest {

    private static final String salt = "bzruanjian";
    public static void main(String[] args) {

        Map<String, Object> soureceMap = new HashMap<>();
        soureceMap.put("name", "changerzhuo");
        soureceMap.put("age", 12);
        soureceMap.put("birthday", new Date());
        String josnStr = JSONObject.toJSONString(soureceMap);
        System.out.println(josnStr);
        String encode = EncodeAndDecode.AESencode(josnStr, salt);
        System.out.println(encode);
        String decode = EncodeAndDecode.AESdecode(encode, salt);
        System.out.println(decode);
        System.out.println(JSONObject.parse(decode));
    }
}
