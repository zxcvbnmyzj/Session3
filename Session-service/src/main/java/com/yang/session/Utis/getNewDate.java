package com.yang.session.Utis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getNewDate {

    public static String getDate(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
