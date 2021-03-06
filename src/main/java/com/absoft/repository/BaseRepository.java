package com.absoft.repository;

import com.absoft.model.BaseEntity;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * @author Diego Arantes
 * @param <T> Classe
 */
public abstract class BaseRepository<T extends BaseEntity, PK extends Number> {
/**
     * Classe da entidade, necessário para o método
     * <code>EntityManager.find</code>.
     */
    private Class<T> entityClass;

    @Resource(lookup = "java:/ABSoft")
    DataSource datasource;

    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T e) {
        if (e.getId() != null) {
            return getEntityManager().merge(e);
        } else {
            getEntityManager().persist(e);
            return e;
        }
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(PK id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        return getEntityManager().createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
    }
    
    public Long count() {
        Query q = getEntityManager().createQuery("select count(c) from " + entityClass.getSimpleName() + " c", Long.class);
        return (Long) q.getSingleResult();
    }

    /**
     * @return retorna uma conexão jdbc
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

    /**
     * Exige a definição do <code>EntityManager</code> responsável pelas
     * operações de persistência.
     *
     * @return
     */
    protected abstract EntityManager getEntityManager();
}
