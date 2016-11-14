package fr.micropole.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.micropole.dao.GenericDAO;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected T             type;

    @SuppressWarnings( "unchecked" )
    public GenericDAOImpl() {
        super();
        this.type = (T) new Object();
    }

    @Override
    public T create( T t ) {
        this.entityManager.merge( t );
        return t;
    }

    @SuppressWarnings( "unchecked" )
    public List<T> readAll() {

        String query = "SELECT t FROM " + type.getClass().getSimpleName() + " t";
        return (List<T>) this.entityManager.createQuery( query ).getResultList();

    }

    public void remove( T t ) {
        this.entityManager.remove( t );
    }

    @SuppressWarnings( "unchecked" )
    public T findById( Integer id ) {
        String query = "SELECT t FROM " + type.getClass().getSimpleName() + " t WHERE t.id = :id";
        T t = (T) this.entityManager.createQuery( query ).setParameter( "id", id ).getSingleResult();
        return t;
    }

    public T getType() {
        return type;
    }

    public void setType( T type ) {
        this.type = type;
    }

}
