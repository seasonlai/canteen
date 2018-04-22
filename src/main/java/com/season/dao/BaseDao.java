package com.season.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
public class BaseDao<T> {

    private Class<T> entityClass;

    protected HibernateTemplate hibernateTemplate;

    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    public T load(Serializable id) {
        return hibernateTemplate.load(entityClass, id);
    }

    public T get(Serializable id) {
        return hibernateTemplate.get(entityClass, id);
    }

    public List<T> loadAll() {
        return hibernateTemplate.loadAll(entityClass);
    }

    public void save(T entity) {
        hibernateTemplate.save(entity);
    }

    public void remove(T entity) {
        hibernateTemplate.delete(entity);
    }

    public void remove(Collection<T> entity) {
        hibernateTemplate.deleteAll(entity);
    }

    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    public List runSql(String sql, Object... values) {
        return createSqlQuery(sql, values).list();
    }

    public List find(String sql) {
        return hibernateTemplate.find(sql);
    }

    public List find(String sql, Object... params) {
        return hibernateTemplate.find(sql, params);
    }

    public void initialize(Object entity) {
        hibernateTemplate.initialize(entity);
    }

    public SQLQuery createSqlQuery(String sql, Object... values) {
        Assert.hasText(sql);
        SQLQuery query = getSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "页数应从1开始");
        String countStr;
        countStr = "select count(*) " + removeOrders(removeSelect(hql));
        List countlist = hibernateTemplate.find(countStr, values);
        long totalCount = countlist.size() > 0 ? (Long) countlist.get(0) : 0;
        return pagedQuery(hql, totalCount, pageNo, pageSize, values);
    }

    public Page pagedQuery(String hql, long totalCount, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "页数应从1开始");
        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, values);
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page(startIndex, totalCount, pageSize, list);
    }

    private Query createQuery(String hql, Object[] values) {
        Assert.hasText(hql);
        Query query = getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     *
     * @see #pagedQuery(String, int, int, Object[])
     */
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     *
     * @see #pagedQuery(String, int, int, Object[])
     */
    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}