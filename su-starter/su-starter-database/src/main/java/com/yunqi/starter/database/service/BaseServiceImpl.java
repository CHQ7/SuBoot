package com.yunqi.starter.database.service;


import org.nutz.dao.*;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by @author JsckChin on 2022/1/29
 */
public class BaseServiceImpl<T> extends EntityService<T> implements BaseService<T> {

    /**
     * 获取实体的Entity
     *
     * @return 实体的Entity
     */
    @Override
    public Entity<T> getEntity() {
        return super.getEntity();
    }

    /**
     * 获取实体类型
     *
     * @return 实体类型
     */
    @Override
    public Class<T> getEntityClass() {
        return super.getEntityClass();
    }

    /**
     * 统计符合条件的对象表条数
     *
     * @return 数量
     */
    @Override
    public int count() {
        return dao().count(this.getEntityClass());
    }

    /**
     * 统计符合条件的对象表条数
     *
     * @param cnd WHERE 条件
     * @return    数量
     */
    @Override
    public int count(Condition cnd) {
        return dao().count(this.getEntityClass(), cnd);
    }

    /**
     * 统计表记录条数
     *
     * @param tableName 表名
     * @return          数量
     */
    @Override
    public int count(String tableName) {
        return dao().count(tableName);
    }

    /**
     * 统计符合条件的记录条数
     *
     * @param tableName     表名
     * @param cnd           WHERE 条件
     * @return              数量
     */
    @Override
    public int count(String tableName, Condition cnd) {
        return dao().count(tableName, cnd);
    }

    /**
     * 自定义SQL统计
     *
     * @param sql   自定义SQL对象
     * @return      将结果对象作为 int 返回
     */
    @Override
    public int count(Sql sql) {
        sql.setCallback((conn, rs, sql1) -> {
            int rsvalue = 0;
            if (rs != null && rs.next()) {
                rsvalue = rs.getInt(1);
            }
            return rsvalue;
        });
        dao().execute(sql);
        return sql.getInt();
    }

    /**
     * 通过数字型主键查询对象
     *
     * @param id    对象 ID
     * @return      对象
     */
    @Override
    public T fetch(long id) {
        return dao().fetch(this.getEntityClass(), id);
    }

    /**
     * 通过字符型主键查询对象
     *
     * @param id    对象 ID
     * @return      对象
     */
    @Override
    public T fetch(String id) {
        return dao().fetch(this.getEntityClass(), id);
    }

    /**
     * 查出符合条件的第一条记录
     *
     * @param cnd 查询条件
     * @return 实体, 如不存在则为null
     */
    @Override
    public T fetch(Condition cnd) {
        return dao().fetch(this.getEntityClass(), cnd);
    }

    /**
     * 查询关联表
     *
     * @param obj   数据对象,可以是普通对象或集合,但不是类
     * @param regex 为null查询全部,支持通配符 ^(a|b)$
     * @return      更新后的数据对象本身
     */
    @Override
    public <T> T fetchLinks(T obj, String regex) {
        return dao().fetchLinks(obj, regex);
    }

    /**
     * 查询关联表
     *
     * @param obj   数据对象,可以是普通对象或集合,但不是类
     * @param regex 为null查询全部,支持通配符 ^(a|b)$
     * @param cnd   关联字段的过滤(排序,条件语句,分页等)
     * @return      传入的数据对象
     */
    @Override
    public <T> T fetchLinks(T obj, String regex, Condition cnd) {
        return dao().fetchLinks(obj, regex, cnd);
    }

    /**
     * 复合主键专用
     *
     * @param pks 键值
     * @return 对象 T
     */
    @Override
    public T fetchx(Object... pks) {
        return dao().fetchx(this.getEntityClass(), pks);
    }

    /**
     * 复合主键专用
     *
     * @param pks 键值
     * @return 对象 T
     */
    @Override
    public boolean exists(Object... pks) {
        return null != fetchx(pks);
    }

    /**
     * 将一个对象插入到一个数据库
     *
     * @param obj 要被插入的对象
     *            它可以是：
     *            普通 POJO
     *            集合
     *            数组
     *            Map
     *            注意：如果是集合，数组或者 Map，所有的对象必须类型相同，否则可能会出错
     * @return 插入后的对象
     */
    @Override
    public <T> T insert(T obj) {
        return dao().insert(obj);
    }


