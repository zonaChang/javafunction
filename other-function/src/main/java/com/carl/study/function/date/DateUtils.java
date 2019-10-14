package com.carl.study.function.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author changez
 * @desc
 * @datetime 2019/9/27 14:46
 */
public class DateUtils {

    public static SimpleDateFormat simpleDateFormat;
    @Test
    public void yyyyMd(){
        simpleDateFormat = new SimpleDateFormat("M.d");
        System.out.println(simpleDateFormat.format(new Date()));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DATE, 5);

        simpleDateFormat = new SimpleDateFormat("M.d");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}
