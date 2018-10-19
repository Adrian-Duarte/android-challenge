package com.example.adrianduarte.androidchallenge.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static String DEFAULT_PATTERN = "dd/MM/yyyy";

    public static String formatDate(int datetime) {
        return formatDate(DEFAULT_PATTERN, datetime);
    }

    public static String formatDate(String pattern, int datetime) {
        return new SimpleDateFormat(pattern,  Locale.US).format(new Date((long)datetime*1000));
    }

    public static String formatDate(String pattern) {
        return formatDate(pattern, null);
    }

    public static String formatDate(String pattern, Date date) {
        return new SimpleDateFormat(pattern, Locale.US).format(date!=null ? date : new Date());
    }

}