    /**
     * 将一个对象按FieldFilter过滤后,插入到一个数据源。
     * <p>
     * <code>dao.insert(pet, FieldFilter.create(Pet.class, FieldMatcher.create(false)));</code>
     *
     * @param obj    要被插入的对象
     * @param filter 字段过滤器, 其中FieldMatcher.isIgnoreId生效
     * @return 插入后的对象
     * @see Dao#insert(Object)
     */
    @Override
    public <T> T insert(T obj, FieldFilter filter) {
        return dao().insert(obj, filter);
    }

    /**
     * 自由的向一个数据表插入一条数据
     *
     * @param tableName 表名
     * @param chain     数据名值链
     */
    @Override
    public void insert(String tableName, Chain chain) {
        dao().insert(tableName, chain);
    }

    /**
     * 根据对象的主键(@Id/@Name/@Pk)先查询, 如果存在就更新, 不存在就插入
     *
     * @param obj 对象
     * @return 原对象
     */
    @Override
    public <T> T insertOrUpdate(T obj) {
        return dao().insertOrUpdate(obj);
    }

    /**
     * 根据对象的主键(@Id/@Name/@Pk)先查询, 如果存在就更新, 不存在就插入
     *
     * @param obj               对象
     * @param insertFieldFilter 插入时的字段过滤, 可以是null
     * @param updateFieldFilter 更新时的字段过滤,可以是null
     * @return 原对象
     */
    @Override
    public <T> T insertOrUpdate(T obj, FieldFilter insertFieldFilter, FieldFilter updateFieldFilter) {
        return dao().insertOrUpdate(obj, insertFieldFilter, updateFieldFilter);
    }


    /**
     * 快速插入一个对象,对象的 '@Prev' 以及 '@Next' 在这个函数里不起作用
     *
     * @param obj
     * @return
     */
    @Override
    public <T> T fastInsert(T obj) {
        return this.dao().fastInsert(obj);
    }

    /**
     * 将对象插入数据库同时，也将符合一个正则表达式的所有关联字段关联的对象统统插入相应的数据库
     * <p>
     * 关于关联字段更多信息，请参看 '@One' | '@Many' | '@ManyMany' 更多的描述
     *
     * @param obj   数据对象
     * @param regex 正则表达式，描述了什么样的关联字段将被关注。如果为 null，则表示全部的关联字段都会被插入
     * @return 数据对象本身
     * @see org.nutz.dao.entity.annotation.One
     * @see org.nutz.dao.entity.annotation.Many
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
    @Override
    public <T> T insertWith(T obj, String regex) {
        return dao().insertWith(obj, regex);
    }

    /**
     * 根据一个正则表达式，仅将对象所有的关联字段插入到数据库中，并不包括对象本身
     *
     * @param obj   数据对象
     * @param regex 正则表达式，描述了什么样的关联字段将被关注。如果为 null，则表示全部的关联字段都会被插入
     * @return 数据对象本身
     * @see org.nutz.dao.entity.annotation.One
     * @see org.nutz.dao.entity.annotation.Many
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
    @Override
    public <T> T insertLinks(T obj, String regex) {
        return dao().insertLinks(obj, regex);
    }

    /**
     * 将对象的一个或者多个，多对多的关联信息，插入数据表
     *
     * @param obj   对象
     * @param regex 正则表达式，描述了那种多对多关联字段将被执行该操作
     * @return 对象自身
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
    @Override
    public <T> T insertRelation(T obj, String regex) {
        return dao().insertRelation(obj, regex);
    }


    /**
     * 更新数据
     *
     * @param obj   要被更新的对象
     * @return      返回实际被更新的记录条数，一般的情况下，如果更新成功，返回 1，否则，返回 0
     */
    @Override
    public int update(Object obj) {
        return dao().update(obj);
    }

