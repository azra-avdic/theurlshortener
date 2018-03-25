package infobiptask.theurlshortener.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.core.GenericTypeResolver;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractDAO<T, K extends Serializable> {
    protected EntityManager entityManager;
    protected CriteriaBuilder builder;
    private final Class<T> genericType;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        Class<?>[] genericTypes = GenericTypeResolver.resolveTypeArguments(getClass(), AbstractDAO.class);
        genericType = (Class<T>) genericTypes[0];
    }

    public T persist(final T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void remove(final T entity) {
        entityManager.remove(entity);
    }

    public void removeByPK(final K primaryKey) {
        T entity = findByPK(primaryKey);
        remove(entity);
    }

    public void merge(final T entity) {
        entityManager.merge(entity);
    }

    public T findByPK(final K primaryKey) {
        return entityManager.find(genericType, primaryKey);
    }

    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery("from " + genericType.getName(), genericType);
        return query.getResultList();
    }

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
    }

    public int countAll() {
        TypedQuery<Long> q = entityManager.createQuery("SELECT COUNT(id) FROM " + genericType.getName(), Long.class);
        Long count = q.getSingleResult();
        return count.intValue();
    }

    public boolean existsByPK(final K primaryKey) {
        T object = findByPK(primaryKey);
        if (object != null) {
            return true;
        }
        return false;
    }
}
