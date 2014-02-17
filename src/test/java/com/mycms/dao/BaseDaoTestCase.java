package com.mycms.dao;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;


/**
 * 执行DAO测试的测试基类.
 */
@ContextConfiguration(
        locations = {"classpath:/applicationContext-resources.xml",
                "classpath:/applicationContext-dao.xml",
                "classpath*:/applicationContext.xml",
                "classpath:**/applicationContext*.xml"})
public abstract class BaseDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 为所有子类提供一个日志类.
     */
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * ResourceBundle 从 src/test/resources/${package.name}/ClassName.properties 加载(如果有)
     */
    protected ResourceBundle rb;

    /**
     * 缺省的构造函数，如果以下目录中包含对象的测试用类的资源文件，则加载之.
     * src/test/resources.
     */
    public BaseDaoTestCase() {
        // 类的资源文件并不是必须的，这里只是简单的进行检查是否存在对应的资源文件.
        String className = this.getClass().getName();

        try {
            rb = ResourceBundle.getBundle(className);
        } catch (MissingResourceException mre) {
            log.trace("No resource bundle found for: " + className);
        }
    }

    /**
     * 从资源文件中加载JavaBean格式的资源信息的助手方法，实质上是进行对象属性的拷贝.
     *
     * @param obj 要加载资源文件的对象
     * @return 根据资源文件信息装载后的对象.
     * @throws Exception 如果复制对象属性失败抛出异常.
     */
    protected Object populate(final Object obj) throws Exception {
        // loop through all the beans methods and set its properties from its .properties file
        Map<String, String> map = new HashMap<String, String>();

        for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements(); ) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        BeanUtils.copyProperties(obj, map);

        return obj;
    }

    /**
     * 在执行完Hibernate的Save之后，通过flush强制更新结果.
     *
     * @throws org.springframework.beans.BeansException when can't find 'sessionFactory' bean
     */
    protected void flush() throws BeansException {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.flush();
    }

    /**
     * 在执行了reindex() 或 reindexAll()后强制刷新.
     */
    public void flushSearchIndexes() {
        Session currentSession = sessionFactory.getCurrentSession();
        final FullTextSession fullTextSession = Search.getFullTextSession(currentSession);
        fullTextSession.flushToIndexes();
    }
}
