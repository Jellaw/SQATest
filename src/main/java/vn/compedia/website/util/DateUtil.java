package vn.compedia.website.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String FROM_DATE_FORMAT = "dd/MM/yyyy 00:00:00";
    public static final String TO_DATE_FORMAT = "dd/MM/yyyy 23:59:59";
    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_FORMAT_UPLOAD = "ddMMyyyyHHmmssSSS";
    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String YYYYMMDD_FOLDER = "yyyyMMdd";
    public static final SimpleDateFormat cmdateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static Date formatToDate(Date toDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TO_DATE_FORMAT);
        String strDate = dateFormat.format(toDate);
        try {
            return cmdateFormat.parse(strDate);
        } catch (ParseException e) {

        }
        return null;
    }

    public static Date formatFromDate(Date toDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FROM_DATE_FORMAT);
        String strDate = dateFormat.format(toDate);
        try {
            return cmdateFormat.parse(strDate);
        } catch (ParseException e) {

        }
        return null;
    }

    public static boolean checkDateFormat(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Date parseDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentDateStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_UPLOAD);
        return dateFormat.format(new Date());
    }

    public static String getTodayFolder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD_FOLDER);
        return dateFormat.format(new Date());
    }
}
