package ru.itpark.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.BaseDao;
import ru.itpark.entity.BaseEntity;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */

public class BaseDaoImpl<E extends BaseEntity> implements BaseDao<E> {

  private Class<E> entityClass;

  @Autowired
  private SessionFactory sessionFactory;

  public BaseDaoImpl(Class<E> entityClass) {
    init(entityClass);
  }

  protected final void init(Class<E> entityClass) {
    this.entityClass = entityClass;
  }


  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }


  @Transactional
  public void saveOrUpdate(E entity) {
    getSession().saveOrUpdate(entity);
  }

  protected Criteria getCriteria() {
    return getSession().createCriteria(entityClass);
  }
}
