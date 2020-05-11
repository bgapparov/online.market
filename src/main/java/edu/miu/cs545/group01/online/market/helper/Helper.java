package edu.miu.cs545.group01.online.market.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static Date getDate(int i, int year, int month, int day){
        return parseDate(String.format("%s-%s-%s %s:%s:%s", year, month, day, 0, 0, 0));
    }
    public static Date getDate(int year, int month, int day, int hour, int minute, int second){
        return parseDate(String.format("%s-%s-%s %s:%s:%s", year, month, day, hour, minute, second));
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
