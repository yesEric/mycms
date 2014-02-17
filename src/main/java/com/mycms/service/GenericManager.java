package com.mycms.service;

import com.mycms.dao.SearchException;

import java.io.Serializable;
import java.util.List;

/**
 * 实现GenericDao的CRUD的通用Manager.
 *
 * @param <T>  要操作的Domain对象
 * @param <PK> 实体对象的主键
 */
public interface GenericManager<T, PK extends Serializable> {

    /**
     * 获得所有Domain对象的方法.
     *
     * @return List of populated objects
     */
    List<T> getAll();


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
     * 根据关键字查找对象的方法 "*" 表示所有记录.
     *
     * @param searchTerm 查询关键字
     * @param clazz      要查询的实体对象
     * @return 服务查询条件的结果
     */
    List<T> search(String searchTerm, Class clazz);

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
