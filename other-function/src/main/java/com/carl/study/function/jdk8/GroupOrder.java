package com.carl.study.function.jdk8;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author changez
 * @desc 分组排序
 * @datetime 2019/9/23 19:37
 */
public class GroupOrder {

    public static void main(String[] args) {
        List<Province> provinceList = new ArrayList<Province>(){{
            add(new Province().setName("安新县").setFirstLetter("A").setPinyin("anxinxian"));
            add(new Province().setName("北京市").setFirstLetter("B").setPinyin("beijingshi"));
            add(new Province().setName("朝阳区").setFirstLetter("C").setPinyin("chaoyangqu"));
            add(new Province().setName("东城区").setFirstLetter("A").setPinyin("aongchengqu"));
            add(new Province().setName("鄂尔多斯市").setFirstLetter("E").setPinyin("eerduosishi"));
            add(new Province().setName("丰台区").setFirstLetter("A").setPinyin("aengtaiqu"));
            add(new Province().setName("藁城区").setFirstLetter("G").setPinyin("gaochengqu"));
        }};

        Map<String, List<Province>> resultMap  = provinceList.stream()
                .collect(Collectors.groupingBy(Province::getFirstLetter, TreeMap::new, Collectors.toList()));
        System.out.println(resultMap);
        resultMap.forEach((k,v)->{
            v = v.stream().sorted(Comparator.comparing(Province::getPinyin)).collect(Collectors.toList());
            resultMap.put(k, v);
        });
        System.out.println(resultMap);
    }

}
