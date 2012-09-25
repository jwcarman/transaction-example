package com.gafri.example.transaction.domain.repository.impl;

import org.domdrides.repository.Repository;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractJpaRepository<EntityType extends org.domdrides.entity.Entity<IdType>, IdType extends java.io.Serializable> extends JpaDaoSupport implements Repository<EntityType, IdType>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Class<EntityType> entityType;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractJpaRepository(Class<EntityType> entityType)
    {
        this.entityType = entityType;
    }

//----------------------------------------------------------------------------------------------------------------------
// Repository Implementation
//----------------------------------------------------------------------------------------------------------------------

    public EntityType add(final EntityType entity)
    {
        return execute(new JpaCallback<EntityType>()
        {
            @Override
            public EntityType doInJpa(EntityManager entityManager) throws PersistenceException
            {
                entityManager.persist(entity);
                return entity;
            }
        });

    }

    public boolean contains(EntityType entity)
    {
        return getById(entity.getId()) != null;
    }

    @SuppressWarnings("unchecked")
    public Set<EntityType> getAll()
    {
        return execute(new JpaCallback<Set<EntityType>>()
        {
            @Override
            public Set<EntityType> doInJpa(EntityManager entityManager) throws PersistenceException
            {
                final String jpaql = "select x from " + entityType.getName() + " x";
                return asSet(entityManager.createQuery(jpaql));
            }
        });

    }

    public EntityType getById(final IdType id)
    {
        return execute(new JpaCallback<EntityType>()
        {
            @Override
            public EntityType doInJpa(EntityManager entityManager) throws PersistenceException
            {
                return entityManager.find(entityType, id);
            }
        });
    }

    public void remove(final EntityType entity)
    {
        execute(new JpaCallback<Void>()
        {
            @Override
            public Void doInJpa(EntityManager entityManager) throws PersistenceException
            {
                entityManager.remove(entity);
                return null;
            }
        });
    }

    public int size()
    {
        return execute(new JpaCallback<Integer>()
        {
            @Override
            public Integer doInJpa(EntityManager entityManager) throws PersistenceException
            {
                Number count = singleResult(entityManager.createQuery(("select count(*) from " + entityType.getName())));
                return count == null ? 0 : count.intValue();
            }
        });
    }

    public EntityType update(final EntityType entity)
    {
        return execute(new JpaCallback<EntityType>()
        {
            @Override
            public EntityType doInJpa(EntityManager entityManager) throws PersistenceException
            {
                return entityManager.merge(entity);
            }
        });
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    protected List<EntityType> asList(Query query)
    {
        return new ArrayList<EntityType>(query.getResultList());
    }

    @SuppressWarnings("unchecked")
    protected Set<EntityType> asSet(Query query)
    {
        return new HashSet<EntityType>(query.getResultList());
    }

    protected <T> T execute(JpaCallback<T> callback)
    {
        return getJpaTemplate().execute(callback);
    }

    @SuppressWarnings("unchecked")
    protected <T> T singleResult(Query query)
    {
        return (T) query.getSingleResult();
    }
}
