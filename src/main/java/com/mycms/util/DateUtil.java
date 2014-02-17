package com.mycms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mycms.Constants;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 将字符串转换为 Dates 和 Timestamps
 */
public final class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";

    /**
     * Checkstyle rule:工具类不能有public的构造函数.
     */
    private DateUtil() {
    }

    /**
     * 返回缺省的日期格式 (MM/dd/yyyy)
     *
     * @return 缺省的日期格式
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale)
                    .getString("date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = "MM/dd/yyyy";
        }

        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * 将Oracle格式的日期从dd-MMM-yyyy 转换为 mm/dd/yyyy.
     *
     * @param aDate 数据库中的日期对象
     * @return 格式后的字符串.
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 根据指定的格式将字符串显示为指定格式的日期对象.
     *
     * @param aMask   日期格式
     * @param strDate 代表日期的字符串
     * @return 转换后的Date对象
     * @throws java.text.ParseException when String doesn't match the expected format
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * 将指定日期转换为以下格式:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime 当前日期
     * @return 转换后的日期
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * 将当前日期转换为以下格式:: MM/dd/yyyy
     *
     * @return 当前日期
     * @throws java.text.ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * 将日期按指定格式转换为日期+时间.
     *
     * @param aMask 日期格式
     * @param aDate 日期对象
     * @return 格式化后的日期字符串.
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 根据系统默认的日期格式将日期对象转换为字符串.
     *
     * @param aDate 要转换的日期
     * @return 代表日期的字符串
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * 将字符串转换为日期
     *
     * @param strDate 要转换的字符串(格式为 MM/dd/yyyy)
     * @return 转换后的日期对象
     * @throws java.text.ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }
}
