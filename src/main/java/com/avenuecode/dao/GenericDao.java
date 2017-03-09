package com.avenuecode.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

class GenericDao<T, I extends Serializable> {

    private static SessionFactory sessionFactory = null;  
    private static StandardServiceRegistry serviceRegistry = null;  
    
    private void configureSessionFactory() {  
    	if (serviceRegistry == null) 
        	serviceRegistry = new StandardServiceRegistryBuilder().configure().build();        	
	
        if (sessionFactory == null)
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().getSessionFactoryBuilder().build();
    }    
	
	private Class<T> persistedClass;

	protected GenericDao() {
		configureSessionFactory();
	}

	protected GenericDao(Class<T> persistedClass) {
	   this();
       this.persistedClass = persistedClass;
	}
	
	public T save(T entity) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			session.flush();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		
		return entity;
	}
	
	public void delete(I id) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			T entity = session.find(persistedClass, id);
			session.remove(entity);
			session.flush();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	protected List<T> getList(String query) {
		Session session = null;
		List<T> l = null;
		try {
			session = sessionFactory.openSession();
			l = session.createQuery(query).list();
		} finally {
			session.close();
		}
		return l;
	}
	
	public T findById(I id) {
		Session session = null;
		T entity = null;
		
		try {
			session = sessionFactory.openSession();
			entity = session.find(persistedClass, id);
		} finally {
			session.close();
		}
		
		return entity;
	}
}

