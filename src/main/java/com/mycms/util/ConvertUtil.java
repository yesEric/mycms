package com.mycms.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mycms.model.LabelValue;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * 将一个对象转换为另一个对象的类.
 */
public final class ConvertUtil {
    private static final Log log = LogFactory.getLog(ConvertUtil.class);

    /**
     * Checkstyle rule: 工具类应该没有构造函数。
     */
    private ConvertUtil() {
    }

    /**
     * 将 ResourceBundle 转换为Map 对象的方法.
     *
     * @param rb 一个给定的资源对象
     * @return 返回的Map对象
     */
    public static Map<String, String> convertBundleToMap(ResourceBundle rb) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        return map;
    }

    /**
     * 将LabelValue对象的java.util.List 转换为LinkedHashMap.
     *
     * @param list 要转换的List
     * @return 转换后的Map对象以LabelValue对象的label为key
     */
    public static Map<String, String> convertListToMap(List<LabelValue> list) {
        Map<String, String> map = new LinkedHashMap<String, String>();

        for (LabelValue option : list) {
            map.put(option.getLabel(), option.getValue());
        }

        return map;
    }

    /**
     * 将ResourceBundle转换为Properties 对象的方法.
     *
     * @param rb 给定的ResourceBundle
     * @return 转换后的 Properties
     */
    public static Properties convertBundleToProperties(ResourceBundle rb) {
        Properties props = new Properties();

        for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements(); ) {
            String key = keys.nextElement();
            props.put(key, rb.getString(key));
        }

        return props;
    }

    /**
     * 测试类中用于将资源文件中的字段值拷贝到JavaBean中的方法.
     *
     * @param obj 初始的JavaBean对象
     * @param rb  资源文件对象
     * @return 赋值后的JavaBean
     */
    public static Object populateObject(Object obj, ResourceBundle rb) {
        try {
            Map<String, String> map = convertBundleToMap(rb);
            BeanUtils.copyProperties(obj, map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception occurred populating object: " + e.getMessage());
        }

        return obj;
    }
}
