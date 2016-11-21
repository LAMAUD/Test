package fr.micropole.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import fr.micropole.dao.DAOTransaction;
import fr.micropole.pojo.Transaction;

@Repository
public class DAOImplTransaction extends GenericDAOImpl<Transaction> implements DAOTransaction {

    public DAOImplTransaction() {
        super();
        this.type = new Transaction();
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Transaction> readTransactionBetweenDateAndByCategory( String dateDebut, String dateFin, String category )
            throws ParseException {

        SimpleDateFormat sm = new SimpleDateFormat( "yyyyMMdd", Locale.ENGLISH );

        if ( StringUtils.isEmpty( dateDebut ) ) {
            dateDebut = "19700101";
        }
        if ( StringUtils.isEmpty( dateFin ) ) {
            dateFin = "20500101";
        }
        Date dateDeb = sm.parse( dateDebut );
        Date dateF = sm.parse( dateFin );

        String query = "SELECT t FROM Transaction t JOIN FETCH t.category c WHERE c.name = :name AND t.date BETWEEN :dateDebut AND :dateFin ORDER BY t.date DESC";
        return entityManager.createQuery( query ).setParameter( "name", category )
                .setParameter( "dateDebut", dateDeb ).setParameter( "dateFin", dateF ).getResultList();
    }

}
