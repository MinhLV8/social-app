package com.minhlv.socialappapi.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public interface DATE_TIME_FORMAT {
        SimpleDateFormat SQL_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat SQL_DATE = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat SQL_SIMPLE_DATETIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat SQL_SIMPLE_DATE = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat SQL_SIMPLE_YYYYMMDD_DATE = new SimpleDateFormat("yyyyMMdd");
    }

    private static final Random RANDOM = new Random();

    public static String randomString(int length) {
        byte[] array = new byte[length]; // length is bounded by 7
        RANDOM.nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param {"elastic":
     *            {"username": "abc"}}
     * @param path
     *            ex: elastic.username
     * @return value
     */
    public static String get(JSONObject json, String path) {
        try {
            String[] keys = path.split("[.]");
            for (int i = 0; i < keys.length; i++) {
                // not get last key
                if (i == keys.length - 1) {
                    break;
                }
                String key = keys[i];
                json = json.getJSONObject(key);
            }
            return json.getString(keys[keys.length - 1]);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(path);
            return null;
        }
    }

    public static InputStream getInputStreamFromClassPath(String path) {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(path);
            return resource.getInputStream();
        } catch (Exception e) {
            return null;
        }
    }

    public static FileInputStream getFileInputStreamFromClassPath(String path) {
        try {
            return new FileInputStream(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method will take Date in "MMMM, dd yyyy HH:mm:s" format and return time
     * difference like added: 3 min ago
     *
     * @param date
     *            : date in "MMMM, dd yyyy HH:mm:s" format
     * @return : time difference
     */
    private String getDurationTimeStamp(String date) {
        String timeDifference = "";

        // date formatter as per the coder need
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:s");
        TimeZone timeZone = TimeZone.getTimeZone("VN");
        sdf.setTimeZone(timeZone);
        Date startDate = null;
        try {
            startDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // end date will be the current system time to calculate the lapse time
        // difference
        Date endDate = new Date();

        // get the time difference in milliseconds
        long duration = endDate.getTime() - startDate.getTime();

        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        if (diffInDays >= 365) {
            int year = (int) (diffInDays / 365);
            timeDifference = year + " năm trước.";
        } else if (diffInDays >= 30) {
            int month = (int) (diffInDays / 30);
            timeDifference = month + " tháng trước.";
        }
        // if days are not enough to create year then get the days
        else if (diffInDays >= 1) {
            timeDifference = diffInDays + " ngày trước.";
        }
        // if days value<1 then get the hours
        else if (diffInHours >= 1) {
            timeDifference = diffInHours + " giờ trước.";
        }
        // if hours value<1 then get the minutes
        else if (diffInMinutes >= 1) {
            timeDifference = diffInMinutes + " phút trước.";
        }
        // if minutes value<1 then get the seconds
        else if (diffInSeconds >= 1) {
            timeDifference = diffInSeconds + " giây trước.";
        } else if (timeDifference.isEmpty()) {
            timeDifference = "Vừa xong.";
        }

        return timeDifference;
    }
}
