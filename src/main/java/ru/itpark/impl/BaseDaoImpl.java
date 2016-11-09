package ru.itpark.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.itpark.dao.BaseDao;
import ru.itpark.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */

public class BaseDaoImpl<E extends BaseEntity> implements BaseDao<E> {

  private Class<E> entityClass;

  @PersistenceContext
  private EntityManager entityManager;

  public BaseDaoImpl(Class<E> entityClass) {
    init(entityClass);
  }

  protected final void init(Class<E> entityClass) {
    this.entityClass = entityClass;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }


  @Transactional
  public void saveOrUpdate(E entity) {
    entityManager.persist(entity);
  }

  @Transactional
  public void remove(E entity) {
    entityManager.remove(entity);
  }
}
