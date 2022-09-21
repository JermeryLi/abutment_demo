package com.netintech.abutment.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static com.netintech.abutment.demo.Constant.API_KEY;
import static com.netintech.abutment.demo.Constant.API_SECRET;

/**
 * 描述：
 *
 * @author AI
 * createTime 2022/9/15 12:59
 * lastModify
 * lastModifyTime
 * 智慧底座 人工智能技术团队
 */
public class Util {
    private static final ThreadLocal<DateFormat> LOCAL_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static final String HTTP_METHOD = "POST";
    private static final String ALGORITHM_NAME = "hmacsha256";

    /**
     * 获取header时间参数
     */
    public static String getFormatDate() {
        return LOCAL_DATE_FORMAT.get().format(new Date());
    }

    /**
     * 获取header attestation参数
     *
     * @param formatDate 格式化的时间
     */
    public static String getAttestation(String formatDate) throws Exception {
        StringBuilder builder = new StringBuilder().
                append("date: ").append(formatDate).append("\n").
                append(HTTP_METHOD).append(" ").append("HTTP/1.1");
        System.out.println(builder.toString());
        Mac mac = Mac.getInstance(ALGORITHM_NAME);
        SecretKeySpec secretKeySpec = new SecretKeySpec(API_SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM_NAME);
        mac.init(secretKeySpec);
        byte[] hexDigits = mac.doFinal(builder.toString().getBytes(StandardCharsets.UTF_8));
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        return String.format("api_key=\"%s\",algorithm=\"%s\",signature=\"%s\"", API_KEY, "hmac-sha256", sha);
    }
}
