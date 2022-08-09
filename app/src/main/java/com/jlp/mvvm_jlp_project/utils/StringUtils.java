package com.jlp.mvvm_jlp_project.utils;

import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ganesha on 12/8/2016.
 */

public class StringUtils {

    private static final String TAG = StringUtils.class.getSimpleName();

    /**
     * Validate the password with at least one alphabet and one number
     * @param password
     * @return true or false
     */
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    /**
     * Get today's date  in string format dd-MMM-yyyy
     * @return today's formatted date
     */
    public static String getTodayDateInStringFormat(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat(AppConstants.APP_DATE_FORMAT, Locale.getDefault());
        return df.format(date);
    }

    /**
     * Get today's date  in date object
     * @return today's formatted date
     */
    public static Date getTodayDateInDateObject(){
        return Calendar.getInstance().getTime();
    }

    /**
     * Format the date in dd-MMM-yyyy
     * @return formatted date
     */
    public static String getFormattedDate(int dayOfMonth, int monthOfYear, int year){
        try {
            Time time = new Time();
            time.set(dayOfMonth, monthOfYear, year);
            long convertedLongDate = time.toMillis(true);
            return DateFormat.format(AppConstants.APP_DATE_FORMAT, convertedLongDate).toString();
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
            return "";
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        boolean isDateSame = false;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(AppConstants.APP_DATE_FORMAT);
            isDateSame =  fmt.format(date1).equals(fmt.format(date2));
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
        }
        return isDateSame;
    }

    public static Date getStringToDateObject(String date) {
        try {
            return new SimpleDateFormat(AppConstants.APP_DATE_FORMAT).parse(date);
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
            return null;
        }
    }

    public static Date getStringToDateObject(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
            return null;
        }
    }

    public static String getCurrentTimeIn24HoursFormat() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.APP_TIME_FORMAT);
            return dateFormat.format( new Date());
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
            return "";
        }
    }

    public static int getDifferenceOfTimes(String selectedTime, String currentTime) {
        int timeDifferenceInHorse = 0;
        try {
            Date selectedDate = getStringToDateObject(selectedTime, AppConstants.APP_TIME_FORMAT);
            Date currentDate = getStringToDateObject(currentTime, AppConstants.APP_TIME_FORMAT);
            long diff = selectedDate.getTime() - currentDate.getTime();
            timeDifferenceInHorse = (int) TimeUnit.MILLISECONDS.toMinutes(diff);
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
        }
        return timeDifferenceInHorse;
    }

    public static String getFormattedDate(String date, String format) {
        String formattedDate;
        try {
            SimpleDateFormat sm = new SimpleDateFormat(format);
            formattedDate = sm.format(date);
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
            return date;
        }
        return formattedDate;
    }

}
