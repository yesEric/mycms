package com.mycms.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mycms.util.ConvertUtil;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A mock class for testing using Mockito.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseManagerMockTestCase {
    /**
     * 日志对象
     */
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * resourceBundle
     */
    protected ResourceBundle rb;

    /**
     * 缺省的构造函数用于加载实体对象的资源文件.
     */
    public BaseManagerMockTestCase() {
        // Since a ResourceBundle is not required for each class, just
        // do a simple check to see if one exists
        String className = this.getClass().getName();

        try {
            rb = ResourceBundle.getBundle(className);
        } catch (MissingResourceException mre) {
            //log.debug("No resource bundle found for: " + className);
        }
    }

    /**
     * 从资源文件中加载JavaBean格式的资源信息的助手方法，实质上是进行对象属性的拷贝.
     *
     * @param obj 要加载资源文件的对象
     * @return 根据资源文件信息装载后的对象.
     * @throws Exception 如果复制对象属性失败抛出异常.
     */
    protected Object populate(Object obj) throws Exception {
        // loop through all the beans methods and set its properties from
        // its .properties file
        Map map = ConvertUtil.convertBundleToMap(rb);

        BeanUtils.copyProperties(obj, map);

        return obj;
    }
}
