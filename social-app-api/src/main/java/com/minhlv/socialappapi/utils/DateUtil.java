package com.minhlv.socialappapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.ObjectUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    /**
     * Parsing java dateTime to Timestamp ex: 2021-12-02 -> 6621454514446
     *
     */
    public static final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final Long getTimeStampNanos(String date) {
        try {

            if (ObjectUtils.isEmpty(date)) {
                log.info("");
                return null;
            }
            date = date.replace("\"", "");
            if (date.length() <= 10) {
                if (isDateMatch(date)) {
                    return getNanos(date, Utils.DATE_TIME_FORMAT.SQL_DATE);
                } else {
                    return getNanos(date, Utils.DATE_TIME_FORMAT.SQL_SIMPLE_DATE);
                }
            } else {
                date = date.substring(0, 19);
                if (isDateTimeMatch(date)) {
                    return getNanos(date, Utils.DATE_TIME_FORMAT.SQL_DATETIME);
                } else {
                    return getNanos(date, Utils.DATE_TIME_FORMAT.SQL_SIMPLE_DATETIME);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isDateTimeMatch(String date) {
        return date.matches(Constants.REGEX_YYYYMMDD_DATETIME_MATCH);
    }

    public static boolean isDateMatch(String date) {
        return date.matches(Constants.REGEX_YYYYMMDD_DATE_MATCH);
    }

    public static Long getNanos(String date, SimpleDateFormat pattern) {

        try {
            if (date == null || date.equals("null") || "".equals(date)) {
                return null;
            }
            return pattern.parse(date).getTime();
        } catch (ParseException e) {
            return null;
        }
    }

    public static String format(Date date, SimpleDateFormat pattern) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        return pattern.format(date);
    }

    public static Date format(String date, SimpleDateFormat pattern) {
        try {
            if (ObjectUtils.isEmpty(date)) {
                return null;
            }
            return pattern.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long getTime(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    /**
     *
     * @return DateTimeFormatter of pattern yyyy-MM-dd
     */
    public static DateTimeFormatter getSQLDateFormat() {
        return DateTimeFormatter.ofPattern(Utils.DATE_TIME_FORMAT.SQL_DATE.toPattern());
    }

    /**
     *
     * @return DateTimeFormatter of pattern dd/MM/yyyy
     */
    public static DateTimeFormatter getSQLSimpleDateFormat() {
        return DateTimeFormatter.ofPattern(Utils.DATE_TIME_FORMAT.SQL_SIMPLE_DATE.toPattern());
    }

    public static SimpleDateFormat getDefaultDateFormat() {
        return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
    }

}