    /**
     * 更新数据忽略值为null的字段
     *
     * @param obj   要被更新的对象
     * @return      返回实际被更新的记录条数，一般的情况下，如果是单一Pojo,更新成功，返回 1，否则，返回 0
     */
    @Override
    public int updateIgnoreNull(Object obj) {
        return dao().updateIgnoreNull(obj);
    }

    /**
     * 部分更新实体表
     *
     * @param chain     数据名值链
     * @param cnd       WHERE 条件
     * @return          有多少条记录被更新了
     */
    @Override
    public int update(Chain chain, Condition cnd) {
        return dao().update(this.getEntityClass(), chain, cnd);
    }

    /**
     * 部分更新表
     *
     * @param tableName 数据表名
     * @param chain     数据名值链
     * @param cnd       WHERE 条件
     * @return          有多少条记录被更新了
     */
    @Override
    public int update(String tableName, Chain chain, Condition cnd) {
        return dao().update(tableName, chain, cnd);
    }

    /**
     * 将对象更新的同时，也将符合一个正则表达式的所有关联字段关联的对象统统更新
     * <p>
     * 关于关联字段更多信息，请参看 '@One' | '@Many' | '@ManyMany' 更多的描述
     *
     * @param obj   数据对象
     * @param regex 正则表达式，描述了什么样的关联字段将被关注。如果为 null，则表示全部的关联字段都会被更新
     * @return 数据对象本身
     * @see org.nutz.dao.entity.annotation.One
     * @see org.nutz.dao.entity.annotation.Many
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
    @Override
    public <T> T updateWith(T obj, String regex) {
        return dao().updateWith(obj, regex);
    }


    /**
     * 根据一个正则表达式，仅更新对象所有的关联字段，并不包括对象本身
     *
     * @param obj   数据对象
     * @param regex 正则表达式，描述了什么样的关联字段将被关注。如果为 null，则表示全部的关联字段都会被更新
     * @return 数据对象本身
     * @see org.nutz.dao.entity.annotation.One
     * @see org.nutz.dao.entity.annotation.Many
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
    @Override
    public <T> T updateLinks(T obj, String regex) {
        return dao().updateLinks(obj, regex);
    }

    /**
     * 多对多关联是通过一个中间表将两条数据表记录关联起来。
     * <p>
     * 而这个中间表可能还有其他的字段，比如描述关联的权重等
     * <p>
     * 这个操作可以让你一次更新某一个对象中多个多对多关联的数据
     *
     * @param classOfT 对象类型
     * @param regex    正则表达式，描述了那种多对多关联字段将被执行该操作
     * @param chain    针对中间关联表的名值链。
     * @param cnd      针对中间关联表的 WHERE 条件
     * @return 共有多少条数据被更新
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
    @Override
    public int updateRelation(Class<?> classOfT, String regex, Chain chain, Condition cnd) {
        return dao().updateRelation(classOfT, regex, chain, cnd);
    }

    /**
     * 基于版本的更新，版本不一样无法更新到数据
     *
     * @param obj 需要更新的对象, 必须有version属性
     * @return 若更新成功, 大于0, 否则小于0
     */
    @Override
    public int updateWithVersion(Object obj) {
        return dao().updateWithVersion(obj);
    }

    /**
     * 基于版本的更新，版本不一样无法更新到数据
     *
     * @param obj         需要更新的对象, 必须有version属性
     * @param fieldFilter 需要过滤的字段设置
     * @return 若更新成功, 大于0, 否则小于0
     */
    @Override
    public int updateWithVersion(Object obj, FieldFilter fieldFilter) {
        return dao().updateWithVersion(obj, fieldFilter);
    }

    /**
     * 乐观锁, 以特定字段的值作为限制条件,更新对象,并自增该字段.
     * <p>
     * 执行的sql如下:
     * <p>
     * <code>update t_user set age=30, city="广州", version=version+1 where name="wendal" and version=124;</code>
     *
     * @param obj         需要更新的对象, 必须带@Id/@Name/@Pk中的其中一种.
     * @param fieldFilter 需要过滤的属性. 若设置了哪些字段不更新,那务必确保过滤掉fieldName的字段
     * @param fieldName   参考字段的Java属性名.默认是"version",可以是任意数值字段
     * @return 若更新成功, 返回值大于0, 否则小于等于0
     */
    @Override
    public int updateAndIncrIfMatch(Object obj, FieldFilter fieldFilter, String fieldName) {
        return dao().updateAndIncrIfMatch(obj, fieldFilter, fieldName);
    }

