package com.example.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParseTest {
    public static void main(String[] args) {
        String isoDate = "2026-03-03T16:08:08.329Z";
        
        try {
            // 尝试解析ISO格式的日期字符串（带Z后缀）
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
            Date checkInTime = sdf.parse(isoDate);
            System.out.println("解析成功：" + checkInTime);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("解析失败");
        }
    }
}