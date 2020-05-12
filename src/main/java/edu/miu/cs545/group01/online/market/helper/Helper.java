package edu.miu.cs545.group01.online.market.helper;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static String getExtension(String fileName) {
        String extension = ".png";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i);
        }
        return extension;
    }
    public static boolean isItImage(String extension){
        List<String> extensions = new ArrayList<>();
        extensions.add(".jpg");
        extensions.add(".png");
        extensions.add(".jpeg");
        extensions.add(".gif");
        return extensions.contains(extension);
    }

    public static String getImagesFolder(ServletContext servletContext) {
        String rootDirectory = servletContext.getRealPath("/");
        File dir = new File(rootDirectory + "images" );
        if(!dir.exists()){
            dir.mkdir();
        }
        return dir.toString();
    }
}