    /**
     * 获取某个对象,最大的 ID 值,这个对象必须声明了 '@Id'
     *
     * @return 最大 ID 值
     */
    @Override
    public int getMaxId() {
        return dao().getMaxId(this.getEntityClass());
    }

    /**
     * 通过long主键删除数据
     *
     * @param id    对象 ID
     * @return      影响的行数
     */
    @Override
    public int delete(long id) {
        return dao().delete(this.getEntityClass(), id);
    }

    /**
     * 通过int主键删除数据
     *
     * @param id    对象 ID
     * @return      影响的行数
     */
    @Override
    public int delete(int id) {
        return dao().delete(this.getEntityClass(), id);
    }

    /**
     * 通过string主键删除数据
     *
     * @param id    对象 ID
     * @return      影响的行数
     */
    @Override
    public int delete(String id) {
        return dao().delete(this.getEntityClass(), id);
    }


    /**
     * 批量删除
     *
     * @param ids ID数组
     */
    @Override
    public void delete(Integer[] ids) {
        dao().clear(this.getEntityClass(), Cnd.where("id", IN, ids));
    }

    /**
     * 批量删除
     *
     * @param ids ID数组
     */
    @Override
    public void delete(Long[] ids) {
        dao().clear(this.getEntityClass(), Cnd.where("id", IN, ids));
    }

    /**
     * 批量删除
     *
     * @param ids ID数组
     */
    @Override
    public void delete(String[] ids) {
        dao().clear(this.getEntityClass(), Cnd.where("id", IN, ids));
    }

    /**
     * 批量删除
     *
     * @param ids ID数组
     */
    @Override
    public void delete(List<String> ids) {
        dao().clear(this.getEntityClass(), Cnd.where("id", IN, ids));
    }


    /**
     * 清空表
     *
     * @return 影响的行数
     */
    @Override
    public int clear() {
        return dao().clear(this.getEntityClass());
    }

    /**
     * 清空表
     *
     * @return  影响的行数
     */
    @Override
    public int clear(String tableName) {
        return dao().clear(tableName);
    }

    /**
     * 按条件清除一组数据
     *
     * @return  影响的行数
     */
    @Override
    public int clear(Condition cnd) {
        return dao().clear(this.getEntityClass(), cnd);
    }

    /**
     * 按条件清除一组数据
     *
     * @return  影响的行数
     */
    @Override
    public int clear(String tableName, Condition cnd) {
        return dao().clear(tableName, cnd);
    }

    /**
     * 伪删除
     *
     * @param id 对象ID
     * @return   影响的行数
     */
    @Override
    public int vDelete(String id) {
        return dao().update(this.getEntityClass(), Chain.make("delFlag", true), Cnd.where("id", EQ, id));
    }

    /**
     * 批量伪删除
     *
     * @param ids   对象ID数组
     * @return      影响的行数
     */
    @Override
    public int vDelete(String[] ids) {
        return dao().update(this.getEntityClass(), Chain.make("delFlag", true), Cnd.where("id", IN, ids));
    }

    /**
     * 批量伪删除
     *
     * @param ids   对象ID数组
     * @return      影响的行数
     */
    @Override
    public int vDelete(List<String> ids) {
        return dao().update(this.getEntityClass(), Chain.make("delFlag", true), Cnd.where("id", IN, ids));
    }

    /**
     * 根据条件进行伪删除
     *
     * @param cnd   WHERE 条件
     * @return      影响的行数
     */
    @Override
    public int vDelete(Condition cnd) {
        return dao().update(this.getEntityClass(), Chain.make("delFlag", true), cnd);
    }

    /**
     * 根据条件进行伪删除
     * @param tableName 表名
     * @param cnd   WHERE 条件
     * @return      影响的行数
     */
    @Override
    public int vDelete(String tableName, Condition cnd) {
        return dao().update(tableName, Chain.make("delFlag", true), cnd);
    }

}

