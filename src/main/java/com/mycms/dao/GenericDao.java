package com.mycms.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 通用的DAO访问对象，包含了简单的CRUD操作.
 *
 * @param <T>  Dao要操作的Domain对象
 * @param <PK> Domain对象的主键
 */
public interface GenericDao<T, PK extends Serializable> {

    /**
     * 获得所有Domain对象的方法.
     *
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * 获得所有不重复对象的方法.
     * <p>注意:Domain对象必须实现 hashcode/equals 方法</p>
     *
     * @return List of populated objects
     */
    List<T> getAllDistinct();

    /**
     * 根据关键字查找对象的方法 "*" 表示所有记录.
     *
     * @param searchTerm 查询关键字
     * @return 匹配条件的记录
     * @throws SearchException
     */
    List<T> search(String searchTerm) throws SearchException;

    /**
     * 通过主键获取对象的方法.如果找不到会抛出一个
     * ObjectRetrievalFailureException 运行时异常.
     *
     * @param id 对象的主键
     * @return 获取的对象
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * 根据对象主键判断对象是否存在.
     *
     * @param id domain对象的主键
     * @return - 存在返回true,否则返回false
     */
    boolean exists(PK id);

    /**
     * 保存对象的ORM的方法，包括insert和update.
     *
     * @param object 要保存的对象
     * @return 保存后的对象
     */
    T save(T object);


    /**
     * 删除对象的方法.
     *
     * @param object 要删除的对象
     */
    void remove(T object);

    /**
     * 根据对象主键删除对象的方法.
     *
     * @param id 要删除的对象的主键
     */
    void remove(PK id);

    /**
     * 根据查询语句查找对象的方法，这里用dao了Hibernate的HQL.
     *
     * @param queryName   HQL查询语句
     * @param queryParams 查询语句中的参数对象
     * @return 查询到的对象集合
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * 为对象T生成全文索引的方法.
     */
    void reindex();

    /**
     * 为所有已经索引的对象重新创建全文索引的方法.
     *
     * @param async true 表示异步创建索引
     */
    void reindexAll(boolean async);
}
