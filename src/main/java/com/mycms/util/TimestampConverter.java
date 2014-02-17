package com.mycms.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将 java.util.Date转为String
 * 将String转为 java.util.Date
 * 它用于BeanUtils复制资源文件属性时.
 */
public class TimestampConverter extends DateConverter {
    /**
     * 国际化的时间戳格式,基于ApplicationResources.properties
     */
    public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";

    /**
     * 将String转为Date
     *
     * @param type  java.util.Date
     * @param value String值
     * @return 转换后的日期对象.
     */
    protected Object convertToDate(Class type, Object value) {
        DateFormat df = new SimpleDateFormat(TS_FORMAT);
        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }

                return df.parse((String) value);
            } catch (Exception pe) {
                throw new ConversionException("Error converting String to Timestamp");
            }
        }

        throw new ConversionException("Could not convert "
                + value.getClass().getName() + " to " + type.getName());
    }

    /**
     * java.util.Date 转 String
     *
     * @param type  java.lang.String
     * @param value 日期对象
     * @return 转换后的字符串
     */
    protected Object convertToString(Class type, Object value) {
        DateFormat df = new SimpleDateFormat(TS_FORMAT);
        if (value instanceof Date) {
            try {
                return df.format(value);
            } catch (Exception e) {
                throw new ConversionException("Error converting Timestamp to String");
            }
        }

        return value.toString();
    }
}