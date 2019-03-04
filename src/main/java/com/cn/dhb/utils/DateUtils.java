package com.cn.dhb.utils;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/6/15
 * @Description: 使用第三方 jar 包 joda-time
 * @Version: 1.0.0
 */
public class DateUtils {

    private static DateTimeFormatter dtfDay = DateTimeFormat.forPattern("yyyyMMdd");
    private static DateTimeFormatter dtfSeconds = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    private static DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 给定日期返回指定单位的格式
     *
     * @param date
     * @param unit d 天 s 秒
     * @return yyyyMMdd or yyyyMMddHHmmss
     */
    public static String getDayFormat(Date date, String unit) {
        String str = null;
        switch (unit) {
            case "d":
                str = dtfDay.print(date.getTime());
                break;
            case "s":
                str = dtfSeconds.print(date.getTime());
                break;
            default:
                str = dtfDay.print(date.getTime());
                break;
        }
        return str;
    }

    /**
     * 计算相差 diff 天后的日期
     *
     * @param date yyyyMMdd
     * @param diff
     * @return yyyyMMdd
     * @throws Exception
     */
    public static String getDay(String date, int diff) throws Exception {
        String str = null;
        if (StringUtils.isEmpty(date)) {
            str = dtfDay.parseDateTime(new DateTime().toString("yyyyMMdd")).minusDays(-diff).toString("yyyyMMdd");
        } else {
            str = dtfDay.parseDateTime(date).minusDays(-diff).toString("yyyyMMdd");
        }
        return str;
    }

    /**
     * 根据时间戳返回规定时间格式
     *
     * @param l
     * @return
     * @throws Exception
     */
    public static String getFormatTime(long l) throws Exception {
        return dtf.print(l);
    }


}
