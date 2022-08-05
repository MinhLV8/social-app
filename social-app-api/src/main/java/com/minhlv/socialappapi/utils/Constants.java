package com.minhlv.socialappapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String YML_PATH_CONFIG = "application.yml";

    public static final String YML_PATH_CONFIG_EXTEND = "file:/SocialMediaApp/social-app/social-app-api/backend/config/application.yml";

    public static final String FILE_MESSAGE_CONSTRAINT_DATETIME = "Vui lòng nhập dữ liệu ngày tháng theo định dạng yyyy-mm-dd (ví dụ: 2021-12-12)";

    public static final Integer DATETIME_LENGTH = 10;

    public static final Integer JDBC_BATCH_SIZE = 20000;

    public static final String DATABASE_ERROR = "Lỗi truy vấn cơ sở dữ liệu";

    public static final String REGEX_YYYYMMDD_DATE_MATCH = "^\\d{4}-\\d{2}-\\d{2}$";

    public static final String REGEX_YYYYMMDD_DATETIME_MATCH = "^\\d{4}-\\d{2}-\\d{2} ([0-2]\\d:[0-6]\\d:[0-6]\\d)?$";
}
