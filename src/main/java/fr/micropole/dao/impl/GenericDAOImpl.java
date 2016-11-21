package fr.micropole.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.util.StringUtils;

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

    @SuppressWarnings( "unchecked" )
    public List<T> readTransactionBetweenDate( String dateDebut, String dateFin ) throws ParseException {

        SimpleDateFormat sm = new SimpleDateFormat( "yyyyMMdd", Locale.ENGLISH );

        if ( StringUtils.isEmpty( dateDebut ) ) {
            dateDebut = "19700101";
        }
        if ( StringUtils.isEmpty( dateFin ) ) {
            dateFin = "20500101";
        }
        Date dateDeb = sm.parse( dateDebut );
        Date dateF = sm.parse( dateFin );

        String query = "SELECT t FROM " + type.getClass().getSimpleName()
                + " t WHERE t.date BETWEEN :dateDebut AND :dateFin ORDER BY t.date DESC";
        return (List<T>) this.entityManager
                .createQuery( query )
                .setParameter( "dateDebut", dateDeb )
                .setParameter( "dateFin", dateF )
                .getResultList();
    }

    public T getType() {
        return type;
    }

    public void setType( T type ) {
        this.type = type;
    }

}
