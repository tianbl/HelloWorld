package com.eastsoft.pub.dao.impl;


import com.eastsoft.pub.dao.BaseDao;
import com.eastsoft.pub.uitl.pager.Pager;
import com.eastsoft.pub.uitl.pager.SqlUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.util.Assert;
import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseDaoImpl
 * @Description: Dao类 - 基类
 * @author zhangym
 * @date 2014年4月22日14:14:23
 *
 * @param <T>
 * @param <PK>
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	protected Class<T> entityClass;

	@Resource
	protected SessionFactory sessionFactory;


	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		this.entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}


	@SuppressWarnings("unchecked")
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName()
				+ " as model where model.id in(:ids)";
		return getSession()
				.createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return getSession()
				.createQuery(hql).setParameter(0, value).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession()
				.createQuery(hql).list();
	}

	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().getSessionFactory()
				.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null
					&& StringUtils.equalsIgnoreCase((String) oldValue,
					(String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}

	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) throws Exception {
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	public void update(T entity) throws Exception {
		Assert.notNull(entity, "entity is required");
		// getSession().clear();
		getSession().update(entity);
	}

	public void delete(T entity) throws Exception {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	public boolean delete(PK id) {
		Assert.notNull(id, "id is required");
		boolean flag = true;
		try {
			T entity = load(id);
			getSession().delete(entity);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public boolean delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		boolean flag = true;
		for (PK id : ids) {
			T entity = load(id);
			try {
				getSession().delete(entity);
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}


	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	@SuppressWarnings("unchecked")
	public T getT(String Hql) {
		return (T) getSession().getSessionFactory()
				.getCurrentSession().createQuery(Hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> getByHql(String Hql) {
		return getSession()
				.createQuery(Hql).list();
	}

	/**
	 * 批量save使用spring控制事务
	 */
	public void doInHibernate(List<T> list) throws HibernateException,
			SQLException {

		for (int i = 0; i < list.size(); i++) {
			getSession().save(list.get(i));
			if (i != 0) {
				if (i % 50 == 0) {
					this.flush();
					this.clear();
				}
			}

		}
	}

	/**
	 * 批量del使用spring控制事务
	 */
	public void doDelHibernate(List<T> list) throws HibernateException,
			SQLException {
		for (int i = 0; i < list.size(); i++) {
			getSession().delete(list.get(i));
			if (i != 0) {
				if (i % 50 == 0) {
					this.flush();
					this.clear();
				}
			}

		}
	}

	/**
	 * 批量update使用spring控制事务
	 */
	public void doUpdatelHibernate(List<T> list) throws HibernateException,
			SQLException {
		for (int i = 0; i < list.size(); i++) {
			getSession().update(list.get(i));
			if (i != 0) {
				if (i % 50 == 0) {
					this.flush();
					this.clear();
				}
			}

		}
	}



	/**
	 *
	 * @Title: findByPagerSql
	 * @Description: 原生sql 分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 *            查询sql语句，一般应含order by
	 * @param sqlcount
	 *            查询总数量的sql语句，一般应为null，复合查询重写可提高效率
	 * @param map
	 *            参数值
	 * @return
	 *
	 */
	public Pager findByPagerSql(int pageNumber, int pageSize, String sql, String sqlcount, Map<String, Object> map) {
		Pager pager = new Pager();
		pager.setPageNumber(pageNumber);
		pager.setPageSize(pageSize);
		pager.setSql(sql);
		pager.setSqlcount(sqlcount);
		pager.setMap(map);
		return findByPagerSql(pager);
	}
	public Pager findByPagerSql(int pageNumber, int pageSize, String sql, String sqlcount) {
		Map<String, Object> map = new HashMap<String, Object>();
		return findByPagerSql(pageNumber, pageSize, sql, sqlcount, map);
	}

	/**
	 *
	 * @Title: findByPagerSql
	 * @Description: 原生sql分页查询
	 * @param pager
	 * @return
	 * 
	 */
	public Pager findByPagerSql(Pager pager) {
		if (pager == null) {
			pager = new Pager();
		}
		String nsqlcout = StringUtils.isEmpty(pager.getSqlcount()) ? SqlUtil.getSqlCount(pager.getSql()) : pager.getSqlcount();
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(nsqlcout);
		Map<String, Object> map = pager.getMap();
		if (map != null) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		pager.setTotalCount(Integer.parseInt(query.uniqueResult().toString()));
		String nsql = pager.getSql();
		//		System.out.println(nsql);
		query = session.createSQLQuery(nsql);
		if (map != null) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		pager.setList(query.setFirstResult(pager.getOffset()).setMaxResults(pager.getLimit()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list());
		return pager;
	}

}
