package com.minhlv.socialappapi.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Random;

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
}
