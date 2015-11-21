package com.eastsoft.pub.dao;

import org.hibernate.HibernateException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseDao
 * @Description:
 * @author zhangym
 * @date 2014��4��22��14:14:23
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T, PK extends Serializable>{

	/**
	 * ����ID��ȡʵ�����.
	 *
	 * @param id
	 *            ��¼ID
	 * @return ʵ�����
	 */
	public T get(PK id);

	/**
	 * ����ID��ȡʵ�����.
	 *
	 * @param id
	 *            ��¼ID
	 * @return ʵ�����
	 */
	public T load(PK id);

	/**
	 * ����ID�����ȡʵ����󼯺�.
	 *
	 * @param ids
	 *            ID��������
	 *
	 * @return ʵ����󼯺�
	 */
	public List<T> get(PK[] ids);

	/**
	 * ����������������ֵ��ȡʵ�����.
	 *
	 * @param propertyName
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ʵ�����
	 */
	public T get(String propertyName, Object value);

	/**
	 * ����HQL���ʵ�����.
	 *
	 * @param
	 *
	 * @param
	 *
	 * @return ʵ�����
	 */
	public T getT(String Hql);

	public List<T> getByHql(String Hql);

	/**
	 * ����������������ֵ��ȡʵ����󼯺�.
	 *
	 * @param propertyName
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ʵ����󼯺�
	 */
	public List<T> getList(String propertyName, Object value);

	/**
	 * ��ȡ����ʵ����󼯺�.
	 *
	 * @return ʵ����󼯺�
	 */
	public List<T> getAll();

	/**
	 * ��ȡ����ʵ���������.
	 *
	 * @return ʵ���������
	 */
	public Long getTotalCount();

	/**
	 * �������������޸�ǰ������ֵ�ж������ݿ����Ƿ�Ψһ(�����޸ĵ�ֵ��ԭ��ֵ�����ֱ�ӷ���true).
	 *
	 * @param propertyName
	 *            ��������
	 * @param oldValue
	 *            �޸�ǰ������ֵ
	 * @param oldValue
	 *            �޸ĺ������ֵ
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue,
							Object newValue);

	/**
	 * �����������ж������Ƿ��Ѵ���.
	 *
	 * @param propertyName
	 *            ��������
	 * @param value
	 *            ֵ
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * ����ʵ�����.
	 *
	 * @param entity
	 *            ����
	 * @return ID
	 * @throws Exception
	 */
	public PK save(T entity) throws Exception;

	/**
	 * ����ʵ�����.
	 *
	 * @param entity
	 *            ����
	 */
	public void update(T entity) throws Exception;

	/**
	 * ɾ��ʵ�����.
	 *
	 * @param entity
	 *            ����
	 * @return
	 */
	public void delete(T entity) throws Exception;

	/**
	 * ����IDɾ��ʵ�����.
	 *
	 * @param id
	 *            ��¼ID
	 */
	public boolean delete(PK id);

	/**
	 * ����ID����ɾ��ʵ�����.
	 *
	 * @param ids
	 *            ID����
	 */
	public boolean delete(PK[] ids);

	/**
	 * ˢ��session.
	 *
	 */
	public void flush();

	/**
	 * ���Session.
	 *
	 */
	public void clear();

	/**
	 * ���ĳһ����.
	 *
	 * @param object
	 *            ��Ҫ����Ķ���
	 */
	public void evict(Object object);

	/**
	 * ����saveʹ��spring��������
	 */
	public void doInHibernate(List<T> list) throws HibernateException,
			SQLException;

	/**
	 * ����delʹ��spring��������
	 */
	public void doDelHibernate(List<T> list) throws HibernateException,
			SQLException;

	/**
	 * ����updateʹ��spring��������
	 */
	public void doUpdatelHibernate(List<T> list) throws HibernateException,
			SQLException;

}