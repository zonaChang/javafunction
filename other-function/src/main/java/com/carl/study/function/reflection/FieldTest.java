package com.carl.study.function.reflection;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * @author changez
 * @desc
 * @datetime 2019/10/10 17:16
 */
@Slf4j
public class FieldTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("  ");
        boolean flag = validateBeanAllFieldIsNull(student);
        System.out.println(flag);
        System.out.println(String.class.getTypeName());
    }

    public static boolean validateBeanAllFieldIsNull(Object obj){

        Class clazz = obj.getClass();
        if (obj == null || clazz == null) {
            throw new RuntimeException("目标对象和对象类型不能为空");
        }
        Object temp;
        do {
            Field[] fieldArr = clazz.getDeclaredFields();
            try {
                for (Field field : fieldArr) {
                    field.setAccessible(true);
                    temp = field.get(obj);
                    if (temp != null) {
                        if (field.getGenericType().getTypeName().equals(String.class.getTypeName())) {
                            if (StringUtils.isNotBlank((String) temp)) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                log.error("对象属性校验null失败, err={}", e.getMessage());
                return false;
            }
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class);
        return true;
    }
}
