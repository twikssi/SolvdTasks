package by.demoqa.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    public static DateFormat dateFormatFullWithMill = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static Date parseStringToDate(String dataText, DateFormat format) {
        Date date = null;
        try {
            date = format.parse(dataText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long getNumberOfDaysTokenIsAvailable(String dataText) {
        Date date = CalendarUtil.parseStringToDate(dataText, CalendarUtil.dateFormatFullWithMill);
        long resultDate = date.getTime() - Calendar.getInstance().getTime().getTime();
        long result = resultDate / 86400000;
        return result;
    }

}
